/*******************************************************************************
 * Copyright (c) 2006, 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.test.validation.rules.ju.testcases.dcom;

import java.util.Arrays;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.test.framework.api.OracleDefinition;
import org.polarsys.capella.test.validation.rules.ju.testcases.AbstractRulesOnDesignTest;

/**
 *
 */
public class Rule_DCOM_06 extends AbstractRulesOnDesignTest {

  /**
   * @see org.polarsys.capella.test.validation.rules.ju.testcases.ValidationRuleTestCase#getTargetedEClass()
   */
	protected EClass getTargetedEClass() {
		return CsPackage.Literals.INTERFACE;
	}

  /**
   * @see org.polarsys.capella.test.validation.rules.ju.testcases.ValidationRuleTestCase#getRuleID()
   */
	protected String getRuleID() {
		return "org.polarsys.capella.core.data.cs.validation.DCOM_06"; //$NON-NLS-1$
	}

  /**
   * @see org.polarsys.capella.test.validation.rules.ju.testcases.ValidationRulePartialTestCase#getScopeDefinition()
   */
  protected List<String> getScopeDefinition() {
    return Arrays.asList(new String[] {
        "d234ae5a-4173-4ebd-9906-4ec29af8a502", //$NON-NLS-1$
        "47c126f9-9398-468f-8755-6811b152cbc1" //$NON-NLS-1$
    });
  }

  /**
   * @see org.polarsys.capella.test.validation.rules.ju.testcases.ValidationRuleTestCase#getOracleDefinitions()
   */
	protected List<OracleDefinition> getOracleDefinitions() {
		return Arrays.asList(new OracleDefinition[] {
		    new OracleDefinition("47c126f9-9398-468f-8755-6811b152cbc1", 1) //$NON-NLS-1$
			});
	}
}
