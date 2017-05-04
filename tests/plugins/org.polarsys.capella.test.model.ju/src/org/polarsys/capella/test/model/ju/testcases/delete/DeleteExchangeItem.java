/*******************************************************************************
* Copyright (c) 2006, 2017 THALES GLOBAL SERVICES.
* All rights reserved. This program and the accompanying materials
* are made available under the terms of the Eclipse Public License v1.0
* which accompanies this distribution, and is available at
* http://www.eclipse.org/legal/epl-v10.html
*
* Contributors:
*    Thales - initial API and implementation
*******************************************************************************/
package org.polarsys.capella.test.model.ju.testcases.delete;

import java.util.Collections;

import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.core.data.fa.FunctionalChain;
import org.polarsys.capella.core.libraries.model.ICapellaModel;
import org.polarsys.capella.core.libraries.utils.ScopeModelWrapper;
import org.polarsys.capella.core.platform.sirius.ui.commands.CapellaDeleteCommand;
import org.polarsys.capella.shared.id.handler.IScope;
import org.polarsys.capella.shared.id.handler.IdManager;
import org.polarsys.capella.test.framework.helpers.TestHelper;
import org.polarsys.capella.test.model.ju.model.MiscModel;

public class DeleteExchangeItem extends MiscModel {

  /**
   * {@inheritDoc}
   */
  @Override
  public void test() {
  	ICapellaModel model = getTestModel();
    IScope scope = new ScopeModelWrapper(model);

    FunctionalChain chain = (FunctionalChain) IdManager.getInstance().getEObject(SA__ROOT_SF__FC1, scope);
    int nbInvolvements = chain.getOwnedFunctionalChainInvolvements().size();

    EObject object = IdManager.getInstance().getEObject(SA__DATA__EI1, scope);
    CapellaDeleteCommand command = new CapellaDeleteCommand(TestHelper.getExecutionManager(object), Collections.singletonList(object), true, false, false);

    if (command.canExecute()) {
      command.execute();
    } else {
      assertTrue("cannot remove an element", false);
    }

    assertTrue("an involvement has been removed when EI has been removed", chain.getOwnedFunctionalChainInvolvements().size() == nbInvolvements);
  }

}
