/*******************************************************************************
 * Copyright (c) 2017 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.libraries.ui.move;

import java.util.Collection;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;

/**
 * Filters a tree so that its only leafs are the elements of the given collection,
 * or all children of the elements of the given collection, depending on a flag.
 */
public class CollectionTreeFilter extends ViewerFilter {

  final Collection<?> c;
  boolean filterChildren;

  public CollectionTreeFilter(Collection<?> c, boolean filterChildren) {
    this.c = c;
    this.filterChildren = filterChildren;
  };

  @Override
  public boolean select(Viewer viewer, Object parentElement, Object element) {

    ITreeContentProvider provider = (ITreeContentProvider) ((TreeViewer) viewer).getContentProvider();

    if (!filterChildren && EcoreUtil.isAncestor(c, (EObject) element)){
      return true;
    }

    for (Object e : c) {

      Object current = e;
      while (current != null){
        if (current == element){
          return true;
        }
        current = provider.getParent(current);
      }
    }
    return false;
  }

  @Override
  public boolean isFilterProperty(Object element, String property) {
    return true;
  }

}
