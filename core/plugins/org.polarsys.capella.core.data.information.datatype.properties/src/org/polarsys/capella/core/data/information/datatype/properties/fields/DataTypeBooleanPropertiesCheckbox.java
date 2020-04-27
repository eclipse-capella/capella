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
package org.polarsys.capella.core.data.information.datatype.properties.fields;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;

import org.polarsys.capella.core.data.information.datatype.DatatypePackage;
import org.polarsys.capella.core.data.information.datatype.properties.Messages;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.ui.properties.fields.AbstractSemanticCheckboxGroup;
import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;

/**
 * The DataType customized section class.
 */
public class DataTypeBooleanPropertiesCheckbox extends AbstractSemanticCheckboxGroup {
  private Button _isFinalBtn;
  private Button _isAbstractBtn;
  private Button _isDiscreteBtn;
  private Button _isMinInclusiveBtn;
  private Button _isMaxInclusiveBtn;

  /**
   * Constructor.
   * @param parent
   * @param style
   * @param widgetFactory
   */
  public DataTypeBooleanPropertiesCheckbox(Composite parent, TabbedPropertySheetWidgetFactory widgetFactory) {
    this(parent, widgetFactory, true);
  }

  /**
   * Constructor.
   * @param parent
   * @param style
   * @param widgetFactory
   * @param showInclusiveFields
   */
  public DataTypeBooleanPropertiesCheckbox(Composite parent, TabbedPropertySheetWidgetFactory widgetFactory, boolean showInclusiveFields) {
    super(parent, widgetFactory);

    _isFinalBtn = createButton(ModellingcorePackage.Literals.FINALIZABLE_ELEMENT__FINAL, Messages.getString("DataType.IsFinalLabel"), parent); //$NON-NLS-1$  
    _isAbstractBtn = createButton(CapellacorePackage.Literals.GENERALIZABLE_ELEMENT__ABSTRACT, Messages.getString("DataType.IsAbstractLabel"), parent); //$NON-NLS-1$ 
    _isDiscreteBtn = createButton(DatatypePackage.Literals.DATA_TYPE__DISCRETE, Messages.getString("DataType.IsDiscreteLabel"), parent); //$NON-NLS-1$
    if (showInclusiveFields) {
      _isMinInclusiveBtn = createButton(DatatypePackage.Literals.DATA_TYPE__MIN_INCLUSIVE, Messages.getString("DataType.IsMinInclusiveLabel"), parent); //$NON-NLS-1$  
      _isMaxInclusiveBtn = createButton(DatatypePackage.Literals.DATA_TYPE__MAX_INCLUSIVE, Messages.getString("DataType.IsMaxInclusiveLabel"), parent); //$NON-NLS-1$  
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<Button> getSemanticFields() {
    List<Button> fields = new ArrayList<Button>();

    fields.add(_isFinalBtn);
    fields.add(_isAbstractBtn);
    fields.add(_isDiscreteBtn);
    fields.add(_isMinInclusiveBtn);
    fields.add(_isMaxInclusiveBtn);

    return fields;
  }
}
