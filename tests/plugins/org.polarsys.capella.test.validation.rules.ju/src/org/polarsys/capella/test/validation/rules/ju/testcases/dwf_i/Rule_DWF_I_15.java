/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.validation.rules.ju.testcases.dwf_i;

import java.util.Arrays;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.test.framework.api.OracleDefinition;
import org.polarsys.capella.test.validation.rules.ju.testcases.ValidationRuleTestCase;

/**
 * @author Erwan Brottier
 */
public class Rule_DWF_I_15 extends ValidationRuleTestCase {

  @Override
  protected String getRequiredTestModel() {
    return "Project_validation4"; //$NON-NLS-1$
  }

  @Override
  protected EClass getTargetedEClass() {
    return CsPackage.Literals.EXCHANGE_ITEM_ALLOCATION;
  }

  @Override
  protected String getRuleID() {
    return "org.polarsys.capella.core.data.cs.validation.DWF_I_15"; //$NON-NLS-1$
  }

  @Override
  protected List<OracleDefinition> getOracleDefinitions() {
    return Arrays.asList(new OracleDefinition[] {
        new OracleDefinition("d74adaeb-393b-426c-b5c9-a507f9ea02d6", 1), //$NON-NLS-1$
        new OracleDefinition("89dde182-0e46-44c8-9ade-e2286e2b9e1d", 1), //$NON-NLS-1$
        new OracleDefinition("c036a5da-5bae-4af0-8301-58b6412cd246", 1), //$NON-NLS-1$
        new OracleDefinition("6e8266d6-ba9d-41e7-8e9b-6f7e4ad6a298", 1), //$NON-NLS-1$
        new OracleDefinition("43de7e40-11b0-4c14-9acf-19be18a7625c", 1), //$NON-NLS-1$
        new OracleDefinition("eefe58d3-5f7c-44ec-88fe-16f401c9e3ce", 1), //$NON-NLS-1$
    });
  }
}
