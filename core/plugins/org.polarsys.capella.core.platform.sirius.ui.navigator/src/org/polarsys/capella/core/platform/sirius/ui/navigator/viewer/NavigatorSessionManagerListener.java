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

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.NotificationImpl;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.provider.ViewerNotification;
import org.eclipse.emf.transaction.impl.TransactionalEditingDomainImpl;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionListener;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.business.api.session.SessionManagerListener;
import org.eclipse.sirius.business.api.session.danalysis.DAnalysisSession;
import org.eclipse.sirius.viewpoint.description.Viewpoint;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.navigator.CommonViewer;
import org.polarsys.capella.common.helpers.EcoreUtil2;
import org.polarsys.capella.common.platform.sirius.ted.SemanticEditingDomainFactory.SemanticEditingDomain;
import org.polarsys.capella.common.ui.services.helper.ViewerHelper;
import org.polarsys.capella.core.data.capellamodeller.ModelRoot;
import org.polarsys.capella.core.data.capellamodeller.Project;
import org.polarsys.capella.core.platform.sirius.ui.navigator.view.CapellaCommonNavigator;
import org.polarsys.capella.core.sirius.ui.helper.SessionHelper;

/**
 * The Capella Session Manager listener.<br>
 * Its job is to drive session listeners, these ones are notified and refresh the UI.
 */
public class NavigatorSessionManagerListener extends SessionManagerListener.Stub {

  /**
   * Capella Common Navigator.
   */
  private CapellaCommonNavigator _capellaCommonNavigator;

  /**
   * Constructs the Capella session listener.
   * @param viewer_p The viewer.
   * @param capellaCommonNavigator
   */
  public NavigatorSessionManagerListener(CapellaCommonNavigator capellaCommonNavigator) {
    _capellaCommonNavigator = capellaCommonNavigator;
    // Add this listener to the session manager.
    SessionManager.INSTANCE.addSessionsListener(this);

    Collection<Session> activeSessions = SessionManager.INSTANCE.getSessions();
    // Add existing active Session in the saveable provider.
    for (Session activeSession : activeSessions) {
      notify(activeSession, SessionListener.OPENED); /* Use to expand the tree viewer */
    }
  }

  /**
   * Dispose
   */
  public void dispose() {
    // Dispose method can be called in different ways.
    // Make sure this listener is removed before internal resources are disposed to avoid session notifications.
    SessionManager.INSTANCE.removeSessionsListener(this);
    _capellaCommonNavigator = null;
  }

  /**
   * @see org.eclipse.sirius.business.api.session.SessionManagerListener#notifyRemoveSession(org.eclipse.sirius.business.api.session.Session)
   */
  @Override
  public void notifyRemoveSession(final Session removedSession_p) {
    if (SessionManager.INSTANCE.getSessions().isEmpty()) {
      PlatformUI.getWorkbench().getDisplay().asyncExec(new Runnable() {
        @SuppressWarnings("synthetic-access")
        public void run() {
          CommonViewer viewer = _capellaCommonNavigator.getCommonViewer();
          // Force to free memory.
          if ((null != viewer) && (!viewer.getControl().isDisposed())) {
            viewer.getNavigatorContentService().getActivationService()
                .deactivateExtensions(new String[] { CapellaNavigatorContentProvider.CONTENT_EXTENSION_ID }, true);
            viewer.getNavigatorContentService().getActivationService()
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
    CommonViewer viewer = _capellaCommonNavigator.getCommonViewer();

    if ((null != viewer) && (!viewer.getControl().isDisposed())) {
      if (!viewer.isBusy() && _capellaCommonNavigator.getContentProvider().isEnabledContentNotifications(updatedSession_p.getTransactionalEditingDomain())) {
        // Avoid reentrant refresh. No reproduction use case.
        refreshItem(updatedSession_p, true);
      }
    }
  }

  /**
   * Refresh the viewer for alive sessions.<br>
   * Must be called within UI thread.
   * @param updatedSession_p
   */
  @Deprecated
  protected void refreshItem(final Session updatedSession_p) {
    refreshItem(updatedSession_p, false);
  }

  protected void refreshItem(final Session updatedSession_p, boolean contentRefresh_p) {
    // we manually trigger a refresh if we are not inside a transaction.
    // (ContentProvider is a ResourceSetListenerImpl and is automatically triggered at postCommit)
    boolean triggerRefresh = (((TransactionalEditingDomainImpl) updatedSession_p.getTransactionalEditingDomain()).getActiveTransaction() == null);
    refreshItem(updatedSession_p, contentRefresh_p, triggerRefresh);
  }

  /**
   * Refresh the viewer for alive sessions.<br>
   * Must be called within UI thread.
   * @param updatedSession_p
   */
  protected void refreshItem(final Session updatedSession_p, boolean contentRefresh_p, boolean triggerRefresh_p) {

    final IFile file = EcoreUtil2.getFile(updatedSession_p.getSessionResource());

    Notification notification = new NotificationImpl(Notification.SET, null, file) {
      /**
       * @see org.eclipse.emf.common.notify.impl.NotificationImpl#getNotifier()
       */
      @Override
      public Object getNotifier() {
        return file;
      }
    };

    if (_capellaCommonNavigator != null) {
      _capellaCommonNavigator.getContentProvider().notifyChanged(new ViewerNotification(notification, file, contentRefresh_p, true));
      if (triggerRefresh_p) {
        _capellaCommonNavigator.getContentProvider().runRefresh();
      }
    }
  }

  /**
   * Handle closed session event.<br>
   * Must be called within UI thread.
   * @param updatedSession_p
   */
  protected void handleClosed(Session updatedSession_p) {
    CommonViewer viewer = _capellaCommonNavigator.getCommonViewer();
    if ((null != viewer) && (!viewer.getControl().isDisposed())) {
      // Make sure the viewer has no selection to avoid memory leak.
      viewer.setSelection(StructuredSelection.EMPTY);
      ViewerHelper.refresh(viewer);
    }
  }

  /**
   * Handle opened session event.<br>
   * Must be called within UI thread.
   * @param updatedSession_p
   */
  @SuppressWarnings("synthetic-access")
  protected void handleOpened(Session updatedSession_p) {
    final CommonViewer viewer = _capellaCommonNavigator.getCommonViewer();
    // At open time, we want to expand the semantic model to level 1.
    if ((null != viewer) && (!viewer.getControl().isDisposed())) {
      final IFile parent = SessionHelper.getFirstAnalysisFile((DAnalysisSession) updatedSession_p);
      ViewerHelper.refresh(viewer, parent);
      // Make sure aird file is visible (necessary when creating capella project)
      // Test on parent to avoid NPE when exporting diagram images. It seems that Sirius opens a new session without a project.
      if ((null != parent) && !viewer.getExpandedState(parent)) {
        ViewerHelper.run(viewer, new Runnable() {
          public void run() {
            viewer.expandToLevel(parent, 1);
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
        ViewerHelper.run(viewer, new Runnable() {
          public void run() {
            viewer.expandToLevel(expandedObject[0], 1);
          }
        });
      }
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void notify(final Session updatedSession_p, final int notification_p) {
    final SemanticEditingDomain domain = (SemanticEditingDomain) updatedSession_p.getTransactionalEditingDomain();

    switch (notification_p) {
      case SessionListener.OPENING:
      case SessionListener.CLOSING:
        _capellaCommonNavigator.disableContentNotifications(domain);
      break;

      case SessionListener.OPENED:
        // Update the common viewer to force it to get session children.
        PlatformUI.getWorkbench().getDisplay().asyncExec(new Runnable() {
          public void run() {
            _capellaCommonNavigator.enableContentNotifications(domain);
            handleOpened(updatedSession_p);
          }
        });
      break;
      case SessionListener.SYNC:
        PlatformUI.getWorkbench().getDisplay().asyncExec(new Runnable() {
          public void run() {
            refreshViewer(updatedSession_p);
          }
        });
      break;
      case SessionListener.DIRTY:
        PlatformUI.getWorkbench().getDisplay().asyncExec(new Runnable() {
          public void run() {
            refreshItem(updatedSession_p, false);
          }
        });
      break;

      case SessionListener.ABOUT_TO_BE_REPLACED:
        _capellaCommonNavigator.disableContentNotifications(domain);
      break;

      case SessionListener.REPLACED:
        PlatformUI.getWorkbench().getDisplay().asyncExec(new Runnable() {
          public void run() {
            _capellaCommonNavigator.enableContentNotifications(domain);
            refreshViewer(updatedSession_p);
          }
        });
      break;
      case SessionListener.REPRESENTATION_CHANGE:
        PlatformUI.getWorkbench().getDisplay().asyncExec(new Runnable() {
          public void run() {
            refreshViewer(updatedSession_p);
          }
        });
      break;
      case SessionListener.SEMANTIC_CHANGE:
        PlatformUI.getWorkbench().getDisplay().asyncExec(new Runnable() {
          public void run() {
            refreshViewer(updatedSession_p);
          }
        });
      break;

      case SessionListener.CLOSED:
        PlatformUI.getWorkbench().getDisplay().asyncExec(new Runnable() {
          public void run() {
            _capellaCommonNavigator.enableContentNotifications(domain);
            handleClosed(updatedSession_p);
          }
        });
      break;
    }
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
  @Override
  public void viewpointDeselected(final Viewpoint deselectedViewpoint_p) {
    doUpdateViewpoint();
  }

  /**
   * @see org.eclipse.sirius.business.api.session.SessionManagerListener#viewpointSelected(org.eclipse.sirius.description.Viewpoint)
   */
  @Override
  public void viewpointSelected(final Viewpoint selectedViewpoint_p) {
    doUpdateViewpoint();
  }

  /**
   * Refresh the viewer due to viewpoint activation changes.
   */
  protected void doUpdateViewpoint() {
    final CommonViewer viewer = _capellaCommonNavigator.getCommonViewer();
    Runnable runnable = new Runnable() {
      @SuppressWarnings("synthetic-access")
      public void run() {
        // Refreshes the session structure.
        ISelection selection = viewer.getSelection();
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
      Control control = viewer.getControl();
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
