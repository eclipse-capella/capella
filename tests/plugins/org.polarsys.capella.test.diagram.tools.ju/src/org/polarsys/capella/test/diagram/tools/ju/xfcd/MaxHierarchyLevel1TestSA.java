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
 * Max Hierarchy level: 1 | Tested tools: Functional chain, Function, Exchange, Exchange and Function, Connect.
 * 
 */
public class MaxHierarchyLevel1TestSA extends AbstractMaxHierarchyLevel1Test {

  public MaxHierarchyLevel1TestSA() {

    EMPTYCHAIN1_DIAG = SFCD_EMPTYCHAIN1_SA_DIAG;

    LEVEL1CHAIN1 = LEVEL1CHAIN1_SA;
    LEVEL1CHAIN2 = LEVEL1CHAIN2_SA;

    FUNCTION_1 = SYSTEM_FUNCTION_1_SA;
    FUNCTION_2 = SYSTEM_FUNCTION_2_SA;
    FUNCTION_3 = SYSTEM_FUNCTION_3_SA;

    EXCHANGE_2 = FUNCTIONAL_EXCHANGE_2_SA;
  }

}
