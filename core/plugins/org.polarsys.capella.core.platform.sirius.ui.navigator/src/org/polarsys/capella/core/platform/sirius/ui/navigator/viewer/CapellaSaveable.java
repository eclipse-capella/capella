/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/

package org.polarsys.capella.core.platform.sirius.ui.navigator.viewer;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.business.api.session.SessionStatus;
import org.eclipse.sirius.business.api.session.danalysis.DAnalysisSession;
import org.eclipse.sirius.ui.business.internal.session.SessionSaveable;
import org.eclipse.ui.model.IWorkbenchAdapter;
import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;
import org.polarsys.capella.core.sirius.ui.SaveSessionAction;
import org.polarsys.capella.core.sirius.ui.helper.SessionHelper;

/**
 * The capella saveable.
 */
public class CapellaSaveable extends SessionSaveable {

  /**
   * Constructor.
   * @param session_p
   */
  public CapellaSaveable(Session session_p) {
    super(session_p);
  }

  /**
   * @see org.eclipse.ui.Saveable#getAdapter(java.lang.Class)
   */
  @SuppressWarnings("rawtypes")
  @Override
  public Object getAdapter(Class adapter_p) {
    if (Session.class == adapter_p) {
      return getSession();
    }
    return super.getAdapter(adapter_p);
  }

  /**
   * @see org.eclipse.ui.Saveable#doSave(org.eclipse.core.runtime.IProgressMonitor)
   */
  @Override
  public void doSave(IProgressMonitor monitor_p) {
    SaveSessionAction saveSessionAction = new SaveSessionAction();
    saveSessionAction.selectionChanged(new StructuredSelection(getSession()));
    saveSessionAction.run();
  }

  /**
   * @see org.eclipse.ui.Saveable#equals(java.lang.Object)
   */
  @Override
  public boolean equals(Object object_p) {
    boolean result = false;
    if (object_p instanceof SessionSaveable) {
      SessionSaveable saveable = (SessionSaveable) object_p;
      result = (getSession() == saveable.getSession());
    }
    return result;
  }

  /**
   * @see org.eclipse.ui.Saveable#hashCode()
   */
  @Override
  public int hashCode() {
    return getSession().hashCode();
  }

  /**
   * @see org.eclipse.ui.Saveable#getImageDescriptor()
   */
  @Override
  public ImageDescriptor getImageDescriptor() {
    ImageDescriptor result = null;
    // Compute the image descriptor on underlying diagram resource rather than the session since this later one is no longer displayed.
    IFile analysisFile = SessionHelper.getFirstAnalysisFile((DAnalysisSession) getSession());
    IWorkbenchAdapter workbenchAdapter = (IWorkbenchAdapter) analysisFile.getAdapter(IWorkbenchAdapter.class);
    if (null != workbenchAdapter) {
      result = workbenchAdapter.getImageDescriptor(analysisFile);
    }
    return result;
  }

  /**
   * @see org.eclipse.ui.Saveable#getName()
   */
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

  /**
   * @see org.eclipse.ui.Saveable#getToolTipText()
   */
  @Override
  public String getToolTipText() {
    return null;
  }

  /**
   * @see org.eclipse.ui.Saveable#isDirty()
   */
  @Override
  public boolean isDirty() {
    Session currentSession = getSession();
    if (currentSession != null) {
      if (SessionManager.INSTANCE.getSessions().contains(currentSession)) {
        return SessionStatus.DIRTY.equals(getSession().getStatus());
      }
    }
    return false;
  }
}
