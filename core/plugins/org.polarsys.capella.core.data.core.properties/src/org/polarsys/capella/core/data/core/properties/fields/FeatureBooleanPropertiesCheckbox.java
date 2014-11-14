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
 * The DataType customized section class.
 */
public class FeatureBooleanPropertiesCheckbox extends AbstractSemanticCheckboxGroup {
  private Button _isAbstractBtn;
  private Button _isStaticBtn;

  /**
   * Constructor.
   * @param parent_p
   * @param widgetFactory_p
   */
  public FeatureBooleanPropertiesCheckbox(Composite parent_p, TabbedPropertySheetWidgetFactory widgetFactory_p) {
    this(parent_p, widgetFactory_p, true, true);
  }

  /**
   * Constructor.
   * @param parent_p
   * @param widgetFactory_p
   * @param showIsAbstract_p
   * @param showIsStatic_p
   */
  public FeatureBooleanPropertiesCheckbox(Composite parent_p, TabbedPropertySheetWidgetFactory widgetFactory_p, boolean showIsAbstract_p, boolean showIsStatic_p) {
    super(parent_p, widgetFactory_p);

    if (showIsAbstract_p) {
      _isAbstractBtn = createButton(CapellacorePackage.Literals.FEATURE__IS_ABSTRACT, Messages.getString("Feature.IsAbstractLabel"), parent_p); //$NON-NLS-1$ 
    }
    if (showIsStatic_p) {
      _isStaticBtn = createButton(CapellacorePackage.Literals.FEATURE__IS_STATIC, Messages.getString("Feature.IsStaticLabel"), parent_p); //$NON-NLS-1$ 
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<Button> getSemanticFields() {
    List<Button> fields = new ArrayList<Button>();

    fields.add(_isAbstractBtn);
    fields.add(_isStaticBtn);

    return fields;
  }
}
