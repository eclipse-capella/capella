/*******************************************************************************
 * Copyright (c) 2019 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.test.model.ju.activityExplorer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.polarsys.capella.test.framework.api.BasicTestArtefact;
import org.polarsys.capella.test.framework.api.BasicTestSuite;

import junit.framework.Test;

public class ActivityExplorerTestSuite extends BasicTestSuite {

  @Override
  public List<String> getRequiredTestModels() {
    return Arrays.asList("miscmodel");
  }

  public static Test suite() {
    return new ActivityExplorerTestSuite();
  }

  @Override
  protected List<BasicTestArtefact> getTests() {
    List<BasicTestArtefact> tests = new ArrayList<BasicTestArtefact>();
    tests.add(new CIBDActivtiyExplorerTestCase());
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
    tests.add(new OESActivityExplorerTestCase());
    tests.add(new PABActivityExplorerTestCase());
    tests.add(new PCBDActivityExplorerTestCase());
    tests.add(new PDFBActivityExplorerTestCase());
    tests.add(new PFBDActivityExplorerTestCase());
    tests.add(new SABActivityExplorerTestCase());
    tests.add(new SDFBActivityExplorerTestCase());
    tests.add(new SFBDActivityExplorerTestCase());
    

    return tests;
  }
}
