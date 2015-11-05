/*******************************************************************************
 * Copyright (c) 2006, 2015 THALES GLOBAL SERVICES.
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

  private Text requirementIdField;
  private Text additionalInformationField;
  private Text verificationMethodField;
  private Text verificationPhaseField;
  private Text implementationVersionField;
  private Text featureField;

  /**
   * @param parent
   * @param widgetFactory
   */
  public RequirementGroup(Composite parent, TabbedPropertySheetWidgetFactory widgetFactory) {
    super(widgetFactory);

    Group textGroup = widgetFactory.createGroup(parent, ""); //$NON-NLS-1$
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
   * @param textGroup
   */
  private void createRequirementIdTextField(Group textGroup) {
    _widgetFactory.createCLabel(textGroup, Messages.getString("RequirementGroup.RequirementId.Label")); //$NON-NLS-1$
    requirementIdField = _widgetFactory.createText(textGroup, ""); //$NON-NLS-1$
    requirementIdField.addFocusListener(this);
    requirementIdField.addKeyListener(this);
    requirementIdField.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
  }

  /**
   * @param textGroup
   */
  private void createAdditionalInformationTextField(Group textGroup) {
    CLabel label = _widgetFactory.createCLabel(textGroup, Messages.getString("RequirementGroup.AdditionalInformation.Label")); //$NON-NLS-1$
    GridData gd = new GridData();
    gd.horizontalSpan = 2;
    label.setLayoutData(gd);
    additionalInformationField = _widgetFactory.createText(textGroup, "", SWT.BORDER | SWT.WRAP | SWT.MULTI); //$NON-NLS-1$
    additionalInformationField.addFocusListener(this);
    additionalInformationField.addKeyListener(this);
    gd = new GridData(GridData.FILL_HORIZONTAL);
    gd.horizontalSpan = 2;
    gd.heightHint = 80;
    additionalInformationField.setLayoutData(gd);
  }

  /**
   * @param textGroup
   */
  private void createVerificationMethodTextField(Group textGroup) {
    _widgetFactory.createCLabel(textGroup, Messages.getString("RequirementGroup.VerificationMethod.Label")); //$NON-NLS-1$
    verificationMethodField = _widgetFactory.createText(textGroup, ""); //$NON-NLS-1$
    verificationMethodField.addFocusListener(this);
    verificationMethodField.addKeyListener(this);
    verificationMethodField.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
  }

  /**
   * @param textGroup
   */
  private void createVerificationPhaseTextField(Group textGroup) {
    _widgetFactory.createCLabel(textGroup, Messages.getString("RequirementGroup.VerificationPhase.Label")); //$NON-NLS-1$
    verificationPhaseField = _widgetFactory.createText(textGroup, ""); //$NON-NLS-1$
    verificationPhaseField.addFocusListener(this);
    verificationPhaseField.addKeyListener(this);
    verificationPhaseField.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
  }

  /**
   * @param textGroup
   */
  private void createImplementationVersionTextField(Group textGroup) {
    _widgetFactory.createCLabel(textGroup, Messages.getString("RequirementGroup.ImplementationVersion.Label")); //$NON-NLS-1$
    implementationVersionField = _widgetFactory.createText(textGroup, ""); //$NON-NLS-1$
    implementationVersionField.addFocusListener(this);
    implementationVersionField.addKeyListener(this);
    implementationVersionField.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
  }

  /**
   * @param textGroup
   */
  private void createFeatureTextField(Group textGroup) {
    _widgetFactory.createCLabel(textGroup, Messages.getString("RequirementGroup.Feature.Label")); //$NON-NLS-1$
    featureField = _widgetFactory.createText(textGroup, ""); //$NON-NLS-1$
    featureField.addFocusListener(this);
    featureField.addKeyListener(this);
    featureField.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
  }

  /**
   * @see org.polarsys.capella.core.ui.properties.fields.custom.properties.fields.AbstractSemanticField#loadData(org.polarsys.capella.core.data.capellacore.CapellaElement)
   */
  @Override
  public void loadData(CapellaElement semanticElement) {
    loadData(semanticElement, null);

    if (null != _semanticElement) {
      if (null != requirementIdField)
        setTextValue(requirementIdField, _semanticElement, RequirementPackage.eINSTANCE.getRequirement_RequirementId());
      if (null != additionalInformationField)
        setTextValue(additionalInformationField, _semanticElement, RequirementPackage.eINSTANCE.getRequirement_AdditionalInformation());
      if (null != verificationMethodField)
        setTextValue(verificationMethodField, _semanticElement, RequirementPackage.eINSTANCE.getRequirement_VerificationMethod());
      if (null != verificationPhaseField)
        setTextValue(verificationPhaseField, _semanticElement, RequirementPackage.eINSTANCE.getRequirement_VerificationPhase());
      if (null != implementationVersionField)
        setTextValue(implementationVersionField, _semanticElement, RequirementPackage.eINSTANCE.getRequirement_ImplementationVersion());
      if (null != featureField)
        setTextValue(featureField, _semanticElement, RequirementPackage.eINSTANCE.getRequirement_Feature());
    }
  }

  /**
   * @param textField text field to be filled
   */
  @Override
  protected void fillTextField(Text textField) {
    if (textField.equals(requirementIdField)) {
      setDataValue(_semanticElement, RequirementPackage.eINSTANCE.getRequirement_RequirementId(), requirementIdField.getText());
    } else if (textField.equals(additionalInformationField)) {
      setDataValue(_semanticElement, RequirementPackage.eINSTANCE.getRequirement_AdditionalInformation(), additionalInformationField.getText());
    } else if (textField.equals(verificationMethodField)) {
      setDataValue(_semanticElement, RequirementPackage.eINSTANCE.getRequirement_VerificationMethod(), verificationMethodField.getText());
    } else if (textField.equals(verificationPhaseField)) {
      setDataValue(_semanticElement, RequirementPackage.eINSTANCE.getRequirement_VerificationPhase(), verificationPhaseField.getText());
    } else if (textField.equals(implementationVersionField)) {
      setDataValue(_semanticElement, RequirementPackage.eINSTANCE.getRequirement_ImplementationVersion(), implementationVersionField.getText());
    } else if (textField.equals(featureField)) {
      setDataValue(_semanticElement, RequirementPackage.eINSTANCE.getRequirement_Feature(), featureField.getText());
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setEnabled(boolean enabled) {
    if (null != requirementIdField && !requirementIdField.isDisposed()) {
      requirementIdField.setEnabled(enabled);
    }
    if (null != additionalInformationField && !additionalInformationField.isDisposed()) {
      additionalInformationField.setEnabled(enabled);
    }
    if (null != verificationMethodField && !verificationMethodField.isDisposed()) {
      verificationMethodField.setEnabled(enabled);
    }
    if (null != verificationPhaseField && !verificationPhaseField.isDisposed()) {
      verificationPhaseField.setEnabled(enabled);
    }
    if (null != implementationVersionField && !implementationVersionField.isDisposed()) {
      implementationVersionField.setEnabled(enabled);
    }
    if (null != featureField && !featureField.isDisposed()) {
      featureField.setEnabled(enabled);
    }
  }
}
