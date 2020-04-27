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
public class MaxHierarchyLevel2TestSA extends AbstractMaxHierarchyLevel2Test {

  public MaxHierarchyLevel2TestSA() {
    LEVEL3_CHAIN1_DIAG = SFCD_LEVEL3_CHAIN1_SA_DIAG;

    LEVEL1CHAIN1 = LEVEL1CHAIN1_SA;

    FUNCTION_2 = SYSTEM_FUNCTION_2_SA;
    FUNCTION_3 = SYSTEM_FUNCTION_3_SA;

    EXCHANGE_2 = FUNCTIONAL_EXCHANGE_2_SA;

    FUNCTIONAL_CHAIN_REFERENCE_TO_LEVEL2CHAIN1 = FUNCTIONAL_CHAIN_REFERENCE_TO_LEVEL2CHAIN1_SA;
    FUNCTIONAL_CHAIN_REFERENCE_TO_LEVEL2CHAIN1_BIS = FUNCTIONAL_CHAIN_REFERENCE_TO_LEVEL2CHAIN1_BIS_SA;
  }

}
