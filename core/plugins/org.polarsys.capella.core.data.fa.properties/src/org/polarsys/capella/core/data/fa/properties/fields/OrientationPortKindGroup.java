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

import org.polarsys.capella.core.data.fa.OrientationPortKind;
import org.polarsys.capella.core.data.fa.properties.Messages;
import org.polarsys.capella.core.ui.properties.fields.AbstractSemanticKindGroup;

/**
 */
public class OrientationPortKindGroup extends AbstractSemanticKindGroup {
  private Button orientationPortKindBtnUnset;
  private Button orientationPortKindBtnIn;
  private Button orientationPortKindBtnOut;
  private Button orientationPortKindBtnInOut;

  /**
   * Constructor.
   * @param parent
   * @param widgetFactory
   * @param enabled
   */
  public OrientationPortKindGroup(Composite parent, TabbedPropertySheetWidgetFactory widgetFactory, boolean enabled) {
    super(parent, widgetFactory, Messages.getString("OrientationPortKind.Label"), 4); //$NON-NLS-1$

    orientationPortKindBtnUnset = createButton(OrientationPortKind.UNSET, enabled);
    orientationPortKindBtnIn = createButton(OrientationPortKind.IN, enabled);
    orientationPortKindBtnOut = createButton(OrientationPortKind.OUT, enabled);
    orientationPortKindBtnInOut = createButton(OrientationPortKind.INOUT, enabled);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<Button> getSemanticFields() {
    List<Button> fields = new ArrayList<Button>();

    fields.add(orientationPortKindBtnUnset);
    fields.add(orientationPortKindBtnIn);
    fields.add(orientationPortKindBtnOut);
    fields.add(orientationPortKindBtnInOut);

    return fields;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Button getDefaultSemanticField() {
    return orientationPortKindBtnUnset;
  }
}
