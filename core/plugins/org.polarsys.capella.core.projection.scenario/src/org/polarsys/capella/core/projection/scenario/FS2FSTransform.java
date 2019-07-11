/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
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
import org.polarsys.capella.core.data.interaction.Scenario;
import org.polarsys.capella.core.data.interaction.ScenarioKind;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.projection.common.CapellaEngine;
import org.polarsys.capella.core.projection.common.context.IContext;
import org.polarsys.capella.core.projection.scenario.es2es.handlers.ScenarioES2ESHandler;
import org.polarsys.capella.core.projection.scenario.handlers.IScenarioHandler;
import org.polarsys.capella.core.tiger.ITransfo;
import org.polarsys.capella.core.tiger.impl.TransfoEngine;

/**
 */
public class FS2FSTransform extends ScenarioTransform {

  /**
   * Tiger context used. This context is declared implementing an extension point.
   */
  private static final String CAPELLA_SCENARIO_RULES = "org.polarsys.capella.core.projection.scenario.es2es"; //$NON-NLS-1$

  @Override
  protected String getRules() {
    return CAPELLA_SCENARIO_RULES;
  }

  @Override
  protected IScenarioHandler createScenarioHandler(IContext context_p) {
    return new ScenarioES2ESHandler();
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

    return (scenario_p.getKind() == ScenarioKind.FUNCTIONAL) && !sourceBlock.equals(targetBlock);
  }
}
