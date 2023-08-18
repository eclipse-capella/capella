/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.validation.rules.ju.testcases.tc_i;

import java.util.Arrays;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.polarsys.capella.core.data.information.InformationPackage;
import org.polarsys.capella.test.framework.api.OracleDefinition;
import org.polarsys.capella.test.validation.rules.ju.testcases.ValidationRuleTestCase;

/**
 * @author Erwan Brottier
 */
public class Rule_TC_I_13 extends ValidationRuleTestCase {

	@Override
	protected String getRequiredTestModel() {
		return "Project_validation9"; //$NON-NLS-1$
	}

	@Override
	protected EClass getTargetedEClass() {
		return InformationPackage.Literals.EXCHANGE_ITEM;
	}

	@Override
	protected String getRuleID() {
		return "org.polarsys.capella.core.data.information.validation.TC_I_13"; //$NON-NLS-1$
	}

	@Override
	protected List<OracleDefinition> getOracleDefinitions() {
		return Arrays.asList(new OracleDefinition [] {
				new OracleDefinition("22ff7c22-bf13-4a42-9fa4-a2400772d6d5", 1), //$NON-NLS-1$
				new OracleDefinition("e262b828-32c4-4804-871a-65ad617089f6", 1), //$NON-NLS-1$
			});
	}
}
