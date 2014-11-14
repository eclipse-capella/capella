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
package org.polarsys.capella.core.data.requirement.properties.fields;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;

import org.polarsys.capella.core.data.requirement.RequirementPackage;
import org.polarsys.capella.core.data.requirement.properties.Messages;
import org.polarsys.capella.core.ui.properties.fields.AbstractSemanticCheckboxGroup;

/**
 *
 */
public class RequirementBooleanPropertiesCheckbox extends AbstractSemanticCheckboxGroup {
  private Button _isObsoleteBtn;

  /**
   * Constructor.
   * @param parent_p
   * @param widgetFactory_p
   */
  public RequirementBooleanPropertiesCheckbox(Composite parent_p, TabbedPropertySheetWidgetFactory widgetFactory_p) {
    this(parent_p, widgetFactory_p, true);
  }

  /**
   * Constructor.
   * @param parent_p
   * @param widgetFactory_p
   * @param showIsObsolete_p
   */
  public RequirementBooleanPropertiesCheckbox(Composite parent_p, TabbedPropertySheetWidgetFactory widgetFactory_p, boolean showIsObsolete_p) {
    super(parent_p, widgetFactory_p);

    if (showIsObsolete_p) {
      _isObsoleteBtn = createButton(RequirementPackage.Literals.REQUIREMENT__IS_OBSOLETE, Messages.getString("Requirement.IsObsolete.Label"), parent_p); //$NON-NLS-1$ 
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<Button> getSemanticFields() {
    List<Button> fields = new ArrayList<Button>();

    fields.add(_isObsoleteBtn);

    return fields;
  }
}
