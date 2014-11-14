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
package org.polarsys.capella.core.refinement;

import org.eclipse.core.runtime.IProgressMonitor;

import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.ComponentArchitecture;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.interaction.InteractionPackage;
import org.polarsys.capella.core.data.interaction.InteractionUse;
import org.polarsys.capella.core.data.interaction.Scenario;
import org.polarsys.capella.core.data.interaction.ScenarioKind;
import org.polarsys.capella.core.data.interaction.TimeLapse;
import org.polarsys.capella.core.data.la.LogicalComponent;
import org.polarsys.capella.core.data.capellacore.NamedElement;
import org.polarsys.capella.core.model.helpers.CapellaElementExt;
import org.polarsys.capella.core.refinement.scenarios.core.ScenarioRefinement;
import org.polarsys.capella.core.refinement.scenarios.core.exceptions.ProcessorException;

/**
 */
public class RefinementTargeted extends ScenarioRefinement {

  /**
   * Constructor
   * @param srcDiagram_p
   * @param arch_p
   */
  public RefinementTargeted(Scenario srcDiagram_p, ComponentArchitecture arch_p) {
    super(srcDiagram_p, arch_p, (arch_p.eContainer() instanceof LogicalComponent));
  }

  /**
   * Constructor
   * @param srcDiagram_p
   * @param part_p
   */
  public RefinementTargeted(Scenario srcDiagram_p, Part part_p) {
    super(srcDiagram_p, part_p, true);
  }

  /**
   * Constructor
   * @param srcDiagram_p
   * @param component_p
   */
  public RefinementTargeted(Scenario srcDiagram_p, Component component_p) {
    super(srcDiagram_p, component_p, true);
  }

  /**
   * @see org.polarsys.capella.core.refinement.scenarios.core.ScenarioRefinement#execute()
   * @param progressMonitor_p
   */
  @Override
  public void execute(IProgressMonitor progressMonitor_p) throws ProcessorException {
    super.execute(progressMonitor_p);

    NamedElement tgt = getTarget();
    NamedElement elt = (NamedElement) getResult();
 
    applyNamingRule(elt, tgt);

    processReferencedScenario(progressMonitor_p);
  }

  /**
   * @param progressMonitor_p
   */
  protected void processReferencedScenario(IProgressMonitor progressMonitor_p) {
    for (TimeLapse timeLapse : _srcDiagram.getOwnedTimeLapses()) {
      if ((timeLapse instanceof InteractionUse) &&
          (!_isIntraLayerRefinement || isInteracting((InteractionUse) timeLapse, _tgtElement)))
      {
        Scenario sc = ((InteractionUse) timeLapse).getReferencedScenario();
        if (sc != null) {
          RefinementTargeted ref = null;
          if (_tgtElement instanceof ComponentArchitecture) {
            ref = new RefinementTargeted(sc, (ComponentArchitecture) _tgtElement);
          } else if (_tgtElement instanceof Component) {
            ref = new RefinementTargeted(sc, (Component) _tgtElement);
          } else if (_tgtElement instanceof Part) {
            ref = new RefinementTargeted(sc, (Part) _tgtElement);
          }
          if ((ref != null) && !sc.equals(_srcDiagram) && sc.getKind().equals(ScenarioKind.INTERFACE)) {
            ref.execute(progressMonitor_p);
            Object refinedScenario = ref.getResult();
            if (refinedScenario instanceof Scenario) {
              InteractionUse interactionUse = (InteractionUse) CapellaElementExt.getRefinementSrcElement(timeLapse, InteractionPackage.Literals.INTERACTION_USE, _tgtDiagram);
              if (interactionUse != null) {
                interactionUse.setReferencedScenario((Scenario) refinedScenario);
              }
            }
          }
        }
      }
    }
  }
}
