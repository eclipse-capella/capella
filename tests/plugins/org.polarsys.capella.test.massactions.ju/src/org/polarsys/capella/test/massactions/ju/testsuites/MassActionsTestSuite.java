/*******************************************************************************
 * Copyright (c) 2018 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
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