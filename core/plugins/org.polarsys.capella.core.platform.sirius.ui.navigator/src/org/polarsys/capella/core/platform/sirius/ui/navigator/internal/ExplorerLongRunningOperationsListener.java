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
package org.polarsys.capella.core.platform.sirius.ui.navigator.internal;

import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IViewReference;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.PlatformUI;
import org.polarsys.capella.common.helpers.operations.ILongRunningListener;
import org.polarsys.capella.core.model.handler.command.BasicCapellaDeleteCommand;
import org.polarsys.capella.core.platform.sirius.ui.navigator.view.CapellaCommonNavigator;

/**
 * A long running operations listener dedicated to the Capella Explorer.
 */
public class ExplorerLongRunningOperationsListener implements ILongRunningListener {

  /**
   * Runnable to be run in UI Thread to enable/disable notifications on the Project Explorer.
   */
  private class NotificationEnabler implements Runnable {

    private final boolean enableNotifications;

    protected CapellaCommonNavigator view;

    /**
     * @param enable
     */
    public NotificationEnabler(boolean enable) {
      enableNotifications = enable;
    }

    /**
     * @see java.lang.Runnable#run()
     */
    @Override
    public void run() {
      // The getCapellaExplorerView() call must be done in this method (because of threads management)
      view = getCapellaExplorerView();
      if (null != view) {
        if (enableNotifications) {
          // Enable content notifications.
          view.enableContentNotifications();
        } else {
          // Disable content notifications.
          view.disableContentNotifications();
        }
      }
    }
  }

  /**
   * @see org.polarsys.capella.common.helpers.operations.ILongRunningListener#isListenerFor(java.lang.Class)
   */
  @Override
  public boolean isListenerFor(final Class<?> longRunningOperationClass) {
    // Ignore calls coming from the CapellaDeleteCommand.
    if (BasicCapellaDeleteCommand.class.equals(longRunningOperationClass)) {
      return false;
    }
    // Explorer should not be refreshed during other long operations on model.
    return true;
  }

  /**
   * Get CapellaExplorerNavigator (to be executed in UI Thread).
   * @return the CapellaExplorerNavigator or <code>null</code> if it can't be found.
   */
  protected CapellaCommonNavigator getCapellaExplorerView() {
    // Precondition.
    final IWorkbench workbench = PlatformUI.getWorkbench();
    // Head-less mode is unlikely to be satisfying here.
    if (null == workbench) {
      return null;
    }
    // Navigator finder.
    // Get active page view references.
    IViewReference[] viewReferences = workbench.getActiveWorkbenchWindow().getActivePage().getViewReferences();
    for (IViewReference viewReference : viewReferences) {
      if (CapellaCommonNavigator.ID.equals(viewReference.getId())) {
        try {
          return CapellaCommonNavigator.class.cast(viewReference.getView(false));
        } catch (Exception e) {
          // Oups, can't get the navigator.
          // Either it is not open, or it is no longer a navigator implementation.
          // Someone should take care of this one.
          return null;
        }
      }
    }
    return null;
  }

  /**
   * @see org.polarsys.capella.common.helpers.operations.ILongRunningListener#operationAborted(java.lang.Class)
   */
  @Override
  public void operationAborted(final Class<?> operationClass) {
    // Re-enable notifications.
    Runnable enableNotifications = new NotificationEnabler(true);
    // Ensure execution in UI thread.
    if (null == Display.getCurrent()) {
      PlatformUI.getWorkbench().getDisplay().asyncExec(enableNotifications);
    } else {
      // Already in UI thread.
      enableNotifications.run();
    }
  }

  /**
   * @see org.polarsys.capella.common.helpers.operations.ILongRunningListener#operationEnded(java.lang.Class)
   */
  @Override
  public void operationEnded(final Class<?> operationClass) {
    // Re-enable notifications + Do a whole refresh of the explorer.
    Runnable enableNotificationsAndRefresh = new NotificationEnabler(true) {
      @Override
      public void run() {
        super.run();
        if (null != view) {
          // Do a whole refresh.
          view.getCommonViewer().refresh();
        }
      }
    };

    // Ensure execution in UI thread.
    if (null == Display.getCurrent()) {
      PlatformUI.getWorkbench().getDisplay().asyncExec(enableNotificationsAndRefresh);
    } else {
      // Already in UI thread.
      enableNotificationsAndRefresh.run();
    }

  }

  /**
   * @see org.polarsys.capella.common.helpers.operations.ILongRunningListener#operationStarting(java.lang.Class)
   */
  @Override
  public void operationStarting(final Class<?> operationClass) {
    // Disable notifications.
    Runnable disableNotifications = new NotificationEnabler(false);
    // Ensure execution in UI thread.
    if (null == Display.getCurrent()) {
      PlatformUI.getWorkbench().getDisplay().syncExec(disableNotifications);
    } else {
      // Already in UI thread.
      disableNotifications.run();
    }
  }
}
