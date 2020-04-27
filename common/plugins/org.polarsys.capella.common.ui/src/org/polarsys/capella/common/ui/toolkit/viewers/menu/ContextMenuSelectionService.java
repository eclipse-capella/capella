/*******************************************************************************
 * Copyright (c) 2006, 2018 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.common.ui.toolkit.viewers.menu;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.runtime.ListenerList;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.ui.AbstractSourceProvider;
import org.eclipse.ui.INullSelectionListener;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.ISelectionService;
import org.eclipse.ui.ISources;

/**
 * Abstract selection service.
 */
public class ContextMenuSelectionService extends AbstractSourceProvider implements ISelectionChangedListener, ISelectionService {

  public ContextMenuSelectionService() {
  }

  /**
   * Creates a new SelectionService.
   */
  public ContextMenuSelectionService(ISelectionProvider activeProvider) {
    this.activeProvider = activeProvider;
    if (activeProvider != null) {
      activeProvider.addSelectionChangedListener(this);
    }
  }

  /** 
   * The list of selection listeners (not per-part).
   */
  private ListenerList listeners = new ListenerList();

  /** 
   * The list of post selection listeners (not per-part).
   */
  private ListenerList postListeners = new ListenerList();

  private ISelectionProvider activeProvider;

  /**
   * The JFace selection listener to hook on the active part's selection provider.
   */
  private ISelectionChangedListener selListener = new ISelectionChangedListener() {
    public void selectionChanged(SelectionChangedEvent event) {
      fireSelection(event.getSelection());
    }
  };

  /**
   * The JFace post selection listener to hook on the active part's selection provider.
   */
  private ISelectionChangedListener postSelListener = new ISelectionChangedListener() {
    public void selectionChanged(SelectionChangedEvent event) {
      firePostSelection(event.getSelection());
    }
  };

  /* (non-Javadoc)
   * Method declared on ISelectionService.
   */
  public void addSelectionListener(ISelectionListener l) {
    listeners.add(l);
  }

  /* (non-Javadoc)
   * Method declared on ISelectionService.
   */
  public void addSelectionListener(String partId, ISelectionListener listener) {
    // getPerPartTracker(partId).addSelectionListener(listener);
  }

  /* (non-Javadoc)
   * Method declared on ISelectionService.
   */
  public void addPostSelectionListener(ISelectionListener l) {
    postListeners.add(l);
  }

  /* (non-Javadoc)
   * Method declared on ISelectionService.
   */
  public void addPostSelectionListener(String partId, ISelectionListener listener) {
    //getPerPartTracker(partId).addPostSelectionListener(listener);
  }

  /* (non-Javadoc)
   * Method declared on ISelectionService.
   */
  public void removeSelectionListener(ISelectionListener l) {
    listeners.remove(l);
  }

  /*
   * (non-Javadoc)
   * Method declared on ISelectionListener.
   */
  public void removePostSelectionListener(String partId, ISelectionListener listener) {
    //getPerPartTracker(partId).removePostSelectionListener(listener);
  }

  /* (non-Javadoc)
   * Method declared on ISelectionService.
   */
  public void removePostSelectionListener(ISelectionListener l) {
    postListeners.remove(l);
  }

  /*
   * (non-Javadoc)
   * Method declared on ISelectionListener.
   */
  public void removeSelectionListener(String partId, ISelectionListener listener) {
    //getPerPartTracker(partId).removeSelectionListener(listener);
  }

  /**
   * Fires a selection event to the given listeners.
   * 
   * @param part the part or <code>null</code> if no active part
   * @param sel the selection or <code>null</code> if no active selection
   */
  protected void fireSelection(final ISelection sel) {
    Object[] array = listeners.getListeners();
    for (Object element : array) {
      final ISelectionListener l = (ISelectionListener) element;
      if (((sel != null)) || (l instanceof INullSelectionListener)) {

        try {
          l.selectionChanged(null, sel);
        } catch (Exception e) {
          //Nothing here
        }
      }
    }
  }

  /**
   * Fires a selection event to the given listeners.
   * 
   * @param part the part or <code>null</code> if no active part
   * @param sel the selection or <code>null</code> if no active selection
   */
  public void firePostSelection(final ISelection sel) {
    Object[] array = postListeners.getListeners();
    for (Object element : array) {
      final ISelectionListener l = (ISelectionListener) element;
      if (((sel != null)) || (l instanceof INullSelectionListener)) {
        try {
          l.selectionChanged(null, sel);
        } catch (Exception e) {
          //Nothing here
        }
      }
    }
  }

  /**
   * Returns the selection.
   */
  public ISelection getSelection() {
    if (activeProvider != null) {
      return activeProvider.getSelection();
    } else {
      return null;
    }
  }

  /*
   * @see ISelectionService#getSelection(String)
   */
  public ISelection getSelection(String partId) {
    return getSelection();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void selectionChanged(SelectionChangedEvent event) {
    fireSourceChanged(11, ISources.ACTIVE_CURRENT_SELECTION_NAME, event.getSelection());
    fireSelection(event.getSelection());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Map getCurrentState() {
    HashMap<String, Object> map = new HashMap<String, Object>();
    map.put(ISources.ACTIVE_CURRENT_SELECTION_NAME, getSelection());
    return map;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String[] getProvidedSourceNames() {
    return new String[] { ISources.ACTIVE_CURRENT_SELECTION_NAME };
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void dispose() {

  }
}
