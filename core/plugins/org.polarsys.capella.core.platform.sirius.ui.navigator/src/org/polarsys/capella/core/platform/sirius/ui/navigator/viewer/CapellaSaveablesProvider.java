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
import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionListener;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.business.api.session.SessionManagerListener;
import org.eclipse.sirius.viewpoint.description.Viewpoint;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.Saveable;
import org.eclipse.ui.navigator.SaveablesProvider;
import org.polarsys.capella.core.model.handler.command.CapellaResourceHelper;
import org.polarsys.capella.core.sirius.ui.helper.SessionHelper;

/**
 * The Capella saveables provider.
 */
public class CapellaSaveablesProvider extends SaveablesProvider implements SessionManagerListener {
  /**
   * Mapping between session and its saveable.
   */
  private Map<Session, Saveable> _saveables;

  /**
   * Constructor.
   */
  public CapellaSaveablesProvider() {
    _saveables = new HashMap<Session, Saveable>(0);
  }

  /**
   * @see org.eclipse.ui.navigator.SaveablesProvider#doInit()
   */
  @Override
  protected void doInit() {
    // Move session listener registration to this methods according to SaveablesProvider life's cycle.
    // Add this listener to the session manager.
    SessionManager.INSTANCE.addSessionsListener(this);

    // Add existing active Session in the Capella saveable provider.
    // Must be performed before CNF initialization to have the right saveables computation at CNF initialization.
    // One use case is to re-open capella with a previous model opened.
    for (Session activeSession : SessionManager.INSTANCE.getSessions()) {
      notifyAddSession(activeSession);
    }
  }

  /**
   * Dispose registered session listeners.
   * @see org.eclipse.ui.navigator.SaveablesProvider#dispose()
   */
  @Override
  public void dispose() {
    // Dispose method can be called in different ways.
    // Make sure this listener is removed before internal resources are disposed to avoid session notifications.
    SessionManager.INSTANCE.removeSessionsListener(this);
    synchronized (_saveables) {
      _saveables.clear();
    }
    // DO NOT set to null the _saveables map because dispose is called at different moments in SaveablesProvider life's cycle.
    super.dispose();
  }

  /**
   * @see org.eclipse.ui.navigator.SaveablesProvider#getElements(org.eclipse.ui.Saveable)
   */
  @Override
  public Object[] getElements(Saveable saveable_p) {
    Object[] result = new Object[1];
    result[0] = saveable_p.getAdapter(Session.class);
    return result;
  }

  /**
   * @see org.eclipse.ui.navigator.SaveablesProvider#getSaveable(java.lang.Object)
   */
  @Override
  public Saveable getSaveable(Object element_p) {
    Saveable result = null;
    // Since Session is not displayed, the displayed element representing the saveable is delegated to the aird file.
    if ((element_p instanceof IFile) && ((IFile) element_p).getFileExtension().equals(CapellaResourceHelper.AIRD_FILE_EXTENSION)) {
      synchronized (_saveables) {
        result = _saveables.get(SessionHelper.getSession((IFile) element_p));
      }
    }
    return result;
  }

  /**
   * @see org.eclipse.ui.navigator.SaveablesProvider#getSaveables()
   */
  @Override
  public Saveable[] getSaveables() {
    Saveable[] result = null;
    synchronized (_saveables) {
      Collection<Saveable> saveables = _saveables.values();
      result = saveables.toArray(new Saveable[saveables.size()]);
    }
    return result;
  }

  /**
   * @see org.eclipse.sirius.business.api.session.SessionManagerListener#notifyAddSession(org.eclipse.sirius.business.api.session.Session)
   */
  public void notifyAddSession(Session newSession_p) {
    // Create a new saveable for this new session.
    CapellaSaveable saveable = new CapellaSaveable(newSession_p);
    synchronized (_saveables) {
      _saveables.put(newSession_p, saveable);
    }
  }

  /**
   * @see org.eclipse.sirius.business.api.session.SessionManagerListener#notifyRemoveSession(org.eclipse.sirius.business.api.session.Session)
   */
  public void notifyRemoveSession(Session removedSession_p) {
    synchronized (_saveables) {
      // Remove the saveable for removed session.
      _saveables.remove(removedSession_p);
    }
  }

  /**
   * @see org.eclipse.sirius.business.api.session.SessionManagerListener#notify(org.eclipse.sirius.business.api.session.Session, int)
   */
  public void notify(Session updatedSession_p, int notification_p) {
    final Saveable[] saveable = { null };
    synchronized (_saveables) {
      saveable[0] = _saveables.get(updatedSession_p);
    }
    // Ensure in multi-threading context, the saveable is correctly found.
    if (null == saveable[0]) {
      return;
    }
    Runnable runnable = null;
    switch (notification_p) {
      case SessionListener.SYNC:
      case SessionListener.DIRTY:
        runnable = new Runnable() {
          @SuppressWarnings("synthetic-access")
          public void run() {
            if (null != saveable[0]) {
              // Fire a new dirty event.
              fireSaveablesDirtyChanged(new Saveable[] { saveable[0] });
            }
          }
        };
      break;
      case SessionListener.CLOSED:
        runnable = new Runnable() {
          @SuppressWarnings("synthetic-access")
          public void run() {
            if (null != saveable[0]) {
              // Fire a closed event.
              fireSaveablesClosed(new Saveable[] { saveable[0] });
            }
          }
        };
      break;
      case SessionListener.CLOSING:
        runnable = new Runnable() {
          @SuppressWarnings("synthetic-access")
          public void run() {
            if (null != saveable[0]) {
              // Fire a new dirty state.
              fireSaveablesClosing(new Saveable[] { saveable[0] }, false);
            }
          }
        };
      break;
      case SessionListener.OPENED:
        runnable = new Runnable() {
          @SuppressWarnings("synthetic-access")
          public void run() {
            // Fire a new dirty state.
            fireSaveablesOpened(new Saveable[] { saveable[0] });
          }
        };
      break;
      default:
        // Do nothing.
    }
    // If a runnable is created, execute it.
    if (null != runnable) {
      if (null != Display.getCurrent()) {
        // We are already in the UI thread.
        runnable.run();
      } else {
        // Execute the runnable in the UI thread.
        PlatformUI.getWorkbench().getDisplay().asyncExec(runnable);
      }
    }
  }

  /**
   * @see org.eclipse.sirius.business.api.session.SessionManagerListener#notifyUpdatedSession(org.eclipse.sirius.business.api.session.Session)
   */
  public void notifyUpdatedSession(Session updated_p) {
    // Do nothing.
  }

  /**
   * @see org.eclipse.sirius.business.api.session.SessionManagerListener#viewpointDeselected(org.eclipse.sirius.description.Viewpoint)
   */
  public void viewpointDeselected(Viewpoint deselectedViewpoint_p) {
    // Do nothing.
  }

  /**
   * @see org.eclipse.sirius.business.api.session.SessionManagerListener#viewpointSelected(org.eclipse.sirius.description.Viewpoint)
   */
  public void viewpointSelected(Viewpoint selectedViewpoint_p) {
    // Do nothing.
  }
}
