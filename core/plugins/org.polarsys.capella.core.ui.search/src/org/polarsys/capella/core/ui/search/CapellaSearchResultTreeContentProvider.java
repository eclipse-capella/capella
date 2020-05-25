/*******************************************************************************
 * Copyright (c) 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.ui.search;

import java.util.ArrayList;
import java.util.Arrays;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

/*
 * Class used to provide input when we display the result of the search as a Tree (display the tree structure)
 */
public class CapellaSearchResultTreeContentProvider implements ITreeContentProvider {
  private CapellaSearchResult searchResult;

  public CapellaSearchResultTreeContentProvider(CapellaSearchResultPage capellaSearchResultPage) {
    searchResult = capellaSearchResultPage.getInput();
  }

  @Override
  public Object[] getElements(Object inputElement) {
    if (inputElement instanceof CapellaSearchResult) {
      return ((CapellaSearchResult) inputElement).getTreeData().getElements();
    } else if (searchResult != null) {
      return getChildren(inputElement);
    }

    return new Object[0];
  }

  @Override
  public Object[] getChildren(Object parentElement) {
    if (searchResult != null) {
      ArrayList<Object> list = new ArrayList<Object>();
      list.addAll(searchResult.getCapellaEntryMatches(parentElement));
      list.addAll(Arrays.asList(searchResult.getTreeData().getChildren(parentElement)));
      return list.toArray();
    }
    return new Object[0];
  }

  @Override
  public Object getParent(Object element) {
    if (searchResult != null)
      return searchResult.getTreeData().getParent(element);
    return new Object[0];
  }

  @Override
  public boolean hasChildren(Object element) {
    return getChildren(element).length > 0;
  }

  @Override
  public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
    if (newInput instanceof CapellaSearchResult) {
      searchResult = (CapellaSearchResult) newInput;
    }
  }
}
