/*******************************************************************************
 * Copyright (c) 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.table.ju.function.state;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.polarsys.capella.test.framework.api.BasicTestArtefact;
import org.polarsys.capella.test.framework.api.BasicTestSuite;

import junit.framework.Test;

public class StateModeTableTestSuite extends BasicTestSuite {

  public static Test suite() {
    return new StateModeTableTestSuite();
  }

  @Override
  public List<String> getRequiredTestModels() {
    return Arrays.asList("SF-OA");
  }

  @Override
  protected List<BasicTestArtefact> getTests() {
    ArrayList<BasicTestArtefact> tests = new ArrayList<BasicTestArtefact>();
    tests.add(new StateModeTableTestCase());
    return tests;
  }
}
