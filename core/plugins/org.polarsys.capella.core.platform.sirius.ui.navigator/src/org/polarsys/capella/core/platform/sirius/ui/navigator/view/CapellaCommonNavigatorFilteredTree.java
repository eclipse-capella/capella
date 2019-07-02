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

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.dialogs.PatternFilter;
import org.polarsys.capella.common.ui.toolkit.widgets.filter.CapellaFilteredTree;

public class CapellaCommonNavigatorFilteredTree extends CapellaFilteredTree {
  
  public CapellaCommonNavigatorFilteredTree(Composite parent, int treeStyle, CapellaCommonNavigatorPatternFilter filter) {
    super(parent, treeStyle, filter);
    
    try {
      // As we are not using the default NotifyingTreeViewer of FilteredTree, the cache is not activated.
      // Use java reflection to activate it here
      Method refreshTitleMethod = PatternFilter.class.getDeclaredMethod("setUseCache", boolean.class); //$NON-NLS-1$
      refreshTitleMethod.setAccessible(true);
      refreshTitleMethod.invoke(filter, true);
    } catch (IllegalAccessException e) {
      e.printStackTrace();
    } catch (IllegalArgumentException e) {
      e.printStackTrace();
    } catch (InvocationTargetException e) {
      e.printStackTrace();
    } catch (NoSuchMethodException e) {
      e.printStackTrace();
    } catch (SecurityException e) {
      e.printStackTrace();
    }
    
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
