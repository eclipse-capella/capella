/*******************************************************************************
 * Copyright (c) 2006, 2022 THALES GLOBAL SERVICES.
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

package org.polarsys.capella.common.ui.toolkit.viewers;

import java.util.Collection;

import org.eclipse.jface.viewers.AbstractTreeViewer;
import org.eclipse.jface.viewers.IContentProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.polarsys.capella.common.helpers.selection.ILinkSelection;
import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;
import org.polarsys.capella.common.mdsofa.common.helper.MiscHelper;
import org.polarsys.capella.common.ui.services.helper.AdapterManagerHelper;
import org.polarsys.capella.common.ui.toolkit.viewers.data.AbstractData;
import org.polarsys.capella.common.ui.toolkit.viewers.data.DataContentProvider;
import org.polarsys.capella.common.ui.toolkit.viewers.data.ListData;
import org.polarsys.capella.common.ui.toolkit.viewers.data.TreeData;

/**
 * {@link TreeAndListViewer} is a viewer that displays elements either as tree data structure or as list data
 * structure.<br>
 * A TreeViewer mode button can be displayed to allow the end-user to switch between a tree or flat representation.<br>
 * This viewer only works with {@link AbstractData} element as input and {@link DataContentProvider} as content
 * provider.
 */
public class TreeAndListViewer extends RegExpTreeViewer {
  /**
   * Data structure.
   */
  private AbstractData _data;
  /**
   * Keep a reference on the selection to get it even if the viewer is disposed.
   */
  private IStructuredSelection _selection;
  /**
   * Status bar widget.
   */
  private Text _statusBar;
  /**
   * Tree viewer button to switch between flat or tree representation.
   */
  private Button _treeViewerModeButton;

  /**
   * Constructor.
   * 
   * @param parent
   * @param isMultipleSelection
   * @param style
   *          could be 0 or a combination of {@value #SHOW_STATUS_BAR} {@value #SHOW_TREE_VIEW_MODE_BUTTON}
   */
  public TreeAndListViewer(Composite parent, boolean isMultipleSelection, int style, int viewerExpandLevel) {
    super(parent, isMultipleSelection, style, viewerExpandLevel);
  }

  /**
   * Constructor.
   * 
   * @param parent
   * @param isMultipleSelection
   * @param style
   *          could be 0 or a combination of {@value #SHOW_STATUS_BAR} {@value #SHOW_TREE_VIEW_MODE_BUTTON}
   */
  public TreeAndListViewer(Composite parent, boolean isMultipleSelection, int style) {
    super(parent, isMultipleSelection, style, AbstractTreeViewer.ALL_LEVELS);
  }

  /**
   * Constructor.
   * 
   * @param parent
   * @param displayedElements
   * @param isMultipleSelection
   * @param labelProvider
   */
  public TreeAndListViewer(Composite parent, Collection<? extends Object> displayedElements,
      boolean isMultipleSelection, ILabelProvider labelProvider) {
    this(parent, displayedElements, null /* no context */, isMultipleSelection, labelProvider,
        IViewerStyle.SHOW_STATUS_BAR | IViewerStyle.SHOW_TREE_VIEW_MODE_BUTTON, AbstractTreeViewer.ALL_LEVELS);
  }

  /**
   * Constructor.
   * 
   * @param parent
   * @param displayedElements
   * @param context
   *          to set an undefined context to {@link ILinkSelection} contribution, set
   *          {@link AbstractData#UNDEFINED_CONTEXT}.
   * @param isMultipleSelection
   * @param contentProvider
   * @param labelProvider
   * @param style
   *          could be 0 or a combination of {@value #SHOW_STATUS_BAR} {@value #SHOW_TREE_VIEW_MODE_BUTTON}
   * @param viewerExpandLevel
   */
  public TreeAndListViewer(Composite parent, Collection<? extends Object> displayedElements, Object context,
      boolean isMultipleSelection, IContentProvider contentProvider, ILabelProvider labelProvider, int style,
      int viewerExpandLevel) {
    super(parent, isMultipleSelection, style, viewerExpandLevel);
    // Default implementation displays elements as a tree.
    TreeViewer viewer = getClientViewer();
    viewer.setContentProvider(/* new GroupingContentProvider( */contentProvider/* ) */);
    viewer.setLabelProvider(labelProvider);
    setInput(displayedElements, context);
  }

  /**
   * Constructor.
   * 
   * @param parent
   * @param displayedElements
   * @param context
   *          to set an undefined context to {@link ILinkSelection} contribution, set
   *          {@link AbstractData#UNDEFINED_CONTEXT}.
   * @param isMultipleSelection
   * @param labelProvider
   * @param style
   *          could be 0 or a combination of {@value #SHOW_STATUS_BAR} {@value #SHOW_TREE_VIEW_MODE_BUTTON}
   * @param viewerExpandLevel
   */
  public TreeAndListViewer(Composite parent, Collection<? extends Object> displayedElements, Object context,
      boolean isMultipleSelection, ILabelProvider labelProvider, int style, int viewerExpandLevel) {
    this(parent, displayedElements, context, isMultipleSelection, new DataContentProvider(), labelProvider, style,
        viewerExpandLevel);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void createControl(Composite parent) {
    super.createControl(parent);
    // Create the TreeViewer mode with the Tree Viewer composite as parent.
    createTreeViewerModeButton(getControl());
    // Create the status bar with the Tree Viewer composite as parent.
    createStatusBar(getControl());
  }

  /**
   * Create the right {@link ListData} when the end-user changes the Tree view mode.
   * 
   * @param viewer
   *          viewer that the end-user wants to change the Tree view mode.
   * @param displayedElements
   * @param context
   * @return a not <code>null</code> instance.
   */
  protected ListData createListDataWhenTreeViewModeClicked(TreeViewer viewer, java.util.List<?> displayedElements,
      Object context) {
    return new ListData(displayedElements, context);
  }

  /**
   * Create Status bar widget.
   * 
   * @param parent
   */
  protected void createStatusBar(Composite parent) {
    if ((IViewerStyle.SHOW_STATUS_BAR & getStyle()) != 0) {
      _statusBar = new Text(parent, SWT.BORDER | SWT.READ_ONLY);
      GridData gridData = new GridData(GridData.FILL, GridData.BEGINNING, true, false);
      gridData.widthHint = getStatusBarWidthhint();
      _statusBar.setLayoutData(gridData);

    }
  }

  /**
   * Create the right {@link TreeData} when the end-user changes the Tree view mode.
   * 
   * @param viewer
   *          viewer that the end-user wants to change the Tree view mode.
   * @param displayedElements
   * @param context
   * @return a not <code>null</code> instance.
   */
  protected TreeData createTreeDataWhenTreeViewModeClicked(TreeViewer viewer,
      java.util.List<? extends Object> displayedElements, Object context) {
    return new TreeData(displayedElements, context);
  }

  /**
   * Create a checkbox button to switch between a Tree or Flat representation.
   * 
   * @param parent
   */
  protected void createTreeViewerModeButton(Composite parent) {
    if ((IViewerStyle.SHOW_TREE_VIEW_MODE_BUTTON & getStyle()) != 0) {
      _treeViewerModeButton = new Button(parent, SWT.CHECK);
      _treeViewerModeButton.setText(Messages.getString("TreeAndListViewer_TreeViewerMode_Title")); //$NON-NLS-1$
      _treeViewerModeButton.setSelection((IViewerStyle.SHOW_LIST_VIEW_MODE & getStyle()) == 0);
      _treeViewerModeButton.addSelectionListener(new SelectionAdapter() {
        /**
         * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
         */
        @Override
        public void widgetSelected(SelectionEvent event) {
          handleTreeViewModeClicked(((Button) event.widget).getSelection(), getClientViewer());
        }
      });
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected TreeViewer doClientViewer(Composite parent) {
    TreeViewer viewer = super.doClientViewer(parent);
    // We need to collect the selection to provide requesters with it even if the viewer is disposed.
    viewer.addSelectionChangedListener(new ISelectionChangedListener() {
      @SuppressWarnings("synthetic-access")
      public void selectionChanged(SelectionChangedEvent event) {
        _selection = (IStructuredSelection) event.getSelection();
        if (_selection.size() == 1) {
          updateStatusBar(_selection.toList().get(0));
        } else {
          // No selection or multi selection, do not display anything in the status bar.
          updateStatusBar(null);
        }
      }
    });
    // Install a sorter which uses the default collator to sort strings.
    viewer.setAutoExpandLevel(_viewerExpandLevel);

    return viewer;
  }

  /**
   * Get data for Tree usage.
   * 
   * @return the dataTree
   */
  public AbstractData getData() {
    return _data;
  }

  /**
   * Get first selected element.
   * 
   * @return <code>null</code> if no selection.
   */
  public Object getFirstElement() {
    Object result = null;
    ISelection selection = getSelection();
    if ((null != selection) && !selection.isEmpty()) {
      result = ((IStructuredSelection) selection).getFirstElement();
    }
    return result;
  }

  /**
   * @see org.polarsys.capella.common.ui.toolkit.viewers.FieldsViewer#getSelection()
   */
  @Override
  public ISelection getSelection() {
    return _selection;
  }

  /**
   * @see org.polarsys.capella.common.ui.toolkit.viewers.RegExpTreeViewer#getTreeStyle()
   */
  @Override
  protected int getTreeStyle() {
    return SWT.FULL_SELECTION | (super._isMultipleSelection ? SWT.MULTI : SWT.SINGLE) | SWT.BORDER | SWT.V_SCROLL;
  }

  /**
   * Handle TreeView mode clicked event.
   * 
   * @param checked
   * @param viewer
   */
  protected void handleTreeViewModeClicked(boolean checked, TreeViewer viewer) {
    // Get the current viewer input.
    AbstractData currentInitialInput = (AbstractData) viewer.getInput();
    // New viewer input.
    AbstractData newInitialInput = null;
    if (checked) {
      // Move from List To Tree representation.
      newInitialInput = createTreeDataWhenTreeViewModeClicked(viewer,
          MiscHelper.asList(currentInitialInput.getElements()), currentInitialInput.getContext());
    } else {
      // Move from Tree To List representation.
      newInitialInput = createListDataWhenTreeViewModeClicked(viewer, currentInitialInput.getValidElements(),
          currentInitialInput.getContext());
    }
    // Transfer the notified object.
    newInitialInput.setNotifiedForValidElementsChanges(currentInitialInput.getNotifiedForValidElementsChanges());
    viewer.setInput(newInitialInput);
  }

  /**
   * Is given element valid ?
   * 
   * @param element
   * @return <code>true</code> means valid.
   */
  public boolean isValidElement(Object element) {
    return _data.isValid(element);
  }

  /**
   * Set given list of objects as new input elements.
   * 
   * @param displayedElements
   * @param context
   */
  public void setInput(Collection<? extends Object> displayedElements, Object context) {
    boolean hierarchicalDisplay = ((IViewerStyle.SHOW_LIST_VIEW_MODE & getStyle()) == 0);
    if ((null != _treeViewerModeButton) && !_treeViewerModeButton.isDisposed()) {
      hierarchicalDisplay = _treeViewerModeButton.getSelection();
    }
    _data = (hierarchicalDisplay) ? new TreeData(displayedElements, context) : new ListData(displayedElements, context);
    getClientViewer().setInput(_data);
  }

  /**
   * Set the selection listener.
   * 
   * @param listener
   */
  public void setSelectionListener(ISelectionChangedListener listener) {
    getClientViewer().addSelectionChangedListener(listener);
  }

  /**
   * Set the expand level.
   * 
   * @param expandLevel
   */
  public void setAutoExpandLevel(int expandLevel) {
    getClientViewer().setAutoExpandLevel(expandLevel);
  }

  /**
   * Update the status bar.
   * 
   * @param selectedElement
   */
  protected void updateStatusBar(Object selectedElement) {
    // Precondition
    if ((null == _statusBar) || _statusBar.isDisposed()) {
      return;
    }
    ITreeLabelAdapter treeLabelAdapter = (ITreeLabelAdapter) AdapterManagerHelper.getAdapter(selectedElement,
        ITreeLabelAdapter.class);
    if (null != treeLabelAdapter) {
      _statusBar.setText(treeLabelAdapter.getFullLabel());
    } else {
      _statusBar.setText(ICommonConstants.EMPTY_STRING);
    }
  }

  /**
   * Get width hint value to constraint status bar.
   * 
   * @return default value is <code>10</code>.
   */
  protected int getStatusBarWidthhint() {
    return 10; // Magic value to get something nice.
  }
}
