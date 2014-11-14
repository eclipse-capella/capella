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
import org.polarsys.capella.core.ui.properties.controllers.ISimpleSemanticFieldController;
import org.polarsys.capella.core.ui.properties.helpers.DialogHelper;
import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;

/**
 */
public class SimpleSemanticField extends BrowseSemanticField {

  protected String _defaultName;

  /**
   * Controller associated to this semantic field.
   */
  protected ISimpleSemanticFieldController _controller;

  /**
   * Constructor.
   * @param parent_p
   * @param label_p
   * @param widgetFactory_p
   * @param controller_p
   */
  public SimpleSemanticField(Composite parent_p, String label_p, TabbedPropertySheetWidgetFactory widgetFactory_p, ISimpleSemanticFieldController controller_p) {
    this(parent_p, label_p, widgetFactory_p, 3, controller_p, null, false);
  }

  /**
   * Constructor.
   * @param parent_p
   * @param label_p
   * @param widgetFactory_p
   * @param textFieldSpan_p
   * @param controller_p
   * @param hasEditBtn_p
   */
  public SimpleSemanticField(Composite parent_p, String label_p, TabbedPropertySheetWidgetFactory widgetFactory_p,
		    int textFieldSpan_p, ISimpleSemanticFieldController controller_p, String defaultName_p, boolean hasEditBtn_p, boolean hasShortcutBtn_p, int cardType)
{
	    super(parent_p, label_p, widgetFactory_p, textFieldSpan_p);
	    setController(controller_p);

	    _defaultName = defaultName_p;

	    if (hasShortcutBtn_p) {
	    	 createShortcutButton(parent_p, cardType);
	      }
	    if (hasEditBtn_p) {
	      createEditButton(parent_p);
	    }
	    createOpenButton(parent_p);
	    createDeleteButton(parent_p); 
}
  
  public SimpleSemanticField(Composite parent_p, String label_p, TabbedPropertySheetWidgetFactory widgetFactory_p,
		    int textFieldSpan_p, ISimpleSemanticFieldController controller_p, String defaultName_p, boolean hasEditBtn_p)
  {
	  this(parent_p, label_p, widgetFactory_p, textFieldSpan_p, controller_p, defaultName_p, hasEditBtn_p, false, 0);
  }
  
  /**
   * Set given controller.
   * @param controller_p
   */
  public void setController(ISimpleSemanticFieldController controller_p) {
    _controller = controller_p;
  }

  /**
   * @see org.polarsys.capella.core.data.core.custom.properties.widgets.SimpleEditableSemanticField#loadData(org.polarsys.capella.core.data.information.datatype.BooleanType,
   *      org.eclipse.emf.ecore.EReference)
   */
  @Override
  public void loadData(CapellaElement semanticElement_p, EStructuralFeature semanticFeature_p) {
    super.loadData(semanticElement_p, semanticFeature_p);

    if (null != _controller) {
      EObject obj = _controller.loadValue(semanticElement_p, semanticFeature_p);
      setValueTextField(obj);
    }
  }

  /**
   * Handle Open button click event.
   * @param button_p
   */
  @Override
  protected void handleOpenButtonClicked(final Button button_p) {
    AbstractReadWriteCommand command = new AbstractReadWriteCommand() {
      public void run() {
        List<EObject> list = _controller.readOpenValues(_semanticElement, _semanticFeature);
        // calling selection wizard
        EObject firstResult = DialogHelper.openSimpleSelectionDialog(button_p, list);
        if (null != firstResult) {
          EObject obj = _controller.writeOpenValue(_semanticElement, _semanticFeature, _defaultName, firstResult);
          setValueTextField(obj);
        }
      }

      @Override
      public String getName() {
        return "Edit " + _semanticElement.eGet(ModellingcorePackage.eINSTANCE.getAbstractNamedElement_Name()); //$NON-NLS-1$
      }
    };
    executeCommand(command);
  }
}
