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
public class Rule_TC_I_11 extends ValidationRuleTestCase {

	@Override
	protected String getRequiredTestModel() {
		return "Project_validation7"; //$NON-NLS-1$
	}

	@Override
	protected EClass getTargetedEClass() {
		return InformationPackage.Literals.EXCHANGE_ITEM;
	}

	@Override
	protected String getRuleID() {
		return "org.polarsys.capella.core.data.information.validation.TC_I_11"; //$NON-NLS-1$
	}

	@Override
	protected List<OracleDefinition> getOracleDefinitions() {
		return Arrays.asList(new OracleDefinition [] {
				new OracleDefinition("8711bd73-5106-4258-94f4-6e58a3b12ab0", 1), //$NON-NLS-1$
				new OracleDefinition("6004f2cf-20b0-4b27-bfc2-3f2f5d22e72b", 1), //$NON-NLS-1$
			});
	}
}
