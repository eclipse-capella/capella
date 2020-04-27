/*******************************************************************************
 * Copyright (c) 2020 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.data.core.properties.fields;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.sirius.business.api.dialect.command.RefreshRepresentationsCommand;
import org.eclipse.sirius.common.tools.api.interpreter.EvaluationException;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.viewpoint.description.DAnnotation;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;
import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;
import org.polarsys.capella.core.data.core.properties.Messages;
import org.polarsys.capella.core.diagram.helpers.TitleBlockHelper;
import org.polarsys.capella.core.sirius.analysis.TitleBlockServices;
import org.polarsys.capella.core.ui.properties.fields.AbstractSemanticField;
import org.polarsys.capella.core.ui.properties.helpers.LockHelper;

public class TitleBlockBasicElementGroup extends AbstractSemanticField {
  private static final String NAME = "Name:";
  private static final String CONTENT = "Content:";
  private static final String INTERPRETER_ERROR = "Interpreter Error: Syntax not valid";
  private static final String EMPTY_STRING = "";
  protected Text nameTextField;
  protected Text contentTextField;
  protected CLabel error_label;

  /**
   * @param parent
   * @param widgetFactory
   * @param hasNameField
   * @param hasContentField
   */
  public TitleBlockBasicElementGroup(Composite parent, TabbedPropertySheetWidgetFactory widgetFactory) {
    super(widgetFactory);

    Group textGroup = widgetFactory.createGroup(parent, ICommonConstants.EMPTY_STRING);
    textGroup.setLayout(new GridLayout(2, false));
    GridData gd = new GridData(GridData.FILL_HORIZONTAL);
    gd.horizontalSpan = 2;
    textGroup.setLayoutData(gd);

    nameTextField = createTextField(textGroup, Messages.getString("NamedElement.NameLabel"));
    contentTextField = createTextField(textGroup, Messages.getString("NamedElement.ContentLabel"));
    error_label = widgetFactory.createCLabel(parent, "Error");
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
  public void loadData(EObject semanticElement, String name, String content) {
    super.loadData(semanticElement, null);
    if (null != semanticElement) {
      if (null != nameTextField)
        setTextValue(nameTextField, name);
      if (null != contentTextField)
        setTextValue(contentTextField, content);
    }
    DAnnotation titleBlockCell = ((DAnnotation) semanticElement);
    EObject container = titleBlockCell.eContainer();
    if (container instanceof DDiagram) {
      DDiagram diagram = (DDiagram) container;
      DAnnotation titleBlock = TitleBlockHelper.getParentTitleBlock(titleBlockCell, diagram);
      Object evaluateResult = TitleBlockServices.getService().getResultOfExpression(titleBlock,
          contentTextField.getText(), titleBlockCell);
      if (evaluateResult instanceof EvaluationException) {
        error_label.setText(INTERPRETER_ERROR);
        error_label.setForeground(Display.getCurrent().getSystemColor(SWT.COLOR_RED));
      } else {
        error_label.setText(EMPTY_STRING);
      }
    }
  }

  protected void setTextValue(Text text, String value) {
    if (null != value) {
      text.setText(value);
    }
  }

  /**
   * @param textField
   *          text field to be filled
   */
  @Override
  protected void fillTextField(Text textField) {
    if (textField.equals(nameTextField)) {
      setFieldValue(semanticElement, NAME, nameTextField.getText());
    } else if (textField.equals(contentTextField)) {
      setFieldValue(semanticElement, CONTENT, contentTextField.getText());
    }
  }

  private void setFieldValue(EObject object, String field, final Object value) {
    if (!((DAnnotation) object).getDetails().get(field).equals(value.toString())) {
      DDiagram diagram = (DDiagram) object.eContainer();
      AbstractReadWriteCommand command = new AbstractReadWriteCommand() {
        @Override
        public void run() {
          ((DAnnotation) object).getDetails().put(field, value.toString());
          RefreshRepresentationsCommand refreshCommand = new RefreshRepresentationsCommand(
              TransactionUtil.getEditingDomain(diagram), new NullProgressMonitor(), diagram);
          refreshCommand.execute();
        }
      };
      executeCommand(command);
    }
  }

  /**
   * @param enabled
   *          whether or not the name text field is enabled
   */
  public void enableNameField(boolean enabled) {
    if (null != nameTextField && !nameTextField.isDisposed()) {
      nameTextField.setEnabled(enabled);
    }
  }

  /**
   * @param enabled
   *          whether or not the content text field is enabled
   */
  public void enableContentField(boolean enabled) {
    if (null != contentTextField && !contentTextField.isDisposed()) {
      contentTextField.setEnabled(enabled);
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setEnabled(boolean enabled) {
    LockHelper.getInstance().enable(nameTextField, enabled);
    LockHelper.getInstance().enable(contentTextField, enabled);
  }

  @Override
  public void loadData(EObject semanticElement) {
    // TODO Auto-generated method stub
  }

}
