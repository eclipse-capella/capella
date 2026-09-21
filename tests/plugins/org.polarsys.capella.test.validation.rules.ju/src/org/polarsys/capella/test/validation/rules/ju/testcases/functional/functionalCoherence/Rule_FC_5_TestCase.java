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
 * FunctionalExchange allocated to a CE should have one or more Exchanged Items
 */
public class Rule_FC_5_TestCase extends ValidationRuleTestCase {

  @Override
  protected String getRequiredTestModel() {
    return "FC_5"; //$NON-NLS-1$
  }

  @Override
  protected EClass getTargetedEClass() {
    return FaPackage.Literals.FUNCTIONAL_EXCHANGE;
  }

  @Override
  protected String getRuleID() {
    return "org.polarsys.capella.core.data.fa.validation.FC_5"; //$NON-NLS-1$
  }

  @Override
  protected List<OracleDefinition> getOracleDefinitions() {
    return Arrays.asList(new OracleDefinition[] { 
        // Elements of interest for OK and KO patterns,
        new OracleDefinition("31b4a642-5259-4501-8a44-9f756130706a", 0), //$NON-NLS-1$
        new OracleDefinition("f31b4c4a-862f-4dfb-972e-08910e33c86a", 1), //$NON-NLS-1$
        new OracleDefinition("4b4d7a86-ad91-4983-b05c-10dbda34789a", 0), //$NON-NLS-1$
    });
  }

}
