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
package org.polarsys.capella.test.model.ju.testcases.delete;

public class DeleteChainInvolvmentFunction extends DeleteTest {

  @Override
  protected void setUp() throws Exception {
    super.setUp();
    mustExist(SA__ROOT_SF__FUNCTIONAL_CHAIN_INVOLVEMENT_LINK_TO_FE3_1);
    mustExist(SA__ROOT_SF__FUNCTIONAL_CHAIN_INVOLVEMENT_LINK_TO_FE3_2);
    mustExist(SA__ROOT_SF__SEQUENCE_LINK);
  }
  
  @Override
  public void test() {
    removeElement(SA__ROOT_SF__FUNCTIONAL_CHAIN_INVOLVEMENT_FUNCTION_TO_SF3_3);
    mustBeRemoved(SA__ROOT_SF__FUNCTIONAL_CHAIN_INVOLVEMENT_LINK_TO_FE3_1);
    mustBeRemoved(SA__ROOT_SF__FUNCTIONAL_CHAIN_INVOLVEMENT_LINK_TO_FE3_2);
    mustBeRemoved(SA__ROOT_SF__SEQUENCE_LINK);
  }

}
