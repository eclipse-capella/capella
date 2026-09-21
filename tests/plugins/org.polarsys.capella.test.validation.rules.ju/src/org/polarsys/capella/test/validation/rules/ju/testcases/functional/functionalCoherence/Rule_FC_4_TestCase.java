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
import org.polarsys.capella.core.data.fa.FaPackage;
import org.polarsys.capella.test.framework.api.OracleDefinition;
import org.polarsys.capella.test.validation.rules.ju.testcases.ValidationRuleTestCase;

/**
 * Port with CE should have provided/required Interface
 */
public class Rule_FC_4_TestCase extends ValidationRuleTestCase {

  @Override
  protected String getRequiredTestModel() {
    return "FC_4"; //$NON-NLS-1$
  }

  @Override
  protected EClass getTargetedEClass() {
    return FaPackage.Literals.COMPONENT_PORT;
  }

  @Override
  protected String getRuleID() {
    return "org.polarsys.capella.core.data.fa.validation.FC_4"; //$NON-NLS-1$
  }

  @Override
  protected List<OracleDefinition> getOracleDefinitions() {
    return Arrays.asList(new OracleDefinition[] { 
        // Elements of interest for OK and KO patterns,
        new OracleDefinition(/*CP_1_OUT*/"c946d772-c2e3-427d-8c79-8cbb099a7d0a", 0), //$NON-NLS-1$
        new OracleDefinition(/*CP_2_OUT*/"169e1ff9-29ff-497e-887b-fab3cca0d4ac", 1), //$NON-NLS-1$
        new OracleDefinition(/*CP_3_OUT*/"e91bdb07-39bb-47b2-8691-9c6fa6a1d063", 0), //$NON-NLS-1$
        new OracleDefinition(/*CP_1_IN*/ "2a0c07ff-e174-492b-8591-befcebb08abb", 0), //$NON-NLS-1$
        new OracleDefinition(/*CP_2_IN*/ "379763cf-b568-4964-9906-cb9ca1f264dd", 1), //$NON-NLS-1$
        new OracleDefinition(/*CP_3_IN*/ "87086631-fa4d-44a5-b0d0-d3e6a459d28c", 0), //$NON-NLS-1$
    });
  }

}
