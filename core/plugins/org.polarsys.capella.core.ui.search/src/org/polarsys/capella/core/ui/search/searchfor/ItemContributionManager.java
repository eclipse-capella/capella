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

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.eclipse.core.runtime.IConfigurationElement;
import org.polarsys.capella.common.mdsofa.common.helper.ExtensionPointHelper;
import org.polarsys.capella.core.ui.search.Activator;
import org.polarsys.capella.core.ui.search.searchfor.item.SearchForAttributeItem;

public class ItemContributionManager {
  private static ItemContributionManager instance;
  private Set<SearchForAttributeItem> contributedAttributeItems;

  private ItemContributionManager() {
    contributedAttributeItems = new HashSet<>();
    loadContributedAttributeItems();
  }

  public static ItemContributionManager getInstance() {
    if (instance == null) {
      instance = new ItemContributionManager();
    }
    return instance;
  }

  private void loadContributedAttributeItems() {
    IConfigurationElement[] element = ExtensionPointHelper.getConfigurationElements(Activator.PLUGIN_ID,
        "AttributeItem"); //$NON-NLS-1$
    for (IConfigurationElement ce : element) {
      SearchForAttributeItem extension = (SearchForAttributeItem) ExtensionPointHelper.createInstance(ce,
          "attributeItem"); //$NON-NLS-1$
      if (extension != null) {
        contributedAttributeItems.add(extension);
      }
    }
  }

  public Set<SearchForAttributeItem> getContributedAttributeItems() {
    return contributedAttributeItems;
  }

  public SearchForAttributeItem getContributedAttributeItem(Object attribute) {
    Optional<SearchForAttributeItem> attributeItem = contributedAttributeItems.stream()
        .filter(item -> item.represent(attribute)).findFirst();
    if (attributeItem.isPresent()) {
      return attributeItem.get();
    }
    return null;
  }
}
