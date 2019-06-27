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
package org.polarsys.capella.core.platform.sirius.ui.navigator.filters;

import java.util.HashMap;

import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.core.runtime.preferences.IEclipsePreferences.IPreferenceChangeListener;
import org.eclipse.core.runtime.preferences.IEclipsePreferences.PreferenceChangeEvent;
import org.eclipse.core.runtime.preferences.InstanceScope;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.internal.navigator.NavigatorPlugin;
import org.eclipse.ui.navigator.CommonNavigator;
import org.polarsys.capella.core.platform.sirius.ui.navigator.view.CapellaCommonNavigator;
import org.polarsys.capella.core.ui.toolkit.helpers.IUserPartManagement;

public class FilterManager implements IUserPartManagement, org.eclipse.core.runtime.IAdapterFactory {

  public static String PART_FILTER = "capella.project.explorer.filters.part"; //$NON-NLS-1$

  HashMap<IWorkbenchWindow, Boolean> windows = new HashMap<IWorkbenchWindow, Boolean>();

  public FilterManager() {
    IEclipsePreferences prefs = (IEclipsePreferences) Platform.getPreferencesService().getRootNode()
        .node(InstanceScope.SCOPE).node(NavigatorPlugin.PLUGIN_ID);
    // We need to add a listener on preferences of Navigator. If they change, we will recompute cache.
    prefs.addPreferenceChangeListener(new IPreferenceChangeListener() {
      @Override
      public void preferenceChange(PreferenceChangeEvent event) {
        windows.clear();
      }
    });
  }

  public boolean isPartsVisible(IWorkbenchWindow source) {
    boolean result = false;
    IWorkbenchWindow window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
    if (window != null) {
      if (windows.containsKey(window)) {
        return windows.get(window);
      }
      IViewPart part = window.getActivePage().findView(CapellaCommonNavigator.ID);
      if (part instanceof CommonNavigator) {
        result = !((CommonNavigator) part).getNavigatorContentService().getFilterService().isActive(PART_FILTER);
      }
      windows.put(window, result);
    }
    return result;

  }

  @Override
  public <T> T getAdapter(Object adaptableObject, Class<T> adapterType) {
    if (IUserPartManagement.class.equals(adapterType)) {
      return (T) this;
    }
    return null;
  }

  @Override
  public Class<?>[] getAdapterList() {
    return new Class[] { IUserPartManagement.class };
  }
}
