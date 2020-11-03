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
 * The DataType customized section class.
 */
public class MultiplicityElementBooleanPropertiesCheckbox extends AbstractSemanticCheckboxGroup {
  private Button _isOrderededBtn;
  private Button _isUniqueBtn;
  private Button _isMinInclusiveBtn;
  private Button _isMaxInclusiveBtn;

  /**
   * Constructor.
   * @param parent
   * @param widgetFactory
   */
  public MultiplicityElementBooleanPropertiesCheckbox(Composite parent, TabbedPropertySheetWidgetFactory widgetFactory) {
    this(parent, widgetFactory, true, true, true, true);
  }

  /**
   * Constructor.
   * @param parent
   * @param widgetFactory
   * @param showIsOrdered
   * @param showIsUnique
   * @param showIsMinInclusive
   * @param showIsMaxInclusive
   */
  public MultiplicityElementBooleanPropertiesCheckbox(Composite parent, TabbedPropertySheetWidgetFactory widgetFactory, boolean showIsOrdered,
      boolean showIsUnique, boolean showIsMinInclusive, boolean showIsMaxInclusive) {
    super(parent, widgetFactory);

    if (showIsOrdered) {
      _isOrderededBtn =
          createButton(InformationPackage.Literals.MULTIPLICITY_ELEMENT__ORDERED,
              Messages.getString("MultiplicityElement.IsOrderedLabel"), parent); //$NON-NLS-1$  
    }
    if (showIsUnique) {
      _isUniqueBtn =
          createButton(InformationPackage.Literals.MULTIPLICITY_ELEMENT__UNIQUE,
              Messages.getString("MultiplicityElement.IsUniqueLabel"), parent); //$NON-NLS-1$ 
    }
    if (showIsMinInclusive) {
      _isMinInclusiveBtn =
          createButton(InformationPackage.Literals.MULTIPLICITY_ELEMENT__MIN_INCLUSIVE,
              Messages.getString("MultiplicityElement.IsMinInclusiveLabel"), parent); //$NON-NLS-1$ 
    }
    if (showIsMaxInclusive) {
      _isMaxInclusiveBtn =
          createButton(InformationPackage.Literals.MULTIPLICITY_ELEMENT__MAX_INCLUSIVE,
              Messages.getString("MultiplicityElement.IsMaxInclusiveLabel"), parent); //$NON-NLS-1$ 
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<Button> getSemanticFields() {
    List<Button> fields = new ArrayList<Button>();

    fields.add(_isOrderededBtn);
    fields.add(_isUniqueBtn);
    fields.add(_isMinInclusiveBtn);
    fields.add(_isMaxInclusiveBtn);

    return fields;
  }
}
