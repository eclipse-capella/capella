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
package org.polarsys.capella.core.data.information.datatype.properties.fields;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;

import org.polarsys.capella.core.data.information.datatype.NumericTypeKind;
import org.polarsys.capella.core.data.information.datatype.properties.Messages;
import org.polarsys.capella.core.ui.properties.fields.AbstractSemanticKindGroup;

/**
 */
public class NumericTypeKindGroup extends AbstractSemanticKindGroup {
  private Button _numericTypeBtnFloat;
  private Button _numericTypeBtnInteger;

  /**
   * Constructor.
   * @param parent_p
   * @param widgetFactory_p
   */
  public NumericTypeKindGroup(Composite parent_p, TabbedPropertySheetWidgetFactory widgetFactory_p) {
    super(parent_p, widgetFactory_p, Messages.getString("NumericTypeKind.Label"), 2); //$NON-NLS-1$

    _numericTypeBtnFloat = createButton(NumericTypeKind.FLOAT);
    _numericTypeBtnInteger = createButton(NumericTypeKind.INTEGER);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<Button> getSemanticFields() {
    List<Button> fields = new ArrayList<Button>();

    fields.add(_numericTypeBtnFloat);
    fields.add(_numericTypeBtnInteger);

    return fields;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Button getDefaultSemanticField() {
    return _numericTypeBtnInteger;
  }
}
