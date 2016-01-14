/*******************************************************************************
 * Copyright (c) 2006, 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.test.refinement.ju.testcases.physical;

import java.util.Arrays;
import java.util.List;

import org.polarsys.capella.test.refinement.ju.testcases.RefinementTest;

/**
 */
public abstract class AbstractPhysicalRefinementTest extends RefinementTest {

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
  public AbstractPhysicalRefinementTest(String testName, String testCaseDesc, List<String> refinementTargetsChoiceId, List<String> refinementTargetProposalsId, String scenarioToRefineId, String scenarioReferenceId, List<String> ambiguityChoicesId, List<List<String>> ambiguitiesProposalsId, List<String> additionalElementsId) {
    super(refinementTargetsChoiceId, refinementTargetProposalsId, scenarioToRefineId, scenarioReferenceId, ambiguityChoicesId, ambiguitiesProposalsId, additionalElementsId);
  }

  @Override
  public List<String> getRequiredTestModels() {
    return Arrays.asList("PhysicalRefinement_TCs");
  }
}
