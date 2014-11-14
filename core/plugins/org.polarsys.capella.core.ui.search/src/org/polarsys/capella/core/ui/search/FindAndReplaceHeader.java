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
package org.polarsys.capella.core.ui.search;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;

/**
 */
public class FindAndReplaceHeader extends Composite {

  private Combo _combo;
  private Combo _combo_1;
  private Button _button;
  private Button _btnCheckButton;
  private Button _button_1;
  private Button _button_2;
  private Button _button_3;
  private Button _btnAllModel;
  private Button _btnCheckButton_1;
  private Button _btnSelectedElements;
  private Group impact_group;
  private Label nbImpactedNames;
  private Label nbImpactedSummaries;
  private Label nbImpactedDescs;
  private GridData gd_impact_group;
  private Button _button_4;
  private Composite _composite;

  @Override
  protected void checkSubclass() {
    // Disable the check that prevents subclassing of SWT components
  }

  /**
   * Create the composite.
   * @param parent
   * @param style
   */
  public FindAndReplaceHeader(Composite parent, int style) {
    super(parent, SWT.NONE);
    addDisposeListener(new DisposeListener() {
      public void widgetDisposed(DisposeEvent e) {
        dispose();
      }
    });
    GridLayout gridLayout = new GridLayout(1, false);
    gridLayout.marginWidth = 2;
    gridLayout.verticalSpacing = 0;
    setLayout(gridLayout);
    setLayoutData(new GridData(GridData.FILL, GridData.FILL, true, true));

    Composite composite = new Composite(this, SWT.NONE);
    GridData gd_composite = new GridData(GridData.FILL, SWT.TOP, true, false, 1, 1);
    gd_composite.widthHint = 450;
    composite.setLayoutData(gd_composite);

    GridLayout gl_composite = new GridLayout(2, false);
    //gl_composite.marginLeft = 10;
    gl_composite.verticalSpacing = 2;
    //gl_composite.marginWidth = 4;
    gl_composite.marginTop = 1;
    composite.setLayout(gl_composite);

    Label label = new Label(composite, SWT.NONE);
    label.setText("Find:"); //$NON-NLS-1$

    _combo = new Combo(composite, SWT.NONE);
    _combo.setVisibleItemCount(50);
    GridData gd_combo = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
    gd_combo.widthHint = 450;
    _combo.setLayoutData(gd_combo);

    Label label_1 = new Label(composite, SWT.NONE);
    label_1.setText("Replace with:"); //$NON-NLS-1$

    _combo_1 = new Combo(composite, SWT.NONE);
    GridData gd_combo_1 = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
    gd_combo_1.widthHint = 158;
    _combo_1.setLayoutData(gd_combo_1);
    gl_composite.marginWidth = 0;
    gd_composite.widthHint = 466;

    Group grpOptions = new Group(this, SWT.NONE);
    GridData gd_grpOptions = new GridData(GridData.FILL, SWT.CENTER, true, false, 1, 1);
    gd_grpOptions.widthHint = 450;
    grpOptions.setLayoutData(gd_grpOptions);
    grpOptions.setText("Options"); //$NON-NLS-1$
    GridLayout gl_grpOptions = new GridLayout(4, false);
    gl_grpOptions.verticalSpacing = 2;
    grpOptions.setLayout(gl_grpOptions);

    _button = new Button(grpOptions, SWT.CHECK);
    _button.setText("Case sensitive"); //$NON-NLS-1$

    _btnCheckButton = new Button(grpOptions, SWT.CHECK);
    _btnCheckButton.addSelectionListener(new SelectionAdapter() {
      @Override
      public void widgetSelected(SelectionEvent e) {
        if (_btnCheckButton.getSelection()) {
          _button_4.setEnabled(false);
        } else {
          _button_4.setEnabled(true);
        }

      }
    });
    _btnCheckButton.setText("Wildcards"); //$NON-NLS-1$

    _button_4 = new Button(grpOptions, SWT.CHECK);
    _button_4.setText("Whole expression");

    _btnCheckButton_1 = new Button(grpOptions, SWT.CHECK);
    _btnCheckButton_1.setText("Update description hyperlinks");

    _composite = new Composite(this, SWT.NONE);
    GridData gd_composite2 = new GridData(GridData.FILL, SWT.CENTER, true, false, 1, 1);
    gd_composite2.widthHint = 476;
    _composite.setLayoutData(gd_composite2);
    GridLayout gl_composite2 = new GridLayout(2, false);
    //gl_composite2.marginWidth = 10;
    _composite.setLayout(gl_composite2);

    Group group = new Group(_composite, SWT.NONE);
    GridData gd_group = new GridData(GridData.FILL, SWT.CENTER, true, false, 1, 1);
    gd_group.heightHint = 30;
    gd_group.widthHint = 224;
    group.setLayoutData(gd_group);
    group.setText("Search for"); //$NON-NLS-1$
    GridLayout gl_group = new GridLayout(6, false);
    gl_group.verticalSpacing = 3;
    group.setLayout(gl_group);

    _button_1 = new Button(group, SWT.CHECK);
    _button_1.setSelection(true);
    _button_1.setText("Name"); //$NON-NLS-1$

    _button_2 = new Button(group, SWT.CHECK);
    _button_2.setSelection(true);
    _button_2.setText("Summary"); //$NON-NLS-1$

    _button_3 = new Button(group, SWT.CHECK);
    _button_3.setSelection(true);
    _button_3.setText("Description"); //$NON-NLS-1$
    new Label(group, SWT.NONE);
    new Label(group, SWT.NONE);
    new Label(group, SWT.NONE);

    Group grpScope = new Group(_composite, SWT.NONE);
    GridData gd_grpScope = new GridData(GridData.FILL, SWT.CENTER, true, false, 1, 1);
    gd_grpScope.heightHint = 30;
    gd_grpScope.widthHint = 215;
    grpScope.setLayoutData(gd_grpScope);
    grpScope.setLayout(new GridLayout(2, false));
    grpScope.setText("Scope"); //$NON-NLS-1$

    _btnAllModel = new Button(grpScope, SWT.RADIO);

    _btnAllModel.setText("Whole model"); //$NON-NLS-1$

    _btnSelectedElements = new Button(grpScope, SWT.RADIO);
    _btnSelectedElements.setSelection(true);
    _btnSelectedElements.setText("Selected elements"); //$NON-NLS-1$
    new Label(grpScope, SWT.NONE);
    new Label(grpScope, SWT.NONE);

    impact_group = new Group(this, SWT.NONE);
    GridLayout gl_impact_group = new GridLayout(9, true);
    impact_group.setLayout(gl_impact_group);
    gd_impact_group = new GridData(GridData.FILL, SWT.CENTER, true, false, 1, 1);
    gd_impact_group.heightHint = 30;
    gd_impact_group.widthHint = 450;
    impact_group.setLayoutData(gd_impact_group);
    impact_group.setText("Impacts"); //$NON-NLS-1$

    Label lblNewLabel_1 = new Label(impact_group, SWT.NONE);
    GridData gd_lblNewLabel_1 = new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1);
    gd_lblNewLabel_1.widthHint = 35;
    lblNewLabel_1.setLayoutData(gd_lblNewLabel_1);
    lblNewLabel_1.setText("Name:"); //$NON-NLS-1$

    nbImpactedNames = new Label(impact_group, SWT.NONE);
    GridData gd_nbImpactedNames = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
    gd_nbImpactedNames.widthHint = 55;
    nbImpactedNames.setLayoutData(gd_nbImpactedNames);
    nbImpactedNames.setText("0"); //$NON-NLS-1$

    Label lblNewLabel = new Label(impact_group, SWT.NONE);
    lblNewLabel.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
    lblNewLabel.setText("Summary:"); //$NON-NLS-1$

    nbImpactedSummaries = new Label(impact_group, SWT.NONE);
    GridData gd_nbImpactedSummaries = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
    gd_nbImpactedSummaries.widthHint = 57;
    nbImpactedSummaries.setLayoutData(gd_nbImpactedSummaries);
    nbImpactedSummaries.setText("0"); //$NON-NLS-1$

    Label lblDescription = new Label(impact_group, SWT.NONE);
    lblDescription.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
    lblDescription.setText("Description:"); //$NON-NLS-1$

    nbImpactedDescs = new Label(impact_group, SWT.NONE);
    GridData gd_nbImpactedDescs = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
    gd_nbImpactedDescs.widthHint = 57;
    nbImpactedDescs.setLayoutData(gd_nbImpactedDescs);
    nbImpactedDescs.setText("0"); //$NON-NLS-1$
    new Label(impact_group, SWT.NONE);
    new Label(impact_group, SWT.NONE);
    new Label(impact_group, SWT.NONE);

  }

  protected Combo getFindCombo() {
    return _combo;
  }

  protected Combo getReplaceCombo() {
    return _combo_1;
  }

  public boolean isCaseSensitiveChecked() {
    return _button.getSelection();
  }

  public boolean isWildCardsChecked() {
    return _btnCheckButton.getSelection();
  }

  public boolean isNameChecked() {
    return _button_1.getSelection();
  }

  public boolean isSummaryChecked() {
    return _button_2.getSelection();
  }

  public boolean isDescriptionChecked() {
    return _button_3.getSelection();
  }

  public Label getImpactedElementsLabel() {
    return nbImpactedNames;
  }

  public Button getWholeModelRadioButton() {
    return _btnAllModel;
  }

  public boolean isWholeModelChecked() {
    return _btnAllModel.getSelection();
  }

  public boolean isUpdateHyperlinks() {
    return _btnCheckButton_1.getSelection();
  }

  public Button getSelectedElementsRadioBtn() {
    return _btnSelectedElements;
  }

  public Label getNbImpactedSummaries() {
    return nbImpactedSummaries;
  }

  public Label getNbImpactedDescs() {
    return nbImpactedDescs;
  }

  public boolean isWholeExpression() {
    return _button_4.getSelection();
  }
}
