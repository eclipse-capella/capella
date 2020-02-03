/*******************************************************************************
 * Copyright (c) 2020 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.ui.search;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.resources.IProject;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

public class CapellaSearchResultTreeContentProvider implements ITreeContentProvider {
  private CapellaSearchResult searchResult;

  public CapellaSearchResultTreeContentProvider(CapellaSearchResultPage capellaSearchResultPage) {
    searchResult = capellaSearchResultPage.getInput();
  }
  
  public Object[] getSources(Object[] input) {
    Object[] res = new Object[input.length];
    for(int i = 0; i < input.length; i++) {
      if(input[i] instanceof CapellaSearchMatchEntry) {
        CapellaSearchMatchEntry entry = (CapellaSearchMatchEntry)input[i];
        res[i] = entry.getElement();
      }
      else {
        res[i] = input[i];
      }
    }
    return res;
  }
  /**
   * @see org.eclipse.jface.viewers.ITreeContentProvider#getChildren(java.lang.Object)
   */
  public Object[] getChildren(Object parentElement) {
    if (parentElement instanceof IProject) {
      return getSources(searchResult.getRootResultHierarchies().get((IProject) parentElement).toArray());
    } else if (parentElement instanceof AbstractCapellaSearchEntry) {
      return getSources(((AbstractCapellaSearchEntry) parentElement).getChildren().toArray());
    }
    else if(parentElement instanceof EObject) {
      AbstractCapellaSearchEntry a = searchResult.getElementToMatches().get(parentElement);
      if(a != null)
        return getSources(((AbstractCapellaSearchEntry) a).getChildren().toArray());
    }
    return new Object[0];
  }

  /**
   * @see org.eclipse.jface.viewers.ITreeContentProvider#getParent(java.lang.Object)
   */
  public Object getParent(Object element) {
    if (element instanceof Resource) {
      return searchResult;
    } else if (element instanceof AbstractCapellaSearchEntry) {
      return ((AbstractCapellaSearchEntry) element).getParent();
    }
    else if(element instanceof EObject) {
      AbstractCapellaSearchEntry a = searchResult.getElementToMatches().get(element);
      if(a != null && a.getParent() != null)
        return a.getParent().getElement();
    }
    return null;
  }

  /**
   * @see org.eclipse.jface.viewers.ITreeContentProvider#hasChildren(java.lang.Object)
   */
  public boolean hasChildren(Object element) {
    if (element instanceof IProject) {
      return true;
    } else if (element instanceof AbstractCapellaSearchEntry) {
      return !((AbstractCapellaSearchEntry) element).getChildren().isEmpty();
    } else if(element instanceof EObject) {
      AbstractCapellaSearchEntry a = searchResult.getElementToMatches().get(element);
      if(a != null)
        return !((AbstractCapellaSearchEntry) a).getChildren().isEmpty();
    }
    return false;
  }

  /**
   * @see org.eclipse.jface.viewers.IStructuredContentProvider#getElements(java.lang.Object)
   */
  public Object[] getElements(Object inputElement) {
    if (inputElement instanceof CapellaSearchResult) {
      CapellaSearchResult input = (CapellaSearchResult) inputElement;
      Set<Object> files = input.getRootResultHierarchies().keySet();
      return getSources(files.toArray());
    } else if (inputElement instanceof Collection) {
      return ((Collection<?>) inputElement).toArray();
    }
    return new Object[0];
  }

  public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
    if (newInput instanceof CapellaSearchResult) {
      searchResult = (CapellaSearchResult) newInput;
    }
  }
}
