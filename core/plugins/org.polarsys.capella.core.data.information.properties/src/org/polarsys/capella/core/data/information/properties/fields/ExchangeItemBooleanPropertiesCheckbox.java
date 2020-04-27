/*******************************************************************************
 * Copyright (c) 2006, 2015 THALES GLOBAL SERVICES.
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

import java.util.List;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;
import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;
import org.polarsys.capella.core.data.core.properties.fields.GeneralizableElementBooleanPropertiesCheckbox;
import org.polarsys.capella.core.data.information.properties.Messages;

/**
 */
public class ExchangeItemBooleanPropertiesCheckbox extends GeneralizableElementBooleanPropertiesCheckbox {
  private Button _isFinalBtn;

  /**
   * Constructor.
   * @param parent
   * @param widgetFactory
   */
  public ExchangeItemBooleanPropertiesCheckbox(Composite parent, TabbedPropertySheetWidgetFactory widgetFactory) {
    this(parent, widgetFactory, true, true);
  }

  /**
   * Constructor.
   * @param parent
   * @param widgetFactory
   * @param showIsAbstract
   * @param showIsFinal
   */
  public ExchangeItemBooleanPropertiesCheckbox(Composite parent, TabbedPropertySheetWidgetFactory widgetFactory, boolean showIsAbstract, boolean showIsFinal) {
    super(parent, widgetFactory, false);
    if (showIsFinal) {
    	_isFinalBtn = createButton(ModellingcorePackage.Literals.FINALIZABLE_ELEMENT__FINAL, Messages.getString("Class.IsFinalLabel"), parent); //$NON-NLS-1$    	
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<Button> getSemanticFields() {
    List<Button> fields = super.getSemanticFields();
    fields.add(_isFinalBtn);
    if (null != _isFinalBtn) {
      fields.add(_isFinalBtn);
    }
    return fields;
  }
}
