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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.swt.widgets.Widget;

import org.polarsys.capella.common.ui.services.helper.ViewerHelper;
import org.polarsys.capella.common.ui.toolkit.viewers.data.IMoveableData;
import org.polarsys.capella.common.ui.toolkit.viewers.data.ListData;
import org.polarsys.capella.common.ui.toolkit.viewers.data.MultipleValidElementsListData;
import org.polarsys.capella.common.ui.toolkit.viewers.data.MultipleValidElementsTreeData;
import org.polarsys.capella.common.ui.toolkit.viewers.data.TreeData;
import org.polarsys.capella.common.ui.toolkit.widgets.handler.SelectionChangedHandler;
import org.polarsys.capella.common.mdsofa.common.helper.MiscHelper;

/**
 * {@link TransferTreeListViewer} with 2 buttons (Up & Down) to arrange the order of transfered elements.<br>
 * This viewer supports multiple add of same elements.
 */
public class OrderedTransferTreeListViewer extends TransferTreeListViewer {
  /**
   * Style constant for displaying the 'Up' button.
   */
  public static final int UP_BUTTON = 1 << 10;
  /**
   * Style constant for displaying the 'Down' button.
   */
  public static final int DOWN_BUTTON = 1 << 11;
  /**
   * Up button.
   */
  private Button _upButton;
  /**
   * Down button.
   */
  private Button _downButton;

  /**
   * Constructor.
   * @param parent_p
   * @param style_p
   * @param leftViewerStyleBits_p
   * @param rightViewerStyleBits_p
   */
  public OrderedTransferTreeListViewer(Composite parent_p, int style_p, int leftViewerStyleBits_p, int rightViewerStyleBits_p) {
    super(parent_p, style_p, leftViewerStyleBits_p, rightViewerStyleBits_p);
  }

  /**
   * @see org.polarsys.capella.common.ui.toolkit.viewers.transfer.AbstractTransferViewer2#createButtonArea(org.eclipse.swt.widgets.Composite)
   */
  @Override
  protected void createButtonArea(Composite parent_p) {
    super.createButtonArea(parent_p);
    _upButton = createButton(getButtonsContainer(), Messages.OrderedTransferTreeListViewer_Up_Title, Messages.OrderedTransferTreeListViewer_Up_Tooltip);
    _downButton = createButton(getButtonsContainer(), Messages.OrderedTransferTreeListViewer_Down_Title, Messages.OrderedTransferTreeListViewer_Down_Tooltip);
  }

  /**
   * @see org.polarsys.capella.common.ui.toolkit.viewers.transfer.AbstractTransferViewer2#doHandleAddSelectedButton()
   */
  @Override
  protected boolean doHandleAddSelectedButton() {
    boolean changed = false;
    ISelection leftSelection = getLeftViewer().getSelection();
    if (!leftSelection.isEmpty()) {
      changed = true;
      IStructuredSelection structuredSelection = (IStructuredSelection) leftSelection;
      Object[] elements = structuredSelection.toArray();
      TreeViewer rightViewer = getRightViewer();
      // Update the right viewer.
      // But don't change anything on the left viewer as we want to be able to add many times a same element.
      rightViewer.getContentProvider().inputChanged(rightViewer, null, elements);
    }
    return changed;
  }

  /**
   * Handle the "Down" button click.
   * @return <code>True</code> if a change occurs else <code>false</code>.
   */
  protected boolean doHandleDownButton() {
    Tree tree = getRightViewer().getTree();
    List<TreeItem> treeItems = MiscHelper.asList(tree.getSelection());
    // To move down elements must be processed in the reverse order to have correct indexes with a many selection.
    Collections.reverse(treeItems);
    swapElements(treeItems, false);
    return true;
  }

  /**
   * Handle the "Up" button click.
   * @return <code>True</code> if a change occurs else <code>false</code>.
   */
  protected boolean doHandleUpButton() {
    Tree tree = getRightViewer().getTree();
    swapElements(MiscHelper.asList(tree.getSelection()), true);
    return true;
  }

  /**
   * Swap tree items.
   */
  protected void swapElements(List<TreeItem> treeItems_p, boolean isMovingUp_p) {
    TreeViewer rightViewer = getRightViewer();
    Tree tree = rightViewer.getTree();
    // A list that holds the selection after the move operation.
    // When an element is contained many times by a node in a tree viewer, after the refresh the tree viewer is lost (bug in AbstractTreeViewer) regarding the
    // selection.
    List<TreeItem> newlySelectedElements = new ArrayList<TreeItem>(treeItems_p.size());
    for (TreeItem treeItem : treeItems_p) {
      TreeItem parentItem = treeItem.getParentItem();
      // Handle tree and flat data structure.
      int index = (null != parentItem) ? parentItem.indexOf(treeItem) : tree.indexOf(treeItem);
      int newIndex = index + ((isMovingUp_p) ? -1 : 1);
      ((IMoveableData) getRightInput()).swap(treeItem.getData(), index, newIndex);
      // Treak : Add the TreeItem that will host the element.
      TreeItem selectedItem = (null != parentItem) ? parentItem.getItem(newIndex) : tree.getItem(newIndex);
      newlySelectedElements.add(selectedItem);
    }
    // Refresh the viewer to get UI changes according to model changes.
    ViewerHelper.refresh(rightViewer);
    // Set the selection to the right items due to issues with a node that contains many times a same element.
    tree.setSelection(newlySelectedElements.toArray(new TreeItem[newlySelectedElements.size()]));
    // Force to refresh the Up & Down buttons.
    updateButtons(rightViewer.getSelection(), rightViewer);
  }

  /**
   * @see org.polarsys.capella.common.ui.toolkit.viewers.transfer.AbstractTransferViewer2#handleButtonClicked(org.eclipse.swt.widgets.Widget)
   */
  @Override
  protected void handleButtonClicked(Widget button_p) {
    if (button_p == _upButton) {
      doHandleUpButton();
    } else if (button_p == _downButton) {
      doHandleDownButton();
    } else {
      super.handleButtonClicked(button_p);
    }
  }

  /**
   * @see org.polarsys.capella.common.ui.toolkit.viewers.transfer.TransferTreeListViewer#createListDataWhenTreeViewModeClicked(org.eclipse.jface.viewers.TreeViewer,
   *      java.util.List, java.lang.Object)
   */
  @Override
  protected ListData createListDataWhenTreeViewModeClicked(TreeViewer viewer_p, List<? extends Object> displayedElements_p, Object context_p) {
    // Only the TreeData for the right viewer is changed.
    if (viewer_p == getRightViewer()) {
      return new MultipleValidElementsListData(displayedElements_p, context_p);
    }
    return super.createListDataWhenTreeViewModeClicked(viewer_p, displayedElements_p, context_p);
  }

  /**
   * @see org.polarsys.capella.common.ui.toolkit.viewers.transfer.TransferTreeListViewer#createTreeDataWhenTreeViewModeClicked(org.eclipse.jface.viewers.TreeViewer,
   *      java.util.List, java.lang.Object)
   */
  @Override
  protected TreeData createTreeDataWhenTreeViewModeClicked(TreeViewer viewer_p, List<? extends Object> displayedElements_p, Object context_p) {
    // Only the TreeData for the right viewer is changed.
    if (viewer_p == getRightViewer()) {
      return new MultipleValidElementsTreeData(displayedElements_p, context_p);
    }
    return super.createTreeDataWhenTreeViewModeClicked(viewer_p, displayedElements_p, context_p);
  }

  /**
   * @see org.polarsys.capella.common.ui.toolkit.viewers.transfer.AbstractTransferViewer2#setSelectionChangedHandler(org.polarsys.capella.common.ui.toolkit.widgets.handler.SelectionChangedHandler,
   *      int)
   */
  @Override
  public void setSelectionChangedHandler(SelectionChangedHandler handler_p, int buttonStyleConstant_p) {
    super.setSelectionChangedHandler(handler_p, buttonStyleConstant_p);
    if ((null != _upButton) && (UP_BUTTON & buttonStyleConstant_p) != 0) {
      handler_p.addControl(_upButton);
      getSelectionChangedHandlersForRightViewer().add(handler_p);
    }
    if ((null != _downButton) && (DOWN_BUTTON & buttonStyleConstant_p) != 0) {
      handler_p.addControl(_downButton);
      getSelectionChangedHandlersForRightViewer().add(handler_p);
    }
  }
}
