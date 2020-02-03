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
  private Text txtFeature;

  private String name;
  private String feature;

  public TitleBlockDialog(Shell parentShell) {

    super(parentShell);
    // TODO Auto-generated constructor stub
  }

  @Override
  public void create() {
    super.create();
    setTitle("Add name and feature");
  }

  @Override
  protected Control createDialogArea(Composite parent) {
    Composite area = (Composite) super.createDialogArea(parent);
    Composite container = new Composite(area, SWT.NONE);
    container.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
    GridLayout layout = new GridLayout(2, false);
    container.setLayout(layout);

    createFirstName(container);
    createLastName(container);

    return area;
  }

  private void createFirstName(Composite container) {
    Label lbtFirstName = new Label(container, SWT.NONE);
    lbtFirstName.setText("First Name");

    GridData dataFirstName = new GridData();
    dataFirstName.grabExcessHorizontalSpace = true;
    dataFirstName.horizontalAlignment = GridData.FILL;

    txtName = new Text(container, SWT.BORDER);
    txtName.setLayoutData(dataFirstName);
  }

  private void createLastName(Composite container) {
    Label lbtLastName = new Label(container, SWT.NONE);
    lbtLastName.setText("Last Name");

    GridData dataLastName = new GridData();
    dataLastName.grabExcessHorizontalSpace = true;
    dataLastName.horizontalAlignment = GridData.FILL;
    txtFeature = new Text(container, SWT.BORDER);
    txtFeature.setLayoutData(dataLastName);
  }

  @Override
  protected boolean isResizable() {
    return true;
  }

  private void saveInput() {
    name = txtName.getText();
    feature = txtFeature.getText();

  }

  @Override
  protected void okPressed() {
    saveInput();
    super.okPressed();
  }

  public String getFirstName() {
    return name;
  }

  public String getLastName() {
    return feature;
  }
}
