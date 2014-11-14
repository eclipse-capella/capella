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
  public void inputChanged(Viewer viewer_p, Object oldInput_p, Object newInput_p) {
    handleViewer(viewer_p);
    // Handle the case : elements are removed.
    if ((null != oldInput_p) && (null == newInput_p)) {
      handleElementRemoved(viewer_p, oldInput_p);
    }
    // Handle the case : elements are added.
    else if ((null == oldInput_p) && (null != newInput_p)) {
      handleElementAdded(viewer_p, newInput_p);
    }
    // Handle the case : elements are updated.
    else if ((oldInput_p == newInput_p) && (null != newInput_p)) {
      handleElementUpdated(viewer_p, oldInput_p, newInput_p);
    }
  }

  /**
   * Give an opportunity to handle the viewer when {@link #inputChanged(Viewer, Object, Object)} is called.
   * @param viewer_p The viewer.
   */
  protected abstract void handleViewer(Viewer viewer_p);

  /**
   * Called when elements are updated in the viewer.
   * @param viewer_p The viewer.
   * @param oldInput_p The old input value.
   * @param newInput_p The new input value.
   */
  protected abstract void handleElementUpdated(Viewer viewer_p, Object oldInput_p, Object newInput_p);

  /**
   * Called when elements are added in the viewer.
   * @param viewer_p The viewer.
   * @param addedElements_p The elements which are added to this viewer.
   */
  protected abstract void handleElementAdded(Viewer viewer_p, Object addedElements_p);

  /**
   * Called when elements are removed from the viewer.
   * @param viewer_p The viewer.
   * @param removedElements_p The elements which are removed from this viewer.
   */
  protected abstract void handleElementRemoved(Viewer viewer_p, Object removedElements_p);
}
