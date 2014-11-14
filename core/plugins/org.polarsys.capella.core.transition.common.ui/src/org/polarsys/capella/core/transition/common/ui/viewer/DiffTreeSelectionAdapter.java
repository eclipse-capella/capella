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
package org.polarsys.capella.core.transition.common.ui.viewer;

import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.TreeColumn;

public class DiffTreeSelectionAdapter extends SelectionAdapter {

  private DiffTreeSorter ts;

  private int columnIndex;

  private TreeViewer viewer;

  private TreeColumn column;

  public DiffTreeSelectionAdapter(DiffTreeSorter pTs, TreeColumn pTc, int pColIndex, TreeViewer pTv) {
    ts = pTs;
    columnIndex = pColIndex;
    viewer = pTv;
    column = pTc;
  }

  @Override
  public void widgetSelected(SelectionEvent e) {
    ts.setColumn(columnIndex);

    int dir = viewer.getTree().getSortDirection();
    if (viewer.getTree().getSortColumn() == column) {
      dir = dir == SWT.UP ? SWT.DOWN : SWT.UP;
    } else {
      dir = SWT.UP;
    }
    viewer.getTree().setSortDirection(dir);
    viewer.getTree().setSortColumn(column);
    viewer.refresh();
  }

}
