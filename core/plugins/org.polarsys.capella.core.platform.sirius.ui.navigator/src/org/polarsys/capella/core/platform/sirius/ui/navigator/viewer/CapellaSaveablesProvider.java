/*******************************************************************************
 * Copyright (c) 2006, 2017 THALES GLOBAL SERVICES.
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

package org.polarsys.capella.core.platform.sirius.ui.navigator.viewer;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionListener;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.business.api.session.SessionManagerListener;
import org.eclipse.sirius.ui.business.api.session.IEditingSession;
import org.eclipse.sirius.ui.business.api.session.SessionUIManager;
import org.eclipse.sirius.viewpoint.description.Viewpoint;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.ISaveablesSource;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.Saveable;
import org.eclipse.ui.navigator.SaveablesProvider;
import org.polarsys.capella.core.model.handler.command.CapellaResourceHelper;
import org.polarsys.capella.core.platform.sirius.ui.navigator.Messages;
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
  public Object[] getElements(Saveable saveable) {
    Object[] result = new Object[1];
    result[0] = saveable.getAdapter(Session.class);
    return result;
  }

  /**
   * @see org.eclipse.ui.navigator.SaveablesProvider#getSaveable(java.lang.Object)
   */
  @Override
  public Saveable getSaveable(Object element) {
    Saveable result = null;
    // Since Session is not displayed, the displayed element representing the saveable is delegated to the aird file.
    if ((element instanceof IFile) && ((IFile) element).getFileExtension().equals(CapellaResourceHelper.AIRD_FILE_EXTENSION)) {
      synchronized (_saveables) {
        result = _saveables.get(SessionHelper.getSession((IFile) element));
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
  @Override
  public void notifyAddSession(Session newSession) {
	// Instead of manually creating the saveables, rely on the UISession to provide the saveables.
	IEditingSession uiSession = SessionUIManager.INSTANCE.getOrCreateUISession(newSession);
	if(uiSession instanceof ISaveablesSource){
		ISaveablesSource saveablesProvider = (ISaveablesSource) uiSession;
		Saveable[] saveables = saveablesProvider.getSaveables();
		if(saveables.length == 1){
			synchronized (_saveables) {
				_saveables.put(newSession, saveables[0]);
			}
		} else{
		    // ISaveableSource returned 0 or more than 1 saveables.
			throw new IllegalArgumentException(String.format(Messages.CapellaSaveablesProvider_IEditingSessionRetrieval_WrongNumberOfSaveables, ISaveablesSource.class.getSimpleName(), uiSession.getClass().getSimpleName(), Saveable.class.getSimpleName(), _saveables.size()));
		}
	} else{
		// IEditingSession instance does not implement ISaveablesSource.
		throw new ClassCastException(String.format(Messages.CapellaSaveablesProvider_IEditingSessionRetrieval_ShouldAlsoImplementISaveablesSource, IEditingSession.class.getSimpleName(), ISaveablesSource.class.getSimpleName()));
	}
  }

  /**
   * @see org.eclipse.sirius.business.api.session.SessionManagerListener#notifyRemoveSession(org.eclipse.sirius.business.api.session.Session)
   */
  @Override
  public void notifyRemoveSession(Session removedSession) {
    synchronized (_saveables) {
      // Remove the saveable for removed session.
      _saveables.remove(removedSession);
    }
  }

  /**
   * @see org.eclipse.sirius.business.api.session.SessionManagerListener#notify(org.eclipse.sirius.business.api.session.Session, int)
   */
  @Override
  public void notify(Session updatedSession, int notification) {
    final Saveable[] saveable = { null };
    synchronized (_saveables) {
      saveable[0] = _saveables.get(updatedSession);
    }
    // Ensure in multi-threading context, the saveable is correctly found.
    if (null == saveable[0]) {
      return;
    }
    Runnable runnable = null;
    switch (notification) {
      case SessionListener.SYNC:
      case SessionListener.DIRTY:
        runnable = new Runnable() {
          @Override
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
          @Override
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
          @Override
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
          @Override
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
  public void notifyUpdatedSession(Session updated) {
    // Do nothing.
  }

  /**
   * @see org.eclipse.sirius.business.api.session.SessionManagerListener#viewpointDeselected(org.eclipse.sirius.description.Viewpoint)
   */
  @Override
  public void viewpointDeselected(Viewpoint deselectedViewpoint) {
    // Do nothing.
  }

  /**
   * @see org.eclipse.sirius.business.api.session.SessionManagerListener#viewpointSelected(org.eclipse.sirius.description.Viewpoint)
   */
  @Override
  public void viewpointSelected(Viewpoint selectedViewpoint) {
    // Do nothing.
  }
}
