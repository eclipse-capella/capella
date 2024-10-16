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
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.test.diagram.tools.ju.testsuites.partial;

import java.util.ArrayList;
import java.util.List;

import org.polarsys.capella.test.diagram.tools.ju.diagram.actions.RefreshAllOnDisabledProject;
import org.polarsys.capella.test.diagram.tools.ju.diagram.actions.RefreshAllOnEnabledProject;
import org.polarsys.capella.test.diagram.tools.ju.diagram.actions.RefreshAllOnNonCustomizedProject;
import org.polarsys.capella.test.diagram.tools.ju.diagram.actions.RefreshAllSubRepresentations;
import org.polarsys.capella.test.diagram.tools.ju.diagram.actions.RemoveHiddenElements;
import org.polarsys.capella.test.diagram.tools.ju.diagram.actions.SpecificSettings;
import org.polarsys.capella.test.framework.api.BasicTestArtefact;
import org.polarsys.capella.test.framework.api.BasicTestSuite;

import junit.framework.Test;

public class DiagramActionsTestSuite extends BasicTestSuite {

  /**
   * Returns the suite. This is required to unary launch this test.
   */
  public static Test suite() {
    return new DiagramActionsTestSuite();
  }

  /**
   * @see org.polarsys.capella.test.framework.api.BasicTestSuite#getTests()
   */
  @Override
  protected List<BasicTestArtefact> getTests() {
    List<BasicTestArtefact> tests = new ArrayList<BasicTestArtefact>();
    tests.add(new RefreshAllSubRepresentations());
    tests.add(new SpecificSettings());
    tests.add(new RefreshAllOnEnabledProject());
    tests.add(new RefreshAllOnDisabledProject());
    tests.add(new RefreshAllOnNonCustomizedProject());
    tests.add(new RemoveHiddenElements());
    return tests;
  }

}
