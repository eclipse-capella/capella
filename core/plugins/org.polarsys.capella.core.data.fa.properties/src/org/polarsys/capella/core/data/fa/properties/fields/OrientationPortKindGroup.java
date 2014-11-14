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

import org.polarsys.capella.core.data.fa.OrientationPortKind;
import org.polarsys.capella.core.data.fa.properties.Messages;
import org.polarsys.capella.core.ui.properties.fields.AbstractSemanticKindGroup;

/**
 */
public class OrientationPortKindGroup extends AbstractSemanticKindGroup {
  private Button _orientationPortKindBtnUnset;
  private Button _orientationPortKindBtnIn;
  private Button _orientationPortKindBtnOut;
  private Button _orientationPortKindBtnInOut;

  /**
   * Constructor.
   * @param parent_p
   * @param widgetFactory_p
   * @param enabled_p
   */
  public OrientationPortKindGroup(Composite parent_p, TabbedPropertySheetWidgetFactory widgetFactory_p, boolean enabled_p) {
    super(parent_p, widgetFactory_p, Messages.getString("OrientationPortKind.Label"), 4); //$NON-NLS-1$

    _orientationPortKindBtnUnset = createButton(OrientationPortKind.UNSET, enabled_p);
    _orientationPortKindBtnIn = createButton(OrientationPortKind.IN, enabled_p);
    _orientationPortKindBtnOut = createButton(OrientationPortKind.OUT, enabled_p);
    _orientationPortKindBtnInOut = createButton(OrientationPortKind.INOUT, enabled_p);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<Button> getSemanticFields() {
    List<Button> fields = new ArrayList<Button>();

    fields.add(_orientationPortKindBtnUnset);
    fields.add(_orientationPortKindBtnIn);
    fields.add(_orientationPortKindBtnOut);
    fields.add(_orientationPortKindBtnInOut);

    return fields;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Button getDefaultSemanticField() {
    return _orientationPortKindBtnUnset;
  }
}
