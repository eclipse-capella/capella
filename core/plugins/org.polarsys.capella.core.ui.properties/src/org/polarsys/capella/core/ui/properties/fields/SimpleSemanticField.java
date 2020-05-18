/*******************************************************************************
 * Copyright (c) 2006, 2018 THALES GLOBAL SERVICES.
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
import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;
import org.polarsys.capella.core.ui.properties.controllers.ISimpleSemanticFieldController;
import org.polarsys.capella.core.ui.properties.helpers.DialogHelper;

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
   * @param parent
   * @param label
   * @param widgetFactory
   * @param controller
   */
  public SimpleSemanticField(Composite parent, String label, TabbedPropertySheetWidgetFactory widgetFactory, ISimpleSemanticFieldController controller) {
    this(parent, label, widgetFactory, 3, controller, null, false);
  }

  /**
   * Constructor.
   * @param parent
   * @param label
   * @param widgetFactory
   * @param textFieldSpan
   * @param controller
   * @param hasEditBtn
   */
  public SimpleSemanticField(Composite parent, String label, TabbedPropertySheetWidgetFactory widgetFactory,
		    int textFieldSpan, ISimpleSemanticFieldController controller, String defaultName, boolean hasEditBtn, boolean hasShortcutBtn, int cardType)
{
	    super(parent, label, widgetFactory, textFieldSpan);
	    setController(controller);

	    _defaultName = defaultName;

	    if (hasShortcutBtn) {
	    	 createShortcutButton(parent, cardType);
	      }
	    if (hasEditBtn) {
	      createEditButton(parent);
	    }
	    createOpenButton(parent);
	    createDeleteButton(parent); 
}
  
  public SimpleSemanticField(Composite parent, String label, TabbedPropertySheetWidgetFactory widgetFactory,
		    int textFieldSpan, ISimpleSemanticFieldController controller, String defaultName, boolean hasEditBtn)
  {
	  this(parent, label, widgetFactory, textFieldSpan, controller, defaultName, hasEditBtn, false, 0);
  }
  
  /**
   * Set given controller.
   * @param controller
   */
  public void setController(ISimpleSemanticFieldController controller) {
    _controller = controller;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void loadData(EObject semanticElement, EStructuralFeature semanticFeature) {
    super.loadData(semanticElement, semanticFeature);

    if (null != _controller) {
      EObject obj = _controller.loadValue(semanticElement, semanticFeature);
      setValueTextField(obj);
    }
  }

  /**
   * Handle Open button click event.
   * @param button
   */
  @Override
  protected void handleOpenButtonClicked(final Button button) {
    AbstractReadWriteCommand command = new AbstractReadWriteCommand() {
      public void run() {
        List<EObject> list = _controller.readOpenValues(semanticElement, semanticFeature);
        // calling selection wizard
        EObject firstResult = DialogHelper.openSimpleSelectionDialog(button, list);
        if (null != firstResult) {
          EObject obj = _controller.writeOpenValue(semanticElement, semanticFeature, _defaultName, firstResult);
          setValueTextField(obj);
        }
      }

      @Override
      public String getName() {
        return "Edit " + semanticElement.eGet(ModellingcorePackage.eINSTANCE.getAbstractNamedElement_Name()); //$NON-NLS-1$
      }
    };
    executeCommand(command);
  }
}
