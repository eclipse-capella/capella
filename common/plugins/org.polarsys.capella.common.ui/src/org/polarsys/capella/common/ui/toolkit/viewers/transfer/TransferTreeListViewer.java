/*******************************************************************************
 * Copyright (c) 2006, 2018 THALES GLOBAL SERVICES.
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

package org.polarsys.capella.common.ui.toolkit.viewers.transfer;

import java.util.Iterator;

import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Tree;

import org.polarsys.capella.common.ui.services.helper.AdapterManagerHelper;
import org.polarsys.capella.common.ui.toolkit.dialogs.TransferTreeListDialog;
import org.polarsys.capella.common.ui.toolkit.viewers.ITreeLabelAdapter;
import org.polarsys.capella.common.ui.toolkit.viewers.IViewerStyle;
import org.polarsys.capella.common.ui.toolkit.viewers.TreeAndListViewer;
import org.polarsys.capella.common.ui.toolkit.viewers.data.AbstractData;
import org.polarsys.capella.common.ui.toolkit.viewers.data.DataContentProvider;
import org.polarsys.capella.common.ui.toolkit.viewers.data.ListData;
import org.polarsys.capella.common.ui.toolkit.viewers.data.TreeData;

/**
 * Transfer Tree Viewer that displayed elements as a {@link Tree} or a {@link List} (i.e a flat tree) in both viewers.<br>
 * <br> {@link DataContentProvider}, {@link AbstractData}, {@link TreeData}, {@link ListData}
 */
public class TransferTreeListViewer extends AbstractTransferViewer2 {

  /**
   * Constructor.
   * @param parent
   * @param style
   * @param leftViewerStyleBits
   * @param rightViewerStyleBits
   */
  public TransferTreeListViewer(Composite parent, int style, int leftViewerStyleBits, int rightViewerStyleBits) {
    super(parent, style, leftViewerStyleBits, rightViewerStyleBits);
  }

  /**
   * Constructor.
   * @param parent
   * @param style
   * @param leftViewerStyleBits
   * @param rightViewerStyleBits
   * @param leftViewerExpandLevel
   * @param rightViewerExpandLevel
   */
  public TransferTreeListViewer(Composite parent, int style, int leftViewerStyleBits, int rightViewerStyleBits, int leftViewerExpandLevel, int rightViewerExpandLevel) {
    super(parent, style, leftViewerStyleBits, rightViewerStyleBits, leftViewerExpandLevel, rightViewerExpandLevel);
  }

  /**
   * Create the right {@link ListData} when the end-user changes the Tree view mode.
   * @param viewer viewer that the end-user wants to change the Tree view mode.
   * @param displayedElements
   * @param context
   * @return a not <code>null</code> instance.
   */
  protected ListData createListDataWhenTreeViewModeClicked(TreeViewer viewer, java.util.List<?> displayedElements, Object context) {
    return new ListData(displayedElements, context);
  }

  /**
   * Create the right {@link TreeData} when the end-user changes the Tree view mode.
   * @param viewer viewer that the end-user wants to change the Tree view mode.
   * @param displayedElements
   * @param context
   * @return a not <code>null</code> instance.
   */
  protected TreeData createTreeDataWhenTreeViewModeClicked(TreeViewer viewer, java.util.List<?> displayedElements, Object context) {
    return new TreeData(displayedElements, context);
  }

  /**
   * @see org.polarsys.capella.common.ui.toolkit.viewers.transfer.AbstractTransferViewer2#doHandleAddAllButton()
   */
  @Override
  protected boolean doHandleAddAllButton() {
    boolean changed = false;
    java.util.List<Object> validElements = getLeftInput().getValidElements();
    applyViewerFilters(getLeftViewer(), getLeftContentProvider(), validElements);
    if (!validElements.isEmpty()) {
      changed = true;
      Object[] elements = validElements.toArray();
      getLeftContentProvider().inputChanged(getLeftViewer(), elements, null);
      getRightContentProvider().inputChanged(getRightViewer(), null, elements);
    }
    return changed;
  }
  
  

  /**
   * @see org.polarsys.capella.common.ui.toolkit.viewers.transfer.AbstractTransferViewer2#doHandleRemoveAllButton()
   */
  @Override
  protected boolean doHandleRemoveAllButton() {
    boolean changed = false;
    java.util.List<Object> validElements = getRightInput().getValidElements();
    applyViewerFilters(getRightViewer(), getRightContentProvider(), validElements);
    if (!validElements.isEmpty()) {
      changed = true;
      Object[] elements = validElements.toArray();
      getLeftContentProvider().inputChanged(getLeftViewer(), null, elements);
      getRightContentProvider().inputChanged(getRightViewer(), elements, null);
    }
    return changed;
  }

  /**
   * @see org.polarsys.capella.common.ui.toolkit.viewers.transfer.AbstractTransferViewer2#doLeftViewer(org.eclipse.swt.widgets.Composite)
   */
  @Override
  protected TreeViewer doLeftViewer(Composite composite) {
    TreeAndListViewer treeViewer = new TreeAndListViewer(composite, (SWT.MULTI & getLeftViewerStyleBits()) != 0, IViewerStyle.SHOW_TREE_VIEW_MODE_BUTTON, _leftViewerExpandLevel) {
      @Override
      public String getContextMenuLocation() {
        return TransferTreeListDialog.TRANSFERT_TREELIST_DIALOG_LEFT;
      }
    };
    // treeViewer.setAutoExpandLevel(_leftViewerExpandLevel);
    // Register a double click listener to behave as a '>' button click.
    TreeViewer clientViewer = treeViewer.getClientViewer();
    clientViewer.addDoubleClickListener(new IDoubleClickListener() {
      /**
       * @see org.eclipse.jface.viewers.IDoubleClickListener#doubleClick(org.eclipse.jface.viewers.DoubleClickEvent)
       */
      public void doubleClick(DoubleClickEvent event) {
        // Handle the event if the related button is enabled (driven by its selection handlers).
        if (_addSelectedBtn.getEnabled()) {
          doHandleAddSelectedButton();
        }
      }
    });
    return clientViewer;
  }

  /**
   * @see org.polarsys.capella.common.ui.toolkit.viewers.transfer.AbstractTransferViewer2#doRightViewer(org.eclipse.swt.widgets.Composite)
   */
  @Override
  protected TreeViewer doRightViewer(Composite composite) {
    TreeAndListViewer treeViewer = new TreeAndListViewer(composite, (SWT.MULTI & getRightViewerStyleBits()) != 0, IViewerStyle.SHOW_TREE_VIEW_MODE_BUTTON, _rightViewerExpandLevel) {
      @Override
      public String getContextMenuLocation() {
        return TransferTreeListDialog.TRANSFERT_TREELIST_DIALOG_RIGHT;
      }
    };
    // treeViewer.setAutoExpandLevel(_rightViewerExpandLevel);
    // Register a double click listener to behave as a '<' button click.
    TreeViewer clientViewer = treeViewer.getClientViewer();
    clientViewer.addDoubleClickListener(new IDoubleClickListener() {
      /**
       * @see org.eclipse.jface.viewers.IDoubleClickListener#doubleClick(org.eclipse.jface.viewers.DoubleClickEvent)
       */
      public void doubleClick(DoubleClickEvent event) {
        // Handle the event if the related button is enabled (driven by its selection handlers).
        if (_removeSelectedBtn.getEnabled()) {
          doHandleRemoveSelectedButton();
        }
      }
    });
    return treeViewer.getClientViewer();
  }

  /**
   * @see org.polarsys.capella.common.ui.toolkit.viewers.transfer.AbstractTransferViewer2#getLeftContentProvider()
   */
  @Override
  public DataContentProvider getLeftContentProvider() {
    return (DataContentProvider) super.getLeftContentProvider();
  }

  /**
   * @see org.polarsys.capella.common.ui.toolkit.viewers.transfer.AbstractTransferViewer2#getLeftInput()
   */
  @Override
  public AbstractData getLeftInput() {
    return (AbstractData) super.getLeftInput();
  }

  /**
   * @see org.polarsys.capella.common.ui.toolkit.viewers.transfer.AbstractTransferViewer2#getLeftViewer()
   */
  @Override
  public TreeViewer getLeftViewer() {
    return (TreeViewer) super.getLeftViewer();
  }

  /**
   * @see org.polarsys.capella.common.ui.toolkit.viewers.transfer.AbstractTransferViewer2#getRightContentProvider()
   */
  @Override
  public DataContentProvider getRightContentProvider() {
    return (DataContentProvider) super.getRightContentProvider();
  }

  /**
   * @see org.polarsys.capella.common.ui.toolkit.viewers.transfer.AbstractTransferViewer2#getRightInput()
   */
  @Override
  public AbstractData getRightInput() {
    return (AbstractData) super.getRightInput();
  }

  /**
   * @see org.polarsys.capella.common.ui.toolkit.viewers.transfer.AbstractTransferViewer2#getRightViewer()
   */
  @Override
  public TreeViewer getRightViewer() {
    return (TreeViewer) super.getRightViewer();
  }

  /**
   * @see org.polarsys.capella.common.ui.toolkit.viewers.transfer.AbstractTransferViewer2#handleStatusLineUpdate(java.lang.Object,
   *      org.eclipse.jface.viewers.SelectionChangedEvent)
   */
  @Override
  protected String handleStatusLineUpdate(Object selectedElement, SelectionChangedEvent event) {
    String result = null;
    ITreeLabelAdapter treeLabelAdapter = (ITreeLabelAdapter) AdapterManagerHelper.getAdapter(selectedElement, ITreeLabelAdapter.class);
    if (null != treeLabelAdapter) {
      result = treeLabelAdapter.getFullLabel();
    } else {
      result = super.handleStatusLineUpdate(selectedElement, event);
    }
    return result;
  }
  
  // Only transfer elements that aren't filtered in the viewer
  // FIXME it seems the same filter is installed twice...
  protected void applyViewerFilters(StructuredViewer viewer, ITreeContentProvider provider, java.util.List<?> elements){
    for (Iterator<?> it = elements.iterator(); it.hasNext();){
      Object elem = it.next();
      filter:
        for (ViewerFilter filter : viewer.getFilters()){
          if (!filter.select(viewer, provider.getParent(elem), elem)){
            it.remove();
            break filter;
          }
        }
    }
  }

}
