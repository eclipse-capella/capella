/*******************************************************************************
 * Copyright (c) 2006, 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.data.core.properties.fields;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;

import org.polarsys.capella.core.data.core.properties.Messages;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.ui.properties.fields.AbstractSemanticCheckboxGroup;

/**
 *
 */
public class GeneralizableElementBooleanPropertiesCheckbox extends AbstractSemanticCheckboxGroup {
  private Button _isAbstractBtn;

  /**
   * Constructor.
   * @param parent
   * @param widgetFactory
   */
  public GeneralizableElementBooleanPropertiesCheckbox(Composite parent, TabbedPropertySheetWidgetFactory widgetFactory) {
    this(parent, widgetFactory, true);
  }

  /**
   * Constructor.
   * @param parent
   * @param widgetFactory
   * @param showIsAbstract
   */
  public GeneralizableElementBooleanPropertiesCheckbox(Composite parent, TabbedPropertySheetWidgetFactory widgetFactory, boolean showIsAbstract) {
    super(parent, widgetFactory);

    if (showIsAbstract) {
      _isAbstractBtn = createButton(CapellacorePackage.Literals.GENERALIZABLE_ELEMENT__ABSTRACT, Messages.getString("GeneralizableElement.IsAbstractLabel"), parent); //$NON-NLS-1$ 
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<Button> getSemanticFields() {
    List<Button> fields = new ArrayList<Button>();

    if (null != _isAbstractBtn) {
      fields.add(_isAbstractBtn);
    }

    return fields;
  }
}
