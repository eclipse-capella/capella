/*******************************************************************************
 * Copyright (c) 2019, THALES GLOBAL SERVICES.
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
 * Max Hierarchy level: 2 | Tested tools: Functional chain, Function, Exchange, Connect.
 * 
 */
public class MaxHierarchyLevel2TestOA extends AbstractMaxHierarchyLevel2Test {

  public MaxHierarchyLevel2TestOA() {
    LEVEL3_CHAIN1_DIAG = OPD_LEVEL3CHAIN1_OA_DIAG;

    LEVEL1CHAIN1 = LEVEL1CHAIN1_OA;

    FUNCTION_2 = OPERATIONAL_ACTIVITY_2_OA;
    FUNCTION_3 = OPERATIONAL_ACTIVITY_3_OA;

    EXCHANGE_2 = INTERACTION_2_OA;

    FUNCTIONAL_CHAIN_REFERENCE_TO_LEVEL2CHAIN1 = FUNCTIONAL_CHAIN_REFERENCE_TO_LEVEL2CHAIN1_OA;
    FUNCTIONAL_CHAIN_REFERENCE_TO_LEVEL2CHAIN1_BIS = FUNCTIONAL_CHAIN_REFERENCE_TO_LEVEL2CHAIN1_BIS_OA;
  }

}
