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
 * FunctionalExchanges on same input port must have same EI
 */
public class Rule_FC_7_TestCase extends ValidationRuleTestCase {

  @Override
  protected String getRequiredTestModel() {
    return "FC_7"; //$NON-NLS-1$
  }

  @Override
  protected EClass getTargetedEClass() {
    return FaPackage.Literals.FUNCTIONAL_EXCHANGE;
  }

  @Override
  protected String getRuleID() {
    return "org.polarsys.capella.core.data.fa.validation.FC_7"; //$NON-NLS-1$
  }

  @Override
  protected List<OracleDefinition> getOracleDefinitions() {
    return Arrays.asList(new OracleDefinition[] { 
        // Elements of interest for OK and KO patterns,
        new OracleDefinition("a5334ed3-61c5-4672-88d4-3cbb22efdd14", 0), //$NON-NLS-1$
        new OracleDefinition("a421f829-48a5-4c99-9f92-f2943404146c", 0), //$NON-NLS-1$
        new OracleDefinition("77607e2f-b32f-420e-9bc7-0ce258959888", 1), //$NON-NLS-1$
        new OracleDefinition("630d2b62-bd33-452b-8c06-12e0998ec128", 1), //$NON-NLS-1$
        new OracleDefinition("e5c1228b-dc53-4ad6-800c-faad17e92911", 0), //$NON-NLS-1$
        new OracleDefinition("734a2541-e388-44a6-b64a-7890abb6bfce", 0), //$NON-NLS-1$
    });
  }

}
