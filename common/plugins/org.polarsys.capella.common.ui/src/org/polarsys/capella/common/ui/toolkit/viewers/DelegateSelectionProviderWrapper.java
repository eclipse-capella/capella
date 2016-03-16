/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.common.ui.toolkit.viewers;

import java.util.List;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ISelectionProvider;

/**
 * Delegate Selection Provider.
 */
public class DelegateSelectionProviderWrapper implements ISelectionProvider {
  private List<ISelectionProvider> _delegates;

  private ISelectionProvider _activeDelegate;

  /**
   * Constructor.
   * 
   * @param selectionProvider_p
   */
  public DelegateSelectionProviderWrapper(List<ISelectionProvider> selectionProviders) {
    _delegates = selectionProviders;
  }

  /**
   * {@inheritDoc}
   */
  public void addSelectionChangedListener(ISelectionChangedListener listener) {
    for (ISelectionProvider _delegate : _delegates) {
      _delegate.addSelectionChangedListener(listener);
    }
  }

  /**
   * Get the delegate selection provider.
   * 
   * @return a not <code>null</code> object.
   */
  public ISelectionProvider getActiveDelegate() {
    return _activeDelegate;
  }

  /**
   * {@inheritDoc}
   */
  public ISelection getSelection() {
    return _activeDelegate.getSelection();
  }

  /**
   * {@inheritDoc}
   */
  public void removeSelectionChangedListener(ISelectionChangedListener listener) {
    for (ISelectionProvider _delegate : _delegates) {
      _delegate.removeSelectionChangedListener(listener);
    }
  }

  /**
   * Set the delegate
   * 
   * @param delegate_p
   *          the delegate to set
   */
  public void setDelegates(List<ISelectionProvider> delegates) {
    _delegates = delegates;
  }

  public void setActiveDelegate(ISelectionProvider delegates) {
    _activeDelegate = delegates;
  }

  /**
   * {@inheritDoc}
   */
  public void setSelection(ISelection selection) {
    _activeDelegate.setSelection(selection);
  }
}
