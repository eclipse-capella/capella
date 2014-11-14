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
package org.polarsys.capella.common.ui.toolkit.dialogs;

import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
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
  /**
   * Constructor.
   * @param parentShell_p
   * @param editingDomain_p
   * @param adapterFactory_p
   * @param dialogTitle_p
   * @param dialogMessage_p
   */
  public OrderedTransferTreeListDialog(Shell parentShell_p, TransactionalEditingDomain editingDomain_p, AdapterFactory adapterFactory_p, String dialogTitle_p, String dialogMessage_p) {
    super(parentShell_p, dialogTitle_p, dialogMessage_p, editingDomain_p, adapterFactory_p);
  }

  /**
   * Constructor.
   * @param parentShell_p
   * @param dialogTitle_p
   * @param dialogMessage_p
   * @param leftLabelProvider_p
   * @param rightLabelProvider_p
   * @param leftViewerStyle_p
   * @param rightViewerStyle_p
   */
  public OrderedTransferTreeListDialog(Shell parentShell_p, String dialogTitle_p, String dialogMessage_p,
    DataLabelProvider leftLabelProvider_p, DataLabelProvider rightLabelProvider_p,
    int leftViewerStyle_p, int rightViewerStyle_p)
  {
    super(parentShell_p, dialogTitle_p, dialogMessage_p, leftLabelProvider_p, rightLabelProvider_p, leftViewerStyle_p, rightViewerStyle_p);
  }

  /**
   * @see org.polarsys.capella.common.ui.toolkit.dialogs.TransferTreeListDialog#createRightViewerData(java.util.List, java.lang.Object)
   */
  @Override
  protected AbstractData createRightViewerData(List<? extends EObject> elements_p, Object context_p) {
    return new MultipleValidElementsTreeData(elements_p, context_p);
  }

  /**
   * @see org.polarsys.capella.common.ui.toolkit.dialogs.TransferTreeListDialog#createTransferTreeListViewer(org.eclipse.swt.widgets.Composite)
   */
  @Override
  protected TransferTreeListViewer createTransferTreeListViewer(Composite parent_p) {
    return new OrderedTransferTreeListViewer(parent_p, TRANSFER_TREE_STYLE, getLeftViewerStyle(), getRightViewerStyle());
  }

  /**
   * Handle selection enablement for OrderedTransferTreeListViewer.UP_BUTTON & OrderedTransferTreeListViewer.DOWN_BUTTON buttons.<br>
   * Default implementation checks selected elements are moveable i.e. a top index object can't move up and bottom index object can't move down.
   * @param rightInput_p
   * @param selection_p
   * @param isMovingUp_p
   * @return <code>true</code> means valid, <code>false</code> if one selected element is not moveable.
   */
  protected boolean handleSelectionForUpAndDownButton(AbstractData input_p, ISelection selection_p, boolean isMovingUp_p) {
    boolean result = !selection_p.isEmpty();
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
      if (input_p.isValid(data)) {
        TreeItem parentItems = treeItem.getParentItem();
        // Handle tree and flat structure.
        int index = (null != parentItems) ? parentItems.indexOf(treeItem) : tree.indexOf(treeItem);
        // Handle border index.
        if ((index == 0) && isMovingUp_p) {
          // Move up an object at the top position is impossible.
          result = false;
          break;
        }
        // Handle tree and flat structure.
        int itemCount = (null != parentItems) ? parentItems.getItemCount() : tree.getItemCount();
        if ((index == (itemCount - 1)) && !isMovingUp_p) {
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
  protected void initializeRightViewer(TransferTreeListViewer transferViewer_p) {
    super.initializeRightViewer(transferViewer_p);
    // Override the default sorter to enable up & down buttons.
    getTransferViewer().getRightViewer().setSorter(null);
  }

  /**
   * Register selection handlers that drive the transfer buttons.
   * @param transferViewer_p
   */
  @Override
  protected void registerSelectionHandler(final TransferTreeListViewer transferViewer_p) {
    // Register a selection handler to drive the Up button for right viewer.
    transferViewer_p.setSelectionChangedHandler(new SelectionChangedHandler() {
      @Override
      protected boolean doHandleSelection(ISelection selection_p) {
        return handleSelectionForUpAndDownButton(transferViewer_p.getRightInput(), selection_p, true);
      }
    }, OrderedTransferTreeListViewer.UP_BUTTON);
    // Register a selection handler to drive the Down button for right viewer.
    transferViewer_p.setSelectionChangedHandler(new SelectionChangedHandler() {
      @Override
      protected boolean doHandleSelection(ISelection selection_p) {
        return handleSelectionForUpAndDownButton(transferViewer_p.getRightInput(), selection_p, false);
      }
    }, OrderedTransferTreeListViewer.DOWN_BUTTON);
    super.registerSelectionHandler(transferViewer_p);
  }
}
