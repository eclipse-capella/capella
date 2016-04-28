/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
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
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Cursor;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.internal.PartSite;
import org.eclipse.ui.model.IWorkbenchAdapter;
import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;
import org.polarsys.capella.core.sirius.ui.SaveSessionAction;
import org.polarsys.capella.core.sirius.ui.helper.SessionHelper;

/**
 * The capella saveable.
 */
public class CapellaSaveable extends SessionSaveable {

  /**
   * Either {@code null} or the System's {@link SWT#CURSOR_WAIT} cursor
   * instance. Should never be disposed.
   */
  //FIXME Delete both cursor fields when migrating to Eclipse 4.6 
  // (see https://bugs.eclipse.org/bugs/show_bug.cgi?id=479356)
  private Cursor waitCursor;
  private Cursor originalCursor;
  
  /**
   * Constructor.
   * @param session
   */
  public CapellaSaveable(Session session) {
    super(session);
  }

  /**
   * @see org.eclipse.ui.Saveable#getAdapter(java.lang.Class)
   */
  @SuppressWarnings("rawtypes")
  @Override
  public Object getAdapter(Class adapter) {
    if (Session.class == adapter) {
      return getSession();
    }
    return super.getAdapter(adapter);
  }

  /**
   * @see org.eclipse.ui.Saveable#doSave(org.eclipse.core.runtime.IProgressMonitor)
   */
  @Override
  public void doSave(IProgressMonitor monitor) {
    SaveSessionAction saveSessionAction = new SaveSessionAction();
    saveSessionAction.selectionChanged(new StructuredSelection(getSession()));
    saveSessionAction.run();
  }

  /**
   * @see org.eclipse.ui.Saveable#equals(java.lang.Object)
   */
  @Override
  public boolean equals(Object object) {
    boolean result = false;
    if (object instanceof SessionSaveable) {
      SessionSaveable saveable = (SessionSaveable) object;
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

    if (ActiveSessionManager.getInstance().isEnabledContentNotifications(
        this.getSession().getTransactionalEditingDomain())) {

      Session currentSession = getSession();
      if (currentSession != null) {
        if (SessionManager.INSTANCE.getSessions().contains(currentSession)) {
          return SessionStatus.DIRTY.equals(getSession().getStatus());
        }
      }
    }
    
    return false;
  }
  
  // FIXME Delete this when migrating to Eclipse 4.6 
  // (see https://bugs.eclipse.org/bugs/show_bug.cgi?id=479356)
  public void disableUI(IWorkbenchPart[] parts, boolean closing) {
    for (int i = 0; i < parts.length; i++) {
      IWorkbenchPart workbenchPart = parts[i];
      Composite paneComposite = (Composite) ((PartSite) workbenchPart.getSite()).getModel().getWidget();
      Control[] paneChildren = paneComposite.getChildren();
      Composite toDisable = ((Composite) paneChildren[0]);
      toDisable.setEnabled(false);
      if (waitCursor == null) {
        waitCursor = workbenchPart.getSite().getWorkbenchWindow().getShell().getDisplay()
            .getSystemCursor(SWT.CURSOR_WAIT);
      }
      if (waitCursor.equals(paneComposite.getCursor())) {
        originalCursor = paneComposite.getCursor();
        paneComposite.setCursor(waitCursor);
      }
    }
  }
  
  //FIXME Delete this when migrating to Eclipse 4.6 (Neon)
  // (see https://bugs.eclipse.org/bugs/show_bug.cgi?id=479356)
  public void enableUI(IWorkbenchPart[] parts) {
    for (int i = 0; i < parts.length; i++) {
      IWorkbenchPart workbenchPart = parts[i];
      Composite paneComposite = (Composite) ((PartSite) workbenchPart.getSite()).getModel().getWidget();
      Control[] paneChildren = paneComposite.getChildren();
      Composite toEnable = ((Composite) paneChildren[0]);
      paneComposite.setCursor(originalCursor);
      if (waitCursor != null) {
        /*
         * waitCursor is always the System SWT.CURSOR_WAIT instance and
         * should never be disposed
         */
        waitCursor = null;
      }
      toEnable.setEnabled(true);
    }
  }  
}
