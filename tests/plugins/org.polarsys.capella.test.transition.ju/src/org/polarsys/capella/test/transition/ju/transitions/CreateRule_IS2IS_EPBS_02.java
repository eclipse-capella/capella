/*******************************************************************************
 * Copyright (c) 2020 THALES GLOBAL SERVICES.
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

import org.polarsys.capella.core.transition.system.topdown.constants.ITopDownConstants;
import org.polarsys.capella.test.transition.ju.model.CreateRule_Scenario_IS2IS_EPBS;

public class CreateRule_IS2IS_EPBS_02 extends  CreateRule_Scenario_IS2IS_EPBS {
  
  @Override
  public void performTest() throws Exception {
    setPreferenceValue(ITopDownConstants.OPTIONS_TRANSITION__PCCI_ENABLED, true);

    performIStoISTransition(Arrays.asList(getObject(IS_CAPABILITYREALIZATION_1)));

    mustBeMonoTransitioned(IR_PC_6);
    mustBeMonoTransitioned(SM56);
  }

}
