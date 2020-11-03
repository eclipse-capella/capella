/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.data.information.datavalue.properties.fields;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;

import org.polarsys.capella.core.data.information.datavalue.UnaryOperator;
import org.polarsys.capella.core.data.information.datavalue.properties.Messages;
import org.polarsys.capella.core.ui.properties.fields.AbstractSemanticKindGroup;

/**
 */
public class UnaryOperatorGroup extends AbstractSemanticKindGroup {
  private Button _operatorBtnUnset;
  private Button _operatorBtnNot;
  private Button _operatorBtnPos;
  private Button _operatorBtnPre;
  private Button _operatorBtnSuc;
  private Button _operatorBtnVal;

  /**
   * Constructor.
   * @param parent
   * @param widgetFactory
   */
  public UnaryOperatorGroup(Composite parent, TabbedPropertySheetWidgetFactory widgetFactory) {
    super(parent, widgetFactory, Messages.getString("UnaryOperator.Label"), 6); //$NON-NLS-1$

    _operatorBtnUnset = createButton(UnaryOperator.UNSET);
    _operatorBtnNot = createButton(UnaryOperator.NOT);
    _operatorBtnPos = createButton(UnaryOperator.POS);
    _operatorBtnPre = createButton(UnaryOperator.PRE);
    _operatorBtnSuc = createButton(UnaryOperator.SUC);
    _operatorBtnVal = createButton(UnaryOperator.VAL);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<Button> getSemanticFields() {
    List<Button> fields = new ArrayList<Button>();

    fields.add(_operatorBtnUnset);
    fields.add(_operatorBtnNot);
    fields.add(_operatorBtnPos);
    fields.add(_operatorBtnPre);
    fields.add(_operatorBtnSuc);
    fields.add(_operatorBtnVal);

    return fields;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Button getDefaultSemanticField() {
    return _operatorBtnUnset;
  }
}
