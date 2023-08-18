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
package org.polarsys.capella.test.validation.rules.ju.testcases.dwf_i;

import java.util.Arrays;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.test.framework.api.OracleDefinition;
import org.polarsys.capella.test.validation.rules.ju.testcases.ValidationRuleTestCase;

/**
 * @author Erwan Brottier
 */
public class Rule_DWF_I_20 extends ValidationRuleTestCase {

	@Override
	protected String getRequiredTestModel() {
		return "Project_validation11"; //$NON-NLS-1$
	}

	@Override
	protected EClass getTargetedEClass() {
		return CsPackage.Literals.INTERFACE;
	}

	@Override
	protected String getRuleID() {
		return "org.polarsys.capella.core.data.cs.validation.DWF_I_20"; //$NON-NLS-1$
	}

	@Override
	protected List<OracleDefinition> getOracleDefinitions() {
		return Arrays.asList(new OracleDefinition [] {
				new OracleDefinition("6d1a5741-bf26-4bd5-9ab1-0e0c0905abff", 1), //$NON-NLS-1$
			});
	}
}
