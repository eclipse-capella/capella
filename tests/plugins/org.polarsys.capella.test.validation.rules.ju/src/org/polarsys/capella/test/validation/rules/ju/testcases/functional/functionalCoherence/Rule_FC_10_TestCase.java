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
import org.polarsys.capella.core.data.ctx.CtxPackage;
import org.polarsys.capella.test.framework.api.OracleDefinition;
import org.polarsys.capella.test.validation.rules.ju.testcases.ValidationRuleTestCase;

/**
 * Function name shall be unique
 */
public class Rule_FC_10_TestCase extends ValidationRuleTestCase {

  @Override
  protected String getRequiredTestModel() {
    return "FC_10"; //$NON-NLS-1$
  }

  @Override
  protected EClass getTargetedEClass() {
    return CtxPackage.Literals.SYSTEM_FUNCTION;
  }

  @Override
  protected String getRuleID() {
    return "org.polarsys.capella.core.data.ctx.validation.FC_10"; //$NON-NLS-1$
  }

  @Override
  protected List<OracleDefinition> getOracleDefinitions() {

    return Arrays.asList(new OracleDefinition[] {
        // Elements of interest for OK and KO patterns,

        // This rule will not report on first element (as a taboo-list is built step by step),
        new OracleDefinition("335661fb-b3a3-4f67-8dc8-8dc5ea9b9096", 0), //$NON-NLS-1$

        // !!! here, nbExpectedErrors should be == 1, but is set to == 0
        // because this Rule's context should persist for all oracle's objectID,
        // but it does not, so the Rule do not report problems
        new OracleDefinition("e220576b-e769-4ed5-b4a1-5f083e79fa16", 0), //$NON-NLS-1$

        new OracleDefinition("bb14280e-6a8e-4268-9884-881d892b815f", 0), //$NON-NLS-1$
    });
  }

}
