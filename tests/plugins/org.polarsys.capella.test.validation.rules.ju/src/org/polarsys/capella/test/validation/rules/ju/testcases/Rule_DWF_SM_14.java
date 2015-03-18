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

import java.util.Arrays;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.polarsys.capella.core.data.capellacommon.CapellacommonPackage;
import org.polarsys.capella.test.framework.api.OracleDefinition;

/**
 * @author Aurelien Didier
 */
public class Rule_DWF_SM_14 extends ValidationRuleTestCase {

  @Override
  protected String getRequiredTestModel() {
    return "Project_validation_12"; //$NON-NLS-1$
  }

  @Override
  protected EClass getTargetedEClass() {
    return CapellacommonPackage.Literals.STATE;
  }

  @Override
  protected String getRuleID() {
    return "org.polarsys.capella.core.data.common.validation.DWF_SM_14"; //$NON-NLS-1$
  }

  @Override
  protected List<OracleDefinition> getOracleDefinitions() {
    return Arrays.asList(new OracleDefinition[] { new OracleDefinition("dfb4bf08-470c-45eb-9528-791c6c5a6c6c", 1), // Mode
        new OracleDefinition("cebfe58e-8049-4dbc-85e2-dff31605c929", 1) }); // Mode 1
  }

  public void testRule_DWF_SM_08() throws Exception {
    test();
  }
}
