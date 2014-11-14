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
package org.polarsys.capella.core.data.core.properties.fields;

import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;

import org.polarsys.capella.core.data.core.properties.Messages;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.ui.properties.fields.AbstractSemanticField;

/**
 */
public class KeyValueGroup extends AbstractSemanticField {

  private Text _keyField;
  private Text _valueField;

  /**
   * @param parent_p
   * @param widgetFactory_p
   */
  public KeyValueGroup(Composite parent_p, TabbedPropertySheetWidgetFactory widgetFactory_p) {
    super(widgetFactory_p);

    Group textGroup = widgetFactory_p.createGroup(parent_p, ""); //$NON-NLS-1$
    textGroup.setLayout(new GridLayout(2, false));
    GridData gd = new GridData(GridData.FILL_HORIZONTAL);
    gd.horizontalSpan = 2;
    textGroup.setLayoutData(gd);

    createKeyTextField(textGroup);
    createValueTextField(textGroup);
  }

  /**
   * @param textGroup_p
   */
  private void createKeyTextField(Group textGroup_p) {
    _widgetFactory.createCLabel(textGroup_p, Messages.getString("KeyValueGroup.Key.Label")); //$NON-NLS-1$
    _keyField = _widgetFactory.createText(textGroup_p, ""); //$NON-NLS-1$
    _keyField.addFocusListener(this);
    _keyField.addKeyListener(this);
    _keyField.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
  }

  /**
   * @param textGroup_p
   */
  private void createValueTextField(Group textGroup_p) {
    _widgetFactory.createCLabel(textGroup_p, Messages.getString("KeyValueGroup.Value.Label")); //$NON-NLS-1$
    _valueField = _widgetFactory.createText(textGroup_p, ""); //$NON-NLS-1$
    _valueField.addFocusListener(this);
    _valueField.addKeyListener(this);
    _valueField.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
  }

  /**
   * @see org.polarsys.capella.core.ui.properties.fields.custom.properties.fields.AbstractSemanticField#loadData(org.polarsys.capella.core.data.capellacore.CapellaElement)
   */
  @Override
  public void loadData(CapellaElement semanticElement_p) {
    loadData(semanticElement_p, null);

    if (null != _semanticElement) {
      if (null != _keyField)
        setTextValue(_keyField, _semanticElement, CapellacorePackage.eINSTANCE.getKeyValue_Key());
      if (null != _valueField)
        setTextValue(_valueField, _semanticElement, CapellacorePackage.eINSTANCE.getKeyValue_Value());
    }
  }

  /**
   * @param textField_p text field to be filled
   */
  @Override
  protected void fillTextField(Text textField_p) {
    if (textField_p.equals(_keyField)) {
      setDataValue(_semanticElement, CapellacorePackage.eINSTANCE.getKeyValue_Key(), _keyField.getText());
    } else if (textField_p.equals(_valueField)) {
      setDataValue(_semanticElement, CapellacorePackage.eINSTANCE.getKeyValue_Value(), _valueField.getText());
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setEnabled(boolean enabled_p) {
    if (null != _keyField && !_keyField.isDisposed()) {
      _keyField.setEnabled(enabled_p);
    }
    if (null != _valueField && !_valueField.isDisposed()) {
      _valueField.setEnabled(enabled_p);
    }
  }
}
