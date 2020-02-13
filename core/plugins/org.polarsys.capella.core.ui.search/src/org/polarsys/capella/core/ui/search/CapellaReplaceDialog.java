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
package org.polarsys.capella.core.ui.search;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class CapellaReplaceDialog extends Dialog {

  private Combo inputReplacement;

  private final String title;

  private final String pattern;
  private String replacement = "";

  private List<CapellaSearchSettings> previousSearchSettings = new ArrayList<>();

  public CapellaReplaceDialog(Shell parentShell, String pattern, String title) {
    super(parentShell);
    this.pattern = pattern;
    this.title = title;
  }

  @Override
  protected void configureShell(Shell newShell) {
    super.configureShell(newShell);
    newShell.setText(CapellaSearchConstants.ReplaceDialog_Title);
  }

  @Override
  protected Control createDialogArea(Composite parent) {
    Composite area = (Composite) super.createDialogArea(parent);
    Composite container = new Composite(area, SWT.NONE);
    container.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
    GridLayout layout = new GridLayout(2, false);
    container.setLayout(layout);

    Label titleLabel = new Label(container, SWT.LEAD);
    titleLabel.setText(title);
    titleLabel.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 2, 1));

    createInputPattern(container);
    createInputReplacement(container);

    return area;
  }

  private void createInputPattern(Composite container) {
    Label label = new Label(container, SWT.NONE);
    label.setText(CapellaSearchConstants.ReplaceDialog_Label_Pattern);

    GridData gridData = new GridData();
    gridData.grabExcessHorizontalSpace = true;
    gridData.horizontalAlignment = GridData.FILL;

    Text inputPattern = new Text(container, SWT.BORDER);
    inputPattern.setText(pattern);
    inputPattern.setLayoutData(gridData);
    inputPattern.setEditable(false);
  }

  private void createInputReplacement(Composite container) {
    previousSearchSettings.addAll(CapellaReplaceHistory.getAllSearchSettings());
    Label label = new Label(container, SWT.NONE);
    label.setText(CapellaSearchConstants.ReplaceDialog_Label_Replacement);

    GridData gridData = new GridData();
    gridData.grabExcessHorizontalSpace = true;
    gridData.horizontalAlignment = GridData.FILL;
    inputReplacement = new Combo(container, SWT.BORDER);
    inputReplacement.setLayoutData(gridData);
    if (!previousSearchSettings.isEmpty()) {
      String[] previousSearchPatterns = previousSearchSettings.stream()
          .map(CapellaSearchSettings::getReplaceTextPattern).toArray(String[]::new);
      if (previousSearchPatterns != null) {
        inputReplacement.setItems(previousSearchPatterns);
        inputReplacement.select(0);
      }
    }
    inputReplacement.addSelectionListener(new SelectionAdapter() {
      @Override
      public void widgetSelected(SelectionEvent e) {
        int selectionIndex = inputReplacement.getSelectionIndex();
        if (-1 < selectionIndex && selectionIndex < previousSearchSettings.size()) {
          CapellaSearchSettings previous = previousSearchSettings.get(selectionIndex);
          if (previous != null) {
            inputReplacement.setText(previous.getReplaceTextPattern());
          }
        }
      }
    });
  }

  @Override
  protected boolean isResizable() {
    return true;
  }

  @Override
  protected void okPressed() {
    replacement = inputReplacement.getText();
    super.okPressed();
  }

  public String getReplacement() {
    return replacement;
  }
}