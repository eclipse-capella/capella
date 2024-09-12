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
 * FunctionalExchanges on same output port must have same name
 */
public class Rule_FC_9_TestCase extends ValidationRuleTestCase {

  @Override
  protected String getRequiredTestModel() {
    return "FC_9"; //$NON-NLS-1$
  }

  @Override
  protected EClass getTargetedEClass() {
    return FaPackage.Literals.FUNCTIONAL_EXCHANGE;
  }

  @Override
  protected String getRuleID() {
    return "org.polarsys.capella.core.data.fa.validation.FC_9"; //$NON-NLS-1$
  }

  @Override
  protected List<OracleDefinition> getOracleDefinitions() {
    return Arrays.asList(new OracleDefinition[] { 
        // Elements of interest for OK and KO patterns,
        new OracleDefinition("b5db1c2b-38b2-40e5-a0b7-249176168119", 0), //$NON-NLS-1$
        new OracleDefinition("dfbc5cf6-c477-4474-9984-9cb5c964f3f8", 0), //$NON-NLS-1$
        new OracleDefinition("d7797d56-666d-491c-bb54-b9b01c715ec4", 0), //$NON-NLS-1$
        new OracleDefinition("b9ddd1fd-58ec-402b-9bc0-122d7e36ca97", 0), //$NON-NLS-1$
        new OracleDefinition("17135c74-1c36-4a45-8559-864d4a04a28a", 1), //$NON-NLS-1$
        new OracleDefinition("c32cfb64-73a8-4f0b-99a6-972591b976c9", 1), //$NON-NLS-1$
    });
  }

}
