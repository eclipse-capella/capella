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

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;
import org.polarsys.capella.common.ef.ExecutionManager;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.core.ui.properties.controllers.RepresentationContextualElementsController;
import org.polarsys.capella.core.ui.properties.helpers.DialogHelper;
import org.polarsys.capella.core.ui.properties.helpers.NamingHelper;

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
   * @param parent
   * @param label
   * @param widgetFactory
   * @param controller
   */
  public RepresentationContextualElementsField(Composite parent, String label, TabbedPropertySheetWidgetFactory widgetFactory,
      RepresentationContextualElementsController controller) {
    this(parent, label, widgetFactory, 3, controller, false);
  }

  /**
   * Constructor.
   * @param parent
   * @param label
   * @param widgetFactory
   * @param textFieldSpan
   * @param controller
   * @param hasAddBtn
   */
  public RepresentationContextualElementsField(Composite parent, String label, TabbedPropertySheetWidgetFactory widgetFactory, int textFieldSpan,
      RepresentationContextualElementsController controller, boolean hasAddBtn) {
    super(parent, label, widgetFactory, textFieldSpan);

    _controller = controller;

    if (hasAddBtn) {
      createAddButton(parent);
    }
    createOpenButton(parent);
    createDeleteButton(parent);
  }

  /**
   * @param dRepresentation
   */
  public void loadData(DRepresentation dRepresentation) {
    _representation = new WeakReference<DRepresentation>(dRepresentation);
    setValueTextField(_controller.loadValues(dRepresentation));
  }

  @Override
  protected ExecutionManager getExecutionManager() {
    return TransactionHelper.getExecutionManager(_representation.get());
  }

  /**
   * Handle Open button click event.
   * @param button
   */
  @Override
  protected void handleOpenButtonClicked(final Button button) {
    AbstractReadWriteCommand command = new AbstractReadWriteCommand() {
      public void run() {
        List<EObject> currentElements = _controller.readOpenValues(_representation.get(), false);
        List<EObject> availableElements = _controller.readOpenValues(_representation.get(), true);
        availableElements.removeAll(currentElements);

        String title = _representation.get().getName();
        String message = NamingHelper.getDefaultMessage(_representation.get(), "contextual elements"); //$NON-NLS-1$

        // calling selection wizard
        List<EObject> allResults = DialogHelper.openTransferDialog(button, currentElements, availableElements, title, message);
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
