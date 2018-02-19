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

package org.polarsys.capella.core.sirius.ui.actions;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.ui.URIEditorInput;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.danalysis.DAnalysisSession;
import org.eclipse.sirius.common.ui.tools.api.util.EclipseUIUtil;
import org.eclipse.sirius.common.ui.tools.api.util.SWTUtil;
import org.eclipse.sirius.ui.business.api.session.IEditingSession;
import org.eclipse.sirius.ui.business.api.session.SessionUIManager;
import org.eclipse.sirius.viewpoint.SiriusPlugin;
import org.eclipse.sirius.viewpoint.provider.SiriusEditPlugin;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.ISaveablePart2;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.BaseSelectionListenerAction;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.polarsys.capella.common.ef.command.AbstractNonDirtyingCommand;
import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;
import org.polarsys.capella.core.sirius.ui.Messages;
import org.polarsys.capella.core.sirius.ui.closeproject.SessionCloseManager;
import org.polarsys.capella.core.sirius.ui.helper.SessionHelper;

/**
 * The action allowing to close sessions.
 */
public class CloseSessionAction extends BaseSelectionListenerAction {
  /**
   * Whether or not a dialog is prompted to ask to save a dirty session.
   */
  private boolean showDialog;
  /**
   * Whether or not a dirty session must be saved if no dialog is prompted to the end-user.
   */
  private boolean shouldSaveIfNoDialog;

  /**
   * Constructs the action allowing to close sessions.
   */
  public CloseSessionAction() {
    super(Messages.CloseSessionAction_Title);
    setActionDefinitionId("org.eclipse.ui.file.close"); //$NON-NLS-1$
    setImageDescriptor(AbstractUIPlugin.imageDescriptorFromPlugin(SiriusEditPlugin.ID, "/icons/full/others/close.gif")); //$NON-NLS-1$
    showDialog = true;
  }

  /**
   * Show dialogs to the end-user if needed.
   *
   * @param showDialog
   *          <code>false</code> to run silently the close action (i.e no dialog is displayed).
   */
  public void showDialog(boolean showDialog) {
    this.showDialog = showDialog;
  }

  /**
   * Should Save if no dialog is prompted to the end-user, {@link #showDialog(boolean)}.
   *
   * @param save
   */
  public void shouldSaveIfNoDialog(boolean save) {
    this.shouldSaveIfNoDialog = save;
  }

  /**
   * @see org.eclipse.jface.action.Action#run()
   */
  @Override
  public void run() {
    // Gets all selected sessions.
    List<Session> sessions = getSessionsFromSelection(getStructuredSelection());
    // Launch the close sessions operation on all selected sessions.
    IRunnableWithProgress closeSessionOperation = new CloseSessionOperation(sessions);

    Shell activeShell = PlatformUI.getWorkbench().getDisplay().getActiveShell();
    ProgressMonitorDialog monitor = new ProgressMonitorDialog(activeShell);
    try {
      monitor.run(false, false, closeSessionOperation);
    } catch (final InvocationTargetException ite) {
      SiriusPlugin.getDefault().error("the program was not able close the session", ite); //$NON-NLS-1$
    } catch (final InterruptedException ie) {
      SiriusPlugin.getDefault().warning("the close session action was interrupted", ie); //$NON-NLS-1$
    }
  }

  /**
   * Get the sessions from internal structured selection.
   *
   * @return a not <code>null</code> list.
   */
  private List<Session> getSessionsFromSelection(IStructuredSelection selection) {
    List<Session> sessions = new ArrayList<Session>(0);
    Iterator<?> iterator = selection.iterator();
    while (iterator.hasNext()) {
      Object selectedElement = iterator.next();
      if (selectedElement instanceof Session) {
        sessions.add((Session) selectedElement);
      } else if (selectedElement instanceof IFile) {
        Session session = SessionHelper.getSessionForDiagramFile((IFile) selectedElement);
        if (null != session) {
          sessions.add(session);
        }
      }
    }
    return sessions;
  }

  /**
   * @see org.eclipse.ui.actions.BaseSelectionListenerAction#updateSelection(org.eclipse.jface.viewers.IStructuredSelection)
   */
  @Override
  protected boolean updateSelection(IStructuredSelection selection) {
    // Iterate over sessions to check all are open.
    Iterator<Session> sessions = getSessionsFromSelection(selection).iterator();
    boolean isOneSessionClosed = false;
    while (sessions.hasNext() && !isOneSessionClosed) {
      Session selectedSession = sessions.next();
      isOneSessionClosed = !selectedSession.isOpen();
    }
    return !isOneSessionClosed;
  }

  /**
   * The close session workspace operation.
   */
  public class CloseSessionOperation implements IRunnableWithProgress {
    // The selected sessions list.
    private List<Session> selectedSessions;

    /**
     * Constructs the workspace operation to close sessions.
     *
     * @param selectedSessions
     *          The sessions list.
     */
    public CloseSessionOperation(List<Session> selectedSessions) {
      this.selectedSessions = selectedSessions;
    }

    /**
     * @see org.eclipse.jface.operation.IRunnableWithProgress#run(org.eclipse.core.runtime.IProgressMonitor)
     */
    @Override
    public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
      for (Session session : selectedSessions) {
        CloseSessionCommand command = new CloseSessionCommand(session);
        command.run();
      }
    }
  }

  /*******************************************************************************
   * Copyright (c) 2008, 2010, 2011 THALES GLOBAL SERVICES. All rights reserved. This program and the accompanying
   * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this
   * distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
   *
   * Contributors: Obeo - initial API and implementation Thales - Contributor
   *
   * Close session command. <br>
   *
   * @see {@link org.eclipse.sirius.ui.tools.internal.views.sessionview.DesignerSessionView}
   *
   *******************************************************************************/
  private class CloseSessionCommand extends AbstractNonDirtyingCommand {
    /**
     * Session to close.
     */
    private Session session;

    /**
     * Constructs the command allowing to close session.
     *
     * @param session
     *          The session to close.
     */
    public CloseSessionCommand(Session session) {
      this.session = session;
    }

    protected void saveSession(Session session) {
      Collection<IFile> files = new ArrayList<IFile>();
      if (SessionCloseManager.isSaveable(session,
          files)/* SessionHelper.areSessionResourcesSaveable(session, files) */) {
        SessionCloseManager.saveSession(session);// session.save(new NullProgressMonitor());
      } else {
        String msg;
        msg = Messages.unableToSaveDialog_TopMsg;
        for (IFile file : files) {
          msg += file.toString() + ICommonConstants.EOL_CHARACTER;
        }
        msg += ICommonConstants.EOL_CHARACTER + Messages.unableToSaveDuringCloseOpsDialog_BottomQuestion
            + ICommonConstants.EOL_CHARACTER;
        Shell activeShell = Display.getCurrent().getActiveShell();

        MessageDialog.openQuestion(activeShell, Messages.CloseSessionAction_Title, msg);
      }
    }

    protected void closeSession(Session session, boolean saveIsNeeded) {
      IEditingSession uiSession = SessionUIManager.INSTANCE.getUISession(session);
      if (null != uiSession) {
        SessionCloseManager.closeUISession(uiSession, saveIsNeeded);// uiSession.close(saveIsNeeded);
      }
      // Close editors not yet registered with an UI session
      Display.getDefault().syncExec(new CloseOthersEditorRunnable(session, saveIsNeeded));
      // Remove the UI session.
      if (uiSession != null) {
        SessionCloseManager.removeUiSession(uiSession);// SessionUIManager.INSTANCE.remove(uiSession);
      }
      // sessionsToClose.add(session);
      if (session.isOpen()) {
        SessionCloseManager.closeSession(session);// session.close(null);
      }
      SessionCloseManager.cleanSession(session);
    }

    /**
     * @see java.lang.Runnable#run()
     */
    @Override
    @SuppressWarnings("synthetic-access")
    public void run() {

      // Ask user confirmation, if needed.
      int choice = ISaveablePart2.NO;
      if (SessionCloseManager.isDirty(session)/* SessionStatus.DIRTY.equals(session.getStatus()) */) {
        if (showDialog) {
          // Show a dialog.
          choice = SWTUtil.showSaveDialog(session, "Session", true); //$NON-NLS-1$
        } else {
          choice = (shouldSaveIfNoDialog) ? ISaveablePart2.YES : ISaveablePart2.NO;
        }
      }
      if (ISaveablePart2.CANCEL == choice) {
        return;
      }

      // Save session, if required.
      boolean saveIsNeeded = (ISaveablePart2.YES == choice);
      if (saveIsNeeded) {
        saveSession(session);
      }

      closeSession(session, saveIsNeeded);
    }

  }

  /**
   * Close Open editors linked to a session.
   */
  private class CloseOthersEditorRunnable implements Runnable {
    private boolean save;
    private Session currentSession;

    /**
     * Constructs the runnable to close others editors.
     *
     * @param session
     *          The session.
     * @param save
     *          <code>True</code> if save operation before closing is needed else <code>false</code>.
     */
    public CloseOthersEditorRunnable(Session session, boolean save) {
      this.currentSession = session;
      this.save = save;
    }

    /**
     * @see java.lang.Runnable#run()
     */
    @Override
    public void run() {
      IWorkbenchPage page = EclipseUIUtil.getActivePage();
      if (null == page) {
        return;
      }

      // Get all editor references i.e active and not activated editors. Indeed, after a restart, previous open editors
      // are re-opened by the workbench.
      // But only the active one is fully loaded, other ones are only editors references.
      IEditorReference[] editorReferences = page.getEditorReferences();
      // Editor references to close.
      List<IEditorReference> editorReferencesToClose = new ArrayList<IEditorReference>(0);
      // Loop over open editor references.
      for (IEditorReference editorReference : editorReferences) {
        try {
          IEditorInput editorReferenceInput = editorReference.getEditorInput();
          // Get the editor input from the editor reference.
          if (editorReferenceInput instanceof URIEditorInput) {
            // Check if current session matches current editor reference input.
            if (currentSession instanceof DAnalysisSession) {
              // Get the analysis resources.
              Collection<Resource> analysisResources = SessionHelper.getAllAirdResources(currentSession);
              // Loop over theses ones to match URIs.
              for (Resource resource : analysisResources) {
                URI uri = resource.getURI();
                URI trimFragment = ((URIEditorInput) editorReferenceInput).getURI().trimFragment();
                if ((uri != null) && uri.equals(trimFragment)) {
                  editorReferencesToClose.add(editorReference);
                  break;
                }
              }
            }
          }
        } catch (final PartInitException e) {
          // do nothing
        }
      }
      // Close found editors.
      if (!editorReferencesToClose.isEmpty()) {
        page.closeEditors(editorReferencesToClose.toArray(new IEditorReference[editorReferencesToClose.size()]), save);
      }
    }
  }
}
