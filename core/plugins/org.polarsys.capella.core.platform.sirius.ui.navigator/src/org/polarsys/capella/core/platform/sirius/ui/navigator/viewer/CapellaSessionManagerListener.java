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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionListener;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.business.api.session.SessionManagerListener2;
import org.eclipse.sirius.business.api.session.danalysis.DAnalysisSession;
import org.eclipse.sirius.viewpoint.description.Viewpoint;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.navigator.CommonViewer;
import org.polarsys.capella.common.ui.services.helper.ViewerHelper;
import org.polarsys.capella.common.mdsofa.common.helper.ExtensionPointHelper;
import org.polarsys.capella.core.data.capellamodeller.ModelRoot;
import org.polarsys.capella.core.data.capellamodeller.Project;
import org.polarsys.capella.core.platform.sirius.ui.navigator.CapellaNavigatorPlugin;
import org.polarsys.capella.core.platform.sirius.ui.navigator.view.CapellaCommonNavigator;
import org.polarsys.capella.core.sirius.ui.helper.SessionHelper;

/**
 * The Capella Session Manager listener.<br>
 * Now, the capella session manager does not handle events to refresh the UI.<br>
 * Its job is to drive session listeners, these ones are notified and refresh the UI.
 */
@SuppressWarnings("deprecation")
public class CapellaSessionManagerListener implements SessionManagerListener2 {
  /**
   * The viewer to refresh.
   */
  private CommonViewer _viewer;
  /**
   * Capella Common Navigator.
   */
  private CapellaCommonNavigator _capellaCommonNavigator;
  /**
   * Capella Common Navigator session listeners.
   */
  private List<ISessionListener> _sessionListeners;

  private volatile boolean _sessionListenersDetected;

  /**
   * Constructs the Capella session listener.
   * @param viewer_p The viewer.
   * @param capellaCommonNavigator
   */
  public CapellaSessionManagerListener(CommonViewer viewer_p, CapellaCommonNavigator capellaCommonNavigator) {
    _viewer = viewer_p;
    _capellaCommonNavigator = capellaCommonNavigator;
    // Add this listener to the session manager.
    SessionManager.INSTANCE.addSessionsListener(this);
    _sessionListenersDetected = false;
  }

  /**
   * Load the unique semantic editing domain providers.
   */
  private void loadSessionListeners() {
    if (!_sessionListenersDetected) {
      _sessionListenersDetected = true;
      // Load session listeners if any.
      IConfigurationElement[] configurationElements =
          ExtensionPointHelper.getConfigurationElements(CapellaNavigatorPlugin.getDefault().getPluginId(), "sessionListener"); //$NON-NLS-1$
      // Loop over contributed session listeners.
      if (configurationElements.length > 0) {
        _sessionListeners = new ArrayList<ISessionListener>(0);
        for (IConfigurationElement configurationElement : configurationElements) {
          _sessionListeners.add((ISessionListener) ExtensionPointHelper.createInstance(configurationElement, ExtensionPointHelper.ATT_CLASS));
        }
      }
    }
  }

  /**
   * Dispose
   */
  public void dispose() {
    // Dispose method can be called in different ways.
    // Make sure this listener is removed before internal resources are disposed to avoid session notifications.
    SessionManager.INSTANCE.removeSessionsListener(this);
    _viewer = null;
    if (null != _sessionListeners) {
      _sessionListeners.clear();
    }
  }

  /**
   * @see org.eclipse.sirius.business.api.session.SessionManagerListener#notifyAddSession(org.eclipse.sirius.business.api.session.Session)
   */
  public void notifyAddSession(final Session newSession_p) {
    // Do nothing.
  }

  /**
   * @see org.eclipse.sirius.business.api.session.SessionManagerListener#notifyRemoveSession(org.eclipse.sirius.business.api.session.Session)
   */
  public void notifyRemoveSession(final Session removedSession_p) {
    if (SessionManager.INSTANCE.getSessions().isEmpty()) {
      PlatformUI.getWorkbench().getDisplay().asyncExec(new Runnable() {
        @SuppressWarnings("synthetic-access")
        public void run() {
          // Force to free memory.
          if ((null != _viewer) && (!_viewer.getControl().isDisposed())) {
            _viewer.getNavigatorContentService().getActivationService()
                .deactivateExtensions(new String[] { CapellaNavigatorContentProvider.CONTENT_EXTENSION_ID }, true);
            _viewer.getNavigatorContentService().getActivationService()
                .activateExtensions(new String[] { CapellaNavigatorContentProvider.CONTENT_EXTENSION_ID }, false);
          }
        }
      });
    }
  }

  /**
   * Refresh the viewer for alive sessions.<br>
   * Must be called within UI thread.
   * @param updatedSession_p
   */
  protected void refreshViewer(Session updatedSession_p) {
    if ((null != _viewer) && (!_viewer.getControl().isDisposed())) {
      final IFile parent = SessionHelper.getFirstAnalysisFile((DAnalysisSession) updatedSession_p);
      // Avoid reentrant refresh. No reproduction use case.
      if (!_viewer.isBusy() && !_viewer.getControl().isDisposed()) {
        ViewerHelper.refresh(_viewer, parent);
      }
    }
  }

  /**
   * Handle closed session event.<br>
   * Must be called within UI thread.
   * @param updatedSession_p
   */
  protected void handleClosed(Session updatedSession_p) {
    if ((null != _viewer) && (!_viewer.getControl().isDisposed())) {
      // Make sure the viewer has no selection to avoid memory leak.
      _viewer.setSelection(StructuredSelection.EMPTY);
      ViewerHelper.refresh(_viewer);
    }
  }

  /**
   * Handle opened session event.<br>
   * Must be called within UI thread.
   * @param updatedSession_p
   */
  @SuppressWarnings("synthetic-access")
  protected void handleOpened(Session updatedSession_p) {
    // At open time, we want to expand the semantic model to level 1.
    if ((null != _viewer) && (!_viewer.getControl().isDisposed())) {
      final IFile parent = SessionHelper.getFirstAnalysisFile((DAnalysisSession) updatedSession_p);
      ViewerHelper.refresh(_viewer, parent);
      // Make sure aird file is visible (necessary when creating capella project)
      // Test on parent to avoid NPE when exporting diagram images. It seems that Sirius opens a new session without a project.
      if ((null != parent) && !_viewer.getExpandedState(parent)) {
        ViewerHelper.run(_viewer, new Runnable() {
          public void run() {
            _viewer.expandToLevel(parent, 1);
          }
        });
      }
      // Get the semantic resource registered for current session.
      Iterator<Resource> iterator = updatedSession_p.getSemanticResources().iterator();
      if (iterator.hasNext()) {
        Resource semanticResource = iterator.next();
        // Make sure the first capella model is visible.
        EObject rootObject = semanticResource.getContents().get(0);
        final Object[] expandedObject = new Object[] { rootObject };
        if (rootObject instanceof Project) {
          Project root = (Project) rootObject;
          List<ModelRoot> modelRoots = root.getOwnedModelRoots();
          if (!modelRoots.isEmpty()) {
            expandedObject[0] = modelRoots.get(0);
          }
        }
        ViewerHelper.run(_viewer, new Runnable() {
          public void run() {
            _viewer.expandToLevel(expandedObject[0], 1);
          }
        });
      }
    }
  }

  /**
   * Forward session notification to contributed session listener if any.
   * @param session_p
   * @param notification_p
   */
  protected void notifySessionListeners(Session session_p, int notification_p) {
    loadSessionListeners();
    // Preconditions
    if ((null == _sessionListeners) || _sessionListeners.isEmpty()) {
      return;
    }
    for (ISessionListener currentSessionListener : _sessionListeners) {
      switch (notification_p) {
        case SessionListener.OPENING:
          currentSessionListener.sessionOpening(session_p);
        break;
        case SessionListener.OPENED:
          currentSessionListener.sessionOpened(session_p);
        break;
        case SessionListener.CLOSING:
          currentSessionListener.sessionClosing(session_p);
        break;
        case SessionListener.CLOSED:
          currentSessionListener.sessionClosed(session_p);
        break;
      }
    }
  }

  /**
   * {@inheritDoc}
   */
  public void notify(final Session updatedSession_p, final int notification_p) {
    switch (notification_p) {
      case SessionListener.OPENING:
        _capellaCommonNavigator.disableContentNotifications();
      break;
      case SessionListener.OPENED:
        _capellaCommonNavigator.enableContentNotifications();
        // Update the common viewer to force it to get session children.
        PlatformUI.getWorkbench().getDisplay().asyncExec(new Runnable() {
          public void run() {
            handleOpened(updatedSession_p);
          }
        });
      break;
      case SessionListener.REPLACED:
      case SessionListener.REPRESENTATION_CHANGE:
      case SessionListener.SEMANTIC_CHANGE:
        PlatformUI.getWorkbench().getDisplay().asyncExec(new Runnable() {
          public void run() {
            refreshViewer(updatedSession_p);
          }
        });
      break;
      case SessionListener.CLOSING:
        _capellaCommonNavigator.disableContentNotifications();
      break;
      case SessionListener.CLOSED:
        _capellaCommonNavigator.enableContentNotifications();
        PlatformUI.getWorkbench().getDisplay().asyncExec(new Runnable() {
          public void run() {
            handleClosed(updatedSession_p);
          }
        });
      break;
    }
    // Notify contributed session listeners.
    notifySessionListeners(updatedSession_p, notification_p);
  }

  /**
   * @see org.eclipse.sirius.business.api.session.SessionManagerListener#notifyUpdatedSession(org.eclipse.sirius.business.api.session.Session)
   */
  public void notifyUpdatedSession(final Session updatedSession_p) {
    fakeRepresentationChange(updatedSession_p);
  }

  /**
   * @see org.eclipse.sirius.business.api.session.SessionManagerListener#viewpointDeselected(org.eclipse.sirius.description.Viewpoint)
   */
  public void viewpointDeselected(final Viewpoint deselectedViewpoint_p) {
    doUpdateViewpoint();
  }

  /**
   * @see org.eclipse.sirius.business.api.session.SessionManagerListener#viewpointSelected(org.eclipse.sirius.description.Viewpoint)
   */
  public void viewpointSelected(final Viewpoint selectedViewpoint_p) {
    doUpdateViewpoint();
  }

  /**
   * Refresh the viewer due to viewpoint activation changes.
   */
  protected void doUpdateViewpoint() {
    Runnable runnable = new Runnable() {
      @SuppressWarnings("synthetic-access")
      public void run() {
        // Refreshes the session structure.
        ISelection selection = _viewer.getSelection();
        if (selection instanceof IStructuredSelection) {
          Object firstElement = ((IStructuredSelection) selection).getFirstElement();
          Session session = null;
          if (firstElement instanceof Session) {
            session = (Session) firstElement;
          } else if (firstElement instanceof EObject) {
            session = SessionManager.INSTANCE.getSession((EObject) firstElement);
          }
          if (null != session) {
            fakeRepresentationChange(session);
          }
        }
      }
    };
    // Ensure running in UI Thread.
    if (null != Display.getCurrent()) {
      runnable.run();
    } else {
      Control control = _viewer.getControl();
      if (!control.isDisposed()) {
        control.getDisplay().asyncExec(runnable);
      }
    }
  }

  /**
   * Fake a representation change for given session.
   * @param session_p
   */
  private void fakeRepresentationChange(Session session_p) {
    // Precondition.
    if (null == session_p) {
      return;
    }
    // Should be not null, forward the notification to the session listener.
    // Fake a representation change.
    notify(session_p, SessionListener.REPRESENTATION_CHANGE);
  }
}
