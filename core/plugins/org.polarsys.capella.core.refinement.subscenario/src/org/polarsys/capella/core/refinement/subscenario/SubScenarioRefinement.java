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
package org.polarsys.capella.core.refinement.subscenario;

import org.eclipse.core.runtime.IProgressMonitor;

import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.ComponentArchitecture;
import org.polarsys.capella.core.data.interaction.Scenario;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.model.helpers.RefinementLinkExt;
import org.polarsys.capella.core.model.helpers.ScenarioExt;
import org.polarsys.capella.core.model.utils.CapellaLayerCheckingExt;
import org.polarsys.capella.core.refinement.processor.StructureSynchronizationProcessor;
import org.polarsys.capella.core.refinement.scenarios.core.ScenarioRefinement;
import org.polarsys.capella.core.refinement.scenarios.core.exceptions.ProcessorException;

/**
 */
public class SubScenarioRefinement extends ScenarioRefinement {
 
  private String scenarioName = null;
  private Scenario targetScenario = null;


  public SubScenarioRefinement(Scenario srcDiagram, Scenario tgtDiagram) {
    super(srcDiagram, ScenarioExt.getContainer(tgtDiagram), isIntraLogicalLayer(srcDiagram, tgtDiagram), false);
    targetScenario = tgtDiagram;
  }

  public SubScenarioRefinement(Scenario srcDiagram, ComponentArchitecture arch, String scenarioName) {
    super(srcDiagram, arch, isIntraLogicalLayer(srcDiagram, arch), false);
    this.scenarioName = scenarioName;
  }

  public SubScenarioRefinement(Scenario srcDiagram_, Component component, String scenarioName) {
    super(srcDiagram_, component, isIntraLogicalLayer(srcDiagram_, component), false);
    this.scenarioName = scenarioName;
  }

  /**
   * @see org.polarsys.capella.core.refinement.scenarios.core.ScenarioRefinement#execute()
   */
  @Override
  public void execute(IProgressMonitor progressMonitor) throws ProcessorException {
    if (targetScenario == null) {
      /**
       * Add a new sub-scenario
       */
      StructureSynchronizationProcessor proc = new StructureSynchronizationProcessor(getContext(), getTarget(), true);
      proc.execute(progressMonitor);

      targetScenario = (Scenario) proc.getResult();
      applyNamingRule(targetScenario, getTarget(), scenarioName);
    }
    else {
      /**
       * Attach an existing sub-scenario
       */
      RefinementLinkExt.createRefinementTraceabilityLink(targetScenario, getContext());
    }

    forceTargetScenario(targetScenario);

    super.execute(progressMonitor);
  }
  
  
  private static boolean isIntraLogicalLayer(CapellaElement srcDiagram, CapellaElement tgtDiagram) {
    if (CapellaLayerCheckingExt.isInLogicalLayer(srcDiagram) && CapellaLayerCheckingExt.isInLogicalLayer(tgtDiagram))
      return true;
    
    return false;
  }
  
}
