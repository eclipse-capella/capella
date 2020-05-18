/*******************************************************************************
 * Copyright (c) 2018 THALES GLOBAL SERVICES.
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 * 
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 * Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.common.ui.massactions.core.shared.extensions.columnprovider;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.ui.massactions.core.shared.column.SemanticBrowserColumn;
import org.polarsys.capella.common.ui.massactions.core.shared.helper.SemanticBrowserHelper;
import org.polarsys.capella.common.ui.toolkit.browser.category.ICategory;
import org.polarsys.kitalpha.massactions.core.column.IMAColumn;
import org.polarsys.kitalpha.massactions.core.extensions.columnprovider.AbstractMAColumnProvider;
import org.polarsys.kitalpha.massactions.core.helper.container.PossibleFeature;

/**
 * The default Semantic Browser queries column provider.
 * 
 * @author Sandu Postaru
 *
 */
public class SemanticBrowserColumnProvider extends AbstractMAColumnProvider {

  private Map<ICategory, IMAColumn> columnsMap;

  public SemanticBrowserColumnProvider() {
    columnsMap = new HashMap<>();
  }

  @Override
  public Collection<IMAColumn> getColumnValues(Collection<PossibleFeature> commonPossibleFeatures,
      Collection<EObject> data) {

    Set<ICategory> commonCategories = SemanticBrowserHelper.getCommonObjectCategories(data);

    // remove unused columns
    columnsMap.keySet().removeIf(category -> !commonCategories.contains(category));

    // add new columns
    commonCategories.removeAll(columnsMap.keySet());

    for (ICategory category : commonCategories) {

      SemanticBrowserColumn column = new SemanticBrowserColumn();
      column.setBodyLayer(bodyLayer);
      column.setName(category.getName());
      column.setEditable(false);
      column.setCategory(category);

      columnsMap.put(category, column);
    }

    return columnsMap.values();
  }

}
