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
package org.polarsys.capella.core.sirius.ui.helper;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.sirius.business.api.modelingproject.ModelingProject;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.business.api.session.danalysis.DAnalysisSession;
import org.eclipse.sirius.ui.business.api.viewpoint.ViewpointSelectionCallback;
import org.eclipse.sirius.viewpoint.description.Viewpoint;
import org.polarsys.capella.common.ef.ExecutionManager;
import org.polarsys.capella.common.ef.command.AbstractNonDirtyingCommand;
import org.polarsys.capella.common.helpers.EcoreUtil2;
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.common.mdsofa.common.helper.ExtensionPointHelper;
import org.polarsys.capella.common.mdsofa.common.misc.Couple;
import org.polarsys.capella.core.data.capellamodeller.Project;
import org.polarsys.capella.core.libraries.utils.IFileRequestor;
import org.polarsys.capella.core.model.handler.command.CapellaResourceHelper;
import org.polarsys.capella.core.sirius.ui.actions.CloseSessionAction;
import org.polarsys.capella.core.sirius.ui.session.ISessionAdvisor;

/**
 * {@link Session} helper.
 */
public class SessionHelper {
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
    Collection<Resource> allResources = getAllAirdResources(session_p);
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

  /**
   * Do close the active sessions.<br>
   * The caller must be in the UI Thread.
   */
  public static void closeUiSessions(final List<?> list_p) {
    Iterator<?> resources = list_p.iterator();
    while (resources.hasNext()) {
      IResource resource = (IResource) resources.next();
      if (IResource.PROJECT == resource.getType()) {
        for (Session session : getExistingSessions((IProject) resource)) {
          CloseSessionAction closeSessionAction = new CloseSessionAction();
          closeSessionAction.selectionChanged(new StructuredSelection(Collections.singletonList(session).toArray()));
          closeSessionAction.run();
        }
      }
    }
  }

  /**
   * Get the underlying analysis file (i.e aird file)
   * @param session_p
   * @return the root aird resource used to open specified session.
   */
  public static IFile getFirstAnalysisFile(DAnalysisSession session_p) {
    return EcoreUtil2.getFile(session_p.getSessionResource());
  }

  /**
   * Get the Capella project (only one instance) for given session.
   * @param session_p
   * @return must be not <code>null</code>.
   */
  public static Project getCapellaProject(Session session_p) {
    Project result = null;
    Iterator<Resource> semanticResources = session_p.getSemanticResources().iterator();
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
   * @param firstDiagramResourceFile_p the file is expected to be the first diagram file contained in a session.
   * @return <code>null</code> if no session found among all active sessions.
   */
  public static Session getSession(IFile firstDiagramResourceFile_p) {
    Session result = null;
    // Iterate over active sessions to search the ones that semantic
    // resources are contained by the project.
    Iterator<Session> allActiveSessions = SessionManager.INSTANCE.getSessions().iterator();
    while (allActiveSessions.hasNext() && (null == result)) {
      Session session = allActiveSessions.next();
      if (session instanceof DAnalysisSession) {
        IFile sessionAnalysisFile = getFirstAnalysisFile((DAnalysisSession) session);
        if (firstDiagramResourceFile_p.equals(sessionAnalysisFile)) {
          // Found.
          result = session;
        }
      }
    }
    return result;
  }

  /**
   * Get a session for given analysis file.<br>
   * @param selectedElement_p
   * @return <code>null</code> if no session found among all active sessions.
   */
  public static Session getSessionForDiagramFile(IFile diagramResourceFile_p) {
    Session result = null;
    // Iterate over active sessions to search the ones that semantic
    // resources are contained by the project.
    Iterator<Session> allActiveSessions = SessionManager.INSTANCE.getSessions().iterator();
    while (allActiveSessions.hasNext() && (null == result)) {
      Session session = allActiveSessions.next();
      if (session instanceof DAnalysisSession) {
        if (isAnalysisFileInvolvedIn((DAnalysisSession) session, diagramResourceFile_p)) {
          // Found.
          result = session;
        }
      }
    }
    return result;
  }

  /**
   * Get a session from a representation resource.
   * @param airdResource_p
   * @return
   */
  public static Session getSession(Resource airdResource_p) {
    Session result = null;
    for (Session session : SessionManager.INSTANCE.getSessions()) {
      if (getAllAirdResources(session).contains(airdResource_p)) {
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
  public static List<Couple<Session, IFile>> getSessionsFromSelection(IStructuredSelection selection_p) {
    List<Couple<Session, IFile>> sessions = new ArrayList<Couple<Session, IFile>>(0);
    Iterator<?> iterator = selection_p.iterator();
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
   * @param project_p
   * @return a not <code>null</code> array.
   * use getExistingSessions instead
   */
  @Deprecated
  public static Session getSessions(IProject project_p) {
    // Sessions are children of IProjects, get all active sessions.
    // Iterate over active sessions to search the ones that semantic
    // resources are contained by the project.
    try {
      ModelingProject modelingProject = (ModelingProject) project_p.getNature(CapellaResourceHelper.CAPELLA_PROJECT_NATURE);

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
  public static Collection<Session> getExistingSessions(IProject project_p) {
    // Sessions are children of IProjects, get all active sessions.
    // Iterate over active sessions to search the ones that semantic
    // resources are contained by the project.
    Collection<Session> sessions = new ArrayList<Session>();
    List<IFile> files = new IFileRequestor().search(project_p, CapellaResourceHelper.AIRD_FILE_EXTENSION, false);
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
   * @param session_p
   * @param analysisFile_p
   * @return <code>true</code> means the given session contained given analysis file.
   */
  public static boolean isAnalysisFileInvolvedIn(DAnalysisSession session_p, IFile analysisFile_p) {
    boolean result = false;

    try {
      // Precondition.
      if ((null == session_p) || (null == analysisFile_p)) {
        return result;
      }
      // Get all resources involved in the session.
      Collection<Resource> allAnalysisResources = getAllAirdResources(session_p);

      Iterator<Resource> analysisResources = allAnalysisResources.iterator();
      while (analysisResources.hasNext()) {
        Resource resource = analysisResources.next();
        if (analysisFile_p.equals(EcoreUtil2.getFile(resource))) {
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
   * @param session_p
   * @return a not <code>null</code> collection.
   */
  public static Collection<Resource> getAllAirdResources(Session session_p) {
    Collection<Resource> allAnalysisResources = new HashSet<Resource>(session_p.getReferencedSessionResources());
    allAnalysisResources.add(session_p.getSessionResource());
    return allAnalysisResources;
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
  public static void activateViewpoints(final Session session_p, final Collection<Viewpoint> viewpoints_p) {

    ExecutionManager manager = TransactionHelper.getExecutionManager(session_p);

    // Select the viewpoints in TED way as of Sirius 4.15
    manager.execute(new AbstractNonDirtyingCommand() {

      public void run() {
        ViewpointSelectionCallback viewpointSelectionCallback = new ViewpointSelectionCallback();
        // Activate all viewpoints
        for (Viewpoint viewpoint : viewpoints_p) {
          viewpointSelectionCallback.selectViewpoint(viewpoint, session_p, new NullProgressMonitor());
        }
      }
    });
  }
}
