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

package org.polarsys.capella.common.ui.toolkit.viewers.data;

import java.util.ArrayList;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.AbstractTreeViewer;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

import org.polarsys.capella.common.helpers.EcoreUtil2;

/**
 * Data content provider that deals with {@link AbstractData}
 */
public class DataContentProvider implements ITreeContentProvider {
  /**
   * Class used as filter in children /elements computation.
   */
  private Class<?> _classFilter;
  /**
   * Handle data input.
   */
  private AbstractData _dataInput;
  /**
   * EObject used as parent filter in children / elements computations.<br>
   * All children / elements not contained in their hierarchy by this EObject are filtered out.
   */
  private EObject _eObjectFilter;

  /**
   * Viewer.
   */
  private AbstractTreeViewer _viewer;

  /*
   * Should we expand the tree to reveal newly added content?
   */
  private boolean expandAddedContent = false;
  
  /**
   * Constructor.
   */
  public DataContentProvider() {
    this((EObject) null);
  }

  /**
   * Constructor.
   * @param classFilter Class used as filter (if defined) in children /elements computation.
   */
  public DataContentProvider(Class<?> classFilter) {
    _classFilter = classFilter;
  }

  /**
   * Constructor.
   * @param objectFilter EObject used as parent filter in children / elements computations.<br>
   *          All children / elements not contained in their hierarchy by this EObject are filtered out.
   */
  public DataContentProvider(EObject objectFilter) {
    _eObjectFilter = objectFilter;
  }

  /**
   * @see org.eclipse.jface.viewers.IContentProvider#dispose()
   */
  public void dispose() {
    _dataInput = null;
  }

  /**
   * Filter elements according to EClass filter.
   * @param elements
   * @return a not <code>null</code> array.
   */
  protected Object[] filter(Object[] elements) {
    Object[] result = elements;
    if ((null != _classFilter) || (null != _eObjectFilter)) {
      ArrayList<Object> filteredElements = new ArrayList<Object>(0);
      for (Object object : elements) {
        if (object instanceof EObject) {
          if (EcoreUtil2.isContainedBy((EObject) object, _eObjectFilter)) {
            filteredElements.add(object);
          }
        }
      }
      result = filteredElements.toArray();
    }
    return result;
  }

  /**
   * @see org.eclipse.jface.viewers.ITreeContentProvider#getChildren(java.lang.Object)
   */
  public Object[] getChildren(Object element) {
    return filter(_dataInput.getChildren(element));
  }

  /**
   * Get Class used as filter (if defined) in children /elements computation.
   * @return the childrenEClassFilter
   */
  public Class<?> getClassFilter() {
    return _classFilter;
  }

  /**
   * @see org.eclipse.jface.viewers.IStructuredContentProvider#getElements(java.lang.Object)
   */
  public Object[] getElements(Object element) {
    return filter(_dataInput.getElements());
  }

  /**
   * Get the EObject used as parent filter in children / elements computations.<br>
   * All children / elements not contained in their hierarchy by this EObject are filtered out.
   * @return the eObjectFilter
   */
  public EObject getObjectFilter() {
    return _eObjectFilter;
  }

  /**
   * @see org.eclipse.jface.viewers.ITreeContentProvider#getParent(java.lang.Object)
   */
  public Object getParent(Object element) {
    return _dataInput.getParent(element);
  }

  /**
   * @see org.eclipse.jface.viewers.ITreeContentProvider#hasChildren(java.lang.Object)
   */
  public boolean hasChildren(Object element) {
    return getChildren(element).length > 0;
  }

  /**
   * @see org.eclipse.jface.viewers.IContentProvider#inputChanged(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
   */
  public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
    if (null == _viewer) {
      // Initial case.
      if (newInput instanceof AbstractData) {
        _viewer = (AbstractTreeViewer) viewer;
        _dataInput = (AbstractData) newInput;
      }
    } else {
      // Cases due to add / remove operations.
      boolean shouldRefresh = false;
      boolean addedContent = false;
      if (newInput instanceof Object[]) {
        // handle Add element in the viewer.
        _dataInput.addAllElements((Object[]) newInput);
        shouldRefresh = true;
        addedContent = true;
      } else if (newInput instanceof AbstractData) {
        // Handle Tree Mode view change.
        _dataInput = (AbstractData) newInput;
      } else if (oldInput instanceof Object[]) {
        // Handle remove element in the viewer.
        _dataInput.removeAllElements((Object[]) oldInput);
        shouldRefresh = true;
      }
      if (shouldRefresh) {
        _viewer.refresh();
        if (addedContent && isExpandingNewContent()){
          for (Object o : (Object[]) newInput){
            _viewer.expandToLevel(o, 0);
          }
        }
        _dataInput.notifyValidElementsChanges();
      }
    }
  }

  /**
   * Should newly added content be revealed by expanding
   * the tree to the required level? For compatibility
   * reasons, this value is false by default. Use
   * setExpandingNewContent() to true to enable this 
   * feature.
   */
  public boolean isExpandingNewContent() {
    return expandAddedContent;
  }
  
  /**
   * Control whether newly added content will be revealed
   * by expanding the tree to the required level.
   * 
   * @param b
   */
  public void setExpandingNewContent(boolean b){
    expandAddedContent = b;
  }

  /**
   * Set Class used as filter (if defined) in children /elements computation.
   * @param classFilter
   */
  public void setClassFilter(Class<?> classFilter) {
    _classFilter = classFilter;
  }

  /**
   * Set the EObject used as parent filter in children / elements computations.<br>
   * All children / elements not contained in their hierarchy by this EObject are filtered out.
   * @param objectFilter the eObjectFilter to set
   */
  public void setObjectFilter(EObject objectFilter) {
    _eObjectFilter = objectFilter;
  }
}
