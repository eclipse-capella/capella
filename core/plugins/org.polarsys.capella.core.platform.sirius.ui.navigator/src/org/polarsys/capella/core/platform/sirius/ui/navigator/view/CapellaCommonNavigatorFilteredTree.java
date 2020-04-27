/*******************************************************************************
 * Copyright (c) 2019 THALES GLOBAL SERVICES.
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

import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Composite;
import org.polarsys.capella.common.ui.toolkit.widgets.filter.CapellaFilteredTree;

public class CapellaCommonNavigatorFilteredTree extends CapellaFilteredTree {
  
  public CapellaCommonNavigatorFilteredTree(Composite parent, int treeStyle, CapellaCommonNavigatorPatternFilter filter) {
    super(parent, treeStyle, filter);
    
    filter.doSetUseCache(true);
    
    setLevelOfExpandByDefault(4); // 4 so that all architectural levels are visible
    
    ((CapellaCommonViewer) getViewer()).addMandatoryFilter(filter);
  }

  @Override
  protected TreeViewer doCreateTreeViewer(Composite parent, int style) {
    return new CapellaCommonViewer(CapellaCommonNavigator.ID, parent, style);
  }

  public void setSearchInDescriptionEnabled(boolean isSearchInDescriptionEnabled) {
    ((CapellaCommonNavigatorPatternFilter) getPatternFilter()).setSearchInDescriptionEnabled(isSearchInDescriptionEnabled);
  }
}
