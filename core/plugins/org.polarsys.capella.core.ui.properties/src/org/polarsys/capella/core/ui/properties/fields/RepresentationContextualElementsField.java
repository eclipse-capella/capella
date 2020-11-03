/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.ui.properties.fields;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;
import org.polarsys.capella.common.ef.ExecutionManager;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.core.model.utils.NamingHelper;
import org.polarsys.capella.core.ui.properties.controllers.RepresentationContextualElementsController;
import org.polarsys.capella.core.ui.properties.helpers.DialogHelper;

/**
 */
public class RepresentationContextualElementsField extends BrowseSemanticField {

  protected WeakReference<DRepresentationDescriptor> descriptor;

  /**
   * Controller associated to this semantic field.
   */
  protected RepresentationContextualElementsController controller;

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

    this.controller = controller;

    if (hasAddBtn) {
      createAddButton(parent);
    }
    createOpenButton(parent);
    createDeleteButton(parent);
  }

  /**
   * @param dRepresentation
   */
  public void loadData(DRepresentationDescriptor dRepresentation) {
    descriptor = new WeakReference<>(dRepresentation);
    setValueTextField(controller.loadValues(dRepresentation));
  }

  @Override
  protected ExecutionManager getExecutionManager() {
    return TransactionHelper.getExecutionManager(descriptor.get());
  }

  /**
   * Handle Open button click event.
   * @param button
   */
  @Override
  protected void handleOpenButtonClicked(final Button button) {
    AbstractReadWriteCommand command = new AbstractReadWriteCommand() {
      public void run() {
        List<EObject> currentElements = controller.readOpenValues(descriptor.get(), false);
        List<EObject> availableElements = controller.readOpenValues(descriptor.get(), true);
        availableElements.removeAll(currentElements);

        String title = descriptor.get().getName();
        String message = NamingHelper.getDefaultMessage(descriptor.get(), "contextual elements"); //$NON-NLS-1$

        // calling selection wizard
        List<EObject> allResults = DialogHelper.openTransferDialog(button, currentElements, availableElements, title, message);
        if (allResults != null) {
          List<EObject> writeOpenValues = controller.writeOpenValues(descriptor.get(), allResults);
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
        List<EObject> allResults = new ArrayList<>();
        List<EObject> writeOpenValues = controller.writeOpenValues(descriptor.get(), allResults);
        // Update the widget according to user selection.
        setValueTextField(writeOpenValues);
      }
    };
    executeCommand(command);
  }
}
