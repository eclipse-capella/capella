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
package org.polarsys.capella.test.validation.rules.ju.testcases.dwf_sm;

import java.util.Arrays;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.polarsys.capella.core.data.capellacommon.CapellacommonPackage;
import org.polarsys.capella.test.framework.api.OracleDefinition;
import org.polarsys.capella.test.validation.rules.ju.testcases.ValidationRuleTestCase;

/**
 * @author Aurelien Didier
 */
public class Rule_DWF_SM_09bis extends ValidationRuleTestCase {

  @Override
  protected String getRequiredTestModel() {
    return "Project_validation_12"; //$NON-NLS-1$
  }

  @Override
  protected EClass getTargetedEClass() {
    return CapellacommonPackage.Literals.STATE_TRANSITION;
  }

  @Override
  protected String getRuleID() {
    return "org.polarsys.capella.core.data.common.validation.DWF_SM_09"; //$NON-NLS-1$
  }

  @Override
  protected List<OracleDefinition> getOracleDefinitions() {
    return Arrays.asList(new OracleDefinition[] {
        new OracleDefinition("983223eb-645a-43bc-a007-60e06bca3142", 1),
        new OracleDefinition("de0fc276-52c2-4b58-b56b-9f3c0c0c50d1", 1) }); // Mode 1
  }
}
