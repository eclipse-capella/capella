/*******************************************************************************
 * Copyright (c) 2006, 2019 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.validation.rules.ju.testcases.dwf_d;

import java.util.Arrays;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.polarsys.capella.core.data.information.InformationPackage;
import org.polarsys.capella.test.framework.api.OracleDefinition;
import org.polarsys.capella.test.validation.rules.ju.testcases.AbstractRulesOnDesignTest;

/**
 * test on DWF_D_52: This rule checks that for a property, type of the default (resp. null) value shall be Undefined
 * (implicit typing) or the property�s datatype or a super type of the property�s datatype.
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
        "373c7118-a957-4499-9485-6e89d30b1d22",   // 1  - no default/null value
        "1d057a80-1f52-4092-8db4-b8e42898f567",   // 2  - default value inconsistent with its property type (fail expected)
        "9dff8332-f6b6-4516-9168-529604764831",   // 3  - default value with same type (String)
        "b5a86f51-1c94-47c0-8d62-262d235f682d",   // 4  - null value inconsistent with its property type (fail expected)
        "ff102fef-4c22-4726-89f3-18ad63ec8e1b",   // 5  - default value with same type (Integer)
        "52090b7a-3926-457c-bf0c-2495c1e87f0a",   // 6  - default value type undefined (implicit typing)  
        "c4f72f6c-e5db-4cfa-8179-61d0901e6656",   // 7  - null value with same type but no name (Enumeration)
        "ba53cb67-dcae-415f-81ba-23a8a4886e3b",   // 8  - null value inconsistent with its property type (fail expected)
        "f8ee8b34-641f-4aad-907f-1e0f68ca34df",   // 9  - property type is a super type of default value type (BooleanTest -> Boolean)
        "e01bc138-bed6-41f2-ad19-d7c460358d09",   // 10 - not same boolean type (fail expected)
        "9b17f3c1-164b-46e2-a344-e02c0c17b7cf",   // 11 - property type is a super type of default value type (Class 3 -> Class 4)
        "a083a96e-8b8b-4b70-98bc-47d0948a052e",   // 12 - property type is a sub type of default value type (fail expected)
        "4828fe6e-edfb-4901-906f-949e7c370e0a"}); // 13 - default and null value inconsistent with their property type (2 error messages expected)
  }

  /**
   * @see org.polarsys.capella.test.validation.rules.ju.testcases.ValidationRuleTestCase#getOracleDefinitions()
   * @generated
   */
  protected List<OracleDefinition> getOracleDefinitions() {
    return Arrays.asList(new OracleDefinition[] {
        new OracleDefinition("1d057a80-1f52-4092-8db4-b8e42898f567", 1),
        new OracleDefinition("b5a86f51-1c94-47c0-8d62-262d235f682d", 1),
        new OracleDefinition("ba53cb67-dcae-415f-81ba-23a8a4886e3b", 1),
        new OracleDefinition("e01bc138-bed6-41f2-ad19-d7c460358d09", 1),
        new OracleDefinition("a083a96e-8b8b-4b70-98bc-47d0948a052e", 1),
        new OracleDefinition("4828fe6e-edfb-4901-906f-949e7c370e0a", 1)});
  }

}
