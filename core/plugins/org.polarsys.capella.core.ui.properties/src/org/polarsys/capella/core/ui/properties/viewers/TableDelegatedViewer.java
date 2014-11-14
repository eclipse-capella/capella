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
package org.polarsys.capella.core.ui.properties.viewers;

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;

import org.polarsys.capella.core.ui.toolkit.viewers.CapellaElementLabelProvider;

/**
 */
public class TableDelegatedViewer extends AbstractDelegatedViewer {

  protected Table _table;

  /**
   * Constructor.
   * @param widgetFactory_p
   */
  public TableDelegatedViewer(TabbedPropertySheetWidgetFactory widgetFactory_p) {
    this(widgetFactory_p, null);
  }

  /**
   * Constructor.
   * @param widgetFactory_p
   * @param cellEditorProvider_p
   */
  public TableDelegatedViewer(TabbedPropertySheetWidgetFactory widgetFactory_p, ICellEditorProvider cellEditorProvider_p) {
    super(widgetFactory_p, cellEditorProvider_p);
  }

  /**
   * {@inheritDoc}
   */
  public void createContainer(Composite parent_p) {
    _table = _widgetFactory.createTable(getViewerGroup(parent_p), SWT.MULTI | SWT.BORDER | SWT.FULL_SELECTION);
    GridData tableLayoutData = new GridData(GridData.FILL, GridData.FILL, true, true);
    tableLayoutData.horizontalSpan = 5;
    _table.setLayoutData(tableLayoutData);
    // Create the table viewer for created table.
    _columnViewer = new TableViewer(_table);
    _columnViewer.setContentProvider(new ArrayContentProvider());
    _columnViewer.setLabelProvider(new CapellaElementLabelProvider());

    addViewerListeners();

    if (createViewerColumns()) {
      _table.setHeaderVisible(true);
      _table.setLinesVisible(true);
    }

    createCellEditors(_table);
  }

  /**
   * @param colNumber
   * @param labelProvider_p
   * @return
   */
  protected TableViewerColumn createTableViewerColumn(int colNumber_p, ColumnLabelProvider labelProvider_p) {
    TableViewerColumn viewerColumn = new TableViewerColumn((TableViewer) getColumnViewer(), SWT.NONE);
    TableColumn column = viewerColumn.getColumn();
    column.setText(getColumnProperties()[colNumber_p]);
    column.setWidth(DEFAULT_COLUMN_BOUND);
    column.setResizable(true);
    column.setMoveable(true);
    viewerColumn.setLabelProvider(labelProvider_p);
    return viewerColumn;
  }

  /**
   * {@inheritDoc}
   */
  public void setInput(List<EObject> input_p) {
    if (null != _columnViewer) {
      _columnViewer.setInput(input_p);
    }
  }

  /**
   * {@inheritDoc}
   */
  public void setEnabled(boolean enabled_p) {
    if (null != _table && !_table.isDisposed()) {
      _table.setEnabled(enabled_p);
    }
  }
}
