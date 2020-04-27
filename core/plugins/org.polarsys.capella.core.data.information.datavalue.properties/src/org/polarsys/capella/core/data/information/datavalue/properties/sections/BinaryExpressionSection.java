/*******************************************************************************
 * Copyright (c) 2006, 2019 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.data.information.datavalue.properties.sections;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;
import org.polarsys.capella.core.data.information.datavalue.BinaryExpression;
import org.polarsys.capella.core.data.information.datavalue.BinaryOperator;
import org.polarsys.capella.core.data.information.datavalue.DatavaluePackage;
import org.polarsys.capella.core.data.information.datavalue.properties.Messages;
import org.polarsys.capella.core.data.information.datavalue.properties.controllers.ExpressionController;
import org.polarsys.capella.core.data.information.datavalue.properties.fields.BinaryOperatorGroup;
import org.polarsys.capella.core.data.information.datavalue.properties.fields.UnparsedExpressionGroup;
import org.polarsys.capella.core.ui.properties.fields.AbstractSemanticField;
import org.polarsys.capella.core.ui.properties.fields.SimpleEditableSemanticField;
import org.polarsys.capella.core.ui.properties.fields.SimpleSemanticField;

/**
 * The BinaryExpression section.
 */
public class BinaryExpressionSection extends DataValueSection {

  private BinaryOperatorGroup operatorGroup;
  private SimpleSemanticField unitField;
  private SimpleEditableSemanticField leftOperandWidget;
  private SimpleEditableSemanticField rightOperandWidget;
  protected UnparsedExpressionGroup unparsedExpressionField;

  @Override
  public void createContents(Composite parent, TabbedPropertySheetPage aTabbedPropertySheetPage) {
    super.createContents(parent, aTabbedPropertySheetPage);

    boolean displayedInWizard = isDisplayedInWizard();

    operatorGroup = new BinaryOperatorGroup(parent, getWidgetFactory()) {
      /**
       * @see org.polarsys.capella.core.ui.properties.fields.custom.properties.fields.AbstractSemanticField#widgetDefaultSelected(org.eclipse.swt.events.SelectionEvent)
       */
      @Override
      public void widgetSelected(SelectionEvent event) {
        super.widgetSelected(event);

        evaluateTextStatus((BinaryOperator) ((Button) event.getSource()).getData());
      }
    };
    operatorGroup.setDisplayedInWizard(displayedInWizard);

    unitField = new SimpleSemanticField(getReferencesGroup(), Messages.getString("NumericValue.Unit.Label"), getWidgetFactory(), new ExpressionController()); //$NON-NLS-1$
    unitField.setDisplayedInWizard(displayedInWizard);

    leftOperandWidget =
      new SimpleEditableSemanticField(
          getReferencesGroup(),
          Messages.getString("BinaryExpression.LeftOperandLabel"), getWidgetFactory(), //$NON-NLS-1$
          Messages.getString("BinaryExpression.LeftOperandDefaultName"), new ExpressionController()); //$NON-NLS-1$
    leftOperandWidget.setDisplayedInWizard(displayedInWizard);

    rightOperandWidget =
      new SimpleEditableSemanticField(
          getReferencesGroup(),
          Messages.getString("BinaryExpression.RightOperandLabel"), getWidgetFactory(), //$NON-NLS-1$
          Messages.getString("BinaryExpression.RightOperandDefaultName"), new ExpressionController()); //$NON-NLS-1$
    rightOperandWidget.setDisplayedInWizard(displayedInWizard);

    unparsedExpressionField = new UnparsedExpressionGroup(parent, getWidgetFactory());
    unparsedExpressionField.setDisplayedInWizard(displayedInWizard);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void loadData(EObject capellaElement) {
    super.loadData(capellaElement);

    unitField.loadData(capellaElement, DatavaluePackage.eINSTANCE.getNumericValue_Unit());
    operatorGroup.loadData(capellaElement, DatavaluePackage.eINSTANCE.getBinaryExpression_Operator());
    leftOperandWidget.loadData(capellaElement, DatavaluePackage.Literals.BINARY_EXPRESSION__OWNED_LEFT_OPERAND);
    rightOperandWidget.loadData(capellaElement, DatavaluePackage.Literals.BINARY_EXPRESSION__OWNED_RIGHT_OPERAND);
    unparsedExpressionField.loadData(capellaElement);

    evaluateTextStatus(((BinaryExpression) capellaElement).getOperator());
  }

  /**
   * @param expression
   */
  protected void evaluateTextStatus(BinaryOperator operator) {
    if (BinaryOperator.UNSET.equals(operator)) {
      unparsedExpressionField.setEnabled(true);
    } else {
      unparsedExpressionField.setEnabled(false);
    }
  }

  /**
   * @see org.eclipse.jface.viewers.IFilter#select(java.lang.Object)
   */
  @Override
  public boolean select(Object toTest) {
    EObject eObjectToTest = super.selection(toTest);
    return ((eObjectToTest != null) && (eObjectToTest.eClass() == DatavaluePackage.eINSTANCE.getBinaryExpression()));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<AbstractSemanticField> getSemanticFields() {
    List<AbstractSemanticField> fields = new ArrayList<AbstractSemanticField>();

    fields.addAll(super.getSemanticFields());
    fields.add(leftOperandWidget);
    fields.add(operatorGroup);
    fields.add(rightOperandWidget);
    fields.add(unitField);
    fields.add(unparsedExpressionField);

    return fields;
  }
}
