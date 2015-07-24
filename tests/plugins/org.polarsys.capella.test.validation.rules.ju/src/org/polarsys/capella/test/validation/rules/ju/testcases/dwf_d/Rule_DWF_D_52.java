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
package org.polarsys.capella.test.validation.rules.ju.testcases.dwf_d;

import java.util.Arrays;
import java.util.List;
import org.eclipse.emf.ecore.EClass;
import org.polarsys.capella.core.data.information.InformationPackage;
import org.polarsys.capella.test.framework.api.OracleDefinition;
import org.polarsys.capella.test.validation.rules.ju.testcases.AbstractRulesOnDesignTest;

/**
 * test on DWF_D_52: This rule checks that for a property, type of the default (resp. null) value shall be Undefined
 * (implicit typing) or the property’s datatype or a super type of the property’s datatype.
 * 
 * @generated
 */
public class Rule_DWF_D_52 extends AbstractRulesOnDesignTest {

  /**
   * @see org.polarsys.capella.test.validation.rules.ju.testcases.ValidationRuleTestCase#getTargetedEClass()
   * @generated
   */
  protected EClass getTargetedEClass() {
    return InformationPackage.Literals.CLASS;
  }

  /**
   * @see org.polarsys.capella.test.validation.rules.ju.testcases.ValidationRuleTestCase#getRuleID()
   * @generated
   */
  protected String getRuleID() {
    return "org.polarsys.capella.core.data.information.validation.DWF_D_52";
  }

  /**
   * @see org.polarsys.capella.test.validation.rules.ju.testcases.ValidationRulePartialTestCase#getScopeDefinition()
   * @generated
   */
  protected List<String> getScopeDefinition() {
    return Arrays.asList(new String[] {
        "373c7118-a957-4499-9485-6e89d30b1d22",
        "1d057a80-1f52-4092-8db4-b8e42898f567",
        "9dff8332-f6b6-4516-9168-529604764831",
        "b5a86f51-1c94-47c0-8d62-262d235f682d",
        "ff102fef-4c22-4726-89f3-18ad63ec8e1b",
        "52090b7a-3926-457c-bf0c-2495c1e87f0a",
        "c4f72f6c-e5db-4cfa-8179-61d0901e6656",
        "ba53cb67-dcae-415f-81ba-23a8a4886e3b",
        "f8ee8b34-641f-4aad-907f-1e0f68ca34df",
        "e01bc138-bed6-41f2-ad19-d7c460358d09"});
  }

  /**
   * @see org.polarsys.capella.test.validation.rules.ju.testcases.ValidationRuleTestCase#getOracleDefinitions()
   * @generated
   */
  protected List<OracleDefinition> getOracleDefinitions() {
    return Arrays.asList(new OracleDefinition[] {
        new OracleDefinition("1d057a80-1f52-4092-8db4-b8e42898f567", 1),
        new OracleDefinition("b5a86f51-1c94-47c0-8d62-262d235f682d", 1),
        new OracleDefinition("52090b7a-3926-457c-bf0c-2495c1e87f0a", 1),
        new OracleDefinition("ba53cb67-dcae-415f-81ba-23a8a4886e3b", 1),
        new OracleDefinition("e01bc138-bed6-41f2-ad19-d7c460358d09", 1)});
  }

}
