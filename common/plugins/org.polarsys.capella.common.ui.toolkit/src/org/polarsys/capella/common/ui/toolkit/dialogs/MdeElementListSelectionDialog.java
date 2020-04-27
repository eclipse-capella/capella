/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
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

package org.polarsys.capella.common.ui.toolkit.dialogs;

import java.util.Arrays;

import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.dialogs.SelectionStatusDialog;

import org.polarsys.capella.common.ui.toolkit.widgets.Messages;

/**
 */
public class MdeElementListSelectionDialog extends AbstractMdeElementListSelectionDialog {
  private Text _statusBar;
  private Object[] _elements;
  private ILabelProvider _statusRenderer = null;

  /**
   * Constructs a list selection dialog.
   * @param parent The parent for the list.
   * @param renderer {@link ILabelProvider} for the list
   * @param statusRenderer {@link ILabelProvider} for the status
   */
  public MdeElementListSelectionDialog(Shell parent, ILabelProvider renderer, ILabelProvider statusRenderer) {
    super(parent, renderer);
    
    _statusRenderer = statusRenderer;
  }

  /**
   * Sets the elements of the list.
   * @param elements the elements of the list.
   */
  public void setElements(Object[] elements) {
    _elements = elements;
  }

  /**
   * @see SelectionStatusDialog#computeResult()
   */
  @Override
  protected void computeResult() {
    setResult(Arrays.asList(getSelectedElements()));
  }

  /**
   * @see org.eclipse.ui.dialogs.AbstractElementListSelectionDialog#createMessageArea(org.eclipse.swt.widgets.Composite)
   */
  @Override
  protected Label createMessageArea(Composite composite) {
    Label lbl = super.createMessageArea(composite);
    lbl.setText(Messages.getString("FilteredTree_FilterMessageTitle")); //$NON-NLS-1$
    return lbl;
  }

  /**
   * @see org.eclipse.ui.dialogs.AbstractElementListSelectionDialog#createFilterText(org.eclipse.swt.widgets.Composite)
   */
  @Override
  protected Text createFilterText(Composite parent) {
    Label regExpLabel = new Label(parent, SWT.NONE);
    GridData gdData = new GridData(GridData.FILL_HORIZONTAL);
    regExpLabel.setText(Messages.getString("FilteredTree_FilterMessageText")); //$NON-NLS-1$
    regExpLabel.setLayoutData(gdData);

    return super.createFilterText(parent);
  }

  /**
   * @see org.eclipse.ui.dialogs.ElementListSelectionDialog#createDialogArea(org.eclipse.swt.widgets.Composite)
   */
  @Override
  protected Control createDialogArea(Composite parent) {
    // Calls the super method.
    Composite contents = (Composite) super.createDialogArea(parent);

    createMessageArea(contents);
    createFilterText(contents);
    createFilteredList(contents);
    
    setListElements(_elements);

    setSelection(getInitialElementSelections().toArray());
    _statusBar = new Text(contents, SWT.BORDER | SWT.READ_ONLY);
    GridData data = new GridData();
    data.grabExcessVerticalSpace = false;
    data.grabExcessHorizontalSpace = true;
    data.horizontalAlignment = GridData.FILL;
    data.verticalAlignment = GridData.BEGINNING;
    _statusBar.setLayoutData(data);

    SelectionListener listener = new SelectionAdapter() {
      /**
       * @see org.eclipse.swt.events.SelectionListener#widgetSelected(org.eclipse.swt.events.SelectionEvent)
       */
      @Override
      public void widgetSelected(SelectionEvent event) {
        updateStatusBar(event);
      }
    };

    _filteredList.addSelectionListener(listener);
    return contents;
  }

  /**
   * Updates the status bar.
   * @param event The selection event.
   */
  protected void updateStatusBar(SelectionEvent event) {
    Object[] objectSet = _filteredList.getSelection();
    if (objectSet.length > 0) {
      Object object = _filteredList.getSelection()[0];
      String text = _statusRenderer.getText(object);
      _statusBar.setText(text);
    }
    else {
      _statusBar.setText(""); //$NON-NLS-1$
    }
  }
}
