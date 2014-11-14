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
   * @param parent_p
   * @param widgetFactory_p
   */
  public MultiplicityElementBooleanPropertiesCheckbox(Composite parent_p, TabbedPropertySheetWidgetFactory widgetFactory_p) {
    this(parent_p, widgetFactory_p, true, true, true, true);
  }

  /**
   * Constructor.
   * @param parent_p
   * @param widgetFactory_p
   * @param showIsOrdered_p
   * @param showIsUnique_p
   * @param showIsMinInclusive_p
   * @param showIsMaxInclusive_p
   */
  public MultiplicityElementBooleanPropertiesCheckbox(Composite parent_p, TabbedPropertySheetWidgetFactory widgetFactory_p, boolean showIsOrdered_p,
      boolean showIsUnique_p, boolean showIsMinInclusive_p, boolean showIsMaxInclusive_p) {
    super(parent_p, widgetFactory_p);

    if (showIsOrdered_p) {
      _isOrderededBtn =
          createButton(InformationPackage.Literals.MULTIPLICITY_ELEMENT__ORDERED,
              Messages.getString("MultiplicityElement.IsOrderedLabel"), parent_p); //$NON-NLS-1$  
    }
    if (showIsUnique_p) {
      _isUniqueBtn =
          createButton(InformationPackage.Literals.MULTIPLICITY_ELEMENT__UNIQUE,
              Messages.getString("MultiplicityElement.IsUniqueLabel"), parent_p); //$NON-NLS-1$ 
    }
    if (showIsMinInclusive_p) {
      _isMinInclusiveBtn =
          createButton(InformationPackage.Literals.MULTIPLICITY_ELEMENT__MIN_INCLUSIVE,
              Messages.getString("MultiplicityElement.IsMinInclusiveLabel"), parent_p); //$NON-NLS-1$ 
    }
    if (showIsMaxInclusive_p) {
      _isMaxInclusiveBtn =
          createButton(InformationPackage.Literals.MULTIPLICITY_ELEMENT__MAX_INCLUSIVE,
              Messages.getString("MultiplicityElement.IsMaxInclusiveLabel"), parent_p); //$NON-NLS-1$ 
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
