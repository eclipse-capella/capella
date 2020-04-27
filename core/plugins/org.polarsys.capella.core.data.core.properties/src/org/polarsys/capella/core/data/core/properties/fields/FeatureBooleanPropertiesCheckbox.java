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
   * @param parent
   * @param widgetFactory
   */
  public FeatureBooleanPropertiesCheckbox(Composite parent, TabbedPropertySheetWidgetFactory widgetFactory) {
    this(parent, widgetFactory, true, true);
  }

  /**
   * Constructor.
   * @param parent
   * @param widgetFactory
   * @param showIsAbstract
   * @param showIsStatic
   */
  public FeatureBooleanPropertiesCheckbox(Composite parent, TabbedPropertySheetWidgetFactory widgetFactory, boolean showIsAbstract, boolean showIsStatic) {
    super(parent, widgetFactory);

    if (showIsAbstract) {
      _isAbstractBtn = createButton(CapellacorePackage.Literals.FEATURE__IS_ABSTRACT, Messages.getString("Feature.IsAbstractLabel"), parent); //$NON-NLS-1$ 
    }
    if (showIsStatic) {
      _isStaticBtn = createButton(CapellacorePackage.Literals.FEATURE__IS_STATIC, Messages.getString("Feature.IsStaticLabel"), parent); //$NON-NLS-1$ 
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
