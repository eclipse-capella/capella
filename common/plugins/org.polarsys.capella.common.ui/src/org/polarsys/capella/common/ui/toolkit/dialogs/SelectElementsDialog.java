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
package org.polarsys.capella.common.ui.toolkit.dialogs;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.viewers.AbstractTreeViewer;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IContentProvider;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
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
import org.polarsys.capella.common.ui.toolkit.viewers.IViewerStyle;
import org.polarsys.capella.common.ui.toolkit.viewers.TreeAndListViewer;
import org.polarsys.capella.common.ui.toolkit.viewers.data.AbstractData;
import org.polarsys.capella.common.ui.toolkit.viewers.data.DataContentProvider;
import org.polarsys.capella.common.ui.toolkit.viewers.data.DataLabelProvider;

/**
 * Dialog to select {@link ModelElement} through {@link AbstractData}.<br>
 */
public class SelectElementsDialog extends AbstractViewerDialog {
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
  private Collection<? extends Object> _displayedElements;
  /**
   * Label provider used to render tree elements.
   */
  private DataLabelProvider _labelProvider;

  /**
   * Label provider used to render tree elements.
   */
  protected IContentProvider _contentProvider;

  
  /**
   * Multi selection flag.
   */
  private boolean _multiSelection;

  /**
   * Selected objects as result.
   */
  private List<? extends EObject> _result;

  /**
   * Main viewer.
   */
  private TreeAndListViewer _viewer;

  /**
   * Expand level for the tree viewer.
   */
  private int _treeViewerExpandLevel;

  /**
   * Constructor.<br>
   * Instantiate a dialog with no context and mono selection capable.
   * @param parentShell_p
   * @param editingDomain_p
   * @param adapterFactory_p
   * @param dialogTitle_p
   * @param dialogMessage_p
   * @param displayedElements_p
   */
  // 1
  public SelectElementsDialog(Shell parentShell_p, TransactionalEditingDomain editingDomain_p, AdapterFactory adapterFactory_p, String dialogTitle_p,
      String dialogMessage_p, Collection<? extends Object> displayedElements_p) {
    this(parentShell_p, editingDomain_p, adapterFactory_p, dialogTitle_p, dialogMessage_p, displayedElements_p, false, null);
  }
  
  /**
   * Constructor.<br>
   * @param parentShell_p
   * @param editingDomain_p
   * @param adapterFactory_p
   * @param dialogTitle_p
   * @param dialogMessage_p
   * @param displayedElements_p
   * @param multiSelection_p whether or not this dialog is multi selection capable.
   * @param context_p optional parameter, to set an undefined context to {@link ILinkSelection} contribution, set {@link #UNDEFINED_CONTEXT}.
   */
  public SelectElementsDialog(Shell parentShell_p, TransactionalEditingDomain editingDomain_p, AdapterFactory adapterFactory_p, String dialogTitle_p,
      String dialogMessage_p, Collection<? extends Object> displayedElements_p, boolean multiSelection_p, Object context_p) {
    this(parentShell_p, new DataLabelProvider(editingDomain_p, adapterFactory_p), dialogTitle_p, dialogMessage_p, displayedElements_p, multiSelection_p,
         context_p, AbstractTreeViewer.ALL_LEVELS);
  }

  /**
   * Constructor.<br>
   * @param parentShell_p
   * @param editingDomain_p
   * @param adapterFactory_p
   * @param dialogTitle_p
   * @param dialogMessage_p
   * @param displayedElements_p
   * @param multiSelection_p whether or not this dialog is multi selection capable.
   * @param context_p optional parameter, to set an undefined context to {@link ILinkSelection} contribution, set {@link #UNDEFINED_CONTEXT}.
   * @param treeViewerExpandLevel_p
   */
  public SelectElementsDialog(Shell parentShell_p, TransactionalEditingDomain editingDomain_p, AdapterFactory adapterFactory_p, String dialogTitle_p,
      String dialogMessage_p, Collection<? extends Object> displayedElements_p, boolean multiSelection_p, Object context_p, int treeViewerExpandLevel_p) {
    this(parentShell_p, new DataLabelProvider(editingDomain_p, adapterFactory_p), dialogTitle_p, dialogMessage_p, displayedElements_p, multiSelection_p,
         context_p, treeViewerExpandLevel_p);
  }

  /**
   * Constructor.<br>
   * @param parentShell_p
   * @param labelProvider_p
   * @param dialogTitle_p
   * @param dialogMessage_p
   * @param displayedElements_p
   * @param multiSelection_p whether or not this dialog is multi selection capable.
   * @param context_p optional parameter, to set an undefined context to {@link ILinkSelection} contribution, set {@link #UNDEFINED_CONTEXT}.
   * @param
   */
  public SelectElementsDialog(Shell parentShell_p, DataLabelProvider labelProvider_p, String dialogTitle_p, String dialogMessage_p,
      Collection<? extends Object> displayedElements_p, boolean multiSelection_p, Object context_p, int treeViewerExpandLevel_p) {
  	this(parentShell_p, new DataContentProvider(), labelProvider_p, dialogTitle_p, dialogMessage_p, displayedElements_p, multiSelection_p, context_p, treeViewerExpandLevel_p);
  }

  /**
   * Constructor.<br>
   * @param parentShell_p
   * @param coontentProvider_p
   * @param labelProvider_p
   * @param dialogTitle_p
   * @param dialogMessage_p
   * @param displayedElements_p
   * @param multiSelection_p whether or not this dialog is multi selection capable.
   * @param context_p optional parameter, to set an undefined context to {@link ILinkSelection} contribution, set {@link #UNDEFINED_CONTEXT}.
   * @param
   */
  public SelectElementsDialog(Shell parentShell_p, IContentProvider contentProvider_p, DataLabelProvider labelProvider_p, String dialogTitle_p, String dialogMessage_p,
      Collection<? extends Object> displayedElements_p, boolean multiSelection_p, Object context_p, int treeViewerExpandLevel_p) {
    super(parentShell_p, dialogTitle_p, dialogMessage_p, Messages.SelectElementsDialog_Shell_Title);
    _contentProvider = contentProvider_p;
    _labelProvider = labelProvider_p;
    _multiSelection = multiSelection_p;
    _context = context_p;
    _treeViewerExpandLevel = treeViewerExpandLevel_p;

    if (null != displayedElements_p) {
      _displayedElements = displayedElements_p;
    } else {
      _displayedElements = new ArrayList<Object>(0);
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
   * @param parent_p
   */
  protected void createTreeViewer(Composite parent_p) {
    // Create a TreeAndListViewer.
    _viewer = new TreeAndListViewer(parent_p, _displayedElements, _context, _multiSelection, _contentProvider, _labelProvider, getTreeViewerStyle(), _treeViewerExpandLevel) {
      /**
       * Overridden to set the viewer in the label provider at creation time.
       * @see org.polarsys.capella.common.ui.toolkit.viewers.TreeAndListViewer#doClientViewer(org.eclipse.swt.widgets.Composite)
       */
      @SuppressWarnings("synthetic-access")
      @Override
      protected TreeViewer doClientViewer(Composite parent__p) {
        TreeViewer clientViewer = super.doClientViewer(parent__p);
        _labelProvider.setViewer(clientViewer);
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
    ISelectionChangedListener viewerSelectionChangedListener = new ISelectionChangedListener() {
      public void selectionChanged(SelectionChangedEvent event_p) {
        // Handle the selection itself.
        IStructuredSelection selection = (IStructuredSelection) event_p.getSelection();
        // Update the buttons state according to the selection.
        updateButtons(selection);
      }
    };
    clientViewer.addSelectionChangedListener(viewerSelectionChangedListener);  	
  }
  
  protected void addDoubleCLickListener() {
  	TreeViewer clientViewer = _viewer.getClientViewer();
  	IDoubleClickListener viewerDoubleClickListener = new IDoubleClickListener() {
      public void doubleClick(DoubleClickEvent event_p) {
        ISelection selection = event_p.getSelection();
        if ((null != selection) && !selection.isEmpty() && (selection instanceof IStructuredSelection)) {
          Object doubleClickedElement = ((StructuredSelection) selection).getFirstElement();
          if (((AbstractData) event_p.getViewer().getInput()).isValid(doubleClickedElement)) {
            okPressed();
          }
        }
      }
    };
    clientViewer.addDoubleClickListener(viewerDoubleClickListener);  	
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
   * @param parent_p
   */
  protected void createTreeViewerPart(Composite parent_p) {
    // Create the tree Viewer.
    createTreeViewer(parent_p);
  }

  /**
   * @see org.polarsys.capella.common.ui.toolkit.dialogs.AbstractViewerDialog#doCreateDialogArea(org.eclipse.swt.widgets.Composite)
   */
  @Override
  protected void doCreateDialogArea(Composite parent_p) {
    // Create Tree Viewer part.
    createTreeViewerPart(parent_p);
    configureInitialDisplay();
  }

  /**
   * Create a default composite to host page widgets.
   * @param parent_p
   * @param numColumns_p
   * @return
   */
  protected Composite createScrolledComposite(Composite parent_p) {
    FormToolkit formToolkit = new FormToolkit(parent_p.getDisplay());
    formToolkit.setBackground(parent_p.getBackground());
    ScrolledForm scrolledComposite = formToolkit.createScrolledForm(parent_p);
    scrolledComposite.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_BLUE));
    return scrolledComposite.getBody();
  }

  /**
   * Get selected object as a selection result.
   * @return the result
   */
  @Override
  public List<? extends EObject> getResult() {
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
    return new ArrayList<EObject>(((IStructuredSelection) _viewer.getClientViewer().getSelection()).toList());
  }

  /**
   * Are conditions met to enable the OK button.
   * @param selection_p
   * @return <code>true</code> means OK button can be enabled.
   */
  protected boolean isOkToClose(ISelection selection_p) {
    boolean isOkEnabled = false;
    // Precondition.
    if ((null != selection_p) && !selection_p.isEmpty()) {
      isOkEnabled = true;
      // Something selected.
      IStructuredSelection selection = (IStructuredSelection) selection_p;
      // Loop over selected elements to check validity.
      AbstractData input = (AbstractData) _viewer.getClientViewer().getInput();
      for (Object selectedElement : selection.toList()) {
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
   * @param selection_p
   */
  protected void updateButtons(ISelection selection_p) {
    boolean isOkEnabled = isOkToClose(selection_p);
    // Update the OK button.
    Button okButton = getButton(IDialogConstants.OK_ID);
    if ((null != okButton) && !okButton.isDisposed()) {
      okButton.setEnabled(isOkEnabled);
    }
  }
}
