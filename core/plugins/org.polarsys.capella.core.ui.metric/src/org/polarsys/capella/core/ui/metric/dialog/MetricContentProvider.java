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
package org.polarsys.capella.core.ui.metric.dialog;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

import org.polarsys.capella.core.ui.metric.core.MetricTree;

/**
 *
 */
public class MetricContentProvider implements ITreeContentProvider {

  /**
   * @see org.eclipse.jface.viewers.ITreeContentProvider#getChildren(java.lang.Object)
   */
  @SuppressWarnings("unchecked")
  public Object[] getChildren(Object parentElement_p) {
    
    return 
      hasChildren(parentElement_p) ?
         ((MetricTree<EObject>) parentElement_p).getChildren().toArray():
         null
    ;
  }

  /**
   * @see org.eclipse.jface.viewers.ITreeContentProvider#getParent(java.lang.Object)
   */
  @SuppressWarnings("unchecked")
  public Object getParent(Object element_p) {
    return ((MetricTree<EObject>) element_p).getParent();
  }

  /**
   * @see org.eclipse.jface.viewers.ITreeContentProvider#hasChildren(java.lang.Object)
   */
  @SuppressWarnings("unchecked")
  public boolean hasChildren(Object element_p) {   
    return ((MetricTree<EObject>) element_p).hasChildren();
  }

  /**
   * @see org.eclipse.jface.viewers.IStructuredContentProvider#getElements(java.lang.Object)
   */
  @SuppressWarnings("unchecked")
  public Object[] getElements(Object inputElement_p) {
    return ((MetricTree<EObject>) inputElement_p).getChildren().toArray();
  }

  /**
   * @see org.eclipse.jface.viewers.IContentProvider#dispose()
   */
  public void dispose() {return;}

  /**
   * @see org.eclipse.jface.viewers.IContentProvider#inputChanged(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
   */
  public void inputChanged(Viewer viewer_p, Object oldInput_p, Object newInput_p) {
    return;
  }

}
