/*******************************************************************************
 * Copyright (c) 2024 Thales LAS France SAS.
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
import org.polarsys.capella.core.data.ctx.CtxPackage;
import org.polarsys.capella.test.framework.api.OracleDefinition;
import org.polarsys.capella.test.validation.rules.ju.testcases.ValidationRuleTestCase;

/**
 * Actors name shall be unique
 */
public class Rule_FC_11_TestCase extends ValidationRuleTestCase {

  @Override
  protected String getRequiredTestModel() {
    return "FC_11"; //$NON-NLS-1$
  }

  @Override
  protected EClass getTargetedEClass() {
    return CtxPackage.Literals.SYSTEM_COMPONENT;
  }

  @Override
  protected String getRuleID() {
    return "org.polarsys.capella.core.data.cs.validation.FC_11"; //$NON-NLS-1$
  }

  @Override
  protected List<OracleDefinition> getOracleDefinitions() {
    
    return Arrays.asList(new OracleDefinition[] { 
        // Elements of interest for OK and KO patterns,
        
        // This rule will not report on first element (as a taboo-list is built step by step),
        new OracleDefinition("271a1737-da5b-45e7-97d7-ed026534c9f0", 0), //$NON-NLS-1$
        
        new OracleDefinition("272477d3-258f-4ab8-ba0a-2b3f3885a54d", 0), //$NON-NLS-1$

        // !!! here, nbExpectedErrors should be == 1, but is set to == 0
        // because this Rule's context should persist for all oracle's objectID,
        // but it does not, so the Rule do not report problems
        new OracleDefinition("51abde22-c6f6-4cfc-b480-53691e6ed112", 0), //$NON-NLS-1$
    });
  }

}
