/*******************************************************************************
 * Copyright (c) 2006, 2017 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.data.fa.ui.wizards.actions;

import java.util.Map;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;

import org.polarsys.capella.core.data.fa.FunctionInputPort;
import org.polarsys.capella.core.data.fa.FunctionOutputPort;
import org.polarsys.capella.core.data.fa.ui.wizards.dialogs.EIAllocationTreeViewer;
import org.polarsys.capella.core.data.information.ExchangeItem;

/**
 */
public abstract class RemoveElementAction extends Action {
  /** */
  protected TreeViewer treeViewer;

  /**
   * 
   */
  public RemoveElementAction(TreeViewer treeViewer) {
    super();
    this.treeViewer = treeViewer;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void run() {
    Map<Object, Object> selectionMap = EIAllocationTreeViewer.getSelectionMap(treeViewer);
    for (Object selectedItem : selectionMap.keySet()) {
      Object parentItem = selectionMap.get(selectedItem);
      if (null != parentItem) {
        if (parentItem instanceof FunctionInputPort) {
          ((FunctionInputPort) parentItem).getIncomingExchangeItems().remove(selectedItem);
        } else if (parentItem instanceof FunctionOutputPort) {
          ((FunctionOutputPort) parentItem).getOutgoingExchangeItems().remove(selectedItem);
        }
      }
    }

    postRun();
  }

  /**
   * 
   */
  protected abstract void postRun();

  /**
   * {@inheritDoc}
   */
  @Override
  public String getText() {
    return "Remove ExchangeItem Allocation"; //$NON-NLS-1$
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ImageDescriptor getImageDescriptor() {
    return PlatformUI.getWorkbench().getSharedImages().getImageDescriptor(ISharedImages.IMG_TOOL_DELETE);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isEnabled() {
    IStructuredSelection selection = ((IStructuredSelection)treeViewer.getSelection());
    if (selection != null && selection.size() == 1) {
      return (selection.getFirstElement() instanceof ExchangeItem);
    }
    return false;
  }
}
