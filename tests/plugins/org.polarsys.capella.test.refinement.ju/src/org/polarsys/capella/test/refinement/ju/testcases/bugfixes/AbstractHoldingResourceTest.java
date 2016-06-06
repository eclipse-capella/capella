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
package org.polarsys.capella.test.refinement.ju.testcases.bugfixes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.junit.Assert;
import org.polarsys.capella.common.ef.command.AbstractCommand;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;
import org.polarsys.capella.common.helpers.EcoreUtil2;
import org.polarsys.capella.core.data.capellacore.NamedElement;
import org.polarsys.capella.core.data.capellamodeller.CapellamodellerPackage;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.data.interaction.Scenario;
import org.polarsys.capella.core.model.handler.helpers.HoldingResource;
import org.polarsys.capella.core.refinement.RefinementMultiple;
import org.polarsys.capella.core.refinement.scenarios.core.exceptions.ProcessorException;
import org.polarsys.capella.test.framework.helpers.TestHelper;
import org.polarsys.capella.test.refinement.ju.Messages;
import org.polarsys.capella.test.refinement.ju.helpers.ScenarioTestHelper;
import org.polarsys.capella.test.refinement.ju.testcases.RefinementTest;

/**
 */
public abstract class AbstractHoldingResourceTest extends RefinementTest {

  /**
   * @param testName
   * @param testCaseDesc
   * @param refinementTargetsChoiceId
   * @param refinementTargetProposalsId
   * @param scenarioToRefineId
   * @param scenarioReferenceId
   * @param ambiguityChoicesId
   * @param ambiguitiesProposalsId
   * @param additionalElementsId
   */
  public AbstractHoldingResourceTest(String testName, String testCaseDesc, List<String> refinementTargetsChoiceId,
      List<String> refinementTargetProposalsId, String scenarioToRefineId, String scenarioReferenceId,
      List<String> ambiguityChoicesId, List<List<String>> ambiguitiesProposalsId, List<String> additionalElementsId) {
    super(refinementTargetsChoiceId, refinementTargetProposalsId, scenarioToRefineId,
        scenarioReferenceId, ambiguityChoicesId, ambiguitiesProposalsId, additionalElementsId);
  }

  @Override
  protected void additionalCheck(Scenario refinedScenario, Scenario referenceScenario) {
    SystemEngineering se = (SystemEngineering) EcoreUtil2.getFirstContainer(refinedScenario,
        CapellamodellerPackage.Literals.SYSTEM_ENGINEERING);
    Iterator<EObject> iter = se.eAllContents();
    while (iter.hasNext()) {
      EObject obj = iter.next();
      assertFalse(obj.eResource() instanceof HoldingResource);
    }
  }

  @Override
  public void refinementTest() {
    final Scenario scenario = (Scenario) _semanticObjectMap.get(_scenarioToRefineIds.get(0));

    final List<NamedElement> targets = new ArrayList<NamedElement>();
    for (String target : _refinementTargetsChoiceId) {
      targets.add((NamedElement) _semanticObjectMap.get(target));
    }

    final List<NamedElement> possibleTargets = new ArrayList<NamedElement>();
    for (String possibleTarget : _refinementTargetProposalsId) {
      EObject obj = _semanticObjectMap.get(possibleTarget);
      if (obj != null) {
        possibleTargets.add((NamedElement) obj);
      }
    }

    if (scenario != null) {
      AbstractCommand cmd = new AbstractReadWriteCommand() {
        public void run() {
          try {
            RefinementMultiple refinement = new RefinementMultiple(scenario) {
              boolean skipSelection = false;

              /**
               * This method checks if the target proposals returned by super.evaluateTarget() are coherent with the
               * parameters given to this test.
               * 
               * @see org.polarsys.capella.core.refinement.RefinementMultiple#evaluateTarget(java.util.List)
               */
              @Override
              protected Map<NamedElement, List<NamedElement>> evaluateTarget(List<Scenario> scenarios) {
                skipSelection = true;
                Map<NamedElement, List<NamedElement>> result = super.evaluateTarget(scenarios);
                for (Scenario sc : scenarios) {
                  List<NamedElement> evaluatedTargets = result.get(sc);
                  if (evaluatedTargets != null) {
                    Assert.assertTrue(Messages.wrongRefinementTargets,
                        evaluatedTargets.size() == possibleTargets.size());
                    Assert.assertTrue(Messages.wrongRefinementTargets, evaluatedTargets.containsAll(targets));
                    Assert.assertTrue(Messages.wrongRefinementTargets, possibleTargets.containsAll(targets));
                  }
                }
                skipSelection = false;
                return super.evaluateTarget(scenarios);
              }

              /**
               * This method had to be overridden because it launches a UI that allows to select the refinement target.
               * 
               * @see org.polarsys.capella.core.refinement.RefinementMultiple#selectTarget(java.util.Map)
               */
              @Override
              protected Map<NamedElement, List<NamedElement>> selectTarget(
                  Map<NamedElement, List<NamedElement>> allTargets) {
                if (skipSelection) {
                  return allTargets;
                }

                if (!targets.isEmpty()) {
                  Map<NamedElement, List<NamedElement>> result = new HashMap<NamedElement, List<NamedElement>>();
                  List<NamedElement> selectedTargets = new ArrayList<NamedElement>();
                  List<NamedElement> lst = allTargets.get(scenario);
                  if (lst.containsAll(targets)) {
                    selectedTargets.addAll(targets);
                  }
                  result.put(scenario, selectedTargets);

                  return result;
                }
                return super.selectTarget(allTargets);
              }
            };
            refinement.execute();
          } catch (ProcessorException exception) {
            Assert.fail(Messages.refinementDoesNotSucceed);
          }
        }
      };
      TestHelper.getExecutionManager(scenario).execute(cmd);

      for (NamedElement target : targets) {
        Scenario refinedScenario = getRefinedScenario(scenario, target);
        if (refinedScenario != null) {
          ScenarioTestHelper.checkRefinementLink(refinedScenario);

          // eventually, does some additional checks (must be overridden)
          additionalCheck(refinedScenario, null);
        } else {
          Assert.fail(Messages.noRefinedScenarioCreated);
        }
      }
    } else {
      Assert.fail(Messages.modelCannotbeLoaded);
    }
  }

  @Override
  public List<String> getRequiredTestModels() {
    return Arrays.asList("BugFixes");
  }
}
