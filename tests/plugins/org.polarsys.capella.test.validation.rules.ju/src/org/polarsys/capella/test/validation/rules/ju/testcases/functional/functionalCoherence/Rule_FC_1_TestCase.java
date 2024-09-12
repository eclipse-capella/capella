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
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.test.framework.api.OracleDefinition;
import org.polarsys.capella.test.validation.rules.ju.testcases.ValidationRuleTestCase;

/**
 * Components and actors should be connected by at least one CE
 */
public class Rule_FC_1_TestCase extends ValidationRuleTestCase {

  @Override
  protected String getRequiredTestModel() {
    return "FC_1"; //$NON-NLS-1$
  }

  @Override
  protected EClass getTargetedEClass() {
    return CsPackage.Literals.COMPONENT;
  }

  @Override
  protected String getRuleID() {
    return "org.polarsys.capella.core.data.cs.validation.FC_1"; //$NON-NLS-1$
  }

  @Override
  protected List<OracleDefinition> getOracleDefinitions() {
    return Arrays.asList(new OracleDefinition[] { 
        // Elements of interest for OK and KO patterns,
        new OracleDefinition(/*LA_1*/"a1e016eb-4521-4154-b4a5-3b2bc42739d0", 0), //$NON-NLS-1$
        new OracleDefinition(/*LC 1*/"2821bd52-df6c-47a6-acdc-93a771a2310f", 0), //$NON-NLS-1$
        new OracleDefinition(/*LA_2*/"6a8d1055-8943-4b30-9885-3ad00fe3c6d6", 1), //$NON-NLS-1$
        new OracleDefinition(/*LC 2*/"5dfa6515-53cb-46a0-945c-c6ad0c3d7bee", 1), //$NON-NLS-1$
        // Elements to ignore,
        new OracleDefinition(/*CtxSystem*/     "0d1a7ee3-3b2b-4a00-be3c-bd56940da7aa", 1), //$NON-NLS-1$
        new OracleDefinition(/*LogicalSystem*/ "7235d684-303b-4275-85ce-b0e34b7114aa", 1), //$NON-NLS-1$
        new OracleDefinition(/*PhysicalSystem*/"9eb201aa-9e19-46fc-ba96-547c240576ec", 1), //$NON-NLS-1$
    });
  }

}
