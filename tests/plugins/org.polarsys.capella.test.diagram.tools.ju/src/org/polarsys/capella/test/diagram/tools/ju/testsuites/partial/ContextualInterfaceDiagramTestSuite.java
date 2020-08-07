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
package org.polarsys.capella.test.diagram.tools.ju.testsuites.partial;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.polarsys.capella.test.diagram.tools.ju.cei.ShowHideCEIActorQuery;
import org.polarsys.capella.test.diagram.tools.ju.cii.ShowHideCIIActorQuery;
import org.polarsys.capella.test.framework.api.BasicTestArtefact;
import org.polarsys.capella.test.framework.api.BasicTestSuite;

import junit.framework.Test;

public class ContextualInterfaceDiagramTestSuite extends BasicTestSuite {

  /**
   * Returns the suite. This is required to unary launch this test.
   */
  public static Test suite() {
    return new ContextualInterfaceDiagramTestSuite();
  }
  
  @Override
  public List<String> getRequiredTestModels() {
    return Arrays.asList("ContextualInterface");
  }
  
  @Override
  protected List<BasicTestArtefact> getTests() {
    List<BasicTestArtefact> tests = new ArrayList<>();

    tests.add(new ShowHideCEIActorQuery());
    tests.add(new ShowHideCIIActorQuery());
    
    return tests;
  }

}
