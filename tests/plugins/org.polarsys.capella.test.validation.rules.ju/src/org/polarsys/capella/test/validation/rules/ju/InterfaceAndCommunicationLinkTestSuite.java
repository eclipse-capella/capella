/*******************************************************************************
 * Copyright (c) 2006, 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.test.validation.rules.ju;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Test;

import org.polarsys.capella.test.framework.api.BasicTestSuite;
import org.polarsys.capella.test.validation.rules.ju.testcases.Rule_DC_CL_01;
import org.polarsys.capella.test.validation.rules.ju.testcases.Rule_DC_CL_02;
import org.polarsys.capella.test.validation.rules.ju.testcases.Rule_DWF_I_05;
import org.polarsys.capella.test.validation.rules.ju.testcases.Rule_DWF_I_14;
import org.polarsys.capella.test.validation.rules.ju.testcases.Rule_DWF_I_15;
import org.polarsys.capella.test.validation.rules.ju.testcases.Rule_DWF_I_16;
import org.polarsys.capella.test.validation.rules.ju.testcases.Rule_DWF_I_17;
import org.polarsys.capella.test.validation.rules.ju.testcases.Rule_DWF_I_18;
import org.polarsys.capella.test.validation.rules.ju.testcases.Rule_DWF_I_19;
import org.polarsys.capella.test.validation.rules.ju.testcases.Rule_DWF_I_20;
import org.polarsys.capella.test.validation.rules.ju.testcases.Rule_TC_I_11;
import org.polarsys.capella.test.validation.rules.ju.testcases.Rule_TC_I_12;
import org.polarsys.capella.test.validation.rules.ju.testcases.Rule_TC_I_13;

/**
 * @author Erwan Brottier
 */
public class InterfaceAndCommunicationLinkTestSuite extends BasicTestSuite {

  @Override
  protected String getPluginId() {
  	return TestValidationRulesPlugin.PLUGIN_ID;
  }

  @Override
  protected List<Test> getTests() {
    List<Test> tests = new ArrayList<Test>();
    tests.add(new Rule_DC_CL_01());
    tests.add(new Rule_DC_CL_02());
    tests.add(new Rule_DWF_I_05());
    tests.add(new Rule_DWF_I_14());
    tests.add(new Rule_DWF_I_15());
    tests.add(new Rule_DWF_I_16());
    tests.add(new Rule_DWF_I_17());
    tests.add(new Rule_DWF_I_18());
    tests.add(new Rule_DWF_I_19());
    tests.add(new Rule_DWF_I_20());
    tests.add(new Rule_TC_I_11());
    tests.add(new Rule_TC_I_12());
    tests.add(new Rule_TC_I_13());
    return tests;
  }

  /** Mandatory for launching tests. */
  public static Test suite() {
    return new InterfaceAndCommunicationLinkTestSuite();
  }
}
