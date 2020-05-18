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
package org.polarsys.capella.core.ui.toolkit.decomposition;

import java.util.List;

import org.eclipse.jface.dialogs.DialogPage;
import org.eclipse.jface.fieldassist.ControlDecoration;
import org.eclipse.jface.util.Util;
import org.eclipse.jface.viewers.ColumnViewerToolTipSupport;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ITreeSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Tree;

import org.polarsys.capella.common.ui.toolkit.viewers.FieldsViewer;

/**
 * Class <code>DecompositionGeneralViewer</code> creates the viewer according to the <code>DecompositionModel</code>, with two tree viewers on left and right
 * sides.
 */
public class DecompositionGeneralViewer extends FieldsViewer implements IDecompositionDataConstants {

  private DecompositionModel _decompositionModel;
  private Button _defaultButton;
  private DialogPage _dialogPage;
  private boolean _isLeftNRight;
  private DecompositionDragAndDrop _sourceDND;
  private TreeViewer _sourceTreeViewer;
  private Text _statusBarText;
  private TreeViewer _targetTreeViewer;
  private DecompositionTargetViewer _targetViewer;
  private boolean isDNDCreated = false;

  protected DecompositionModel model;  
  protected Button optionHideTechnicalInterfaces_button;

  /**
   * Constructor
   * @param parent_p the composite in which components are added
   * @param model_p the DecompositionModel
   * @param leftNRight_p the flag to display either both the viewers or the source viewer alone
   * @param wizardPage_p the wizard page
   */
  public DecompositionGeneralViewer(Composite parent_p, DecompositionModel model_p, boolean leftNRight_p) {
    super(parent_p);
    this._decompositionModel = model_p;
    this._isLeftNRight = leftNRight_p;
    this.model = model_p;
    DecompositionUtil.initializeImages(model_p.getImgRegistry());
    Composite internalComposite = createInternalComposite(getControl());
    createViewer(internalComposite);
    createOptions(internalComposite);    
  }

  public DecompositionGeneralViewer(Composite parent_p, DecompositionModel model_p, boolean leftNRight_p, boolean syschronizeName, boolean syschronizeDeletion) {
    this(parent_p, model_p, leftNRight_p);
    DecompositionPreferenceOption.synchronizeName = syschronizeName;
    DecompositionPreferenceOption.synchronizeDeletion = syschronizeDeletion;

  }

  public void addSelectionListenerToViewer(TreeViewer viewer_p) {
    viewer_p.addSelectionChangedListener(new ISelectionChangedListener() {

      @SuppressWarnings("synthetic-access")
      public void selectionChanged(SelectionChangedEvent event_p) {
        ITreeSelection selection = (ITreeSelection) event_p.getSelection();
        if (!selection.isEmpty() || (selection.size() == 1)) {
          updateStatusBar(selection.getFirstElement());
        } else {
          updateStatusBar(null);
        }
        if (null != _targetViewer) {
          _targetViewer.setRemoveTCButtonEnabled(!isDecompositionItemServiceOrDecompositionItemInternalSelected(selection));
        }
      }
    });
  }

  /**
   * Adds tree selection listener during synthesis check
   */
  public void addTreeSelectionListener() {
    ControlDecoration decoration = new DecompositionControlDecoration(_sourceTreeViewer.getTree(), SWT.LEFT);
    decoration.setShowOnlyOnFocus(true);
  }

  /**
   * Adds listeners to display tooltips on source tree
   */
  public void addTreeTipListeners() {
    ColumnViewerToolTipSupport.enableFor(_sourceTreeViewer);
  }

  public void createDNDForTargetViewer(CTabFolder folder_p) {
    CTabItem tabItem = folder_p.getSelection();
    TreeViewer viewer = (TreeViewer) tabItem.getData(TARGET_TREEVIEWER_DATA);
    if (viewer == null) {
      viewer = _targetViewer.getTargetTreeViewer();
    }
    if (isDNDCreated) {
      _sourceDND.setTargetViewer(viewer);
    }
    Decomposition decomposition = (Decomposition) tabItem.getData();
    if (decomposition != null) {
      _decompositionModel.refreshStatus(decomposition);
      refreshItems(null);
    }
  }

  /**
   * Creates the Drag and Drop Operations for the tree viewers.
   * @see DecompositionDragAndDrop
   */
  protected void createDragDropSourceTargets(TreeViewer targetViewer_p) {
    Tree sourceTree = _sourceTreeViewer.getTree();
    sourceTree.setData(TREEID_DATA, SOURCE_TREE_ID);
    Tree targetTree = targetViewer_p.getTree();
    targetTree.setData(TREEID_DATA, TARGET_TREE_ID);
    if (!isDNDCreated) {
      _sourceDND = new DecompositionDragAndDrop(sourceTree, _decompositionModel);
      isDNDCreated = true;
      _sourceDND.setSourceViewer(_sourceTreeViewer);
    }
    DecompositionDragAndDrop dnd = new DecompositionDragAndDrop(targetTree, _decompositionModel);
    dnd.setSourceViewer(_sourceTreeViewer);
    dnd.setTargetViewer(targetViewer_p);
    _sourceDND.setTargetViewer(targetViewer_p);
  }

  protected void createOptions(Composite parent) {
    optionHideTechnicalInterfaces_button = new Button(parent, SWT.CHECK);
    optionHideTechnicalInterfaces_button.setText(Messages.getString("LCDecompGeneralViewer.hideTechnicalInterfaces")); //$NON-NLS-1$
    optionHideTechnicalInterfaces_button.setSelection(model.doesHideTechnicalInterfaces());
    optionHideTechnicalInterfaces_button.addSelectionListener(new SelectionAdapter() {
      @Override
      public void widgetSelected(SelectionEvent event_p) {
      	model.setHideTechnicalInterfaces(((Button) event_p.widget).getSelection());
      	refreshItems(_targetViewer.getTargetTreeViewer());
      }
    });
  }
  
  /**
   * Creates the internal composite.
   * @param parent_p The parent composite.
   * @return The internal composite.
   */
  @Override
  protected Composite createInternalComposite(Composite parent_p) {
    Composite composite = new Composite(parent_p, SWT.NONE);
    // Install a layout manager, all widgets are displayed on 2 columns.
    composite.setLayout(new GridLayout(1, true));
    // Set its layout.
    GridData gridData = new GridData(SWT.FILL, SWT.FILL, true, true);
    composite.setLayoutData(gridData);
    return composite;
  }	
  
  /**
   * Creates the left viewer
   * @param composite_p the parent composite
   */
  private void createLeftViewer(Composite composite_p) {
    DecompositionSourceViewer sourceViewer = new DecompositionSourceViewer(composite_p);
    _sourceTreeViewer = sourceViewer.getSourceTreeViewer();
    sourceViewer.addSourceTreeLightbulbListener();
    
    addSelectionListenerToViewer(_sourceTreeViewer);
  }

  /**
   * creates the right viewer
   * @param parent_p the parent composite
   */
  private void createRightViewer(Composite parent_p) {
    _targetViewer = new DecompositionTargetViewer(parent_p, this);
    _targetTreeViewer = _targetViewer.getTargetTreeViewer();
  }

  /**
   * @param parent_p
   */
  private void createStatusTextField(Composite parent_p) {
    _statusBarText = new Text(parent_p, SWT.READ_ONLY | SWT.BORDER);
    _statusBarText.setEditable(false);
    GridData gdData = new GridData();
    gdData.horizontalAlignment = SWT.FILL;
    gdData.grabExcessHorizontalSpace = true;
    gdData.grabExcessVerticalSpace = false;
    gdData.horizontalSpan = 2;
    _statusBarText.setLayoutData(gdData);
  }

  /**
   * @see org.polarsys.capella.common.ui.toolkit.viewers.FieldsViewer#createControl(org.eclipse.swt.widgets.Composite)
   */
  protected void createViewer(Composite parent_p) {
    Composite treeComposite = new Composite(parent_p, SWT.CENTER);
    treeComposite.setLayout(new FillLayout(SWT.HORIZONTAL));
    GridData gdData = new GridData(GridData.FILL_BOTH);
    gdData.horizontalAlignment = SWT.FILL;
    gdData.grabExcessHorizontalSpace = true;
    gdData.grabExcessVerticalSpace = true;
    treeComposite.setLayoutData(gdData);
    createLeftViewer(treeComposite);
    if (_isLeftNRight) {
      createRightViewer(treeComposite);
    }

    populateTree(_decompositionModel);
    createStatusTextField(parent_p);
  }

  /**
   * @return the decompositionModel
   */
  public DecompositionModel getDecompositionModel() {
    return _decompositionModel;
  }

  /**
   * @return the dialogPage
   */
  public DialogPage getDialogPage() {
    return _dialogPage;
  }

  /**
   * @see org.polarsys.capella.common.ui.toolkit.viewers.FieldsViewer#getInput()
   */
  @Override
  public Object getInput() {
    return null;
  }

  private boolean isDecompositionItemServiceOrDecompositionItemInternalSelected(ITreeSelection selection_p) {
    List<?> list = selection_p.toList();
    if (list.isEmpty()) {
      return true;
    }
    for (Object obj : list) {
      if (obj instanceof DecompositionItemService) {
        return true;
      } else if (obj instanceof DecompositionItem) {
        // Add check for allow destruction on Internal Interface only
        if (!((DecompositionItem) obj).isInternal()) {
          return true;
        }
      }
    }
    return false;
  }

  /**
   * Populates the tree according to the model
   * @param model_p
   */
  private void populateTree(DecompositionModel model_p) {
    _sourceTreeViewer.setLabelProvider(new DecompositionLabelProvider(true));

    if (_isLeftNRight) {
      int decompSize = _decompositionModel.getDecompositions().size();
      if (decompSize > 0) {
        for (Decomposition decomp : _decompositionModel.getDecompositions()) {
          _targetViewer.createDecompositionTab(decomp);
          _targetTreeViewer = _targetViewer.getTargetTreeViewer();
          createDragDropSourceTargets(_targetTreeViewer);
          _targetViewer.setDecompositionFieldsEnabled(decompSize);
          model_p.refreshStatus(decomp);
          refreshItems(null);
        }
        _targetViewer.setDefaultSelectedTabIndex();
      }
    }
  }

  /**
   * Refreshes the source tree viewer and target tree viewer
   * @param targetViewer_p the target tree viewer
   */
  public void refreshItems(TreeViewer targetViewer_p) {
    _sourceTreeViewer.refresh(true);
    _sourceTreeViewer.setSelection(null, true);
    _sourceTreeViewer.getTree().notifyListeners(SWT.Selection, new Event());
    if (targetViewer_p != null) {
      targetViewer_p.refresh(true);
      targetViewer_p.setSelection(null, true);
    }
  }

  // ///////////////////////////////////////////////////////////////////////////
  // //////////////////////// ALL LISTENERS ////////////////////////////////////
  // ///////////////////////////////////////////////////////////////////////////

  public void removeDialogFocus() {
    _dialogPage.getShell().setDefaultButton(null);
  }

  /**
   * @param decompositionModel_p the decompositionModel to set
   */
  public void setDecompositionModel(DecompositionModel decompositionModel_p) {
    _decompositionModel = decompositionModel_p;
  }

  public void setDialogFocus() {
    _dialogPage.getShell().setDefaultButton(_defaultButton);
  }

  /**
   * @param dialogPage_p the dialogPage to set
   */
  public void setDialogPage(DialogPage dialogPage_p) {
    _dialogPage = dialogPage_p;
    _defaultButton = _dialogPage.getShell().getDefaultButton();
    removeDialogFocus();
  }

  /**
   * @see org.polarsys.capella.common.ui.toolkit.viewers.FieldsViewer#setInput(java.lang.Object)
   */
  @Override
  public void setInput(Object input_p) {
    // do nothing
  }

  /**
   * Sets the content provider and input for the source tree.
   * @param isSynthesisCheckPage_p flag for synthesis check
   */
  public void setSourceTreeContentProvider(boolean isSynthesisCheckPage_p) {
    _sourceTreeViewer.setContentProvider(new DecompositionTreeNodeContentProvider(model, isSynthesisCheckPage_p));
    _sourceTreeViewer.setInput(_decompositionModel);
  }

  /**
   * Shows the error message
   * @param message the message to be shown
   */
  public void showErrorMessage(String message) {
    _dialogPage.setErrorMessage(message);
  }

  /**
   * @param element_p
   */
  protected void updateStatusBar(Object element_p) {
    if (null == _statusBarText) {
      return;
    }
    if (element_p instanceof DecompositionItem) {
      DecompositionItem item = (DecompositionItem) element_p;
      String path = item.getPath();
      _statusBarText.setText((path == null) ? Util.ZERO_LENGTH_STRING : path);
      if (_targetViewer != null) {
        _targetViewer.setDetachInterfaceButtonEnabled(!item.getParentComponent().isReusedComponent());
      }
    }
    else if (element_p instanceof DecompositionComponent) {
      String str = ((DecompositionComponent) element_p).getPath();
      _statusBarText.setText((str == null) ? Util.ZERO_LENGTH_STRING : str);
    }
    else if (element_p instanceof DecompositionItemService) {
      DecompositionItemService item = (DecompositionItemService) element_p;
      String name = item.getPath();
      _statusBarText.setText((name == null) ? Util.ZERO_LENGTH_STRING : name);
    }
    else {
      _statusBarText.setText(Util.ZERO_LENGTH_STRING);
    }
  }
}
