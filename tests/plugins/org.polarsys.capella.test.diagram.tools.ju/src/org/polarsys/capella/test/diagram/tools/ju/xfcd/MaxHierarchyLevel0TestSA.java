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
 * Max Hierarchy level: 0 | Tested tools: Function, Exchange, Exchange and Function, Connect.
 * 
 */
public class MaxHierarchyLevel0TestSA extends AbstractMaxHierarchyLevel0Test {

  public MaxHierarchyLevel0TestSA() {
    EMPTYCHAIN1_DIAG = SFCD_EMPTYCHAIN1_SA_DIAG;

    FUNCTION_2 = SYSTEM_FUNCTION_2_SA;
    FUNCTION_3 = SYSTEM_FUNCTION_3_SA;

    EXCHANGE_1 = FUNCTIONAL_EXCHANGE_1_SA;
    EXCHANGE_2 = FUNCTIONAL_EXCHANGE_2_SA;
    EXCHANGE_3 = FUNCTIONAL_EXCHANGE_3_SA;
    EXCHANGE_5 = FUNCTIONAL_EXCHANGE_5_SA;
  }

}
