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
package org.polarsys.capella.core.platform.sirius.ui.navigator.internal;

import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IViewReference;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.navigator.CommonNavigator;

import org.polarsys.capella.common.helpers.operations.ILongRunningListener;
import org.polarsys.capella.core.platform.sirius.ui.commands.CapellaDeleteCommand;
import org.polarsys.capella.core.platform.sirius.ui.navigator.view.CapellaCommonNavigator;

/**
 * A long running operations listener dedicated to the capella explorer.
 */
public class ExplorerLongRunningOperationsListener implements ILongRunningListener {
  /**
   * @see org.polarsys.capella.common.helpers.operations.ILongRunningListener#isListenerFor(java.lang.Class)
   */
  public boolean isListenerFor(Class<?> longRunningOperationClass_p) {
    // Explorer should not be refreshed during long operation on model.
    // Handle'em all !
    return true;
  }

  /**
   * Get common viewer.
   * @return
   */
  protected CapellaCommonNavigator getCapellaExplorerView() {
    final CapellaCommonNavigator[] result = new CapellaCommonNavigator[] { null };
    final IWorkbench workbench = PlatformUI.getWorkbench();
    // Precondition.
    // Head-less mode is unlikely to be satisfying here.
    if (null == workbench) {
      return result[0];
    }
    // Navigator finder.
    Runnable navigatorFinder = new Runnable() {
      /**
       * @see java.lang.Runnable#run()
       */
      public void run() {
        // Get active page view references.
        IViewReference[] viewReferences = workbench.getActiveWorkbenchWindow().getActivePage().getViewReferences();
        for (IViewReference viewReference : viewReferences) {
          if (CapellaCommonNavigator.ID.equals(viewReference.getId())) {
            try {
              result[0] = CapellaCommonNavigator.class.cast(viewReference.getView(false));
            } catch (Exception e_p) {
              // Oups, can't get the navigator.
              // Either it is not open, or it is no longer a navigator implementation.
              // Someone should take care of this one.
            }
            // Found it, stop here.
            break;
          }
        }
      }
    };
    // Ensure execution in UI thread.
    if (null == Display.getCurrent()) {
      workbench.getDisplay().syncExec(navigatorFinder);
    } else {
      // Already in UI thread.
      navigatorFinder.run();
    }
    return result[0];
  }

  /**
   * @see org.polarsys.capella.common.helpers.operations.ILongRunningListener#operationAborted(java.lang.Class)
   */
  public void operationAborted(final Class<?> operationClass_p) {
    final CapellaCommonNavigator view = getCapellaExplorerView();
    // Precondition.
    // There is nothing that can be done here.
    if (null == view) {
      return;
    }
    /**
     * Enable redraw runnable.
     */
    Runnable enableRedraw = new Runnable() {
      /**
       * @see java.lang.Runnable#run()
       */
      public void run() {
        // Re-enable content notifications for capella explorer.
        if (!CapellaDeleteCommand.class.equals(operationClass_p)) {
          view.enableContentNotifications();
        }
      }
    };
    // Ensure execution in UI thread.
    if (null == Display.getCurrent()) {
      PlatformUI.getWorkbench().getDisplay().asyncExec(enableRedraw);
    } else {
      // Already in UI thread.
      enableRedraw.run();
    }
  }

  /**
   * @see org.polarsys.capella.common.helpers.operations.ILongRunningListener#operationEnded(java.lang.Class)
   */
  public void operationEnded(Class<?> operationClass_p) {
    // Go for it.
    operationAborted(operationClass_p);
    // Ask for a full refresh of the explorer, if it is not a delete command.
    if (!CapellaDeleteCommand.class.equals(operationClass_p)) {
      final CommonNavigator view = getCapellaExplorerView();

      // Precondition.
      // There is nothing that can be done here.
      if (null == view) {
        return;
      }

      // Refresh the whole tree.
      Runnable refreshTree = new Runnable() {
        /**
         * @see java.lang.Runnable#run()
         */
        public void run() {
          view.getCommonViewer().refresh();
        }
      };

      // Ensure execution in UI thread.
      if (null == Display.getCurrent()) {
        PlatformUI.getWorkbench().getDisplay().asyncExec(refreshTree);
      } else {
        // Already in UI thread.
        refreshTree.run();
      }
    }
  }

  /**
   * @see org.polarsys.capella.common.helpers.operations.ILongRunningListener#operationStarting(java.lang.Class)
   */
  public void operationStarting(final Class<?> operationClass_p) {
    final CapellaCommonNavigator view = getCapellaExplorerView();
    // Precondition.
    // There is nothing that can be done here.
    if (null == view) {
      return;
    }
    /**
     * Disable redraw runnable.
     */
    Runnable disableRedraw = new Runnable() {
      /**
       * @see java.lang.Runnable#run()
       */
      public void run() {
        // Disable content notifications for capella explorer.
        if (!CapellaDeleteCommand.class.equals(operationClass_p)) {
          view.disableContentNotifications();
        }
      }
    };
    // Ensure execution in UI thread.
    if (null == Display.getCurrent()) {
      PlatformUI.getWorkbench().getDisplay().syncExec(disableRedraw);
    } else {
      // Already in UI thread.
      disableRedraw.run();
    }
  }
}
