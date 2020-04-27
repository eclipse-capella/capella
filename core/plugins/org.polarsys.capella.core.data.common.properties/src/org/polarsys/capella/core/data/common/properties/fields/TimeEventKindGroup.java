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
import org.polarsys.capella.core.data.capellacommon.TimeEventKind;
import org.polarsys.capella.core.ui.properties.fields.AbstractSemanticKindGroup;

/**
 *
 */
public class TimeEventKindGroup extends AbstractSemanticKindGroup {

  private Button _kindBtnAt;
  private Button _kindBtnAfter;

  /**
   * @param parent
   * @param widgetFactory
   * @param groupLabel
   * @param numColumns
   */
  public TimeEventKindGroup(Composite parent, TabbedPropertySheetWidgetFactory widgetFactory, boolean enabled) {
    super(parent, widgetFactory, "Time Event Kind: ", 3); //$NON-NLS-1$

    _kindBtnAfter = createButton(TimeEventKind.AFTER, enabled);
    _kindBtnAt = createButton(TimeEventKind.AT, enabled);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Button getDefaultSemanticField() {
    return _kindBtnAt;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<Button> getSemanticFields() {
    List<Button> buttons = new ArrayList<Button>();

    buttons.add(_kindBtnAt);
    buttons.add(_kindBtnAfter);

    return buttons;
  }

}
