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

import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.ViewerColumn;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.ui.PlatformUI;

import org.polarsys.capella.core.transition.common.handlers.filter.AbstractFilterItem.FilterAction;
import org.polarsys.capella.core.transition.common.handlers.log.IDiffModelViewer;

public class DiffTreeColumnLabelProvider extends ColumnLabelProvider {
  protected Color USER_CHOICE = null;
  protected Color READONLY = null;

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.jface.viewers.ColumnLabelProvider#getBackground(java.lang.Object)
   */
  @Override
  public Color getBackground(Object element) {
    IDiffModelViewer diff = (IDiffModelViewer) element;

    if ((diff.getActionDiff() != diff.getDefaultActionDiff())) {
      return USER_CHOICE;

    } else if (diff.getActionDiff() == FilterAction.NO_ACTION) {
      return READONLY;
    }

    return super.getBackground(element);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void initialize(ColumnViewer viewer_p, ViewerColumn column_p) {
    super.initialize(viewer_p, column_p);
    USER_CHOICE = new Color(PlatformUI.getWorkbench().getDisplay(), new RGB(230, 230, 240));
    READONLY = new Color(PlatformUI.getWorkbench().getDisplay(), new RGB(255, 255, 255));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void dispose() {
    if (USER_CHOICE != null) {
      USER_CHOICE.dispose();
    }
    if (READONLY != null) {
      READONLY.dispose();
    }
    super.dispose();
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.jface.viewers.CellLabelProvider#getToolTipShift(java.lang.Object)
   */
  @Override
  public Point getToolTipShift(Object object) {
    return new Point(8, 14);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.jface.viewers.CellLabelProvider#getToolTipDisplayDelayTime(java.lang.Object)
   */
  @Override
  public int getToolTipDisplayDelayTime(Object object) {
    return 500;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.jface.viewers.CellLabelProvider#getToolTipTimeDisplayed(java.lang.Object)
   */
  @Override
  public int getToolTipTimeDisplayed(Object object) {
    return 10000;
  }

}
