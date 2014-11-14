/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.ui.properties.fields;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;

import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.ui.properties.controllers.IMultipleSemanticFieldController;

/**
 */
public class CompositionMultipleSemanticField extends MultipleSemanticField {
  /**
   * Composition element's feature handled by this field.
   */
  protected EStructuralFeature _compositionFeature;

  /**
   * @param parent_p
   * @param label_p
   * @param widgetFactory_p
   * @param controller_p
   */
  public CompositionMultipleSemanticField(Composite parent_p, String label_p, TabbedPropertySheetWidgetFactory widgetFactory_p,
      IMultipleSemanticFieldController controller_p) {
    super(parent_p, label_p, widgetFactory_p, controller_p);
  }

  /**
   * @see org.polarsys.capella.core.ui.properties.fields.custom.properties.widgets.SimpleEditableSemanticField#loadData(org.polarsys.capella.core.data.information.datatype.BooleanType,
   *      org.eclipse.emf.ecore.EReference)
   */
  public void loadData(CapellaElement semanticElement_p, EStructuralFeature semanticFeature_p, EStructuralFeature compositionFeature_p) {
    super.loadData(semanticElement_p, semanticFeature_p);

    _compositionFeature = compositionFeature_p;
  }

  /**
   * Handle Delete button click event.
   * Reset all data value in this field.
   */
  @Override
  protected void handleDeleteButtonClicked() {
    executeCommand(getDeleteCommand(_semanticElement, (null != _compositionFeature) ? _compositionFeature : _semanticFeature));
  }
}
