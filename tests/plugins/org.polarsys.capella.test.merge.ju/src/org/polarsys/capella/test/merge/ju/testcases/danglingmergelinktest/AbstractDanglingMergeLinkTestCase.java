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
package org.polarsys.capella.test.merge.ju.testcases.danglingmergelinktest;

import java.util.Arrays;
import java.util.List;

import org.polarsys.capella.test.merge.ju.testcases.AbstractMergerTest;

public abstract class AbstractDanglingMergeLinkTestCase extends AbstractMergerTest {

  /**
   *
   */
  public AbstractDanglingMergeLinkTestCase(String scenarioToMergeId, String controlScenarioId, String readableTestName, String testCaseDesc) {
    super(scenarioToMergeId, controlScenarioId, readableTestName, testCaseDesc);
  }

  @Override
  public List<String> getRequiredTestModels() {
    return Arrays.asList("DanglingMergeLinkTest");
  }
}
