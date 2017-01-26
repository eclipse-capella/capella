/*******************************************************************************
 * Copyright (c) 2006, 2017 THALES GLOBAL SERVICES.
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

import org.polarsys.capella.core.data.fa.ui.wizards.dialogs.LinkManager;

/**
 */
public class StartLinkAction extends AbstractLinkAction {

  /**
   * 
   */
  public StartLinkAction(LinkManager linkManager, TreeViewer treeViewer) {
    super(linkManager, treeViewer);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void run() {
    linkManager.startLinkFrom(((TreeSelection) treeViewer.getSelection()).toList());

    super.run();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String getText() {
    return "Start link"; //$NON-NLS-1$
  }
}
