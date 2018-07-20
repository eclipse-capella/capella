/*******************************************************************************
 * Copyright (c) 2018 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.common.ui.massactions.visualization;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.views.properties.IPropertySheetPage;
import org.polarsys.capella.common.ui.massactions.core.visualize.table.CapellaMVTable;
import org.polarsys.capella.common.ui.massactions.shared.property.PropertyViewManager;
import org.polarsys.kitalpha.massactions.core.table.IMATable;
import org.polarsys.kitalpha.massactions.visualize.MVView;

/**
 * A Capella Mass Visualization view extension of the default {@link MVView}.
 * 
 * @author Sandu Postaru
 *
 */
public class CapellaMVView extends MVView {

  protected PropertyViewManager propertyViewManager;

  public CapellaMVView() {
    propertyViewManager = new PropertyViewManager();
  }

  @Override
  public IMATable createTable(Composite parent) {
    return new CapellaMVTable(parent);
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

}
