/*******************************************************************************
 * Copyright (c) 2024, 2024 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.validation.rules.ju.testcases.functional.functionalCoherence;

import java.util.Arrays;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.polarsys.capella.core.data.fa.FaPackage;
import org.polarsys.capella.test.framework.api.OracleDefinition;
import org.polarsys.capella.test.validation.rules.ju.testcases.ValidationRuleTestCase;

/**
 * FE between components/actors should be allocated to CE
 * Note: not tested when connected to an actor whose name contains:'User' (case-sensitive).
 */
public class Rule_FC_3_TestCase extends ValidationRuleTestCase {

  @Override
  protected String getRequiredTestModel() {
    return "FC_3"; //$NON-NLS-1$
  }

  @Override
  protected EClass getTargetedEClass() {
    return FaPackage.Literals.FUNCTIONAL_EXCHANGE;
  }

  @Override
  protected String getRuleID() {
    return "org.polarsys.capella.core.data.fa.validation.FC_3"; //$NON-NLS-1$
  }

  @Override
  protected List<OracleDefinition> getOracleDefinitions() {
    return Arrays.asList(new OracleDefinition[] { 
        // Elements of interest for OK and KO patterns,
        new OracleDefinition(/*FE_1*/"4fef2cc4-7e08-48f0-9ee6-4c94c6ec482b", 0), //$NON-NLS-1$
        new OracleDefinition(/*FE_2*/"8badb742-5b8d-45d1-9344-1cbd7c82ce69", 1), //$NON-NLS-1$
        new OracleDefinition(/*FE_3*/"63e64592-3095-40a3-b36a-a6bb39dad27a", 1), //$NON-NLS-1$
        new OracleDefinition(/*FE_4*/"33dacf70-bf6a-4aa8-9fd4-5357a395d310", 0), //$NON-NLS-1$
    });
  }

}
