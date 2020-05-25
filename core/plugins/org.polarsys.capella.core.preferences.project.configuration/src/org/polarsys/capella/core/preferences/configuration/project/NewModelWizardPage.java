/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
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

package org.polarsys.capella.core.preferences.configuration.project;

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

/**
 * The new Capella model wizard page.
 */
public class NewModelWizardPage extends WizardPage {
  // constants
  private static final int SIZING_TEXT_FIELD_WIDTH = 150;

  // The model name text field.
  private Text _modelNameField;
  // The model name.
  private String _modelName;


  private boolean _opaSelected;
  private boolean _epbsSelected;

  // The name field listener.
  private ModifyListener _nameModifyListener = new ModifyListener() {
    @Override
    public void modifyText(ModifyEvent event_p) {
      boolean valid = true;
      setPageComplete(valid);
    }
  };

  /**
   * Constructs the page of the new CAPELLA model wizard.
   * @param pageName_p The page name.
   * @param selection_p The selection.
   */
  public NewModelWizardPage(String pageName_p, IStructuredSelection selection_p) {
    super(pageName_p);
    setPageComplete(false);
    _opaSelected = true;
    _epbsSelected = true;
  }

  /**
   * @see org.eclipse.jface.dialogs.DialogPage#dispose()
   */
  @Override
  public void dispose() {
    if (!_modelNameField.isDisposed()) {
      _modelNameField.removeModifyListener(_nameModifyListener);
    }
    super.dispose();
  }

  /**
   * @see org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets.Composite)
   */
  @Override
  public void createControl(Composite parent_p) {
    // 1 - Initialize the wizard page.
    initializeDialogUnits(parent_p);

    Composite composite = new Composite(parent_p, SWT.NONE);
    GridLayout layout = new GridLayout();
    layout.numColumns = 2;
    composite.setLayout(layout);
    GridData gData = new GridData();
    gData.horizontalAlignment = SWT.FILL;
    gData.verticalAlignment = SWT.FILL;
    composite.setLayoutData(gData);

    // 2 - Add a label and a text field to get model name from user input.
    Label modelLabel = new Label(composite, SWT.NONE);
    modelLabel.setText("NewModelWizardPage.model.name"); //$NON-NLS-1$
    modelLabel.setFont(parent_p.getFont());

    _modelNameField = new Text(composite, SWT.BORDER);
    gData = new GridData();
    gData.horizontalAlignment = SWT.FILL;
    gData.grabExcessHorizontalSpace = true;
    gData.minimumWidth = SIZING_TEXT_FIELD_WIDTH;
    gData.minimumWidth = SIZING_TEXT_FIELD_WIDTH;
    _modelNameField.setLayoutData(gData);
    _modelNameField.setFont(parent_p.getFont());

    _modelNameField.addModifyListener(_nameModifyListener);

    // 3 - Add checkboxes to select the optionals Operational Analysis & EPBS Architecture.
    Label architectureOptionsLabel = new Label(composite, SWT.NONE);
    architectureOptionsLabel.setText("NewModelWizardPage.architectures.options.label"); //$NON-NLS-1$

    Composite container = new Composite(composite, SWT.BORDER);
    GridData containerData = new GridData();
    containerData.horizontalAlignment = SWT.FILL;
    containerData.grabExcessHorizontalSpace = true;
    containerData.verticalAlignment = SWT.FILL;
    containerData.grabExcessVerticalSpace = true;
    container.setLayoutData(containerData);

    GridLayout buttonsLayout = new GridLayout(1, true);
    container.setLayout(buttonsLayout);

    // 3.1 - The "Operational Analysis" check box.
    Button opaCheckBox = createCheckBox(container, "NewModelWizardPage.operationalAnalysis.name", true, _opaSelected); //$NON-NLS-1$
    opaCheckBox.addSelectionListener(new SelectionAdapter() {
      /**
       * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
       */
      @Override
      public void widgetSelected(SelectionEvent event_p) {
        doOpaSelected(event_p);
      }
    });
    // 3.2 - The "System Analysis" check box.
    createCheckBox(container, "NewModelWizardPage.contextArchitecture.name", false, true); //$NON-NLS-1$
    // 3.3 - The "Logical Architecture" check box.
    createCheckBox(container, "NewModelWizardPage.logicalArchitecture.name", false, true); //$NON-NLS-1$
    // 3.4 - The "Physical Architecture" check box.
    createCheckBox(container, "NewModelWizardPage.physicalArchitecture.name", false, true); //$NON-NLS-1$
    // 3.5 - The "EPBS Architecture" check box.
    Button epbsCheckBox = createCheckBox(container, "NewModelWizardPage.epbsArchitecture.name", true, _epbsSelected); //$NON-NLS-1$
    epbsCheckBox.addSelectionListener(new SelectionAdapter() {
      /**
       * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
       */
      @Override
      public void widgetSelected(SelectionEvent event_p) {
        doEpbsSelected(event_p);
      }
    });
    setPageComplete(true/* validatePage() */);
    setControl(composite);
  }

  /**
   * Create a check button with given parameter.
   * @param parent_p
   * @param label_p
   * @param enabled <code>true</code> means you can interact with the button.
   * @param defaultSelection_p <code>true</code> means checked.
   */
  protected Button createCheckBox(Composite parent_p, String label_p, boolean enabled_p, boolean defaultSelection_p) {
    Button checkBox = new Button(parent_p, SWT.CHECK);
    checkBox.setText(label_p);
    GridData ctxGridData = new GridData();
    ctxGridData.horizontalAlignment = SWT.FILL;
    checkBox.setLayoutData(ctxGridData);
    checkBox.setSelection(defaultSelection_p);
    checkBox.setEnabled(enabled_p);
    return checkBox;
  }

  /**
   * @param event_p
   */
  protected void doEpbsSelected(SelectionEvent event_p) {
    _epbsSelected = ((Button) event_p.widget).getSelection();
  }

  /**
   * @see org.eclipse.jface.dialogs.DialogPage#setVisible(boolean)
   */
  @Override
  public void setVisible(boolean visible_p) {
    super.setVisible(visible_p);
    if (visible_p) {
      _modelNameField.setFocus();
    }
  }

  /**
   * Gets the model name.
   * @return The model name.
   */
  public String getModelName() {
    return _modelName;
  }

  /**
   * Checks if the operational analysis button is selected.
   * @return <code>True</code> if the Operational analysis is selected else <code>false</code>.
   */
  public boolean isOpaSelected() {
    return _opaSelected;
  }

  /**
   * Checks if the EPBS architecture button is selected.
   * @return <code>True</code> if the Operational analysis is selected else <code>false</code>.
   */
  public boolean isEpbsSelected() {
    return _epbsSelected;
  }

  /**
   * Sets the value_p of the model name field.
   * @param value_p The value to set.
   */
  public void setModelNameFieldValue(String value_p) {
    _modelNameField.setText(value_p);
  }

  /**
   * @param event_p
   */
  protected void doOpaSelected(SelectionEvent event_p) {
    _opaSelected = ((Button) event_p.widget).getSelection();
  }
}
