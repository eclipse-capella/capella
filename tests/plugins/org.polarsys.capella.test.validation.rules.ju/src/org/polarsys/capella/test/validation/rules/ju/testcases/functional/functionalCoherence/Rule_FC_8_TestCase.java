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
 * FunctionalExchanges on same output port must have same name
 */
public class Rule_FC_8_TestCase extends ValidationRuleTestCase {

  @Override
  protected String getRequiredTestModel() {
    return "FC_8"; //$NON-NLS-1$
  }

  @Override
  protected EClass getTargetedEClass() {
    return FaPackage.Literals.FUNCTIONAL_EXCHANGE;
  }

  @Override
  protected String getRuleID() {
    return "org.polarsys.capella.core.data.fa.validation.FC_8"; //$NON-NLS-1$
  }

  @Override
  protected List<OracleDefinition> getOracleDefinitions() {
    return Arrays.asList(new OracleDefinition[] { 
        // Elements of interest for OK and KO patterns,
        new OracleDefinition("f61445f9-0607-45ee-94c0-f88e79eb4c3f", 0), //$NON-NLS-1$
        new OracleDefinition("6a74b49c-2e66-4fa2-9bea-d61891a88628", 0), //$NON-NLS-1$
        new OracleDefinition("14970c14-45d9-491a-a720-41722ae84b21", 1), //$NON-NLS-1$
        new OracleDefinition("4b39255f-9563-4da1-ab56-5eb6db473183", 1), //$NON-NLS-1$
    });
  }

}
