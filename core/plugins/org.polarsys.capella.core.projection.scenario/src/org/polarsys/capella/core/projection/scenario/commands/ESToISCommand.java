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
package org.polarsys.capella.core.projection.scenario.commands;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.core.data.interaction.InteractionUse;
import org.polarsys.capella.core.data.interaction.Scenario;
import org.polarsys.capella.core.data.interaction.TimeLapse;
import org.polarsys.capella.core.data.interaction.validation.interactionUse.MDCHK_InteractionUse_ReferencedScenario;
import org.polarsys.capella.core.projection.common.AbstractTransform;
import org.polarsys.capella.core.projection.common.AbstractTransitionCommand;
import org.polarsys.capella.core.projection.common.TransitionHelper;
import org.polarsys.capella.core.projection.scenario.Messages;
import org.polarsys.capella.core.projection.scenario.ScenarioTransform;
import org.polarsys.capella.core.projection.scenario.es2is.ES2ISTransform;

/**
 *
 */
public class ESToISCommand extends AbstractTransitionCommand {

  public ESToISCommand(Collection<EObject> rootElements_p) {
    super(rootElements_p);
  }

  public ESToISCommand(Collection<EObject> rootElements_p, IProgressMonitor progressMonitor_p) {
    super(rootElements_p, progressMonitor_p);
  }

  @Override
  public String getName() {
    return Messages.transitionES2IS_label;
  }

  @Override
  protected Collection<EObject> retrieveModelElements(EObject modelElement_p) {
    return searchScenarios(modelElement_p);
  }

  /**
   * @see org.polarsys.capella.core.projection.common.AbstractTransitionCommand#getTransformation(org.polarsys.capella.common.data.modellingcore.ModelElement)
   */
  @Override
  protected AbstractTransform getTransformation(EObject element_p) {
    return new ES2ISTransform();
  }

  /**
   * @param selectedElement_p
   * @return
   */
  protected Collection<EObject> searchScenarios(EObject selectedElement_p) {
    Collection<EObject> result = new HashSet<EObject>();
    if (selectedElement_p instanceof Scenario) {
      addAllScenarios((Scenario) selectedElement_p, result);
      return result; // nothing interesting under
    }

    TreeIterator<EObject> it = selectedElement_p.eAllContents();
    while (it.hasNext()) {
      EObject eObject = it.next();

      if (eObject instanceof Scenario) {
        Scenario scenario = (Scenario) eObject;
        if (isScenarioValid(scenario)) {
          // add also the scenario referenced by this scenario
          addAllScenarios(scenario, result);
        }
        it.prune();
      }
      // TODO we can prune many objects to limit lookup
    }
    return result;
  }

  protected void addAllScenarios(Scenario scenario, Collection<EObject> result) {
    // add also the scenario referenced by this scenario
    Set<EObject> referencedScenarios = new HashSet<EObject>();
    searchReferencedScenarios(scenario, referencedScenarios, result);
    result.addAll(referencedScenarios);
    result.add(scenario);
  }

  /**
   * recursively, search for all referenced scenarios
   * 
   * @param scenario:
   *          the current scenario, for which all his references are added in the references set given as parameter
   * @param references:
   *          the references found for each scenarios, we accumulate them in a set
   * @param result:
   *          the scenarios selected until now, for these scenarios the references are already processed so there is no
   *          need to process the once again (in case we select multiple elements on transition, or the entire package)
   * @return
   */
  protected void searchReferencedScenarios(Scenario scenario, Set<EObject> references, Collection<EObject> result) {
    if (result.contains(scenario))
      return;

    MDCHK_InteractionUse_ReferencedScenario checkRule = new MDCHK_InteractionUse_ReferencedScenario();
    for (TimeLapse timelapse : scenario.getOwnedTimeLapses()) {
      if (timelapse instanceof InteractionUse) {
        InteractionUse interaction = (InteractionUse) timelapse;
        Scenario refScenario = interaction.getReferencedScenario();

        // check if is a valid reference and the reference was not already processed
        if (refScenario != null && checkRule.isValidReference(interaction, scenario, refScenario)
            && !references.contains(refScenario)) {

          // check if is a valid scenario
          if (isScenarioValid(refScenario)) {
            references.add(refScenario);
            searchReferencedScenarios(refScenario, references, result);
          }
        }
      }
    }
  }

  protected boolean isScenarioValid(Scenario scenario_p) {
    return TransitionHelper.getService().isES2ISTransitionAvailable(scenario_p);
  }

  @Override
  public void run() {
    super.run();
    setReferencedScenarios(elements);
  }

  /**
   * set the referenced scenarios
   * 
   * @param elements:
   *          the scenarios that were transitioned and that need to have set the referenced scenario
   * @return
   */
  protected void setReferencedScenarios(Collection<EObject> elements) {
    MDCHK_InteractionUse_ReferencedScenario checkRule = new MDCHK_InteractionUse_ReferencedScenario();
    for (EObject element : elements) {
      if (element instanceof Scenario) {
        for (Scenario scenario : ((Scenario) element).getRealizingScenarios()) {
          // set the referenced scenarios
          List<TimeLapse> timelapses = (scenario).getOwnedTimeLapses();
          for (TimeLapse timelapse : timelapses) {
            if (timelapse instanceof InteractionUse) {
              InteractionUse interaction = (InteractionUse) timelapse;
              Scenario refScenario = interaction.getReferencedScenario();

              if (refScenario != null) {
                AbstractTransform transform = getTransformation(refScenario);
                if (transform instanceof ScenarioTransform) {
                  ScenarioTransform scenarioTransform = (ScenarioTransform) transform;
                  Scenario transitionedScenario = scenarioTransform.getTransitionedScenario((Scenario) refScenario,
                      scenarioTransform.getTransfo());
                  if(transitionedScenario !=  null) {
                    interaction.setReferencedScenario(transitionedScenario);
                  }
                  // if the referenced scenario is still invalid, change it to transitionedScenario (Can be null)
                  if(!checkRule.isValidReference(interaction, scenario, refScenario))
                    interaction.setReferencedScenario(transitionedScenario);
                }
              }
            }
          }
        }
      }
    }
  }
}
