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
 
  private String _scenarioName = null;
  private Scenario _targetScenario = null;


  public SubScenarioRefinement(Scenario srcDiagram_p, Scenario tgtDiagram_p) {
    super(srcDiagram_p, ScenarioExt.getContainer(tgtDiagram_p), isIntraLogicalLayer(srcDiagram_p, tgtDiagram_p), false);
    _targetScenario = tgtDiagram_p;
  }

  public SubScenarioRefinement(Scenario srcDiagram_p, ComponentArchitecture arch_p, String scenarioName_p) {
    super(srcDiagram_p, arch_p, isIntraLogicalLayer(srcDiagram_p, arch_p), false);
    _scenarioName = scenarioName_p;
  }

  public SubScenarioRefinement(Scenario srcDiagram_, Component component_p, String scenarioName_p) {
    super(srcDiagram_, component_p, isIntraLogicalLayer(srcDiagram_, component_p), false);
    _scenarioName = scenarioName_p;
  }

  /**
   * @see org.polarsys.capella.core.refinement.scenarios.core.ScenarioRefinement#execute()
   */
  @Override
  public void execute(IProgressMonitor progressMonitor_p) throws ProcessorException {
    if (_targetScenario == null) {
      /**
       * Add a new sub-scenario
       */
      StructureSynchronizationProcessor proc = new StructureSynchronizationProcessor(getContext(), getTarget(), true);
      proc.execute(progressMonitor_p);

      _targetScenario = (Scenario) proc.getResult();
      applyNamingRule(_targetScenario, getTarget(), _scenarioName);
    }
    else {
      /**
       * Attach an existing sub-scenario
       */
      RefinementLinkExt.createRefinementTraceabilityLink(_targetScenario, getContext());
    }

    forceTargetScenario(_targetScenario);

    super.execute(progressMonitor_p);
  }
  
  
  private static boolean isIntraLogicalLayer(CapellaElement srcDiagram_p, CapellaElement tgtDiagram_p) {
    if (CapellaLayerCheckingExt.isInLogicalLayer(srcDiagram_p) && CapellaLayerCheckingExt.isInLogicalLayer(tgtDiagram_p))
      return true;
    
    return false;
  }
  
}
