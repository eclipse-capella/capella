/*******************************************************************************
 * Copyright (c) 2019, THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.test.diagram.tools.ju.xfcd;

/**
 * Max Hierarchy level: 1 | Tested tools: Functional chain, Function, Exchange, Exchange and Function, Connect.
 * 
 */
public class MaxHierarchyLevel1TestOA extends AbstractMaxHierarchyLevel1Test {

  public MaxHierarchyLevel1TestOA() {

    EMPTYCHAIN1_DIAG = OPD_EMPTYCHAIN1_OA_DIAG;

    LEVEL1CHAIN1 = LEVEL1CHAIN1_OA;
    LEVEL1CHAIN2 = LEVEL1CHAIN2_OA;

    FUNCTION_1 = OPERATIONAL_ACTIVITY_1_OA;
    FUNCTION_2 = OPERATIONAL_ACTIVITY_2_OA;
    FUNCTION_3 = OPERATIONAL_ACTIVITY_3_OA;

    EXCHANGE_2 = INTERACTION_2_OA;
  }

}
