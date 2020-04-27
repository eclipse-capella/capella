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

package org.polarsys.capella.common.ui.toolkit.viewers;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;

/**
 * Base class to implement content provider.<br>
 * This implementation handles input changes.<br>
 * Algorithm of the inputChanged method : <br>
 * 
 * <pre>
 * if (null == oldInput) &amp;&amp; (null != newInput) then we call handleElementAdded method with newInput as argument.
 * if (null != oldInput) &amp;&amp; (null == newInput) then we call handleElementRemoved method with oldInput as argument.
 * if (newInput == oldInput) &amp;&amp; (null != newInput) then we call handleElementUpdated method with both inputs as arguments.
 * </pre>
 * 
 */
public abstract class AbstractStructuredContentProvider implements IStructuredContentProvider {
  /**
   * @see org.eclipse.jface.viewers.IContentProvider#inputChanged(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
   */
  public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
    handleViewer(viewer);
    // Handle the case : elements are removed.
    if ((null != oldInput) && (null == newInput)) {
      handleElementRemoved(viewer, oldInput);
    }
    // Handle the case : elements are added.
    else if ((null == oldInput) && (null != newInput)) {
      handleElementAdded(viewer, newInput);
    }
    // Handle the case : elements are updated.
    else if ((oldInput == newInput) && (null != newInput)) {
      handleElementUpdated(viewer, oldInput, newInput);
    }
  }

  /**
   * Give an opportunity to handle the viewer when {@link #inputChanged(Viewer, Object, Object)} is called.
   * @param viewer The viewer.
   */
  protected abstract void handleViewer(Viewer viewer);

  /**
   * Called when elements are updated in the viewer.
   * @param viewer The viewer.
   * @param oldInput The old input value.
   * @param newInput The new input value.
   */
  protected abstract void handleElementUpdated(Viewer viewer, Object oldInput, Object newInput);

  /**
   * Called when elements are added in the viewer.
   * @param viewer The viewer.
   * @param addedElements The elements which are added to this viewer.
   */
  protected abstract void handleElementAdded(Viewer viewer, Object addedElements);

  /**
   * Called when elements are removed from the viewer.
   * @param viewer The viewer.
   * @param removedElements The elements which are removed from this viewer.
   */
  protected abstract void handleElementRemoved(Viewer viewer, Object removedElements);
}
