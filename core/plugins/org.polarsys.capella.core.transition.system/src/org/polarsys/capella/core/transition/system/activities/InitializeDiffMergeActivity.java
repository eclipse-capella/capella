/*******************************************************************************
 * Copyright (c) 2006, 2022 THALES GLOBAL SERVICES.
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 * 
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/

package org.polarsys.capella.core.transition.system.activities;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.transition.common.activities.InitializeDiffMergeFromTransformationActivity;
import org.polarsys.capella.core.transition.common.handlers.IHandler;
import org.polarsys.capella.core.transition.common.handlers.merge.IMergeHandler;
import org.polarsys.capella.core.transition.common.handlers.transformation.TransformationHandlerHelper;
import org.polarsys.capella.core.transition.common.merge.scope.IModelScopeFilter;
import org.polarsys.capella.core.transition.system.handlers.merge.AttributeDescriptionValueFromSource;
import org.polarsys.capella.core.transition.system.handlers.merge.AttributeNameValueFromSource;
import org.polarsys.capella.core.transition.system.handlers.merge.AttributeSummaryValueFromSource;
import org.polarsys.capella.core.transition.system.handlers.merge.ManyToOneCategoryFilter;
import org.polarsys.capella.core.transition.system.handlers.merge.OneToManyCategoryFilter;
import org.polarsys.capella.core.transition.system.handlers.merge.PartPropagationCategoryFilter;
import org.polarsys.capella.core.transition.system.handlers.merge.RootCategoryFilter;
import org.polarsys.kitalpha.cadence.core.api.parameter.ActivityParameters;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 *
 */
public abstract class InitializeDiffMergeActivity extends InitializeDiffMergeFromTransformationActivity {

  public static final String ID = "org.polarsys.capella.core.transition.system.activities.InitializeDiffMergeActivity"; //$NON-NLS-1$

  @Override
  protected abstract IHandler createDefaultTraceabilitySourceHandler(IContext context);

  @Override
  protected abstract IHandler createDefaultTraceabilityTargetHandler(IContext context);

  @Override
  protected IStatus initializeCategoriesHandlers(IContext context, IMergeHandler handler,
      ActivityParameters activityParams) {
    super.initializeCategoriesHandlers(context, handler, activityParams);

    handler.addCategory(new AttributeNameValueFromSource(context), context);
    handler.addCategory(new AttributeSummaryValueFromSource(context), context);
    handler.addCategory(new AttributeDescriptionValueFromSource(context), context);
    handler.addCategory(new PartPropagationCategoryFilter(context), context);
    handler.addCategory(new RootCategoryFilter(context), context);
    handler.addCategory(new OneToManyCategoryFilter(context), context);
    handler.addCategory(new ManyToOneCategoryFilter(context), context);

    return Status.OK_STATUS;
  }

  @Override
  protected IModelScopeFilter getTargetFilter(final IContext context) {

    return new IModelScopeFilter() {
      public boolean accepts(EObject element) {

        // With a transformation, we filter the target model according to the transformed architecture.
        BlockArchitecture architecture = BlockArchitectureExt.getRootBlockArchitecture(element);
        if (architecture == null) {
          return true;
        }
        EClass targetArchitecture = TransformationHandlerHelper.getInstance(context).getTargetType(architecture,
            context);
        if ((targetArchitecture != null) && (architecture.eClass() == targetArchitecture)) {
          return true;
        }

        return false;

      }
    };
  }

}
