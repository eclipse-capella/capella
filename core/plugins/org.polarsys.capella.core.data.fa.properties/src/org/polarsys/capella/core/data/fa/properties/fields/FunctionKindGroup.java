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

import org.polarsys.capella.core.data.fa.FunctionKind;
import org.polarsys.capella.core.data.fa.properties.Messages;
import org.polarsys.capella.core.ui.properties.fields.AbstractSemanticKindGroup;

/**
 */
public class FunctionKindGroup extends AbstractSemanticKindGroup {
  private Button componentExchangeKindBtnDuplicate;
  private Button componentExchangeKindBtnFunction;
  private Button componentExchangeKindBtnGather;
  private Button componentExchangeKindBtnRoute;
  private Button componentExchangeKindBtnSelect;
  private Button componentExchangeKindBtnSplit;

  /**
   * Constructor.
   * @param parent
   * @param widgetFactory
   * @param enabled
   */
  public FunctionKindGroup(Composite parent, TabbedPropertySheetWidgetFactory widgetFactory, boolean enabled) {
    super(parent, widgetFactory, Messages.getString("FunctionKind.Label"), 6); //$NON-NLS-1$

    componentExchangeKindBtnDuplicate = createButton(FunctionKind.DUPLICATE, enabled);
    componentExchangeKindBtnFunction = createButton(FunctionKind.FUNCTION, enabled);
    componentExchangeKindBtnGather = createButton(FunctionKind.GATHER, enabled);
    componentExchangeKindBtnRoute = createButton(FunctionKind.ROUTE, enabled);
    componentExchangeKindBtnSelect = createButton(FunctionKind.SELECT, enabled);
    componentExchangeKindBtnSplit = createButton(FunctionKind.SPLIT, enabled);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<Button> getSemanticFields() {
    List<Button> fields = new ArrayList<Button>();

    fields.add(componentExchangeKindBtnDuplicate);
    fields.add(componentExchangeKindBtnFunction);
    fields.add(componentExchangeKindBtnGather);
    fields.add(componentExchangeKindBtnRoute);
    fields.add(componentExchangeKindBtnSelect);
    fields.add(componentExchangeKindBtnSplit);

    return fields;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Button getDefaultSemanticField() {
    return componentExchangeKindBtnFunction;
  }
}
