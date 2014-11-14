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

import java.util.List;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;

import org.polarsys.capella.core.data.core.properties.fields.GeneralizableElementBooleanPropertiesCheckbox;
import org.polarsys.capella.core.data.information.InformationPackage;
import org.polarsys.capella.core.data.information.properties.Messages;
import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;

/**
 */
public class ClassBooleanPropertiesCheckbox extends GeneralizableElementBooleanPropertiesCheckbox {
  private Button _isFinalBtn;
  private Button _isPrimitiveBtn;

  /**
   * Constructor.
   * @param parent_p
   * @param widgetFactory_p
   */
  public ClassBooleanPropertiesCheckbox(Composite parent_p, TabbedPropertySheetWidgetFactory widgetFactory_p) {
    this(parent_p, widgetFactory_p, true, true);
  }

  /**
   * Constructor.
   * @param parent_p
   * @param widgetFactory_p
   * @param showIsAbstract_p
   * @param showIsPrimitive_p
   */
  public ClassBooleanPropertiesCheckbox(Composite parent_p, TabbedPropertySheetWidgetFactory widgetFactory_p, boolean showIsAbstract_p, boolean showIsPrimitive_p) {
    super(parent_p, widgetFactory_p, showIsAbstract_p);

    _isFinalBtn = createButton(ModellingcorePackage.Literals.FINALIZABLE_ELEMENT__FINAL, Messages.getString("Class.IsFinalLabel"), parent_p); //$NON-NLS-1$
    if (showIsPrimitive_p) {
      _isPrimitiveBtn = createButton(InformationPackage.Literals.CLASS__IS_PRIMITIVE, Messages.getString("Class.IsPrimitiveLabel"), parent_p); //$NON-NLS-1$ 
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
