/*******************************************************************************
 * Copyright (c) 2019 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.platform.sirius.ui.navigator.view;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.navigator.CommonViewer;

public class CapellaCommonViewer extends CommonViewer {
  Set<ViewerFilter> mandatoryFilters = new HashSet<>();

  public CapellaCommonViewer(String aViewerId, Composite aParent, int aStyle) {
    super(aViewerId, aParent, aStyle);
  }

  @Override
  public void setFilters(ViewerFilter... filters) {
    // The filters to set must include also the mandatory filters.
    ViewerFilter[] filtersToSet = new ViewerFilter[filters.length + mandatoryFilters.size()];
    
    for (int j = 0; j < filters.length; j++) {
      filtersToSet[j] = filters[j];
    }
    
    int i = filters.length;
    for (ViewerFilter mandatoryFilter : mandatoryFilters) {
      filtersToSet[i] = mandatoryFilter;
      i++;
    }
    
    super.setFilters(filtersToSet);
  }
  
  /**
   * This helps to always activate the filter.
   * Use it only for programmatically defined filter.
   * 
   * @param filter
   */
  public void addMandatoryFilter(ViewerFilter filter) {
    mandatoryFilters.add(filter);
  }
  
  public void removeMandatoryFilter(ViewerFilter filter) {
    mandatoryFilters.remove(filter);
  }
}
