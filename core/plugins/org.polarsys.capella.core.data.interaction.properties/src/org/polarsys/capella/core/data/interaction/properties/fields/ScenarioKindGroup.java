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
package org.polarsys.capella.core.data.interaction.properties.fields;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;

import org.polarsys.capella.core.data.interaction.ScenarioKind;
import org.polarsys.capella.core.data.interaction.properties.Messages;
import org.polarsys.capella.core.ui.properties.fields.AbstractSemanticKindGroup;

/**
 */
public class ScenarioKindGroup extends AbstractSemanticKindGroup {
  private Button _scenarioKindBtnUnset;
  private Button _scenarioKindBtnDataflow;
  private Button _scenarioKindBtnFunctional;
  private Button _scenarioKindBtnInteraction;
  private Button _scenarioKindBtnInterface;

  /**
   * Constructor.
   * @param parent
   * @param widgetFactory
   * @param enabled
   */
  public ScenarioKindGroup(Composite parent, TabbedPropertySheetWidgetFactory widgetFactory, boolean enabled) {
    super(parent, widgetFactory, Messages.getString("ScenarioKind.Label"), 5); //$NON-NLS-1$

    _scenarioKindBtnUnset = createButton(ScenarioKind.UNSET, enabled);
    _scenarioKindBtnDataflow = createButton(ScenarioKind.DATA_FLOW, enabled);
    _scenarioKindBtnFunctional = createButton(ScenarioKind.FUNCTIONAL, enabled);
    _scenarioKindBtnInteraction = createButton(ScenarioKind.INTERACTION, enabled);
    _scenarioKindBtnInterface = createButton(ScenarioKind.INTERFACE, enabled);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<Button> getSemanticFields() {
    List<Button> fields = new ArrayList<Button>();

    fields.add(_scenarioKindBtnUnset);
    fields.add(_scenarioKindBtnDataflow);
    fields.add(_scenarioKindBtnFunctional);
    fields.add(_scenarioKindBtnInteraction);
    fields.add(_scenarioKindBtnInterface);

    return fields;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Button getDefaultSemanticField() {
    return _scenarioKindBtnUnset;
  }
}
