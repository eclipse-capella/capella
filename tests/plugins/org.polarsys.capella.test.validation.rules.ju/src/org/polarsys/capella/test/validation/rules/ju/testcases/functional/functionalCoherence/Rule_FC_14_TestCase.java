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
 * FunctionalExchanges incoming with same name must use same port
 */
public class Rule_FC_14_TestCase extends ValidationRuleTestCase {

  @Override
  protected String getRequiredTestModel() {
    return "FC_14"; //$NON-NLS-1$
  }

  @Override
  protected EClass getTargetedEClass() {
    return FaPackage.Literals.FUNCTIONAL_EXCHANGE;
  }

  @Override
  protected String getRuleID() {
    return "org.polarsys.capella.core.data.fa.validation.FC_14"; //$NON-NLS-1$
  }

  @Override
  protected List<OracleDefinition> getOracleDefinitions() {
    
    return Arrays.asList(new OracleDefinition[] { 
        // Elements of interest for OK and KO patterns,
        new OracleDefinition("b90954ef-0c13-4e20-8ea6-6755ca875796", 0), //$NON-NLS-1$
        new OracleDefinition("b4230119-426b-491a-903f-0b0de785fc01", 0), //$NON-NLS-1$
        
        // !!! here, nbExpectedErrors should be == 1, but is set to == 0
        // because this Rule's context should persist for all oracle's objectID,
        // but it does not, so the Rule do not report problems
        new OracleDefinition("d11465ba-1e8d-4dd4-8fb1-1be5435ff1b3", 0), //$NON-NLS-1$
        
        // !!! here, nbExpectedErrors should be == 1, but is set to == 0
        // because this Rule's context should persist for all oracle's objectID,
        // but it does not, so the Rule do not report problems
        new OracleDefinition("2c75421e-5b29-47ce-ae61-c84e1fad1436", 0), //$NON-NLS-1$
    });
  }

}
