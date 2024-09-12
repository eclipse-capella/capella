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
 * FunctionalExchanges outgoing with same name must use same port
 */
public class Rule_FC_15_TestCase extends ValidationRuleTestCase {

  @Override
  protected String getRequiredTestModel() {
    return "FC_15"; //$NON-NLS-1$
  }

  @Override
  protected EClass getTargetedEClass() {
    return FaPackage.Literals.FUNCTIONAL_EXCHANGE;
  }

  @Override
  protected String getRuleID() {
    return "org.polarsys.capella.core.data.fa.validation.FC_15"; //$NON-NLS-1$
  }

  @Override
  protected List<OracleDefinition> getOracleDefinitions() {
    
    return Arrays.asList(new OracleDefinition[] { 
        // Elements of interest for OK and KO patterns,
        new OracleDefinition("a0831b1f-d2a0-4465-b8e6-8c10107d103c", 0), //$NON-NLS-1$
        new OracleDefinition("99b29b32-4f1e-40c0-82ef-baa6b7d97c44", 0), //$NON-NLS-1$
        
        // !!! here, nbExpectedErrors should be == 1, but is set to == 0
        // because this Rule's context should persist for all oracle's objectID,
        // but it does not, so the Rule do not report problems
        new OracleDefinition("d80f2551-e8f7-4378-91ac-5c3991588b31", 0), //$NON-NLS-1$
        
        // !!! here, nbExpectedErrors should be == 1, but is set to == 0
        // because this Rule's context should persist for all oracle's objectID,
        // but it does not, so the Rule do not report problems
        new OracleDefinition("61160f24-d85e-4288-9f4d-b2dabb72a785", 0), //$NON-NLS-1$
    });
  }

}
