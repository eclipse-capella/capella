/*******************************************************************************
 * Copyright (c) 2006, 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.test.framework.internal.helpers;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.ui.URIEditorInput;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionStatus;
import org.eclipse.sirius.business.api.session.danalysis.DAnalysisSession;
import org.eclipse.sirius.common.ui.tools.api.util.EclipseUIUtil;
import org.eclipse.sirius.ui.business.api.session.IEditingSession;
import org.eclipse.sirius.ui.business.api.session.SessionUIManager;
import org.eclipse.sirius.viewpoint.SiriusPlugin;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.polarsys.capella.common.ef.command.AbstractNonDirtyingCommand;
import org.polarsys.capella.common.helpers.EcoreUtil2;
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.test.framework.helpers.SessionHelper;

/** 
 * It has been added in order to (quickly) remove any dependencies to Capella layers plugins.
 * This class is mainly copied from CloseSessionAction class. 
 *
 */
public class CloseSessionHelper {

  private Session _session;
  
  private boolean _shouldSaveBeforeClosing;
  
  public CloseSessionHelper(Session session, boolean shouldSaveBeforeclosing) {
    _session = session;
    _shouldSaveBeforeClosing = shouldSaveBeforeclosing;
  }

  public void run() {
    // Gets all selected sessions.
    List<Session> sessions = Collections.singletonList(_session);
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
   * The close session workspace operation.
   */
  private class CloseSessionOperation implements IRunnableWithProgress {
    // The selected sessions list.
    private List<Session> _selectedSessions;

    /**
     * Constructs the workspace operation to close sessions.
     * @param selectedSessions_p The sessions list.
     */
    public CloseSessionOperation(List<Session> selectedSessions_p) {
      _selectedSessions = selectedSessions_p;
    }

    /**
     * @see org.eclipse.jface.operation.IRunnableWithProgress#run(org.eclipse.core.runtime.IProgressMonitor)
     */
    public void run(IProgressMonitor monitor_p) throws InvocationTargetException, InterruptedException {
      for (Session session : _selectedSessions) {
        CloseSessionCommand command = new CloseSessionCommand(session);
        TransactionHelper.getExecutionManager(session).execute(command);
        boolean cr = command.isCompleted();
        if (cr) {
          // Close and remove must not be run in the TED command to make sure memory allocations are freed. Recommendation from Obeo.
          if (session.isOpen()) {
            session.close(monitor_p);
          }
        }
      }
    }
  }

  /**
   * Close session command.<br>
   * Code copied from {@link DesignerSessionView}
   */
  private class CloseSessionCommand extends AbstractNonDirtyingCommand {
    /**
     * Session to close.
     */
    private Session _sessionToClose;
    /**
     * Flag to report if the command completes or not.
     */
    private boolean _isCompleted;

    /**
     * Constructs the command allowing to close session.
     * @param session_p The session to close.
     */
    public CloseSessionCommand(Session session_p) {
      _sessionToClose = session_p;
      _isCompleted = false;
    }

    /**
     * Is this command completed i.e the end-user don't cancel it.
     * @return <code>false</code> means the end-users canceled the close operation.
     */
    public boolean isCompleted() {
      return _isCompleted;
    }

    /**
     * @see java.lang.Runnable#run()
     */
    @SuppressWarnings("synthetic-access")
    public void run() {

      // Save session, if asked and needed.
      boolean saveIsNeeded = 
        _shouldSaveBeforeClosing &&
        SessionStatus.DIRTY.equals(_sessionToClose.getStatus())
      ;
        
      if (saveIsNeeded) {
        Collection<IFile> files = new ArrayList<IFile>();
        if (areSessionResourcesSaveable(_sessionToClose, files)) {
          _sessionToClose.save(new NullProgressMonitor());
        } else {
//          String msg;
//          msg = Messages.unableToSaveDialog_TopMsg;
//          for (IFile file : files) {
//            msg += file.toString() + ICommonConstants.EOL_CHARACTER;
//          }
//          msg += ICommonConstants.EOL_CHARACTER + Messages.unableToSaveDuringCloseOpsDialog_BottomQuestion + ICommonConstants.EOL_CHARACTER;
//
//          Shell activeShell = Display.getCurrent().getActiveShell();
//          if (!MessageDialog.openQuestion(activeShell, Messages.CloseSessionAction_Title, msg)) {
//            return;
//          }
        }
      }
      IEditingSession uiSession = SessionUIManager.INSTANCE.getUISession(_sessionToClose);
      if (null != uiSession) {
        uiSession.close(saveIsNeeded);
      }
      // Close editors not yet registered with an UI session see trac #1217
      Display.getDefault().syncExec(new CloseOthersEditorRunnable(_sessionToClose, saveIsNeeded));
      // Remove the UI session.
      if (null != uiSession) {
        SessionUIManager.INSTANCE.remove(uiSession);
      }
      _isCompleted = true;
    }
  }

  /**
   * Close Open editors linked to a session.
   */
  private class CloseOthersEditorRunnable implements Runnable {
    private boolean _save;
    private Session _currentSession;

    /**
     * Constructs the runnable to close others editors.
     * @param session_p The session.
     * @param save_p <code>True</code> if save operation before closing is needed else <code>false</code>.
     */
    public CloseOthersEditorRunnable(Session session_p, boolean save_p) {
      _currentSession = session_p;
      _save = save_p;
    }

    /**
     * @see java.lang.Runnable#run()
     */
    public void run() {
      IWorkbenchPage page = EclipseUIUtil.getActivePage();
      if (null == page) {
        return;
      }

      // Get all editor references i.e active and not activated editors. Indeed, after a restart, previous open editors are re-opened by the workbench.
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
            if (_currentSession instanceof DAnalysisSession) {
              // Get the analysis resources.
              Collection<Resource> analysisResources = SessionHelper.getAllAirdResources(_currentSession);
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
        page.closeEditors(editorReferencesToClose.toArray(new IEditorReference[editorReferencesToClose.size()]), _save);
      }
    }
  }
  
  /**
   * Utility method in order to perform a pre checking on a Session about the saveable state of its embedded resource.
   * @param session_p the target Session
   * @param unsaveableFiles_p return the list of unsaveable resources, otherwise an empty one.
   * @return <code>true</code> if session can be saved, <code>false</code> otherwise.
   */
  public static boolean areSessionResourcesSaveable(final Session session_p, Collection<IFile> unsaveableFiles_p) {
    // Preconditions
    if ((null == session_p) || (null == unsaveableFiles_p) || !(session_p instanceof DAnalysisSession)) {
      return false;
    }
    boolean ret = true;
    // Let's obtain all resources for this session.
    Collection<Resource> allResources = SessionHelper.getAllAirdResources(session_p);
    allResources.addAll(session_p.getSemanticResources());

    // Let's check it
    for (Resource resource : allResources) {
      if (null != resource) {
        // check whether physical device is available
        IFile file = EcoreUtil2.getFile(resource);
        if (null != file) {
          IPath path = file.getLocation();
          IPath device = path.removeLastSegments(path.segmentCount());
          File dir = device.toFile();

          if (!dir.canWrite()) {
            ret = false;
            unsaveableFiles_p.add(file);
          }
        }
      }
    }
    return ret;
  }
  
}
