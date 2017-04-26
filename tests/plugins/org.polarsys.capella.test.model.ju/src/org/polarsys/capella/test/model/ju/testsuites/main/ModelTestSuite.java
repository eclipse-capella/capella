/*******************************************************************************
 * Copyright (c) 2006, 2017 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.test.model.ju.testsuites.main;

import java.util.ArrayList;
import java.util.List;

import org.polarsys.capella.test.framework.api.BasicTestArtefact;
import org.polarsys.capella.test.framework.api.BasicTestSuite;
import org.polarsys.capella.test.model.ju.obfuscate.ObfuscateModelTestCase;
import org.polarsys.capella.test.model.ju.rename.RenameModelTestSuite;
import org.polarsys.capella.test.model.ju.sortContent.SortContentTestSuite;
import org.polarsys.capella.test.model.ju.sortSelection.SortSelectionTestSuite;
import org.polarsys.capella.test.model.ju.testcase.LCDecomposition.LCDecompositionWithCommunicationLink;
import org.polarsys.capella.test.model.ju.testcase.LCDecomposition.LCDecompositionWithInternalInterface;
import org.polarsys.capella.test.model.ju.testcase.copyPasteModel.CopyPasteModelWithAppliedPVGandPV;
import org.polarsys.capella.test.model.ju.testcase.copyPasteModel.CopyPasteModelWithFunctionalAllocation;
import org.polarsys.capella.test.model.ju.testcase.dialoglabel.NewDiagramDialogLabel;
import org.polarsys.capella.test.model.ju.testcases.delete.DeleteElementTestSuite;
import org.polarsys.capella.test.model.ju.testcases.interfacescenario.message.ISMessage;

import junit.framework.Test;

/**
 * @author Hakim Sellou
 */
public class ModelTestSuite extends BasicTestSuite {

  /**
   * Returns the suite. This is required to unary launch this test.
   */
  public static Test suite() {
    return new ModelTestSuite();
  }

  @Override
  protected List<BasicTestArtefact> getTests() {
    List<BasicTestArtefact> tests = new ArrayList<BasicTestArtefact>();
    tests.add(new CopyPasteModelWithAppliedPVGandPV());
    tests.add(new CopyPasteModelWithFunctionalAllocation());
    tests.add(new LCDecompositionWithCommunicationLink());
    tests.add(new LCDecompositionWithInternalInterface());
    tests.add(new ISMessage());
    tests.add(new SortSelectionTestSuite());
    tests.add(new SortContentTestSuite());
    tests.add(new NewDiagramDialogLabel());
    tests.add(new RenameModelTestSuite());
    tests.add(new ObfuscateModelTestCase());
    tests.add(new DeleteElementTestSuite());
    return tests;
  }

}
