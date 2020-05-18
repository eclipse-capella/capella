/*******************************************************************************
 * Copyright (c) 2006, 2019 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.projection.scenario.topdown;

import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.data.modellingcore.AbstractTrace;
import org.polarsys.capella.common.data.modellingcore.TraceableElement;
import org.polarsys.capella.core.data.capellacommon.CapellacommonPackage;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.fa.FaPackage;
import org.polarsys.capella.core.data.information.InformationPackage;
import org.polarsys.capella.core.data.interaction.InteractionPackage;
import org.polarsys.capella.core.data.interaction.Scenario;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.projection.common.CapellaEngine;
import org.polarsys.capella.core.projection.common.context.IContext;
import org.polarsys.capella.core.projection.scenario.ScenarioTransfo;
import org.polarsys.capella.core.projection.scenario.ScenarioTransform;
import org.polarsys.capella.core.projection.scenario.helpers.IScenarioHelper;
import org.polarsys.capella.core.tiger.ITransfo;
import org.polarsys.capella.core.tiger.ITransfoRuleBase;
import org.polarsys.capella.core.tiger.impl.TransfoEngine;

/**
 */
public class TopDownTransform extends ScenarioTransform {

  /**
   * Tiger context used. This context is declared implementing an extension point.
   */
  private static final String CAPELLA_SCENARIO_RULES = "org.polarsys.capella.core.projection.scenario.topdown"; //$NON-NLS-1$

  @Override
  protected String getRules() {
    return CAPELLA_SCENARIO_RULES;
  }

  protected ScenarioTransfo createTransfo(ITransfoRuleBase ruleBase_p) throws ClassNotFoundException {
    ScenarioTransfo transfo = super.createTransfo(ruleBase_p);
    
    transfo.setSpecificLinkKindFromMap(InteractionPackage.Literals.ABSTRACT_CAPABILITY, InteractionPackage.Literals.ABSTRACT_CAPABILITY,
        InteractionPackage.Literals.ABSTRACT_CAPABILITY_REALIZATION);
    transfo.setSpecificLinkKindFromMap(FaPackage.Literals.ABSTRACT_FUNCTION, FaPackage.Literals.ABSTRACT_FUNCTION, FaPackage.Literals.FUNCTION_REALIZATION);
    transfo.setSpecificLinkKindFromMap(CapellacommonPackage.Literals.ABSTRACT_STATE, CapellacommonPackage.Literals.ABSTRACT_STATE, CapellacommonPackage.Literals.ABSTRACT_STATE_REALIZATION);
    transfo.setSpecificLinkKindFromMap(CsPackage.Literals.COMPONENT, CsPackage.Literals.COMPONENT, CsPackage.Literals.COMPONENT_REALIZATION);
    transfo.setSpecificLinkKindFromMap(FaPackage.Literals.COMPONENT_EXCHANGE, FaPackage.Literals.COMPONENT_EXCHANGE, FaPackage.Literals.COMPONENT_EXCHANGE_REALIZATION);
    transfo.setSpecificLinkKindFromMap(InformationPackage.Literals.EXCHANGE_ITEM, InformationPackage.Literals.EXCHANGE_ITEM, InformationPackage.Literals.INFORMATION_REALIZATION);
    transfo.setSpecificLinkKindFromMap(FaPackage.Literals.FUNCTIONAL_CHAIN, FaPackage.Literals.FUNCTIONAL_CHAIN, FaPackage.Literals.FUNCTIONAL_CHAIN_REALIZATION);
    transfo.setSpecificLinkKindFromMap(FaPackage.Literals.FUNCTIONAL_EXCHANGE, FaPackage.Literals.FUNCTIONAL_EXCHANGE, FaPackage.Literals.FUNCTIONAL_EXCHANGE_REALIZATION);
    
    return transfo;
  }
  
  @Override
  protected IScenarioHelper createScenarioHandler(IContext context_p) {
    return new TopDownHelper();
  }

  /**
   * @see org.polarsys.capella.core.projection.common.AbstractTransform#retainContextElement(org.polarsys.capella.common.data.modellingcore.ModelElement,
   *      org.polarsys.capella.core.tiger.ITransfo)
   */
  @Override
  protected boolean retainContextElement(EObject contextElement, ITransfo transfo) {
    if (contextElement instanceof Scenario) {
      BlockArchitecture targetBlock = null;
      BlockArchitecture sourceBlock = BlockArchitectureExt.getRootBlockArchitecture(contextElement);
      for (AbstractTrace trace : sourceBlock.getIncomingTraces()) {
        TraceableElement targetElement = trace.getSourceElement();
        if (targetElement instanceof BlockArchitecture) {
          targetBlock = (BlockArchitecture) targetElement;
        }
      }

      if (targetBlock != null) {
        transfo.put(TransfoEngine.TRANSFO_SOURCE, contextElement);
        transfo.put(TransfoEngine.TRANSFO_TARGET, getTransitionedScenario((Scenario) contextElement, transfo));
        transfo.put(CapellaEngine.TRANSFO_TARGET_CONTAINER, targetBlock);
        return true;
      }
    }
    return false;
  }

  /**
   * @see org.polarsys.capella.core.projection.scenario.ScenarioTransform#isValidTransitionedScenario(org.polarsys.capella.common.data.modellingcore.ModelElement,
   *      org.polarsys.capella.core.data.interaction.Scenario)
   */
  @Override
  protected boolean isValidTransitionedScenario(Scenario contextElement_p, Scenario scenario_p) {
    BlockArchitecture sourceBlock = BlockArchitectureExt.getRootBlockArchitecture(contextElement_p);
    BlockArchitecture targetBlock = BlockArchitectureExt.getRootBlockArchitecture(scenario_p);

    return (scenario_p.getKind() == contextElement_p.getKind()) && !sourceBlock.equals(targetBlock);
  }

}
