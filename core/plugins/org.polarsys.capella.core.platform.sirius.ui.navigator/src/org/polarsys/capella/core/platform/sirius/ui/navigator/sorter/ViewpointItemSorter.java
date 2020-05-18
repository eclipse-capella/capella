/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.platform.sirius.ui.navigator.sorter;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerSorter;
import org.eclipse.sirius.ui.tools.api.views.common.item.ViewpointItem;
import org.eclipse.sirius.viewpoint.description.Viewpoint;

/**
 * Used to sort ViewpointItem(s) under ViewpointsFolderItem (Representations per category).<br>
 * Known viewpoints are displayed first and sorted with a predefined order, unknown viewpoints are added at the end with no particular order.
 */
public class ViewpointItemSorter extends ViewerSorter {
  /**
   * Default sort order for known viewpoints.
   */
  public static final List<String> DEFAULT_VIEW_POINTS_ORDER = Collections.unmodifiableList(Arrays.asList(Messages.COMMON, Messages.OPERATIONAL_ANALYSIS,
      Messages.SYSTEM_ANALYSIS, Messages.LOGICAL_ARCHITECTURE, Messages.PHYSICAL_ARCHITECTURE, Messages.EPBS_ARCHITECTURE));

  /**
   * @see org.eclipse.jface.viewers.ViewerComparator#compare(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
   */
  @Override
  public int compare(Viewer viewer_p, Object object1_p, Object object2_p) {
    String viewpointName1 = getViewpointName(object1_p);
    String viewpointName2 = getViewpointName(object2_p);
    // Get indexes of the names in the default order table (unknown viewpoints will get -1).
    int name1Index = DEFAULT_VIEW_POINTS_ORDER.indexOf(viewpointName1);
    int name2Index = DEFAULT_VIEW_POINTS_ORDER.indexOf(viewpointName2);
    // Same indexes -> names are identical or no order is defined between them (this is the case for 2 unknown viewpoints).
    if (name1Index == name2Index) {
      return 0;
    }
    // Get rid of -1 to have unknown viewpoints greater than known one (and so put them at the end).
    name1Index = (name1Index > -1) ? name1Index : Integer.MAX_VALUE;
    name2Index = (name2Index > -1) ? name2Index : Integer.MAX_VALUE;
    return name1Index < name2Index ? -1 : 1;
  }

  /**
   * Get viewpoint label.
   * @param object_p
   * @return
   */
  protected String getViewpointName(Object object_p) {
    String result = null;
    // Given object must be an ItemWrapper wrapped to a Viewpoint.
    if (object_p instanceof ViewpointItem) {
      Viewpoint viewpoint = (Viewpoint) ((ViewpointItem) object_p).getWrappedObject();
      result = viewpoint.getName();
    }
    return result;
  }
}
