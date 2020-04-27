/*******************************************************************************
 * Copyright (c) 2019 THALES GLOBAL SERVICES.
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
 * test on DWF_DF_20 - SequenceLink between the referenced Involvement Functions/OperationalActivities is in opposite
 * direction to Involvement Links referencing them referencing them
 * 
 * @generated
 */
public class Rule_DWF_DF_20 extends Project_DWF_DF_for_SL_CN {

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
    return "org.polarsys.capella.core.data.fa.validation.DWF_DF_20";
  }

  /**
   * @see org.polarsys.capella.test.validation.rules.ju.testcases.ValidationRulePartialTestCase#getScopeDefinition()
   * @generated
   */
  @Override
  protected List<String> getScopeDefinition() {
    return Arrays.asList(new String[] { OA_SEQUENCE_LINK_1, OA_SEQUENCE_LINK_2, OA_SEQUENCE_LINK_3, OA_SEQUENCE_LINK_4,
        OA_SEQUENCE_LINK_5, OA_SEQUENCE_LINK_6, OA_SEQUENCE_LINK_7, OA_SEQUENCE_LINK_8, OA_SEQUENCE_LINK_9,
        SA_SEQUENCE_LINK_1, SA_SEQUENCE_LINK_2, SA_SEQUENCE_LINK_3, SA_SEQUENCE_LINK_4, SA_SEQUENCE_LINK_5,
        SA_SEQUENCE_LINK_6, SA_SEQUENCE_LINK_7, SA_SEQUENCE_LINK_8, SA_SEQUENCE_LINK_9, LA_SEQUENCE_LINK_1,
        LA_SEQUENCE_LINK_2, LA_SEQUENCE_LINK_3, LA_SEQUENCE_LINK_4, LA_SEQUENCE_LINK_5, LA_SEQUENCE_LINK_6,
        LA_SEQUENCE_LINK_7, LA_SEQUENCE_LINK_8, LA_SEQUENCE_LINK_9

    });
  }

  /**
   * @see org.polarsys.capella.test.validation.rules.ju.testcases.ValidationRuleTestCase#getOracleDefinitions()
   * @generated
   */
  @Override
  protected List<OracleDefinition> getOracleDefinitions() {
    return Arrays.asList(new OracleDefinition[] { new OracleDefinition(OA_SEQUENCE_LINK_6, 1),
        new OracleDefinition(OA_SEQUENCE_LINK_9, 1), new OracleDefinition(SA_SEQUENCE_LINK_6, 1),
        new OracleDefinition(SA_SEQUENCE_LINK_9, 1), new OracleDefinition(LA_SEQUENCE_LINK_6, 1),
        new OracleDefinition(LA_SEQUENCE_LINK_9, 1) });
  }
}
