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
package org.polarsys.capella.common.ui.massactions.edit;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.views.properties.IPropertySheetPage;
import org.polarsys.capella.common.ui.massactions.core.edit.table.CapellaMETable;
import org.polarsys.capella.common.ui.massactions.shared.helper.CapellaMASelectionHelper;
import org.polarsys.capella.common.ui.massactions.shared.property.PropertyViewManager;
import org.polarsys.kitalpha.massactions.core.table.IMATable;
import org.polarsys.kitalpha.massactions.edit.MEView;
import org.polarsys.kitalpha.massactions.shared.helper.MASelectionHelper;

/**
 * A Capella Mass Editing view extension of the default {@link MEView}.
 * 
 * @author Sandu Postaru
 *
 */
public class CapellaMEView extends MEView {

  protected PropertyViewManager propertyViewManager;

  public CapellaMEView() {
    propertyViewManager = new PropertyViewManager();
  }

  @Override
  public IMATable createTable(Composite parent) {
    return new CapellaMETable(parent);
  }

  @SuppressWarnings("unchecked")
  @Override
  public <T> T getAdapter(Class<T> adapter) {

    if (IPropertySheetPage.class.equals(adapter)) {
      return (T) propertyViewManager.getPropertySheetPage(selectionProvider);
    } else if (Control.class.equals(adapter)) {
      return (T) parent;
    } else {
      return super.getAdapter(adapter);
    }
  }
  
  @Override
  protected MASelectionHelper createSelectionHelper() {
    return new CapellaMASelectionHelper();
  }
}
