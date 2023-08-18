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
 */
public class CreateRule_ES2IS_02 extends TopDownTransitionTestCase {
  public static final String SYSTEM = "1f03e5f1-c3ee-410d-a61b-1bbefff461ca"; //$NON-NLS-1$
  public static final String PART_3 = "ee12e626-77d6-4526-85fc-95817c005dda"; //$NON-NLS-1$
  public static final String PART_2 = "1188a468-26ae-4c21-9075-158d66649d72"; //$NON-NLS-1$
  public static final String FUNCTIONALEXCHANGE_1 = "74e9b5f8-2644-4875-82aa-4c7246d51481"; //$NON-NLS-1$
  public static final String ES_CAPABILITY_1 = "889d7b96-9c5d-42a0-a8c7-1559d0cac729"; //$NON-NLS-1$

  @Override
  public List<String> getRequiredTestModels() {
    return Arrays.asList("CreateRule_Scenario");
  }

  @Override
  public void performTest() throws Exception {
    performEStoISTransition(Arrays.asList(getObject(ES_CAPABILITY_1)));
    mustBeTransitioned(ES_CAPABILITY_1);
    mustBeTransitioned(PART_2);
    mustBeTransitioned(PART_3);

    // FE1 shall not be transitioned as there is no ComponentExchange allocating it
    // (then Generation of Interface doesn't generate an Interface for the FE)
    shouldNotBeTransitioned(FUNCTIONALEXCHANGE_1);

  }

}
