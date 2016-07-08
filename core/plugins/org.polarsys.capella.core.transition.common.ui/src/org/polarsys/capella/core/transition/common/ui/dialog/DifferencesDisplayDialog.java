/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.transition.common.ui.dialog;

import java.util.HashMap;
import java.util.List;

import org.eclipse.emf.diffmerge.api.diff.IDifference;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;
import org.polarsys.capella.core.transition.common.handlers.log.IDiffModelViewer;
import org.polarsys.capella.core.transition.common.ui.utils.CSVExporter;
import org.polarsys.capella.core.transition.common.ui.viewer.DiffTreeViewer;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

public class DifferencesDisplayDialog extends Dialog {

  private DiffTreeViewer diffTreeViewer;

  private Composite compositeResult;

  private List<IDiffModelViewer> listDiff;
  private HashMap<IDifference, IDiffModelViewer> listMerge;

  private static final int SHELL_HEIGHT = 400;

  private static final int SHELL_WIDTH = 920;

  private static final int BUTTON_HEIGHT = 30;

  private static final int BUTTON_WIDTH = 100;

  IContext context;

  /**
    */
  public DifferencesDisplayDialog(IContext context, HashMap<IDifference, IDiffModelViewer> listMerge,
      List<IDiffModelViewer> listDiff) {
    super(PlatformUI.getWorkbench().getDisplay().getActiveShell());
    setShellStyle(getShellStyle() | SWT.RESIZE);
    this.context = context;
    this.listDiff = listDiff;
    this.listMerge = listMerge;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.jface.window.Window#configureShell(org.eclipse.swt.widgets.Shell)
   */
  @Override
  protected void configureShell(Shell newShell) {
    newShell.setText(Messages.DifferencesTable_Title);
    newShell.setSize(SHELL_WIDTH, SHELL_HEIGHT);
    super.configureShell(newShell);
  }

  protected DiffTreeViewer createDiffTreeViewer(IContext context, Composite parent,
      HashMap<IDifference, IDiffModelViewer> listMerge, List<IDiffModelViewer> listDiff) {
    return new DiffTreeViewer(context, parent, listMerge, listDiff);
  }

  /**
   * Create contents of the application window.
   * 
   * @param parent
   */
  @Override
  protected Control createContents(Composite parent) {
    compositeResult = new Composite(parent, SWT.None);
    GridData gd = new GridData(GridData.FILL_BOTH);
    compositeResult.setLayoutData(gd);
    compositeResult.setLayout(new GridLayout(1, false));

    diffTreeViewer = createDiffTreeViewer(context, compositeResult, listMerge, listDiff);

    createCompositeButtons(compositeResult);

    return compositeResult;
  }

  /**
   * @param parent
   */
  private void createCompositeButtons(Composite parent) {
    // Composite buttons
    Composite compButtons = new Composite(parent, SWT.NONE);

    Button saveButton = new Button(compButtons, SWT.PUSH);
    saveButton.setText(Messages.DifferencesTable_Export);
    saveButton.addSelectionListener(new SelectionListener() {
      public void widgetDefaultSelected(SelectionEvent e) {
      }

      public void widgetSelected(SelectionEvent e) {
        if (listDiff != null) {
          new CSVExporter(PlatformUI.getWorkbench().getDisplay().getActiveShell(), diffTreeViewer.getViewerResult());
        }
      }

    });

    // Create the Apply button and add a handler
    Button applyButton = new Button(compButtons, SWT.PUSH);
    applyButton.setText(Messages.DifferencesTable_Apply);
    applyButton.addSelectionListener(new SelectionAdapter() {
      @Override
      public void widgetSelected(SelectionEvent event) {
        okPressed();
      }
    });
    applyButton.setFocus();

    // Create the Cancel button and add a handler
    Button cancelButton = new Button(compButtons, SWT.PUSH);
    cancelButton.setText(Messages.DifferencesTable_Cancel);
    cancelButton.addSelectionListener(new SelectionAdapter() {
      @Override
      public void widgetSelected(SelectionEvent event) {
        cancelPressed();
      }
    });

    GridData gd = new GridData();
    gd.horizontalAlignment = GridData.FILL;
    gd.grabExcessVerticalSpace = false;

    compButtons.setLayout(new FormLayout());
    compButtons.setLayoutData(gd);

    FormData formData1 = new FormData();
    formData1.height = BUTTON_HEIGHT;
    formData1.width = BUTTON_WIDTH;
    formData1.left = new FormAttachment(0, 10);

    FormData formData2 = new FormData();
    formData2.height = BUTTON_HEIGHT;
    formData2.width = BUTTON_WIDTH;
    formData2.right = new FormAttachment(cancelButton, -10);

    FormData formData3 = new FormData();
    formData3.height = BUTTON_HEIGHT;
    formData3.width = BUTTON_WIDTH;
    formData3.right = new FormAttachment(100, -10);

    saveButton.setLayoutData(formData1);
    applyButton.setLayoutData(formData2);
    cancelButton.setLayoutData(formData3);
  }

}
