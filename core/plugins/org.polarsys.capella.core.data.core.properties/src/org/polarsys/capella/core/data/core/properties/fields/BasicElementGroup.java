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
import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;
import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.data.core.properties.Messages;
import org.polarsys.capella.core.ui.properties.fields.AbstractSemanticField;
import org.polarsys.capella.core.ui.properties.helpers.LockHelper;

/**
 */
public class BasicElementGroup extends AbstractSemanticField {

  protected Text nameTextField;
  protected Text summaryTextField;

  /**
   * @param parent
   * @param widgetFactory
   */
  public BasicElementGroup(Composite parent, TabbedPropertySheetWidgetFactory widgetFactory) {
    this(parent, widgetFactory, true, true);
  }

  /**
   * @param parent
   * @param widgetFactory
   * @param hasNameField
   * @param hasSummaryField
   */
  public BasicElementGroup(Composite parent, TabbedPropertySheetWidgetFactory widgetFactory, boolean hasNameField, boolean hasSummaryField) {
    super(widgetFactory);

    Group textGroup = widgetFactory.createGroup(parent, ICommonConstants.EMPTY_STRING);
    textGroup.setLayout(new GridLayout(2, false));
    GridData gd = new GridData(GridData.FILL_HORIZONTAL);
    gd.horizontalSpan = 2;
    textGroup.setLayoutData(gd);

    // Name
    if (hasNameField) {
      nameTextField = createTextField(textGroup, Messages.getString("NamedElement.NameLabel")); //$NON-NLS-1$
    }
    // Summary
    if (hasSummaryField) {
      summaryTextField = createTextField(textGroup, Messages.getString("NamedElement.SummaryLabel")); //$NON-NLS-1$
    }
  }

  /**
   * @param textGroup
   * @param textLabel
   */
  private Text createTextField(Group textGroup, String textLabel) {
    widgetFactory.createCLabel(textGroup, textLabel);

    Text textField = widgetFactory.createText(textGroup, ICommonConstants.EMPTY_STRING);
    textField.addFocusListener(this);
    textField.addKeyListener(this);
    textField.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

    return textField;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void loadData(EObject semanticElement) {
    loadData(semanticElement, null);

    if (null != semanticElement) {
      if (null != nameTextField)
        setTextValue(nameTextField, semanticElement, ModellingcorePackage.eINSTANCE.getAbstractNamedElement_Name());
      if (null != summaryTextField)
        setTextValue(summaryTextField, semanticElement, CapellacorePackage.eINSTANCE.getCapellaElement_Summary());
    }
  }

  /**
   * @param textField text field to be filled
   */
  @Override
  protected void fillTextField(Text textField) {
    if (textField.equals(nameTextField)) {
      setDataValue(semanticElement, ModellingcorePackage.eINSTANCE.getAbstractNamedElement_Name(), nameTextField.getText());
    } else if (textField.equals(summaryTextField)) {
      setDataValue(semanticElement, CapellacorePackage.eINSTANCE.getCapellaElement_Summary(), summaryTextField.getText());
    }
  }

  /**
   *
   */
  public void clearNameField() {
    if (null != nameTextField) {
      setDataValue(semanticElement, ModellingcorePackage.eINSTANCE.getAbstractNamedElement_Name(), ""); //$NON-NLS-1$
      nameTextField.setText(""); //$NON-NLS-1$
    }
  }

  /**
   * @param enabled whether or not the name text field is enabled
   */
  public void enableNameField(boolean enabled) {
    if (null != nameTextField && !nameTextField.isDisposed()) {
      nameTextField.setEnabled(enabled);
    }
  }

  /**
   * @param enabled whether or not the summary text field is enabled
   */
  public void enableSummaryField(boolean enabled) {
    if (null != summaryTextField && !summaryTextField.isDisposed()) {
      summaryTextField.setEnabled(enabled);
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setEnabled(boolean enabled) {
    LockHelper.getInstance().enable(nameTextField, enabled);
    LockHelper.getInstance().enable(summaryTextField, enabled);
  }
}
