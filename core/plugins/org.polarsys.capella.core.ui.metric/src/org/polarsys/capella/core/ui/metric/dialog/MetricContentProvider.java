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

public class MetricContentProvider implements ITreeContentProvider {

  @Override
  @SuppressWarnings("unchecked")
  public Object[] getChildren(Object parentElement) {
    return ((MetricTree<EObject>) parentElement).getChildren().toArray();
  }

  @Override
  @SuppressWarnings("unchecked")
  public Object getParent(Object element) {
    return ((MetricTree<EObject>) element).getParent();
  }

  @Override
  @SuppressWarnings("unchecked")
  public boolean hasChildren(Object element) {
    return ((MetricTree<EObject>) element).hasChildren();
  }

  @Override
  @SuppressWarnings("unchecked")
  public Object[] getElements(Object inputElement) {
    return ((MetricTree<EObject>) inputElement).getChildren().toArray();
  }

  @Override
  public void dispose() {
    return;
  }

  @Override
  public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
    return;
  }
}
