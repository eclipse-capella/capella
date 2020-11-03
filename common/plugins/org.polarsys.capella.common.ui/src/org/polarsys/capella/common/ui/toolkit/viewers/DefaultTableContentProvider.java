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

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.Viewer;

/**
 * The default table content provider.
 */
public class DefaultTableContentProvider extends AbstractStructuredContentProvider {

  /**
   * Constructs the default table content provider.
   */
  public DefaultTableContentProvider() {
    // Do nothing.
  }

  /**
   * @see org.polarsys.capella.common.ui.toolkit.viewers.AbstractStructuredContentProvider#handleElementAdded(org.eclipse.jface.viewers.Viewer, java.lang.Object)
   */
  @Override
  protected void handleElementAdded(Viewer viewer, Object addedElements) {
    TableViewer tableViewer = (TableViewer) viewer;
    if (addedElements instanceof Object[]) {
      Object[] array = (Object[]) addedElements;
      tableViewer.add(array);
    } else if (addedElements instanceof Collection<?>) {
      Collection<?> collection = (Collection<?>) addedElements;
      tableViewer.add(collection.toArray());
    } else {
      tableViewer.add(addedElements);
    }
  }

  /**
   * @see org.polarsys.capella.common.ui.toolkit.viewers.AbstractStructuredContentProvider#handleElementRemoved(org.eclipse.jface.viewers.Viewer,
   *      java.lang.Object)
   */
  @Override
  protected void handleElementRemoved(Viewer viewer, Object removedElements) {
    TableViewer tableViewer = (TableViewer) viewer;
    if (removedElements instanceof Object[]) {
      Object[] array = (Object[]) removedElements;
      tableViewer.remove(array);
    } else if (removedElements instanceof Collection<?>) {
      Collection<?> collection = (Collection<?>) removedElements;
      tableViewer.remove(collection.toArray());
    } else {
      tableViewer.remove(removedElements);
    }
  }

  /**
   * @see org.polarsys.capella.common.ui.toolkit.viewers.AbstractStructuredContentProvider#handleElementUpdated(org.eclipse.jface.viewers.Viewer,
   *      java.lang.Object, java.lang.Object)
   */
  @Override
  protected void handleElementUpdated(Viewer viewer, Object oldInput, Object newInput) {
    ((TableViewer) viewer).refresh(newInput);
  }

  /**
   * @see org.polarsys.capella.common.ui.toolkit.viewers.AbstractStructuredContentProvider#handleViewer(org.eclipse.jface.viewers.Viewer)
   */
  @Override
  protected void handleViewer(Viewer viewer) {
    if (!(viewer instanceof TableViewer)) {
      throw new IllegalArgumentException("Viewer must be an instance of TableViewer"); //$NON-NLS-1$
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
