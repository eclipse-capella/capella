/*******************************************************************************
 * Copyright (c) 2006, 2018 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.sirius.ui.actions;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.osgi.util.NLS;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.common.ui.tools.api.util.SWTUtil;
import org.eclipse.sirius.ui.business.api.session.IEditingSession;
import org.eclipse.sirius.ui.business.api.session.SessionUIManager;
import org.eclipse.sirius.viewpoint.provider.SiriusEditPlugin;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.ISaveablePart2;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.BaseSelectionListenerAction;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.polarsys.capella.common.ef.command.AbstractNonDirtyingCommand;
import org.polarsys.capella.common.helpers.EcoreUtil2;
import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;
import org.polarsys.capella.common.tools.report.appenders.usage.UsageMonitoringLogger;
import org.polarsys.capella.common.tools.report.appenders.usage.util.UsageMonitoring.EventStatus;
import org.polarsys.capella.core.sirius.ui.Messages;
import org.polarsys.capella.core.sirius.ui.SiriusUIPlugin;
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
    SiriusUIPlugin.getDefault().runSaveOperation(activeShell, closeSessionOperation);
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

    protected void closeSession(Session session, boolean saveIsNeeded) {
      IEditingSession uiSession = SessionUIManager.INSTANCE.getUISession(session);
      if (null != uiSession) {
        SessionCloseManager.closeUISession(uiSession, saveIsNeeded);
      }
      SessionCloseManager.closeSession(session);
    }

    /**
     * @see java.lang.Runnable#run()
     */
    @Override
    @SuppressWarnings("synthetic-access")
    public void run() {

      // Ask user confirmation, if needed.
      int choice = ISaveablePart2.NO;
      if (SessionCloseManager.isDirty(session)) {
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

        try {
          SessionCloseManager.saveSession(session);

        } catch (RuntimeException e) {
          String msg = NLS.bind(Messages.unableToSaveDuringCloseOpsDialog_BottomQuestion, e.getMessage()); 
          Shell activeShell = Display.getCurrent().getActiveShell();
          if (!MessageDialog.openQuestion(activeShell, Messages.CloseSessionAction_Title, msg)) {
            return;
          }
          saveIsNeeded = false;
        }
      }

      String eventName = "Close Session";
      IFile resourceFile = EcoreUtil2.getFile(session.getSessionResource());
	  String eventContext = resourceFile != null ? resourceFile.getName() : ICommonConstants.EMPTY_STRING;
	  UsageMonitoringLogger.getInstance().log(eventName, eventContext, EventStatus.NONE);
      
      closeSession(session, saveIsNeeded);
      
      UsageMonitoringLogger.getInstance().log(eventName, eventContext, EventStatus.OK);
    }

  }

}
