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
package org.polarsys.capella.test.transition.ju.transitions;

import java.util.Arrays;
import java.util.List;

import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.test.transition.ju.TopDownTransitionTestCase;

/**
 * Test the contextual transition - Context00 - Context01
 * 
 * <pre>
 * Model is created with the following elements�
 * Some functions in OA, all others root function are deleted
 * 
 * 
 * Expected Result:\
 * Transitions are performed in all phases
 * </pre>
 */
public class Context_SF01_07 extends TopDownTransitionTestCase {

  private String id_OpPkgA = "2f3ce7fc-5762-441b-8316-b20184e92508";
  private String id_OPPkgB = "12922eee-c739-4870-95a2-88f9ee75e7c7";
  private String id_OA2 = "4053887b-b086-4b41-a5ad-d19059ae602d";

  @Override
  public List<String> getRequiredTestModels() {
    return Arrays.asList("Context_SF01");
  }

  @Override
  public void performTest() throws Exception {
    oa2sa();
    sa2la();
    la2pa();
  }

  private void oa2sa() {
    performFunctionalTransition(Arrays.asList(getObject(id_OA2)));
    id_OpPkgA = ((CapellaElement) mustBeTransitioned(id_OpPkgA)).getId();
    id_OPPkgB = ((CapellaElement) mustBeTransitioned(id_OPPkgB)).getId();
    id_OA2 = ((CapellaElement) mustBeTransitioned(id_OA2)).getId();
  }

  private void sa2la() {
    performFunctionalTransition(Arrays.asList(getObject(id_OA2)));
    id_OpPkgA = ((CapellaElement) mustBeTransitioned(id_OpPkgA)).getId();
    id_OPPkgB = ((CapellaElement) mustBeTransitioned(id_OPPkgB)).getId();
    id_OA2 = ((CapellaElement) mustBeTransitioned(id_OA2)).getId();
  }

  private void la2pa() {
    performFunctionalTransition(Arrays.asList(getObject(id_OA2)));
    id_OpPkgA = ((CapellaElement) mustBeTransitioned(id_OpPkgA)).getId();
    id_OPPkgB = ((CapellaElement) mustBeTransitioned(id_OPPkgB)).getId();
    id_OA2 = ((CapellaElement) mustBeTransitioned(id_OA2)).getId();
  }
}