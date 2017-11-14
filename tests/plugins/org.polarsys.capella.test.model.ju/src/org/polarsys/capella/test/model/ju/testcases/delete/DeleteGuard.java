/*******************************************************************************
* Copyright (c) 2017 THALES GLOBAL SERVICES.
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

import org.polarsys.capella.core.data.capellacore.Constraint;
import org.polarsys.capella.core.libraries.model.ICapellaModel;
import org.polarsys.capella.core.libraries.utils.ScopeModelWrapper;
import org.polarsys.capella.core.platform.sirius.ui.commands.CapellaDeleteCommand;
import org.polarsys.capella.shared.id.handler.IScope;
import org.polarsys.capella.shared.id.handler.IdManager;
import org.polarsys.capella.test.framework.helpers.TestHelper;
import org.polarsys.capella.test.model.ju.model.MiscModel;

public class DeleteGuard extends MiscModel {

  /**
   * {@inheritDoc}
   */
  @Override
  public void test() {
    ICapellaModel model = getTestModel();
    IScope scope = new ScopeModelWrapper(model);

    Constraint constraint = (Constraint) IdManager.getInstance()
        .getEObject(SA__SYSTEM__STATEMACHINE_1__DEFAULT_REGION__STATE_TRANSITION__CONSTRAINT, scope);
    CapellaDeleteCommand command = new CapellaDeleteCommand(TestHelper.getExecutionManager(constraint),
        Collections.singletonList(constraint), true, false, false);

    if (command.canExecute()) {
      command.execute();
    } else {
      assertTrue("Cannot delete the constraint", false);
    }

    assertTrue("The containing State Transition must not be deleted", IdManager.getInstance()
        .getEObject(SA__SYSTEM__STATEMACHINE_1__DEFAULT_REGION__STATE_TRANSITION, scope) != null);
  }

}
