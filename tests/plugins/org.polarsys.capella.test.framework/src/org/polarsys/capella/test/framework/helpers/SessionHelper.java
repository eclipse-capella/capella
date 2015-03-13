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
package org.polarsys.capella.test.framework.helpers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.ui.URIEditorInput;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.business.api.session.danalysis.DAnalysisSession;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.polarsys.capella.common.ef.command.AbstractCommand;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;
import org.polarsys.capella.core.sirius.ui.actions.OpenSessionAction;
import org.polarsys.capella.test.framework.actions.gui.GuiActions;
import org.polarsys.capella.test.framework.actions.headless.HeadlessCloseSessionAction;
import org.polarsys.capella.test.framework.internal.helpers.CloseSessionHelper;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;

/**
 * Utility tools for session
 */
public class SessionHelper {

  /**
   * Save a given session
   * @param session_p the target {@link Session}
   */
  public static void saveSession(final Session session_p) {

    final AbstractCommand cmd = new AbstractReadWriteCommand() {
      public void run() {
        session_p.save(new NullProgressMonitor());
      }
    };

    // Let's perform the job
    TestHelper.getExecutionManager(session_p).execute(cmd);
    GuiActions.flushASyncGuiThread();
  }

  /**
   * Close a given session
   * @param session_p the target {@link Session}
   * @param save_p should we save the session before to close it?
   */
  public static void closeSession(final Session session_p, final boolean save_p) {

    final CloseSessionHelper csh = new CloseSessionHelper(session_p, save_p);
    if (Display.getCurrent() != null) {
      csh.run();
    } else {
      PlatformUI.getWorkbench().getDisplay().syncExec(new Runnable() {
        @Override
        public void run() {
          csh.run();
        }
      });
    }
    GuiActions.flushASyncGuiThread();
    return;
  }

  public static void headlessCloseSession(final Session session_p, final boolean save_p) {
     List<Session> sessionsToClose = Collections.singletonList(session_p);
     final HeadlessCloseSessionAction closeSessionAction = new HeadlessCloseSessionAction(sessionsToClose);
     if (save_p) {
        session_p.save(new NullProgressMonitor());
     }
     closeSessionAction.run();
  }

	  
  /**
   * Closes the session for the given URI, optionally saving the session.
   * @param airdURI
   */
  public static void closeSession(URI airdURI, final boolean save) {
    final Session session = SessionManager.INSTANCE.getExistingSession(airdURI);
    if ((session != null) && session.isOpen()) {
      closeSession(session, save);
    }
  }

  /**
   * Apply a function on all available sessions that match a given predicate.
   * @param function
   * @param predicate
   * @return
   */
  public static <T> Collection<T> processAllSessions(Function<Session, ? extends T> function, Predicate<Session> predicate) {
    Collection<T> result = new ArrayList<T>();
    for (Session s : new ArrayList<Session>(SessionManager.INSTANCE.getSessions())) {
      if (predicate.apply(s)) {
        result.add(function.apply(s));
      }
    }
    return result;
  }

  /**
   * Close and optionally save all sessions for which the given predicate holds.
   * @param predicate
   * @param save
   */
  public static void closeAllSessions(Predicate<Session> predicate, final boolean save) {
    Function<Session, Object> clsfun = new Function<Session, Object>() {
      @Override
      public Object apply(Session arg0_p) {
        closeSession(arg0_p, save);
        return null;
      }
    };
    processAllSessions(clsfun, predicate);
  }

  /**
   * Close and optionally save all sessions.
   * @param save
   */
  public static void closeAllSessions(boolean save) {
    closeAllSessions(Predicates.<Session> alwaysTrue(), save);
  }

  /**
   * Close all editors connected with this Session
   * @param session_p : the target session
   * @param save_p : editors should be saved?
   */
  public static void closeEditors(final Session session_p, final boolean save_p) {
    // FIXME CloseEditorsCommand has changed. Please adapt the code
    Assert.isNotNull(session_p);

    IWorkbenchPage iwp = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();

    // Editor references to close.
    List<IEditorReference> editorReferencesToClose = new ArrayList<IEditorReference>(0);

    for (IEditorReference editorReference : iwp.getEditorReferences()) {
      IEditorInput editorReferenceInput;
      try {
        editorReferenceInput = editorReference.getEditorInput();
        if (session_p instanceof DAnalysisSession) { // should be, just in case of
          // Get the analysis resources.
          Collection<Resource> analysisResources = getAllAirdResources(session_p);
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
      } catch (PartInitException e) {
        // Do nothing
      }
    }

    if (!editorReferencesToClose.isEmpty()) {
      iwp.closeEditors(editorReferencesToClose.toArray(new IEditorReference[editorReferencesToClose.size()]), save_p);
    }
    GuiActions.flushASyncGuiThread();
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

  public static void openSession(final URI airdURI) {
    final Runnable runnable = new Runnable() {
      @Override
      public void run() {
        OpenSessionAction oa = new OpenSessionAction();
        IResource resource = ResourcesPlugin.getWorkspace().getRoot().findMember(new Path(airdURI.toPlatformString(true)));
        oa.selectionChanged(new StructuredSelection(resource));
        oa.run();
        IStatus failed = oa.getFailedOpeningSessions().get(resource);
        if (failed != null) {
          throw new RuntimeException(failed.getMessage(), failed.getException());
        }
      }
    };
    PlatformUI.getWorkbench().getDisplay().syncExec(runnable);
    GuiActions.flushASyncGuiThread();
  }

}
