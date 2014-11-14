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
  private final TreeViewer _sourceViewer;
  private final TreeViewer _targetViewer;

  /**
   * Default constructor
   */
  public EIAllocationDropAdapter(TreeViewer sourceViewer_p, TreeViewer targetViewer_p) {
    super(targetViewer_p);

    _sourceViewer = sourceViewer_p;
    _targetViewer = targetViewer_p;
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
            _targetViewer.refresh();
            _sourceViewer.refresh();
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
   * @param items_p
   * @return
   */
  boolean validateItemsType(TreeItem[] items_p) {
    for (TreeItem item : items_p) {
      if (!(item.getData() instanceof ExchangeItem)) {
        return false;
      }
    }
    return true;
  }
}
