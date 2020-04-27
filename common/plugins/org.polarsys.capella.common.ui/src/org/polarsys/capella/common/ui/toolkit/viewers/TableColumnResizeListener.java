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

import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.ControlListener;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;

/**
 *
 */
public class TableColumnResizeListener implements ControlListener {
  
  private TableColumn _tableColumn;
  int _minimumWidth;
  
  public TableColumnResizeListener(TableColumn tableColumn) {
    this._tableColumn = tableColumn;
    this._minimumWidth = getMinimumColumnWidth(_tableColumn, _tableColumn.getText());
  }
  

  /**
   * @see org.eclipse.swt.events.ControlListener#controlMoved(org.eclipse.swt.events.ControlEvent)
   */
  public void controlMoved(ControlEvent e) {
    // do nothing
  }

  /**
   * @see org.eclipse.swt.events.ControlListener#controlResized(org.eclipse.swt.events.ControlEvent)
   */
  public void controlResized(ControlEvent e) {
    if(_tableColumn.getWidth() < _minimumWidth)
      _tableColumn.pack();
  }
  
//setting min width for a column
  private int getMinimumColumnWidth(TableColumn column, String columnName) {
    Table table = column.getParent();
    GC gc = new GC(table);
    gc.setFont(table.getFont());
    int averageWidth = gc.getFontMetrics().getAverageCharWidth();
    // TODO you need to get the max size of the element labels contained into the column => you can make a recursive check into the label provider.
    int columnSize = averageWidth * columnName.length();
    return columnSize;
  }

}
