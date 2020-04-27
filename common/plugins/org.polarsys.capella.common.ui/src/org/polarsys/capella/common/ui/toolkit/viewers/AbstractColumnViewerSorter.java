/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
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

package org.polarsys.capella.common.ui.toolkit.viewers;

import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerComparator;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Table;

/**
 * Basic column viewer sorter.
 *
 */
public abstract class AbstractColumnViewerSorter extends ViewerComparator {
  public static final int ASC = 1;
  
  public static final int DESC = -1;
  
  private int _direction = 0;
  
  private TableViewerColumn _column;
  
  private ColumnViewer _viewer;
  
  public int getDirection(){return _direction;}
  
  public AbstractColumnViewerSorter(ColumnViewer viewer, TableViewerColumn column) {
    
    _column = column;
    _viewer = viewer;
    
    return;
  }
  
  public void update() {
      switch(_direction) {
      case ASC: 
        update(DESC);
        break;
      case DESC:
        update(ASC);
        break;   
      default:
        update(ASC);
    }
    
    return;
  }
  
  public void update(int direction) {
    
    Table table = _column.getColumn().getParent();
    
    table.setSortColumn(_column.getColumn());
    _direction = direction;
    _viewer.setComparator(this);
      
      if( direction == ASC ) {
        table.setSortDirection(SWT.DOWN);
      } else {
        table.setSortDirection(SWT.UP);
      }
    
    _viewer.refresh();

    return;
  }

  @Override
  public int compare(Viewer viewer, Object e1, Object e2) {
    return _direction * doCompare(viewer, e1, e2);
  }
  
  protected abstract int doCompare(Viewer viewer, Object e1, Object e2);
}
