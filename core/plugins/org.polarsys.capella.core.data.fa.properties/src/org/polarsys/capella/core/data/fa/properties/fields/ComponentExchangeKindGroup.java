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

import org.polarsys.capella.core.data.fa.ComponentExchangeKind;
import org.polarsys.capella.core.data.fa.properties.Messages;
import org.polarsys.capella.core.ui.properties.fields.AbstractSemanticKindGroup;

/**
 */
public class ComponentExchangeKindGroup extends AbstractSemanticKindGroup {
  private Button _componentExchangeKindBtnUnset;
  private Button _componentExchangeKindBtnAssembly;
  private Button _componentExchangeKindBtnDelegation;
  private Button _componentExchangeKindBtnFlow;

  /**
   * Constructor.
   * @param parent_p
   * @param widgetFactory_p
   * @param enabled_p
   */
  public ComponentExchangeKindGroup(Composite parent_p, TabbedPropertySheetWidgetFactory widgetFactory_p, boolean enabled_p) {
    super(parent_p, widgetFactory_p, Messages.getString("ComponentExchangeKind.Label"), 4); //$NON-NLS-1$

    _componentExchangeKindBtnUnset = createButton(ComponentExchangeKind.UNSET, enabled_p);
    _componentExchangeKindBtnAssembly = createButton(ComponentExchangeKind.ASSEMBLY, enabled_p);
    _componentExchangeKindBtnDelegation = createButton(ComponentExchangeKind.DELEGATION, enabled_p);
    _componentExchangeKindBtnFlow = createButton(ComponentExchangeKind.FLOW, enabled_p);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<Button> getSemanticFields() {
    List<Button> fields = new ArrayList<Button>();

    fields.add(_componentExchangeKindBtnUnset);
    fields.add(_componentExchangeKindBtnAssembly);
    fields.add(_componentExchangeKindBtnDelegation);
    fields.add(_componentExchangeKindBtnFlow);

    return fields;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Button getDefaultSemanticField() {
    return _componentExchangeKindBtnUnset;
  }
}
