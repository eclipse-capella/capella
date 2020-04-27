/*******************************************************************************
 * Copyright (c) 2006, 2018 THALES GLOBAL SERVICES.
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

import org.eclipse.emf.ecore.EObject;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.data.core.properties.Messages;
import org.polarsys.capella.core.ui.properties.fields.AbstractSemanticField;

/**
 */
public class KeyValueGroup extends AbstractSemanticField {

  private Text _keyField;
  private Text _valueField;

  /**
   * @param parent
   * @param widgetFactory
   */
  public KeyValueGroup(Composite parent, TabbedPropertySheetWidgetFactory widgetFactory) {
    super(widgetFactory);

    Group textGroup = widgetFactory.createGroup(parent, ""); //$NON-NLS-1$
    textGroup.setLayout(new GridLayout(2, false));
    GridData gd = new GridData(GridData.FILL_HORIZONTAL);
    gd.horizontalSpan = 2;
    textGroup.setLayoutData(gd);

    createKeyTextField(textGroup);
    createValueTextField(textGroup);
  }

  /**
   * @param textGroup
   */
  private void createKeyTextField(Group textGroup) {
    widgetFactory.createCLabel(textGroup, Messages.getString("KeyValueGroup.Key.Label")); //$NON-NLS-1$
    _keyField = widgetFactory.createText(textGroup, ""); //$NON-NLS-1$
    _keyField.addFocusListener(this);
    _keyField.addKeyListener(this);
    _keyField.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
  }

  /**
   * @param textGroup
   */
  private void createValueTextField(Group textGroup) {
    widgetFactory.createCLabel(textGroup, Messages.getString("KeyValueGroup.Value.Label")); //$NON-NLS-1$
    _valueField = widgetFactory.createText(textGroup, ""); //$NON-NLS-1$
    _valueField.addFocusListener(this);
    _valueField.addKeyListener(this);
    _valueField.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void loadData(EObject semanticElement) {
    loadData(semanticElement, null);

    if (null != semanticElement) {
      if (null != _keyField)
        setTextValue(_keyField, semanticElement, CapellacorePackage.eINSTANCE.getKeyValue_Key());
      if (null != _valueField)
        setTextValue(_valueField, semanticElement, CapellacorePackage.eINSTANCE.getKeyValue_Value());
    }
  }

  /**
   * @param textField text field to be filled
   */
  @Override
  protected void fillTextField(Text textField) {
    if (textField.equals(_keyField)) {
      setDataValue(semanticElement, CapellacorePackage.eINSTANCE.getKeyValue_Key(), _keyField.getText());
    } else if (textField.equals(_valueField)) {
      setDataValue(semanticElement, CapellacorePackage.eINSTANCE.getKeyValue_Value(), _valueField.getText());
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setEnabled(boolean enabled) {
    if (null != _keyField && !_keyField.isDisposed()) {
      _keyField.setEnabled(enabled);
    }
    if (null != _valueField && !_valueField.isDisposed()) {
      _valueField.setEnabled(enabled);
    }
  }
}
