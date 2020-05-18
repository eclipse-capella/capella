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

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.AbstractTreeViewer;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.polarsys.capella.common.helpers.selection.ILinkSelection;
import org.polarsys.capella.common.mdsofa.common.misc.Couple;
import org.polarsys.capella.common.ui.toolkit.viewers.data.AbstractData;
import org.polarsys.capella.common.ui.toolkit.viewers.data.DataContentProvider;
import org.polarsys.capella.common.ui.toolkit.viewers.data.DataLabelProvider;
import org.polarsys.capella.common.ui.toolkit.viewers.data.ListData;
import org.polarsys.capella.common.ui.toolkit.viewers.data.TreeData;
import org.polarsys.capella.common.ui.toolkit.viewers.transfer.AbstractTransferViewer2;
import org.polarsys.capella.common.ui.toolkit.viewers.transfer.TransferTreeListViewer;
import org.polarsys.capella.common.ui.toolkit.widgets.handler.SelectionChangedHandler;

/**
 * Dialog that displays a {@link TransferTreeListViewer}
 */
public class TransferTreeListDialog extends AbstractViewerDialog {

  public static final String TRANSFERT_TREELIST_DIALOG = "org.polarsys.capella.common.ui.toolkit.dialogs.transferTreeListElements";
  
  public static final String TRANSFERT_TREELIST_DIALOG_LEFT = "org.polarsys.capella.common.ui.toolkit.dialogs.transferTreeListElements.leftPane";
  
  public static final String TRANSFERT_TREELIST_DIALOG_RIGHT = "org.polarsys.capella.common.ui.toolkit.dialogs.transferTreeListElements.rightPane";
  
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

  public TransferTreeListDialog(Shell parentShell, String dialogTitle, String dialogMessage) {
    this(parentShell, dialogTitle, dialogMessage,
      new DataLabelProvider(),
      new DataLabelProvider(),
      DEFAULT_TREE_VIEWER_STYLE, DEFAULT_TREE_VIEWER_STYLE,
      AbstractTreeViewer.ALL_LEVELS, AbstractTreeViewer.ALL_LEVELS);
  }

  protected TransferTreeListDialog(Shell parentShell, String dialogTitle, String dialogMessage,
    DataLabelProvider leftLabelProvider, DataLabelProvider rightLabelProvider,
    int leftViewerStyle, int rightViewerStyle, int leftViewerExpandLevel, int rightViewerExpandLevel)
  {
    super(parentShell, dialogTitle, dialogMessage, Messages.TransferTreeListDialog_Shell_Title);
    // Left viewer.
    _leftLabelProvider = leftLabelProvider;
    _leftViewerStyle = leftViewerStyle;
    _leftViewerExpandLevel = leftViewerExpandLevel;
    // Right viewer.
    _rightLabelProvider = rightLabelProvider;
    _rightViewerStyle = rightViewerStyle;
    _rightViewerExpandLevel = rightViewerExpandLevel;
  }

  /**
   * DEFAULT_TREE_VIEWER_STYLE is used for both left and right viewers.
   */
  public TransferTreeListDialog(Shell parentShell, String dialogTitle, String dialogMessage,
    DataLabelProvider leftLabelProvider, DataLabelProvider rightLabelProvider)
  {
    this(parentShell, dialogTitle, dialogMessage,
      leftLabelProvider, rightLabelProvider,
      DEFAULT_TREE_VIEWER_STYLE, DEFAULT_TREE_VIEWER_STYLE,
      AbstractTreeViewer.ALL_LEVELS, AbstractTreeViewer.ALL_LEVELS);
  }

  /**
   * DEFAULT_TREE_VIEWER_STYLE is used for both left and right viewers.
   */
  public TransferTreeListDialog(Shell parentShell, String dialogTitle, String dialogMessage,
    DataLabelProvider leftLabelProvider, DataLabelProvider rightLabelProvider,
    int leftViewerExpandLevel, int rightViewerExpandLevel)
  {
    this(parentShell, dialogTitle, dialogMessage,
      leftLabelProvider, rightLabelProvider,
      DEFAULT_TREE_VIEWER_STYLE, DEFAULT_TREE_VIEWER_STYLE,
      leftViewerExpandLevel, rightViewerExpandLevel);
  }

  /**
   * Create right viewer data at dialog creation time.<br>
   * Default implementation creates a {@link TreeData}.
   * @param elements
   * @param context
   * @return a not <code>null</code> {@link AbstractData} instance.
   */
  protected AbstractData createRightViewerData(List<? extends EObject> elements, Object context) {
    return new TreeData(elements, context);
  }

  /**
   * Create {@link TransferTreeListViewer}.
   * @param parent
   * @return a not <code>null</code> instance.
   */
  protected TransferTreeListViewer createTransferTreeListViewer(Composite parent) {
    return new TransferTreeListViewer(parent, TRANSFER_TREE_STYLE, _leftViewerStyle, _rightViewerStyle, _leftViewerExpandLevel, _rightViewerExpandLevel);
  }

  /**
   * @see org.polarsys.capella.common.ui.toolkit.dialogs.AbstractViewerDialog#doCreateDialogArea(org.eclipse.swt.widgets.Composite)
   */
  @Override
  protected void doCreateDialogArea(Composite parent) {
    // Create the transfer tree list viewer.
    _transferViewer = createTransferTreeListViewer(parent);
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
	protected Control createContents(Composite parent) {
		Control result = super.createContents(parent);
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
   * @param initialInput
   * @param selection
   * @return <code>true</code> means valid.
   */
  protected boolean handleSelectionForAddAndRemoveButton(AbstractData initialInput, ISelection selection) {
    boolean result = false;
    if (!selection.isEmpty()) {
      result = true;
      StructuredSelection sel = (StructuredSelection) selection;
      Object[] selectedElements = sel.toArray();
      for (Object object : selectedElements) {
        result &= initialInput.isValid(object);
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
   * @param transferViewer
   */
  protected void initializeLeftViewer(TransferTreeListViewer transferViewer) {
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
   * @param transferViewer
   */
  protected void initializeRightViewer(TransferTreeListViewer transferViewer) {
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
   * @param transferViewer
   */
  protected void registerSelectionHandler(final TransferTreeListViewer transferViewer) {
    // Register a selection handler to drive the add button i.e selection is coming from left viewer.
    transferViewer.setSelectionChangedHandler(new SelectionChangedHandler() {
      @Override
      protected boolean doHandleSelection(ISelection selection) {
        return handleSelectionForAddAndRemoveButton(transferViewer.getLeftInput(), selection);
      }
    }, AbstractTransferViewer2.ADD_SELECTED_BUTTON);
    // Register a selection handler to drive the remove button i.e selection is coming from right viewer.
    transferViewer.setSelectionChangedHandler(new SelectionChangedHandler() {
      @Override
      protected boolean doHandleSelection(ISelection selection) {
        return handleSelectionForAddAndRemoveButton(transferViewer.getRightInput(), selection);
      }
    }, AbstractTransferViewer2.REMOVE_SELECTED_BUTTON);
    // Force '>' & '<' buttons to update their enablement state according to registered handlers.
    transferViewer.getLeftViewer().setSelection(StructuredSelection.EMPTY);
    transferViewer.getRightViewer().setSelection(StructuredSelection.EMPTY);
  }

  /**
   * Set the content provider for left viewer.
   * @param leftContentProvider must be not <code>null</code>.
   */
  public void setLeftContentProvider(DataContentProvider leftContentProvider) {
    _leftContentProvider = leftContentProvider;
  }

  /**
   * Set left input.
   * @param elements
   * @param context optional parameter, to set an undefined context to {@link ILinkSelection} contribution, set {@link #UNDEFINED_CONTEXT}.
   */
  public void setLeftInput(List<? extends EObject> elements, Object context) {
    _leftInput = new Couple<List<? extends EObject>, Object>(elements, context);
  }

  /**
   * Set the content provider for the right viewer.
   * @param rightContentProvider
   */
  public void setRightContentProvider(DataContentProvider rightContentProvider) {
    _rightContentProvider = rightContentProvider;
  }

  /**
   * Set right input.
   * @param elements
   * @param context optional parameter, to set an undefined context to {@link ILinkSelection} contribution, set {@link #UNDEFINED_CONTEXT}.
   */
  public void setRightInput(List<? extends EObject> elements, Object context) {
    _rightInput = new Couple<List<? extends EObject>, Object>(elements, context);
  }
}
