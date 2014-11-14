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

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerSorter;

import org.polarsys.capella.core.transition.common.handlers.log.DiffModelViewer;
import org.polarsys.capella.core.transition.common.handlers.log.IDiffModelViewer;

public class DiffTreeSorter extends ViewerSorter {
  private int propertyIndex;

  private static final int ASCENDING = 0;

  private static final int DESCENDING = 1;

  private int direction = ASCENDING;

  public DiffTreeSorter() {
    this.propertyIndex = 0;

  }

  public void setColumn(int column) {
    if (column == this.propertyIndex) {
      // Same column as last sort; toggle the direction
      direction = 1 - direction;
    } else {
      // New column; do an ascending sort
      this.propertyIndex = column;
      direction = ASCENDING;
    }
  }

  @Override
  public int compare(Viewer viewer, Object e1, Object e2) {
    int rc = 0;

    if ((e1 instanceof DiffModelViewer) && (e2 instanceof DiffModelViewer)) {
      IDiffModelViewer p1 = (IDiffModelViewer) e1;
      IDiffModelViewer p2 = (IDiffModelViewer) e2;

      switch (propertyIndex) {
        case DiffTreeViewer.COL_DIFF:
          rc = p1.getTextDiff().compareTo(p2.getTextDiff());
        break;
        case DiffTreeViewer.COL_ACTION:
          rc = p1.getActionDiff().name().compareTo(p2.getActionDiff().name());
        break;
        case DiffTreeViewer.COL_TYPE:
          rc = p1.getTypeDiff().compareTo(p2.getTypeDiff());
        break;
        case DiffTreeViewer.COL_ELT:
          rc = p1.getElementDiff().compareTo(p2.getElementDiff());
        break;
        case DiffTreeViewer.COL_TRACEABILITY:
          rc = p1.getTraceability().compareTo(p2.getTraceability());
        break;
        default:
          rc = 0;
      }
      // If descending order, flip the direction
      if (direction == DESCENDING) {
        rc = -rc;
      }
    }
    return rc;
  }
}
