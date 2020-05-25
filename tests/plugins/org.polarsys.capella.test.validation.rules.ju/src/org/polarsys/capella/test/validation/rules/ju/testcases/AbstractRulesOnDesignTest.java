/*******************************************************************************
 * Copyright (c) 2006, 2015 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.validation.rules.ju.testcases;


/**
 * This class defines a TestCase based on 'RulesOnDesignTest' model<br>
 * When this model is updated, all the subclasses are potentially impacted.
 */
public abstract class AbstractRulesOnDesignTest extends ValidationRulePartialTestCase {

	@Override
	protected String getRequiredTestModel() {
		return "RulesOnDesignTest"; //$NON-NLS-1$
	}
}
