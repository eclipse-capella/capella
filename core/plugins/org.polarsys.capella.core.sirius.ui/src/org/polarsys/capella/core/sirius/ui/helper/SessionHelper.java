/*******************************************************************************
 * Copyright (c) 2006, 2019 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.sirius.ui.helper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.sirius.business.api.modelingproject.ModelingProject;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.business.api.session.danalysis.DAnalysisSession;
import org.eclipse.sirius.ui.business.api.dialect.DialectEditor;
import org.eclipse.sirius.ui.business.api.session.IEditingSession;
import org.eclipse.sirius.ui.business.api.session.SessionEditorInput;
import org.eclipse.sirius.ui.business.api.session.SessionUIManager;
import org.eclipse.sirius.ui.business.api.viewpoint.ViewpointSelectionCallback;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.sirius.viewpoint.description.Viewpoint;
import org.eclipse.ui.IReusableEditor;
import org.polarsys.capella.common.ef.ExecutionManager;
import org.polarsys.capella.common.ef.command.AbstractNonDirtyingCommand;
import org.polarsys.capella.common.helpers.EObjectExt;
import org.polarsys.capella.common.helpers.EcoreUtil2;
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.common.mdsofa.common.helper.ExtensionPointHelper;
import org.polarsys.capella.common.mdsofa.common.misc.Couple;
import org.polarsys.capella.core.data.capellamodeller.Project;
import org.polarsys.capella.core.libraries.utils.IFileRequestor;
import org.polarsys.capella.core.model.handler.command.CapellaResourceHelper;
import org.polarsys.capella.core.model.handler.helpers.RepresentationHelper;
import org.polarsys.capella.core.sirius.ui.actions.CloseSessionAction;
import org.polarsys.capella.core.sirius.ui.session.ISessionAdvisor;

/**
 * {@link Session} helper.
 */
public class SessionHelper {

  /**
   * Do close the active sessions.<br>
   * The caller must be in the UI Thread.
   */
  public static void closeUiSessions(final List<?> list) {
    Iterator<?> resources = list.iterator();
    while (resources.hasNext()) {
      IResource resource = (IResource) resources.next();
      if (IResource.PROJECT == resource.getType()) {
        for (Session session : getExistingSessions((IProject) resource)) {
          closeUiSession(session);
        }
      }
    }
  }

  /**
   * Do close the given active session.<br>
   * The caller must be in the UI Thread.
   */
  public static void closeUiSession(Session session) {
    CloseSessionAction closeSessionAction = new CloseSessionAction();
    closeSessionAction.selectionChanged(new StructuredSelection(Collections.singletonList(session).toArray()));
    closeSessionAction.run();
  }

  /**
   * Get the underlying analysis file (i.e aird file)
   * @param session
   * @return the root aird resource used to open specified session.
   */
  public static IFile getFirstAnalysisFile(DAnalysisSession session) {
    return EcoreUtil2.getFile(session.getSessionResource());
  }

  /**
   * Get the Capella project (only one instance) for given session.
   * @param session
   * @return must be not <code>null</code>.
   */
  public static Project getCapellaProject(Session session) {
    Project result = null;
    Iterator<Resource> semanticResources = session.getSemanticResources().iterator();
    // Iterate over semantic resources to search for a capella project.
    while (semanticResources.hasNext()) {
      Resource semanticResource = semanticResources.next();
      EObject object = semanticResource.getContents().get(0);
      if (object instanceof Project) {
        result = (Project) object;
        break;
      }
    }
    return result;
  }

  /**
   * Get a session for given diagram file (i.e aird file).<br>
   * Only compare the given file with the first diagram resource.
   * @param firstDiagramResourceFile the file is expected to be the first diagram file contained in a session.
   * @return <code>null</code> if no session found among all active sessions.
   */
  public static Session getSession(IFile firstDiagramResourceFile) {
    Session result = null;
    // Iterate over active sessions to search the ones that semantic
    // resources are contained by the project.
    Iterator<Session> allActiveSessions = SessionManager.INSTANCE.getSessions().iterator();
    while (allActiveSessions.hasNext() && (null == result)) {
      Session session = allActiveSessions.next();
      if (session instanceof DAnalysisSession) {
        IFile sessionAnalysisFile = getFirstAnalysisFile((DAnalysisSession) session);
        if (firstDiagramResourceFile.equals(sessionAnalysisFile)) {
          // Found.
          result = session;
        }
      }
    }
    return result;
  }

  /**
   * Get a session for given analysis file.<br>
   * @param diagramResourceFile
   * @return <code>null</code> if no session found among all active sessions.
   */
  public static Session getSessionForDiagramFile(IFile diagramResourceFile) {
    Session result = null;
    // Iterate over active sessions to search the ones that semantic
    // resources are contained by the project.
    Iterator<Session> allActiveSessions = SessionManager.INSTANCE.getSessions().iterator();
    while (allActiveSessions.hasNext() && (null == result)) {
      Session session = allActiveSessions.next();
      if (session instanceof DAnalysisSession) {
        if (isAnalysisFileInvolvedIn((DAnalysisSession) session, diagramResourceFile)) {
          // Found.
          result = session;
        }
      }
    }
    return result;
  }

  /**
   * Get a session from a representation resource.
   * @param airdResource
   * @return
   */
  public static Session getSession(Resource airdResource) {
    Session result = null;
    for (Session session : SessionManager.INSTANCE.getSessions()) {
      if (getAllAirdResources(session).contains(airdResource)) {
        result = session;
        break;
      }
    }
    return result;
  }

  /**
   * Get the sessions from structured selection.
   * @return a not <code>null</code> list.
   */
  public static List<Couple<Session, IFile>> getSessionsFromSelection(IStructuredSelection selection) {
    List<Couple<Session, IFile>> sessions = new ArrayList<Couple<Session, IFile>>(0);
    Iterator<?> iterator = selection.iterator();
    while (iterator.hasNext()) {
      Object selectedElement = iterator.next();
      if (selectedElement instanceof IFile) {
        Session session = SessionHelper.getSessionForDiagramFile((IFile) selectedElement);
        if (null != session) {
          Couple<Session, IFile> couple = new Couple<Session, IFile>(session, (IFile) selectedElement);
          sessions.add(couple);
        }
      }
    }
    return sessions;
  }

  /**
   * Get project all active sessions (among all active sessions) in given project.
   * @param project
   * @return a not <code>null</code> array.
   * use getExistingSessions instead
   */
  @Deprecated
  public static Session getSessions(IProject project) {
    // Sessions are children of IProjects, get all active sessions.
    // Iterate over active sessions to search the ones that semantic
    // resources are contained by the project.
    try {
      ModelingProject modelingProject = (ModelingProject) project.getNature(CapellaResourceHelper.CAPELLA_PROJECT_NATURE);

      if (modelingProject == null) {
        return null;
      }

      return modelingProject.getSession();
    } catch (CoreException e) {
      // Error while trying to get session.
    }

    return null;
  }

  /**
   * Get active sessions from the given project
   */
  public static Collection<Session> getExistingSessions(IProject project) {
    // Sessions are children of IProjects, get all active sessions.
    // Iterate over active sessions to search the ones that semantic
    // resources are contained by the project.
    Collection<Session> sessions = new ArrayList<Session>();
    List<IFile> files = new IFileRequestor().search(project, CapellaResourceHelper.AIRD_FILE_EXTENSION, false);
    for (IFile mmFile : files) {
      Session session = SessionManager.INSTANCE.getExistingSession(EcoreUtil2.getURI(mmFile));
      if (session != null) {
        sessions.add(session);
      }
    }
    return sessions;
  }

  /**
   * Whether or not given analysis file is involved in given session.
   * @param session
   * @param analysisFile
   * @return <code>true</code> means the given session contained given analysis file.
   */
  public static boolean isAnalysisFileInvolvedIn(DAnalysisSession session, IFile analysisFile) {
    boolean result = false;

    try {
      // Precondition.
      if ((null == session) || (null == analysisFile)) {
        return result;
      }
      // Get all resources involved in the session.
      Collection<Resource> allAnalysisResources = getAllAirdResources(session);

      Iterator<Resource> analysisResources = allAnalysisResources.iterator();
      while (analysisResources.hasNext()) {
        Resource resource = analysisResources.next();
        if (analysisFile.equals(EcoreUtil2.getFile(resource))) {
          result = true;
          break;
        }
      }
    } catch (IllegalStateException exception) {
      // Could occur in case the session has been already closed.
    }
    return result;
  }

  /**
   * Get all aird resources contained in specified session.
   * @param session
   * @return a not <code>null</code> collection.
   */
  public static Collection<Resource> getAllAirdResources(Session session) {
    Collection<Resource> allAnalysisResources = new HashSet<Resource>(session.getReferencedSessionResources());
    allAnalysisResources.add(session.getSessionResource());
    return allAnalysisResources;
  }
  
  public static Collection<Resource> getSemanticResources(Session session) {
	  List<Resource> resources = new ArrayList<Resource>();
	  for (Resource resource : session.getSemanticResources()) {
		  if (CapellaResourceHelper.isCapellaResource(resource)) {
			  resources.add(resource);
		  }
	  }
	  return resources;
  }

  /**
   * Get the session advisors.
   * @return a not <code>null</code> list.
   */
  public static List<ISessionAdvisor> getSessionAdvisors() {
    List<ISessionAdvisor> sessionAdvisors = new ArrayList<ISessionAdvisor>(0);
    IConfigurationElement[] configurationElements = ExtensionPointHelper.getConfigurationElements("org.polarsys.capella.core.sirius.ui", "sessionAdvisor"); //$NON-NLS-1$ //$NON-NLS-2$
    if (configurationElements.length > 0) {
      ISessionAdvisor sessionAdvisor = (ISessionAdvisor) ExtensionPointHelper.createInstance(configurationElements[0], ExtensionPointHelper.ATT_CLASS);
      sessionAdvisors.add(sessionAdvisor);
    }
    return sessionAdvisors;
  }

  /**
   * Get the session advisors.
   * @return a not <code>null</code> list.
   */
  //  public static List<ISessionActionListener> getSessionActionListeners() {
  //    List<ISessionActionListener> sessionAdvisors = new ArrayList<ISessionActionListener>(0);
  //    IConfigurationElement[] configurationElements =
  //        ExtensionPointHelper.getConfigurationElements("org.polarsys.capella.core.sirius.ui", "sessionActionListener"); //$NON-NLS-1$ //$NON-NLS-2$
  //    if (configurationElements.length > 0) {
  //      ISessionActionListener sessionAdvisor =
  //          (ISessionActionListener) ExtensionPointHelper.createInstance(configurationElements[0], ExtensionPointHelper.ATT_CLASS);
  //      sessionAdvisors.add(sessionAdvisor);
  //    }
  //    return sessionAdvisors;
  //  }

  /**
   * Activate viewpoints in the given session
   */
  public static void activateViewpoints(final Session session, final Collection<Viewpoint> viewpoints) {

    ExecutionManager manager = TransactionHelper.getExecutionManager(session);

    // Select the viewpoints in TED way as of Sirius 4.15
    manager.execute(new AbstractNonDirtyingCommand() {

      public void run() {
        ViewpointSelectionCallback viewpointSelectionCallback = new ViewpointSelectionCallback();
        // Activate all viewpoints
        for (Viewpoint viewpoint : viewpoints) {
          viewpointSelectionCallback.selectViewpoint(viewpoint, session, new NullProgressMonitor());
        }
      }
    });
  }

  /**
   * Update editors for the given representations
   */
  public static void reloadEditors(Session session, Set<DRepresentation> representations) {
    IEditingSession uiSession = SessionUIManager.INSTANCE.getUISession(session);
    for (DRepresentation representation : representations) {
      DialectEditor editor = uiSession.getEditor(representation);
      if (editor instanceof IReusableEditor) {
        IReusableEditor iReusableEditor = (IReusableEditor) editor;
        SessionEditorInput updatedEditorInput = new SessionEditorInput(EcoreUtil.getURI(representation), EObjectExt.getText(representation), session);
        iReusableEditor.setInput(updatedEditorInput);
      }
    }
  }

  /**
   * Update all opened editors
   */
  public static void reloadEditors(Session session) {
    IEditingSession uiSession = SessionUIManager.INSTANCE.getUISession(session);
    for (DialectEditor dialectEditor : uiSession.getEditors()) {
      if (dialectEditor instanceof IReusableEditor) {
        IReusableEditor iReusableEditor = (IReusableEditor) dialectEditor;
        DRepresentation representation = dialectEditor.getRepresentation();
        SessionEditorInput updatedEditorInput = new SessionEditorInput(EcoreUtil.getURI(representation), EObjectExt.getText(representation), session);
        iReusableEditor.setInput(updatedEditorInput);
      }
    }
  }
}
