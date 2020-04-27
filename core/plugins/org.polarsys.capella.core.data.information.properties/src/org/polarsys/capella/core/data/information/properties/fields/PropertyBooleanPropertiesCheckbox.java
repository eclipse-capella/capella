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
   * @param parent
   * @param style
   */
  public PropertyBooleanPropertiesCheckbox(Composite parent, TabbedPropertySheetWidgetFactory widgetFactory) {
    this(parent, widgetFactory, true, true, true);
  }

  /**
   * Constructor.
   * @param parent
   * @param widgetFactory
   * @param showIsDerived
   * @param showIsPartOfKey
   * @param showIsReadOnly
   */
  public PropertyBooleanPropertiesCheckbox(Composite parent, TabbedPropertySheetWidgetFactory widgetFactory, boolean showIsDerived,
      boolean showIsPartOfKey, boolean showIsReadOnly) {
    super(parent, widgetFactory);

    if (showIsPartOfKey) {
      _isPartOfKeyBtn = createButton(InformationPackage.Literals.PROPERTY__IS_PART_OF_KEY, Messages.getString("Property.IsPartOfKeyLabel"), parent); //$NON-NLS-1$
    }
    if (showIsDerived) {
      _isDerivedBtn = createButton(InformationPackage.Literals.PROPERTY__IS_DERIVED, Messages.getString("Property.IsDerivedlabel"), parent); //$NON-NLS-1$
    }
    if (showIsReadOnly) {
      _isReadOnlyBtn = createButton(InformationPackage.Literals.PROPERTY__IS_READ_ONLY, Messages.getString("Property.IsReadOnlyLabel"), parent); //$NON-NLS-1$ 
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
