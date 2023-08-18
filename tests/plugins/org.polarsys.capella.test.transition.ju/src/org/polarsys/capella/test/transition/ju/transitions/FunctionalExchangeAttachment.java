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
public class FunctionalExchangeAttachment extends TopDownTransitionTestCase {
  private String id_feo1111 = "d97e3598-6cb3-4f3c-8b6e-2bd2f21e7280";
  private String id_fes1111 = "062a7f9c-939a-4387-a103-a8758470263e";
  private String id_fel1111 = "bb0013c8-69e5-41c9-89a2-edd739c42cd0";

  @Override
  public List<String> getRequiredTestModels() {
    return Arrays.asList("Context_SF01");
  }

  @Override
  public void performTest() throws Exception {
    step1();
    step2();
    step3();
  }

  private void step1() {
    performFunctionalTransition(Arrays.asList(getObject(id_feo1111)));
    mustBeTransitioned(id_feo1111);
  }

  private void step2() {
    performFunctionalTransition(Arrays.asList(getObject(id_fes1111)));
    mustBeTransitioned(id_fes1111);
  }

  private void step3() {
    performFunctionalTransition(Arrays.asList(getObject(id_fel1111)));
    mustBeTransitioned(id_fel1111);
  }

}
