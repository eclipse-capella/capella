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
package org.polarsys.capella.core.data.core.properties.fields;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;

import org.polarsys.capella.core.data.core.properties.Messages;
import org.polarsys.capella.core.ui.properties.fields.AbstractSemanticKindGroup;
import org.polarsys.capella.common.data.modellingcore.ParameterEffectKind;

/**
 */
public class ParameterEffectKindGroup extends AbstractSemanticKindGroup {
  private Button _effectBtnRead;
  private Button _effectBtnUpdate;
  private Button _effectBtnCreate;
  private Button _effectBtnDelete;

  /**
   * Constructor.
   * @param parent
   * @param widgetFactory
   */
  public ParameterEffectKindGroup(Composite parent, TabbedPropertySheetWidgetFactory widgetFactory) {
    super(parent, widgetFactory, Messages.getString("ParameterEffectKind.Label"), 4); //$NON-NLS-1$

    _effectBtnRead = createButton(ParameterEffectKind.READ);
    _effectBtnUpdate = createButton(ParameterEffectKind.UPDATE);
    _effectBtnCreate = createButton(ParameterEffectKind.CREATE);
    _effectBtnDelete = createButton(ParameterEffectKind.DELETE);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<Button> getSemanticFields() {
    List<Button> fields = new ArrayList<Button>();

    fields.add(_effectBtnRead);
    fields.add(_effectBtnUpdate);
    fields.add(_effectBtnCreate);
    fields.add(_effectBtnDelete);

    return fields;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Button getDefaultSemanticField() {
    return _effectBtnRead;
  }
}
