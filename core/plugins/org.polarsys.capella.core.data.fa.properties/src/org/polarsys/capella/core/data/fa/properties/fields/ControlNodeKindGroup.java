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
package org.polarsys.capella.core.data.fa.properties.fields;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;
import org.polarsys.capella.core.data.fa.ControlNodeKind;
import org.polarsys.capella.core.data.fa.properties.Messages;
import org.polarsys.capella.core.ui.properties.fields.AbstractSemanticKindGroup;

/**
 */
public class ControlNodeKindGroup extends AbstractSemanticKindGroup {
  private Button controlNodeKindBtnOR;
  private Button controlNodeKindBtnAND;
  private Button controlNodeKindBtnITERATE;

  /**
   * Constructor.
   * 
   * @param parent
   * @param widgetFactory
   * @param enabled
   */
  public ControlNodeKindGroup(Composite parent, TabbedPropertySheetWidgetFactory widgetFactory, boolean enabled) {
    super(parent, widgetFactory, Messages.getString("ControlNodeKind.Label"), 6);

    controlNodeKindBtnOR = createButton(ControlNodeKind.OR, enabled);
    controlNodeKindBtnAND = createButton(ControlNodeKind.AND, enabled);
    controlNodeKindBtnITERATE = createButton(ControlNodeKind.ITERATE, enabled);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<Button> getSemanticFields() {
    List<Button> fields = new ArrayList<Button>();

    fields.add(controlNodeKindBtnOR);
    fields.add(controlNodeKindBtnAND);
    fields.add(controlNodeKindBtnITERATE);

    return fields;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Button getDefaultSemanticField() {
    return controlNodeKindBtnOR;
  }
}
