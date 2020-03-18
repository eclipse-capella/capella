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
import org.polarsys.capella.test.transition.ju.model.CreateRule_Scenario_IS2IS;

public class CreateRule_IS2IS_StateFragment_01 extends CreateRule_Scenario_IS2IS {
  
  @Override
  public void performTest() throws Exception {

    setPreferenceValue(ITopDownConstants.OPTIONS_TRANSITION__FUNCTIONAL, false);
    setPreferenceValue(ITopDownConstants.OPTIONS_TRANSITION__STATE_MACHINE, false);
    
    performIStoISTransition(Arrays.asList(getObject(IS_CAPABILITY_1)));

    mustBeMonoTransitioned(STATE_MODE2); //This mode shall be propagated, as already exist
    mustBeMonoTransitioned(STATE_SF2); //This function shall be propagated, as already exist
    mustBeMonoTransitioned(STATE_SF1); //This function shall be propagated, as already exist
    
    mustBeMonoTransitioned(STATE_SF6); //This function shall be propagated (once OPTIONS_TRANSITION__FUNCTIONAL will work on all layer, this shall be replaced by shouldNotBeTransitioned)
    shouldNotBeTransitioned(STATE_MODE1); //This mode shall not be propagated
    
  }

}
