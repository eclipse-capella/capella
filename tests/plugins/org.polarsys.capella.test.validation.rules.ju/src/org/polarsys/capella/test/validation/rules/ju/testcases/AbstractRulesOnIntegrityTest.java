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
package org.polarsys.capella.test.validation.rules.ju.testcases;


/**
 * This class defines a TestCase based on 'RulesOnIntegrityTest' model<br>
 * When this model is updated, all the subclasses are potentially impacted.
 */
public abstract class AbstractRulesOnIntegrityTest extends ValidationRulePartialTestCase {

	@Override
  protected String getRequiredTestModel() {
		return "RulesOnIntegrityTest"; //$NON-NLS-1$
	}
}
