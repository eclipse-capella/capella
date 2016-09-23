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
   * @param srcDiagram
   * @param arch
   */
  public RefinementTargeted(Scenario srcDiagram, ComponentArchitecture arch) {
    super(srcDiagram, arch, (arch.eContainer() instanceof LogicalComponent));
  }

  /**
   * Constructor
   * @param srcDiagram
   * @param part
   */
  public RefinementTargeted(Scenario srcDiagram, Part part) {
    super(srcDiagram, part, true);
  }

  /**
   * Constructor
   * @param srcDiagram
   * @param component
   */
  public RefinementTargeted(Scenario srcDiagram, Component component) {
    super(srcDiagram, component, true);
  }

  /**
   * @see org.polarsys.capella.core.refinement.scenarios.core.ScenarioRefinement#execute()
   * @param progressMonitor
   */
  @Override
  public void execute(IProgressMonitor progressMonitor) throws ProcessorException {
    super.execute(progressMonitor);

    NamedElement tgt = getTarget();
    NamedElement elt = (NamedElement) getResult();
 
    applyNamingRule(elt, tgt);

    processReferencedScenario(progressMonitor);
  }

  /**
   * @param progressMonitor
   */
  protected void processReferencedScenario(IProgressMonitor progressMonitor) {
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
            ref.execute(progressMonitor);
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
