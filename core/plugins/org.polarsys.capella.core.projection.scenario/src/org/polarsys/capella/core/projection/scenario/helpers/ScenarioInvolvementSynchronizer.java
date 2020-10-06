/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.projection.scenario.helpers;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;
import org.eclipse.sirius.viewpoint.description.Viewpoint;
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.interaction.AbstractCapability;
import org.polarsys.capella.core.data.interaction.InstanceRole;
import org.polarsys.capella.core.data.interaction.InteractionUse;
import org.polarsys.capella.core.data.interaction.Scenario;
import org.polarsys.capella.core.data.interaction.TimeLapse;
import org.polarsys.capella.core.model.helpers.AbstractCapabilityExt;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.sirius.ui.actions.NewRepresentationAction;
import org.polarsys.capella.core.tiger.IFinalizer;
import org.polarsys.capella.core.tiger.ITransfo;
import org.polarsys.capella.core.tiger.impl.TransfoEngine;
import org.polarsys.capella.core.transition.system.topdown.preferences.PreferenceHelper;

public class ScenarioInvolvementSynchronizer implements IFinalizer {

  /**
   * @see org.polarsys.capella.core.tiger.IFinalizer#finalize(org.polarsys.capella.core.tiger.ITransfo)
   */
  public void finalize(ITransfo transfo_p) {
    Scenario scenario = (Scenario) transfo_p.get(TransfoEngine.TRANSFO_TARGET);
    if (scenario != null) {
      EObject container = scenario.eContainer();
      if (container instanceof AbstractCapability) {
        for (InstanceRole role : scenario.getOwnedInstanceRoles()) {
          if (role.getRepresentedInstance() instanceof Part) {
            Component component = (Component) role.getRepresentedInstance().getAbstractType();
            AbstractCapabilityExt.addInvolvedComponent((AbstractCapability) container, component);

          } else if (role.getRepresentedInstance() instanceof AbstractFunction) {
            AbstractFunction function = (AbstractFunction) role.getRepresentedInstance();
            AbstractCapabilityExt.addInvolvedFunction((AbstractCapability) container, function);
          }
        }
      }

      // set the referenced scenarios
      List<TimeLapse> timelapses = scenario.getOwnedTimeLapses();
      for (TimeLapse timelapse : timelapses) {
        if (timelapse instanceof InteractionUse) {
          InteractionUse interaction = (InteractionUse) timelapse;
          Scenario refScenario = interaction.getReferencedScenario();
          if (refScenario != null) {
            BlockArchitecture sourceBlock = BlockArchitectureExt.getRootBlockArchitecture(refScenario);
            BlockArchitecture targetBlock = BlockArchitectureExt.getRootBlockArchitecture(scenario);
            if (!sourceBlock.equals(targetBlock)) {
              Optional<Scenario> match = refScenario.getRealizingScenarios().stream().filter(sc -> sc.getName().equals(refScenario.getName())).findFirst();
              if(match.isPresent()) {
                 interaction.setReferencedScenario(match.get());
              }
            }
          }
        }
      }
      
      // check if the option to Initialize the creation of related scenario is checked
      if (PreferenceHelper.getInstance().transitionInitializeTransitionedScenario()) {
        Session currentSession = SessionManager.INSTANCE.getSession(scenario);
        Collection<Viewpoint> selectedViewpoints = currentSession.getSelectedViewpoints(false);

        Collection<RepresentationDescription> descriptions = DialectManager.INSTANCE
            .getAvailableRepresentationDescriptions(selectedViewpoints, scenario);
        
        if (!descriptions.isEmpty()) {
          // Computes the "New Diagram..." menu content according to the current selection.
          for (RepresentationDescription description : descriptions) {
            NewRepresentationAction representationAction = new NewRepresentationAction(description, scenario,
                currentSession, true, false);
            representationAction.run();
          }
        }
      }
    }
  }
}
