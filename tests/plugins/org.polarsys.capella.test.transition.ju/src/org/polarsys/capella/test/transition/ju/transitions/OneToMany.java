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
 * Test transition where one element is treaced by many elements
 * 
 * <pre>
 * 
 * Expected Result:\
 * Sub elements should be created one the first traced element, and existing sub elements should not be moved
 * </pre>
 */
public class OneToMany extends TopDownTransitionTestCase {
  public static String ONETOMANY__SA__ROOTSF__SF1 = "bb6346b2-637c-49c8-affa-476ae99882a1";
  public static String ONETOMANY__SA__ROOTSF__SF1__SF11 = "21522472-31ac-46ca-a82f-67f6124dce96";

  public static String ONETOMANY__LA__ROOTLF__LF1 = "6ef320de-f833-42ec-b0fe-df66666af344";
  public static String ONETOMANY__LA__ROOTLF__LF1__LF11 = "5a6c3fc4-3f05-4bc3-b236-c783af4bb9a2";
  public static String ONETOMANY__LA__ROOTLF__LF2 = "9e00e9f8-b16b-41f9-8a85-808f6e42dd51";

  public static String ONETOMANY__SA__ROOTSF__SF1__SF12 = "4f0cdd7f-f748-4cfd-88f5-91c51a37fc68";
  public static String ONETOMANY__LA__ROOTLF__LF2__LF12 = "c42cc6d7-504d-4fec-a690-f84cb8a2104e";

  @Override
  public List<String> getRequiredTestModels() {
    return Arrays.asList("Context_SF01");
  }

  @Override
  public void performTest() throws Exception {
    step1();
  }

  /**
   * Sub elements should not be moved
   */
  private void step1() {
    performFunctionalTransition(Arrays.asList(getObject(ONETOMANY__SA__ROOTSF__SF1)));
    mustBeTransitioned(ONETOMANY__SA__ROOTSF__SF1__SF11);
    mustBeTransitioned(ONETOMANY__SA__ROOTSF__SF1__SF12);

    mustBeMultiTransitioned(ONETOMANY__SA__ROOTSF__SF1, 2);

    EObject LF1 = getObject(ONETOMANY__LA__ROOTLF__LF1);
    mustBeTransitionedDirecltyContainedBy("SF11", ONETOMANY__SA__ROOTSF__SF1__SF11, LF1);

    EObject LF2 = getObject(ONETOMANY__LA__ROOTLF__LF2);
    mustBeTransitionedDirecltyContainedBy("SF12", ONETOMANY__SA__ROOTSF__SF1__SF12, LF2);
  }

}
