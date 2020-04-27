/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.libraries.ui.views.libraryManager.accessPolicyManager;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Table;
import org.polarsys.capella.common.flexibility.properties.schema.IProperty;
import org.polarsys.capella.common.flexibility.wizards.schema.IRendererContext;
import org.polarsys.capella.common.libraries.IModel;
import org.polarsys.capella.core.libraries.properties.LibraryManagerModel;

/**
 * A simple TableViewer to demonstrate usage
 */
public class AccessPolicyManagerWidget {

  private static final String LIBRARY_COLUMN_NAME = "Library"; //$NON-NLS-1$
  private static final String ACCESS_POLICY_COLUMN_NAME = "Access Policy"; //$NON-NLS-1$

  protected Table table;
  protected TableViewer tableViewer;
  protected LibraryManagerModel model;
  private IProperty property;
  private IRendererContext rendererContext;
  private TableViewerColumn accessPolicyColumn;

  public AccessPolicyManagerWidget(Composite parent) {
    TableLayout tableLayout = new TableLayout();
    tableLayout.addColumnData(new ColumnWeightData(1));
    tableLayout.addColumnData(new ColumnWeightData(1));
    table = new Table(parent, SWT.BORDER | SWT.FULL_SELECTION | SWT.V_SCROLL);
    table.setLinesVisible(true);
    table.setHeaderVisible(true);
    table.setLayout(tableLayout);
    tableViewer = new TableViewer(table);

    tableViewer.getControl().setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
    TableViewerColumn labelPolicyColumn = new TableViewerColumn(tableViewer, SWT.NONE);
    labelPolicyColumn.getColumn().setText(LIBRARY_COLUMN_NAME);
    accessPolicyColumn = new TableViewerColumn(tableViewer, SWT.NONE);
    accessPolicyColumn.getColumn().setWidth(20);
    accessPolicyColumn.getColumn().setText(ACCESS_POLICY_COLUMN_NAME);
    tableViewer.setContentProvider(new ArrayContentProvider());
  }

  public void initializeView(LibraryManagerModel model_, IProperty property_, IRendererContext rendererContext_) {
    model = model_;
    property = property_;
    rendererContext = rendererContext_;
    accessPolicyColumn.setEditingSupport(new AccessPolicyEditingSupport(accessPolicyColumn.getViewer(), model, this));
    tableViewer.setLabelProvider(new AccessPolicyLabelProvider(model));
    accessPolicyColumn.setLabelProvider(new ColumnLabelProvider() {
      @Override
      public void update(final ViewerCell cell) {
        IModel library = (IModel) cell.getElement();
        if (!model.isAccessPolicyModifiable(library)) {
          cell.setForeground(Display.getDefault().getSystemColor(SWT.COLOR_DARK_GRAY));
        } else {
          cell.setForeground(Display.getDefault().getSystemColor(SWT.COLOR_BLACK));
        }
        cell.setText(model.getAccessPolicy(library).getLiteral());
      }
    });

    refreshView();
  }

  protected void refreshView() {
    tableViewer.setInput(model.getAllReferencedLibrariesByRootModel().toArray());
  }

  protected void notifyValueModification() {
    // Workaround to indicate that the value has changed
    rendererContext.getPropertyContext().setCurrentValue(property, model);
  }

}
