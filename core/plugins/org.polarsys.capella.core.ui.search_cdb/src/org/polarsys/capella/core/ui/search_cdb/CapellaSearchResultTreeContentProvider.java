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
package org.polarsys.capella.core.ui.search_cdb;

import java.util.Set;

import org.eclipse.core.resources.IProject;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.polarsys.capella.core.platform.sirius.ui.navigator.viewer.CapellaNavigatorContentProvider;

public class CapellaSearchResultTreeContentProvider extends CapellaNavigatorContentProvider {
  private CapellaSearchResult_CDB searchResult;

  public CapellaSearchResultTreeContentProvider(CapellaSearchResultPage_CDB capellaSearchResultPage) {
    super();
    searchResult = capellaSearchResultPage.getInput();
  }
  
  public Object[] getChildren(Object parentElement) {
    if (parentElement instanceof CapellaSearchMatch_CDB) {
      return ((CapellaSearchMatch_CDB)parentElement).getMatchOccurrences().toArray();
    }
    return super.getChildren(parentElement);
  }
  
  public Object getParent(Object element) {
    if (element instanceof CapellaSearchMatch_CDB) {
      return super.getParent(((CapellaSearchMatch_CDB)element).getElement());
    }
    return super.getParent(element);
  }
  
  @Override
  public Object[] getElements(Object inputElement) {
    if (inputElement instanceof CapellaSearchResult_CDB) {
      CapellaSearchResult_CDB input = (CapellaSearchResult_CDB) inputElement;
      Set<IProject> files = input.getProjects();
      return files.toArray();
    }
    return super.getElements(inputElement);
  }
  
  @Override
  public boolean hasChildren(Object element) {
    if (element instanceof IProject) {
      return true;
    } else if (element instanceof CapellaSearchMatch_CDB) {
      return !((CapellaSearchMatch_CDB) element).getMatchOccurrences().isEmpty();
    }
    return super.hasChildren(element);
  }
  
  /*public Object[] getSources(Object[] input) {
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
  public Object[] getChildren(Object parentElement) {
    if (parentElement instanceof IProject) {
      return getSources(searchResult.getRootResultHierarchies().get((IProject) parentElement).toArray());
    } else if (parentElement instanceof CapellaSearchMatch_CDB) {
      return getSources(((CapellaSearchMatch_CDB) parentElement).getChildren().toArray());
    }
    else if(parentElement instanceof EObject) {
      CapellaSearchMatch_CDB a = searchResult.getElementToMatches().get(parentElement);
      if(a != null)
        return getSources(((CapellaSearchMatch_CDB) a).getChildren().toArray());
    }
    return new Object[0];
  }

  public Object getParent(Object element) {
    if (element instanceof Resource) {
      return searchResult;
    } else if (element instanceof CapellaSearchMatch_CDB) {
      return ((CapellaSearchMatch_CDB) element).getParent();
    }
    else if(element instanceof EObject) {
      CapellaSearchMatch_CDB a = searchResult.getElementToMatches().get(element);
      if(a != null && a.getParent() != null)
        return a.getParent().getElement();
    }
    return null;
  }

  public boolean hasChildren(Object element) {
    if (element instanceof IProject) {
      return true;
    } else if (element instanceof CapellaSearchMatch_CDB) {
      return !((CapellaSearchMatch_CDB) element).getChildren().isEmpty();
    } else if(element instanceof EObject) {
      CapellaSearchMatch_CDB a = searchResult.getElementToMatches().get(element);
      if(a != null)
        return !((CapellaSearchMatch_CDB) a).getChildren().isEmpty();
    }
    return false;
  }

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
  */

  public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
    if (newInput instanceof CapellaSearchResult_CDB) {
      searchResult = (CapellaSearchResult_CDB) newInput;
    }
  }
}
