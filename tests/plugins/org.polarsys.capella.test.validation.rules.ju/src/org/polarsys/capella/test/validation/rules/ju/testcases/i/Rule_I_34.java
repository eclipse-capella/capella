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
package org.polarsys.capella.test.validation.rules.ju.testcases.i;

import java.util.Arrays;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.polarsys.capella.core.data.capellamodeller.CapellamodellerPackage;
import org.polarsys.capella.test.framework.api.OracleDefinition;
import org.polarsys.capella.test.validation.rules.ju.testcases.ValidationRuleTestCase;

public class Rule_I_34 extends ValidationRuleTestCase {

  @Override
  protected String getRequiredTestModel() {
    return "Project_validation12";
  }

  @Override
  public List<String> getRequiredTestModels() {
    return Arrays.asList("Library_validation12", "Project_validation12");
  }

  @Override
  protected EClass getTargetedEClass() {
    return CapellamodellerPackage.Literals.SYSTEM_ENGINEERING;
  }

  @Override
  protected String getRuleID() {
    return "org.polarsys.capella.core.data.core.validation.I_34"; //$NON-NLS-1$
  }

  @Override
  protected List<OracleDefinition> getOracleDefinitions() {
    return Arrays.asList(new OracleDefinition[] { new OracleDefinition("7e398665-01f6-47a6-a38d-4f971f4a60ed", 1) //$NON-NLS-1$
        });
  }
}
