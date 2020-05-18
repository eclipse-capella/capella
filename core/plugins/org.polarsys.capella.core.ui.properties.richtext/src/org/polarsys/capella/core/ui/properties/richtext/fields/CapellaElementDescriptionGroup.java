/*******************************************************************************
 * Copyright (c) 2017, 2018 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.ui.properties.richtext.fields;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.viewpoint.description.DescriptionPackage;
import org.eclipse.sirius.viewpoint.description.DocumentedElement;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.tabbed.ISection;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;

/**
 * A customized description group.
 * 
 * @author Joao Barata
 */
public class CapellaElementDescriptionGroup extends ElementDescriptionGroup {

  /**
   * @param parent
   * @param widgetFactory
   */
  public CapellaElementDescriptionGroup(Composite parent, TabbedPropertySheetWidgetFactory widgetFactory,
      ISection section) {
    super(parent, widgetFactory, section);
  }

  /**
   * Called by the section refresh coming from wherever.
   */
  public void loadData(EObject element) {
    if (element instanceof CapellaElement) {
      loadData(element, CapellacorePackage.Literals.CAPELLA_ELEMENT__DESCRIPTION);

    } else if (element instanceof DocumentedElement) {
      loadData(element, DescriptionPackage.Literals.DOCUMENTED_ELEMENT__DOCUMENTATION);
    }
  }

}
