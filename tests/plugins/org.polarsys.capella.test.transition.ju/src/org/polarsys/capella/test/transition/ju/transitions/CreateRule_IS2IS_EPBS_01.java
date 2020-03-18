/*******************************************************************************
 * Copyright (c) 2020 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.test.transition.ju.transitions;

import java.util.Arrays;

import org.polarsys.capella.core.transition.system.topdown.constants.ITopDownConstants;
import org.polarsys.capella.test.transition.ju.model.CreateRule_Scenario_IS2IS_EPBS;

public class CreateRule_IS2IS_EPBS_01 extends CreateRule_Scenario_IS2IS_EPBS {

  @Override
  public void performTest() throws Exception {
    setPreferenceValue(ITopDownConstants.OPTIONS_TRANSITION__PCCI_ENABLED, false);

    performIStoISTransition(Arrays.asList(getObject(IS_CAPABILITYREALIZATION_1)));

    //Ensure that Role of PC6 is not transitioned
    shouldNotBeTransitioned(IR_PC_6);
    shouldNotBeTransitioned(SM56);

  }

}
