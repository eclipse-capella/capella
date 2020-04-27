/*******************************************************************************
 * Copyright (c) 2016, 2019 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.transition.system.handlers.merge;

import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.ctx.CtxPackage;
import org.polarsys.capella.core.data.fa.FaPackage;
import org.polarsys.capella.core.data.interaction.InteractionPackage;
import org.polarsys.capella.core.data.pa.deployment.DeploymentPackage;
import org.polarsys.capella.core.transition.common.handlers.merge.IMergeHandler;
import org.polarsys.capella.core.transition.system.preferences.PreferenceConstants;
import org.polarsys.kitalpha.emde.model.EmdePackage;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

public class CapellaClassFilters {

  private CapellaClassFilters(){}
  
  public static void addClassFilters(IMergeHandler handler, IContext context){
    handler.addCategory(new ActorFilter(context), context);
    handler.addCategory(new ComponentFilter(context), context);

    handler.addCategory(new FunctionFilter(context), context);
    handler.addCategory(new FunctionalChainFilter(context), context);

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
    handler.addCategory(new EClassCategoryFilter(context, EmdePackage.Literals.ELEMENT_EXTENSION, "Viewpoint elements", "Viewpoint"),
        context);
  }

}
