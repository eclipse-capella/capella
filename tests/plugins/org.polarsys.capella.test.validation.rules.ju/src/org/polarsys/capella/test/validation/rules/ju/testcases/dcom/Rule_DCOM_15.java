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
import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;
import org.polarsys.capella.test.framework.api.OracleDefinition;
import org.polarsys.capella.test.validation.rules.ju.testcases.AbstractRulesOnDesignTest;

/**
 *
 */
public class Rule_DCOM_15 extends AbstractRulesOnDesignTest {

  /**
   * @see org.polarsys.capella.test.validation.rules.ju.testcases.ValidationRuleTestCase#getTargetedEClass()
   */
	protected EClass getTargetedEClass() {
		return ModellingcorePackage.Literals.MODEL_ELEMENT;
	}

  /**
   * @see org.polarsys.capella.test.validation.rules.ju.testcases.ValidationRuleTestCase#getRuleID()
   */
	protected String getRuleID() {
		return "org.polarsys.capella.core.data.cs.validation.DCOM_15"; //$NON-NLS-1$
	}

  /**
   * @see org.polarsys.capella.test.validation.rules.ju.testcases.ValidationRulePartialTestCase#getScopeDefinition()
   */
  protected List<String> getScopeDefinition() {
    return Arrays.asList(new String[] {
        "609b4e09-d811-49a4-8aa2-8bd2be477c30", //$NON-NLS-1$
        "83183934-dac3-46c8-a6bd-41027e2ac39c" //$NON-NLS-1$
    });
  }

  /**
   * @see org.polarsys.capella.test.validation.rules.ju.testcases.ValidationRuleTestCase#getOracleDefinitions()
   */
	protected List<OracleDefinition> getOracleDefinitions() {
		return Arrays.asList(new OracleDefinition[] {
		    new OracleDefinition("83183934-dac3-46c8-a6bd-41027e2ac39c", 1) //$NON-NLS-1$
			});
	}
}
