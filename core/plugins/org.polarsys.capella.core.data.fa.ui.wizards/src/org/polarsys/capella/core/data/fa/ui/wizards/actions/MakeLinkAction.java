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
package org.polarsys.capella.core.data.fa.ui.wizards.actions;

import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.jface.viewers.TreeViewer;

import org.polarsys.capella.core.data.fa.ui.wizards.dialogs.EIAllocationModelHelpers;
import org.polarsys.capella.core.data.fa.ui.wizards.dialogs.LinkManager;

/**
 */
public class MakeLinkAction extends AbstractLinkAction {

  /**
   * 
   */
  public MakeLinkAction(LinkManager linkManager_p, TreeViewer treeViewer_p) {
    super(linkManager_p, treeViewer_p);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void run() {
    _linkManager.makeLinkTo(((TreeSelection) _treeViewer.getSelection()).toList());

    super.run();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String getText() {
    return "Make link from start"; //$NON-NLS-1$
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isEnabled() {
    if (super.isEnabled() && _linkManager.isStarted()) {
      if (EIAllocationModelHelpers.isCompatibleType(_linkManager.getStartedElements().get(0), ((TreeSelection) _treeViewer.getSelection()).getFirstElement())) {
        return true;
      }
    }
    return false;
  }
}
