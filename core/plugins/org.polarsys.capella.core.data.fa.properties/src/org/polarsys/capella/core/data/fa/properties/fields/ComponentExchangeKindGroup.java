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
package org.polarsys.capella.core.data.fa.properties.fields;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;

import org.polarsys.capella.core.data.fa.ComponentExchangeKind;
import org.polarsys.capella.core.data.fa.properties.Messages;
import org.polarsys.capella.core.ui.properties.fields.AbstractSemanticKindGroup;

/**
 */
public class ComponentExchangeKindGroup extends AbstractSemanticKindGroup {
  private Button componentExchangeKindBtnUnset;
  private Button componentExchangeKindBtnAssembly;
  private Button componentExchangeKindBtnDelegation;
  private Button componentExchangeKindBtnFlow;

  /**
   * Constructor.
   * @param parent
   * @param widgetFactory
   * @param enabled
   */
  public ComponentExchangeKindGroup(Composite parent, TabbedPropertySheetWidgetFactory widgetFactory, boolean enabled) {
    super(parent, widgetFactory, Messages.getString("ComponentExchangeKind.Label"), 4); //$NON-NLS-1$

    componentExchangeKindBtnUnset = createButton(ComponentExchangeKind.UNSET, enabled);
    componentExchangeKindBtnAssembly = createButton(ComponentExchangeKind.ASSEMBLY, enabled);
    componentExchangeKindBtnDelegation = createButton(ComponentExchangeKind.DELEGATION, enabled);
    componentExchangeKindBtnFlow = createButton(ComponentExchangeKind.FLOW, enabled);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<Button> getSemanticFields() {
    List<Button> fields = new ArrayList<Button>();

    fields.add(componentExchangeKindBtnUnset);
    fields.add(componentExchangeKindBtnAssembly);
    fields.add(componentExchangeKindBtnDelegation);
    fields.add(componentExchangeKindBtnFlow);

    return fields;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Button getDefaultSemanticField() {
    return componentExchangeKindBtnUnset;
  }
}
