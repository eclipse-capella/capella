/*******************************************************************************
 * Copyright (c) 2006, 2019 THALES GLOBAL SERVICES.
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
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.viewers.AbstractTreeViewer;
import org.eclipse.jface.viewers.IContentProvider;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.ui.internal.keys.model.ModelElement;
import org.polarsys.capella.common.helpers.selection.ILinkSelection;
import org.polarsys.capella.common.ui.toolkit.viewers.IViewerStyle;
import org.polarsys.capella.common.ui.toolkit.viewers.TreeAndListViewer;
import org.polarsys.capella.common.ui.toolkit.viewers.data.AbstractData;
import org.polarsys.capella.common.ui.toolkit.viewers.data.DataContentProvider;
import org.polarsys.capella.common.ui.toolkit.viewers.data.DataLabelProvider;

/**
 * Dialog to select {@link ModelElement} through {@link AbstractData}.<br>
 */
public class SelectElementsDialog extends AbstractViewerDialog {
  
  public static final String SELECT_ELEMENTS_DIALOG = "org.polarsys.capella.common.ui.toolkit.dialogs.selectElements";
  
  /**
   * Undefined context constant.<br>
   * Use this context to enable computation on {@link ILinkSelection} contribution mechanism without an explicit context object.
   */
  public static final Object UNDEFINED_CONTEXT = AbstractData.UNDEFINED_CONTEXT;
  /**
   * Context object.
   */
  private Object _context;
  /**
   * Displayed elements.
   */
  //TODO Why ? extends EObject
  private Collection<? extends Object> _displayedElements;
  /**
   * Label provider used to render tree elements.
   */
  private DataLabelProvider labelProvider;

  /**
   * Label provider used to render tree elements.
   */
  protected IContentProvider _contentProvider;

  
  /**
   * Multi selection flag.
   */
  private boolean multiSelection;

  /**
   * Selected objects as result.
   */
  //TODO Why ? extends EObject
  private List<? extends EObject> _result;

  /**
   * Main viewer.
   */
  private TreeAndListViewer _viewer;

  /**
   * Expand level for the tree viewer.
   */
  private int _treeViewerExpandLevel;

  public SelectElementsDialog(Shell parentShell, String dialogTitle,
      String dialogMessage, Collection<? extends Object> displayedElements) {
    this(parentShell, dialogTitle, dialogMessage, displayedElements, false, null);
  }
  
  public SelectElementsDialog(Shell parentShell, String dialogTitle,
      String dialogMessage, Collection<? extends Object> displayedElements, boolean multiSelection, Object context) {
    this(parentShell, new DataLabelProvider(), dialogTitle, dialogMessage, displayedElements, multiSelection,
         context, AbstractTreeViewer.ALL_LEVELS);
  }

  public SelectElementsDialog(Shell parentShell, String dialogTitle,
      String dialogMessage, Collection<? extends Object> displayedElements, boolean multiSelection, Object context, int treeViewerExpandLevel) {
    this(parentShell, new DataLabelProvider(), dialogTitle, dialogMessage, displayedElements, multiSelection,
         context, treeViewerExpandLevel);
  }

  public SelectElementsDialog(Shell parentShell, DataLabelProvider labelProvider, String dialogTitle, String dialogMessage,
      Collection<? extends Object> displayedElements, boolean multiSelection, Object context, int treeViewerExpandLevel) {
  	this(parentShell, new DataContentProvider(), labelProvider, dialogTitle, dialogMessage, displayedElements, multiSelection, context, treeViewerExpandLevel);
  }

  public SelectElementsDialog(Shell parentShell, IContentProvider contentProvider, DataLabelProvider labelProvider, String dialogTitle, String dialogMessage,
      Collection<? extends Object> displayedElements, boolean multiSelection, Object context, int treeViewerExpandLevel) {
    super(parentShell, dialogTitle, dialogMessage, Messages.SelectElementsDialog_Shell_Title);
    _contentProvider = contentProvider;
    this.labelProvider = labelProvider;
    this.multiSelection = multiSelection;
    _context = context;
    _treeViewerExpandLevel = treeViewerExpandLevel;

    if (null != displayedElements) {
      _displayedElements = displayedElements;
    } else {
      _displayedElements = new ArrayList<>(0);
    }
  }

  /**
   * Handle initial display.<br>
   * Called after {@link #createTreeViewerPart(Composite)}.<br>
   * Default implementation select an element in the tree viewer if displayedElements contains only one element.
   */
  protected void configureInitialDisplay() {
    // If displayable elements contains only one element, select it.
    if (_displayedElements.size() == 1) {
      TreeViewer clientViewer = _viewer.getClientViewer();
      clientViewer.setSelection(new StructuredSelection(_displayedElements));
      clientViewer.getControl().setFocus();
    }
  }

  /**
   * Create tree viewer.
   * @param parent
   */
  protected void createTreeViewer(Composite parent) {
    // Create a TreeAndListViewer.
    _viewer = new TreeAndListViewer(parent, _displayedElements, _context, multiSelection, _contentProvider, labelProvider, getTreeViewerStyle(), _treeViewerExpandLevel) {

      @Override
      public String getContextMenuLocation() {
        return SELECT_ELEMENTS_DIALOG;
      }
      
      /**
       * Overridden to set the viewer in the label provider at creation time.
       * @see org.polarsys.capella.common.ui.toolkit.viewers.TreeAndListViewer#doClientViewer(org.eclipse.swt.widgets.Composite)
       */
      @Override
      protected TreeViewer doClientViewer(Composite prt) {
        TreeViewer clientViewer = super.doClientViewer(prt);
        labelProvider.setViewer(clientViewer);
        return clientViewer;
      }
    };
    // Set its layout data.
    _viewer.getControl().setLayoutData(new GridData(GridData.FILL, GridData.FILL, true, true));
    // Add a selection listener to update widgets according to the selection.
    addSelectionChangedListener();    
    // Add a double click listener to select and close the dialog.
    addDoubleCLickListener();
    // Customize this dialog appearance.
    TreeViewer clientViewer = _viewer.getClientViewer();
    constrainViewer(clientViewer, getViewerHeighthint());
  }
  
  protected void addSelectionChangedListener() {
  	TreeViewer clientViewer = _viewer.getClientViewer();
    clientViewer.addSelectionChangedListener(event -> {
      // Handle the selection itself.
      IStructuredSelection selection = (IStructuredSelection) event.getSelection();
      // Update the buttons state according to the selection.
      updateButtons(selection);
    });  	
  }
  
  protected void addDoubleCLickListener() {
  	TreeViewer clientViewer = _viewer.getClientViewer();
    clientViewer.addDoubleClickListener(event -> {
      ISelection selection = event.getSelection();
      if ((null != selection) && !selection.isEmpty() && (selection instanceof IStructuredSelection)) {
        Object doubleClickedElement = ((StructuredSelection) selection).getFirstElement();
        if (((AbstractData) event.getViewer().getInput()).isValid(doubleClickedElement)) {
          okPressed();
        }
      }
    });  	
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void create() {
    super.create();
    ISelection selection = null;
    if ((getViewer() != null) && (getViewer().getSelection() != null)) {
      selection = getViewer().getSelection();
    }
    updateButtons(selection);
  }

  /**
   * Create the Tree viewer part.<br>
   * Calls {@link #createTreeViewer(Composite)}, {@link #createStatusBar(Composite)} and {@link #createTreeViewerModeButton(Composite)}
   * @param parent
   */
  protected void createTreeViewerPart(Composite parent) {
    // Create the tree Viewer.
    createTreeViewer(parent);
  }

  /**
   * @see org.polarsys.capella.common.ui.toolkit.dialogs.AbstractViewerDialog#doCreateDialogArea(org.eclipse.swt.widgets.Composite)
   */
  @Override
  protected void doCreateDialogArea(Composite parent) {
    // Create Tree Viewer part.
    createTreeViewerPart(parent);
    configureInitialDisplay();
  }

  /**
   * Create a default composite to host page widgets.
   * @param parent
   * @return
   */
  protected Composite createScrolledComposite(Composite parent) {
    FormToolkit formToolkit = new FormToolkit(parent.getDisplay());
    formToolkit.setBackground(parent.getBackground());
    ScrolledForm scrolledComposite = formToolkit.createScrolledForm(parent);
    scrolledComposite.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_BLUE));
    return scrolledComposite.getBody();
  }

  /**
   * Get selected object as a selection result.
   * @return the result
   */
  @Override
  public List<? extends EObject> getResult() {
    // TODO Why ? extends EObject
    return _result;
  }

  /**
   * Tree viewer style.<br>
   * Default implementation set {@link IViewerStyle#SHOW_STATUS_BAR} and {@link IViewerStyle#SHOW_TREE_VIEW_MODE_BUTTON}
   * @return
   */
  protected int getTreeViewerStyle() {
    return IViewerStyle.SHOW_STATUS_BAR | IViewerStyle.SHOW_TREE_VIEW_MODE_BUTTON;
  }

  /**
   * Get the main viewer.
   * @return
   */
  protected TreeAndListViewer getViewer() {
    return _viewer;
  }

  /**
   * Default implementation gets the selected objects in the main viewer.
   */
  @SuppressWarnings("unchecked")
  protected List<? extends EObject> handleResult() {
    // TODO Why ? extends EObject
    return new ArrayList<EObject>(((IStructuredSelection) _viewer.getClientViewer().getSelection()).toList());
  }

  /**
   * Are conditions met to enable the OK button.
   * @param selection
   * @return <code>true</code> means OK button can be enabled.
   */
  protected boolean isOkToClose(ISelection selection) {
    boolean isOkEnabled = false;
    // Precondition.
    if ((null != selection) && !selection.isEmpty()) {
      isOkEnabled = true;
      // Something selected.
      IStructuredSelection sel = (IStructuredSelection) selection;
      // Loop over selected elements to check validity.
      AbstractData input = (AbstractData) _viewer.getClientViewer().getInput();
      for (Object selectedElement : sel.toList()) {
        isOkEnabled &= input.isValid(selectedElement);
        if (!isOkEnabled) {
          break;
        }
      }
    }
    return isOkEnabled;
  }

  /**
   * @see org.eclipse.jface.dialogs.Dialog#okPressed()
   */
  @Override
  protected void okPressed() {
    _result = handleResult();
    super.okPressed();
  }

  /**
   * Update Ok and Cancel buttons.
   * @param selection
   */
  protected void updateButtons(ISelection selection) {
    boolean isOkEnabled = isOkToClose(selection);
    // Update the OK button.
    Button okButton = getButton(IDialogConstants.OK_ID);
    if ((null != okButton) && !okButton.isDisposed()) {
      okButton.setEnabled(isOkEnabled);
    }
  }
  
  public DataLabelProvider getLabelProvider() {
    return labelProvider;
  }

  public boolean isMultiSelection() {
    return multiSelection;
  }

  public void setMultiSelection(boolean multiSelection) {
    this.multiSelection = multiSelection;
  }
}
