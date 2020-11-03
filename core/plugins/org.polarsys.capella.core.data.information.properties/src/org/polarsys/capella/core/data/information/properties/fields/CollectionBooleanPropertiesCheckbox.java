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

import java.util.List;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;

import org.polarsys.capella.core.data.core.properties.fields.GeneralizableElementBooleanPropertiesCheckbox;
import org.polarsys.capella.core.data.information.InformationPackage;
import org.polarsys.capella.core.data.information.properties.Messages;
import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;

/**
 * The Collection customized section class.
 */
public class CollectionBooleanPropertiesCheckbox extends GeneralizableElementBooleanPropertiesCheckbox {
  private Button _isFinalBtn;
  private Button _isPrimitiveBtn;

  /**
   * Constructor.
   * @param parent
   * @param style
   */
  public CollectionBooleanPropertiesCheckbox(Composite parent, TabbedPropertySheetWidgetFactory widgetFactory) {
    this(parent, widgetFactory, true, true);
  }

  /**
   * Constructor.
   * @param parent
   * @param widgetFactory
   * @param showIsAbstract
   * @param showIsPrimitive
   */
  public CollectionBooleanPropertiesCheckbox(Composite parent, TabbedPropertySheetWidgetFactory widgetFactory, boolean showIsAbstract, boolean showIsPrimitive) {
    super(parent, widgetFactory, showIsAbstract);

    _isFinalBtn = createButton(ModellingcorePackage.Literals.FINALIZABLE_ELEMENT__FINAL, Messages.getString("Collection.IsFinalLabel"), parent); //$NON-NLS-1$
    if (showIsPrimitive) {
      _isPrimitiveBtn = createButton(InformationPackage.Literals.COLLECTION__IS_PRIMITIVE, Messages.getString("Collection.IsPrimitiveLabel"), parent); //$NON-NLS-1$
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<Button> getSemanticFields() {
    List<Button> fields = super.getSemanticFields();

    fields.add(_isFinalBtn);
    if (null != _isPrimitiveBtn) {
      fields.add(_isPrimitiveBtn);
    }

    return fields;
  }
}
