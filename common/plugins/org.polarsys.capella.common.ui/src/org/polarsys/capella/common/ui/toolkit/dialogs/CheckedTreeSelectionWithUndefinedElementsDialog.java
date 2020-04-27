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

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.viewers.CheckStateChangedEvent;
import org.eclipse.jface.viewers.ICheckStateListener;
import org.eclipse.jface.viewers.ICheckStateProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.dialogs.CheckedTreeSelectionDialog;

/**
 * Dialog with a TreeViewer for elements selection. getCheckedElements and getUncheckedElements only returns user modifications. Undefined elements are those
 * that no effect will be performed after user selection unless they don't explicitely check or uncheck it. Use it as CheckedTreeSelectionDialog and then set
 * the undefinedElements
 */
public class CheckedTreeSelectionWithUndefinedElementsDialog extends CheckedTreeSelectionDialog {

  private ITreeContentProvider contentProvider;

  private Object input;

  private List<Object> checkedElements = null;

  private List<Object> uncheckedElements = null;

  private List<Object> initialCheckedElements = null;

  private List<Object> undefinedElements = null;

  public List<Object> getCheckedElements() {
    return checkedElements;
  }

  public List<Object> getUnCheckedElements() {
    return uncheckedElements;
  }

  public void setUndefinedElements(List<Object> undefinedElements) {
    this.undefinedElements = undefinedElements;
  }

  public List<Object> getUndefinedElements() {
    return undefinedElements;
  }

  /**
   * CheckedTreeSelectionWithUndefinedElementsDialog constructor Then use setInput and setUndefinedElements
   * @param parent
   * @param labelProvider
   * @param contentProvider
   */
  public CheckedTreeSelectionWithUndefinedElementsDialog(Shell parent, ILabelProvider labelProvider, ITreeContentProvider contentProvider) {
    super(parent, labelProvider, contentProvider);
    this.contentProvider = contentProvider;
    checkedElements = new ArrayList<Object>();
    uncheckedElements = new ArrayList<Object>();
    initialCheckedElements = new ArrayList<Object>();
    undefinedElements = new ArrayList<Object>();
  }

  /**
   * Store the input for being used in the listener
   */
  @Override
  public void setInput(Object input) {
    super.setInput(input);
    this.input = input;
  }

  /**
   * Create contents, initialize states and add listeners
   */
  @SuppressWarnings("unchecked")
  @Override
  protected Control createContents(Composite parent) {

    // Create contents
    Control result = super.createContents(parent);

    // Initial states
    initialCheckedElements = getInitialElementSelections();
    getTreeViewer().setCheckStateProvider(new ICheckStateProvider() {
      public boolean isGrayed(Object element) {
        return undefinedElements.contains(element);
      }

      public boolean isChecked(Object element) {
        return initialCheckedElements.contains(element);
      }
    });

    // User modifications
    getTreeViewer().addCheckStateListener(new ICheckStateListener() {
      public void checkStateChanged(CheckStateChangedEvent event) {
        if (getTreeViewer().getGrayed(event.getElement())) {
          getTreeViewer().setGrayed(event.getElement(), false);
        }
        if (event.getChecked()) {
          checkedElements.add(event.getElement());
          uncheckedElements.remove(event.getElement());
        } else {
          checkedElements.remove(event.getElement());
          uncheckedElements.add(event.getElement());
        }
      }
    });

    return result;
  }

  /**
   * CheckedTreeSelectionDialog implementation with modifications to update the states and lists. Adds the selection and deselection buttons to the dialog.
   * @param composite the parent composite
   * @return Composite the composite the buttons were created in.
   */
  @Override
  protected Composite createSelectionButtons(Composite composite) {
    Composite buttonComposite = new Composite(composite, SWT.RIGHT);
    GridLayout layout = new GridLayout();
    layout.numColumns = 0;
    layout.marginWidth = 0;
    layout.horizontalSpacing = convertHorizontalDLUsToPixels(IDialogConstants.HORIZONTAL_SPACING);
    buttonComposite.setLayout(layout);
    buttonComposite.setFont(composite.getFont());
    GridData data = new GridData(GridData.HORIZONTAL_ALIGN_END | GridData.GRAB_HORIZONTAL);
    data.grabExcessHorizontalSpace = true;
    buttonComposite.setLayoutData(data);
    Button selectButton = createButton(buttonComposite, IDialogConstants.SELECT_ALL_ID, Messages.selectAll, false);
    SelectionListener listener = new SelectionAdapter() {
      @Override
      public void widgetSelected(SelectionEvent e) {
        Object[] viewerElements = contentProvider.getElements(input);
        for (Object viewerElement : viewerElements) {
          getTreeViewer().setSubtreeChecked(viewerElement, true);
          checkedElements.add(viewerElement);
          uncheckedElements.remove(viewerElement);
          getTreeViewer().setGrayed(viewerElement, false);
        }
        updateOKStatus();
      }
    };
    selectButton.addSelectionListener(listener);
    Button deselectButton = createButton(buttonComposite, IDialogConstants.DESELECT_ALL_ID, Messages.deselectAll, false);
    listener = new SelectionAdapter() {
      @Override
      public void widgetSelected(SelectionEvent e) {
        getTreeViewer().setCheckedElements(new Object[0]);
        Object[] viewerElements = contentProvider.getElements(input);
        for (Object viewerElement : viewerElements) {
          getTreeViewer().setSubtreeChecked(viewerElement, false);
          checkedElements.remove(viewerElement);
          uncheckedElements.add(viewerElement);
          getTreeViewer().setGrayed(viewerElement, false);
        }
        updateOKStatus();
      }
    };
    deselectButton.addSelectionListener(listener);
    return buttonComposite;
  }

}
