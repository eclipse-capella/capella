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
package org.polarsys.capella.test.merge.ju.testcases;



/**
 * Basic Test class to test the merge update.
 *
 */
public abstract class AbstractMergeUpdateTest extends AbstractMergerTest {

  /**
   * @param scenarioToMergeId
   * @param controlScenarioId
   * @param readableTestName
   * @param testCaseDesc
   */
  public AbstractMergeUpdateTest(String scenarioToMergeId, String controlScenarioId, String readableTestName, String testCaseDesc) {
    super(scenarioToMergeId, controlScenarioId, readableTestName, testCaseDesc);
  }

  /**
   * @see org.polarsys.capella.test.merge.ju.testcases.AbstractMergerTest#test()
   */
  @Override
  public void test() throws Exception {
    
    // First pass
    super.test();
    
    // potential changes
    performeChange();
    
    //Second pass
    super.test();
  }

  /**
   * potential changes to apply on the scenario to merge
   */
  protected void performeChange() {
    // default implementation does nothing.
  }

}
