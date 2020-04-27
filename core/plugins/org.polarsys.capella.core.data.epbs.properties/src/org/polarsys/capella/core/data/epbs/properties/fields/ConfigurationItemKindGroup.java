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
package org.polarsys.capella.core.data.epbs.properties.fields;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;

import org.polarsys.capella.core.data.epbs.ConfigurationItemKind;
import org.polarsys.capella.core.data.epbs.properties.Messages;
import org.polarsys.capella.core.ui.properties.fields.AbstractSemanticKindGroup;

/**
 */
public class ConfigurationItemKindGroup extends AbstractSemanticKindGroup {
  private Button _ciKindBtnUnset;
  private Button _ciKindBtnCOTSCI;
  private Button _ciKindBtnCSCI;
  private Button _ciKindBtnHWCI;
  private Button _ciKindBtnInterfaceCI;
  private Button _ciKindBtnNDICI;
  private Button _ciKindBtnPrimeItemCI;
  private Button _ciKindBtnSystemCI;

  /**
   * Constructor.
   * @param parent
   * @param widgetFactory
   * @param enabled
   */
  public ConfigurationItemKindGroup(Composite parent, TabbedPropertySheetWidgetFactory widgetFactory, boolean enabled) {
    super(parent, widgetFactory, Messages.getString("ConfigurationItemKind.Label"), 4); //$NON-NLS-1$

    _ciKindBtnUnset = createButton(ConfigurationItemKind.UNSET, enabled);
    _ciKindBtnCOTSCI = createButton(ConfigurationItemKind.COTSCI, enabled);
    _ciKindBtnCSCI = createButton(ConfigurationItemKind.CSCI, enabled);
    _ciKindBtnHWCI = createButton(ConfigurationItemKind.HWCI, enabled);
    _ciKindBtnInterfaceCI = createButton(ConfigurationItemKind.INTERFACE_CI, enabled);
    _ciKindBtnNDICI = createButton(ConfigurationItemKind.NDICI, enabled);
    _ciKindBtnPrimeItemCI = createButton(ConfigurationItemKind.PRIME_ITEM_CI, enabled);
    _ciKindBtnSystemCI = createButton(ConfigurationItemKind.SYSTEM_CI, enabled);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<Button> getSemanticFields() {
    List<Button> fields = new ArrayList<Button>();

    fields.add(_ciKindBtnUnset);
    fields.add(_ciKindBtnCOTSCI);
    fields.add(_ciKindBtnCSCI);
    fields.add(_ciKindBtnHWCI);
    fields.add(_ciKindBtnInterfaceCI);
    fields.add(_ciKindBtnNDICI);
    fields.add(_ciKindBtnPrimeItemCI);
    fields.add(_ciKindBtnSystemCI);

    return fields;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Button getDefaultSemanticField() {
    return _ciKindBtnUnset;
  }
}
