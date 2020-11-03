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
package org.polarsys.capella.core.data.information.properties.fields;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;

import org.polarsys.capella.core.data.information.UnionKind;
import org.polarsys.capella.core.data.information.properties.Messages;
import org.polarsys.capella.core.ui.properties.fields.AbstractSemanticKindGroup;

/**
 */
public class UnionKindGroup extends AbstractSemanticKindGroup {
  private Button _unionKindBtnUnion;
  private Button _unionKindBtnVariant;

  /**
   * Constructor.
   * @param parent
   * @param widgetFactory
   */
  public UnionKindGroup(Composite parent, TabbedPropertySheetWidgetFactory widgetFactory) {
    super(parent, widgetFactory, Messages.getString("UnionKind.Label"), 2); //$NON-NLS-1$

    _unionKindBtnUnion = createButton(UnionKind.UNION);
    _unionKindBtnVariant = createButton(UnionKind.VARIANT);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<Button> getSemanticFields() {
    List<Button> fields = new ArrayList<Button>();

    fields.add(_unionKindBtnUnion);
    fields.add(_unionKindBtnVariant);

    return fields;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Button getDefaultSemanticField() {
    return _unionKindBtnUnion;
  }
}
