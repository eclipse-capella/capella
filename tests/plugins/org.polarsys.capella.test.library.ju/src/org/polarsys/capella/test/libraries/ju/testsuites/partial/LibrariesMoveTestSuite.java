/*******************************************************************************
 * Copyright (c) 2018 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *   
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.test.libraries.ju.testsuites.partial;

import java.util.ArrayList;
import java.util.List;

import org.polarsys.capella.test.framework.api.BasicTestArtefact;
import org.polarsys.capella.test.framework.api.BasicTestSuite;
import org.polarsys.capella.test.libraries.ju.testcases.move.MoveActorTest;
import org.polarsys.capella.test.libraries.ju.testcases.move.MoveConfigurationItemTest;
import org.polarsys.capella.test.libraries.ju.testcases.move.MoveEntityTest;
import org.polarsys.capella.test.libraries.ju.testcases.move.MoveLogicalActorTest;
import org.polarsys.capella.test.libraries.ju.testcases.move.MoveLogicalComponentTest;
import org.polarsys.capella.test.libraries.ju.testcases.move.MoveOperationalActorTest;
import org.polarsys.capella.test.libraries.ju.testcases.move.MovePhysicalActorTest;
import org.polarsys.capella.test.libraries.ju.testcases.move.MovePhysicalComponentTest;

import junit.framework.Test;

public class LibrariesMoveTestSuite extends BasicTestSuite {

  /**
   * Returns the suite. This is required to unary launch this test.
   */
  public static Test suite() {
    return new LibrariesMoveTestSuite();
  }

  /**
   * @see org.polarsys.capella.test.framework.api.BasicTestSuite#getTests()
   */
  @Override
  protected List<BasicTestArtefact> getTests() {
    List<BasicTestArtefact> tests = new ArrayList<BasicTestArtefact>();
    tests.add(new MoveOperationalActorTest());
    tests.add(new MoveEntityTest());
    tests.add(new MoveActorTest());
    tests.add(new MoveLogicalActorTest());
    tests.add(new MoveLogicalComponentTest());
    tests.add(new MovePhysicalActorTest());
    tests.add(new MovePhysicalComponentTest());
    tests.add(new MoveConfigurationItemTest());

    return tests;
  }

}
