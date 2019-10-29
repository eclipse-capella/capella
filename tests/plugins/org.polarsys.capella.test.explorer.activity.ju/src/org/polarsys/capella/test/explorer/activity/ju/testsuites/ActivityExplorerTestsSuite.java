/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.explorer.activity.ju.testsuites;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.polarsys.capella.test.explorer.activity.ju.testcases.AutoOpen;
import org.polarsys.capella.test.explorer.activity.ju.testcases.CDBLogicalActivityExplorerTestCase;
import org.polarsys.capella.test.explorer.activity.ju.testcases.CDBOperationalActivityExplorerTestCase;
import org.polarsys.capella.test.explorer.activity.ju.testcases.CDBPhysicalActivityExplorerTestCase;
import org.polarsys.capella.test.explorer.activity.ju.testcases.CDBSystemActivityExplorerTestCase;
import org.polarsys.capella.test.explorer.activity.ju.testcases.CIBDActivityExplorerTestCase;
import org.polarsys.capella.test.explorer.activity.ju.testcases.CheckRightClickActions;
import org.polarsys.capella.test.explorer.activity.ju.testcases.EABActivityExplorerTestCase;
import org.polarsys.capella.test.explorer.activity.ju.testcases.ESLogicalActivityExplorerTestCase;
import org.polarsys.capella.test.explorer.activity.ju.testcases.ESPhysicalActivityExplorerTestCase;
import org.polarsys.capella.test.explorer.activity.ju.testcases.ESSystemActivtyExplorerTestCase;
import org.polarsys.capella.test.explorer.activity.ju.testcases.FSLogicalActivityExplorerTestCase;
import org.polarsys.capella.test.explorer.activity.ju.testcases.FSPhysicalActivityExplorerTestCase;
import org.polarsys.capella.test.explorer.activity.ju.testcases.FSSystemActivityExplorerTestCase;
import org.polarsys.capella.test.explorer.activity.ju.testcases.ISLogicalActivityExplorerTestCase;
import org.polarsys.capella.test.explorer.activity.ju.testcases.ISPhysicalActivityExplorerTestCase;
import org.polarsys.capella.test.explorer.activity.ju.testcases.ISSystemActivityExplorerTestCase;
import org.polarsys.capella.test.explorer.activity.ju.testcases.LABActivityExplorerTestCase;
import org.polarsys.capella.test.explorer.activity.ju.testcases.LASystemFunctionalTransitionDiagramActivityExplorerTestCase;
import org.polarsys.capella.test.explorer.activity.ju.testcases.LCBDActivityExplorerTestCase;
import org.polarsys.capella.test.explorer.activity.ju.testcases.LDFBActivityExplorerTestCase;
import org.polarsys.capella.test.explorer.activity.ju.testcases.LFBDActivityExplorerTestCase;
import org.polarsys.capella.test.explorer.activity.ju.testcases.ManualOpen;
import org.polarsys.capella.test.explorer.activity.ju.testcases.MultipleModels;
import org.polarsys.capella.test.explorer.activity.ju.testcases.OABActivityExplorerTestCase;
import org.polarsys.capella.test.explorer.activity.ju.testcases.OABDActivityExplorerTestCase;
import org.polarsys.capella.test.explorer.activity.ju.testcases.OAIBActivityExplorerTestCase;
import org.polarsys.capella.test.explorer.activity.ju.testcases.OASActivityExplorerTestCase;
import org.polarsys.capella.test.explorer.activity.ju.testcases.OCBActivityExplorerTestCase;
import org.polarsys.capella.test.explorer.activity.ju.testcases.OEBDActivityExplorerTestCase;
import org.polarsys.capella.test.explorer.activity.ju.testcases.OESActivityExplorerTestCase;
import org.polarsys.capella.test.explorer.activity.ju.testcases.ORBActivityExplorerTestCase;
import org.polarsys.capella.test.explorer.activity.ju.testcases.PABActivityExplorerTestCase;
import org.polarsys.capella.test.explorer.activity.ju.testcases.PALogicalFunctionalTransitionDiagramActivityExplorerTestCase;
import org.polarsys.capella.test.explorer.activity.ju.testcases.PCBDActivityExplorerTestCase;
import org.polarsys.capella.test.explorer.activity.ju.testcases.PDFBActivityExplorerTestCase;
import org.polarsys.capella.test.explorer.activity.ju.testcases.PFBDActivityExplorerTestCase;
import org.polarsys.capella.test.explorer.activity.ju.testcases.SABActivityExplorerTestCase;
import org.polarsys.capella.test.explorer.activity.ju.testcases.SAOperationalActivitiesTransitionDiagramActivityExplorerTestCase;
import org.polarsys.capella.test.explorer.activity.ju.testcases.SDFBActivityExplorerTestCase;
import org.polarsys.capella.test.explorer.activity.ju.testcases.SFBDActivityExplorerTestCase;
import org.polarsys.capella.test.explorer.activity.ju.testcases.ScenarioName;
import org.polarsys.capella.test.framework.api.BasicTestArtefact;
import org.polarsys.capella.test.framework.api.BasicTestSuite;

import junit.framework.Test;

/**
 * Test Suite - Activity Explorer (integration in Capella).
 */
public class ActivityExplorerTestsSuite extends BasicTestSuite {

  /**
   * {@inheritDoc}
   */
  @Override
  protected List<BasicTestArtefact> getTests() {
    List<BasicTestArtefact> tests = new ArrayList<BasicTestArtefact>();
    tests.add(new LASystemFunctionalTransitionDiagramActivityExplorerTestCase());
    tests.add(new PALogicalFunctionalTransitionDiagramActivityExplorerTestCase());
    tests.add(new SAOperationalActivitiesTransitionDiagramActivityExplorerTestCase());
    tests.add(new CIBDActivityExplorerTestCase());
    tests.add(new CDBLogicalActivityExplorerTestCase());
    tests.add(new CDBOperationalActivityExplorerTestCase());
    tests.add(new CDBPhysicalActivityExplorerTestCase());
    tests.add(new CDBSystemActivityExplorerTestCase());
    tests.add(new EABActivityExplorerTestCase());
    tests.add(new ESLogicalActivityExplorerTestCase());
    tests.add(new ESPhysicalActivityExplorerTestCase());
    tests.add(new ESSystemActivtyExplorerTestCase());
    tests.add(new FSLogicalActivityExplorerTestCase());
    tests.add(new FSPhysicalActivityExplorerTestCase());
    tests.add(new FSSystemActivityExplorerTestCase());
    tests.add(new ISLogicalActivityExplorerTestCase());
    tests.add(new ISPhysicalActivityExplorerTestCase());
    tests.add(new ISSystemActivityExplorerTestCase());
    tests.add(new LABActivityExplorerTestCase());
    tests.add(new LCBDActivityExplorerTestCase());
    tests.add(new LDFBActivityExplorerTestCase());
    tests.add(new LFBDActivityExplorerTestCase());
    tests.add(new OABActivityExplorerTestCase());
    tests.add(new OABDActivityExplorerTestCase());
    tests.add(new OAIBActivityExplorerTestCase());
    tests.add(new OASActivityExplorerTestCase());
    tests.add(new OCBActivityExplorerTestCase());
    tests.add(new OEBDActivityExplorerTestCase());
    tests.add(new ORBActivityExplorerTestCase());
    tests.add(new OESActivityExplorerTestCase());
    tests.add(new PABActivityExplorerTestCase());
    tests.add(new PCBDActivityExplorerTestCase());
    tests.add(new PDFBActivityExplorerTestCase());
    tests.add(new PFBDActivityExplorerTestCase());
    tests.add(new SABActivityExplorerTestCase());
    tests.add(new SDFBActivityExplorerTestCase());
    tests.add(new SFBDActivityExplorerTestCase());
    tests.add(new AutoOpen());
    tests.add(new ManualOpen());
    tests.add(new MultipleModels());
    tests.add(new ScenarioName());
    tests.add(new CheckRightClickActions());
    return tests;
  }

  /**
   * Added in order to launch this test suite without the Capella test framework.
   * 
   * @return
   */
  public static Test suite() {
    return new ActivityExplorerTestsSuite();
  }

  @Override
  public List<String> getRequiredTestModels() {
    return Arrays.asList("EmptyModel");
  }

}
