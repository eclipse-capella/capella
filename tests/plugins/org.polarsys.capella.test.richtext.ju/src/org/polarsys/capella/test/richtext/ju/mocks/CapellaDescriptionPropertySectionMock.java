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
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;
import org.polarsys.capella.core.ui.properties.richtext.fields.CapellaElementDescriptionGroup;
import org.polarsys.capella.core.ui.properties.richtext.sections.CapellaDescriptionPropertySection;

public class CapellaDescriptionPropertySectionMock extends CapellaDescriptionPropertySection {
  @Override
  public void createContents(Composite parent, TabbedPropertySheetPage aTabbedPropertySheetPage) {
    super.createContents(parent, aTabbedPropertySheetPage);
    if (PlatformUI.getTestableObject().getTestHarness() != null)
      descriptionGroup = new CapellaElementDescriptionGroupMock(parent,
          (aTabbedPropertySheetPage != null) ? getWidgetFactory() : null, this);
  }

  public CapellaElementDescriptionGroup getDescriptionGroup() {
    return descriptionGroup;
  }
}
