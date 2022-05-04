/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.data.information.datavalue.properties.fields;

import org.eclipse.emf.ecore.EObject;
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
import org.polarsys.capella.core.ui.properties.fields.AbstractSemanticField;

/**
 * The UnparsedExpression customized section class.
 */
public class UnparsedExpressionGroup extends AbstractSemanticField {

  protected Text unparsedExpressionTextField;

  /**
   * @param parent
   * @param widgetFactory
   */
  public UnparsedExpressionGroup(Composite parent, TabbedPropertySheetWidgetFactory widgetFactory) {
    super(widgetFactory);

    Group textGroup = widgetFactory.createGroup(parent, ""); //$NON-NLS-1$
    textGroup.setLayout(new GridLayout(2, false));
    GridData gd = new GridData(GridData.FILL_HORIZONTAL);
    gd.horizontalSpan = 2;
    textGroup.setLayoutData(gd);

    // UnparsedExpression
    createUnparsedExpressionField(textGroup);
  }

  /**
   * @param textGroup
   */
  private void createUnparsedExpressionField(Group textGroup) {
    CLabel unparsedExpressionLabel = widgetFactory.createCLabel(textGroup, Messages.getString("Expression.UnparsedExpressionLabel")); //$NON-NLS-1$
    GridData gd = new GridData();
    gd.horizontalSpan = 2;
    unparsedExpressionLabel.setLayoutData(gd);
    unparsedExpressionTextField = widgetFactory.createText(textGroup, "", SWT.BORDER | SWT.WRAP | SWT.MULTI); //$NON-NLS-1$
    gd = new GridData(GridData.FILL_HORIZONTAL);
    gd.horizontalSpan = 2;
    gd.heightHint = 80;
    gd.widthHint = 150;
    unparsedExpressionTextField.addFocusListener(this);
    unparsedExpressionTextField.addKeyListener(this);
    unparsedExpressionTextField.setLayoutData(gd);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void loadData(EObject semanticElement) {
    loadData(semanticElement, null);

    if (null != semanticElement && null != unparsedExpressionTextField) {
        setTextValue(unparsedExpressionTextField, semanticElement, DatavaluePackage.eINSTANCE.getAbstractExpressionValue_UnparsedExpression());
    }
  }

  /**
   * @param textField text field to be filled
   */
  @Override
  protected void fillTextField(Text textField) {
    if (textField.equals(unparsedExpressionTextField)) {
      setDataValue(semanticElement, DatavaluePackage.eINSTANCE.getAbstractExpressionValue_UnparsedExpression(), unparsedExpressionTextField.getText());
    }
  }

  /**
   *
   */
  public void clearUnparsedExpressionField() {
    if (null != unparsedExpressionTextField) {
      setDataValue(semanticElement, DatavaluePackage.eINSTANCE.getAbstractExpressionValue_UnparsedExpression(), ""); //$NON-NLS-1$
      unparsedExpressionTextField.setText(""); //$NON-NLS-1$
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setEnabled(boolean enabled) {
    if (null != unparsedExpressionTextField && !unparsedExpressionTextField.isDisposed()) {
      unparsedExpressionTextField.setEnabled(enabled);
    }
  }
}
