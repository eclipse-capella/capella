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
package org.polarsys.capella.core.ui.search.result.providers.content;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.search.ui.text.AbstractTextSearchViewPage;
import org.polarsys.capella.common.ui.toolkit.viewers.data.TreeData;
import org.polarsys.capella.core.ui.search.match.SearchMatch;
import org.polarsys.capella.core.ui.search.result.CapellaSearchResult;
import org.polarsys.capella.core.ui.search.result.CapellaSearchResultPage;

/**
 * A Tree Content Provider for the Search View. Its content must be synchronized with that of
 * {@CapellaSearchResultListContentProvider}.
 * 
 * Please note that this provider uses a {@link TreeData} as source for the elements. This means that the
 * {@link TreeData} must be managed (elements must be added or removed) in response to the {@link #clear()} and
 * {@link #elementsChanged(Object[])} methods.
 * 
 * In the current implementation, we do not need to update the {@link CapellaSearchResultListContentProvider}, since its
 * underlying collection is directly managed by the {@link CapellaSearchResultPage} and its mother class
 * {@link AbstractTextSearchViewPage}.
 */
public class CapellaSearchResultTreeContentProvider extends SearchResultContentProvider
    implements ITreeContentProvider {

  public CapellaSearchResultTreeContentProvider(CapellaSearchResultPage resultPage) {
    super(resultPage);
  }

  @Override
  public Object[] getElements(Object inputElement) {

    if (inputElement instanceof CapellaSearchResult) {
      // return the root elements (the Project)
      TreeData treeData = ((CapellaSearchResult) inputElement).getTreeData();
      return treeData.getElements();
    }

    return getChildren(inputElement);
  }

  @Override
  public Object[] getChildren(Object parentElement) {

    if (searchResult != null) {
      ArrayList<Object> children = new ArrayList<>();

      List<SearchMatch> childrenSearchMatches = searchResult.getCapellaEntryMatches(parentElement);
      children.addAll(childrenSearchMatches);

      List<Object> childrenModelElements = Arrays.asList(searchResult.getTreeData().getChildren(parentElement));
      children.addAll(childrenModelElements);

      return children.toArray();
    }
    return new Object[0];
  }

  @Override
  public Object getParent(Object element) {

    if (searchResult != null) {
      return searchResult.getTreeData().getParent(element);
    }
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

  @Override
  public void clear() {

    if (searchResult != null) {
      TreeData treeData = searchResult.getTreeData();
      treeData.clearData();

      getViewer().refresh();
    }
  }

  @Override
  public void elementsChanged(Object[] updatedElements) {

    if (searchResult != null) {
      TreeData treeData = searchResult.getTreeData();

      Set<Object> removedElements = new HashSet<>();
      Set<Object> addedElements = new HashSet<>();

      for (Object element : updatedElements) {
        if (resultPage.getDisplayedMatchCount(element) > 0) {
          addedElements.add(element);
        } else {
          removedElements.add(element);
        }
      }

      treeData.removeAllElements(removedElements.toArray());
      treeData.addAllElements(addedElements.toArray());

      getViewer().refresh();
    }
  }

}
