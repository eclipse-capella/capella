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
package org.polarsys.capella.core.libraries.ui.views.libraryManager.activeStateManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;
import org.polarsys.capella.common.flexibility.properties.schema.IProperty;
import org.polarsys.capella.common.flexibility.wizards.schema.IRendererContext;
import org.polarsys.capella.common.libraries.IModel;
import org.polarsys.capella.core.libraries.properties.LibraryManagerModel;
import org.polarsys.capella.core.libraries.ui.views.libraryManager.LibraryContentProvider;

/**
 * A simple TableViewer to demonstrate usage
 */
public class ActiveStateManagerWidget {

  protected Table table;
  protected TableViewer tableViewer;
  protected LibraryManagerModel model;
  private IProperty property;
  private IRendererContext rendererContext;

  public ActiveStateManagerWidget(Composite parent) {

    tableViewer = new TableViewer(parent, SWT.CHECK | SWT.BORDER);
    tableViewer.getControl().setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

    table = tableViewer.getTable();
    tableViewer.setLabelProvider(new ActiveStateLabelProvider());
    tableViewer.setContentProvider(new LibraryContentProvider());
    tableViewer.getTable().setLinesVisible(true);
    tableViewer.getTable().addListener(SWT.Selection, new Listener() {
      @Override
      public void handleEvent(Event event) {
        TableItem item = (TableItem) event.item;
        IModel library = (IModel) item.getData();
        if ((event.detail == SWT.CHECK)) {
          if (item.getGrayed()) {
            item.setChecked(true);
          } else {
            if (!item.getChecked()) {
              model.setActiveState(library, false);
            } else {
              model.setActiveState(library, true);
            }
            refreshView();
            notifyValueModification();
          }
        }
      }
    });
  }

  public void initializeView(LibraryManagerModel model_, IProperty property_, IRendererContext rendererContext_) {
    model = model_;
    property = property_;
    rendererContext = rendererContext_;
    refreshView();
  }

  protected void refreshView() {
    List<IModel> allReferencedLibraries = model.getAllReferencedLibrariesByRootModel();
    tableViewer.setInput(allReferencedLibraries.toArray());
    List<TableItem> itemsToBeChecked = new ArrayList<TableItem>();
    List<TableItem> items = Arrays.asList(table.getItems());

    for (IModel library : allReferencedLibraries) {
      if (model.getActiveState(library).booleanValue()) {
        itemsToBeChecked.add(table.getItem(allReferencedLibraries.indexOf(library)));
      }
    }
    for (TableItem item : items) {
      item.setChecked(itemsToBeChecked.contains(item));
    }
  }

  protected void notifyValueModification() {
    // Workaround to indicate that the value has changed
    rendererContext.getPropertyContext().setCurrentValue(property, model);
  }
}
