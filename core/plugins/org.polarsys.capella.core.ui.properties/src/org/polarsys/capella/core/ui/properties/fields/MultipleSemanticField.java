/*******************************************************************************
 * Copyright (c) 2006, 2019 THALES GLOBAL SERVICES.
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

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;
import org.polarsys.capella.core.model.utils.NamingHelper;
import org.polarsys.capella.core.ui.properties.controllers.IMultipleSemanticFieldController;
import org.polarsys.capella.core.ui.properties.helpers.DialogHelper;

/**
 */
public class MultipleSemanticField extends BrowseSemanticField {
  /**
   * Controller associated to this semantic field.
   */
  protected IMultipleSemanticFieldController _controller;

  /**
   * Constructor.
   * @param parent
   * @param label
   * @param widgetFactory
   * @param controller
   */
  public MultipleSemanticField(Composite parent, String label, TabbedPropertySheetWidgetFactory widgetFactory,
      IMultipleSemanticFieldController controller) {
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
  public MultipleSemanticField(Composite parent, String label, TabbedPropertySheetWidgetFactory widgetFactory, int textFieldSpan,
      IMultipleSemanticFieldController controller, boolean hasAddBtn) {
    super(parent, label, widgetFactory, textFieldSpan);

    _controller = controller;

    if (hasAddBtn) {
      createAddButton(parent);
    }
    createOpenButton(parent);
    createDeleteButton(parent);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void loadData(EObject semanticElement, EStructuralFeature semanticFeature) {
    super.loadData(semanticElement, semanticFeature);

    List<EObject> values = _controller.loadValues(semanticElement, semanticFeature);
    setValueTextField(values);
  }

  /**
   * Handle Open button click event.
   * @param button
   */
  @Override
  protected void handleOpenButtonClicked(final Button button) {
    AbstractReadWriteCommand command = new AbstractReadWriteCommand() {
      public void run() {
        List<EObject> currentElements = _controller.readOpenValues(semanticElement, semanticFeature, false);
        List<EObject> availableElements = _controller.readOpenValues(semanticElement, semanticFeature, true);
        availableElements.removeAll(currentElements);

        String title = NamingHelper.getDefaultTitle(semanticElement);
        String message = NamingHelper.getDefaultMessage(semanticElement, (semanticFeature != null) ? semanticFeature.getName() : ""); //$NON-NLS-1$
        
        // calling selection wizard
        List<EObject> allResults = openTransferDialog(button, currentElements, availableElements, title, message);
        if (null != allResults) {
          List<EObject> writeOpenValues = _controller.writeOpenValues(semanticElement, semanticFeature, allResults);
          // Update the widget according to user selection.
          setValueTextField(writeOpenValues);
        }
      }
    };
    executeCommand(command);
  }

  /**
   * Open standard transfer dialog.<br>
   * @param button
   * @param currentElements
   * @param availableElements
   * @param title
   * @param message
   * @return
   */
  protected List<EObject> openTransferDialog(Button button, List<EObject> currentElements, List<EObject> availableElements, String title, String message) {
    return DialogHelper.openTransferDialog(button, currentElements, availableElements, title, message);
  }
}
