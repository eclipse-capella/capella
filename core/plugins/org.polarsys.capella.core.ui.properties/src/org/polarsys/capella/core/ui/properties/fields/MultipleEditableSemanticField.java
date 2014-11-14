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
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;

import org.polarsys.capella.core.ui.properties.controllers.IMultipleEditableSemanticFieldController;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;

/**
 */
public class MultipleEditableSemanticField extends MultipleSemanticField {

  /**
   * Constructor.
   * @param parent_p
   * @param label_p
   * @param widgetFactory_p
   * @param controller_p
   */
  public MultipleEditableSemanticField(Composite parent_p, String label_p, TabbedPropertySheetWidgetFactory widgetFactory_p,
      IMultipleEditableSemanticFieldController controller_p)
  {
    super(parent_p, label_p, widgetFactory_p, 1, controller_p, true);
  }

  /**
   * Handle Add button click event.
   */
  @Override
  protected void handleAddButtonClicked() throws EditableSemanticFieldException {
    AbstractReadWriteCommand command = new AbstractReadWriteCommand() {
      public void run() {
        List<EObject> values = ((IMultipleEditableSemanticFieldController) _controller).addValue(_semanticElement, _semanticFeature);
        // Update the widget according to user selection.
        setValueTextField(values);
      }
    };
    executeCommand(command);
  }
}
