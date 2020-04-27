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
package org.polarsys.capella.test.framework.api;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.core.commands.operations.IUndoContext;
import org.eclipse.emf.common.command.CommandStack;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.sirius.business.api.session.Session;
import org.polarsys.capella.common.ef.internal.command.WorkspaceCommandStackImpl;
import org.polarsys.capella.test.framework.helpers.GuiActions;

/**
 * A test case that discard all changes to the test model at the end of test case.
 */
public abstract class NonDirtyTestCase extends BasicTestCase {

  private Set<String> modelsWithModifiedUndoContexts = new HashSet<>();

  @Override
  protected Session getSession(String relativeModelPath) {
    Session session = super.getSession(relativeModelPath);
    if (session != null && !modelsWithModifiedUndoContexts.contains(relativeModelPath)) {
      setUndoContextLimit(session);
      modelsWithModifiedUndoContexts.add(relativeModelPath);
    }

    return session;
  }

  protected void setUndoContextLimit(Session session) {
    TransactionalEditingDomain transactionalEditingDomain = session.getTransactionalEditingDomain();
    if (transactionalEditingDomain != null) {
      WorkspaceCommandStackImpl commandStack = (WorkspaceCommandStackImpl) transactionalEditingDomain.getCommandStack();
      if (commandStack != null) {
        IUndoContext undoContext = commandStack.getDefaultUndoContext();
        commandStack.getOperationHistory().setLimit(undoContext, 10000);
      }
    }
  }

  @Override
  protected void tearDown() throws Exception {
    GuiActions.flushASyncGuiJobs();
    if (ModelProviderHelper.getInstance().getModelProvider().undoTestCaseChanges()) {
      undoAllChanges();
    }
    super.tearDown();
  }

  protected void undoAllChanges() {
    for (String testModel : getRequiredTestModels()) {
      Session session = getSession(testModel);
      CommandStack commandStack = session.getTransactionalEditingDomain().getCommandStack();
      while (commandStack.canUndo()) {
        commandStack.undo();
      }
    }
  }
}
