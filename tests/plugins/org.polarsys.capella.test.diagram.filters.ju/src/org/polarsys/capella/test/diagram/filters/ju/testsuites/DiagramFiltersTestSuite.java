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
package org.polarsys.capella.test.diagram.filters.ju.testsuites;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.polarsys.capella.test.diagram.filters.ju.cc.CCDiagramFiltersTestSuite;
import org.polarsys.capella.test.diagram.filters.ju.cdb.CDBDiagramFiltersTestSuite;
import org.polarsys.capella.test.diagram.filters.ju.cdi.CDIDiagramFiltersTestSuite;
import org.polarsys.capella.test.diagram.filters.ju.cei.CEIDiagramFiltersTestSuite;
import org.polarsys.capella.test.diagram.filters.ju.cii.CIIDiagramFiltersTestSuite;
import org.polarsys.capella.test.diagram.filters.ju.cm.CMDiagramFiltersTestSuite;
import org.polarsys.capella.test.diagram.filters.ju.coc.COCDiagramFiltersTestSuite;
import org.polarsys.capella.test.diagram.filters.ju.crb.CRBDiagramFiltersTestSuite;
import org.polarsys.capella.test.diagram.filters.ju.idb.IDBDiagramFiltersTestSuite;
import org.polarsys.capella.test.diagram.filters.ju.lab.LABDiagramFiltersTestSuite;
import org.polarsys.capella.test.diagram.filters.ju.lcbd.LCBDDiagramFiltersTestSuite;
import org.polarsys.capella.test.diagram.filters.ju.ldfb.LDFBDiagramFiltersTestSuite;
import org.polarsys.capella.test.diagram.filters.ju.lfbd.LFBDDiagramFiltersTestSuite;
import org.polarsys.capella.test.diagram.filters.ju.mb.MBDiagramFiltersTestSuite;
import org.polarsys.capella.test.diagram.filters.ju.mcb.MCBDiagramFiltersTestSuite;
import org.polarsys.capella.test.diagram.filters.ju.oab.OABDiagramFiltersTestSuite;
import org.polarsys.capella.test.diagram.filters.ju.ocb.OCBDiagramFiltersTestSuite;
import org.polarsys.capella.test.diagram.filters.ju.orb.ORBDiagramFiltersTestSuite;
import org.polarsys.capella.test.diagram.filters.ju.pab.PABDiagramFiltersTestSuite;
import org.polarsys.capella.test.diagram.filters.ju.pdfb.PDFBDiagramFiltersTestSuite;
import org.polarsys.capella.test.diagram.filters.ju.pfbd.PFBDDiagramFiltersTestSuite;
import org.polarsys.capella.test.diagram.filters.ju.sab.SABDiagramFiltersTestSuite;
import org.polarsys.capella.test.diagram.filters.ju.sdfb.SDFBDiagramFiltersTestSuite;
import org.polarsys.capella.test.diagram.filters.ju.sfbd.SFBDDiagramFiltersTestSuite;
import org.polarsys.capella.test.diagram.filters.ju.xab.XABDiagramFiltersTestSuite;
import org.polarsys.capella.test.diagram.filters.ju.xdfb.XDFBDiagramFiltersTestSuite;
import org.polarsys.capella.test.diagram.filters.ju.xfcd.XFCDDiagramFiltersTestSuite;
import org.polarsys.capella.test.framework.api.BasicTestArtefact;
import org.polarsys.capella.test.framework.api.BasicTestSuite;

import junit.framework.Test;

public class DiagramFiltersTestSuite extends BasicTestSuite {

  /**
   * Returns the suite. This is required to unary launch this test.
   */
  public static Test suite() {
    return new DiagramFiltersTestSuite();
  }

  @Override
  public List<String> getRequiredTestModels() {
    return Arrays.asList("ElementLabelFilterModel", "HideFunctionalExchange", "HideSimplifiedLinksFilter", "model2", //$NON-NLS-1$
        "model3_multpart", "PB8", "PB9", "Project_validation_hideControlNodesFilter",
        "Project_validation_hideTechnicalInterfaceFilter", "ShowTriggerSourceFunctionFilter",
        "StandardDiagramFiltersModel", "Test_delegationWizard", "TitleBlocksModel");
  }

  /**
   * @see org.polarsys.capella.test.framework.api.BasicTestSuite#getTests()
   */
  @Override
  protected List<BasicTestArtefact> getTests() {
    List<BasicTestArtefact> tests = new ArrayList<BasicTestArtefact>();

    tests.add(new CDBDiagramFiltersTestSuite());
    tests.add(new CRBDiagramFiltersTestSuite());
    tests.add(new XFCDDiagramFiltersTestSuite());
    tests.add(new XABDiagramFiltersTestSuite());
    tests.add(new CDIDiagramFiltersTestSuite());
    tests.add(new CEIDiagramFiltersTestSuite());
    tests.add(new CIIDiagramFiltersTestSuite());
    tests.add(new IDBDiagramFiltersTestSuite());
    tests.add(new XDFBDiagramFiltersTestSuite());

    tests.add(new OABDiagramFiltersTestSuite());
    tests.add(new COCDiagramFiltersTestSuite());
    tests.add(new ORBDiagramFiltersTestSuite());
    tests.add(new OCBDiagramFiltersTestSuite());

    tests.add(new CCDiagramFiltersTestSuite());
    tests.add(new CMDiagramFiltersTestSuite());
    tests.add(new MBDiagramFiltersTestSuite());
    tests.add(new MCBDiagramFiltersTestSuite());
    tests.add(new SABDiagramFiltersTestSuite());
    tests.add(new SDFBDiagramFiltersTestSuite());
    tests.add(new SFBDDiagramFiltersTestSuite());

    tests.add(new LABDiagramFiltersTestSuite());

    tests.add(new LDFBDiagramFiltersTestSuite());
    tests.add(new LFBDDiagramFiltersTestSuite());
    tests.add(new LCBDDiagramFiltersTestSuite());

    tests.add(new PABDiagramFiltersTestSuite());
    tests.add(new PFBDDiagramFiltersTestSuite());
    tests.add(new PDFBDiagramFiltersTestSuite());

    return tests;
  }
}
