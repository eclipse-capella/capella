/*******************************************************************************
* Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
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

import org.polarsys.capella.core.data.fa.FunctionalChain;
import org.polarsys.capella.shared.id.handler.IdManager;

public class DeleteExchangeItem extends DeleteTest {

  @Override
  public void test() {
    FunctionalChain chain = (FunctionalChain) IdManager.getInstance().getEObject(SA__ROOT_SF__FC1, scope);
    int nbInvolvements = chain.getOwnedFunctionalChainInvolvements().size();
    removeElement(SA__DATA__EI1);
    assertTrue("an involvement has been removed when EI has been removed", chain.getOwnedFunctionalChainInvolvements().size() == nbInvolvements);
  }

}
