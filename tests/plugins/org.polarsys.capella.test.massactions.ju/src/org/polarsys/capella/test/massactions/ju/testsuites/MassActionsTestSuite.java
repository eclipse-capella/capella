/*******************************************************************************
 * Copyright (c) 2018, 2020 THALES GLOBAL SERVICES.
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 * 
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 * Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.test.massactions.ju.testsuites;

import java.util.ArrayList;
import java.util.List;

import org.polarsys.capella.test.framework.api.BasicTestArtefact;
import org.polarsys.capella.test.framework.api.BasicTestSuite;
import org.polarsys.capella.test.massactions.ju.testsuites.helpers.CommonElementsTestSuite;
import org.polarsys.capella.test.massactions.ju.testsuites.view.MAViewsTestSuite;

import junit.framework.Test;

public class MassActionsTestSuite extends BasicTestSuite {

  public static Test suite() {
    return new MassActionsTestSuite();
  }

  @Override
  protected List<BasicTestArtefact> getTests() {
    List<BasicTestArtefact> testSuite = new ArrayList<>();

    testSuite.add(new CommonElementsTestSuite());
    testSuite.add(new MAViewsTestSuite());

    return testSuite;
  }
}