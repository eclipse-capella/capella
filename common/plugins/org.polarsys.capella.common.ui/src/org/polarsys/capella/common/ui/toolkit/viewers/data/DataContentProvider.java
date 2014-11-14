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
   * @param classFilter_p Class used as filter (if defined) in children /elements computation.
   */
  public DataContentProvider(Class<?> classFilter_p) {
    _classFilter = classFilter_p;
  }

  /**
   * Constructor.
   * @param objectFilter_p EObject used as parent filter in children / elements computations.<br>
   *          All children / elements not contained in their hierarchy by this EObject are filtered out.
   */
  public DataContentProvider(EObject objectFilter_p) {
    _eObjectFilter = objectFilter_p;
  }

  /**
   * @see org.eclipse.jface.viewers.IContentProvider#dispose()
   */
  public void dispose() {
    _dataInput = null;
  }

  /**
   * Filter elements according to EClass filter.
   * @param elements_p
   * @return a not <code>null</code> array.
   */
  protected Object[] filter(Object[] elements_p) {
    Object[] result = elements_p;
    if ((null != _classFilter) || (null != _eObjectFilter)) {
      ArrayList<Object> filteredElements = new ArrayList<Object>(0);
      for (Object object : elements_p) {
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
  public Object[] getChildren(Object element_p) {
    return filter(_dataInput.getChildren(element_p));
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
  public Object[] getElements(Object element_p) {
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
  public Object getParent(Object element_p) {
    return _dataInput.getParent(element_p);
  }

  /**
   * @see org.eclipse.jface.viewers.ITreeContentProvider#hasChildren(java.lang.Object)
   */
  public boolean hasChildren(Object element_p) {
    return getChildren(element_p).length > 0;
  }

  /**
   * @see org.eclipse.jface.viewers.IContentProvider#inputChanged(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
   */
  public void inputChanged(Viewer viewer_p, Object oldInput_p, Object newInput_p) {
    if (null == _viewer) {
      // Initial case.
      if (newInput_p instanceof AbstractData) {
        _viewer = (AbstractTreeViewer) viewer_p;
        _dataInput = (AbstractData) newInput_p;
      }
    } else {
      // Cases due to add / remove operations.
      boolean shouldRefresh = false;
      boolean addedContent = false;
      if (newInput_p instanceof Object[]) {
        // handle Add element in the viewer.
        _dataInput.addAllElements((Object[]) newInput_p);
        shouldRefresh = true;
        addedContent = true;
      } else if (newInput_p instanceof AbstractData) {
        // Handle Tree Mode view change.
        _dataInput = (AbstractData) newInput_p;
      } else if (oldInput_p instanceof Object[]) {
        // Handle remove element in the viewer.
        _dataInput.removeAllElements((Object[]) oldInput_p);
        shouldRefresh = true;
      }
      if (shouldRefresh) {
        _viewer.refresh();
        if (addedContent && isExpandingNewContent()){
          for (Object o : (Object[]) newInput_p){
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
   * @param classFilter_p
   */
  public void setClassFilter(Class<?> classFilter_p) {
    _classFilter = classFilter_p;
  }

  /**
   * Set the EObject used as parent filter in children / elements computations.<br>
   * All children / elements not contained in their hierarchy by this EObject are filtered out.
   * @param objectFilter_p the eObjectFilter to set
   */
  public void setObjectFilter(EObject objectFilter_p) {
    _eObjectFilter = objectFilter_p;
  }
}
