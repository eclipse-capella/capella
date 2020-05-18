/*******************************************************************************
 * Copyright (c) 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.ui.search.searchfor;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

public abstract class AbstractSearchForContentProvider implements ITreeContentProvider {
  /**
   * {@inheritDoc}
   */
  @Override
  public Object[] getChildren(Object parentElement) {
    return new Object[0];
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Object getParent(Object element) {
    return new Object[0];
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean hasChildren(Object element) {
    return false;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Object[] getElements(Object inputElement) {
    return new Object[0];
  }

  public Object[] getElements() {
    return new Object[0];
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void dispose() {
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
  }
}
