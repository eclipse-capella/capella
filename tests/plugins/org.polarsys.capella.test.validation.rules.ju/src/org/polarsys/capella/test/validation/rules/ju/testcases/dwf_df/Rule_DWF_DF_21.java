/*******************************************************************************
 * Copyright (c) 2019, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.validation.rules.ju.testcases.dwf_df;

import java.util.Arrays;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.polarsys.capella.core.data.fa.FaPackage;
import org.polarsys.capella.test.framework.api.OracleDefinition;

/**
 * test on DWF_DF_21 - Control Nodes inconsistent operations
 * 
 * @generated
 */
public class Rule_DWF_DF_21 extends Project_DWF_DF_for_SL_CN {

  /**
   * @see org.polarsys.capella.test.validation.rules.ju.testcases.ValidationRuleTestCase#getTargetedEClass()
   * @generated
   */
  @Override
  protected EClass getTargetedEClass() {
    return FaPackage.Literals.SEQUENCE_LINK;
  }

  /**
   * @see org.polarsys.capella.test.validation.rules.ju.testcases.ValidationRuleTestCase#getRuleID()
   * @generated
   */
  @Override
  protected String getRuleID() {
    return "org.polarsys.capella.core.data.fa.validation.DWF_DF_21";
  }

  /**
   * @see org.polarsys.capella.test.validation.rules.ju.testcases.ValidationRulePartialTestCase#getScopeDefinition()
   * @generated
   */
  @Override
  protected List<String> getScopeDefinition() {
    return Arrays.asList(new String[] { OA_CONTROL_NODE_1, OA_CONTROL_NODE_2, OA_CONTROL_NODE_3, OA_CONTROL_NODE_4,
        OA_CONTROL_NODE_5, OA_CONTROL_NODE_6, SA_CONTROL_NODE_1, SA_CONTROL_NODE_2, SA_CONTROL_NODE_3,
        SA_CONTROL_NODE_4, SA_CONTROL_NODE_5, SA_CONTROL_NODE_6, LA_CONTROL_NODE_1, LA_CONTROL_NODE_2,
        LA_CONTROL_NODE_3, LA_CONTROL_NODE_4, LA_CONTROL_NODE_5, LA_CONTROL_NODE_6 });
  }

  /**
   * @see org.polarsys.capella.test.validation.rules.ju.testcases.ValidationRuleTestCase#getOracleDefinitions()
   * @generated
   */
  @Override
  protected List<OracleDefinition> getOracleDefinitions() {
    return Arrays.asList(
        new OracleDefinition[] { new OracleDefinition(OA_CONTROL_NODE_3, 1), new OracleDefinition(OA_CONTROL_NODE_4, 1),
            new OracleDefinition(OA_CONTROL_NODE_5, 1), new OracleDefinition(OA_CONTROL_NODE_6, 1),
            new OracleDefinition(SA_CONTROL_NODE_3, 1), new OracleDefinition(SA_CONTROL_NODE_4, 1),
            new OracleDefinition(SA_CONTROL_NODE_5, 1), new OracleDefinition(SA_CONTROL_NODE_6, 1),
            new OracleDefinition(LA_CONTROL_NODE_3, 1), new OracleDefinition(LA_CONTROL_NODE_4, 1),
            new OracleDefinition(LA_CONTROL_NODE_5, 1), new OracleDefinition(LA_CONTROL_NODE_6, 1) });
  }
}
