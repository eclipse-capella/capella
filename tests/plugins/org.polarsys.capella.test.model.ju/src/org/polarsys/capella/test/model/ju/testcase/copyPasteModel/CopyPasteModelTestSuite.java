/*******************************************************************************
 * Copyright (c) 2019, 2023 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.model.ju.testcase.copyPasteModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.polarsys.capella.test.framework.api.BasicTestArtefact;
import org.polarsys.capella.test.framework.api.BasicTestSuite;

import junit.framework.Test;

public class CopyPasteModelTestSuite extends BasicTestSuite {

  @Override
  public List<String> getRequiredTestModels() {
    return Arrays.asList("miscmodel");
  }
  
  public static Test suite() {
    return new CopyPasteModelTestSuite();
  }

  @Override
  protected List<BasicTestArtefact> getTests() {
    List<BasicTestArtefact> tests = new ArrayList<>();
    tests.add(new CopyPasteModelWithAppliedPVGandPV());
    tests.add(new CopyPasteModelWithFunctionalAllocation());
    tests.add(new CopyPasteModelElementReferencingLibrary());
    tests.add(new CopyPasteComponent());
    tests.add(new CutPasteUndoModelElement());
    return tests;
  }

}
