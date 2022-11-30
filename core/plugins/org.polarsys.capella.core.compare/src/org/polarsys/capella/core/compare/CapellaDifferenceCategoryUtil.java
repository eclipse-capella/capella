/*******************************************************************************
 * Copyright (c) 2022 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.compare;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.emf.diffmerge.ui.viewers.EMFDiffNode;
import org.eclipse.emf.diffmerge.ui.viewers.IDifferenceCategorySet;
import org.eclipse.emf.diffmerge.ui.viewers.categories.DifferenceCategorySet;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.ui.internal.navigator.filters.CommonFilterDescriptor;
import org.eclipse.ui.navigator.ICommonFilterDescriptor;
import org.eclipse.ui.navigator.INavigatorFilterService;
import org.eclipse.ui.navigator.NavigatorContentServiceFactory;
import org.polarsys.capella.core.compare.categories.EObjectCategoryFilter;
import org.polarsys.capella.core.platform.sirius.ui.navigator.filters.SemanticFiltersHelper;
import org.polarsys.capella.core.platform.sirius.ui.navigator.view.CapellaCommonNavigator;

/**
 * CapellaDifferenceCategoryutil populates the given diffnode categories with all filters from the Capella project
 * explorer The filters are disabled by default Some filters are filtered out as they have no semantic value
 * 
 * @author Erwann Traisnel
 *
 */
public class CapellaDifferenceCategoryUtil {

  public static CapellaDifferenceCategoryUtil eINSTANCE = new CapellaDifferenceCategoryUtil();

  private CapellaDifferenceCategoryUtil() {
  }

  /**
   * Contribute a Capella Category with all filters from the capella Project explorer, minus filters declared above
   */
  public void provideCapellaCategories(EMFDiffNode node_p) {
    IDifferenceCategorySet diagramCategorySet = new DifferenceCategorySet(
        org.polarsys.capella.core.compare.Messages.CapellaDifferenceCategoryProvider_CapellaSet_Text,
        org.polarsys.capella.core.compare.Messages.CapellaDifferenceCategoryProvider_CapellaSet_Description);

    INavigatorFilterService filterService = NavigatorContentServiceFactory.INSTANCE
        .createContentService(CapellaCommonNavigator.ID).getFilterService();

    Comparator<ICommonFilterDescriptor> sortByName = new Comparator<ICommonFilterDescriptor>() {
      public int compare(ICommonFilterDescriptor o1_p, ICommonFilterDescriptor o2_p) {
        return o1_p.getName().compareTo(o2_p.getName());
      }
    };

    List<CommonFilterDescriptor> itemList = Arrays.asList(filterService.getVisibleFilterDescriptors()).stream()
        .filter(d -> d instanceof CommonFilterDescriptor).map(CommonFilterDescriptor.class::cast)
        .filter(d -> d.isVisibleInUi() && SemanticFiltersHelper.isSemanticFilter(d)).collect(Collectors.toList());
    Collections.sort(itemList, sortByName);

    for (CommonFilterDescriptor descriptor : itemList) {
      ViewerFilter f = descriptor.createFilter();
      diagramCategorySet.getChildren().add(new EObjectCategoryFilter(descriptor.getId(), descriptor.getName(), false) {
        @Override
        public boolean keepElement(Object element) {
          return !f.select(null, null, element);
        }
      });
    }
    node_p.getCategoryManager().addCategories(diagramCategorySet);
  }


}
