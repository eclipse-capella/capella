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
   * @param selectionProvider_p
   */
  public DelegateSelectionProvider(ISelectionProvider selectionProvider_p) {
    _delegate = selectionProvider_p;
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
   * @param delegate_p the delegate to set
   */
  public void setDelegate(ISelectionProvider delegate_p) {
    _delegate = delegate_p;
  }

  /**
   * {@inheritDoc}
   */
  public void setSelection(ISelection selection) {
    _delegate.setSelection(selection);
  }
}
