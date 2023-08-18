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

import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.test.transition.ju.TopDownTransitionTestCase;

/**
 * Test transition where many elements are traced by one
 * 
 * Expected Result:\ All elements should not be (re)created by default
 * </pre>
 */
public class ManyToOne extends TopDownTransitionTestCase {
  public static String MANYTOONE__SA__ROOTSF = "9f284d62-1006-4544-a09a-55f749f34d78";

  public static String MANYTOONE__SA__ROOTSF__SF1 = "fc9e0ba2-81f2-4ea0-a958-d81992f7a1de";
  public static String MANYTOONE__SA__ROOTSF__SF2 = "71feb156-e01d-46bf-804d-845562c61c93";
  public static String MANYTOONE__LA__ROOTLF__LF1 = "c2e4a000-433e-4ede-9b69-bf4c0e2c5d32";

  @Override
  public List<String> getRequiredTestModels() {
    return Arrays.asList("Context_SF01");
  }

  @Override
  public void performTest() throws Exception {
    step1();
  }

  /**
   * All traced elements mapped to one should not be created. Only one should exist in target model
   */
  private void step1() {
    performFunctionalTransition(Arrays.asList(getObject(MANYTOONE__SA__ROOTSF)));
    EObject SF1t = mustBeTransitioned(MANYTOONE__SA__ROOTSF__SF1);
    EObject SF2t = mustBeTransitioned(MANYTOONE__SA__ROOTSF__SF2);

    EObject LF1 = getObject(MANYTOONE__LA__ROOTLF__LF1);
    assertTrue(SF1t == LF1);
    assertTrue(SF2t == LF1);

    mustBeMultiTransitioned(MANYTOONE__SA__ROOTSF__SF1, 1);
    mustBeMultiTransitioned(MANYTOONE__SA__ROOTSF__SF2, 1);
  }

}
