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

package org.polarsys.capella.core.sirius.ui.actions;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.eclipse.amalgam.explorer.activity.ui.api.editor.input.ActivityExplorerEditorInput;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.URI;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.osgi.util.NLS;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.ui.business.api.session.IEditingSession;
import org.eclipse.sirius.ui.business.api.session.SessionUIManager;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.BaseSelectionListenerAction;
import org.polarsys.capella.common.helpers.EcoreUtil2;
import org.polarsys.capella.common.tools.report.EmbeddedMessage;
import org.polarsys.capella.common.tools.report.appenders.usage.UsageMonitoringLogger;
import org.polarsys.capella.common.tools.report.appenders.usage.util.UsageMonitoring.EventStatus;
import org.polarsys.capella.common.tools.report.config.registry.ReportManagerRegistry;
import org.polarsys.capella.common.tools.report.util.IReportManagerDefaultComponents;
import org.polarsys.capella.core.platform.sirius.ui.session.CapellaSessionHelper;
import org.polarsys.capella.core.platform.sirius.ui.session.GitConflictHelper;
import org.polarsys.capella.core.sirius.ui.Messages;
import org.polarsys.capella.core.sirius.ui.SiriusUIPlugin;
import org.polarsys.capella.core.sirius.ui.helper.SessionHelper;

/**
 * The action allowing to open new sessions.
 */
public class OpenSessionAction extends BaseSelectionListenerAction {
  // Log4j reference logger.
  private static final Logger logger = ReportManagerRegistry.getInstance()
      .subscribe(IReportManagerDefaultComponents.UI);
  /**
   * Activity Explorer editor. See org.eclipse.amalgam.explorer.activity.ui.api.editor.ActivityExplorerEditor.ID.<br>
   */
  public static final String ACTIVITY_EXPLORER_EDITOR = "org.eclipse.amalgam.explorer.activity.ui.editor.activityExplorerEditor"; //$NON-NLS-1$
  /**
   * Should open Activity Explorer ?
   */
  private static boolean shouldOpenActivityExplorer;
  /**
   * Whether or not this action should be ran within a progress service runnable ?
   */
  private boolean shouldRunInProgressService;
  /**
   * Map of session files than cannot be opened with associated statuses.
   */
  private Map<IFile, IStatus> failedOpeningSessions;

  /**
   * Constructor.<br>
   * Default behavior open the Activity Explorer as soon as the session is open.
   */
  public OpenSessionAction() {
    super(Messages.OpenSessionAction_Title);
    shouldOpenActivityExplorer = true;
    shouldRunInProgressService = true;
  }

  /**
   * Open sessions.
   */
  protected void doOpenSessions(IProgressMonitor monitor) {
    failedOpeningSessions = new HashMap<IFile, IStatus>();
    // Go through selected elements.
    for (Object selectedElement : getStructuredSelection().toList()) {

      // Precondition ignore selected elements which are not IFile.
      if (!(selectedElement instanceof IFile)) {
        continue;
      }
      IFile selectedFile = (IFile) selectedElement;

      IStatus conflictFilesStatus = GitConflictHelper.checkConflictFiles(selectedFile.getProject());
      if (!conflictFilesStatus.isOK()) {
        CapellaSessionHelper.reportError(conflictFilesStatus);
        continue;
      }
      
      Session session = null;
      String eventName = "Open Session";
      String eventContext = selectedFile.getName();
      UsageMonitoringLogger.getInstance().log(eventName, eventContext, EventStatus.NONE);

      try {
        // Don't open session if already opened (bad performance).
        if (SessionHelper.getSession(selectedFile) != null) {
          continue;
        }
        URI selectedUri = EcoreUtil2.getURI(selectedFile);
        // Get session.
        session = SessionManager.INSTANCE.getSession(selectedUri, monitor);
        if (null == session) {
          // Session is null : open session failed.
          failedOpeningSessions.put(selectedFile, new Status(IStatus.ERROR, SiriusUIPlugin.getDefault().getPluginId(),
              "Session can't be opened (null session)")); //$NON-NLS-1$
          continue;
        }

        IStatus conflictSemanticResourcesStatus = GitConflictHelper.checkConflictFiles(session);
        if (!conflictSemanticResourcesStatus.isOK()) {
          failedOpeningSessions.put(selectedFile, conflictSemanticResourcesStatus);
          CapellaSessionHelper.reportError(conflictSemanticResourcesStatus);
          continue;
        }
        
        IStatus checkLibraryCompliancyResult = CapellaSessionHelper.checkLibrariesAvailability(session);
        if (!checkLibraryCompliancyResult.isOK()) {
          failedOpeningSessions.put(selectedFile, checkLibraryCompliancyResult);
          continue;
        }

        // Open session.
        session.open(monitor);
        if (!session.isOpen()) {
          failedOpeningSessions.put(selectedFile, new Status(IStatus.ERROR, SiriusUIPlugin.getDefault().getPluginId(),
              NLS.bind("Session can't be opened (null session)", selectedFile))); //$NON-NLS-1$
          continue;
        }

        // initialize the corresponding model
        // should be done in a listener but event opened is triggered while
        // CapellaResourceHelper.CAPELLA_MODEL_FILE_EXTENSION file has not been created
        // (in the case of a project creation)
        // Open the editing session.
        IEditingSession editingSession = SessionUIManager.INSTANCE.getOrCreateUISession(session);
        editingSession.open();
        if (session != null) {
          // Open the startup representations of opened session
          org.eclipse.sirius.ui.business.api.session.SessionHelper.openStartupRepresentations(session,
              new NullProgressMonitor());
        }
        if (shouldOpenActivityExplorer) {
          // Open Activity Explorer for open sessions.
          openActivityExplorer(session);
        }

        UsageMonitoringLogger.getInstance().log(eventName, eventContext, EventStatus.OK);

      } catch (Exception ex) {
        CapellaSessionHelper.reportException(ex);
        UsageMonitoringLogger.getInstance().log(eventName, eventContext, EventStatus.ERROR);
        //
        // IStatus status =
        // new Status(IStatus.ERROR, SiriusUIPlugin.getDefault().getPluginId(), NLS.bind("An error occured when opening
        // session ({0})", selectedFile), ex); //$NON-NLS-1$
        // failedOpeningSessions.put(selectedFile, status);
        // CapellaSessionHelper.reportError(status);

      } finally {
        // Notify action listeners
        // if (session != null) {
        // for (ISessionActionListener listener : SessionHelper.getSessionActionListeners()) {
        // listener.postOpenSession(session);
        // }
        // }
      }
    }

    // Clean up failed sessions' resources (if any).
    // for (IFile sessionFile : _failedOpeningSessions.keySet()) {
    // URI sessionFileURI = URI.createPlatformResourceURI(sessionFile.getFullPath().toString(), true);
    // final ResourceSet set = ResourceSetFactory.createFactory().createResourceSet(sessionFileURI);
    // final TransactionalEditingDomain transactionalEditingDomain =
    // EditingDomainFactoryService.INSTANCE.getEditingDomainFactory().getEditingDomain(set);
    // Resource sessionResource = transactionalEditingDomain.getResourceSet().getResource(sessionFileURI, false);
    // if ((null != sessionResource) && sessionResource.isLoaded()) {
    // // Unload the (badly) loaded resource.
    // sessionResource.unload();
    // // Remove it from the ResourceSet
    // transactionalEditingDomain.getResourceSet().getResources().remove(sessionResource);
    // }
    // }
  }

  /**
   * Get files that a session cannot be open for + a status to explain why.
   * 
   * @return a not <code>null</code> Map.
   */
  public Map<IFile, IStatus> getFailedOpeningSessions() {
    if (null == failedOpeningSessions) {
      failedOpeningSessions = Collections.emptyMap();
    }
    return failedOpeningSessions;
  }

  /**
   * @see org.eclipse.jface.action.Action#run()
   */
  @Override
  public void run() {
    // Checks the selection content.
    if (getStructuredSelection().isEmpty()) {
      return;
    }
    try {
      IRunnableWithProgress runnable = new IRunnableWithProgress() {
        @Override
        public void run(IProgressMonitor monitor) {
          doOpenSessions(monitor);
        }
      };
      if (shouldRunInProgressService) {
        // The open session action is launched in a dedicated thread (fork = true)
        // CDO context : if this runnable is not forked, we will get a deadlock on the UI thread in case the user
        // restart its application without saving his password
        PlatformUI.getWorkbench().getProgressService().run(true, false, runnable);
      } else {
        runnable.run(new NullProgressMonitor());
      }
    } catch (Exception ex) {
      logger.debug(new EmbeddedMessage(ex.getMessage(), IReportManagerDefaultComponents.UI));
    }
  }

  /**
   * Set if the Activity Explorer should be open when running this action.
   * 
   * @param open
   *          <code>true</code> means the Activity Explorer will be open after session open operation.
   */
  public void setOpenActivityExplorer(boolean open) {
    shouldOpenActivityExplorer = open;
  }

  /**
   * Set if this action should be ran within a progress service runnable.
   * 
   * @param runInProgressService
   *          <code>true</code> means this action should be ran within a progress service runnable.
   */
  public void setRunInProgressService(boolean runInProgressService) {
    shouldRunInProgressService = runInProgressService;
  }

  /**
   * Open the Activity Explorer for specified session.
   * 
   * @param session
   * @return
   */
  public static boolean openActivityExplorer(final Session session) {
    if (null == session) {
      return false;
    }

    // Create a runnable that open the Activity Explorer.
    final boolean[] result = { false };
    Runnable runnable = new Runnable() {

      @Override
      @SuppressWarnings("synthetic-access")
      public void run() {
        try {
          IWorkbenchPage activePage = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
          if (session.isOpen() || shouldOpenActivityExplorer) {
            activePage.openEditor(new ActivityExplorerEditorInput(session, SessionHelper.getCapellaProject(session)),
                ACTIVITY_EXPLORER_EDITOR, true, IWorkbenchPage.MATCH_ID | IWorkbenchPage.MATCH_INPUT);
          }
          result[0] = true;
        } catch (PartInitException exception) {
          StringBuilder loggerMessage = new StringBuilder(".run(..) _ Activity Explorer not Found."); //$NON-NLS-1$
          loggerMessage.append(exception.getMessage());
          logger.warn(new EmbeddedMessage(loggerMessage.toString(), IReportManagerDefaultComponents.UI), exception);
        }
      }
    };

    Display display = Display.getCurrent();
    if (null == display) {
      PlatformUI.getWorkbench().getDisplay().asyncExec(runnable);
    } else {
      runnable.run();
    }
    return result[0];
  }
}
