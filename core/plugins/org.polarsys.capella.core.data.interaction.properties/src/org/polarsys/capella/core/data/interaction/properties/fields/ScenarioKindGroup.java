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
   * @param parent_p
   * @param widgetFactory_p
   * @param enabled_p
   */
  public ScenarioKindGroup(Composite parent_p, TabbedPropertySheetWidgetFactory widgetFactory_p, boolean enabled_p) {
    super(parent_p, widgetFactory_p, Messages.getString("ScenarioKind.Label"), 5); //$NON-NLS-1$

    _scenarioKindBtnUnset = createButton(ScenarioKind.UNSET, enabled_p);
    _scenarioKindBtnDataflow = createButton(ScenarioKind.DATA_FLOW, enabled_p);
    _scenarioKindBtnFunctional = createButton(ScenarioKind.FUNCTIONAL, enabled_p);
    _scenarioKindBtnInteraction = createButton(ScenarioKind.INTERACTION, enabled_p);
    _scenarioKindBtnInterface = createButton(ScenarioKind.INTERFACE, enabled_p);
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
