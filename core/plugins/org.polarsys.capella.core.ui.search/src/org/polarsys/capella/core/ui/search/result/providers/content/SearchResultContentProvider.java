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

import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.search.ui.text.AbstractTextSearchViewPage;
import org.polarsys.capella.core.ui.search.result.CapellaSearchResult;
import org.polarsys.capella.core.ui.search.result.CapellaSearchResultPage;

/**
 * An abstract base implementation for a Search Result Content Provider, used by {@link CapellaSearchResultPage}.
 *
 */
public abstract class SearchResultContentProvider {

  protected CapellaSearchResultPage resultPage;
  protected CapellaSearchResult searchResult;

  public SearchResultContentProvider(CapellaSearchResultPage resultPage) {
    this.resultPage = resultPage;
    this.searchResult = resultPage.getInput();
  }

  protected StructuredViewer getViewer() {
    return resultPage.getViewer();
  }

  /**
   * {@link AbstractTextSearchViewPage} clear() method.
   */
  public abstract void clear();

  /**
   * {@link AbstractTextSearchViewPage} elementsChanged(Object[]) method.
   */
  public abstract void elementsChanged(Object[] updateElements);

}
