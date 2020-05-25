/*******************************************************************************
 * Copyright (c) 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.sirius.analysis.preferences;

import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.sirius.common.tools.api.interpreter.EvaluationException;
import org.eclipse.sirius.viewpoint.ViewpointFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.polarsys.capella.core.diagram.helpers.TitleBlockHelper;

public class TitleBlockDialog extends TitleAreaDialog {
  private final String TITLE_NAME = "Add name and content";
  private final String NAME_LABEL = "Name";
  private final String CONTENT_LABEL = "Content";
  private final String INTERPRETOR_ERROR = "The expression is not valid\r\n";
  private final String INFO_MESSAGE = "The content field can be customized via aql, feature or capella queries.\r\n";
  private Text txtName;
  private Text txtContent;

  private String name;
  private String content;

  private String currentName;
  private String currentContent;

  public TitleBlockDialog(Shell parentShell) {

    super(parentShell);
  }

  @Override
  public void create() {
    super.create();
    setTitle(TITLE_NAME);
    setMessage(INFO_MESSAGE, 2);
  }

  @Override
  protected Control createDialogArea(Composite parent) {
    Composite area = (Composite) super.createDialogArea(parent);
    Composite container = new Composite(area, SWT.NONE);
    container.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
    GridLayout layout = new GridLayout(2, false);
    container.setLayout(layout);
    
    createName(container);
    createContent(container);

    return area;
  }

  private void createName(Composite container) {
    Label lbName = new Label(container, SWT.NONE);
    lbName.setText(NAME_LABEL);

    GridData dataName = new GridData();
    dataName.grabExcessHorizontalSpace = true;
    dataName.horizontalAlignment = GridData.FILL;

    txtName = new Text(container, SWT.BORDER);
    txtName.setText(currentName);
    txtName.setLayoutData(dataName);
  }

  private void createContent(Composite container) {
    Label lbContent = new Label(container, SWT.NONE);
    lbContent.setText(CONTENT_LABEL);

    GridData dataContent = new GridData();
    dataContent.grabExcessHorizontalSpace = true;
    dataContent.horizontalAlignment = GridData.FILL;
    txtContent = new Text(container, SWT.BORDER);
    txtContent.setText(currentContent);
    
    // if we have a capella service, auto-get the name from the service name
    txtContent.addModifyListener(new ModifyListener() {
      @Override
      public void modifyText(ModifyEvent e) {
        if (!txtContent.getText().equals(content) && txtContent.getText().contains(TitleBlockHelper.CAPELLA_PREFIX) && evaluate()) {
          txtName.setText(TitleBlockHelper.getServiceName(txtContent.getText()));
        }
      }
    });

    txtContent.setLayoutData(dataContent);
    TitleBlockHelper.getServicesProposals(txtContent,
        ViewpointFactory.eINSTANCE.createDRepresentationDescriptor());
  }

  @Override
  protected boolean isResizable() {
    return true;
  }

  private boolean saveInput() {
    boolean evalResult = evaluate();
    if (evalResult) {
      name = txtName.getText();
      content = txtContent.getText();
    }
    return evalResult;
  }
  
  private boolean evaluate() {
    Object evaluateResult = TitleBlockHelper.getResultOfExpression(ViewpointFactory.eINSTANCE.createDRepresentationDescriptor(),
        txtContent.getText(), null);
    if (evaluateResult instanceof EvaluationException) {
      setErrorMessage(INTERPRETOR_ERROR);
      return false;
    }
    setErrorMessage(null);
    return true;
  }

  @Override
  protected void okPressed() {
    if (saveInput()) {
      super.okPressed();
    }

  }

  public String getName() {
    return name;
  }

  public String getContent() {
    return content;
  }

  public void setCurrentName(String name) {
    currentName = name;
  }

  public void setCurrentContent(String content) {
    currentContent = content;
  }
}
