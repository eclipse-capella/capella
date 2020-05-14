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

import org.eclipse.jface.viewers.IStructuredContentProvider;

/*
 * Class used to provide input when we display the result of the search as a List (display only the leaf)
 */
public class CapellaSearchResultListContentProvider implements IStructuredContentProvider {
  public CapellaSearchResultListContentProvider() {
  }

  @Override
  public Object[] getElements(Object inputElement) {
    if (inputElement instanceof CapellaSearchResult) {
      // For the CapellaSearchResult input, the matched projects are root elements to display
      CapellaSearchResult capellaSearchResult = (CapellaSearchResult) inputElement;
      return capellaSearchResult.getElements();
    }
    return new Object[] {};
  }
}
