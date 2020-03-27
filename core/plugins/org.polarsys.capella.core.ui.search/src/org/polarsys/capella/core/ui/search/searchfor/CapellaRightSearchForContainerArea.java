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

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerComparator;
import org.eclipse.sirius.viewpoint.ViewpointPackage;
import org.eclipse.sirius.viewpoint.description.DescriptionPackage;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Group;
import org.eclipse.ui.dialogs.PatternFilter;
import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;
import org.polarsys.capella.common.ui.toolkit.widgets.filter.CapellaPatternFilter;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.ui.search.CapellaSearchPage;
import org.polarsys.capella.core.ui.search.searchfor.item.SearchForAttributeItem;
import org.polarsys.capella.core.ui.search.searchfor.item.SearchForItem;

public class CapellaRightSearchForContainerArea extends AbstractCapellaSearchForContainerArea {
  protected AbstractSearchForContentProvider partictipantsItemProvider;
  // Map of attributes that should be on the top of attribute list and their index
  private Map<EAttribute, Integer> topAttributes;

  public CapellaRightSearchForContainerArea(Group parent, AbstractCapellaSearchForContainerArea leftArea,
      CapellaSearchPage searchPage) {
    super(parent, leftArea, searchPage);
    topAttributes = new HashMap<>();
    topAttributes.put(ModellingcorePackage.Literals.ABSTRACT_NAMED_ELEMENT__NAME, 0);
    topAttributes.put(CapellacorePackage.Literals.CAPELLA_ELEMENT__SUMMARY, 1);
    topAttributes.put(CapellacorePackage.Literals.CAPELLA_ELEMENT__DESCRIPTION, 2);
    topAttributes.put(DescriptionPackage.Literals.DOCUMENTED_ELEMENT__DOCUMENTATION, 3);
  }

  @Override
  protected AbstractSearchForContentProvider getSearchForContentProvider() {
    if (partictipantsItemProvider == null) {
      partictipantsItemProvider = new AttributeContentProvider(otherSideArea);
    }
    return partictipantsItemProvider;
  }

  protected PatternFilter createPatternFilter() {
    return new CapellaPatternFilter();
  }

  public void updateSearchSettings() {
    searchPage.getCapellaSearchSettings().setSearchAttributeItems(getCheckedElements());
  }

  @Override
  protected void createContentArea() {
    super.createContentArea();

    filteredTree.getViewer().setComparator(new ViewerComparator() {
      @Override
      public int compare(Viewer viewer, Object e1, Object e2) {
        if (e1 instanceof SearchForAttributeItem && e2 instanceof SearchForAttributeItem) {
          SearchForAttributeItem item1 = (SearchForAttributeItem) e1;
          SearchForAttributeItem item2 = (SearchForAttributeItem) e2;
          Optional<EAttribute> item1TopAttribute = topAttributes.keySet().stream().filter(item1::represent).findAny();
          Optional<EAttribute> item2TopAttribute = topAttributes.keySet().stream().filter(item2::represent).findAny();
          if (item1TopAttribute.isPresent() && item2TopAttribute.isPresent()) {
            return topAttributes.get(item1TopAttribute.get()) < topAttributes.get(item2TopAttribute.get()) ? -1 : 1;
          } else if (item1TopAttribute.isPresent()) {
            return -1;
          } else if (item2TopAttribute.isPresent()) {
            return 1;
          }
          return ((SearchForItem) e1).getText().compareTo(((SearchForItem) e2).getText());
        }
        return 0;
      }
    });
  }
  
  @Override
  protected SelectionListener getRestoreDefaultsSelectionListener() {
    return new SelectionListener() {
      @Override
      public void widgetSelected(SelectionEvent e) {
        applyDefaultSearchSettings();
      }

      @Override
      public void widgetDefaultSelected(SelectionEvent e) {
        widgetSelected(e);
      }
    };
  }

  @Override
  public void applyDefaultSearchSettings() {
    Set<Object> topAttributeItems = SearchForItemCache.getInstance().getAttributeItems().stream()
        .filter(SearchForAttributeItem.class::isInstance).map(SearchForAttributeItem.class::cast)
        .filter(item -> topAttributes.keySet().stream().anyMatch(item::represent)).collect(Collectors.toSet());
    filteredTree.getCheckboxTreeViewer().setCheckedElements(topAttributeItems.toArray());
    cleanCheckedElements();
    topAttributeItems.stream().forEach(item -> updateCheckedElements(item, true));
    updateSearchSettings();
  }
}
