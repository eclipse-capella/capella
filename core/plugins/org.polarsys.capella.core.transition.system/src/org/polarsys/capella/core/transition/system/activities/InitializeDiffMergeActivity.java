/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/

package org.polarsys.capella.core.transition.system.activities;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.transition.common.activities.InitializeDiffMergeFromTransformationActivity;
import org.polarsys.capella.core.transition.common.handlers.IHandler;
import org.polarsys.capella.core.transition.common.handlers.traceability.CompoundTraceabilityHandler;
import org.polarsys.capella.core.transition.common.handlers.traceability.config.ITraceabilityConfiguration;
import org.polarsys.capella.core.transition.common.handlers.transformation.TransformationHandlerHelper;
import org.polarsys.capella.core.transition.common.merge.scope.IModelScopeFilter;
import org.polarsys.capella.core.transition.system.handlers.traceability.config.MergeSourceConfiguration;
import org.polarsys.capella.core.transition.system.handlers.traceability.config.MergeTargetConfiguration;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 *
 */
public class InitializeDiffMergeActivity extends InitializeDiffMergeFromTransformationActivity {

  public static final String ID = "org.polarsys.capella.core.transition.system.activities.InitializeDiffMergeActivity"; //$NON-NLS-1$

  @Override
  protected IHandler createDefaultTraceabilitySourceHandler(IContext context) {
    ITraceabilityConfiguration configuration = new MergeSourceConfiguration();
    return new CompoundTraceabilityHandler(configuration);
  }

  @Override
  protected IHandler createDefaultTraceabilityTargetHandler(IContext context) {
    ITraceabilityConfiguration configuration = new MergeTargetConfiguration();
    return new CompoundTraceabilityHandler(configuration);
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
        EClass targetArchitecture = TransformationHandlerHelper.getInstance(context).getTargetType(architecture, context);
        if ((targetArchitecture != null) && (architecture.eClass() == targetArchitecture)) {
          return true;
        }

        return false;

      }
    };
  }

}
