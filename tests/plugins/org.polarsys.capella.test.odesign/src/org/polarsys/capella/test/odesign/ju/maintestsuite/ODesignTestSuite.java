/*******************************************************************************
 * Copyright (c) 2019, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.odesign.ju.maintestsuite;

import java.util.ArrayList;
import java.util.List;

import org.polarsys.capella.test.framework.api.BasicTestArtefact;
import org.polarsys.capella.test.framework.api.BasicTestSuite;
import org.polarsys.capella.test.odesign.identifier.DuplicateToolsAndFiltersTest;
import org.polarsys.capella.test.odesign.identifier.ToolActivityCoverageTest;
import org.polarsys.capella.test.odesign.identifier.ToolAndLabelCoherenceTest;
import org.polarsys.capella.test.odesign.identifier.ToolIdentifierConsistencyTest;
import org.polarsys.capella.test.odesign.ju.deletecheck.CheckDeletionDescriptionTest;
import org.polarsys.capella.test.odesign.ju.directeditlabel.DirectEditLabelTestSuite;
import org.polarsys.capella.test.odesign.ju.domainclass.CheckDomainClassTestSuite;
import org.polarsys.capella.test.odesign.typereferencename.CheckTypeReferenceNameTestSuite;

import junit.framework.Test;

public class ODesignTestSuite extends BasicTestSuite {

  public static Test suite() {
    return new ODesignTestSuite();
  }

  @Override
  protected List<BasicTestArtefact> getTests() {
    List<BasicTestArtefact> tests = new ArrayList<BasicTestArtefact>();

    tests.add(new DirectEditLabelTestSuite());
    tests.add(new CheckDomainClassTestSuite());
    tests.add(new CheckTypeReferenceNameTestSuite());
    tests.add(new DuplicateToolsAndFiltersTest());
    tests.add(new ToolActivityCoverageTest());
    tests.add(new ToolIdentifierConsistencyTest());
    tests.add(new ToolAndLabelCoherenceTest());
    tests.add(new CheckDeletionDescriptionTest());
    return tests;
  }

}
