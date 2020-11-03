/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
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
import org.polarsys.capella.common.ui.providers.MDEAdapterFactoryLabelProvider;

public class TableDelegatedViewer extends AbstractDelegatedViewer {

  protected Table _table;

  /**
   * Constructor.
   * @param widgetFactory
   */
  public TableDelegatedViewer(TabbedPropertySheetWidgetFactory widgetFactory) {
    this(widgetFactory, null);
  }

  /**
   * Constructor.
   * @param widgetFactory
   * @param cellEditorProvider
   */
  public TableDelegatedViewer(TabbedPropertySheetWidgetFactory widgetFactory, ICellEditorProvider cellEditorProvider) {
    super(widgetFactory, cellEditorProvider);
  }

  /**
   * {@inheritDoc}
   */
  public void createContainer(Composite parent) {
    _table = _widgetFactory.createTable(getViewerGroup(parent), SWT.MULTI | SWT.BORDER | SWT.FULL_SELECTION);
    GridData tableLayoutData = new GridData(GridData.FILL, GridData.FILL, true, true);
    tableLayoutData.horizontalSpan = 5;
    _table.setLayoutData(tableLayoutData);
    // Create the table viewer for created table.
    _columnViewer = new TableViewer(_table);
    _columnViewer.setContentProvider(new ArrayContentProvider());
    _columnViewer.setLabelProvider(new MDEAdapterFactoryLabelProvider());

    addViewerListeners();

    if (createViewerColumns()) {
      _table.setHeaderVisible(true);
      _table.setLinesVisible(true);
    }

    createCellEditors(_table);
  }

  /**
   * @param colNumber
   * @param labelProvider
   * @return
   */
  protected TableViewerColumn createTableViewerColumn(int colNumber, ColumnLabelProvider labelProvider) {
    TableViewerColumn viewerColumn = new TableViewerColumn((TableViewer) getColumnViewer(), SWT.NONE);
    TableColumn column = viewerColumn.getColumn();
    column.setText(getColumnProperties()[colNumber]);
    column.setWidth(DEFAULT_COLUMN_BOUND);
    column.setResizable(true);
    column.setMoveable(true);
    viewerColumn.setLabelProvider(labelProvider);
    return viewerColumn;
  }

  /**
   * {@inheritDoc}
   */
  public void setInput(List<EObject> input) {
    if (null != _columnViewer) {
      _columnViewer.setInput(input);
    }
  }

  /**
   * {@inheritDoc}
   */
  public void setEnabled(boolean enabled) {
    if (null != _table && !_table.isDisposed()) {
      _table.setEnabled(enabled);
    }
  }
}
