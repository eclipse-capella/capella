/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
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
import org.polarsys.capella.common.mdsofa.common.helper.MiscHelper;
import org.polarsys.capella.common.ui.services.helper.ViewerHelper;
import org.polarsys.capella.common.ui.toolkit.viewers.data.IMoveableData;
import org.polarsys.capella.common.ui.toolkit.viewers.data.ListData;
import org.polarsys.capella.common.ui.toolkit.viewers.data.MultipleValidElementsListData;
import org.polarsys.capella.common.ui.toolkit.viewers.data.MultipleValidElementsTreeData;
import org.polarsys.capella.common.ui.toolkit.viewers.data.TreeData;
import org.polarsys.capella.common.ui.toolkit.widgets.handler.SelectionChangedHandler;

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
   * @param parent
   * @param style
   * @param leftViewerStyleBits
   * @param rightViewerStyleBits
   */
  public OrderedTransferTreeListViewer(Composite parent, int style, int leftViewerStyleBits, int rightViewerStyleBits) {
    super(parent, style, leftViewerStyleBits, rightViewerStyleBits);
  }

  /**
   * @see org.polarsys.capella.common.ui.toolkit.viewers.transfer.AbstractTransferViewer2#createButtonArea(org.eclipse.swt.widgets.Composite)
   */
  @Override
  protected void createButtonArea(Composite parent) {
    super.createButtonArea(parent);
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
      if (shouldChangeLeftViewer())
      {
        getLeftViewer().getContentProvider().inputChanged(getLeftViewer(), elements, null);
      }
      rightViewer.getContentProvider().inputChanged(rightViewer, null, elements);
    }
    return changed;
  }

  protected boolean shouldChangeLeftViewer() {
    // We do not change the left viewer
    return false;
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
  protected void swapElements(List<TreeItem> treeItems, boolean isMovingUp) {
    TreeViewer rightViewer = getRightViewer();
    Tree tree = rightViewer.getTree();
    // A list that holds the selection after the move operation.
    // When an element is contained many times by a node in a tree viewer, after the refresh the tree viewer is lost (bug in AbstractTreeViewer) regarding the
    // selection.
    List<TreeItem> newlySelectedElements = new ArrayList<TreeItem>(treeItems.size());
    for (TreeItem treeItem : treeItems) {
      TreeItem parentItem = treeItem.getParentItem();
      // Handle tree and flat data structure.
      int index = (null != parentItem) ? parentItem.indexOf(treeItem) : tree.indexOf(treeItem);
      int newIndex = index + ((isMovingUp) ? -1 : 1);
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
  protected void handleButtonClicked(Widget button) {
    if (button == _upButton) {
      doHandleUpButton();
    } else if (button == _downButton) {
      doHandleDownButton();
    } else {
      super.handleButtonClicked(button);
    }
  }

  /**
   * @see org.polarsys.capella.common.ui.toolkit.viewers.transfer.TransferTreeListViewer#createListDataWhenTreeViewModeClicked(org.eclipse.jface.viewers.TreeViewer,
   *      java.util.List, java.lang.Object)
   */
  @Override
  protected ListData createListDataWhenTreeViewModeClicked(TreeViewer viewer, List<? extends Object> displayedElements, Object context) {
    // Only the TreeData for the right viewer is changed.
    if (viewer == getRightViewer()) {
      return new MultipleValidElementsListData(displayedElements, context);
    }
    return super.createListDataWhenTreeViewModeClicked(viewer, displayedElements, context);
  }

  /**
   * @see org.polarsys.capella.common.ui.toolkit.viewers.transfer.TransferTreeListViewer#createTreeDataWhenTreeViewModeClicked(org.eclipse.jface.viewers.TreeViewer,
   *      java.util.List, java.lang.Object)
   */
  @Override
  protected TreeData createTreeDataWhenTreeViewModeClicked(TreeViewer viewer, List<? extends Object> displayedElements, Object context) {
    // Only the TreeData for the right viewer is changed.
    if (viewer == getRightViewer()) {
      return new MultipleValidElementsTreeData(displayedElements, context);
    }
    return super.createTreeDataWhenTreeViewModeClicked(viewer, displayedElements, context);
  }

  /**
   * @see org.polarsys.capella.common.ui.toolkit.viewers.transfer.AbstractTransferViewer2#setSelectionChangedHandler(org.polarsys.capella.common.ui.toolkit.widgets.handler.SelectionChangedHandler,
   *      int)
   */
  @Override
  public void setSelectionChangedHandler(SelectionChangedHandler handler, int buttonStyleConstant) {
    super.setSelectionChangedHandler(handler, buttonStyleConstant);
    if ((null != _upButton) && (UP_BUTTON & buttonStyleConstant) != 0) {
      handler.addControl(_upButton);
      getSelectionChangedHandlersForRightViewer().add(handler);
    }
    if ((null != _downButton) && (DOWN_BUTTON & buttonStyleConstant) != 0) {
      handler.addControl(_downButton);
      getSelectionChangedHandlersForRightViewer().add(handler);
    }
  }
}
