/*******************************************************************************
 * Copyright (c) 2022 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.validation.rules.ju.testcases.dcom;

import java.util.Arrays;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.polarsys.capella.core.data.fa.FaPackage;
import org.polarsys.capella.test.framework.api.OracleDefinition;
import org.polarsys.capella.test.validation.rules.ju.testcases.AbstractRulesOnDesignTest;

/**
 * Test the validation rule for Functional Exchanges defined with input and output port on the same function
 */

public class Rule_DCOM_23 extends AbstractRulesOnDesignTest {

  @Override
  protected String getRuleID() {
    return "org.polarsys.capella.core.data.fa.validation.DCOM_23"; //$NON-NLS-1$
  }

  @Override
  protected List<String> getScopeDefinition() {
    return Arrays.asList(new String[] { "89e91b83-9564-40ba-8bf5-4b1c41a72918" });
  }

  @Override
  protected EClass getTargetedEClass() {
    return FaPackage.Literals.FUNCTIONAL_EXCHANGE;
  }

  @Override
  protected List<OracleDefinition> getOracleDefinitions() {
    return Arrays.asList(new OracleDefinition[] { new OracleDefinition("89e91b83-9564-40ba-8bf5-4b1c41a72918", 1) });
  }

}
