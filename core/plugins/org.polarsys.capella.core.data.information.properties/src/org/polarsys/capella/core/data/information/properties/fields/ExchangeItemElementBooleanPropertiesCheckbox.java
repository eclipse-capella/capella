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
package org.polarsys.capella.core.data.information.properties.fields;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;

import org.polarsys.capella.core.data.information.InformationPackage;
import org.polarsys.capella.core.data.information.properties.Messages;
import org.polarsys.capella.core.ui.properties.fields.AbstractSemanticCheckboxGroup;

/**
 * The ExchangeItemElement customized section class.
 */
public class ExchangeItemElementBooleanPropertiesCheckbox extends AbstractSemanticCheckboxGroup {
  private Button _isCompositeBtn;

  /**
   * Constructor.
   * @param parent
   * @param widgetFactory
   */
  public ExchangeItemElementBooleanPropertiesCheckbox(Composite parent, TabbedPropertySheetWidgetFactory widgetFactory) {
    super(parent, widgetFactory);

    _isCompositeBtn = createButton(InformationPackage.Literals.EXCHANGE_ITEM_ELEMENT__COMPOSITE,
      Messages.getString("ExchangeItemElement.IsCompositeLabel"), parent); //$NON-NLS-1$  
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<Button> getSemanticFields() {
    List<Button> fields = new ArrayList<Button>();

    fields.add(_isCompositeBtn);

    return fields;
  }
}
