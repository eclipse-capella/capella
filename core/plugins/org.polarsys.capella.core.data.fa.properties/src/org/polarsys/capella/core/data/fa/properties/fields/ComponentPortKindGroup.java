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
package org.polarsys.capella.core.data.fa.properties.fields;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;

import org.polarsys.capella.core.data.fa.ComponentPortKind;
import org.polarsys.capella.core.data.fa.properties.Messages;
import org.polarsys.capella.core.ui.properties.fields.AbstractSemanticKindGroup;

/**
 */
public class ComponentPortKindGroup extends AbstractSemanticKindGroup {
  private Button componentPortKindBtnStandard;
  private Button componentPortKindBtnFlow;

  /**
   * Constructor.
   * @param parent
   * @param widgetFactory
   * @param enabled
   */
  public ComponentPortKindGroup(Composite parent, TabbedPropertySheetWidgetFactory widgetFactory, boolean enabled) {
    super(parent, widgetFactory, Messages.getString("ComponentPortKind.Label"), 2); //$NON-NLS-1$

    componentPortKindBtnStandard = createButton(ComponentPortKind.STANDARD, enabled);
    componentPortKindBtnFlow = createButton(ComponentPortKind.FLOW, enabled);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<Button> getSemanticFields() {
    List<Button> fields = new ArrayList<Button>();

    fields.add(componentPortKindBtnStandard);
    fields.add(componentPortKindBtnFlow);

    return fields;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Button getDefaultSemanticField() {
    return componentPortKindBtnStandard;
  }
}
