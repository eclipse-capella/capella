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
package org.polarsys.capella.test.diagram.tools.ju.testsuites.partial;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.polarsys.capella.test.diagram.tools.ju.orb.ORBCreateConstraint;
import org.polarsys.capella.test.diagram.tools.ju.orb.ORBCreateConstraintElement;
import org.polarsys.capella.test.diagram.tools.ju.orb.ORBCreateInteraction;
import org.polarsys.capella.test.diagram.tools.ju.orb.ORBCreateOperationalActivity;
import org.polarsys.capella.test.diagram.tools.ju.orb.ORBCreateOperationalRole;
import org.polarsys.capella.test.diagram.tools.ju.orb.ORBDragAndDropTest;
import org.polarsys.capella.test.diagram.tools.ju.orb.ORBInsertAllAllocatedActivities;
import org.polarsys.capella.test.diagram.tools.ju.orb.ORBInsertRemoveAllocatedActivities;
import org.polarsys.capella.test.diagram.tools.ju.orb.ORBInsertRemoveConstraints;
import org.polarsys.capella.test.diagram.tools.ju.orb.ORBInsertRemoveInteractions;
import org.polarsys.capella.test.diagram.tools.ju.orb.ORBInsertRemoveOperationalRoles;
import org.polarsys.capella.test.diagram.tools.ju.orb.ORBManageAllocatedActivities;
import org.polarsys.capella.test.framework.api.BasicTestArtefact;
import org.polarsys.capella.test.framework.api.BasicTestSuite;

import junit.framework.Test;

public class ORBDiagramToolsTestSuite extends BasicTestSuite {

  public static Test suite() {
    return new ORBDiagramToolsTestSuite();
  }
  
  public List<String> getRequiredTestModels() {
    return Arrays.asList("DiagramToolsModel");
  }

  @Override
  protected List<BasicTestArtefact> getTests() {

    List<BasicTestArtefact> tests = new ArrayList<BasicTestArtefact>();

    tests.add(new ORBCreateOperationalRole());
    tests.add(new ORBCreateOperationalActivity());
    tests.add(new ORBCreateInteraction());
    tests.add(new ORBCreateConstraint());
    tests.add(new ORBCreateConstraintElement());
    tests.add(new ORBInsertRemoveOperationalRoles());
    tests.add(new ORBInsertRemoveInteractions());
    tests.add(new ORBInsertRemoveAllocatedActivities());
    tests.add(new ORBInsertAllAllocatedActivities());
    tests.add(new ORBManageAllocatedActivities());
    tests.add(new ORBInsertRemoveConstraints());
    tests.add(new ORBDragAndDropTest());

    return tests;
  }
}
