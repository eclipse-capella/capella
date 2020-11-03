/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
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

import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.BusyIndicator;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.dialogs.ISelectionStatusValidator;
import org.eclipse.ui.dialogs.SelectionStatusDialog;

import org.polarsys.capella.common.ui.toolkit.widgets.MdeFilteredList;

/**
 * An abstract class to select elements out of a list of elements.
 * @since 2.0
 */
public abstract class AbstractMdeElementListSelectionDialog extends SelectionStatusDialog {

  private ILabelProvider _renderer;
  private boolean _ignoreCase = true;
  private boolean _isMultipleSelection = false;
  private boolean _matchEmptyString = true;
  private boolean _allowDuplicates = true;
  private Label _message;
  protected MdeFilteredList _filteredList;
  private Text _filterText;
  private ISelectionStatusValidator _validator;
  private String _filter = null;
  private String _emptyListMessage = ""; //$NON-NLS-1$
  private String _emptySelectionMessage = ""; //$NON-NLS-1$
  private int _width = 60;
  private int _height = 18;
  private Object[] _selection = new Object[0];

  /**
   * Constructs a list selection dialog.
   * @param parent The parent for the list.
   * @param renderer ILabelProvider for the list
   */
  protected AbstractMdeElementListSelectionDialog(Shell parent, ILabelProvider renderer) {
    super(parent);
    _renderer = renderer;

    int shellStyle = getShellStyle();
    setShellStyle(shellStyle | SWT.MAX | SWT.RESIZE);
  }

  /**
   * Handles default selection (double click). By default, the OK button is pressed.
   */
  protected void handleDefaultSelected() {
    if (validateCurrentSelection()) {
      buttonPressed(IDialogConstants.OK_ID);
    }
  }

  /**
   * Specifies if sorting, filtering and folding is case sensitive.
   * @param ignoreCase
   */
  public void setIgnoreCase(boolean ignoreCase) {
    _ignoreCase = ignoreCase;
  }

  /**
   * Returns if sorting, filtering and folding is case sensitive.
   * @return boolean
   */
  public boolean isCaseIgnored() {
    return _ignoreCase;
  }

  /**
   * Specifies whether everything or nothing should be filtered on empty filter string.
   * @param matchEmptyString boolean
   */
  public void setMatchEmptyString(boolean matchEmptyString) {
    _matchEmptyString = matchEmptyString;
  }

  /**
   * Specifies if multiple selection is allowed.
   * @param multipleSelection
   */
  public void setMultipleSelection(boolean multipleSelection) {
    _isMultipleSelection = multipleSelection;
  }

  /**
   * Specifies whether duplicate entries are displayed or not.
   * @param allowDuplicates
   */
  public void setAllowDuplicates(boolean allowDuplicates) {
    _allowDuplicates = allowDuplicates;
  }

  /**
   * Sets the list size in unit of characters.
   * @param width the width of the list.
   * @param height the height of the list.
   */
  public void setSize(int width, int height) {
    _width = width;
    _height = height;
  }

  /**
   * Sets the message to be displayed if the list is empty.
   * @param message the message to be displayed.
   */
  public void setEmptyListMessage(String message) {
    _emptyListMessage = message;
  }

  /**
   * Sets the message to be displayed if the selection is empty.
   * @param message the message to be displayed.
   */
  public void setEmptySelectionMessage(String message) {
    _emptySelectionMessage = message;
  }

  /**
   * Sets an optional validator to check if the selection is valid. The validator is invoked whenever the selection changes.
   * @param validator the validator to validate the selection.
   */
  public void setValidator(ISelectionStatusValidator validator) {
    _validator = validator;
  }

  /**
   * Sets the elements of the list (widget). To be called within open().
   * @param elements the elements of the list.
   */
  protected void setListElements(Object[] elements) {
    Assert.isNotNull(_filteredList);
    _filteredList.setElements(elements);
  }

  /**
   * Sets the filter pattern.
   * @param filter the filter pattern.
   */
  public void setFilter(String filter) {
    if (_filterText == null) {
      _filter = filter;
    } else {
      _filterText.setText(filter);
    }
  }

  /**
   * Returns the current filter pattern.
   * @return returns the current filter pattern or <code>null<code> if filter was not set.
   */
  public String getFilter() {
    if (_filteredList == null) {
      return _filter;
    }
    return _filteredList.getFilter();
  }

  /**
   * Returns the indices referring the current selection. To be called within open().
   * @return returns the indices of the current selection.
   */
  protected int[] getSelectionIndices() {
    Assert.isNotNull(_filteredList);
    return _filteredList.getSelectionIndices();
  }

  /**
   * Returns an index referring the first current selection. To be called within open().
   * @return returns the indices of the current selection.
   */
  protected int getSelectionIndex() {
    Assert.isNotNull(_filteredList);
    return _filteredList.getSelectionIndex();
  }

  /**
   * Sets the selection referenced by an array of elements. Empty or null array removes selection. To be called within open().
   * @param selection the indices of the selection.
   */
  protected void setSelection(Object[] selection) {
    Assert.isNotNull(_filteredList);
    _filteredList.setSelection(selection);
  }

  /**
   * Returns an array of the currently selected elements. To be called within or after open().
   * @return returns an array of the currently selected elements.
   */
  protected Object[] getSelectedElements() {
    Assert.isNotNull(_filteredList);
    return _filteredList.getSelection();
  }

  /**
   * Returns all elements which are folded together to one entry in the list.
   * @param index the index selecting the entry in the list.
   * @return returns an array of elements folded together.
   */
  public Object[] getFoldedElements(int index) {
    Assert.isNotNull(_filteredList);
    return _filteredList.getFoldedElements(index);
  }

  /**
   * Creates the message text widget and sets layout data.
   * @param composite the parent composite of the message area.
   */
  @Override
  protected Label createMessageArea(Composite composite) {
    Label label = super.createMessageArea(composite);

    GridData data = new GridData();
    data.grabExcessVerticalSpace = false;
    data.grabExcessHorizontalSpace = true;
    data.horizontalAlignment = GridData.FILL;
    data.verticalAlignment = GridData.BEGINNING;
    label.setLayoutData(data);

    _message = label;

    return label;
  }

  /**
   * Handles a selection changed event. By default, the current selection is validated.
   */
  protected void handleSelectionChanged() {
    validateCurrentSelection();
  }

  /**
   * Validates the current selection and updates the status line accordingly.
   * @return boolean <code>true</code> if the current selection is valid.
   */
  protected boolean validateCurrentSelection() {
    Assert.isNotNull(_filteredList);

    IStatus status;
    Object[] elements = getSelectedElements();

    if (elements.length > 0) {
      if (_validator != null) {
        status = _validator.validate(elements);
      } else {
        status = new Status(IStatus.OK, PlatformUI.PLUGIN_ID, IStatus.OK, "", //$NON-NLS-1$
                            null);
      }
    } else {
      if (_filteredList.isEmpty()) {
        status = new Status(IStatus.ERROR, PlatformUI.PLUGIN_ID, IStatus.ERROR, _emptyListMessage, null);
      } else {
        status = new Status(IStatus.ERROR, PlatformUI.PLUGIN_ID, IStatus.ERROR, _emptySelectionMessage, null);
      }
    }

    updateStatus(status);

    return status.isOK();
  }

  /*
   * @see Dialog#cancelPressed
   */
  @Override
  protected void cancelPressed() {
    setResult(null);
    super.cancelPressed();
  }

  /**
   * Creates a filtered list.
   * @param parent the parent composite.
   * @return returns the filtered list widget.
   */
  protected MdeFilteredList createFilteredList(Composite parent) {
    int flags = SWT.BORDER | SWT.V_SCROLL | SWT.H_SCROLL | (_isMultipleSelection ? SWT.MULTI : SWT.SINGLE);

    MdeFilteredList list = new MdeFilteredList(parent, flags, _renderer, _ignoreCase, _allowDuplicates, _matchEmptyString);

    GridData data = new GridData();
    data.widthHint = convertWidthInCharsToPixels(_width);
    data.heightHint = convertHeightInCharsToPixels(_height);
    data.grabExcessVerticalSpace = true;
    data.grabExcessHorizontalSpace = true;
    data.horizontalAlignment = GridData.FILL;
    data.verticalAlignment = GridData.FILL;
    list.setLayoutData(data);
    list.setFont(parent.getFont());
    list.setFilter((_filter == null ? "" : _filter)); //$NON-NLS-1$   

    list.addSelectionListener(new SelectionListener() {
      public void widgetDefaultSelected(SelectionEvent event) {
        handleDefaultSelected();
      }

      @SuppressWarnings("synthetic-access")
      public void widgetSelected(SelectionEvent event) {
        handleWidgetSelected();
      }
    });

    _filteredList = list;
    return list;
  }

  private void handleWidgetSelected() {
    Object[] newSelection = _filteredList.getSelection();

    if (newSelection.length != _selection.length) {
      _selection = newSelection;
      handleSelectionChanged();
    } else {
      for (int i = 0; i != newSelection.length; i++) {
        if (!newSelection[i].equals(_selection[i])) {
          _selection = newSelection;
          handleSelectionChanged();
          break;
        }
      }
    }
  }

  protected Text createFilterText(Composite parent) {
    Text text = new Text(parent, SWT.BORDER);

    GridData data = new GridData();
    data.grabExcessVerticalSpace = false;
    data.grabExcessHorizontalSpace = true;
    data.horizontalAlignment = GridData.FILL;
    data.verticalAlignment = GridData.BEGINNING;
    text.setLayoutData(data);
    text.setFont(parent.getFont());

    text.setText((_filter == null ? "" : _filter)); //$NON-NLS-1$

    Listener listener = new Listener() {
      @SuppressWarnings("synthetic-access")
      public void handleEvent(Event e) {
        _filteredList.setFilter(_filterText.getText());
      }
    };
    text.addListener(SWT.Modify, listener);

    text.addKeyListener(new KeyListener() {
      public void keyPressed(KeyEvent e) {
        if (e.keyCode == SWT.ARROW_DOWN) {
          _filteredList.setFocus();
        }
      }

      public void keyReleased(KeyEvent event) {
        // do nothing.
      }
    });

    _filterText = text;

    return text;
  }

  /**
   * @see org.eclipse.jface.window.Window#open()
   */
  @Override
  public int open() {
    super.open();
    return getReturnCode();
  }

  private void access$superCreate() {
    super.create();
  }

  /**
   * @see org.eclipse.jface.window.Window#create()
   */
  @Override
  public void create() {

    BusyIndicator.showWhile(null, new Runnable() {
      @SuppressWarnings("synthetic-access")
      public void run() {
        access$superCreate();

        Assert.isNotNull(_filteredList);

        if (_filteredList.isEmpty()) {
          handleEmptyList();
        } else {
          validateCurrentSelection();
          _filterText.selectAll();
          _filterText.setFocus();
        }
      }
    });

  }

  /**
   * Handles empty list by disabling widgets.
   */
  protected void handleEmptyList() {
    _message.setEnabled(false);
    _filterText.setEnabled(false);
    _filteredList.setEnabled(false);
    updateOkState();
  }

  /**
   * Update the enablement of the OK button based on whether or not there is a selection.
   */
  protected void updateOkState() {
    Button okButton = getOkButton();
    if (okButton != null) {
      okButton.setEnabled(getSelectedElements().length != 0);
    }
  }
}
