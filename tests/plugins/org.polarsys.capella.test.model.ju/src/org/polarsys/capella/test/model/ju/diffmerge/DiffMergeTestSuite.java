/*******************************************************************************
 * Copyright (c) 2019, 2020, THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.model.ju.diffmerge;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.polarsys.capella.test.framework.api.BasicTestArtefact;
import org.polarsys.capella.test.framework.api.BasicTestSuite;

import junit.framework.Test;

public class DiffMergeTestSuite extends BasicTestSuite {

  @Override
  public List<String> getRequiredTestModels() {
    return Arrays.asList("DiffMergeSourcePrj", "DiffMergeSourceV1Prj", "DiffMergeSourceT4CPrj", "test_B", "test_C",
        "DiffMergeTargetPrj", "CopyPasteTestCase_Lib");
  }

  public static Test suite() {
    return new DiffMergeTestSuite();
  }

  @Override
  protected List<BasicTestArtefact> getTests() {
    List<BasicTestArtefact> tests = new ArrayList<>();
    tests.add(new DiffMergeBetweenVersionsOfSameModelTestCase());
    tests.add(new DiffMergeBetweenVersionsOfSameCapellaT4CModelTestCase());
    tests.add(new DiffMergeBetweenSubsystemModels());
    tests.add(new TransferBetweenModelsCreatedIndepentlyTestCase());
    tests.add(new TransferOfElementsBetweenCapellaProjectAndLibTestCase());
    return tests;
  }

}
