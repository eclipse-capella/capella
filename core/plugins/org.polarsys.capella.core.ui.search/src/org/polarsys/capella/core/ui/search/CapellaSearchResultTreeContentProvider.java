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

import java.util.Arrays;

import org.eclipse.core.resources.IProject;
import org.eclipse.jface.viewers.ITreeContentProvider;

public class CapellaSearchResultTreeContentProvider implements ITreeContentProvider {
  private final CapellaSearchResultPage capellaSearchResultPage;
  private final CapellaSearchResult searchResult;

  public CapellaSearchResultTreeContentProvider(CapellaSearchResultPage capellaSearchResultPage) {
    this.capellaSearchResultPage = capellaSearchResultPage;
    searchResult = capellaSearchResultPage.getInput();
  }

  @Override
  public Object[] getElements(Object inputElement) {
    if (inputElement instanceof CapellaSearchResult) {
      // For the CapellaSearchResult input, the matched projects are root elements to display
      CapellaSearchResult capellaSearchResult = (CapellaSearchResult) inputElement;
      return capellaSearchResult.getProjects().toArray();
    }
    return new Object[] {};
  }

  @Override
  public Object[] getChildren(Object parentElement) {
    if (capellaSearchResultPage != null) {
      if (parentElement instanceof IProject) {
        // If the parent is a matched project, its children are matched elements inside that project
        return capellaSearchResultPage.getInput() //
            .getDisplayedElements((IProject) parentElement) //
            .toArray();
      } else if (parentElement != null) {
        // If the parent is a matched element, its children are Capella Search Match which are not filtered
        return capellaSearchResultPage.getDisplayedMatches(parentElement);
      }
    }
    return new Object[] {};
  }

  @Override
  public Object getParent(Object element) {
    if (capellaSearchResultPage != null) {
      CapellaSearchResult capellaSearchResult = capellaSearchResultPage.getInput();
      if (element instanceof CapellaSearchMatch) {
        // If the element is a CapellaSearchMatch, its parent is a matched element
        CapellaSearchMatch match = (CapellaSearchMatch) element;
        for (Object matchedElement : capellaSearchResult.getElements()) {
          if (Arrays.asList(capellaSearchResult.getMatches(matchedElement)).contains(match)) {
            return matchedElement;
          }
        }
      } else if (element != null) {
        // If the element is a matched element, its parent is a matched project
        for (IProject matchedProject : capellaSearchResult.getProjects()) {
          if (capellaSearchResult.getElements(matchedProject).contains(element)) {
            return matchedProject;
          }
        }
      }
    }
    return null;
  }

  @Override
  public boolean hasChildren(Object element) {
    if (capellaSearchResultPage != null) {
      if (element instanceof CapellaSearchMatch) {
        // CapellaSearchMatch is the most leaf element
        return false;
      } else if (element instanceof IProject) {
        // For a matched project, its children are matched elements
        return capellaSearchResultPage.getInput().getDisplayedOccurrenceCount((IProject) element) > 0;
      } else {
        // For other elements, its children are Capella Search Match which are not filtered
        return capellaSearchResultPage.getInput().getDisplayedOccurrenceCount(element) > 0;
      }
    }
    return false;
  }
}
