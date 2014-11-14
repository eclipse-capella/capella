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
public class RequirementGroup extends AbstractSemanticField {

  private Text _requirementIdField;
  private Text _additionalInformationField;
  private Text _verificationMethodField;
  private Text _verificationPhaseField;
  private Text _implementationVersionField;
  private Text _featureField;

  /**
   * @param parent_p
   * @param widgetFactory_p
   */
  public RequirementGroup(Composite parent_p, TabbedPropertySheetWidgetFactory widgetFactory_p) {
    super(widgetFactory_p);

    Group textGroup = widgetFactory_p.createGroup(parent_p, ""); //$NON-NLS-1$
    textGroup.setLayout(new GridLayout(2, false));
    GridData gd = new GridData(GridData.FILL_HORIZONTAL);
    gd.horizontalSpan = 2;
    textGroup.setLayoutData(gd);

    createRequirementIdTextField(textGroup);
    createAdditionalInformationTextField(textGroup);
    createVerificationMethodTextField(textGroup);
    createVerificationPhaseTextField(textGroup);
    createImplementationVersionTextField(textGroup);
    createFeatureTextField(textGroup);
  }

  /**
   * @param textGroup_p
   */
  private void createRequirementIdTextField(Group textGroup_p) {
    _widgetFactory.createCLabel(textGroup_p, Messages.getString("RequirementGroup.RequirementId.Label")); //$NON-NLS-1$
    _requirementIdField = _widgetFactory.createText(textGroup_p, ""); //$NON-NLS-1$
    _requirementIdField.addFocusListener(this);
    _requirementIdField.addKeyListener(this);
    _requirementIdField.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
  }

  /**
   * @param textGroup_p
   */
  private void createAdditionalInformationTextField(Group textGroup_p) {
    CLabel label = _widgetFactory.createCLabel(textGroup_p, Messages.getString("RequirementGroup.AdditionalInformation.Label")); //$NON-NLS-1$
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
   * @param textGroup_p
   */
  private void createVerificationMethodTextField(Group textGroup_p) {
    _widgetFactory.createCLabel(textGroup_p, Messages.getString("RequirementGroup.VerificationMethod.Label")); //$NON-NLS-1$
    _verificationMethodField = _widgetFactory.createText(textGroup_p, ""); //$NON-NLS-1$
    _verificationMethodField.addFocusListener(this);
    _verificationMethodField.addKeyListener(this);
    _verificationMethodField.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
  }

  /**
   * @param textGroup_p
   */
  private void createVerificationPhaseTextField(Group textGroup_p) {
    _widgetFactory.createCLabel(textGroup_p, Messages.getString("RequirementGroup.VerificationPhase.Label")); //$NON-NLS-1$
    _verificationPhaseField = _widgetFactory.createText(textGroup_p, ""); //$NON-NLS-1$
    _verificationPhaseField.addFocusListener(this);
    _verificationPhaseField.addKeyListener(this);
    _verificationPhaseField.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
  }

  /**
   * @param textGroup_p
   */
  private void createImplementationVersionTextField(Group textGroup_p) {
    _widgetFactory.createCLabel(textGroup_p, Messages.getString("RequirementGroup.ImplementationVersion.Label")); //$NON-NLS-1$
    _implementationVersionField = _widgetFactory.createText(textGroup_p, ""); //$NON-NLS-1$
    _implementationVersionField.addFocusListener(this);
    _implementationVersionField.addKeyListener(this);
    _implementationVersionField.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
  }

  /**
   * @param textGroup_p
   */
  private void createFeatureTextField(Group textGroup_p) {
    _widgetFactory.createCLabel(textGroup_p, Messages.getString("RequirementGroup.Feature.Label")); //$NON-NLS-1$
    _featureField = _widgetFactory.createText(textGroup_p, ""); //$NON-NLS-1$
    _featureField.addFocusListener(this);
    _featureField.addKeyListener(this);
    _featureField.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
  }

  /**
   * @see org.polarsys.capella.core.ui.properties.fields.custom.properties.fields.AbstractSemanticField#loadData(org.polarsys.capella.core.data.capellacore.CapellaElement)
   */
  @Override
  public void loadData(CapellaElement semanticElement_p) {
    loadData(semanticElement_p, null);

    if (null != _semanticElement) {
      if (null != _requirementIdField)
        setTextValue(_requirementIdField, _semanticElement, RequirementPackage.eINSTANCE.getRequirement_RequirementId());
      if (null != _additionalInformationField)
        setTextValue(_additionalInformationField, _semanticElement, RequirementPackage.eINSTANCE.getRequirement_AdditionalInformation());
      if (null != _verificationMethodField)
        setTextValue(_verificationMethodField, _semanticElement, RequirementPackage.eINSTANCE.getRequirement_VerificationMethod());
      if (null != _verificationPhaseField)
        setTextValue(_verificationPhaseField, _semanticElement, RequirementPackage.eINSTANCE.getRequirement_VerificationPhase());
      if (null != _implementationVersionField)
        setTextValue(_implementationVersionField, _semanticElement, RequirementPackage.eINSTANCE.getRequirement_ImplementationVersion());
      if (null != _featureField)
        setTextValue(_featureField, _semanticElement, RequirementPackage.eINSTANCE.getRequirement_Feature());
    }
  }

  /**
   * @param textField_p text field to be filled
   */
  @Override
  protected void fillTextField(Text textField_p) {
    if (textField_p.equals(_requirementIdField)) {
      setDataValue(_semanticElement, RequirementPackage.eINSTANCE.getRequirement_RequirementId(), _requirementIdField.getText());
    } else if (textField_p.equals(_additionalInformationField)) {
      setDataValue(_semanticElement, RequirementPackage.eINSTANCE.getRequirement_AdditionalInformation(), _additionalInformationField.getText());
    } else if (textField_p.equals(_verificationMethodField)) {
      setDataValue(_semanticElement, RequirementPackage.eINSTANCE.getRequirement_VerificationMethod(), _verificationMethodField.getText());
    } else if (textField_p.equals(_verificationPhaseField)) {
      setDataValue(_semanticElement, RequirementPackage.eINSTANCE.getRequirement_VerificationPhase(), _verificationPhaseField.getText());
    } else if (textField_p.equals(_implementationVersionField)) {
      setDataValue(_semanticElement, RequirementPackage.eINSTANCE.getRequirement_ImplementationVersion(), _implementationVersionField.getText());
    } else if (textField_p.equals(_featureField)) {
      setDataValue(_semanticElement, RequirementPackage.eINSTANCE.getRequirement_Feature(), _featureField.getText());
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setEnabled(boolean enabled_p) {
    if (null != _requirementIdField && !_requirementIdField.isDisposed()) {
      _requirementIdField.setEnabled(enabled_p);
    }
    if (null != _additionalInformationField && !_additionalInformationField.isDisposed()) {
      _additionalInformationField.setEnabled(enabled_p);
    }
    if (null != _verificationMethodField && !_verificationMethodField.isDisposed()) {
      _verificationMethodField.setEnabled(enabled_p);
    }
    if (null != _verificationPhaseField && !_verificationPhaseField.isDisposed()) {
      _verificationPhaseField.setEnabled(enabled_p);
    }
    if (null != _implementationVersionField && !_implementationVersionField.isDisposed()) {
      _implementationVersionField.setEnabled(enabled_p);
    }
    if (null != _featureField && !_featureField.isDisposed()) {
      _featureField.setEnabled(enabled_p);
    }
  }
}
