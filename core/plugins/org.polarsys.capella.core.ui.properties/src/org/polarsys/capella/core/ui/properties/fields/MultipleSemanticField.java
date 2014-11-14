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

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;

import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.ui.properties.controllers.IMultipleSemanticFieldController;
import org.polarsys.capella.core.ui.properties.helpers.DialogHelper;
import org.polarsys.capella.core.ui.properties.helpers.NamingHelper;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;

/**
 */
public class MultipleSemanticField extends BrowseSemanticField {
  /**
   * Controller associated to this semantic field.
   */
  protected IMultipleSemanticFieldController _controller;

  /**
   * Constructor.
   * @param parent_p
   * @param label_p
   * @param widgetFactory_p
   * @param controller_p
   */
  public MultipleSemanticField(Composite parent_p, String label_p, TabbedPropertySheetWidgetFactory widgetFactory_p,
      IMultipleSemanticFieldController controller_p) {
    this(parent_p, label_p, widgetFactory_p, 3, controller_p, false);
  }

  /**
   * Constructor.
   * @param parent_p
   * @param label_p
   * @param widgetFactory_p
   * @param textFieldSpan_p
   * @param controller_p
   * @param hasAddBtn_p
   */
  public MultipleSemanticField(Composite parent_p, String label_p, TabbedPropertySheetWidgetFactory widgetFactory_p, int textFieldSpan_p,
      IMultipleSemanticFieldController controller_p, boolean hasAddBtn_p) {
    super(parent_p, label_p, widgetFactory_p, textFieldSpan_p);

    _controller = controller_p;

    if (hasAddBtn_p) {
      createAddButton(parent_p);
    }
    createOpenButton(parent_p);
    createDeleteButton(parent_p);
  }

  /**
   * @see org.polarsys.capella.core.ui.properties.fields.custom.properties.widgets.SimpleEditableSemanticField#loadData(org.polarsys.capella.core.data.information.datatype.BooleanType,
   *      org.eclipse.emf.ecore.EReference)
   */
  @Override
  public void loadData(CapellaElement semanticElement_p, EStructuralFeature semanticFeature_p) {
    super.loadData(semanticElement_p, semanticFeature_p);

    List<EObject> values = _controller.loadValues(semanticElement_p, semanticFeature_p);
    setValueTextField(values);
  }

  /**
   * Handle Open button click event.
   * @param button_p
   */
  @Override
  protected void handleOpenButtonClicked(final Button button_p) {
    AbstractReadWriteCommand command = new AbstractReadWriteCommand() {
      public void run() {
        List<EObject> currentElements = _controller.readOpenValues(_semanticElement, _semanticFeature, false);
        List<EObject> availableElements = _controller.readOpenValues(_semanticElement, _semanticFeature, true);
        availableElements.removeAll(currentElements);

        String title = NamingHelper.getDefaultTitle(_semanticElement);
        String message = NamingHelper.getDefaultMessage(_semanticElement, (_semanticFeature != null) ? _semanticFeature.getName() : ""); //$NON-NLS-1$
        
        // calling selection wizard
        List<EObject> allResults = openTransferDialog(button_p, currentElements, availableElements, title, message);
        if (null != allResults) {
          List<EObject> writeOpenValues = _controller.writeOpenValues(_semanticElement, _semanticFeature, allResults);
          // Update the widget according to user selection.
          setValueTextField(writeOpenValues);
        }
      }
    };
    executeCommand(command);
  }

  /**
   * Open standard transfer dialog.<br>
   * @param button_p
   * @param currentElements
   * @param availableElements
   * @param title
   * @param message
   * @return
   */
  protected List<EObject> openTransferDialog(Button button_p, List<EObject> currentElements, List<EObject> availableElements, String title, String message) {
    return DialogHelper.openTransferDialog(button_p, currentElements, availableElements, title, message);
  }
}
