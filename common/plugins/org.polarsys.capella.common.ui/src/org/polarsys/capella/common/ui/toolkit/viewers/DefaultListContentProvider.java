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

import java.util.Collection;

import org.eclipse.jface.viewers.AbstractListViewer;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;

/**
 * The default list content provider.
 */
public class DefaultListContentProvider extends AbstractStructuredContentProvider {
  /**
   * Constructs the default list content provider.
   */
  public DefaultListContentProvider() {
    // Do nothing.
  }

  /**
   * @see org.polarsys.capella.common.ui.toolkit.viewers.AbstractStructuredContentProvider#handleElementAdded(org.eclipse.jface.viewers.Viewer, java.lang.Object)
   */
  @Override
  protected void handleElementAdded(Viewer viewer, Object addedElements) {
    AbstractListViewer abstractListViewer = (AbstractListViewer) viewer;
    if (addedElements instanceof Object[]) {
      Object[] array = (Object[]) addedElements;
      abstractListViewer.add(array);
    } else if (addedElements instanceof Collection<?>) {
      Collection<?> collection = (Collection<?>) addedElements;
      Object[] array = collection.toArray();
      abstractListViewer.add(array);
    } else {
      abstractListViewer.add(addedElements);
    }
  }

  /**
   * @see org.polarsys.capella.common.ui.toolkit.viewers.AbstractStructuredContentProvider#handleElementRemoved(org.eclipse.jface.viewers.Viewer,
   *      java.lang.Object)
   */
  @Override
  protected void handleElementRemoved(Viewer viewer, Object removedElements) {
    AbstractListViewer abstractListViewer = (AbstractListViewer) viewer;
    if (removedElements instanceof Object[]) {
      Object[] array = (Object[]) removedElements;
      abstractListViewer.remove(array);
    } else if (removedElements instanceof Collection<?>) {
      Collection<?> collection = (Collection<?>) removedElements;
      Object[] array = collection.toArray();
      abstractListViewer.remove(array);
    } else {
      abstractListViewer.remove(removedElements);
    }
  }

  /**
   * @see org.polarsys.capella.common.ui.toolkit.viewers.AbstractStructuredContentProvider#handleElementUpdated(org.eclipse.jface.viewers.Viewer,
   *      java.lang.Object, java.lang.Object)
   */
  @Override
  protected void handleElementUpdated(Viewer viewer, Object oldInput, Object newInput) {
    ((AbstractListViewer) viewer).refresh(oldInput);
  }

  /**
   * @see org.polarsys.capella.common.ui.toolkit.viewers.AbstractStructuredContentProvider#handleViewer(org.eclipse.jface.viewers.Viewer)
   */
  @Override
  protected void handleViewer(Viewer viewer) {
    if (!(viewer instanceof AbstractListViewer)) {
      throw new IllegalArgumentException("Viewer must be an instance of AbstractListViewer"); //$NON-NLS-1$
    }
  }

  /**
   * @see IStructuredContentProvider#getElements(Object)
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
    // Do nothing.
  }
}
