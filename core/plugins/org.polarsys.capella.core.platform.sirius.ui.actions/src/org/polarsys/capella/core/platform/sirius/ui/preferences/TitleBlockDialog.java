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
package org.polarsys.capella.core.platform.sirius.ui.preferences;

import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class TitleBlockDialog extends TitleAreaDialog {
  private final String TITLE_NAME = "Add name and content";
  private final String NAME_LABEL = "Name";
  private final String CONTENT_LABEL = "Content";
  private final String ERROR_MESSAGE = "The content of data fields can be customized via aql, feature or capella:\r\n"
      + "\r\n" + "      AQL query (aql:)\r\n" + "      Name of feature (feature:)\r\n"
      + "      Predefined service (capella:)\r\n\r\n " + "      Example: feature:name";
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

    txtContent.setLayoutData(dataContent);
  }

  @Override
  protected boolean isResizable() {
    return true;
  }

  private boolean saveInput() {
    name = txtName.getText();
    content = txtContent.getText();
    if (content.matches("feature:(.*)") || content.matches("aql:(.*)") || content.matches("capella:(.*)")) {

      return true;
    }
    setErrorMessage(ERROR_MESSAGE);
    return false;
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
