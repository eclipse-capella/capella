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

import org.eclipse.jface.action.Action;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;

import org.polarsys.capella.core.data.fa.ui.wizards.dialogs.EIAllocationModelHelpers;
import org.polarsys.capella.core.data.fa.ui.wizards.dialogs.LinkManager;

/**
 */
public abstract class AbstractLinkAction extends Action {

  /** */
  protected TreeViewer treeViewer;
  protected LinkManager linkManager;

  /**
   * 
   */
  public AbstractLinkAction(LinkManager linkManager, TreeViewer treeViewer) {
    super();
    this.treeViewer = treeViewer;
    this.linkManager = linkManager;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void run() {
    postRun();
  }

  /**
   * 
   */
  protected void postRun() {
    // by default, do nothing
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ImageDescriptor getImageDescriptor() {
    return PlatformUI.getWorkbench().getSharedImages().getImageDescriptor(ISharedImages.IMG_ELCL_SYNCED);
  }

  /**
   * {@inheritDoc}
   */
  @SuppressWarnings("unchecked")
  @Override
  public boolean isEnabled() {
    TreeSelection selection = (TreeSelection) treeViewer.getSelection();
    if (EIAllocationModelHelpers.isSupportedType(selection.getFirstElement()) && EIAllocationModelHelpers.isSameType(selection.toList())) {
      return true;
    }
    return false;
  }
}
