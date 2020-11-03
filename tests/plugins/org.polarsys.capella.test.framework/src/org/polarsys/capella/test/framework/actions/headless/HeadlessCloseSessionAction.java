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
package org.polarsys.capella.test.framework.actions.headless;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.viewpoint.SiriusPlugin;
import org.polarsys.capella.core.sirius.ui.actions.CloseSessionAction;

public class HeadlessCloseSessionAction extends CloseSessionAction {

  protected List<Session> sessionsToClose = new ArrayList<Session>();

  public HeadlessCloseSessionAction(List<Session> inputSessionsToClose, boolean saveSession) {
    super();
    sessionsToClose.addAll(inputSessionsToClose);
    showDialog(false);
    shouldSaveIfNoDialog(saveSession);
  }

  @Override
  public void run() {
    // Launch the close sessions operation on all selected sessions.
    IRunnableWithProgress closeSessionOperation = new CloseSessionOperation(sessionsToClose);

    try {
      closeSessionOperation.run(new NullProgressMonitor());
    } catch (final InvocationTargetException ite) {
      SiriusPlugin.getDefault().error("the program was not able close the session", ite); //$NON-NLS-1$
    } catch (final InterruptedException ie) {
      SiriusPlugin.getDefault().warning("the close session action was interrupted", ie); //$NON-NLS-1$
    }
  }
}
