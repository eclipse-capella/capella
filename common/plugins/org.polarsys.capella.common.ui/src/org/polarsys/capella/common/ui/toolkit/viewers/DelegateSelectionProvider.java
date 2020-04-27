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

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ISelectionProvider;

/**
 * Delegate Selection Provider.
 */
public class DelegateSelectionProvider implements ISelectionProvider {
  private ISelectionProvider _delegate;

  /**
   * Constructor.
   * @param selectionProvider
   */
  public DelegateSelectionProvider(ISelectionProvider selectionProvider) {
    _delegate = selectionProvider;
  }

  /**
   * {@inheritDoc}
   */
  public void addSelectionChangedListener(ISelectionChangedListener listener) {
    _delegate.addSelectionChangedListener(listener);

  }

  /**
   * Get the delegate selection provider.
   * @return a not <code>null</code> object.
   */
  public ISelectionProvider getDelegate() {
    return _delegate;
  }

  /**
   * {@inheritDoc}
   */
  public ISelection getSelection() {
    return _delegate.getSelection();
  }

  /**
   * {@inheritDoc}
   */
  public void removeSelectionChangedListener(ISelectionChangedListener listener) {
    _delegate.removeSelectionChangedListener(listener);
  }

  /**
   * Set the delegate
   * @param delegate the delegate to set
   */
  public void setDelegate(ISelectionProvider delegate) {
    _delegate = delegate;
  }

  /**
   * {@inheritDoc}
   */
  public void setSelection(ISelection selection) {
    _delegate.setSelection(selection);
  }
}
