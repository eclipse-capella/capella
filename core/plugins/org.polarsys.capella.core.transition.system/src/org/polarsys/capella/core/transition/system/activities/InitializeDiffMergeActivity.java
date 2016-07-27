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

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.ctx.CtxPackage;
import org.polarsys.capella.core.data.fa.FaPackage;
import org.polarsys.capella.core.data.interaction.InteractionPackage;
import org.polarsys.capella.core.data.pa.deployment.DeploymentPackage;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.transition.common.activities.InitializeDiffMergeFromTransformationActivity;
import org.polarsys.capella.core.transition.common.handlers.IHandler;
import org.polarsys.capella.core.transition.common.handlers.merge.DefaultFocusCategoryFilter;
import org.polarsys.capella.core.transition.common.handlers.merge.IMergeHandler;
import org.polarsys.capella.core.transition.common.handlers.traceability.CompoundTraceabilityHandler;
import org.polarsys.capella.core.transition.common.handlers.traceability.config.ITraceabilityConfiguration;
import org.polarsys.capella.core.transition.common.handlers.transformation.TransformationHandlerHelper;
import org.polarsys.capella.core.transition.common.merge.scope.IModelScopeFilter;
import org.polarsys.capella.core.transition.system.handlers.merge.ActorFilter;
import org.polarsys.capella.core.transition.system.handlers.merge.AttributeDescriptionValueFromSource;
import org.polarsys.capella.core.transition.system.handlers.merge.AttributeNameValueFromSource;
import org.polarsys.capella.core.transition.system.handlers.merge.AttributeSummaryValueFromSource;
import org.polarsys.capella.core.transition.system.handlers.merge.ComponentExchangeFilter;
import org.polarsys.capella.core.transition.system.handlers.merge.ComponentFilter;
import org.polarsys.capella.core.transition.system.handlers.merge.DataFilter;
import org.polarsys.capella.core.transition.system.handlers.merge.EClassCategoryFilter;
import org.polarsys.capella.core.transition.system.handlers.merge.ExchangeItemFilter;
import org.polarsys.capella.core.transition.system.handlers.merge.FunctionFilter;
import org.polarsys.capella.core.transition.system.handlers.merge.FunctionalExchangeFilter;
import org.polarsys.capella.core.transition.system.handlers.merge.InterfaceFilter;
import org.polarsys.capella.core.transition.system.handlers.merge.ManyToOneCategoryFilter;
import org.polarsys.capella.core.transition.system.handlers.merge.OneToManyCategoryFilter;
import org.polarsys.capella.core.transition.system.handlers.merge.PartPropagationCategoryFilter;
import org.polarsys.capella.core.transition.system.handlers.merge.PhysicalLinkFilter;
import org.polarsys.capella.core.transition.system.handlers.merge.PropertyValueFilter;
import org.polarsys.capella.core.transition.system.handlers.merge.RootCategoryFilter;
import org.polarsys.capella.core.transition.system.handlers.merge.StateMachineFilter;
import org.polarsys.capella.core.transition.system.handlers.traceability.config.MergeSourceConfiguration;
import org.polarsys.capella.core.transition.system.handlers.traceability.config.MergeTargetConfiguration;
import org.polarsys.capella.core.transition.system.preferences.PreferenceConstants;
import org.polarsys.kitalpha.cadence.core.api.parameter.ActivityParameters;
import org.polarsys.kitalpha.emde.model.EmdePackage;
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
  protected IStatus initializeCategoriesHandlers(IContext context, IMergeHandler handler,
      ActivityParameters activityParams) {
    super.initializeCategoriesHandlers(context, handler, activityParams);

    handler.addCategory(new ActorFilter(context), context);
    handler.addCategory(new ComponentFilter(context), context);

    handler.addCategory(new FunctionFilter(context), context);

    handler.addCategory(new EClassCategoryFilter(context, InteractionPackage.Literals.ABSTRACT_CAPABILITY,
        CtxPackage.Literals.CAPABILITY), context);

    handler.addCategory(new DataFilter(context), context);
    handler.addCategory(new InterfaceFilter(context), context);
    handler.addCategory(new ExchangeItemFilter(context), context);
    handler.addCategory(new StateMachineFilter(context), context);

    handler.addCategory(new FunctionalExchangeFilter(context), context);
    handler.addCategory(new ComponentExchangeFilter(context), context);
    handler.addCategory(new PhysicalLinkFilter(context), context);

    handler.addCategory(new EClassCategoryFilter(context, CsPackage.Literals.PART), context);

    handler.addCategory(new EClassCategoryFilter(context, FaPackage.Literals.FUNCTION_PORT,
        FaPackage.Literals.FUNCTION_INPUT_PORT, PreferenceConstants.P_FPort_TEXT), context);
    handler.addCategory(new EClassCategoryFilter(context, FaPackage.Literals.COMPONENT_PORT), context);
    handler.addCategory(new EClassCategoryFilter(context, CsPackage.Literals.PHYSICAL_PORT), context);

    handler.addCategory(new EClassCategoryFilter(context, CapellacorePackage.Literals.GENERALIZATION), context);
    handler.addCategory(new EClassCategoryFilter(context, FaPackage.Literals.COMPONENT_FUNCTIONAL_ALLOCATION), context);
    handler.addCategory(new EClassCategoryFilter(context, FaPackage.Literals.COMPONENT_EXCHANGE_ALLOCATION), context);
    handler.addCategory(
        new EClassCategoryFilter(context, FaPackage.Literals.COMPONENT_EXCHANGE_FUNCTIONAL_EXCHANGE_ALLOCATION),
        context);

    handler.addCategory(new EClassCategoryFilter(context, CsPackage.Literals.ABSTRACT_DEPLOYMENT_LINK,
        DeploymentPackage.Literals.PART_DEPLOYMENT_LINK, PreferenceConstants.P_DL_TEXT), context);

    handler.addCategory(new PropertyValueFilter(context), context);
    handler.addCategory(new EClassCategoryFilter(context, EmdePackage.Literals.ELEMENT_EXTENSION, "Viewpoint elements"),
        context);

    handler.addCategory(new AttributeNameValueFromSource(context), context);
    handler.addCategory(new AttributeSummaryValueFromSource(context), context);
    handler.addCategory(new AttributeDescriptionValueFromSource(context), context);
    handler.addCategory(new PartPropagationCategoryFilter(context), context);
    handler.addCategory(new DefaultFocusCategoryFilter(context), context);
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
