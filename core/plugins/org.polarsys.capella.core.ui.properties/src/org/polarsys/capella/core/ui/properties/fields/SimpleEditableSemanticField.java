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

import org.eclipse.emf.ecore.EObject;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;

import org.polarsys.capella.core.ui.properties.controllers.ISimpleEditableSemanticFieldController;
import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;
import org.polarsys.capella.common.tig.ef.command.AbstractReadWriteCommand;

/**
 */
public class SimpleEditableSemanticField extends SimpleSemanticField {

  /**
   * @param parent_p
   * @param label_p
   * @param widgetFactory_p
   * @param defaultName_p
   * @param controller_p
   */
  public SimpleEditableSemanticField(Composite parent_p, String label_p, TabbedPropertySheetWidgetFactory widgetFactory_p,
      String defaultName_p, ISimpleEditableSemanticFieldController controller_p)
  {
    super(parent_p, label_p, widgetFactory_p, 1, controller_p, defaultName_p, true);
  }

  /**
   * Handle Edit button click event.
   */
  @Override
  protected void handleEditButtonClicked() throws EditableSemanticFieldException {
    AbstractReadWriteCommand command = new AbstractReadWriteCommand() {
      public void run() {
        EObject obj = ((ISimpleEditableSemanticFieldController) _controller).editValue(_semanticElement, _semanticFeature, _defaultName);
        setValueTextField(obj);
      }

      @Override
      public String getName() {
        return "Edit " + _semanticElement.eGet(ModellingcorePackage.eINSTANCE.getAbstractNamedElement_Name()); //$NON-NLS-1$
      }
    };
    executeCommand(command);
  }
}
