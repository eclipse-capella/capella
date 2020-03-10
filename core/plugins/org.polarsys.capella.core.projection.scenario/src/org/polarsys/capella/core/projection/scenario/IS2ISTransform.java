/*******************************************************************************
 * Copyright (c) 2020 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.projection.scenario;

import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.data.modellingcore.AbstractTrace;
import org.polarsys.capella.common.data.modellingcore.TraceableElement;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.fa.FaPackage;
import org.polarsys.capella.core.data.information.InformationPackage;
import org.polarsys.capella.core.data.interaction.InteractionPackage;
import org.polarsys.capella.core.data.interaction.Scenario;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.projection.common.CapellaEngine;
import org.polarsys.capella.core.projection.common.context.IContext;
import org.polarsys.capella.core.projection.scenario.handlers.IScenarioHandler;
import org.polarsys.capella.core.projection.scenario.is2is.handler.ScenarioIS2ISHandler;
import org.polarsys.capella.core.tiger.ITransfo;
import org.polarsys.capella.core.tiger.ITransfoRuleBase;
import org.polarsys.capella.core.tiger.impl.TransfoEngine;

/**
 */
public class IS2ISTransform extends ScenarioTransform {

  /**
   * Tiger context used. This context is declared implementing an extension point.
   */
  private static final String IS2IS_RULES = "org.polarsys.capella.core.projection.scenario.is2is"; //$NON-NLS-1$

  @Override
  protected String getRules() {
    return IS2IS_RULES;
  }

  protected ScenarioTransfo createTransfo(ITransfoRuleBase ruleBase) throws ClassNotFoundException {
    ScenarioTransfo transfo = super.createTransfo(ruleBase);
    
    transfo.setSpecificLinkKindFromMap(InteractionPackage.Literals.ABSTRACT_CAPABILITY, InteractionPackage.Literals.ABSTRACT_CAPABILITY,
        InteractionPackage.Literals.ABSTRACT_CAPABILITY_REALIZATION);
    transfo.setSpecificLinkKindFromMap(FaPackage.Literals.ABSTRACT_FUNCTION, FaPackage.Literals.ABSTRACT_FUNCTION, FaPackage.Literals.FUNCTION_REALIZATION);
    transfo.setSpecificLinkKindFromMap(CsPackage.Literals.COMPONENT, CsPackage.Literals.COMPONENT, CsPackage.Literals.COMPONENT_REALIZATION);
    transfo.setSpecificLinkKindFromMap(FaPackage.Literals.COMPONENT_EXCHANGE, FaPackage.Literals.COMPONENT_EXCHANGE, FaPackage.Literals.COMPONENT_EXCHANGE_REALIZATION);
    transfo.setSpecificLinkKindFromMap(InformationPackage.Literals.EXCHANGE_ITEM, InformationPackage.Literals.EXCHANGE_ITEM, InformationPackage.Literals.INFORMATION_REALIZATION);
    transfo.setSpecificLinkKindFromMap(FaPackage.Literals.FUNCTIONAL_CHAIN, FaPackage.Literals.FUNCTIONAL_CHAIN, FaPackage.Literals.FUNCTIONAL_CHAIN_REALIZATION);
    transfo.setSpecificLinkKindFromMap(FaPackage.Literals.FUNCTIONAL_EXCHANGE, FaPackage.Literals.FUNCTIONAL_EXCHANGE, FaPackage.Literals.FUNCTIONAL_EXCHANGE_REALIZATION);
    
    return transfo;
  }
  
  @Override
  protected IScenarioHandler createScenarioHandler(IContext context_p) {
    return new ScenarioIS2ISHandler();
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
  protected boolean isValidTransitionedScenario(Scenario contextElement, Scenario scenario) {
    BlockArchitecture sourceBlock = BlockArchitectureExt.getRootBlockArchitecture(contextElement);
    BlockArchitecture targetBlock = BlockArchitectureExt.getRootBlockArchitecture(scenario);

    return (scenario.getKind() == contextElement.getKind()) && !sourceBlock.equals(targetBlock);
  }

}
