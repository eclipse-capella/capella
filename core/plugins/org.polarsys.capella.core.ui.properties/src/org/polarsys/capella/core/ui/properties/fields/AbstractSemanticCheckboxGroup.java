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

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;

import org.polarsys.capella.core.data.capellacore.CapellaElement;


/**
 * Abstract based class to implement a semantic field based on check box buttons.<br>
 * Each check box button is related to a feature.
 */
public abstract class AbstractSemanticCheckboxGroup extends AbstractSemanticButtonGroup {

  /**
   * Constructor.
   * @param parent_p
   * @param widgetFactory_p
   */
  public AbstractSemanticCheckboxGroup(Composite parent_p, TabbedPropertySheetWidgetFactory widgetFactory_p) {
    super(widgetFactory_p);
  }

  /**
   * @see org.polarsys.capella.core.ui.properties.fields.custom.properties.fields.AbstractSemanticField#widgetSelected(org.eclipse.swt.events.SelectionEvent)
   */
  @Override
  public void widgetSelected(SelectionEvent event_p) {
    Button button = (Button) event_p.widget;
    setDataValue(_semanticElement, (EStructuralFeature) button.getData(), button.getSelection());
  }

  /**
   * Create a check box button.
   * @param feature_p
   * @param label_p
   * @param group_p
   * @return a not <code>null</code> object.
   */
  protected Button createButton(EStructuralFeature feature_p, String label_p, Composite group_p) {
    return createButton(group_p, label_p, feature_p, true, SWT.CHECK);
  }

  /**
   * @see org.polarsys.capella.core.ui.properties.fields.custom.properties.fields.AbstractSemanticField#loadData(org.polarsys.capella.core.data.capellacore.CapellaElement)
   */
  @Override
  public void loadData(CapellaElement semanticElement_p) {
    loadData(semanticElement_p, null);

    for (Button button : getSemanticFields()) {
      selectButton(button);
    }
  }

  /**
   * Select (means check here) given check box button according to the feature linked to it as button's data object.
   * @param button_p
   */
  private void selectButton(Button button_p) {
    if (null != button_p && null != _semanticElement) {
      setBooleanValue(button_p, _semanticElement, (EStructuralFeature) button_p.getData());
    }
  }
}
