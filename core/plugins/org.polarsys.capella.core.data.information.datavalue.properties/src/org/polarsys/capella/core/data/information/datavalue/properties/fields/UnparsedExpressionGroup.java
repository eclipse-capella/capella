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
package org.polarsys.capella.core.data.information.datavalue.properties.fields;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;

import org.polarsys.capella.core.data.information.datavalue.DatavaluePackage;
import org.polarsys.capella.core.data.information.datavalue.properties.Messages;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.ui.properties.fields.AbstractSemanticField;

/**
 * The UnparsedExpression customized section class.
 */
public class UnparsedExpressionGroup extends AbstractSemanticField {

  protected Text unparsedExpressionTextField;

  /**
   * @param parent_p
   * @param widgetFactory_p
   */
  public UnparsedExpressionGroup(Composite parent_p, TabbedPropertySheetWidgetFactory widgetFactory_p) {
    super(widgetFactory_p);

    Group textGroup = _widgetFactory.createGroup(parent_p, ""); //$NON-NLS-1$
    textGroup.setLayout(new GridLayout(2, false));
    GridData gd = new GridData(GridData.FILL_HORIZONTAL);
    gd.horizontalSpan = 2;
    textGroup.setLayoutData(gd);

    // UnparsedExpression
    createUnparsedExpressionField(textGroup);
  }

  /**
   * @param textGroup_p
   */
  private void createUnparsedExpressionField(Group textGroup_p) {
    CLabel unparsedExpressionLabel = _widgetFactory.createCLabel(textGroup_p, Messages.getString("Expression.UnparsedExpressionLabel")); //$NON-NLS-1$
    GridData gd = new GridData();
    gd.horizontalSpan = 2;
    unparsedExpressionLabel.setLayoutData(gd);
    unparsedExpressionTextField = _widgetFactory.createText(textGroup_p, "", SWT.BORDER | SWT.WRAP | SWT.MULTI); //$NON-NLS-1$
    gd = new GridData(GridData.FILL_HORIZONTAL);
    gd.horizontalSpan = 2;
    gd.heightHint = 80;
    unparsedExpressionTextField.addFocusListener(this);
    unparsedExpressionTextField.addKeyListener(this);
    unparsedExpressionTextField.setLayoutData(gd);
  }

  /**
   * @see org.polarsys.capella.core.ui.properties.fields.custom.properties.fields.AbstractSemanticField#loadData(org.polarsys.capella.core.data.capellacore.CapellaElement)
   */
  @Override
  public void loadData(CapellaElement semanticElement_p) {
    loadData(semanticElement_p, null);

    if (null != _semanticElement) {
      if (null != unparsedExpressionTextField)
        setTextValue(unparsedExpressionTextField, _semanticElement, DatavaluePackage.eINSTANCE.getAbstractExpressionValue_UnparsedExpression());
    }
  }

  /**
   * @param textField_p text field to be filled
   */
  @Override
  protected void fillTextField(Text textField_p) {
    if (textField_p.equals(unparsedExpressionTextField)) {
      setDataValue(_semanticElement, DatavaluePackage.eINSTANCE.getAbstractExpressionValue_UnparsedExpression(), unparsedExpressionTextField.getText());
    }
  }

  /**
   *
   */
  public void clearUnparsedExpressionField() {
    if (null != unparsedExpressionTextField) {
      setDataValue(_semanticElement, DatavaluePackage.eINSTANCE.getAbstractExpressionValue_UnparsedExpression(), ""); //$NON-NLS-1$
      unparsedExpressionTextField.setText(""); //$NON-NLS-1$
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setEnabled(boolean enabled_p) {
    if (null != unparsedExpressionTextField && !unparsedExpressionTextField.isDisposed()) {
      unparsedExpressionTextField.setEnabled(enabled_p);
    }
  }
}
