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
  private Button _componentExchangeKindBtnDuplicate;
  private Button _componentExchangeKindBtnFunction;
  private Button _componentExchangeKindBtnGather;
  private Button _componentExchangeKindBtnRoute;
  private Button _componentExchangeKindBtnSelect;
  private Button _componentExchangeKindBtnSplit;

  /**
   * Constructor.
   * @param parent_p
   * @param widgetFactory_p
   * @param enabled_p
   */
  public FunctionKindGroup(Composite parent_p, TabbedPropertySheetWidgetFactory widgetFactory_p, boolean enabled_p) {
    super(parent_p, widgetFactory_p, Messages.getString("FunctionKind.Label"), 6); //$NON-NLS-1$

    _componentExchangeKindBtnDuplicate = createButton(FunctionKind.DUPLICATE, enabled_p);
    _componentExchangeKindBtnFunction = createButton(FunctionKind.FUNCTION, enabled_p);
    _componentExchangeKindBtnGather = createButton(FunctionKind.GATHER, enabled_p);
    _componentExchangeKindBtnRoute = createButton(FunctionKind.ROUTE, enabled_p);
    _componentExchangeKindBtnSelect = createButton(FunctionKind.SELECT, enabled_p);
    _componentExchangeKindBtnSplit = createButton(FunctionKind.SPLIT, enabled_p);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<Button> getSemanticFields() {
    List<Button> fields = new ArrayList<Button>();

    fields.add(_componentExchangeKindBtnDuplicate);
    fields.add(_componentExchangeKindBtnFunction);
    fields.add(_componentExchangeKindBtnGather);
    fields.add(_componentExchangeKindBtnRoute);
    fields.add(_componentExchangeKindBtnSelect);
    fields.add(_componentExchangeKindBtnSplit);

    return fields;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Button getDefaultSemanticField() {
    return _componentExchangeKindBtnFunction;
  }
}
