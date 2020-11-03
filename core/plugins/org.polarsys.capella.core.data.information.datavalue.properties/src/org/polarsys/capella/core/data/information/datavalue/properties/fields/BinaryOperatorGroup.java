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

import org.polarsys.capella.core.data.information.datavalue.BinaryOperator;
import org.polarsys.capella.core.data.information.datavalue.properties.Messages;
import org.polarsys.capella.core.ui.properties.fields.AbstractSemanticKindGroup;

/**
 */
public class BinaryOperatorGroup extends AbstractSemanticKindGroup {
  private Button _operatorBtnUnset;
  private Button _operatorBtnAdd;
  private Button _operatorBtnDiv;
  private Button _operatorBtnMax;
  private Button _operatorBtnMin;
  private Button _operatorBtnMul;
  private Button _operatorBtnPow;
  private Button _operatorBtnSub;
  private Button _operatorBtnEqu;
  private Button _operatorBtnIOr;
  private Button _operatorBtnXOr;
  private Button _operatorBtnAnd;

  /**
   * Constructor.
   * @param parent
   * @param widgetFactory
   */
  public BinaryOperatorGroup(Composite parent, TabbedPropertySheetWidgetFactory widgetFactory) {
    super(parent, widgetFactory, Messages.getString("BinaryOperator.Label"), 6); //$NON-NLS-1$

    _operatorBtnUnset = createButton(BinaryOperator.UNSET);
    _operatorBtnAdd = createButton(BinaryOperator.ADD);
    _operatorBtnDiv = createButton(BinaryOperator.DIV);
    _operatorBtnMax = createButton(BinaryOperator.MAX);
    _operatorBtnMin = createButton(BinaryOperator.MIN);
    _operatorBtnMul = createButton(BinaryOperator.MUL);
    _operatorBtnPow = createButton(BinaryOperator.POW);
    _operatorBtnSub = createButton(BinaryOperator.SUB);
    _operatorBtnEqu = createButton(BinaryOperator.EQU);
    _operatorBtnIOr = createButton(BinaryOperator.IOR);
    _operatorBtnXOr = createButton(BinaryOperator.XOR);
    _operatorBtnAnd = createButton(BinaryOperator.AND);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<Button> getSemanticFields() {
    List<Button> fields = new ArrayList<Button>();

    fields.add(_operatorBtnUnset);
    fields.add(_operatorBtnAdd);
    fields.add(_operatorBtnDiv);
    fields.add(_operatorBtnMax);
    fields.add(_operatorBtnMin);
    fields.add(_operatorBtnMul);
    fields.add(_operatorBtnPow);
    fields.add(_operatorBtnSub);
    fields.add(_operatorBtnEqu);
    fields.add(_operatorBtnIOr);
    fields.add(_operatorBtnXOr);
    fields.add(_operatorBtnAnd);
    
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
