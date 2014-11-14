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

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;

import org.polarsys.capella.core.data.interaction.InteractionPackage;
import org.polarsys.capella.core.data.interaction.properties.Messages;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.ui.properties.fields.AbstractSemanticField;

/**
 */
public class AbstractCapabilityValueGroup extends AbstractSemanticField {

  protected Text _preConditionField;
  protected Text _postConditionField;

  /**
   * @param parent_p
   * @param widgetFactory_p
   */
  public AbstractCapabilityValueGroup(Composite parent_p, TabbedPropertySheetWidgetFactory widgetFactory_p) {
    super(widgetFactory_p);

    Group textGroup = _widgetFactory.createGroup(parent_p, ""); //$NON-NLS-1$
    textGroup.setLayout(new GridLayout(2, false));
    GridData gd = new GridData(GridData.FILL_HORIZONTAL);
    gd.horizontalSpan = 2;
    textGroup.setLayoutData(gd);

    _preConditionField = createTextField(textGroup, Messages.getString("AbstractCapabilitySection_PreCondition_Label")); //$NON-NLS-1$
    _postConditionField = createTextField(textGroup, Messages.getString("AbstractCapabilitySection_PostCondition_Label")); //$NON-NLS-1$
  }

  /**
   * @param textGroup_p
   * @param label_p
   * @param widgetFactory_p
   */
  protected Text createTextField(Group textGroup_p, String label_p) {
    CLabel label = _widgetFactory.createCLabel(textGroup_p, label_p);
    GridData gd = new GridData();
    gd.horizontalSpan = 2;
    label.setLayoutData(gd);
    Text preConditionField = _widgetFactory.createText(textGroup_p, "", SWT.BORDER | SWT.WRAP | SWT.MULTI); //$NON-NLS-1$
    preConditionField.addFocusListener(this);
    preConditionField.addKeyListener(this);
    gd = new GridData(GridData.FILL_HORIZONTAL);
    gd.horizontalSpan = 2;
    gd.heightHint = 60;
    preConditionField.setLayoutData(gd);
    return preConditionField;
  }

  /**
   * @see org.polarsys.capella.core.ui.properties.fields.custom.properties.fields.AbstractSemanticField#loadData(org.polarsys.capella.core.data.capellacore.CapellaElement)
   */
  @Override
  public void loadData(CapellaElement semanticElement_p) {
    super.loadData(semanticElement_p, null);

    if (null != _semanticElement) {
      if (null != _preConditionField) {
        setTextValue(_preConditionField, _semanticElement, InteractionPackage.eINSTANCE.getAbstractCapability_PreCondition());
      }
      if (null != _postConditionField) {
        setTextValue(_postConditionField, _semanticElement, InteractionPackage.eINSTANCE.getAbstractCapability_PostCondition());
      }
    }
  }

  /**
   * @param textField_p text field to be filled
   */
  @Override
  protected void fillTextField(Text textField_p) {
    if (textField_p.equals(_preConditionField)) {
      setDataValue(_semanticElement, InteractionPackage.eINSTANCE.getAbstractCapability_PreCondition(), _preConditionField.getText());
    } else if (textField_p.equals(_postConditionField)) {
      setDataValue(_semanticElement, InteractionPackage.eINSTANCE.getAbstractCapability_PostCondition(), _postConditionField.getText());
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setEnabled(boolean enabled_p) {
    if (null != _preConditionField && !_preConditionField.isDisposed()) {
      _preConditionField.setEnabled(enabled_p);
    }
    if (null != _postConditionField && !_postConditionField.isDisposed()) {
      _postConditionField.setEnabled(enabled_p);
    }
  }
}
