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
   * @param parent_p
   * @param widgetFactory_p
   * @param groupLabel_p
   * @param numColumns_p
   */
  public TimeEventKindGroup(Composite parent_p, TabbedPropertySheetWidgetFactory widgetFactory_p, boolean enabled_p) {
    super(parent_p, widgetFactory_p, "Time Event Kind: ", 3); //$NON-NLS-1$

    _kindBtnAfter = createButton(TimeEventKind.AFTER, enabled_p);
    _kindBtnAt = createButton(TimeEventKind.AT, enabled_p);
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
