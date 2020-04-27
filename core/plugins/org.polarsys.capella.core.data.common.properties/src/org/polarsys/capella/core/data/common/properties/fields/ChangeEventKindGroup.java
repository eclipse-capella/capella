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
package org.polarsys.capella.core.data.common.properties.fields;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;
import org.polarsys.capella.core.data.capellacommon.ChangeEventKind;
import org.polarsys.capella.core.data.common.properties.Messages;
import org.polarsys.capella.core.ui.properties.fields.AbstractSemanticKindGroup;

/**
 *
 */
public class ChangeEventKindGroup extends AbstractSemanticKindGroup {

  private Button _kindBtnWhen;

  /**
   * @param parent
   * @param widgetFactory
   * @param groupLabel
   * @param numColumns
   */
  public ChangeEventKindGroup(Composite parent, TabbedPropertySheetWidgetFactory widgetFactory, boolean enabled) {
    super(parent, widgetFactory, Messages.getString("StateEvent.ChangeEventKind"), 2); //$NON-NLS-1$

    _kindBtnWhen = createButton(ChangeEventKind.WHEN, enabled);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Button getDefaultSemanticField() {
    return _kindBtnWhen;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<Button> getSemanticFields() {
    List<Button> buttons = new ArrayList<Button>();

    buttons.add(_kindBtnWhen);

    return buttons;
  }

}
