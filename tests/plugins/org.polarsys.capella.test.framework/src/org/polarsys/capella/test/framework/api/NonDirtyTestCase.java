/*******************************************************************************
 * Copyright (c) 2019 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.test.framework.api;

import org.eclipse.core.commands.operations.IUndoContext;
import org.eclipse.emf.common.command.CommandStack;
import org.eclipse.sirius.business.api.session.Session;
import org.polarsys.capella.common.ef.internal.command.WorkspaceCommandStackImpl;

/**
 * A test case that discard all changes to the test model at the end of test case.
 */
public abstract class NonDirtyTestCase extends BasicTestCase {
  @Override
  protected Session getSession(String relativeModelPath) {
    Session session = super.getSession(relativeModelPath);
    setUndoContextLimit(session);
    return session;
  }
  
  protected void setUndoContextLimit(Session session) {
    WorkspaceCommandStackImpl commandStack = (WorkspaceCommandStackImpl) session.getTransactionalEditingDomain()
        .getCommandStack();
    IUndoContext undoContext = commandStack.getDefaultUndoContext();
    commandStack.getOperationHistory().setLimit(undoContext, 10000);
  }
  
  @Override
  protected void tearDown() throws Exception {
    undoAllChanges();
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
