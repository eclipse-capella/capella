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
import java.util.Comparator;
import java.util.List;

import org.eclipse.emf.diffmerge.ui.viewers.EMFDiffNode;
import org.eclipse.emf.diffmerge.ui.viewers.IDifferenceCategorySet;
import org.eclipse.emf.diffmerge.ui.viewers.categories.DifferenceCategorySet;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.ui.internal.navigator.filters.CommonFilterDescriptor;
import org.eclipse.ui.navigator.ICommonFilterDescriptor;
import org.eclipse.ui.navigator.INavigatorFilterService;
import org.eclipse.ui.navigator.NavigatorContentServiceFactory;
import org.polarsys.capella.core.compare.categories.EObjectCategoryFilter;

/**
 * CapellaDifferenceCategoryutil populates the given diffnode categories with all filters from the Capella project
 * explorer The filters are disabled by default Some filters are filtered out as they have no semantic value
 * 
 * @author Erwann Traisnel
 *
 */
public class CapellaDifferenceCategoryUtil {
  /** Filter for .afm file */
  private final String AFM_FILTER = "capella.project.explorer.filters.metadata"; //$NON-NLS-1$
  /** Filter for .capella file */
  private final String CAPELLA_FILTER = "capella.project.explorer.filters.capellaModeller"; //$NON-NLS-1$
  /** Filter for .aird file */
  private final String AIRD_FILTER = "capella.project.explorer.filters.capellaAird"; //$NON-NLS-1$
  /** Filter for emf resources */
  private final String EMF_FILTER = "capella.project.explorer.filters.EMFResource"; //$NON-NLS-1$
  /** Filter for vp per category */
  private final String VP_CAT_FILTER = "capella.project.explorer.filters.ViewpointsFolderItem"; //$NON-NLS-1$
  /** Filter for vp per resource */
  private final String VP_RES_FILTER = "capella.project.explorer.filters.ResourcesFolderItem"; //$NON-NLS-1$
  /** Filter for invalid representation */
  private final String INVALID_REP_FILTER = "capella.project.explorer.filters.invalidRepresentation"; //$NON-NLS-1$

  /** Capella Project Explorer view id */
  private final String CAPELLA_PROJECT_EXPLORER = "capella.project.explorer"; //$NON-NLS-1$

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
        .createContentService(CAPELLA_PROJECT_EXPLORER).getFilterService();

    List<ICommonFilterDescriptor> itemList = Arrays.asList(filterService.getVisibleFilterDescriptors());

    /**
     * Sorts the array in alphanumerical order
     */
    Object[] sortedArray = itemList.stream().sorted(new Comparator<ICommonFilterDescriptor>() {
      public int compare(ICommonFilterDescriptor o1_p, ICommonFilterDescriptor o2_p) {
        return o1_p.getName().compareTo(o2_p.getName());
      }
    }).toArray();

    for (Object item : sortedArray) {
      CommonFilterDescriptor descriptor = (CommonFilterDescriptor) item;
      if (descriptor.isVisibleInUi()) {
        if (descriptor.getId().contains("capella")) { // if interesting //$NON-NLS-1$
          if (!isFiltered(descriptor)) {
            ViewerFilter f = descriptor.createFilter();
            diagramCategorySet.getChildren()
                .add(new EObjectCategoryFilter(descriptor.getId(), descriptor.getName(), false) {
                  @Override
                  public boolean keepElement(Object element) {
                    return !f.select(null, null, element);
                  }
                });
          }
        }
      }
    }
    node_p.getCategoryManager().addCategories(diagramCategorySet);
  }

  /**
   * 
   * @param descriptor
   * @return whether the filter descriptor should be filtered out
   */
  private boolean isFiltered(ICommonFilterDescriptor descriptor) {
    String id = descriptor.getId();
    return id == null || id.equals(AFM_FILTER) || id.equals(CAPELLA_FILTER) || id.equals(AIRD_FILTER)
        || id.equals(EMF_FILTER) || id.equals(VP_CAT_FILTER) || id.equals(VP_RES_FILTER)
        || id.equals(INVALID_REP_FILTER);
  }

}
