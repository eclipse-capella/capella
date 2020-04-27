/*******************************************************************************
 * Copyright (c) 2006, 2017 THALES GLOBAL SERVICES.
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

package org.polarsys.capella.core.platform.sirius.ui.navigator.viewer;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.window.IShellProvider;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.business.api.session.SessionStatus;
import org.eclipse.sirius.business.api.session.danalysis.DAnalysisSession;
import org.eclipse.sirius.ui.business.internal.session.SessionSaveable;
import org.eclipse.ui.model.IWorkbenchAdapter;
import org.eclipse.ui.progress.IJobRunnable;
import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;
import org.polarsys.capella.core.sirius.ui.helper.SessionHelper;

/**
 * The Capella saveable.
 */
public class CapellaSaveable extends SessionSaveable {

  public CapellaSaveable(Session session) {
    super(session);
  }

  /**
   * @see org.eclipse.ui.Saveable#getAdapter(java.lang.Class)
   */
  @Override
  public Object getAdapter(Class adapter) {
    if (Session.class == adapter) {
      return getSession();
    }
    return super.getAdapter(adapter);
  }

  /**
   * @see org.eclipse.ui.Saveable#isDirty()
   */
  @Override
  public boolean isDirty() {

    if (ActiveSessionManager.getInstance()
        .isEnabledContentNotifications(this.getSession().getTransactionalEditingDomain())) {

      Session currentSession = getSession();
      if (currentSession != null) {
        if (SessionManager.INSTANCE.getSessions().contains(currentSession)) {
          return SessionStatus.DIRTY.equals(getSession().getStatus());
        }
      }
    }

    return false;
  }
  
  /**
   * Due to https://bugs.eclipse.org/bugs/show_bug.cgi?id=521989
   */
  @Override
  public void doSave(IProgressMonitor monitor) {
      new CapellaSaveSessionRunnable(getSession()).run(monitor);
  }

  /**
   * Due to https://bugs.eclipse.org/bugs/show_bug.cgi?id=521989
   */
  @Override
  public IJobRunnable doSave(IProgressMonitor monitor, IShellProvider shellProvider) throws CoreException {
      return new CapellaSaveSessionRunnable(getSession());
  }
  
  @Override
  public String getName() {
    String result = ICommonConstants.EMPTY_STRING;
    // Compute the returned name on underlying diagram resource rather than the session since this later one is no longer displayed.
    IFile analysisFile = SessionHelper.getFirstAnalysisFile((DAnalysisSession) getSession());
    IWorkbenchAdapter workbenchAdapter = (IWorkbenchAdapter) analysisFile.getAdapter(IWorkbenchAdapter.class);
    if (null != workbenchAdapter) {
      result = workbenchAdapter.getLabel(analysisFile);
    } else {
      // Last chance to get something.
      result = analysisFile.getFullPath().toString();
    }
    return result;
  }

}
