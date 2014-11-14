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

import org.polarsys.capella.core.data.fa.ComponentPortKind;
import org.polarsys.capella.core.data.fa.properties.Messages;
import org.polarsys.capella.core.ui.properties.fields.AbstractSemanticKindGroup;

/**
 */
public class ComponentPortKindGroup extends AbstractSemanticKindGroup {
  private Button _componentPortKindBtnStandard;
  private Button _componentPortKindBtnFlow;

  /**
   * Constructor.
   * @param parent_p
   * @param widgetFactory_p
   * @param enabled_p
   */
  public ComponentPortKindGroup(Composite parent_p, TabbedPropertySheetWidgetFactory widgetFactory_p, boolean enabled_p) {
    super(parent_p, widgetFactory_p, Messages.getString("ComponentPortKind.Label"), 2); //$NON-NLS-1$

    _componentPortKindBtnStandard = createButton(ComponentPortKind.STANDARD, enabled_p);
    _componentPortKindBtnFlow = createButton(ComponentPortKind.FLOW, enabled_p);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<Button> getSemanticFields() {
    List<Button> fields = new ArrayList<Button>();

    fields.add(_componentPortKindBtnStandard);
    fields.add(_componentPortKindBtnFlow);

    return fields;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Button getDefaultSemanticField() {
    return _componentPortKindBtnStandard;
  }
}
