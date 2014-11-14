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
   * @param parent_p
   * @param style_p
   * @param leftViewerStyleBits_p
   * @param rightViewerStyleBits_p
   */
  public TransferTreeListViewer(Composite parent_p, int style_p, int leftViewerStyleBits_p, int rightViewerStyleBits_p) {
    super(parent_p, style_p, leftViewerStyleBits_p, rightViewerStyleBits_p);
  }

  /**
   * Constructor.
   * @param parent_p
   * @param style_p
   * @param leftViewerStyleBits_p
   * @param rightViewerStyleBits_p
   * @param leftViewerExpandLevel_p
   * @param rightViewerExpandLevel_p
   */
  public TransferTreeListViewer(Composite parent_p, int style_p, int leftViewerStyleBits_p, int rightViewerStyleBits_p, int leftViewerExpandLevel_p, int rightViewerExpandLevel_p) {
    super(parent_p, style_p, leftViewerStyleBits_p, rightViewerStyleBits_p, leftViewerExpandLevel_p, rightViewerExpandLevel_p);
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
  protected TreeViewer doLeftViewer(Composite composite_p) {
    TreeAndListViewer treeViewer = new TreeAndListViewer(composite_p, (SWT.MULTI & getLeftViewerStyleBits()) != 0, IViewerStyle.SHOW_TREE_VIEW_MODE_BUTTON, _leftViewerExpandLevel);
//    treeViewer.setAutoExpandLevel(_leftViewerExpandLevel);
    // Register a double click listener to behave as a '>' button click.
    TreeViewer clientViewer = treeViewer.getClientViewer();
    clientViewer.addDoubleClickListener(new IDoubleClickListener() {
      /**
       * @see org.eclipse.jface.viewers.IDoubleClickListener#doubleClick(org.eclipse.jface.viewers.DoubleClickEvent)
       */
      public void doubleClick(DoubleClickEvent event_p) {
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
  protected TreeViewer doRightViewer(Composite composite_p) {
    TreeAndListViewer treeViewer = new TreeAndListViewer(composite_p, (SWT.MULTI & getRightViewerStyleBits()) != 0, IViewerStyle.SHOW_TREE_VIEW_MODE_BUTTON, _rightViewerExpandLevel);
//    treeViewer.setAutoExpandLevel(_rightViewerExpandLevel);
    // Register a double click listener to behave as a '<' button click.
    TreeViewer clientViewer = treeViewer.getClientViewer();
    clientViewer.addDoubleClickListener(new IDoubleClickListener() {
      /**
       * @see org.eclipse.jface.viewers.IDoubleClickListener#doubleClick(org.eclipse.jface.viewers.DoubleClickEvent)
       */
      public void doubleClick(DoubleClickEvent event_p) {
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
  protected String handleStatusLineUpdate(Object selectedElement_p, SelectionChangedEvent event_p) {
    String result = null;
    ITreeLabelAdapter treeLabelAdapter = (ITreeLabelAdapter) AdapterManagerHelper.getAdapter(selectedElement_p, ITreeLabelAdapter.class);
    if (null != treeLabelAdapter) {
      result = treeLabelAdapter.getFullLabel();
    } else {
      result = super.handleStatusLineUpdate(selectedElement_p, event_p);
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
