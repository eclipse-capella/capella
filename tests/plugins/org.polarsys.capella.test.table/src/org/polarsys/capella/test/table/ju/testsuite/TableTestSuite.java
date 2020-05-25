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
package org.polarsys.capella.test.table.ju.testsuite;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.polarsys.capella.test.framework.api.BasicTestArtefact;
import org.polarsys.capella.test.framework.api.BasicTestSuite;
import org.polarsys.capella.test.table.ju.function.FunctionTableTestSuite;
import org.polarsys.capella.test.table.ju.interfaces.InterfaceTableTestSuite;
import org.polarsys.capella.test.table.ju.requirements.RequirementsTableTestSuite;

import junit.framework.Test;

public class TableTestSuite extends BasicTestSuite {

  public static Test suite() {
    return new TableTestSuite();
  }

  @Override
  public List<String> getRequiredTestModels() {
    return Arrays.asList("SF-OA", "CrossTable-OA", "InterfaceTable");
  }

  @Override
  protected List<BasicTestArtefact> getTests() {
    ArrayList<BasicTestArtefact> tests = new ArrayList<BasicTestArtefact>();
    tests.add(new FunctionTableTestSuite());
    tests.add(new RequirementsTableTestSuite());
    tests.add(new InterfaceTableTestSuite());
    return tests;
  }
}
