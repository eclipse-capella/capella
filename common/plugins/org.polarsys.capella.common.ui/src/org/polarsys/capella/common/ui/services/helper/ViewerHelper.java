/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
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

package org.polarsys.capella.common.ui.services.helper;

import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.swt.widgets.Control;

/**
 */
public class ViewerHelper {
  /**
   * Refresh in an efficient way the specified viewer completely with information freshly obtained from this viewer's model.
   * @param viewer
   */
  public static void refresh(StructuredViewer viewer) {
    refresh(viewer, true);
  }

  /**
   * Refresh in an efficient way the specified viewer completely with information freshly obtained from this viewer's model.<br>
   * If <code>updateLabels</code> is <code>true</code> then labels for otherwise unaffected elements are updated as well.<br>
   * Otherwise, it assumes labels for existing elements are unchanged, and labels are only obtained as needed (for example, for new elements).
   * <p>
   * Calling <code>refresh(viewer, true)</code> has the same effect as <code>refresh(viewer)</code>.
   * <p>
   * Note that the implementation may still obtain labels for existing elements even if <code>updateLabels</code> is false. The intent is simply to allow
   * optimization where possible.
   * @param updateLabels <code>true</code> to update labels for existing elements, <code>false</code> to only update labels as needed, assuming that labels for
   *          existing elements are unchanged.
   * @param viewer
   * @param updateLabels
   */
  public static void refresh(final StructuredViewer viewer, final boolean updateLabels) {
    run(viewer, new Runnable() {
      /**
       * {@inheritDoc}
       */
      public void run() {
        viewer.refresh(updateLabels);
      }
    });
  }

  /**
   * Efficient way to refresh a viewer starting with the given element.
   * @param viewer
   * @param element
   * @see StructuredViewer#refresh(Object)
   */
  public static void refresh(final StructuredViewer viewer, final Object element) {
    run(viewer, new Runnable() {
      /**
       * {@inheritDoc}
       */
      public void run() {
        viewer.refresh(element);
      }
    });
  }

  /**
   * Run a {@link Runnable} in an efficient way operation against specified viewer.
   * @param viewer
   * @param runnable
   */
  public static void run(StructuredViewer viewer, Runnable runnable) {
    // Pre-condition.
    if (null == viewer) {
      return;
    }
    // Get the related control.
    Control control = viewer.getControl();
    try {
      // Switch off redraw
      if (!control.isDisposed()) {
        control.setRedraw(false);
      }
      runnable.run();
    } finally {
      // Finally, redraw again.
      if (!control.isDisposed()) {
        control.setRedraw(true);
      }
    }
  }
}
