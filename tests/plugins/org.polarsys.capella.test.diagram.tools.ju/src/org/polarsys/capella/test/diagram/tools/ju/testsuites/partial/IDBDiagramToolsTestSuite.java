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
package org.polarsys.capella.test.diagram.tools.ju.testsuites.partial;

import java.util.ArrayList;
import java.util.List;

import org.polarsys.capella.test.diagram.tools.ju.idb.CreateActor;
import org.polarsys.capella.test.diagram.tools.ju.idb.CreateCommunicationLinkAcquire;
import org.polarsys.capella.test.diagram.tools.ju.idb.CreateCommunicationLinkTransmit;
import org.polarsys.capella.test.diagram.tools.ju.idb.CreateComponent;
import org.polarsys.capella.test.diagram.tools.ju.idb.CreateComponentPort;
import org.polarsys.capella.test.diagram.tools.ju.idb.CreateComponent_Nature;
import org.polarsys.capella.test.diagram.tools.ju.idb.CreateExchangeItem;
import org.polarsys.capella.test.diagram.tools.ju.idb.CreateGeneralization;
import org.polarsys.capella.test.diagram.tools.ju.idb.CreateImplements;
import org.polarsys.capella.test.diagram.tools.ju.idb.CreateInterface;
import org.polarsys.capella.test.diagram.tools.ju.idb.CreateProvides;
import org.polarsys.capella.test.diagram.tools.ju.idb.CreateRequires;
import org.polarsys.capella.test.diagram.tools.ju.idb.CreateUses;
import org.polarsys.capella.test.diagram.tools.ju.idb.InsertRelationship;
import org.polarsys.capella.test.framework.api.BasicTestArtefact;
import org.polarsys.capella.test.framework.api.BasicTestSuite;

import junit.framework.Test;

public class IDBDiagramToolsTestSuite extends BasicTestSuite {

  /**
   * Returns the suite. This is required to unary launch this test.
   */
  public static Test suite() {
    return new IDBDiagramToolsTestSuite();
  }

  /**
   * @see org.polarsys.capella.test.framework.api.BasicTestSuite#getTests()
   */
  @Override
  protected List<BasicTestArtefact> getTests() {
    List<BasicTestArtefact> tests = new ArrayList<BasicTestArtefact>();
    tests.add(new CreateActor());
    tests.add(new CreateCommunicationLinkAcquire());
    tests.add(new CreateCommunicationLinkTransmit());
    tests.add(new CreateComponent());
    tests.add(new CreateComponent_Nature());
    tests.add(new CreateComponentPort());
    tests.add(new CreateExchangeItem());
    tests.add(new CreateGeneralization());
    tests.add(new CreateImplements());
    tests.add(new CreateInterface());
    tests.add(new CreateProvides());
    tests.add(new CreateRequires());
    tests.add(new CreateUses());
    tests.add(new InsertRelationship());
    return tests;
  }

}
