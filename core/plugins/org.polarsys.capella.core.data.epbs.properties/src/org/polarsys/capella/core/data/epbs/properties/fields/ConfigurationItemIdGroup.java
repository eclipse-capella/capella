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
package org.polarsys.capella.core.data.epbs.properties.fields;

import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;

import org.polarsys.capella.core.data.epbs.EpbsPackage;
import org.polarsys.capella.core.data.epbs.properties.Messages;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.ui.properties.fields.AbstractSemanticField;

/**
 */
public class ConfigurationItemIdGroup extends AbstractSemanticField {

  private Text _itemIdentifierField;

  /**
   * @param parent_p
   * @param widgetFactory_p
   */
  public ConfigurationItemIdGroup(Composite parent_p, TabbedPropertySheetWidgetFactory widgetFactory_p) {
    super(widgetFactory_p);

    Group textGroup = widgetFactory_p.createGroup(parent_p, ""); //$NON-NLS-1$
    textGroup.setLayout(new GridLayout(2, false));
    GridData gd = new GridData(GridData.FILL_HORIZONTAL);
    gd.horizontalSpan = 2;
    textGroup.setLayoutData(gd);

    createConfigurationItemIdTextField(textGroup);
  }

  /**
   * @param textGroup_p
   */
  private void createConfigurationItemIdTextField(Group textGroup_p) {
    _widgetFactory.createCLabel(textGroup_p, Messages.getString("ConfigurationItemIdentifier.Label")); //$NON-NLS-1$
    _itemIdentifierField = _widgetFactory.createText(textGroup_p, ""); //$NON-NLS-1$
    _itemIdentifierField.addFocusListener(this);
    _itemIdentifierField.addKeyListener(this);
    _itemIdentifierField.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
  }

  /**
   * @see org.polarsys.capella.core.ui.properties.fields.custom.properties.fields.AbstractSemanticField#loadData(org.polarsys.capella.core.data.capellacore.CapellaElement)
   */
  @Override
  public void loadData(CapellaElement semanticElement_p) {
    loadData(semanticElement_p, null);

    if (null != _semanticElement) {
      if (null != _itemIdentifierField)
        setTextValue(_itemIdentifierField, _semanticElement, EpbsPackage.eINSTANCE.getConfigurationItem_ItemIdentifier());
    }
  }

  /**
   * @param textField_p text field to be filled
   */
  @Override
  protected void fillTextField(Text textField_p) {
    if (textField_p.equals(_itemIdentifierField)) {
      setDataValue(_semanticElement, EpbsPackage.eINSTANCE.getConfigurationItem_ItemIdentifier(), _itemIdentifierField.getText());
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setEnabled(boolean enabled_p) {
    if (null != _itemIdentifierField && !_itemIdentifierField.isDisposed()) {
      _itemIdentifierField.setEnabled(enabled_p);
    }
  }
}
