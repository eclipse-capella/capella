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

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.jface.viewers.CellLabelProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.TreeViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeColumn;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;
import org.polarsys.capella.common.ui.providers.MDEAdapterFactoryLabelProvider;
import org.polarsys.capella.core.model.handler.provider.CapellaAdapterFactoryProvider;

public class TreeDelegatedViewer extends AbstractDelegatedViewer {

  protected Tree _tree;

  /**
   * Constructor.
   * @param widgetFactory
   */
  public TreeDelegatedViewer(TabbedPropertySheetWidgetFactory widgetFactory) {
    this(widgetFactory, null);
  }

  /**
   * Constructor.
   * @param widgetFactory
   * @param cellEditorProvider
   */
  public TreeDelegatedViewer(TabbedPropertySheetWidgetFactory widgetFactory, ICellEditorProvider cellEditorProvider) {
    super(widgetFactory, cellEditorProvider);
  }

  /**
   * {@inheritDoc}
   */
  public void createContainer(Composite parent) {
    _tree = _widgetFactory.createTree(getViewerGroup(parent), SWT.MULTI | SWT.BORDER | SWT.FULL_SELECTION);
    GridData tableLayoutData = new GridData(GridData.FILL, GridData.FILL, true, true);
    tableLayoutData.horizontalSpan = 5;
    _tree.setLayoutData(tableLayoutData);
    // Create the tree viewer for created tree.
    _columnViewer = new TreeViewer(_tree);
    _columnViewer.setContentProvider(new AdapterFactoryContentProvider(CapellaAdapterFactoryProvider.getInstance().getAdapterFactory()));
    _columnViewer.setLabelProvider(new MDEAdapterFactoryLabelProvider());

    addViewerListeners();

    if (createViewerColumns()) {
      _tree.setHeaderVisible(true);
      _tree.setLinesVisible(true);
    }

    createCellEditors(_tree);
  }

  /**
   * @param colNumber
   * @param labelProvider
   * @return
   */
  protected TreeViewerColumn createTreeViewerColumn(int colNumber, CellLabelProvider labelProvider) {
    TreeViewerColumn viewerColumn = new TreeViewerColumn((TreeViewer) getColumnViewer(), SWT.NONE);
    TreeColumn column = viewerColumn.getColumn();
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
  public void setInput(final List<EObject> input) {
    if (null != _columnViewer) {
      _columnViewer.setInput(new IStructuredItemContentProvider() {
        public Collection<?> getElements(Object object) {
          return input;
        }
      });
    }
  }

  /**
   * {@inheritDoc}
   */
  public void setEnabled(boolean enabled) {
    if ((null != _tree) && !_tree.isDisposed()) {
      _tree.setEnabled(enabled);
    }
  }
}
