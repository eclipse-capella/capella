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
package org.polarsys.capella.core.libraries.ui.views.libraryManager.referencesManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;

import org.polarsys.capella.core.libraries.flexibilityProperties.LibraryManagerModel;
import org.polarsys.capella.core.libraries.ui.views.libraryManager.LibraryContentProvider;
import org.polarsys.capella.common.flexibility.properties.schema.IProperty;
import org.polarsys.capella.common.flexibility.wizards.schema.IRendererContext;
import org.polarsys.capella.common.libraries.IAbstractLibrary;
import org.polarsys.capella.common.libraries.IAbstractModel;

/**
 * A simple TableViewer to demonstrate usage
 */
public class ReferencesManagerWidget {

  protected Table table;
  protected TableViewer tableViewer;
  protected LibraryManagerModel model;

  private IProperty property;
  private IRendererContext rendererContext;

  public ReferencesManagerWidget(Composite parent) {
    tableViewer = new TableViewer(parent, SWT.CHECK | SWT.BORDER);
    tableViewer.getControl().setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

    table = tableViewer.getTable();
    tableViewer.setLabelProvider(new ReferencesLabelProvider());
    tableViewer.setContentProvider(new LibraryContentProvider());
    tableViewer.getTable().setLinesVisible(true);
    tableViewer.getTable().addListener(SWT.Selection, new Listener() {
      @Override
      public void handleEvent(Event event) {
        TableItem item = (TableItem) event.item;
        IAbstractLibrary library = (IAbstractLibrary) item.getData();
        if ((event.detail == SWT.CHECK)) {
          if (!item.getChecked() && !item.getGrayed()) {
            model.removeReferencedLibrary(library);
          } else {
            model.addReferencedLibrary(library);
          }
          refreshView();
          notifyValueModification();
        }
      }
    });
  }

  public void initializeView(LibraryManagerModel model_, IProperty property_, IRendererContext rendererContext_) {
    model = model_;
    property = property_;
    rendererContext = rendererContext_;
    tableViewer.setInput(model.getAllLibraries().toArray());
    refreshView();
  }

  protected void refreshView() {
    List<TableItem> itemsToBeChecked = new ArrayList<TableItem>();
    List<TableItem> itemsToBeGrayed = new ArrayList<TableItem>();
    List<TableItem> items = Arrays.asList(table.getItems());
    Collection<IAbstractLibrary> currentReferencedLibraries = model.getReferencedLibrariesByRootModel();
    for (IAbstractLibrary library : currentReferencedLibraries) {
      itemsToBeChecked.add(table.getItem(model.getAllLibraries().indexOf(library)));
    }
    for (IAbstractModel library : model.getAllReferencedLibrariesByRootModel()) {
      if (!currentReferencedLibraries.contains(library)) {
        TableItem item = table.getItem(model.getAllLibraries().indexOf(library));
        itemsToBeChecked.add(item);
        itemsToBeGrayed.add(item);
      }
    }

    for (TableItem item : items) {
      item.setChecked(itemsToBeChecked.contains(item));
    }
    for (TableItem item : items) {
      item.setGrayed(itemsToBeGrayed.contains(item));
    }
  }

  protected void notifyValueModification() {
	// Workaround to indicate that the value has changed
    rendererContext.getPropertyContext().setCurrentValue(property, model);
  }
}
