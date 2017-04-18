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

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.polarsys.capella.core.data.capellacore.Constraint;
import org.polarsys.capella.core.libraries.model.ICapellaModel;
import org.polarsys.capella.core.libraries.utils.ScopeModelWrapper;
import org.polarsys.capella.core.platform.sirius.ui.commands.CapellaDeleteCommand;
import org.polarsys.capella.shared.id.handler.IScope;
import org.polarsys.capella.shared.id.handler.IdManager;
import org.polarsys.capella.test.framework.api.BasicTestCase;
import org.polarsys.capella.test.framework.helpers.TestHelper;

public class DeleteGuard extends BasicTestCase {

  public static String MODEL_NAME = "miscmodel"; //$NON-NLS-1$

  public static String SA__SYSTEM__STATEMACHINE_1__DEFAULT_REGION__STATE_TRANSITION__CONSTRAINT = "be2b76aa-36bb-4243-861a-b968ad8133cb"; //$NON-NLS-1$
  public static String SA__SYSTEM__STATEMACHINE_1__DEFAULT_REGION__STATE_TRANSITION = "da8600e2-5df6-4ac2-b784-cb70e49c9fa7"; //$NON-NLS-1$

  /**
   * {@inheritDoc}
   */
  @Override
  public List<String> getRequiredTestModels() {
    return Arrays.asList(MODEL_NAME);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void test() {
    ICapellaModel model = getTestModel(MODEL_NAME);
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
