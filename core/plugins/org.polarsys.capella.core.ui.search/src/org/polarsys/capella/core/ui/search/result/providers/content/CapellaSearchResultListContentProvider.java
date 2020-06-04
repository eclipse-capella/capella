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

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.search.ui.text.AbstractTextSearchViewPage;
import org.polarsys.capella.common.ui.toolkit.viewers.data.TreeData;
import org.polarsys.capella.core.ui.search.result.CapellaSearchResult;
import org.polarsys.capella.core.ui.search.result.CapellaSearchResultPage;

/**
 * A List Content Provider for the Search View. Its content must be synchronized with that of
 * {@link CapellaSearchResultTreeContentProvider}.
 * 
 * Please note that this provider uses the {@link CapellaSearchResult#getElements()} collection as source for the
 * elements.
 * 
 * Since this collection is directly managed by the {@link CapellaSearchResultPage} and its mother class
 * {@link AbstractTextSearchViewPage}, there is no need to add or remove elements to this collection in the
 * {@link #clear()} or {@link #elementsChanged(Object[])} methods.
 * 
 * But we still need to update the {@link TreeData} which is used by the {@link CapellaSearchResultTreeContentProvider},
 * in order to ensure that the Tree Display and List Display are synchronized.
 */
public class CapellaSearchResultListContentProvider extends SearchResultContentProvider
    implements IStructuredContentProvider {

  public CapellaSearchResultListContentProvider(CapellaSearchResultPage resultPage) {
    super(resultPage);
  }

  @Override
  public Object[] getElements(Object inputElement) {
    if (inputElement instanceof CapellaSearchResult) {
      CapellaSearchResult capellaSearchResult = (CapellaSearchResult) inputElement;
      return capellaSearchResult.getElements();
    }
    return new Object[0];
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
      Object[] currentElements = searchResult.getElements();

      treeData.clearData();
      treeData.addAllElements(currentElements);

      getViewer().refresh();
    }

  }

}
