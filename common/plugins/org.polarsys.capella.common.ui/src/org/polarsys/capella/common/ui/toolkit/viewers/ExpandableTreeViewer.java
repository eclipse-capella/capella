/*******************************************************************************
 * Copyright (c) 2019 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.common.ui.toolkit.viewers;

import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.custom.BusyIndicator;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Tree;

/**
 * This class overrides default implementation of TreeViewer.internalInitializeTree and expandToLevel to disable
 * setRedraw before expanding tree, and displaying a showWhile cursor during whole expand process.
 * (BusyIndicator.showWhile is used intensively in internalExpandtoLevel.createChildren, so surrounding them by a global
 * one make sense and prevent twinkling)
 *
 * @see explanation on issue 522335 on eclipse.org
 */
public class ExpandableTreeViewer extends TreeViewer {

  public ExpandableTreeViewer(Composite parent) {
    super(parent);
  }

  public ExpandableTreeViewer(Tree tree) {
    super(tree);
  }

  public ExpandableTreeViewer(Composite parent, int style) {
    super(parent, style);
  }

  @Override
  protected void internalInitializeTree(Control widget) {
    BusyIndicator.showWhile(getControl().getDisplay(), () -> {
      try {
        getControl().setRedraw(false);
        super.internalInitializeTree(widget);
      } finally {
        getControl().setRedraw(true);
      }
    });
  }

  @Override
  public void expandToLevel(Object elementOrTreePath, int level) {
    if (checkBusy()) {
      return;
    }
    BusyIndicator.showWhile(getControl().getDisplay(), () -> {
      try {
        getControl().setRedraw(false);
        super.expandToLevel(elementOrTreePath, level);
      } finally {
        getControl().setRedraw(true);
      }
    });
  }
}
