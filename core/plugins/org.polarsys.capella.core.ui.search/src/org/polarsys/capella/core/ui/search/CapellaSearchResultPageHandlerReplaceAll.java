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

import java.util.Set;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.core.commands.IHandlerListener;
import org.eclipse.search.ui.NewSearchUI;

public class CapellaSearchResultPageHandlerReplaceAll implements IHandler {

  @Override
  public void addHandlerListener(IHandlerListener handlerListener) {
    // Nothing
  }

  @Override
  public void dispose() {
    // Nothing
  }

  @Override
  public Object execute(ExecutionEvent event) throws ExecutionException {
    CapellaSearchResultPage capellaSearchResultPage = (CapellaSearchResultPage) NewSearchUI.getSearchResultView()
        .getActivePage();

    if (capellaSearchResultPage != null) {
      CapellaSearchResult capellaSearchResult = capellaSearchResultPage.getInput();
      
      Set<CapellaSearchMatchEntry> matches = capellaSearchResult.getDisplayedMatches();

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
    // Nothing
  }

}
