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
package org.polarsys.capella.common.ui.toolkit.dialogs;

import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.viewers.AbstractTreeViewer;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;

import org.polarsys.capella.common.ui.toolkit.viewers.data.AbstractData;
import org.polarsys.capella.common.ui.toolkit.viewers.data.DataContentProvider;
import org.polarsys.capella.common.ui.toolkit.viewers.data.DataLabelProvider;
import org.polarsys.capella.common.ui.toolkit.viewers.data.ListData;
import org.polarsys.capella.common.ui.toolkit.viewers.data.TreeData;
import org.polarsys.capella.common.ui.toolkit.viewers.transfer.AbstractTransferViewer2;
import org.polarsys.capella.common.ui.toolkit.viewers.transfer.TransferTreeListViewer;
import org.polarsys.capella.common.ui.toolkit.widgets.handler.SelectionChangedHandler;
import org.polarsys.capella.common.mdsofa.common.misc.Couple;

/**
 * Dialog that displays a {@link TransferTreeListViewer}
 */
public class TransferTreeListDialog extends AbstractViewerDialog {
  /**
   * Default TreeViewer style bits constant.
   */
  public final static int DEFAULT_TREE_VIEWER_STYLE = SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER;
  /**
   * Undefined context constant.<br>
   * Use this context to enable computation on {@link ILinkSelection} contribution mechanism without an explicit context object.
   */
  public static final Object UNDEFINED_CONTEXT = AbstractData.UNDEFINED_CONTEXT;
  /**
   * Content provider used by the left viewer.
   */
  private ITreeContentProvider _leftContentProvider;
  /**
   * Left viewer input as an anonymous structure rather than a {@link TreeData}.<br> {@link TreeData} can be switched to {@link ListData} by the end-user when
   * switching the representation mode i.e Tree vs List.<br>
   * Hence, the {@link AbstractData} used as viewer's input must be got from the viewer dynamically.<br>
   * Key is used as the initial list.<br>
   * Value is used as context.
   */
  private Couple<List<? extends EObject>, Object> _leftInput;
  /**
   * Label provider used by the left viewer.
   */
  private DataLabelProvider _leftLabelProvider;
  /**
   * Style bits used for the left viewer.
   */
  private int _leftViewerStyle;
  /**
   * Expand level for the left viewer.
   */
  private int _leftViewerExpandLevel;
  /**
   * Results i.e valid objects transferred to the right viewer.
   */
  private Object[] _results;

  /**
   * Content provider used by the right viewer.
   */
  private ITreeContentProvider _rightContentProvider;

  /**
   * Right viewer input as an anonymous structure rather than a {@link TreeData}.<br> {@link TreeData} can be switched to {@link ListData} by the end-user when
   * switching the representation mode i.e Tree vs List.<br>
   * Hence, the {@link AbstractData} used as viewer's input must be got from the viewer dynamically.<br>
   * Key is used as the initial list.<br>
   * Value is used as context.
   */
  private Couple<List<? extends EObject>, Object> _rightInput;
  /**
   * Label provider used by the right viewer.
   */
  private DataLabelProvider _rightLabelProvider;
  /**
   * Style bits used for the right viewer.
   */
  private int _rightViewerStyle;
  /**
   * Expand level for the right viewer.
   */
  private int _rightViewerExpandLevel;
  /**
   * Transfer viewer displayed in this dialog.
   */
  private TransferTreeListViewer _transferViewer;
  /**
   * Transfer tree style.
   */
  protected final int TRANSFER_TREE_STYLE = AbstractTransferViewer2.SINGLE_SELECTION_VIEWER | AbstractTransferViewer2.ALL_WIDGETS;

  /**
   * Constructor.<br>
   * DEFAULT_TREE_VIEWER_STYLE is used for both left and right viewers.
   * @param parentShell_p
   * @param dialogTitle_p
   * @param dialogMessage_p
   * @param editingDomain_ps
   * @param adapterFactory_p
   */
  public TransferTreeListDialog(Shell parentShell_p, String dialogTitle_p, String dialogMessage_p, TransactionalEditingDomain editingDomain_p, AdapterFactory adapterFactory_p) {
    this(parentShell_p, dialogTitle_p, dialogMessage_p,
      new DataLabelProvider(editingDomain_p, adapterFactory_p),
      new DataLabelProvider(editingDomain_p, adapterFactory_p),
      DEFAULT_TREE_VIEWER_STYLE, DEFAULT_TREE_VIEWER_STYLE,
      AbstractTreeViewer.ALL_LEVELS, AbstractTreeViewer.ALL_LEVELS);
  }

  /**
   * Constructor.
   * @param parentShell_p
   * @param leftLabelProvider_p
   * @param rightLabelProvider_p
   * @param leftViewerStyle_p
   * @param rightViewerStyle_p
   * @param dialogTitle_p
   * @param dialogMessage_p
   * @param leftViewerExpandLevel_p
   * @param rightViewerExpandLevel_p
   */
  protected TransferTreeListDialog(Shell parentShell_p, String dialogTitle_p, String dialogMessage_p,
    DataLabelProvider leftLabelProvider_p, DataLabelProvider rightLabelProvider_p,
    int leftViewerStyle_p, int rightViewerStyle_p, int leftViewerExpandLevel_p, int rightViewerExpandLevel_p)
  {
    super(parentShell_p, dialogTitle_p, dialogMessage_p, Messages.TransferTreeListDialog_Shell_Title);
    // Left viewer.
    _leftLabelProvider = leftLabelProvider_p;
    _leftViewerStyle = leftViewerStyle_p;
    _leftViewerExpandLevel = leftViewerExpandLevel_p;
    // Right viewer.
    _rightLabelProvider = rightLabelProvider_p;
    _rightViewerStyle = rightViewerStyle_p;
    _rightViewerExpandLevel = rightViewerExpandLevel_p;
  }

  /**
   * Constructor.<br>
   * DEFAULT_TREE_VIEWER_STYLE is used for both left and right viewers.
   * @param parentShell_p
   * @param dialogTitle_p
   * @param dialogMessage_p
   * @param leftLabelProvider_p
   * @param rightLabelProvider_p
   */
  public TransferTreeListDialog(Shell parentShell_p, String dialogTitle_p, String dialogMessage_p,
    DataLabelProvider leftLabelProvider_p, DataLabelProvider rightLabelProvider_p)
  {
    this(parentShell_p, dialogTitle_p, dialogMessage_p,
      leftLabelProvider_p, rightLabelProvider_p,
      DEFAULT_TREE_VIEWER_STYLE, DEFAULT_TREE_VIEWER_STYLE,
      AbstractTreeViewer.ALL_LEVELS, AbstractTreeViewer.ALL_LEVELS);
  }

  /**
   * Constructor.<br>
   * DEFAULT_TREE_VIEWER_STYLE is used for both left and right viewers.
   * @param parentShell_p
   * @param dialogTitle_p
   * @param dialogMessage_p
   * @param leftLabelProvider_p
   * @param rightLabelProvider_p
   * @param leftViewerExpandLevel_p
   * @param rightViewerExpandLevel_p
   */
  public TransferTreeListDialog(Shell parentShell_p, String dialogTitle_p, String dialogMessage_p,
    DataLabelProvider leftLabelProvider_p, DataLabelProvider rightLabelProvider_p,
    int leftViewerExpandLevel_p, int rightViewerExpandLevel_p)
  {
    this(parentShell_p, dialogTitle_p, dialogMessage_p,
      leftLabelProvider_p, rightLabelProvider_p,
      DEFAULT_TREE_VIEWER_STYLE, DEFAULT_TREE_VIEWER_STYLE,
      leftViewerExpandLevel_p, rightViewerExpandLevel_p);
  }

  /**
   * Create right viewer data at dialog creation time.<br>
   * Default implementation creates a {@link TreeData}.
   * @param elements
   * @param context_p
   * @return a not <code>null</code> {@link AbstractData} instance.
   */
  protected AbstractData createRightViewerData(List<? extends EObject> elements, Object context_p) {
    return new TreeData(elements, context_p);
  }

  /**
   * Create {@link TransferTreeListViewer}.
   * @param parent_p
   * @return a not <code>null</code> instance.
   */
  protected TransferTreeListViewer createTransferTreeListViewer(Composite parent_p) {
    return new TransferTreeListViewer(parent_p, TRANSFER_TREE_STYLE, _leftViewerStyle, _rightViewerStyle, _leftViewerExpandLevel, _rightViewerExpandLevel);
  }

  /**
   * @see org.polarsys.capella.common.ui.toolkit.dialogs.AbstractViewerDialog#doCreateDialogArea(org.eclipse.swt.widgets.Composite)
   */
  @Override
  protected void doCreateDialogArea(Composite parent_p) {
    // Create the transfer tree list viewer.
    _transferViewer = createTransferTreeListViewer(parent_p);
    // Initialize left viewer.
    initializeLeftViewer(_transferViewer);
    // Initialize right viewer.
    initializeRightViewer(_transferViewer);
    // Get the max width between left and right viewers.
    int widthLeft = _transferViewer.getLeftViewer().getControl().computeSize(SWT.DEFAULT, SWT.DEFAULT).x;
    int widthRight = _transferViewer.getRightViewer().getControl().computeSize(SWT.DEFAULT, SWT.DEFAULT).x;
    int widhtHint = Math.max(widthLeft, widthRight);
    // // Set left & right to get both viewer with the same width even if one is empty.
    constrainViewer(_transferViewer.getLeftViewer(), getViewerHeighthint(), widhtHint);
    constrainViewer(_transferViewer.getRightViewer(), getViewerHeighthint(), widhtHint);
    // Register selection handler.
    registerSelectionHandler(_transferViewer);
    
  }

   /**
    * {@inheritDoc}
 	*/
	@Override
	protected Control createContents(Composite parent_p) {
		Control result = super.createContents(parent_p);
		// set the size of the window
	    result.getShell().setMinimumSize(1024, 688);
		return result;
	}

/**
   * Get the left viewer content provider.<br>
   * If none was given at construction time, a default one, based on containment, is instantiated.
   * @return not <code>null</code> instance.
   */
  protected ITreeContentProvider getLeftContentProvider() {
    if (null == _leftContentProvider) {
      DataContentProvider dataContentProvider = new DataContentProvider();
      dataContentProvider.setExpandingNewContent(true);
      _leftContentProvider = /*new GroupingContentProvider(*/dataContentProvider/*)*/;
    }
    return _leftContentProvider;
  }

  /**
   * Get the left viewer label provider.<br>
   * If none was given at construction time, a default one is instantiated.
   * @return not <code>null</code> instance.
   */
  protected DataLabelProvider getLeftLabelProvider() {
    return _leftLabelProvider;
  }

  /**
   * Get the left viewer style.
   * @return
   */
  protected int getLeftViewerStyle() {
    return _leftViewerStyle;
  }

  /**
   * Get all elements in the right viewer as an overall result.
   * @return a not <code>null</code> array.
   */
  @Override
  public Object[] getResult() {
    return _results;
  }

  /**
   * Get the right viewer content provider.<br>
   * If none was given at construction time, a default one, based on containment, is instantiated.
   * @return not <code>null</code> instance.
   */
  protected ITreeContentProvider getRightContentProvider() {
    if (null == _rightContentProvider) {
      DataContentProvider dataContentProvider = new DataContentProvider();
      dataContentProvider.setExpandingNewContent(true);
      _rightContentProvider = /*new GroupingContentProvider(*/dataContentProvider/*)*/;
    }
    return _rightContentProvider;
  }

  /**
   * Get the right viewer label provider.<br>
   * If none was given at construction time, a default one is instantiated.
   * @return not <code>null</code> instance.
   */
  protected DataLabelProvider getRightLabelProvider() {
    return _rightLabelProvider;
  }

  /**
   * Get the right viewer style.
   * @return
   */
  protected int getRightViewerStyle() {
    return _rightViewerStyle;
  }

  /**
   * Get the transfer viewer.
   * @return a not <code>null</code> instance.
   */
  public TransferTreeListViewer getTransferViewer() {
    return _transferViewer;
  }

  /**
   * Handle selection enablement for AbstractTransferViewer2.REMOVE_SELECTED_BUTTON & AbstractTransferViewer2.ADD_SELECTED_BUTTON buttons.
   * @param initialInput_p
   * @param selection_p
   * @return <code>true</code> means valid.
   */
  protected boolean handleSelectionForAddAndRemoveButton(AbstractData initialInput_p, ISelection selection_p) {
    boolean result = false;
    if (!selection_p.isEmpty()) {
      result = true;
      StructuredSelection selection = (StructuredSelection) selection_p;
      Object[] selectedElements = selection.toArray();
      for (Object object : selectedElements) {
        result &= initialInput_p.isValid(object);
        if (!result) {
          break;
        }
      }
    }
    return result;
  }

  /**
   * Initialize left viewer.<br>
   * Default implementation set the left viewer with :
   * <ul>
   * <li>the content provider</li>
   * <li>the label provider</li>.
   * <li>Initial input.</li>.
   * </ul>
   * @param transferViewer_p
   */
  protected void initializeLeftViewer(TransferTreeListViewer transferViewer_p) {
    _transferViewer.setLeftContentProvider(getLeftContentProvider());
    DataLabelProvider leftLabelProvider = getLeftLabelProvider();
    _transferViewer.setLeftLabelProvider(leftLabelProvider);
    // Configure the left viewer.
    TreeViewer leftViewer = _transferViewer.getLeftViewer();
    leftLabelProvider.setViewer(leftViewer);
    // Create a TreeData as initial input.
    List<? extends EObject> elements = _leftInput.getKey();
    TreeData leftInput = new TreeData(elements, _leftInput.getValue());
    _transferViewer.setLeftInput(leftInput);
    // If one element is provided as input elements and expand to some level is allowed, select it.
    if (elements.size() == 1 && _leftViewerExpandLevel > 0) {
      // Select it.
      leftViewer.setSelection(new StructuredSelection(elements.get(0)), true);
      // Force the focus on the tree.
      leftViewer.getControl().setFocus();
    }
  }

  /**
   * Initialize right viewer.<br>
   * Default implementation set the right viewer with :
   * <ul>
   * <li>the content provider</li>
   * <li>the label provider</li>.
   * <li>Initial input.</li>.
   * </ul>
   * @param transferViewer_p
   */
  protected void initializeRightViewer(TransferTreeListViewer transferViewer_p) {
    _transferViewer.setRightContentProvider(getRightContentProvider());
    DataLabelProvider rightLabelProvider = getRightLabelProvider();
    _transferViewer.setRightLabelProvider(rightLabelProvider);
    // Configure the right viewer.
    TreeViewer rightViewer = _transferViewer.getRightViewer();
    rightLabelProvider.setViewer(rightViewer);
    // Create a TreeData as initial input.
    AbstractData rightInput = createRightViewerData(_rightInput.getKey(), _rightInput.getValue());
    _transferViewer.setRightInput(rightInput);
  }

  /**
   * @see org.eclipse.jface.dialogs.Dialog#okPressed()
   */
  @Override
  protected void okPressed() {
    _results = getTransferViewer().getRightInput().getValidElements().toArray();
    super.okPressed();
  }

  /**
   * Register selection handlers that drive the transfer buttons.
   * @param transferViewer_p
   */
  protected void registerSelectionHandler(final TransferTreeListViewer transferViewer_p) {
    // Register a selection handler to drive the add button i.e selection is coming from left viewer.
    transferViewer_p.setSelectionChangedHandler(new SelectionChangedHandler() {
      @Override
      protected boolean doHandleSelection(ISelection selection_p) {
        return handleSelectionForAddAndRemoveButton(transferViewer_p.getLeftInput(), selection_p);
      }
    }, AbstractTransferViewer2.ADD_SELECTED_BUTTON);
    // Register a selection handler to drive the remove button i.e selection is coming from right viewer.
    transferViewer_p.setSelectionChangedHandler(new SelectionChangedHandler() {
      @Override
      protected boolean doHandleSelection(ISelection selection_p) {
        return handleSelectionForAddAndRemoveButton(transferViewer_p.getRightInput(), selection_p);
      }
    }, AbstractTransferViewer2.REMOVE_SELECTED_BUTTON);
    // Force '>' & '<' buttons to update their enablement state according to registered handlers.
    transferViewer_p.getLeftViewer().setSelection(StructuredSelection.EMPTY);
    transferViewer_p.getRightViewer().setSelection(StructuredSelection.EMPTY);
  }

  /**
   * Set the content provider for left viewer.
   * @param leftContentProvider_p must be not <code>null</code>.
   */
  public void setLeftContentProvider(DataContentProvider leftContentProvider_p) {
    _leftContentProvider = leftContentProvider_p;
  }

  /**
   * Set left input.
   * @param elements_p
   * @param context_p optional parameter, to set an undefined context to {@link ILinkSelection} contribution, set {@link #UNDEFINED_CONTEXT}.
   */
  public void setLeftInput(List<? extends EObject> elements_p, Object context_p) {
    _leftInput = new Couple<List<? extends EObject>, Object>(elements_p, context_p);
  }

  /**
   * Set the content provider for the right viewer.
   * @param rightContentProvider_p
   */
  public void setRightContentProvider(DataContentProvider rightContentProvider_p) {
    _rightContentProvider = rightContentProvider_p;
  }

  /**
   * Set right input.
   * @param elements_p
   * @param context_p optional parameter, to set an undefined context to {@link ILinkSelection} contribution, set {@link #UNDEFINED_CONTEXT}.
   */
  public void setRightInput(List<? extends EObject> elements_p, Object context_p) {
    _rightInput = new Couple<List<? extends EObject>, Object>(elements_p, context_p);
  }
}
