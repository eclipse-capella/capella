/*******************************************************************************
 * Copyright (c) 2019, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.data.cs.properties.fields;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.cs.properties.sections.Messages;
import org.polarsys.capella.core.ui.properties.fields.AbstractSemanticCheckboxGroup;

/**
 *
 */
public class IsHumanCheckbox extends AbstractSemanticCheckboxGroup {
  private Button isHumanBtn;

  /**
   * Constructor.
   * @param parent
   * @param widgetFactory
   */
  public IsHumanCheckbox(Composite parent, TabbedPropertySheetWidgetFactory widgetFactory) {
    this(parent, widgetFactory, true);
  }

  /**
   * Constructor.
   * @param parent
   * @param widgetFactory
   * @param showIsHuman
   */
  public IsHumanCheckbox(Composite parent, TabbedPropertySheetWidgetFactory widgetFactory, boolean showIsHuman) {
    super(parent, widgetFactory);

    if (showIsHuman) {
      isHumanBtn = createButton(CsPackage.Literals.COMPONENT__HUMAN, Messages.Component_IsHuman_Label, parent); //$NON-NLS-1$ 
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<Button> getSemanticFields() {
    List<Button> fields = new ArrayList<>();

    if (null != isHumanBtn) {
      fields.add(isHumanBtn);
    }

    return fields;
  }

  public boolean isEnabled() {
    return isHumanBtn.isEnabled();
  }
}
