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
  
  public void update(int direction_p) {
    
    Table table = _column.getColumn().getParent();
    
    table.setSortColumn(_column.getColumn());
    _direction = direction_p;
    _viewer.setComparator(this);
      
      if( direction_p == ASC ) {
        table.setSortDirection(SWT.DOWN);
      } else {
        table.setSortDirection(SWT.UP);
      }
    
    _viewer.refresh();

    return;
  }

  @Override
  public int compare(Viewer viewer_p, Object e1_p, Object e2_p) {
    return _direction * doCompare(viewer_p, e1_p, e2_p);
  }
  
  protected abstract int doCompare(Viewer viewer_p, Object e1_p, Object e2_p);
}
