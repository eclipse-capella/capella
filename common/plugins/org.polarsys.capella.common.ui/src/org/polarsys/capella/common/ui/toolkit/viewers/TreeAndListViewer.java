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
package org.polarsys.capella.common.ui.toolkit.viewers;

import java.util.Collection;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.AbstractTreeViewer;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.ViewerSorter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

import org.polarsys.capella.common.ui.services.helper.AdapterManagerHelper;
import org.polarsys.capella.common.ui.toolkit.viewers.data.AbstractData;
import org.polarsys.capella.common.ui.toolkit.viewers.data.DataContentProvider;
import org.polarsys.capella.common.ui.toolkit.viewers.data.ListData;
import org.polarsys.capella.common.ui.toolkit.viewers.data.TreeData;
import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;
import org.polarsys.capella.common.mdsofa.common.helper.MiscHelper;

/**
 * {@link TreeAndListViewer} is a viewer that displays elements either as tree data structure or as list data structure.<br>
 * A TreeViewer mode button can be displayed to allow the end-user to switch between a tree or flat representation.<br>
 * This viewer only works with {@link AbstractData} element as input and {@link DataContentProvider} as content provider.
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
   * @param parent_p
   * @param isMultipleSelection_p
   * @param style_p could be 0 or a combination of {@value #SHOW_STATUS_BAR} {@value #SHOW_TREE_VIEW_MODE_BUTTON}
   */
  public TreeAndListViewer(Composite parent_p, boolean isMultipleSelection_p, int style_p, int viewerExpandLevel_p) {
    super(parent_p, isMultipleSelection_p, style_p, viewerExpandLevel_p);
  }

  /**
   * Constructor.
   * @param parent_p
   * @param isMultipleSelection_p
   * @param style_p could be 0 or a combination of {@value #SHOW_STATUS_BAR} {@value #SHOW_TREE_VIEW_MODE_BUTTON}
   */
  public TreeAndListViewer(Composite parent_p, boolean isMultipleSelection_p, int style_p) {
    super(parent_p, isMultipleSelection_p, style_p, AbstractTreeViewer.ALL_LEVELS);
  }

  /**
   * Constructor.
   * @param parent_p
   * @param displayedElements_p
   * @param isMultipleSelection_p
   * @param labelProvider_p
   */
  public TreeAndListViewer(Composite parent_p, Collection<? extends EObject> displayedElements_p, boolean isMultipleSelection_p, ILabelProvider labelProvider_p) {
    this(parent_p, displayedElements_p, null /* no context */, isMultipleSelection_p, labelProvider_p,
      IViewerStyle.SHOW_STATUS_BAR|IViewerStyle.SHOW_TREE_VIEW_MODE_BUTTON, AbstractTreeViewer.ALL_LEVELS);
  }

  /**
   * Constructor.
   * @param parent_p
   * @param displayedElements_p
   * @param context_p to set an undefined context to {@link ILinkSelection} contribution, set {@link AbstractData#UNDEFINED_CONTEXT}.
   * @param isMultipleSelection_p
   * @param labelProvider_p
   * @param style_p could be 0 or a combination of {@value #SHOW_STATUS_BAR} {@value #SHOW_TREE_VIEW_MODE_BUTTON}
   * @param viewerExpandLevel_p
   */
  public TreeAndListViewer(Composite parent_p, Collection<? extends EObject> displayedElements_p, Object context_p, boolean isMultipleSelection_p,
      ILabelProvider labelProvider_p, int style_p, int viewerExpandLevel_p)
  {
    super(parent_p, isMultipleSelection_p, style_p, viewerExpandLevel_p);
    // Default implementation displays elements as a tree.
    TreeViewer viewer = getClientViewer();
    DataContentProvider contentProvider = new DataContentProvider();
    viewer.setContentProvider(/*new GroupingContentProvider(*/contentProvider/*)*/);
    viewer.setLabelProvider(labelProvider_p);
    setInput(displayedElements_p, context_p);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void createControl(Composite parent_p) {
    super.createControl(parent_p);
    // Create the TreeViewer mode with the Tree Viewer composite as parent.
    createTreeViewerModeButton(getControl());
    // Create the status bar with the Tree Viewer composite as parent.
    createStatusBar(getControl());
  }

  /**
   * Create the right {@link ListData} when the end-user changes the Tree view mode.
   * @param viewer_p viewer that the end-user wants to change the Tree view mode.
   * @param displayedElements_p
   * @param context_p
   * @return a not <code>null</code> instance.
   */
  protected ListData createListDataWhenTreeViewModeClicked(TreeViewer viewer_p, java.util.List<? extends Object> displayedElements_p, Object context_p) {
    return new ListData(displayedElements_p, context_p);
  }

  /**
   * Create Status bar widget.
   * @param parent_p
   */
  protected void createStatusBar(Composite parent_p) {
    if ((IViewerStyle.SHOW_STATUS_BAR & getStyle()) != 0) {
      _statusBar = new Text(parent_p, SWT.BORDER | SWT.READ_ONLY);
      _statusBar.setLayoutData(new GridData(GridData.FILL, GridData.BEGINNING, true, false));
    }
  }

  /**
   * Create the right {@link TreeData} when the end-user changes the Tree view mode.
   * @param viewer_p viewer that the end-user wants to change the Tree view mode.
   * @param displayedElements_p
   * @param context_p
   * @return a not <code>null</code> instance.
   */
  protected TreeData createTreeDataWhenTreeViewModeClicked(TreeViewer viewer_p, java.util.List<? extends Object> displayedElements_p, Object context_p) {
    return new TreeData(displayedElements_p, context_p);
  }

  /**
   * Create a checkbox button to switch between a Tree or Flat representation.
   * @param parent_p
   */
  protected void createTreeViewerModeButton(Composite parent_p) {
    if ((IViewerStyle.SHOW_TREE_VIEW_MODE_BUTTON & getStyle()) != 0) {
      _treeViewerModeButton = new Button(parent_p, SWT.CHECK);
      _treeViewerModeButton.setText(Messages.getString("TreeAndListViewer_TreeViewerMode_Title")); //$NON-NLS-1$
      _treeViewerModeButton.setSelection((IViewerStyle.SHOW_LIST_VIEW_MODE & getStyle()) == 0);
      _treeViewerModeButton.addSelectionListener(new SelectionAdapter() {
        /**
         * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
         */
        @Override
        public void widgetSelected(SelectionEvent event_p) {
          handleTreeViewModeClicked(((Button) event_p.widget).getSelection(), getClientViewer());
        }
      });
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected TreeViewer doClientViewer(Composite parent_p) {
    TreeViewer viewer = super.doClientViewer(parent_p);
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
    viewer.setSorter(new ViewerSorter());
    viewer.setAutoExpandLevel(_viewerExpandLevel);

    return viewer;
  }

  /**
   * Get data for Tree usage.
   * @return the dataTree
   */
  public AbstractData getData() {
    return _data;
  }

  /**
   * Get first selected element.
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
   * @param checked
   * @param viewer_p
   */
  protected void handleTreeViewModeClicked(boolean checked, TreeViewer viewer_p) {
    // Get the current viewer input.
    AbstractData currentInitialInput = (AbstractData) viewer_p.getInput();
    // New viewer input.
    AbstractData newInitialInput = null;
    if (checked) {
      // Move from List To Tree representation.
      newInitialInput = createTreeDataWhenTreeViewModeClicked(viewer_p, MiscHelper.asList(currentInitialInput.getElements()), currentInitialInput.getContext());
    } else {
      // Move from Tree To List representation.
      newInitialInput = createListDataWhenTreeViewModeClicked(viewer_p, currentInitialInput.getValidElements(), currentInitialInput.getContext());
    }
    // Transfer the notified object.
    newInitialInput.setNotifiedForValidElementsChanges(currentInitialInput.getNotifiedForValidElementsChanges());
    viewer_p.setInput(newInitialInput);
  }

  /**
   * Is given element valid ?
   * @param element_p
   * @return <code>true</code> means valid.
   */
  public boolean isValidElement(EObject element_p) {
    return _data.isValid(element_p);
  }

  /**
   * Set given list of objects as new input elements.
   * @param displayedElements_p
   * @param context_p
   */
  public void setInput(Collection<? extends EObject> displayedElements_p, Object context_p) {
    boolean hierarchicalDisplay = ((IViewerStyle.SHOW_LIST_VIEW_MODE & getStyle()) == 0);
    if ((null != _treeViewerModeButton) && !_treeViewerModeButton.isDisposed()) {
      hierarchicalDisplay = _treeViewerModeButton.getSelection();
    }
    _data = (hierarchicalDisplay) ? new TreeData(displayedElements_p, context_p) : new ListData(displayedElements_p, context_p);
    getClientViewer().setInput(_data);
  }

  /**
   * Set the selection listener.
   * @param listener_p
   */
  public void setSelectionListener(ISelectionChangedListener listener_p) {
    getClientViewer().addSelectionChangedListener(listener_p);
  }

  /**
   * Set the expand level.
   * @param expandLevel_p
   */
  public void setAutoExpandLevel(int expandLevel_p) {
    getClientViewer().setAutoExpandLevel(expandLevel_p);
  }

  /**
   * Update the status bar.
   * @param selectedElement_p
   */
  protected void updateStatusBar(Object selectedElement_p) {
    // Precondition
    if ((null == _statusBar) || _statusBar.isDisposed()) {
      return;
    }
    ITreeLabelAdapter treeLabelAdapter = (ITreeLabelAdapter) AdapterManagerHelper.getAdapter(selectedElement_p, ITreeLabelAdapter.class);
    if (null != treeLabelAdapter) {
      _statusBar.setText(treeLabelAdapter.getFullLabel());
    } else {
      _statusBar.setText(ICommonConstants.EMPTY_STRING);
    }
  }

}
