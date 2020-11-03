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
package org.polarsys.capella.test.diagram.filters.ju.mcb;

import java.util.ArrayList;
import java.util.List;

import org.polarsys.capella.test.framework.api.BasicTestArtefact;
import org.polarsys.capella.test.framework.api.BasicTestSuite;

public class MCBDiagramFiltersTestSuite extends BasicTestSuite {

  @Override
  protected List<BasicTestArtefact> getTests() {
    List<BasicTestArtefact> tests = new ArrayList<>();
    tests.add(new HideActorCapabilityInvolvementsForMCB());
    tests.add(new HideActorGeneralizationsForMCB());
    tests.add(new HideActorMissionInvolvementsForMCB());
    tests.add(new HideActorsForMCB());
    tests.add(new HideCapabilitiesForMCB());
    tests.add(new HideCapabilityExploitationsForMCB());
    tests.add(new HideCapabilityExtendsForMCB());
    tests.add(new HideCapabilityIncludesForMCB());
    tests.add(new HideCapabilityGeneralizationsForMCB());
    tests.add(new HideMissionsForMCB());
    tests.add(new HidePropertyValuesForMCB());
    return tests;
  }

}
