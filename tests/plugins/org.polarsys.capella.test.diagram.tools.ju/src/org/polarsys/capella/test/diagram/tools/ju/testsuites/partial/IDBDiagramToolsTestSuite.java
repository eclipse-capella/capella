/*******************************************************************************
 * Copyright (c) 2006, 2019 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.diagram.tools.ju.testsuites.partial;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.polarsys.capella.test.diagram.tools.ju.idb.CreateActor;
import org.polarsys.capella.test.diagram.tools.ju.idb.CreateCommunicationLinkAcquire;
import org.polarsys.capella.test.diagram.tools.ju.idb.CreateCommunicationLinkTransmit;
import org.polarsys.capella.test.diagram.tools.ju.idb.CreateComponent;
import org.polarsys.capella.test.diagram.tools.ju.idb.CreateComponentPort;
import org.polarsys.capella.test.diagram.tools.ju.idb.CreateComponent_Nature;
import org.polarsys.capella.test.diagram.tools.ju.idb.CreateEvent;
import org.polarsys.capella.test.diagram.tools.ju.idb.CreateExchangeItem;
import org.polarsys.capella.test.diagram.tools.ju.idb.CreateGeneralization;
import org.polarsys.capella.test.diagram.tools.ju.idb.CreateImplements;
import org.polarsys.capella.test.diagram.tools.ju.idb.CreateInterface;
import org.polarsys.capella.test.diagram.tools.ju.idb.CreateProvides;
import org.polarsys.capella.test.diagram.tools.ju.idb.CreateRequires;
import org.polarsys.capella.test.diagram.tools.ju.idb.CreateUses;
import org.polarsys.capella.test.diagram.tools.ju.idb.DragAndDropComponents;
import org.polarsys.capella.test.diagram.tools.ju.idb.DragAndDropTest;
import org.polarsys.capella.test.diagram.tools.ju.idb.InsertRelationship;
import org.polarsys.capella.test.diagram.tools.ju.idb.ReconnectEdges;
import org.polarsys.capella.test.diagram.tools.ju.idb.ShowHideActorsAndComponents;
import org.polarsys.capella.test.diagram.tools.ju.idb.ShowHideElements;
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

  @Override
  public List<String> getRequiredTestModels() {
    return Arrays.asList("EmptyProject");
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
    tests.add(new ShowHideActorsAndComponents());
    tests.add(new DragAndDropTest());
    tests.add(new ShowHideElements());
    tests.add(new ReconnectEdges());
    tests.add(new CreateEvent());
    tests.add(new DragAndDropComponents());

    return tests;
  }
}
