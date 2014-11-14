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
public class PropertyBooleanPropertiesCheckbox extends AbstractSemanticCheckboxGroup {
  private Button _isDerivedBtn;
  private Button _isPartOfKeyBtn;
  private Button _isReadOnlyBtn;

  /**
   * Constructor.
   * @param parent_p
   * @param style_p
   */
  public PropertyBooleanPropertiesCheckbox(Composite parent_p, TabbedPropertySheetWidgetFactory widgetFactory_p) {
    this(parent_p, widgetFactory_p, true, true, true);
  }

  /**
   * Constructor.
   * @param parent_p
   * @param widgetFactory_p
   * @param showIsDerived_p
   * @param showIsPartOfKey_p
   * @param showIsReadOnly_p
   */
  public PropertyBooleanPropertiesCheckbox(Composite parent_p, TabbedPropertySheetWidgetFactory widgetFactory_p, boolean showIsDerived_p,
      boolean showIsPartOfKey_p, boolean showIsReadOnly_p) {
    super(parent_p, widgetFactory_p);

    if (showIsPartOfKey_p) {
      _isPartOfKeyBtn = createButton(InformationPackage.Literals.PROPERTY__IS_PART_OF_KEY, Messages.getString("Property.IsPartOfKeyLabel"), parent_p); //$NON-NLS-1$
    }
    if (showIsDerived_p) {
      _isDerivedBtn = createButton(InformationPackage.Literals.PROPERTY__IS_DERIVED, Messages.getString("Property.IsDerivedlabel"), parent_p); //$NON-NLS-1$
    }
    if (showIsReadOnly_p) {
      _isReadOnlyBtn = createButton(InformationPackage.Literals.PROPERTY__IS_READ_ONLY, Messages.getString("Property.IsReadOnlyLabel"), parent_p); //$NON-NLS-1$ 
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<Button> getSemanticFields() {
    List<Button> fields = new ArrayList<Button>();

    fields.add(_isDerivedBtn);
    fields.add(_isPartOfKeyBtn);
    fields.add(_isReadOnlyBtn);

    return fields;
  }
}
