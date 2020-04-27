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
package org.polarsys.capella.core.data.information.properties.fields;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;

import org.polarsys.capella.core.data.information.ExchangeMechanism;
import org.polarsys.capella.core.data.information.properties.Messages;
import org.polarsys.capella.core.ui.properties.fields.AbstractSemanticKindGroup;

/**
 */
public class ExchangeMechanismGroup extends AbstractSemanticKindGroup {
  private Button _exchangeMechanismBtnUnset;
  private Button _exchangeMechanismBtnEvent;
  private Button _exchangeMechanismBtnFlow;
  private Button _exchangeMechanismBtnOperation;
  private Button _exchangeMechanismBtnSharedData;

  /**
   * Constructor.
   * @param parent
   * @param style
   */
  public ExchangeMechanismGroup(Composite parent, TabbedPropertySheetWidgetFactory widgetFactory) {
    super(parent, widgetFactory, Messages.getString("ExchangeMechanism.Label"), 5); //$NON-NLS-1$

    _exchangeMechanismBtnUnset = createButton(ExchangeMechanism.UNSET);
    _exchangeMechanismBtnEvent = createButton(ExchangeMechanism.EVENT);
    _exchangeMechanismBtnFlow = createButton(ExchangeMechanism.FLOW);
    _exchangeMechanismBtnOperation = createButton(ExchangeMechanism.OPERATION);
    _exchangeMechanismBtnSharedData = createButton(ExchangeMechanism.SHARED_DATA);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<Button> getSemanticFields() {
    List<Button> fields = new ArrayList<Button>();

    fields.add(_exchangeMechanismBtnUnset);
    fields.add(_exchangeMechanismBtnEvent);
    fields.add(_exchangeMechanismBtnFlow);
    fields.add(_exchangeMechanismBtnOperation);
    fields.add(_exchangeMechanismBtnSharedData);

    return fields;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Button getDefaultSemanticField() {
    return _exchangeMechanismBtnUnset;
  }
}
