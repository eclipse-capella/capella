/*******************************************************************************
 * Copyright (c) 2006, 2015 THALES GLOBAL SERVICES.
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
   * @param parent
   * @param label
   * @param widgetFactory
   * @param controller
   */
  public CompositionMultipleSemanticField(Composite parent, String label, TabbedPropertySheetWidgetFactory widgetFactory,
      IMultipleSemanticFieldController controller) {
    super(parent, label, widgetFactory, controller);
  }

  /**
   * @see org.polarsys.capella.core.ui.properties.fields.custom.properties.widgets.SimpleEditableSemanticField#loadData(org.polarsys.capella.core.data.information.datatype.BooleanType,
   *      org.eclipse.emf.ecore.EReference)
   */
  public void loadData(CapellaElement semanticElement, EStructuralFeature semanticFeature, EStructuralFeature compositionFeature) {
    super.loadData(semanticElement, semanticFeature);

    _compositionFeature = compositionFeature;
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
