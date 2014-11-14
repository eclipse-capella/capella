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
package org.polarsys.capella.core.data.requirement.properties.fields;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;

import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.requirement.RequirementPackage;
import org.polarsys.capella.core.data.requirement.properties.Messages;
import org.polarsys.capella.core.ui.properties.fields.AbstractSemanticField;

/**
 */
public class RequirementsPkgGroup extends AbstractSemanticField {

  private Text _levelField;
  private Text _additionalInformationField;

  /**
   * @param parent_p
   * @param widgetFactory_p
   */
  public RequirementsPkgGroup(Composite parent_p, TabbedPropertySheetWidgetFactory widgetFactory_p) {
    super(widgetFactory_p);

    Group textGroup = _widgetFactory.createGroup(parent_p, ""); //$NON-NLS-1$
    textGroup.setLayout(new GridLayout(2, false));
    GridData gd = new GridData(GridData.FILL_HORIZONTAL);
    gd.horizontalSpan = 2;
    textGroup.setLayoutData(gd);

    createLevelTextField(textGroup);
    createAdditionalInformationTextField(textGroup);
  }

  /**
   * @param textGroup_p
   */
  private void createLevelTextField(Group textGroup_p) {
    _widgetFactory.createCLabel(textGroup_p, Messages.getString("RequirementsPkgGroup.Level.Label")); //$NON-NLS-1$
    _levelField = _widgetFactory.createText(textGroup_p, ""); //$NON-NLS-1$
    _levelField.addFocusListener(this);
    _levelField.addKeyListener(this);
    _levelField.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
  }

  /**
   * @param textGroup_p
   */
  private void createAdditionalInformationTextField(Group textGroup_p) {
    CLabel label = _widgetFactory.createCLabel(textGroup_p, Messages.getString("RequirementsPkgGroup.AdditionalInformation.Label")); //$NON-NLS-1$
    GridData gd = new GridData();
    gd.horizontalSpan = 2;
    label.setLayoutData(gd);
    _additionalInformationField = _widgetFactory.createText(textGroup_p, "", SWT.BORDER | SWT.WRAP | SWT.MULTI); //$NON-NLS-1$
    _additionalInformationField.addFocusListener(this);
    _additionalInformationField.addKeyListener(this);
    gd = new GridData(GridData.FILL_HORIZONTAL);
    gd.horizontalSpan = 2;
    gd.heightHint = 80;
    _additionalInformationField.setLayoutData(gd);
  }

  /**
   * @see org.polarsys.capella.core.ui.properties.fields.custom.properties.fields.AbstractSemanticField#loadData(org.polarsys.capella.core.data.capellacore.CapellaElement)
   */
  @Override
  public void loadData(CapellaElement semanticElement_p) {
    loadData(semanticElement_p, null);

    if (null != _semanticElement) {
      if (null != _levelField)
        setTextValue(_levelField, _semanticElement, RequirementPackage.eINSTANCE.getRequirementsPkg_Level());
      if (null != _additionalInformationField)
        setTextValue(_additionalInformationField, _semanticElement, RequirementPackage.eINSTANCE.getRequirementsPkg_AdditionalInformation());
    }
  }

  /**
   * @param textField_p text field to be filled
   */
  @Override
  protected void fillTextField(Text textField_p) {
    if (textField_p.equals(_levelField)) {
      setDataValue(_semanticElement, RequirementPackage.eINSTANCE.getRequirementsPkg_Level(), _levelField.getText());
    } else if (textField_p.equals(_additionalInformationField)) {
      setDataValue(_semanticElement, RequirementPackage.eINSTANCE.getRequirementsPkg_AdditionalInformation(), _additionalInformationField.getText());
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setEnabled(boolean enabled_p) {
    if (null != _levelField && !_levelField.isDisposed()) {
      _levelField.setEnabled(enabled_p);
    }
    if (null != _additionalInformationField && !_additionalInformationField.isDisposed()) {
      _additionalInformationField.setEnabled(enabled_p);
    }
  }
}
