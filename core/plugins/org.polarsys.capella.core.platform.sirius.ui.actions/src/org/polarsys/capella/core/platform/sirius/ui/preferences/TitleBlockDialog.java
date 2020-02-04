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

  private Text txtName;
  private Text txtContent;

  private String name;
  private String content;

  public TitleBlockDialog(Shell parentShell) {

    super(parentShell);
    // TODO Auto-generated constructor stub
  }

  @Override
  public void create() {
    super.create();
    setTitle("Add name and content");
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
    lbName.setText("Name");

    GridData dataFirstName = new GridData();
    dataFirstName.grabExcessHorizontalSpace = true;
    dataFirstName.horizontalAlignment = GridData.FILL;

    txtName = new Text(container, SWT.BORDER);
    txtName.setLayoutData(dataFirstName);
  }

  private void createContent(Composite container) {
    Label lbContent = new Label(container, SWT.NONE);
    lbContent.setText("Content");

    GridData dataLastName = new GridData();
    dataLastName.grabExcessHorizontalSpace = true;
    dataLastName.horizontalAlignment = GridData.FILL;
    txtContent = new Text(container, SWT.BORDER);
    txtContent.setLayoutData(dataLastName);
  }

  @Override
  protected boolean isResizable() {
    return true;
  }

  private void saveInput() {
    name = txtName.getText();
    content = txtContent.getText();

  }

  @Override
  protected void okPressed() {
    saveInput();
    super.okPressed();
  }

  public String getName() {
    return name;
  }

  public String getContent() {
    return content;
  }
}
