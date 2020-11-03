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
package org.polarsys.capella.core.data.fa.ui.wizards.dialogs;

import org.eclipse.emf.edit.ui.dnd.LocalTransfer;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.ViewerDropAdapter;
import org.eclipse.swt.dnd.TransferData;
import org.eclipse.swt.widgets.TreeItem;

import org.polarsys.capella.core.data.fa.FunctionPort;
import org.polarsys.capella.core.data.information.ExchangeItem;

/**
 */
public class EIAllocationDropAdapter extends ViewerDropAdapter {
  /** */
  private final TreeViewer sourceViewer;
  private final TreeViewer targetViewer;

  /**
   * Default constructor
   */
  public EIAllocationDropAdapter(TreeViewer sourceViewer, TreeViewer targetViewer) {
    super(targetViewer);

    this.sourceViewer = sourceViewer;
    this.targetViewer = targetViewer;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean performDrop(Object data) {
    Object target = getCurrentEvent().item.getData();
    if (data instanceof TreeItem[] && validateItemsType((TreeItem[]) data) && target instanceof FunctionPort) {
      for (TreeItem item : (TreeItem[]) data) {
        TreeItem parent = item.getParentItem();
        if (null != parent) {
          Object parentData = parent.getData();
          if (parentData instanceof FunctionPort) {
            EIAllocationModelHelpers.handleAllocation((FunctionPort) parentData, (FunctionPort) target, (ExchangeItem) item.getData());
            targetViewer.refresh();
            sourceViewer.refresh();
          }
        }
      }
      return true;
    }
    return false;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean validateDrop(Object target, int operation, TransferData transferType) {
    Object source = LocalTransfer.getInstance().nativeToJava(transferType);
    if (getCurrentLocation() == LOCATION_ON
     && source instanceof TreeItem[] && validateItemsType((TreeItem[]) source)
     && target instanceof FunctionPort)
    {
      return true;
    }
    return false;
  }

  /**
   * @param items
   * @return
   */
  boolean validateItemsType(TreeItem[] items) {
    for (TreeItem item : items) {
      if (!(item.getData() instanceof ExchangeItem)) {
        return false;
      }
    }
    return true;
  }
}
