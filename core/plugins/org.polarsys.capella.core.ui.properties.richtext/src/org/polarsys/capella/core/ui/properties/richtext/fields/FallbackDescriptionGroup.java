/*******************************************************************************
 * Copyright (c) 2020 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.ui.properties.richtext.fields;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.viewpoint.description.DescriptionPackage;
import org.eclipse.sirius.viewpoint.description.DocumentedElement;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.ui.properties.fields.TextAreaValueGroup;

public class FallbackDescriptionGroup extends TextAreaValueGroup {

  public FallbackDescriptionGroup(Composite parent, String label, TabbedPropertySheetWidgetFactory widgetFactory) {
    super(parent, label, widgetFactory);
  }

  public FallbackDescriptionGroup(Composite parent, String label, TabbedPropertySheetWidgetFactory widgetFactory,
      boolean skipGroup) {
    super(parent, label, widgetFactory, skipGroup);
  }

  public void loadData(EObject element) {
    if (element instanceof CapellaElement) {
      loadData(element, CapellacorePackage.Literals.CAPELLA_ELEMENT__DESCRIPTION);

    } else if (element instanceof DocumentedElement) {
      loadData(element, DescriptionPackage.Literals.DOCUMENTED_ELEMENT__DOCUMENTATION);
    }
  }
}
