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
package org.polarsys.capella.test.refinement.ju.testcases;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.junit.Assert;
import org.polarsys.capella.common.data.modellingcore.AbstractTrace;
import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;
import org.polarsys.capella.common.data.modellingcore.TraceableElement;
import org.polarsys.capella.common.ef.command.AbstractCommand;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;
import org.polarsys.capella.common.helpers.EcoreUtil2;
import org.polarsys.capella.core.data.capellacore.NamedElement;
import org.polarsys.capella.core.data.information.AbstractInstance;
import org.polarsys.capella.core.data.interaction.MergeLink;
import org.polarsys.capella.core.data.interaction.RefinementLink;
import org.polarsys.capella.core.data.interaction.Scenario;
import org.polarsys.capella.core.refinement.RefinementMultiple;
import org.polarsys.capella.core.refinement.scenarios.core.exceptions.ProcessorException;
import org.polarsys.capella.test.framework.api.NonDirtyTestCase;
import org.polarsys.capella.test.framework.helpers.EObjectHelper;
import org.polarsys.capella.test.framework.helpers.TestHelper;
import org.polarsys.capella.test.refinement.ju.Messages;
import org.polarsys.capella.test.refinement.ju.headless.HeadlessResultProvider;
import org.polarsys.capella.test.refinement.ju.headless.IResolverResult;
import org.polarsys.capella.test.refinement.ju.helpers.ScenarioTestHelper;

/**
 * Basic Test class to test the Refinement.
 */
public abstract class RefinementTest extends NonDirtyTestCase {
  /** The IDs of the Objects that will be used by additional tests */
  protected List<String> _additionalElementsId;
  /** The IDs of the Objects that shall be selected when an UI request occurs */
  protected List<List<String>> _ambiguitiesProposalsId;
  protected List<String> _ambiguityChoicesId;

  /** The IDs of the Objects that shall be selected when an UI request occurs */
  protected List<String> _refinementTargetProposalsId;
  protected List<String> _refinementTargetsChoiceId;
  protected List<String> _scenarioReferenceIds;

  /** The Ids of the Scenarios that will be refined */
  protected List<String> _scenarioToRefineIds;
  /** Map with needed semantic Object, useful */
  protected Map<String, EObject> _semanticObjectMap;

  /**
   * Constructor
   * @param refinementTargetsChoiceId
   * @param refinementTargetProposalsId
   * @param scenarioToRefineId
   * @param scenarioReferenceId
   * @param ambiguityChoicesId
   * @param ambiguitiesProposalsId
   * @param additionalElementsId
   */
  public RefinementTest(List<String> refinementTargetsChoiceId,
      List<String> refinementTargetProposalsId, String scenarioToRefineId, String scenarioReferenceId, List<String> ambiguityChoicesId,
      List<List<String>> ambiguitiesProposalsId, List<String> additionalElementsId) {
    _scenarioToRefineIds = Arrays.asList(scenarioToRefineId.split(",")); //$NON-NLS-1$
    _scenarioReferenceIds = Arrays.asList(scenarioReferenceId.split(",")); //$NON-NLS-1$
    _ambiguityChoicesId = ambiguityChoicesId;
    _ambiguitiesProposalsId = ambiguitiesProposalsId;
    _refinementTargetsChoiceId = refinementTargetsChoiceId;
    _refinementTargetProposalsId = refinementTargetProposalsId;
    _additionalElementsId = additionalElementsId;
  }

  @Override
  public void test() throws Exception {
    refinementTest();
  }

  @Override
  protected void setUp() throws Exception {
    super.setUp();
    setObjects();
  }

  /**
   * @param refinedScenario
   * @param referenceScenario
   */
  protected void additionalCheck(Scenario refinedScenario, Scenario referenceScenario) {
    // by default, does nothing
  }

  /**
   * @param scenario
   * @param target
   */
  protected Scenario getRefinedScenario(Scenario scenario, NamedElement target) {
    if (scenario != null) {
      for (AbstractTrace abstractTrace : scenario.getIncomingTraces()) {
        if (abstractTrace instanceof RefinementLink) {
          TraceableElement traceableElement = abstractTrace.getSourceElement();
          if (traceableElement instanceof Scenario) {
            if ((null == target)
                || EcoreUtil2.isContainedBy(traceableElement, (target instanceof AbstractInstance) ? ((AbstractInstance) target).getAbstractType()
                                                                                                    : target)) {
              return (Scenario) traceableElement;
            }
          }
        } else if (abstractTrace instanceof MergeLink) {
          TraceableElement traceableElement = abstractTrace.getSourceElement();
          if (traceableElement instanceof Scenario) {
            return getRefinedScenario((Scenario) traceableElement, target);
          }
        }
      }
    }
    return null;
  }

  /**
   * The test itself.
   */
  public void refinementTest() {
    final Scenario scenario = (Scenario) _semanticObjectMap.get(_scenarioToRefineIds.get(0));
    final Scenario reference = (Scenario) _semanticObjectMap.get(_scenarioReferenceIds.get(0));

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

    if ((scenario != null) && (reference != null)) {
      AbstractCommand cmd = new AbstractReadWriteCommand() {
        private RefinementMultiple refinement;
        /**
         * @see org.polarsys.capella.common.ef.command.AbstractCommand#getAffectedObjects()
         */
        @Override
        public Collection<?> getAffectedObjects() {
          Object result = refinement.getResult();
          if (result instanceof Collection) {
            return (Collection<?>) result;
          }
          return Collections.singleton(result);
        }
        @Override
        public void run() {
          try {
            refinement = new RefinementMultiple(scenario) {
              boolean skipSelection = false;

              /**
               * This method checks if the target proposals returned by super.evaluateTarget() are coherent with the parameters given to this test.
               * @see org.polarsys.capella.core.refinement.RefinementMultiple#evaluateTarget(java.util.List)
               */
              @Override
              protected Map<NamedElement, List<NamedElement>> evaluateTarget(List<Scenario> scenarios) {
                skipSelection = true;
                Map<NamedElement, List<NamedElement>> result = super.evaluateTarget(scenarios);
                for (Scenario sc : scenarios) {
                  List<NamedElement> evaluatedTargets = result.get(sc);
                  if (evaluatedTargets != null) {
                    Assert.assertTrue(Messages.wrongRefinementTargets, evaluatedTargets.size() == possibleTargets.size());
                    Assert.assertTrue(Messages.wrongRefinementTargets, evaluatedTargets.containsAll(targets));
                    Assert.assertTrue(Messages.wrongRefinementTargets, possibleTargets.containsAll(targets));
                  }
                }
                skipSelection = false;
                return super.evaluateTarget(scenarios);
              }

              /**
               * This method had to be overridden because it launches a UI that allows to select the refinement target.
               * @see org.polarsys.capella.core.refinement.RefinementMultiple#selectTarget(java.util.Map)
               */
              @Override
              protected Map<NamedElement, List<NamedElement>> selectTarget(Map<NamedElement, List<NamedElement>> allTargets) {
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
          ScenarioTestHelper.compareStructure(refinedScenario, reference);
          ScenarioTestHelper.compareOrdering(refinedScenario, reference);
          ScenarioTestHelper.checkRefinementLink(refinedScenario);

          // eventually, does some additional checks (must be overridden)
          additionalCheck(refinedScenario, reference);
        } else {
          Assert.fail(Messages.noRefinedScenarioCreated);
        }
      }
    } else {
      Assert.fail(Messages.modelCannotbeLoaded);
    }
  }

  /**
   * Set the map with interesting object for this test suite. Not really nice in this case because we close and re-open session but really useful in
   * "one session" approach
   */
  protected void setObjects() {
    _semanticObjectMap = new HashMap<String, EObject>();

    Resource semanticResource = TestHelper.getSemanticResource(getSession(getRequiredTestModels().get(0)));

    ArrayList<String> list = new ArrayList<String>();

    list.addAll(_scenarioToRefineIds);
    list.addAll(_scenarioReferenceIds);
    list.addAll(_refinementTargetsChoiceId);
    list.addAll(_refinementTargetProposalsId);
    list.addAll(_ambiguityChoicesId);
    for (List<String> ids : _ambiguitiesProposalsId) {
      list.addAll(ids);
    }
    list.addAll(_additionalElementsId);

    Map<String, EObject> map =
        EObjectHelper.getMatchingEObject(semanticResource.getContents().get(0), ModellingcorePackage.Literals.MODEL_ELEMENT,
            ModellingcorePackage.Literals.MODEL_ELEMENT__ID, list);

    _semanticObjectMap.putAll(map);

    // Simulate user action.
    IResolverResult result = new IResolverResult() {
      private int _counter1 = 0;
      private int _counter2 = 0;
      @Override
      public List<AbstractInstance> getAttemptedAbstractInstances() {
        List<AbstractInstance> attemptedList = new ArrayList<AbstractInstance>();
        List<String> attemptedIds = _ambiguitiesProposalsId.get(_counter2++);
        for (String id : attemptedIds) {
          EObject obj = _semanticObjectMap.get(id);
          if (obj instanceof AbstractInstance) {
            attemptedList.add((AbstractInstance) obj);
          }
        }
        return attemptedList;
      }

      @Override
      public List<AbstractInstance> getSelectedAbstractInstances() {
        List<AbstractInstance> selection = new ArrayList<AbstractInstance>();
        String id = _ambiguityChoicesId.get(_counter1++);
        EObject obj = _semanticObjectMap.get(id);
        if (obj instanceof AbstractInstance) {
          selection.add((AbstractInstance) obj);
        }
        return selection;
      }
    };
    HeadlessResultProvider.INSTANCE.setCurrentResult(result);
  }
}
