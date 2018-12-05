/*******************************************************************************
 * Copyright (c) 2018 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.test.richtext.ju.mocks;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.tabbed.ISection;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;
import org.polarsys.capella.core.ui.properties.richtext.fields.CapellaElementDescriptionGroup;

public class CapellaElementDescriptionGroupMock extends CapellaElementDescriptionGroup {

  private boolean isSaved = false;
  
  public CapellaElementDescriptionGroupMock(Composite parent, TabbedPropertySheetWidgetFactory widgetFactory,
      ISection section) {
    super(parent, widgetFactory, section);
  }

  @Override
  public void save() {
    super.save();
    isSaved = true;
  }
  
  public boolean isSaved() {
    return isSaved;
  }
}
