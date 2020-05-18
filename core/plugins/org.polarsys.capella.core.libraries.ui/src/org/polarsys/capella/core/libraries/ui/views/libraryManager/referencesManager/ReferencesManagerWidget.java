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
package org.polarsys.capella.core.libraries.ui.views.libraryManager.referencesManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;
import org.polarsys.capella.common.flexibility.properties.schema.IProperty;
import org.polarsys.capella.common.flexibility.wizards.schema.IRendererContext;
import org.polarsys.capella.common.libraries.IModel;
import org.polarsys.capella.common.libraries.manager.LibraryManagerExt;
import org.polarsys.capella.core.libraries.model.CapellaLibraryExt;
import org.polarsys.capella.core.libraries.properties.LibraryManagerModel;
import org.polarsys.capella.core.libraries.ui.views.libraryManager.LibraryContentProvider;

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
        IModel library = (IModel) item.getData();

        if (!LibraryManagerExt.getAllUnavailableReferences(library).isEmpty()) {
          refreshView();
          return;
        }

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
    List<TableItem> itemsToBeDisabled = new ArrayList<TableItem>();
    List<TableItem> itemsToBeItalicAndRed = new ArrayList<TableItem>();
    List<TableItem> items = Arrays.asList(table.getItems());
    Collection<IModel> currentReferencedLibraries = model.getReferencedLibrariesByRootModel();

    for (IModel library : currentReferencedLibraries) {
      int index = model.getAllLibraries().indexOf(library);
      if (index >= 0) {
        itemsToBeChecked.add(table.getItem(index));
      }
    }

    for (int i = 0; i < model.getAllLibraries().size(); i++) {
      if (!LibraryManagerExt.getAllUnavailableReferences(model.getAllLibraries().get(i)).isEmpty()) {
        itemsToBeDisabled.add(table.getItem(i));
      }
    }
    
    for (IModel library : currentReferencedLibraries) {
      int index = model.getAllLibraries().indexOf(library);
      if (index >= 0) {
        if (CapellaLibraryExt.isUnresolvableIdentifier(library.getIdentifier())) {
          itemsToBeItalicAndRed.add(table.getItem(index));
        }
      }
    }

    for (IModel library : model.getAllReferencedLibrariesByRootModel()) {
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
    for (TableItem item : items) {
      if (itemsToBeDisabled.contains(item)) {
        item.setForeground(Display.getDefault().getSystemColor(SWT.COLOR_DARK_GRAY));
      } else {
        item.setForeground(Display.getDefault().getSystemColor(SWT.COLOR_BLACK));
      }
    }
    for (TableItem item : itemsToBeItalicAndRed) {
      item.setForeground(Display.getDefault().getSystemColor(SWT.COLOR_RED));
      item.setFont(JFaceResources.getFontRegistry().getItalic(JFaceResources.DEFAULT_FONT));
    }
  }

  protected void notifyValueModification() {
    // Workaround to indicate that the value has changed
    rendererContext.getPropertyContext().setCurrentValue(property, model);
  }
}
