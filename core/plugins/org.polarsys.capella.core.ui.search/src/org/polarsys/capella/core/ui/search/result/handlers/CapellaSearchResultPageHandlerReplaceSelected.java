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
package org.polarsys.capella.core.ui.search.result.handlers;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.core.commands.IHandlerListener;
import org.eclipse.core.resources.IProject;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.search.ui.NewSearchUI;
import org.eclipse.ui.handlers.HandlerUtil;
import org.polarsys.capella.core.ui.search.CapellaReplaceRunnable;
import org.polarsys.capella.core.ui.search.CapellaReplaceRunnableWrapper;
import org.polarsys.capella.core.ui.search.CapellaSearchQuery;
import org.polarsys.capella.core.ui.search.match.SearchMatch;
import org.polarsys.capella.core.ui.search.result.CapellaSearchResult;
import org.polarsys.capella.core.ui.search.result.CapellaSearchResultPage;

public class CapellaSearchResultPageHandlerReplaceSelected implements IHandler {

  @Override
  public void addHandlerListener(IHandlerListener handlerListener) {
  }

  @Override
  public void dispose() {
  }

  @Override
  public Object execute(ExecutionEvent event) throws ExecutionException {
    CapellaSearchResultPage capellaSearchResultPage = (CapellaSearchResultPage) NewSearchUI.getSearchResultView()
        .getActivePage();

    if (capellaSearchResultPage != null) {
      CapellaSearchResult capellaSearchResult = capellaSearchResultPage.getInput();
      IStructuredSelection currentStructuredSelection = HandlerUtil.getCurrentStructuredSelection(event);

      Set<SearchMatch> matches = new HashSet<>();
      for (Iterator<?> iterator = currentStructuredSelection.iterator(); iterator.hasNext();) {
        Object selectedElement = iterator.next();
        if (selectedElement instanceof SearchMatch) {
          matches.add((SearchMatch) selectedElement);
          matches.addAll(((SearchMatch) selectedElement).getChildren());
        } else if (selectedElement instanceof IProject) {
          matches.addAll(capellaSearchResult.getDisplayedMatches(selectedElement));
        } else {
          matches.addAll(capellaSearchResult.getDisplayedMatches(selectedElement));
        }
      }

      CapellaSearchQuery searchQuery = capellaSearchResult.getQuery();

      CapellaReplaceRunnable capellaReplaceRunnable = new CapellaReplaceRunnable(searchQuery, matches, true);

      new CapellaReplaceRunnableWrapper(capellaReplaceRunnable).run();
    }
    return null;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }

  @Override
  public boolean isHandled() {
    return true;
  }

  @Override
  public void removeHandlerListener(IHandlerListener handlerListener) {
  }

}
