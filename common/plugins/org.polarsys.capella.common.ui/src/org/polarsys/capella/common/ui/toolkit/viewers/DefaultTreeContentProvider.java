/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
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
  protected void handleElementAdded(Viewer viewer, Object addedElements) {
    TreeViewer treeViewer = (TreeViewer) viewer;
    if (addedElements instanceof Object[]) {
      Object[] array = (Object[]) addedElements;
      for (Object arr : array) {
        treeViewer.add(getParent(arr), arr);
      }
    } else if (addedElements instanceof Collection<?>) {
      Collection<?> collection = (Collection<?>) addedElements;
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
  protected void handleElementRemoved(Viewer viewer, Object removedElements) {
    TreeViewer treeViewer = (TreeViewer) viewer;
    if (removedElements instanceof Object[]) {
      Object[] array = (Object[]) removedElements;
      treeViewer.remove(array);
    } else if (removedElements instanceof Collection<?>) {
      Collection<?> collection = (Collection<?>) removedElements;
      treeViewer.remove(collection.toArray());
    } else {
      treeViewer.remove(removedElements);
    }
  }

  /**
   * @see org.polarsys.capella.common.ui.toolkit.viewers.AbstractStructuredContentProvider#handleElementUpdated(org.eclipse.jface.viewers.Viewer,
   *      java.lang.Object, java.lang.Object)
   */
  @Override
  protected void handleElementUpdated(Viewer viewer, Object oldInput, Object newInput) {
    ((TreeViewer) viewer).refresh(newInput);
  }

  /**
   * @see org.polarsys.capella.common.ui.toolkit.viewers.AbstractStructuredContentProvider#handleViewer(org.eclipse.jface.viewers.Viewer)
   */
  @Override
  protected void handleViewer(Viewer viewer) {
    if (!(viewer instanceof TreeViewer)) {
      throw new IllegalArgumentException("Viewer must be an instance of TreeViewer"); //$NON-NLS-1$
    }

  }

  /**
   * @see org.eclipse.jface.viewers.IStructuredContentProvider#getElements(java.lang.Object)
   */
  public Object[] getElements(Object inputElement) {
    if (inputElement instanceof Object[]) {
      return (Object[]) inputElement;
    }
    if (inputElement instanceof Collection<?>) {
      return ((Collection<?>) inputElement).toArray();
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
  public Object[] getChildren(Object parentElement) {
    return null;
  }

  /**
   * @see org.eclipse.jface.viewers.ITreeContentProvider#getParent(java.lang.Object)
   */
  public Object getParent(Object element) {
    return null;
  }

  /**
   * @see org.eclipse.jface.viewers.ITreeContentProvider#hasChildren(java.lang.Object)
   */
  public boolean hasChildren(Object element) {
    return false;
  }
}
