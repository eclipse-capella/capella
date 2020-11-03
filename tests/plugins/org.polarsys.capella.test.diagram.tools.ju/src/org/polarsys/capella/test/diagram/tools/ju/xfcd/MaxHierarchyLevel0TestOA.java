/*******************************************************************************
 * Copyright (c) 2019, 2020, THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.diagram.tools.ju.xfcd;

/**
 * Max Hierarchy level: 0 | Tested tools: Function, Exchange, Exchange and Function, Connect.
 * 
 */
public class MaxHierarchyLevel0TestOA extends AbstractMaxHierarchyLevel0Test {

  public MaxHierarchyLevel0TestOA() {
    EMPTYCHAIN1_DIAG = OPD_EMPTYCHAIN1_OA_DIAG;

    FUNCTION_2 = OPERATIONAL_ACTIVITY_2_OA;
    FUNCTION_3 = OPERATIONAL_ACTIVITY_3_OA;

    EXCHANGE_1 = INTERACTION_1_OA;
    EXCHANGE_2 = INTERACTION_2_OA;
    EXCHANGE_3 = INTERACTION_3_OA;
    EXCHANGE_5 = INTERACTION_5_OA;
  }

}
