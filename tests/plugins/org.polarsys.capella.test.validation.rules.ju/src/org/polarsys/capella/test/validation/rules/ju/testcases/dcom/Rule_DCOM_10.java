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
import org.polarsys.capella.core.data.ctx.CtxPackage;
import org.polarsys.capella.test.framework.api.OracleDefinition;
import org.polarsys.capella.test.validation.rules.ju.testcases.AbstractRulesOnDesignTest;

/**
 *
 */
public class Rule_DCOM_10 extends AbstractRulesOnDesignTest {

  /**
   * @see org.polarsys.capella.test.validation.rules.ju.testcases.ValidationRuleTestCase#getTargetedEClass()
   */
	protected EClass getTargetedEClass() {
		return CtxPackage.Literals.SYSTEM;
	}

  /**
   * @see org.polarsys.capella.test.validation.rules.ju.testcases.ValidationRuleTestCase#getRuleID()
   */
	protected String getRuleID() {
		return "org.polarsys.capella.core.data.ctx.validation.DCOM_10"; //$NON-NLS-1$
	}

  /**
   * @see org.polarsys.capella.test.validation.rules.ju.testcases.ValidationRulePartialTestCase#getScopeDefinition()
   */
  protected List<String> getScopeDefinition() {
    return Arrays.asList(new String[] {
        "09e8f82e-ba03-4388-9afa-1830b257a609", //$NON-NLS-1$
        "b738c3d9-bf09-449a-aa69-43356c6d10ff" //$NON-NLS-1$
    });
  }

  /**
   * @see org.polarsys.capella.test.validation.rules.ju.testcases.ValidationRuleTestCase#getOracleDefinitions()
   */
	protected List<OracleDefinition> getOracleDefinitions() {
		return Arrays.asList(new OracleDefinition[] {
		    new OracleDefinition("b738c3d9-bf09-449a-aa69-43356c6d10ff", 1) //$NON-NLS-1$
			});
	}
}
