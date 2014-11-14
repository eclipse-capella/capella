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

import java.util.Collection;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;

/**
 */
public class DefaultTreeContentProvider extends AbstractStructuredContentProvider implements ITreeContentProvider {

  /**
   * @see org.polarsys.capella.common.ui.toolkit.viewers.AbstractStructuredContentProvider#handleElementAdded(org.eclipse.jface.viewers.Viewer, java.lang.Object)
   */
  @Override
  protected void handleElementAdded(Viewer viewer_p, Object addedElements_p) {
    TreeViewer treeViewer = (TreeViewer) viewer_p;
    if (addedElements_p instanceof Object[]) {
      Object[] array = (Object[]) addedElements_p;
      for (Object arr : array) {
        treeViewer.add(getParent(arr), arr);
      }
    } else if (addedElements_p instanceof Collection<?>) {
      Collection<?> collection = (Collection<?>) addedElements_p;
      for (Object elt : collection) {
        treeViewer.add(getParent(elt), elt);
      }
    }
  }

  /**
   * @see org.polarsys.capella.common.ui.toolkit.viewers.AbstractStructuredContentProvider#handleElementRemoved(org.eclipse.jface.viewers.Viewer,
   *      java.lang.Object)
   */
  @Override
  protected void handleElementRemoved(Viewer viewer_p, Object removedElements_p) {
    TreeViewer treeViewer = (TreeViewer) viewer_p;
    if (removedElements_p instanceof Object[]) {
      Object[] array = (Object[]) removedElements_p;
      treeViewer.remove(array);
    } else if (removedElements_p instanceof Collection<?>) {
      Collection<?> collection = (Collection<?>) removedElements_p;
      treeViewer.remove(collection.toArray());
    } else {
      treeViewer.remove(removedElements_p);
    }
  }

  /**
   * @see org.polarsys.capella.common.ui.toolkit.viewers.AbstractStructuredContentProvider#handleElementUpdated(org.eclipse.jface.viewers.Viewer,
   *      java.lang.Object, java.lang.Object)
   */
  @Override
  protected void handleElementUpdated(Viewer viewer_p, Object oldInput_p, Object newInput_p) {
    ((TreeViewer) viewer_p).refresh(newInput_p);
  }

  /**
   * @see org.polarsys.capella.common.ui.toolkit.viewers.AbstractStructuredContentProvider#handleViewer(org.eclipse.jface.viewers.Viewer)
   */
  @Override
  protected void handleViewer(Viewer viewer_p) {
    if (!(viewer_p instanceof TreeViewer)) {
      throw new IllegalArgumentException("Viewer must be an instance of TreeViewer"); //$NON-NLS-1$
    }

  }

  /**
   * @see org.eclipse.jface.viewers.IStructuredContentProvider#getElements(java.lang.Object)
   */
  public Object[] getElements(Object inputElement_p) {
    if (inputElement_p instanceof Object[]) {
      return (Object[]) inputElement_p;
    }
    if (inputElement_p instanceof Collection<?>) {
      return ((Collection<?>) inputElement_p).toArray();
    }
    return new Object[0];
  }

  /**
   * @see org.eclipse.jface.viewers.IContentProvider#dispose()
   */
  public void dispose() {
    // do nothing
  }

  /**
   * @see org.eclipse.jface.viewers.ITreeContentProvider#getChildren(java.lang.Object)
   */
  public Object[] getChildren(Object parentElement_p) {
    return null;
  }

  /**
   * @see org.eclipse.jface.viewers.ITreeContentProvider#getParent(java.lang.Object)
   */
  public Object getParent(Object element_p) {
    return null;
  }

  /**
   * @see org.eclipse.jface.viewers.ITreeContentProvider#hasChildren(java.lang.Object)
   */
  public boolean hasChildren(Object element_p) {
    return false;
  }
}
