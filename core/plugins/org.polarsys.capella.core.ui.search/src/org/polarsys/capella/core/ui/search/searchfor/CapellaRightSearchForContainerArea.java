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
package org.polarsys.capella.core.ui.search.searchfor;

import org.eclipse.jface.viewers.CheckStateChangedEvent;
import org.eclipse.jface.viewers.CheckboxTreeViewer;
import org.eclipse.jface.viewers.ICheckStateListener;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.widgets.Group;
import org.eclipse.ui.dialogs.PatternFilter;
import org.polarsys.capella.core.ui.search.CapellaSearchPage;
import org.polarsys.capella.core.ui.search.CapellaSearchSettings;

public class CapellaRightSearchForContainerArea extends AbstractCapellaSearchForContainerArea {
  protected AbstractMetaModelParticipantsItemProvider partictipantsItemProvider;
  
  public CapellaRightSearchForContainerArea(Group parent, AbstractCapellaSearchForContainerArea leftArea,
       CapellaSearchPage searchPage) {
    super(parent, leftArea, searchPage);
  }

  @Override
  protected AbstractMetaModelParticipantsItemProvider getPartictipantsItemProvider() {
    if (partictipantsItemProvider == null) {
      partictipantsItemProvider = new AttributesParticipantsItemProvider(otherSideArea);
    }
    return partictipantsItemProvider;
  }

  protected PatternFilter createPatternFilter() {
    return new PatternFilter() {
      @Override
      public Object[] filter(Viewer viewer, Object parent, Object[] elements) {
        Object[] result = super.filter(viewer, parent, elements);
        if (parent != null) {
          if (parent.equals("")) {
            displayedElements.clear();
            updateDisplayedElements(result);
          }
        }
        return result;
      }

      private void updateDisplayedElements(Object[] elements) {
        for (Object displayedElement : elements) {
          displayedElements.add(displayedElement);
        }
      }
    };
  }

  @Override
  protected void setCheckSubtree() {
    ((CheckboxTreeViewer) filteredTree.getViewer()).addCheckStateListener(getCheckStateListener());
  }

  private ICheckStateListener getCheckStateListener() {
    return new ICheckStateListener() {
      public void checkStateChanged(final CheckStateChangedEvent event) {
        boolean state = event.getChecked();
        Object element = event.getElement();
        if (state == true)
          checkedElements.add(element);
        else
          checkedElements.remove(element);
        
        searchPage.getCapellaSearchSettings().setSearchAttributes(checkedElements);
        searchPage.updateValidationStatus(searchPage.getCapellaSearchSettings().validate());
      }
    };
  }
}
