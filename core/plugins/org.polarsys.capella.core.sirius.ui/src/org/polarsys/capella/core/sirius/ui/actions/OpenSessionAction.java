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

package org.polarsys.capella.core.sirius.ui.actions;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
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
import org.polarsys.capella.common.tools.report.config.registry.ReportManagerRegistry;
import org.polarsys.capella.common.tools.report.util.IReportManagerDefaultComponents;
import org.polarsys.capella.core.platform.sirius.ui.session.CapellaSessionHelper;
import org.polarsys.capella.core.sirius.ui.Messages;
import org.polarsys.capella.core.sirius.ui.SiriusUIPlugin;
import org.polarsys.capella.core.sirius.ui.editor.CapellaDashboardEditorInput;
import org.polarsys.capella.core.sirius.ui.helper.SessionHelper;

/**
 * The action allowing to open new sessions.
 */
public class OpenSessionAction extends BaseSelectionListenerAction {
  // Log4j reference logger.
  private static final Logger __logger = ReportManagerRegistry.getInstance().subscribe(IReportManagerDefaultComponents.UI);
  /**
   * Capella dashboard editor. See org.polarsys.capella.core.dashboard.editor.CapellaDashboardEditor.ID.<br>
   * We must not depend on capella dashboard editor plug-in due to deployment reasons.
   */
  public static final String CAPELLA_DASHBOARD_EDITOR = "org.polarsys.capella.core.dashboard.editor.capellaDashboardEditor"; //$NON-NLS-1$
  /**
   * Should open the Capella Dashboard ?
   */
  private boolean _shouldOpenCapellaDashboard;
  /**
   * Whether or not this action should be ran within a progress service runnable ?
   */
  private boolean _shouldRunInProgressService;
  /**
   * Map of session files than cannot be opened with associated statuses.
   */
  private Map<IFile, IStatus> _failedOpeningSessions;

  /**
   * Constructor.<br>
   * Default behavior open the Capella Dashboard as soon the session is open.
   */
  public OpenSessionAction() {
    super(Messages.OpenSessionAction_Title);
    _shouldOpenCapellaDashboard = true;
    _shouldRunInProgressService = true;
  }

  /**
   * Open sessions.
   */
  protected void doOpenSessions(IProgressMonitor monitor_p) {
    _failedOpeningSessions = new HashMap<IFile, IStatus>();
    // Go through selected elements.
    for (Object selectedElement : getStructuredSelection().toList()) {
      // Precondition ignore selected elements which are not IFile.
      if (!(selectedElement instanceof IFile)) {
        continue;
      }
      IFile selectedFile = (IFile) selectedElement;
      Session session = null;
      try {
        // Don't open session if already opened (bad performance).
        if (SessionHelper.getSession(selectedFile) != null) {
          continue;
        }
        // Check model compliancy.
        URI selectedUri = EcoreUtil2.getURI(selectedFile);
        IStatus checkModelsCompliancyResult = CapellaSessionHelper.checkModelsCompliancy(selectedUri);
        if (!checkModelsCompliancyResult.isOK()) {
          _failedOpeningSessions.put(selectedFile, checkModelsCompliancyResult);
          continue;
        }
        // Get session.
        session = SessionManager.INSTANCE.getSession(selectedUri, monitor_p);
        if (null == session) {
          // session is null : open session failed.
          _failedOpeningSessions.put(selectedFile, new Status(IStatus.ERROR, SiriusUIPlugin.getDefault().getPluginId(),
              "Session can't be opened (null session)")); //$NON-NLS-1$
          continue;
        }

        IStatus checkLibraryCompliancyResult = CapellaSessionHelper.checkLibrariesAvailability(session);
        if (!checkLibraryCompliancyResult.isOK()) {
          _failedOpeningSessions.put(selectedFile, checkLibraryCompliancyResult);
          continue;
        }

        // Open session.
        session.open(monitor_p);
        // initialize the corresponding model
        // should be done in a listener but event opened is triggered while the melodymodeller file has not been created (in the case of a project creation)
        // Open the editing session.
        IEditingSession editingSession = SessionUIManager.INSTANCE.getOrCreateUISession(session);
        editingSession.open();
        if (_shouldOpenCapellaDashboard) {
          // Open Capella Welcome for open sessions.
          openCapellaDashboard(session);
        }
      } catch (Exception ex) {
        IStatus status =
            new Status(IStatus.ERROR, SiriusUIPlugin.getDefault().getPluginId(), NLS.bind("An error occured when opening session ({0})", selectedFile), ex); //$NON-NLS-1$
        _failedOpeningSessions.put(selectedFile, status);
        __logger.debug(new EmbeddedMessage(status.getMessage(), IReportManagerDefaultComponents.UI));
        CapellaSessionHelper.reportException(ex);

      } finally {
        // Notify action listeners
        //        if (session != null) {
        //          for (ISessionActionListener listener : SessionHelper.getSessionActionListeners()) {
        //            listener.postOpenSession(session);
        //          }
        //        }
      }
    }

    // Clean up failed sessions' resources (if any).
    //    for (IFile sessionFile : _failedOpeningSessions.keySet()) {
    //      URI sessionFileURI = URI.createPlatformResourceURI(sessionFile.getFullPath().toString(), true);
    //      final ResourceSet set = ResourceSetFactory.createFactory().createResourceSet(sessionFileURI);
    //      final TransactionalEditingDomain transactionalEditingDomain = EditingDomainFactoryService.INSTANCE.getEditingDomainFactory().getEditingDomain(set);
    //      Resource sessionResource = transactionalEditingDomain.getResourceSet().getResource(sessionFileURI, false);
    //      if ((null != sessionResource) && sessionResource.isLoaded()) {
    //        // Unload the (badly) loaded resource.
    //        sessionResource.unload();
    //        // Remove it from the ResourceSet
    //        transactionalEditingDomain.getResourceSet().getResources().remove(sessionResource);
    //      }
    //    }
  }

  /**
   * Get files that a session cannot be open for + a status to explain why.
   * @return a not <code>null</code> Map.
   */
  public Map<IFile, IStatus> getFailedOpeningSessions() {
    if (null == _failedOpeningSessions) {
      _failedOpeningSessions = Collections.emptyMap();
    }
    return _failedOpeningSessions;
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
        public void run(IProgressMonitor monitor_p) {
          doOpenSessions(monitor_p);
        }
      };
      if (_shouldRunInProgressService) {
        // The open session action is launched in a dedicated thread (fork = true)
        // CDO context : if this runnable is not forked, we will get a deadlock on the thread UI in case the user restart its application without
        // saving his password
        PlatformUI.getWorkbench().getProgressService().run(true, false, runnable);
      } else {
        runnable.run(new NullProgressMonitor());
      }
    } catch (Exception ex) {
      __logger.debug(new EmbeddedMessage(ex.getMessage(), IReportManagerDefaultComponents.UI));
    }
  }

  /**
   * Set if the Capella dashboard should be open when running this action.
   * @param open_p <code>true</code> means the Capella dashboard will be open after session open operation.
   */
  public void setOpenCapellaDashboard(boolean open_p) {
    _shouldOpenCapellaDashboard = open_p;
  }

  /**
   * Set if this action should be ran within a progress service runnable.
   * @param runInProgressService_p <code>true</code> means this action should be ran within a progress service runnable.
   */
  public void setRunInProgressService(boolean runInProgressService_p) {
    _shouldRunInProgressService = runInProgressService_p;
  }

  /**
   * Open the Capella Dashboard for specified session.
   * @param session_p
   * @return
   */
  public static boolean openCapellaDashboard(final Session session_p) {
    final boolean[] capellaWelcomeOpen = { false };
    if (null == session_p) {
      return capellaWelcomeOpen[0];
    }
    // Create a runnable that open the capella dashboard.
    Runnable runnable = new Runnable() {
      @Override
      @SuppressWarnings("synthetic-access")
      public void run() {
        try {
          IWorkbenchPage activePage = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
          if (session_p.isOpen()) {
            activePage.openEditor(new CapellaDashboardEditorInput(session_p, SessionHelper.getCapellaProject(session_p)), CAPELLA_DASHBOARD_EDITOR);
          }
          capellaWelcomeOpen[0] = true;
        } catch (PartInitException exception_p) {
          StringBuilder loggerMessage = new StringBuilder(".run(..) _ Capella Dashboard not Found."); //$NON-NLS-1$
          loggerMessage.append(exception_p.getMessage());
          __logger.warn(new EmbeddedMessage(loggerMessage.toString(), IReportManagerDefaultComponents.UI), exception_p);
        }
      }
    };
    Display display = Display.getCurrent();
    if (null == display) {
      PlatformUI.getWorkbench().getDisplay().asyncExec(runnable);
    } else {
      runnable.run();
    }
    return capellaWelcomeOpen[0];
  }
}
