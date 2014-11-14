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

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;

import org.polarsys.capella.core.ui.properties.controllers.RepresentationContextualElementsController;
import org.polarsys.capella.core.ui.properties.helpers.DialogHelper;
import org.polarsys.capella.core.ui.properties.helpers.NamingHelper;
import org.polarsys.capella.common.tig.ef.command.AbstractReadWriteCommand;

/**
 */
public class RepresentationContextualElementsField extends BrowseSemanticField {

  protected WeakReference<DRepresentation> _representation;

  /**
   * Controller associated to this semantic field.
   */
  protected RepresentationContextualElementsController _controller;

  /**
   * Constructor.
   * @param parent_p
   * @param label_p
   * @param widgetFactory_p
   * @param controller_p
   */
  public RepresentationContextualElementsField(Composite parent_p, String label_p, TabbedPropertySheetWidgetFactory widgetFactory_p,
      RepresentationContextualElementsController controller_p) {
    this(parent_p, label_p, widgetFactory_p, 2, controller_p, false);
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
  public RepresentationContextualElementsField(Composite parent_p, String label_p, TabbedPropertySheetWidgetFactory widgetFactory_p, int textFieldSpan_p,
      RepresentationContextualElementsController controller_p, boolean hasAddBtn_p) {
    super(parent_p, label_p, widgetFactory_p, textFieldSpan_p);

    _controller = controller_p;

    if (hasAddBtn_p) {
      createAddButton(parent_p);
    }
    createOpenButton(parent_p);
    createDeleteButton(parent_p);
  }

  /**
   * @param dRepresentation_p
   */
  public void loadData(DRepresentation dRepresentation_p) {
    _representation = new WeakReference<DRepresentation>(dRepresentation_p);
    setValueTextField(_controller.loadValues(dRepresentation_p));
  }

  /**
   * Handle Open button click event.
   * @param button_p
   */
  @Override
  protected void handleOpenButtonClicked(final Button button_p) {
    AbstractReadWriteCommand command = new AbstractReadWriteCommand() {
      public void run() {
        List<EObject> currentElements = _controller.readOpenValues(_representation.get(), false);
        List<EObject> availableElements = _controller.readOpenValues(_representation.get(), true);
        availableElements.removeAll(currentElements);

        String title = _representation.get().getName();
        String message = NamingHelper.getDefaultMessage(_representation.get(), "contextual elements"); //$NON-NLS-1$

        // calling selection wizard
        List<EObject> allResults = DialogHelper.openTransferDialog(button_p, currentElements, availableElements, title, message);
        if (allResults != null) {
          List<EObject> writeOpenValues = _controller.writeOpenValues(_representation.get(), allResults);
          // Update the widget according to user selection.
          setValueTextField(writeOpenValues);
        }
      }
    };
    executeCommand(command);
  }

  /**
   * Handle Delete button click event. Reset all data value in this field.
   */
  @Override
  protected void handleDeleteButtonClicked() {
    AbstractReadWriteCommand command = new AbstractReadWriteCommand() {
      public void run() {
        List<EObject> allResults = new ArrayList<EObject>();
        if (allResults != null) {
          List<EObject> writeOpenValues = _controller.writeOpenValues(_representation.get(), allResults);
          // Update the widget according to user selection.
          setValueTextField(writeOpenValues);
        }
      }
    };
    executeCommand(command);
  }
}
