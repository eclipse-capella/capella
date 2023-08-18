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

import org.eclipse.osgi.util.NLS;
import org.polarsys.capella.core.data.fa.FunctionalChain;
import org.polarsys.capella.test.transition.ju.Messages;
import org.polarsys.capella.test.transition.ju.TopDownTransitionTestCase;

/**
 * Test the functional transition - CreateRule-SF01
 * 
 * <pre>
 * Model is created with the following elements�
 * - Create SF1, SF2 SF3, SF31 SF32 SF4 SF5
 * - Create FE8 FE9 FE10 FE11 between functions
 * - Create FC6 (SF1 FE8 SF2 FE9 SF3 FE10 OR(((SF4 FE11 SF5)), SF4) 
 * - Create FC7 (SF4 FE11 SF5)
 * - Perform transition of SF3
 * - Move SF31t, SF32t
 * - Delete SF3
 * 
 * Expected Result:\
 * 
 * -  A functional transition on root should :
 *    not transition FE9, SF3, FE10
 *    not transition FCI related to FE9, SF3, FE10
 * </pre>
 * 
 */
public class Exception_FCI01_02 extends TopDownTransitionTestCase {
  private String id_sf3 = "ee381f61-6840-4220-a8fd-3f474793bf78";
  private String id_fc6 = "26ed7779-8dd0-44c9-aba5-24872f755c02";
  private String id_fe9 = "45fb9697-7696-40a3-a6db-5875aeddea08";
  private String id_fe10 = "cfa43910-539d-4552-8265-bffba2e10042";
  @Override
  public List<String> getRequiredTestModels() {
    return Arrays.asList(getClass().getSimpleName());
  }

  @Override
  public void performTest() throws Exception {
    step1();
  }

  private void step1() {
    performFunctionalTransition(Arrays.asList(getObject(id_fc6)));
    shouldNotBeTransitioned(id_fe9);
    shouldNotBeTransitioned(id_sf3);
    shouldNotBeTransitioned(id_fe10);

    FunctionalChain fc6 = (FunctionalChain) shouldExist(id_fc6);
    FunctionalChain fc6t = (FunctionalChain) mustBeTransitioned(id_fc6);

    assertTrue(NLS.bind(Messages.ShouldBeTransitioned, "FC6"),
        fc6t.getOwnedFunctionalChainInvolvements().size() == fc6.getOwnedFunctionalChainInvolvements().size() - 3);
  }

}
