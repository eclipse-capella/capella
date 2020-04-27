/*******************************************************************************
 * Copyright (c) 2017, 2019 THALES GLOBAL SERVICES.
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

import org.polarsys.capella.test.diagram.tools.ju.xdfb.CompositeChainDisplay;
import org.polarsys.capella.test.diagram.tools.ju.xdfb.XDFBCreateActorFunction;
import org.polarsys.capella.test.diagram.tools.ju.xdfb.XDFBCreateConstraint;
import org.polarsys.capella.test.diagram.tools.ju.xdfb.XDFBCreateConstraintLink;
import org.polarsys.capella.test.diagram.tools.ju.xdfb.XDFBCreateFunction;
import org.polarsys.capella.test.diagram.tools.ju.xdfb.XDFBCreateFunctionalChain;
import org.polarsys.capella.test.diagram.tools.ju.xdfb.XDFBCreateFunctionalExchange;
import org.polarsys.capella.test.diagram.tools.ju.xdfb.XDFBCreateInputOutputPort;
import org.polarsys.capella.test.diagram.tools.ju.xdfb.XDFBDragAndDropTest;
import org.polarsys.capella.test.diagram.tools.ju.xdfb.XDFBInitializeFromDiagram;
import org.polarsys.capella.test.diagram.tools.ju.xdfb.XDFBInsertElementsFromModeState;
import org.polarsys.capella.test.diagram.tools.ju.xdfb.XDFBInsertElementsFromScenario;
import org.polarsys.capella.test.diagram.tools.ju.xdfb.XDFBInsertPropertyValuesAndGroups;
import org.polarsys.capella.test.diagram.tools.ju.xdfb.XDFBSetContextualElementsScenario;
import org.polarsys.capella.test.diagram.tools.ju.xdfb.XDFBShowHideConstraint;
import org.polarsys.capella.test.diagram.tools.ju.xdfb.XDFBShowHideFunctionPorts;
import org.polarsys.capella.test.diagram.tools.ju.xdfb.XDFBShowHideFunctionalChain;
import org.polarsys.capella.test.diagram.tools.ju.xdfb.XDFBShowHideFunctionalExchange;
import org.polarsys.capella.test.diagram.tools.ju.xdfb.XDFBShowHideFunctions;
import org.polarsys.capella.test.diagram.tools.ju.xdfb.XDFBSwitchFunctionalExchangeCategory;
import org.polarsys.capella.test.diagram.tools.ju.xdfb.XDFBTestCaseFunctions;
import org.polarsys.capella.test.diagram.tools.ju.xdfb.XDFBToolReconnect;
import org.polarsys.capella.test.framework.api.BasicTestArtefact;
import org.polarsys.capella.test.framework.api.BasicTestSuite;

import junit.framework.Test;

public class XDFBDiagramToolsTestSuite extends BasicTestSuite {

  /**
   * Returns the suite. This is required to unary launch this test.
   */
  public static Test suite() {
    return new XDFBDiagramToolsTestSuite();
  }
  
  public List<String> getRequiredTestModels() {
    return Arrays.asList("XDFBToolsTestingModel");
  }

  /**
   * @see org.polarsys.capella.test.framework.api.BasicTestSuite#getTests()
   */
  @Override
  protected List<BasicTestArtefact> getTests() {

    List<BasicTestArtefact> tests = new ArrayList<BasicTestArtefact>();

    tests.add(new XDFBCreateFunction());
    tests.add(new XDFBCreateActorFunction());
    tests.add(new XDFBCreateFunctionalExchange());
    tests.add(new XDFBCreateInputOutputPort());
    tests.add(new XDFBCreateFunctionalChain());
    tests.add(new XDFBShowHideFunctions());
    tests.add(new XDFBShowHideFunctionalExchange());
    tests.add(new XDFBShowHideFunctionPorts());
    tests.add(new XDFBShowHideFunctionalChain());
    tests.add(new XDFBSwitchFunctionalExchangeCategory());
    tests.add(new XDFBCreateConstraint());
    tests.add(new XDFBShowHideConstraint());
    tests.add(new XDFBCreateConstraintLink());
    tests.add(new XDFBInsertPropertyValuesAndGroups());
    tests.add(new XDFBInsertElementsFromScenario());
    tests.add(new XDFBInsertElementsFromModeState());
    tests.add(new XDFBToolReconnect());
    tests.add(new XDFBTestCaseFunctions());
    tests.add(new XDFBSetContextualElementsScenario());
    tests.add(new CompositeChainDisplay());

    tests.add(new XDFBDragAndDropTest());
    return tests;
  }
  
  /**
   * 
   * Each test cases of this test suite reloads its test model
   */
  public static class WithReloadedTestModel extends BasicTestSuite {

    /**
     * Returns the suite. This is required to unary launch this test.
     */
    public static Test suite() {
      return new XDFBDiagramToolsTestSuite();
    }

    /**
     * @see org.polarsys.capella.test.framework.api.BasicTestSuite#getTests()
     */
    @Override
    protected List<BasicTestArtefact> getTests() {

      List<BasicTestArtefact> tests = new ArrayList<BasicTestArtefact>();
      tests.add(new XDFBInitializeFromDiagram());
      return tests;
    }
  }
}
