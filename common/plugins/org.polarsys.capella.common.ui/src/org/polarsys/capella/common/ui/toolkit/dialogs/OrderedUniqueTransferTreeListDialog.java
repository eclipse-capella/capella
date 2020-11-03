/*******************************************************************************
 * Copyright (c) 2016, 2020 THALES GLOBAL SERVICES.
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
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;
import org.polarsys.capella.common.ui.toolkit.viewers.data.AbstractData;
import org.polarsys.capella.common.ui.toolkit.viewers.data.DataLabelProvider;
import org.polarsys.capella.common.ui.toolkit.viewers.data.UniqueValidElementsTreeData;
import org.polarsys.capella.common.ui.toolkit.viewers.transfer.OrderedTransferTreeListViewer;
import org.polarsys.capella.common.ui.toolkit.viewers.transfer.TransferTreeListViewer;

/**
 * Dialog that displays a {@link OrderedTransferTreeListViewer} and does not accept duplicated elements
 */
public class OrderedUniqueTransferTreeListDialog extends OrderedTransferTreeListDialog {

  public OrderedUniqueTransferTreeListDialog(Shell parentShell, String dialogTitle, String dialogMessage) {
    super(parentShell, dialogTitle, dialogMessage);
  }

  public OrderedUniqueTransferTreeListDialog(Shell parentShell, String dialogTitle, String dialogMessage,
      DataLabelProvider leftLabelProvider, DataLabelProvider rightLabelProvider, int leftViewerStyle,
      int rightViewerStyle) {
    super(parentShell, dialogTitle, dialogMessage, leftLabelProvider, rightLabelProvider, leftViewerStyle,
        rightViewerStyle);
  }

  @Override
  protected AbstractData createRightViewerData(List<? extends EObject> elements, Object context) {
    return new UniqueValidElementsTreeData(elements, context);
  }

  @Override
  protected TransferTreeListViewer createTransferTreeListViewer(Composite parent) {
    return new OrderedTransferTreeListViewer(parent, TRANSFER_TREE_STYLE, getLeftViewerStyle(), getRightViewerStyle()) {
      @Override
      protected boolean shouldChangeLeftViewer() {
        // We want to change the left viewer
        return true;
      }
    };
  }
}
