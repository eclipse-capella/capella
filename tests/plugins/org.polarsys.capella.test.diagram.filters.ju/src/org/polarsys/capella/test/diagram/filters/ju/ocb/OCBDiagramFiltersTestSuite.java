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
package org.polarsys.capella.test.diagram.filters.ju.ocb;

import java.util.ArrayList;
import java.util.List;

import org.polarsys.capella.test.framework.api.BasicTestArtefact;
import org.polarsys.capella.test.framework.api.BasicTestSuite;

public class OCBDiagramFiltersTestSuite extends BasicTestSuite {

  @Override
  protected List<BasicTestArtefact> getTests() {
    List<BasicTestArtefact> tests = new ArrayList<>();
    tests.add(new HideCommunicationMeansForOCB());
    tests.add(new HideInvolvmentLinksForOCB());
    tests.add(new HideOperationalCapabilityExtendsForOCB());
    tests.add(new HideOperationalCapabilityIncludesForOCB());
    tests.add(new HideOperationalCapabilityGeneralizationsForOCB());
    tests.add(new HidePropertyValuesForOCB());
    return tests;
  }

}
