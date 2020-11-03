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
package org.polarsys.capella.core.data.fa.ui.wizards.actions;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;

import org.polarsys.capella.core.data.fa.ui.wizards.dialogs.EIAllocationModelHelpers;

/**
 */
public abstract class DeleteElementAction extends Action {
  /** */
  protected TreeViewer treeViewer;

  /**
   * 
   */
  public DeleteElementAction(TreeViewer treeViewer) {
    super();
    this.treeViewer = treeViewer;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void run() {
    EIAllocationModelHelpers.handleDeletion(((IStructuredSelection)treeViewer.getSelection()).toList());
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
    return "Delete Element"; //$NON-NLS-1$
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
  @SuppressWarnings("unchecked")
  @Override
  public boolean isEnabled() {
    TreeItem[] selectedItems = treeViewer.getTree().getSelection();
    if (selectedItems.length == 1) {
      if (null == selectedItems[0].getParentItem()) {
        /** deletion is not allowed on root elements */
        return false;
      }
      if (EIAllocationModelHelpers.isValidTypeForDeletion(((IStructuredSelection)treeViewer.getSelection()).toList())) {
        return true;
      }
    }
    return false;
  }
}
