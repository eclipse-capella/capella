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
   * 
   * @param capellaCommonNavigator
   */
  public NavigatorSessionManagerListener(CapellaCommonNavigator capellaCommonNavigator) {
    _capellaCommonNavigator = capellaCommonNavigator;
    // Add this listener to the session manager.
    SessionManager.INSTANCE.addSessionsListener(this);

    Collection<Session> activeSessions = SessionManager.INSTANCE.getSessions();
    // Add existing active Session in the saveable provider.
    for (Session activeSession : activeSessions) {
      notify(activeSession, SessionListener.OPENED); /*
                                                      * Use to expand the tree viewer
                                                      */
    }
  }

  /**
   * Dispose
   */
  public void dispose() {
    // Dispose method can be called in different ways.
    // Make sure this listener is removed before internal resources are
    // disposed to avoid session notifications.
    SessionManager.INSTANCE.removeSessionsListener(this);
    _capellaCommonNavigator = null;
  }

  /**
   * @see org.eclipse.sirius.business.api.session.SessionManagerListener#notifyRemoveSession(org.eclipse.sirius.business.api.session.Session)
   */
  @Override
  public void notifyRemoveSession(final Session removedSession) {
    if (SessionManager.INSTANCE.getSessions().isEmpty()) {
      PlatformUI.getWorkbench().getDisplay().asyncExec(new Runnable() {
        @Override
        @SuppressWarnings("synthetic-access")
        public void run() {
          if (_capellaCommonNavigator != null) {
            CommonViewer viewer = _capellaCommonNavigator.getCommonViewer();
            // Force to free memory.
            if ((null != viewer) && (!viewer.getControl().isDisposed())) {
              viewer.getNavigatorContentService().getActivationService()
                  .deactivateExtensions(new String[] { CapellaNavigatorContentProvider.CONTENT_EXTENSION_ID }, true);
              viewer.getNavigatorContentService().getActivationService()
                  .activateExtensions(new String[] { CapellaNavigatorContentProvider.CONTENT_EXTENSION_ID }, false);
            }
          }
        }
      });
    }
  }

  /**
   * Refresh the viewer for alive sessions.<br>
   * Must be called within UI thread.
   * 
   * @implSpec _capellaCommonNavigator must be not null
   * @param updatedSession
   */
  protected void refreshViewer(Session updatedSession) {
    CommonViewer viewer = _capellaCommonNavigator.getCommonViewer();
    if ((null != viewer) && (!viewer.getControl().isDisposed()) && !viewer.isBusy() && updatedSession.isOpen()
        && ActiveSessionManager.getInstance()
            .isEnabledContentNotifications(updatedSession.getTransactionalEditingDomain())) {
      // Avoid reentrant refresh. No reproduction use case.
      refreshItem(updatedSession, true);
    }
  }

  /**
   * Refresh the viewer for alive sessions.<br>
   * Must be called within UI thread.
   * 
   * @param updatedSession
   */
  @Deprecated
  protected void refreshItem(final Session updatedSession) {
    refreshItem(updatedSession, false);
  }

  /**
   * @implSpec _capellaCommonNavigator must be not null
   */
  protected void refreshItem(final Session updatedSession, boolean contentRefresh) {
    // we manually trigger a refresh if we are not inside a transaction.
    // (ContentProvider is a ResourceSetListenerImpl and is automatically
    // triggered at postCommit)
    // Precondition: ignore refresh on a closed Session.
    if (!updatedSession.isOpen()) {
      return;
    }
    boolean triggerRefresh = (((TransactionalEditingDomainImpl) updatedSession.getTransactionalEditingDomain())
        .getActiveTransaction() == null);
    refreshItem(updatedSession, contentRefresh, triggerRefresh);
  }

  /**
   * Refresh the viewer for alive sessions.<br>
   * Must be called within UI thread.
   * 
   * @implSpec _capellaCommonNavigator must be not null
   * @param updatedSession
   */
  protected void refreshItem(final Session updatedSession, boolean contentRefresh, boolean triggerRefresh) {

    final IFile file = EcoreUtil2.getFile(updatedSession.getSessionResource());

    Notification notification = new NotificationImpl(Notification.SET, null, file) {
      /**
       * @see org.eclipse.emf.common.notify.impl.NotificationImpl#getNotifier()
       */
      @Override
      public Object getNotifier() {
        return file;
      }
    };

    _capellaCommonNavigator.getContentProvider()
        .notifyChanged(new ViewerNotification(notification, file, contentRefresh, true));
    if (triggerRefresh) {
      _capellaCommonNavigator.getContentProvider().runRefresh();
    }
  }

  /**
   * Handle closed session event.<br>
   * Must be called within UI thread.
   * 
   * @implSpec _capellaCommonNavigator must be not null
   * @param updatedSession
   */
  protected void handleClosed(Session updatedSession) {
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
   * 
   * @implSpec _capellaCommonNavigator must be not null
   * @param updatedSession
   */
  protected void handleOpened(Session updatedSession) {
    final CommonViewer viewer = _capellaCommonNavigator.getCommonViewer();
    // At open time, we want to expand the semantic model to level 1.
    if ((null != viewer) && (!viewer.getControl().isDisposed()) && (ActiveSessionManager.getInstance()
        .isEnabledContentNotifications(updatedSession.getTransactionalEditingDomain()))) {
      final IFile parent = SessionHelper.getFirstAnalysisFile((DAnalysisSession) updatedSession);
      ViewerHelper.refresh(viewer, parent);
      // Make sure aird file is visible (necessary when creating capella
      // project)
      // Test on parent to avoid NPE when exporting diagram images. It
      // seems that Sirius opens a new session without a project.
      if ((null != parent) && !viewer.getExpandedState(parent)) {
        ViewerHelper.run(viewer, new Runnable() {
          @Override
          public void run() {
            viewer.expandToLevel(parent, 1);
          }
        });
      }
      // Get the semantic resource registered for current session.
      Iterator<Resource> iterator = updatedSession.getSemanticResources().iterator();
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
          @Override
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
  public void notify(final Session updatedSession, final int notification) {
    final SemanticEditingDomain domain = (SemanticEditingDomain) updatedSession.getTransactionalEditingDomain();

    switch (notification) {
    case SessionListener.OPENING:
    case SessionListener.CLOSING:
      if (_capellaCommonNavigator != null) {
        _capellaCommonNavigator.disableContentNotifications(domain);
      }
      break;

    case SessionListener.OPENED:
      // Update the common viewer to force it to get session children.
      PlatformUI.getWorkbench().getDisplay().asyncExec(new Runnable() {
        @Override
        public void run() {
          if (_capellaCommonNavigator != null) {
            _capellaCommonNavigator.enableContentNotifications(domain);
            handleOpened(updatedSession);
          }
        }
      });
      break;
    case SessionListener.SYNC:
      PlatformUI.getWorkbench().getDisplay().asyncExec(new Runnable() {
        @Override
        public void run() {
          if (_capellaCommonNavigator != null) {
            refreshViewer(updatedSession);
          }
        }
      });
      break;
    case SessionListener.DIRTY:
      PlatformUI.getWorkbench().getDisplay().asyncExec(new Runnable() {
        @Override
        public void run() {
          if (_capellaCommonNavigator != null) {
            refreshItem(updatedSession, false);
          }
        }
      });
      break;

    case SessionListener.ABOUT_TO_BE_REPLACED:
      if (_capellaCommonNavigator != null) {
        _capellaCommonNavigator.disableContentNotifications(domain);
      }
      break;

    case SessionListener.REPLACED:
      PlatformUI.getWorkbench().getDisplay().asyncExec(new Runnable() {
        @Override
        public void run() {
          if (_capellaCommonNavigator != null) {
            _capellaCommonNavigator.enableContentNotifications(domain);
            refreshViewer(updatedSession);
          }
        }
      });
      break;
    case SessionListener.REPRESENTATION_CHANGE:
      PlatformUI.getWorkbench().getDisplay().asyncExec(new Runnable() {
        @Override
        public void run() {
          if (_capellaCommonNavigator != null) {
            refreshViewer(updatedSession);
          }
        }
      });
      break;
    case SessionListener.SEMANTIC_CHANGE:
      PlatformUI.getWorkbench().getDisplay().asyncExec(new Runnable() {
        @Override
        public void run() {
          if (_capellaCommonNavigator != null) {
            refreshViewer(updatedSession);
          }
        }
      });
      break;

    case SessionListener.CLOSED:
      PlatformUI.getWorkbench().getDisplay().asyncExec(new Runnable() {
        @Override
        public void run() {
          if (_capellaCommonNavigator != null) {
            _capellaCommonNavigator.enableContentNotifications(domain);
          }
          ActiveSessionManager.getInstance().remove(domain);
          if (_capellaCommonNavigator != null) {
            handleClosed(updatedSession);
          }
        }
      });
      break;
    }
  }

  /**
   * @see org.eclipse.sirius.business.api.session.SessionManagerListener#notifyUpdatedSession(org.eclipse.sirius.business.api.session.Session)
   */
  public void notifyUpdatedSession(final Session updatedSession) {
    fakeRepresentationChange(updatedSession);
  }

  /**
   * @see org.eclipse.sirius.business.api.session.SessionManagerListener#viewpointDeselected(org.eclipse.sirius.description.Viewpoint)
   */
  @Override
  public void viewpointDeselected(final Viewpoint deselectedViewpoint) {
    if (_capellaCommonNavigator != null) {
      doUpdateViewpoint();
    }
  }

  /**
   * @see org.eclipse.sirius.business.api.session.SessionManagerListener#viewpointSelected(org.eclipse.sirius.description.Viewpoint)
   */
  @Override
  public void viewpointSelected(final Viewpoint selectedViewpoint) {
    if (_capellaCommonNavigator != null) {
      doUpdateViewpoint();
    }
  }

  /**
   * Refresh the viewer due to viewpoint activation changes.
   * 
   * @implSpec _capellaCommonNavigator must be not null
   */
  protected void doUpdateViewpoint() {
    final CommonViewer viewer = _capellaCommonNavigator.getCommonViewer();
    Runnable runnable = new Runnable() {
      @Override
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
   * 
   * @param session
   */
  private void fakeRepresentationChange(Session session) {
    // Precondition.
    if (null == session) {
      return;
    }
    // Should be not null, forward the notification to the session listener.
    // Fake a representation change.
    notify(session, SessionListener.REPRESENTATION_CHANGE);
  }
}
