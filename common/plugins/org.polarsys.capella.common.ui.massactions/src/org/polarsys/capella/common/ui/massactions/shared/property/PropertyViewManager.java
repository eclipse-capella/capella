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
package org.polarsys.capella.common.ui.massactions.shared.property;

import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.ui.part.IPageSite;
import org.eclipse.ui.views.properties.tabbed.ITabbedPropertySheetPageContributor;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;
import org.polarsys.capella.core.ui.properties.CapellaTabbedPropertySheetPage;
import org.polarsys.capella.core.ui.properties.CapellaUIPropertiesPlugin;

/**
 * A property view manager that synchronizes the current selection with the 'Properties view'.
 * 
 * @author Sandu Postaru
 *
 */
public class PropertyViewManager implements ITabbedPropertySheetPageContributor {

  protected TabbedPropertySheetPage propertySheetPage;

  @Override
  public String getContributorId() {
    return CapellaUIPropertiesPlugin.PROPERTIES_CONTRIBUTOR;
  }

  public TabbedPropertySheetPage getPropertySheetPage(ISelectionProvider selectionProvider) {
    if (propertySheetPage == null) {
      propertySheetPage = new CapellaTabbedPropertySheetPage(this) {

        @Override
        public void dispose() {
          super.dispose();
          propertySheetPage = null;
        }

        @Override
        public void init(IPageSite pageSite) {
          super.init(pageSite);
          pageSite.setSelectionProvider(selectionProvider);
        }
      };
    }
    return propertySheetPage;
  }
}
