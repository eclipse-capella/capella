/*******************************************************************************
 * Copyright (c) 2019 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.model.ju.testsuites.partial;

import java.util.ArrayList;
import java.util.List;

import org.polarsys.capella.test.framework.api.BasicTestArtefact;
import org.polarsys.capella.test.framework.api.BasicTestSuite;
import org.polarsys.capella.test.model.ju.patterns.ExistingPatternTest;

public class PatternsTestSuite extends BasicTestSuite {

  private final String projectName = "miscmodel";
  private final String testPatternFileName = "miscmodel";

  private final String standardPattern = "dc75ed3d-4061-4ff8-ba19-5b7875885455";
  private final String templatePattern = "5a627a4d-5ca4-470c-8781-7ae50707344a";

  @Override
  protected List<BasicTestArtefact> getTests() {

    List<BasicTestArtefact> tests = new ArrayList<BasicTestArtefact>();
    List<String> patternIDs = new ArrayList<>();
    patternIDs.add(standardPattern);
    patternIDs.add(templatePattern);

    tests.add(new ExistingPatternTest(projectName, testPatternFileName, patternIDs));
    return tests;
  }

}
