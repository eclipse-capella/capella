/*******************************************************************************
 * Copyright (c) 2019, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.platform.sirius.ui.navigator.view;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.jface.viewers.IContentProvider;
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
    clearCachesForFilters();
    
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
  
  protected void clearCachesForFilters() {
    for (ViewerFilter viewerFilter : getFilters()) {
      if (viewerFilter instanceof CapellaCommonNavigatorPatternFilter) {
        ((CapellaCommonNavigatorPatternFilter) viewerFilter).doClearCaches();
      }
    }
  }
  
  @Override
  public void add(Object parentElementOrTreePath, Object childElement) {
    clearCachesForFilters();
    super.add(parentElementOrTreePath, childElement);
  }

  @Override
  public void add(Object parentElementOrTreePath, Object... childElements) {
    clearCachesForFilters();
    super.add(parentElementOrTreePath, childElements);
  }

  @Override
  protected void inputChanged(Object input, Object oldInput) {
    clearCachesForFilters();
    super.inputChanged(input, oldInput);
  }

  @Override
  public void insert(Object parentElementOrTreePath, Object element, int position) {
    clearCachesForFilters();
    super.insert(parentElementOrTreePath, element, position);
  }

  @Override
  public void refresh() {
    clearCachesForFilters();
    super.refresh();
  }

  @Override
  public void refresh(boolean updateLabels) {
    clearCachesForFilters();
    super.refresh(updateLabels);
  }

  @Override
  public void refresh(Object element) {
    clearCachesForFilters();
    super.refresh(element);
  }

  @Override
  public void refresh(Object element, boolean updateLabels) {
    clearCachesForFilters();
    super.refresh(element, updateLabels);
  }

  @Override
  public void remove(Object elementsOrTreePaths) {
    clearCachesForFilters();
    super.remove(elementsOrTreePaths);
  }

  @Override
  public void remove(Object parent, Object... elements) {
    clearCachesForFilters();
    super.remove(parent, elements);
  }

  @Override
  public void remove(Object... elementsOrTreePaths) {
    clearCachesForFilters();
    super.remove(elementsOrTreePaths);
  }

  @Override
  public void replace(Object parentElementOrTreePath, int index, Object element) {
    clearCachesForFilters();
    super.replace(parentElementOrTreePath, index, element);
  }

  @Override
  public void setChildCount(Object elementOrTreePath, int count) {
    clearCachesForFilters();
    super.setChildCount(elementOrTreePath, count);
  }

  @Override
  public void setContentProvider(IContentProvider provider) {
    clearCachesForFilters();
    super.setContentProvider(provider);
  }

  @Override
  public void setHasChildren(Object elementOrTreePath, boolean hasChildren) {
    clearCachesForFilters();
    super.setHasChildren(elementOrTreePath, hasChildren);
  }

}
