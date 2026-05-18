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
 * The interface EI shall be identical at SA to the CE EI allocated.
 */
public class Rule_FC_12_TestCase extends ValidationRuleTestCase {

  @Override
  protected String getRequiredTestModel() {
    return "FC_12"; //$NON-NLS-1$
  }

  @Override
  protected EClass getTargetedEClass() {
    return CsPackage.Literals.INTERFACE;
  }

  @Override
  protected String getRuleID() {
    return "org.polarsys.capella.core.data.cs.validation.FC_12"; //$NON-NLS-1$
  }

  @Override
  protected List<OracleDefinition> getOracleDefinitions() {
    
    return Arrays.asList(new OracleDefinition[] { 
        // Elements of interest for OK and KO patterns,
        new OracleDefinition("27657102-d96d-4a61-9726-d9f95091a41d", 0), //$NON-NLS-1$
        new OracleDefinition("608eadd0-f8c8-458c-8aa0-9b3eddbeca0b", 1), //$NON-NLS-1$
        new OracleDefinition("b23ccb60-a60e-4a8a-8a27-c05019047334", 1), //$NON-NLS-1$
        new OracleDefinition("27587cfb-f2ed-4834-a002-8af7a8d5d9b3", 0), //$NON-NLS-1$
    });
  }

}
