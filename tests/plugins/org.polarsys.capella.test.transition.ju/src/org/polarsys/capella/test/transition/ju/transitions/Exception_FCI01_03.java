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

import org.polarsys.capella.test.transition.ju.TopDownTransitionTestCase;

/**
 * Test the functional transition - CreateRule-SF01
 * 
 * <pre>
 * Model is created with the following elements�
 * - Create SF1, SF2 SF3 SF4 SF5
 * - Create FE8 FE9 FE10 FE11 between functions
 * - Create FC6 (SF1 FE8 SF2 FE9 SF3 FE10 Alt(FC7, SF4) 
 * - Create FC7 (SF4 FE11 SF5)
 * - Perform transition of FC6
 * - Add something into FC6
 * 
 * Expected Result:\
 * -  A functional transition on root should perform transition of the new FCI
 * </pre>
 * 
 */
public class Exception_FCI01_03 extends TopDownTransitionTestCase {
  private String id_fc6 = "26ed7779-8dd0-44c9-aba5-24872f755c02";
  private String id_fci11 = "3c98b2df-a72b-4929-94aa-209ff81a8eb4";

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
    mustBeTransitioned(id_fc6);
    // New FCI should also be transitioned
    mustBeTransitioned(id_fci11);
  }

}
