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

import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;
import org.polarsys.capella.core.data.core.properties.Messages;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.ui.properties.fields.AbstractSemanticField;
import org.polarsys.capella.core.ui.properties.helpers.LockHelper;
import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;

/**
 */
public class BasicElementGroup extends AbstractSemanticField {

  protected Text nameTextField;
  protected Text summaryTextField;

  /**
   * @param parent_p
   * @param widgetFactory_p
   */
  public BasicElementGroup(Composite parent_p, TabbedPropertySheetWidgetFactory widgetFactory_p) {
    this(parent_p, widgetFactory_p, true, true);
  }

  /**
   * @param parent_p
   * @param widgetFactory_p
   * @param hasNameField_p
   * @param hasSummaryField_p
   */
  public BasicElementGroup(Composite parent_p, TabbedPropertySheetWidgetFactory widgetFactory_p, boolean hasNameField_p, boolean hasSummaryField_p) {
    super(widgetFactory_p);

    Group textGroup = _widgetFactory.createGroup(parent_p, ""); //$NON-NLS-1$
    textGroup.setLayout(new GridLayout(2, false));
    GridData gd = new GridData(GridData.FILL_HORIZONTAL);
    gd.horizontalSpan = 2;
    textGroup.setLayoutData(gd);

    // Name
    if (hasNameField_p) {
      nameTextField = createTextField(textGroup, Messages.getString("NamedElement.NameLabel")); //$NON-NLS-1$
    }
    // Summary
    if (hasSummaryField_p) {
      summaryTextField = createTextField(textGroup, Messages.getString("NamedElement.SummaryLabel")); //$NON-NLS-1$
    }
  }

  /**
   * @param textGroup_p
   * @param textLabel_p
   */
  private Text createTextField(Group textGroup_p, String textLabel_p) {
    _widgetFactory.createCLabel(textGroup_p, textLabel_p);

    Text textField = _widgetFactory.createText(textGroup_p, ICommonConstants.EMPTY_STRING);
    textField.addFocusListener(this);
    textField.addKeyListener(this);
    textField.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

    return textField;
  }

  /**
   * @see org.polarsys.capella.core.ui.properties.fields.custom.properties.fields.AbstractSemanticField#loadData(org.polarsys.capella.core.data.capellacore.CapellaElement)
   */
  @Override
  public void loadData(CapellaElement semanticElement_p) {
    loadData(semanticElement_p, null);

    if (null != _semanticElement) {
      if (null != nameTextField)
        setTextValue(nameTextField, _semanticElement, ModellingcorePackage.eINSTANCE.getAbstractNamedElement_Name());
      if (null != summaryTextField)
        setTextValue(summaryTextField, _semanticElement, CapellacorePackage.eINSTANCE.getCapellaElement_Summary());
    }
  }

  /**
   * @param textField_p text field to be filled
   */
  @Override
  protected void fillTextField(Text textField_p) {
    if (textField_p.equals(nameTextField)) {
      setDataValue(_semanticElement, ModellingcorePackage.eINSTANCE.getAbstractNamedElement_Name(), nameTextField.getText());
    } else if (textField_p.equals(summaryTextField)) {
      setDataValue(_semanticElement, CapellacorePackage.eINSTANCE.getCapellaElement_Summary(), summaryTextField.getText());
    }
  }

  /**
   *
   */
  public void clearNameField() {
    if (null != nameTextField) {
      setDataValue(_semanticElement, ModellingcorePackage.eINSTANCE.getAbstractNamedElement_Name(), ""); //$NON-NLS-1$
      nameTextField.setText(""); //$NON-NLS-1$
    }
  }

  /**
   * @param enabled_p whether or not the name text field is enabled
   */
  public void enableNameField(boolean enabled_p) {
    if (null != nameTextField && !nameTextField.isDisposed()) {
      nameTextField.setEnabled(enabled_p);
    }
  }

  /**
   * @param enabled_p whether or not the summary text field is enabled
   */
  public void enableSummaryField(boolean enabled_p) {
    if (null != summaryTextField && !summaryTextField.isDisposed()) {
      summaryTextField.setEnabled(enabled_p);
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setEnabled(boolean enabled_p) {
    LockHelper.getInstance().enable(nameTextField, enabled_p);
    LockHelper.getInstance().enable(summaryTextField, enabled_p);
  }
}
