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
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.polarsys.capella.common.ui.toolkit.viewers.data.AbstractData;
import org.polarsys.capella.common.ui.toolkit.viewers.data.DataLabelProvider;
import org.polarsys.capella.common.ui.toolkit.viewers.data.MultipleValidElementsTreeData;
import org.polarsys.capella.common.ui.toolkit.viewers.transfer.OrderedTransferTreeListViewer;
import org.polarsys.capella.common.ui.toolkit.viewers.transfer.TransferTreeListViewer;
import org.polarsys.capella.common.ui.toolkit.widgets.handler.SelectionChangedHandler;

/**
 * Dialog that displays a {@link OrderedTransferTreeListViewer}
 */
public class OrderedTransferTreeListDialog extends TransferTreeListDialog {

  public OrderedTransferTreeListDialog(Shell parentShell, String dialogTitle, String dialogMessage) {
    super(parentShell, dialogTitle, dialogMessage);
  }

  public OrderedTransferTreeListDialog(Shell parentShell, String dialogTitle, String dialogMessage,
    DataLabelProvider leftLabelProvider, DataLabelProvider rightLabelProvider,
    int leftViewerStyle, int rightViewerStyle)
  {
    super(parentShell, dialogTitle, dialogMessage, leftLabelProvider, rightLabelProvider, leftViewerStyle, rightViewerStyle);
  }

  /**
   * @see org.polarsys.capella.common.ui.toolkit.dialogs.TransferTreeListDialog#createRightViewerData(java.util.List, java.lang.Object)
   */
  @Override
  protected AbstractData createRightViewerData(List<? extends EObject> elements, Object context) {
    return new MultipleValidElementsTreeData(elements, context);
  }

  /**
   * @see org.polarsys.capella.common.ui.toolkit.dialogs.TransferTreeListDialog#createTransferTreeListViewer(org.eclipse.swt.widgets.Composite)
   */
  @Override
  protected TransferTreeListViewer createTransferTreeListViewer(Composite parent) {
    return new OrderedTransferTreeListViewer(parent, TRANSFER_TREE_STYLE, getLeftViewerStyle(), getRightViewerStyle());
  }

  /**
   * Handle selection enablement for OrderedTransferTreeListViewer.UP_BUTTON & OrderedTransferTreeListViewer.DOWN_BUTTON buttons.<br>
   * Default implementation checks selected elements are moveable i.e. a top index object can't move up and bottom index object can't move down.
   * @param input
   * @param selection
   * @param isMovingUp
   * @return <code>true</code> means valid, <code>false</code> if one selected element is not moveable.
   */
  protected boolean handleSelectionForUpAndDownButton(AbstractData input, ISelection selection, boolean isMovingUp) {
    boolean result = !selection.isEmpty();
    // Precondition: selection must contain something.
    if (!result) {
      return result;
    }
    // Since we can have many times the same elements in the Tree, we must used TreeItems rather than selection.
    Tree tree = getTransferViewer().getRightViewer().getTree();
    TreeItem[] treeItems = tree.getSelection();
    for (TreeItem treeItem : treeItems) {
      Object data = treeItem.getData();
      // Selected element must be valid to be moved.
      if (input.isValid(data)) {
        TreeItem parentItems = treeItem.getParentItem();
        // Handle tree and flat structure.
        int index = (null != parentItems) ? parentItems.indexOf(treeItem) : tree.indexOf(treeItem);
        // Handle border index.
        if ((index == 0) && isMovingUp) {
          // Move up an object at the top position is impossible.
          result = false;
          break;
        }
        // Handle tree and flat structure.
        int itemCount = (null != parentItems) ? parentItems.getItemCount() : tree.getItemCount();
        if ((index == (itemCount - 1)) && !isMovingUp) {
          // Move down an object at the last position is impossible.
          result = false;
          break;
        }
      } else {
        result = false;
        break;
      }
    }
    return result;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void initializeRightViewer(TransferTreeListViewer transferViewer) {
    super.initializeRightViewer(transferViewer);
    // Override the default sorter to enable up & down buttons.
    getTransferViewer().getRightViewer().setSorter(null);
  }

  /**
   * Register selection handlers that drive the transfer buttons.
   * @param transferViewer
   */
  @Override
  protected void registerSelectionHandler(final TransferTreeListViewer transferViewer) {
    // Register a selection handler to drive the Up button for right viewer.
    transferViewer.setSelectionChangedHandler(new SelectionChangedHandler() {
      @Override
      protected boolean doHandleSelection(ISelection selection) {
        return handleSelectionForUpAndDownButton(transferViewer.getRightInput(), selection, true);
      }
    }, OrderedTransferTreeListViewer.UP_BUTTON);
    // Register a selection handler to drive the Down button for right viewer.
    transferViewer.setSelectionChangedHandler(new SelectionChangedHandler() {
      @Override
      protected boolean doHandleSelection(ISelection selection) {
        return handleSelectionForUpAndDownButton(transferViewer.getRightInput(), selection, false);
      }
    }, OrderedTransferTreeListViewer.DOWN_BUTTON);
    super.registerSelectionHandler(transferViewer);
  }
}
