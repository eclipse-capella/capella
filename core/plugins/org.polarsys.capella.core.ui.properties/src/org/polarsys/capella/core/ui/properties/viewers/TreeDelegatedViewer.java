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

import org.polarsys.capella.core.ui.toolkit.viewers.CapellaElementLabelProvider;
import org.polarsys.capella.common.helpers.adapters.MDEAdapterFactory;

/**
 */
public class TreeDelegatedViewer extends AbstractDelegatedViewer {

  protected Tree _tree;

  /**
   * Constructor.
   * @param widgetFactory_p
   */
  public TreeDelegatedViewer(TabbedPropertySheetWidgetFactory widgetFactory_p) {
    this(widgetFactory_p, null);
  }

  /**
   * Constructor.
   * @param widgetFactory_p
   * @param cellEditorProvider_p
   */
  public TreeDelegatedViewer(TabbedPropertySheetWidgetFactory widgetFactory_p, ICellEditorProvider cellEditorProvider_p) {
    super(widgetFactory_p, cellEditorProvider_p);
  }

  /**
   * {@inheritDoc}
   */
  public void createContainer(Composite parent_p) {
    _tree = _widgetFactory.createTree(getViewerGroup(parent_p), SWT.MULTI | SWT.BORDER | SWT.FULL_SELECTION);
    GridData tableLayoutData = new GridData(GridData.FILL, GridData.FILL, true, true);
    tableLayoutData.horizontalSpan = 5;
    _tree.setLayoutData(tableLayoutData);
    // Create the tree viewer for created tree.
    _columnViewer = new TreeViewer(_tree);
    _columnViewer.setContentProvider(new AdapterFactoryContentProvider(MDEAdapterFactory.getAdapterFactory()));
    _columnViewer.setLabelProvider(new CapellaElementLabelProvider());

    addViewerListeners();

    if (createViewerColumns()) {
      _tree.setHeaderVisible(true);
      _tree.setLinesVisible(true);
    }

    createCellEditors(_tree);
  }

  /**
   * @param colNumber_p
   * @param labelProvider_p
   * @return
   */
  protected TreeViewerColumn createTreeViewerColumn(int colNumber_p, CellLabelProvider labelProvider_p) {
    TreeViewerColumn viewerColumn = new TreeViewerColumn((TreeViewer) getColumnViewer(), SWT.NONE);
    TreeColumn column = viewerColumn.getColumn();
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
  public void setInput(final List<EObject> input_p) {
    if (null != _columnViewer) {
      _columnViewer.setInput(new IStructuredItemContentProvider() {
        public Collection<?> getElements(Object object_p) {
          return input_p;
        }
      });
    }
  }

  /**
   * {@inheritDoc}
   */
  public void setEnabled(boolean enabled_p) {
    if (null != _tree && !_tree.isDisposed()) {
      _tree.setEnabled(enabled_p);
    }
  }
}
