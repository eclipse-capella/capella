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
package org.polarsys.capella.test.validation.rules.ju.testcases.dc_cl;

import java.util.Arrays;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.polarsys.capella.core.data.information.communication.CommunicationPackage;
import org.polarsys.capella.test.framework.api.OracleDefinition;
import org.polarsys.capella.test.validation.rules.ju.testcases.ValidationRuleTestCase;

/**
 * @author Erwan Brottier
 */
public class Rule_DC_CL_02 extends ValidationRuleTestCase {

	@Override
	protected String getRequiredTestModel() {
		return "Project_validation5"; //$NON-NLS-1$
	}

	@Override
	protected EClass getTargetedEClass() {		
		return CommunicationPackage.Literals.COMMUNICATION_LINK;
	}

	@Override
	protected String getRuleID() {
		return "org.polarsys.capella.core.data.information.validation.DC_CL_02"; //$NON-NLS-1$
	}

	@Override
	protected List<OracleDefinition> getOracleDefinitions() {
		return Arrays.asList(new OracleDefinition [] {
				new OracleDefinition("e12e0feb-7f0a-479d-844b-8d3fd94e454a", 1), //$NON-NLS-1$
				new OracleDefinition("b019eaa8-ea54-4a3d-87d4-e6c668f138fb", 1), //$NON-NLS-1$
				new OracleDefinition("9fbf72ac-8b8f-49ef-8fa4-00c9fa6c10ba", 1), //$NON-NLS-1$
			});
	}

}
