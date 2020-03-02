/*******************************************************************************
 * Copyright (c) 2020 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.sirius.analysis.preferences;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.ProjectScope;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.layout.TableColumnLayout;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.CellNavigationStrategy;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.ColumnViewerEditor;
import org.eclipse.jface.viewers.ColumnViewerEditorActivationEvent;
import org.eclipse.jface.viewers.ColumnViewerEditorActivationListener;
import org.eclipse.jface.viewers.ColumnViewerEditorActivationStrategy;
import org.eclipse.jface.viewers.ColumnViewerEditorDeactivationEvent;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.FocusCellOwnerDrawHighlighter;
import org.eclipse.jface.viewers.StyledCellLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.TableViewerEditor;
import org.eclipse.jface.viewers.TableViewerFocusCellManager;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.TableColumn;
import org.polarsys.capella.core.commands.preferences.service.AbstractDefaultPreferencePage;
import org.polarsys.capella.core.commands.preferences.service.PreferenceField;
import org.polarsys.capella.core.commands.preferences.service.UserProfileModeEnum;
import org.polarsys.capella.core.preferences.Activator;

public class TitleBlockPreferencePage extends AbstractDefaultPreferencePage {

  private class TitleBlockCell {
    public String name;
    public String content;

    public TitleBlockCell(String name, String content) {
      this.name = name;
      this.content = content;
    }

    @Override
    public String toString() {
      return name;
    }
  }

  public static final String PAGE_ID = "org.polarsys.capella.core.platform.sirius.ui.actions.preferences.TitleBlockPage";
  public static final String NAME = "Name";
  public static final String CONTENT = "Content";
  public static final String TABEL_CONTENT_PREFERENCE_STORE = "tableTitleBlock";
  public static final String COLUMNS_NUMBER_PREFERENCE_STORE = "columnsNumberTitleBlock";
  public static final String ROWS_NUMBER_PREFERENCE_STORE = "rowsNumberTitleBlock";
  public static final String ESCAPED_SEPARATOR = "\\+";
  public static final String SEPARATOR = "+";
  public static final String EMPTY_STRING = "";
  public static final int BOUND = 200;

  public

  TableViewer v;

  private PreferenceField defaultTitleBlockFieldEditor;

  List<List<TitleBlockCell>> tccMatrix;
  int columnsNumber;
  int rowsNumber;
  String tableContent;

  public TitleBlockPreferencePage() {
    super(PAGE_ID);
    tableContent = doGetPreferenceStore().getString(TABEL_CONTENT_PREFERENCE_STORE);
    columnsNumber = doGetPreferenceStore().getInt(COLUMNS_NUMBER_PREFERENCE_STORE);
    rowsNumber = doGetPreferenceStore().getInt("rowsNumberTitleBlock");
  }

  @Override
  protected IPreferenceStore doGetPreferenceStore() {
    return Activator.getDefault().getPreferenceStore();
  }

  @Override
  protected String getPageTitle() {
    return Messages.TitleBlockPreferencePage_Title;
  }

  @Override
  protected String getPageDescription() {
    return Messages.TitleBlockPreferencePage_Description;
  }

  @Override
  protected void createFieldEditors() {
    createCheckBox();

    Composite top = new Composite(getFieldEditorParent(), SWT.NONE);
    GridLayout top_layout = new GridLayout();
    top_layout.numColumns = 2;
    top_layout.marginHeight = 2;
    top_layout.marginWidth = 2;
    top.setLayout(top_layout);
    top.setLayoutData(new GridData(GridData.FILL_BOTH));

    createTable(top);

  }

  private void createCheckBox() {
    defaultTitleBlockFieldEditor = new PreferenceField("defaultTitleBlock", "Add by default Diagram TitleBlock",
        getFieldEditorParent());
    addField(defaultTitleBlockFieldEditor, UserProfileModeEnum.Expert, getFieldEditorParent(), ProjectScope.class);
  }

  private void createTable(Composite top) {
    v = new TableViewer(top, SWT.MULTI | SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL | SWT.FULL_SELECTION);

    createColumns(v, columnsNumber);
    v.setContentProvider(ArrayContentProvider.getInstance());
    v.setInput(createModel(columnsNumber, rowsNumber));

    v.getTable().setLinesVisible(true);
    // v.getTable().setBackground(getShell().getDisplay().getSystemColor(SWT.COLOR_GREEN));
    v.getTable().setHeaderVisible(true);
    GridData gridData = new GridData(GridData.FILL_BOTH);
    gridData.grabExcessVerticalSpace = true;
    gridData.grabExcessHorizontalSpace = true;
    gridData.verticalAlignment = GridData.FILL;
    gridData.horizontalAlignment = GridData.FILL;
    gridData.widthHint = 200;
    gridData.horizontalSpan = 1;
    v.getTable().setLayoutData(gridData);

    addMenu(v);

    Listener treeListener = new Listener() {
      @Override
      public void handleEvent(Event event) {
        switch (event.type) {
        case SWT.MouseDoubleClick: {
          Point coords = new Point(event.x, event.y);

          ViewerCell cell = v.getCell(coords);
          int index = cell.getColumnIndex();
          List<TitleBlockCell> list = (List<TitleBlockCell>) cell.getElement();
          String name = list.get(index).name;
          String content = list.get(index).content;
          TitleBlockDialog dialog = new TitleBlockDialog(getShell());
          if (content == "content") {
            dialog.setCurrentName("");
            dialog.setCurrentContent("");
          } else {
            dialog.setCurrentName(name);
            dialog.setCurrentContent(content);
          }
          dialog.create();
          if (dialog.open() == Window.OK) {
            list.get(index).name = dialog.getName();
            list.get(index).content = dialog.getContent();
            cell.setText(dialog.getName());
          }
        }
        }
      }
    };

    v.getTable().addListener(SWT.MouseDoubleClick, treeListener);
  }

  private void disposeColumns() {
    TableColumn[] columns = v.getTable().getColumns();
    for (TableColumn tc : columns) {
      tc.dispose();
    }
  }

  private void refreshTableColumns() {
    disposeColumns();
    createColumns(v, columnsNumber);
    v.setInput(updateModel(columnsNumber, rowsNumber));

  }

  private void refreshTableAddRows() {
    rowsNumber += 1;
    v.setInput(updateModelRows(columnsNumber, rowsNumber));
  }

  private void refreshTableRemoveRow() {
    int rowToDelete = v.getTable().getSelectionIndex();
    v.setInput(updateModelRowsDelete(rowToDelete));
  }

  @Override
  public boolean performOk() {

    doGetPreferenceStore().setValue("defaultTitleBlock", defaultTitleBlockFieldEditor.getBooleanValue());
    doGetPreferenceStore().setValue(COLUMNS_NUMBER_PREFERENCE_STORE, columnsNumber);
    doGetPreferenceStore().setValue(ROWS_NUMBER_PREFERENCE_STORE, rowsNumber);
    String currentTableContent = "";
    for (int i = 0; i < rowsNumber; i++) {
      for (int j = 0; j < columnsNumber; j++) {
        if (i == rowsNumber - 1 && j == columnsNumber - 1) {
          if (tccMatrix.get(i).get(j).name == "" || tccMatrix.get(i).get(j).content == "") {
            return false;
          }
          currentTableContent = currentTableContent + tccMatrix.get(i).get(j).name + SEPARATOR
              + tccMatrix.get(i).get(j).content;
        } else {
          if (tccMatrix.get(i).get(j).name == "" || tccMatrix.get(i).get(j).content == "") {
            return false;
          }
          currentTableContent = currentTableContent + tccMatrix.get(i).get(j).name + SEPARATOR
              + tccMatrix.get(i).get(j).content + SEPARATOR;

        }

      }
    }
    doGetPreferenceStore().setValue(TABEL_CONTENT_PREFERENCE_STORE, currentTableContent);
    return super.performOk();
  }

  private List<List<TitleBlockCell>> updateModel(int nrCol, int nrRows) {
    for (int i = 0; i < nrRows; i++) {
      List<TitleBlockCell> tbcCell = tccMatrix.get(i);
      // tbcCell.add(new TitleBlockCell("Edit", "aql:edit"));
      tbcCell.add(new TitleBlockCell(EMPTY_STRING, EMPTY_STRING));

    }
    return tccMatrix;
  }

  private List<List<TitleBlockCell>> updateModelRows(int nrCol, int nrRows) {
    List<TitleBlockCell> tbcCell = new ArrayList<>();
    for (int i = 0; i < nrCol; i++) {
      // tbcCell.add(new TitleBlockCell("Edit", "aql:edit"));
      tbcCell.add(new TitleBlockCell(EMPTY_STRING, EMPTY_STRING));
    }
    tccMatrix.add(tbcCell);
    return tccMatrix;
  }

  private List<List<TitleBlockCell>> updateModelRowsDelete(int rowToDelete) {

    tccMatrix.remove(rowToDelete);
    rowsNumber -= 1;
    return tccMatrix;
  }

  private List<List<TitleBlockCell>> updateModelColumnsDelete(int columnToDelete) {

    for (int i = 0; i < rowsNumber; i++) {
      tccMatrix.get(i).remove(columnToDelete);
    }
    return tccMatrix;
  }

  private List<List<TitleBlockCell>> createModel(int nrCol, int nrRows) {
    String[] cellsNameAndContent = tableContent.split(ESCAPED_SEPARATOR);
    int currentIndex = 0;
    tccMatrix = new ArrayList<>();
    for (int i = 0; i < nrRows; i++) {
      List<TitleBlockCell> tbcCell = new ArrayList<>();
      for (int j = 0; j < nrCol; j++) {
        tbcCell.add(new TitleBlockCell(cellsNameAndContent[currentIndex], cellsNameAndContent[currentIndex + 1]));
        currentIndex += 2;
      }
      tccMatrix.add(tbcCell);
    }

    return tccMatrix;
  }

  private void createColumn(TableViewer v, final String title, int index) {
    TableViewerColumn column = createTableViewerColumn(v, title, BOUND);

    column.setLabelProvider(new StyledCellLabelProvider() {
      @Override
      public void update(final ViewerCell cell) {
        List<TitleBlockCell> lst = (List<TitleBlockCell>) cell.getElement();

        final TitleBlockCell tbcell = lst.get(index);
        final String cellText = String.valueOf(tbcell);
        cell.setText(cellText);
      }
    });
  }

  private void testSelectCell(TableViewer viewer) {
    CellNavigationStrategy strategy = new CellNavigationStrategy() {

      @Override
      public ViewerCell findSelectedCell(ColumnViewer cviewer, ViewerCell currentSelectedCell, Event event) {
        ViewerCell cell = null;
        switch (event.keyCode) {
        case SWT.TAB:
          if (event.stateMask == 0) {
            cell = currentSelectedCell.getNeighbor(ViewerCell.RIGHT, true);
          } else {
            cell = currentSelectedCell.getNeighbor(ViewerCell.LEFT, true);
          }
          break;
        }

        if (cell != null) {
          TableColumn t = viewer.getTable().getColumn(cell.getColumnIndex());
          viewer.getTable().showColumn(t);
        }
        return cell;

      }

      @Override
      public boolean isNavigationEvent(ColumnViewer viewer, Event event) {
        switch (event.keyCode) {
        case SWT.TAB:
          return true;
        default:
          return super.isNavigationEvent(viewer, event);
        }
      }

    };

    TableViewerFocusCellManager focusCellManager = new TableViewerFocusCellManager(viewer,
        new FocusCellOwnerDrawHighlighter(viewer), strategy);

    ColumnViewerEditorActivationStrategy actSupport = new ColumnViewerEditorActivationStrategy(viewer) {

      @Override
      protected boolean isEditorActivationEvent(ColumnViewerEditorActivationEvent event) {
        return event.eventType == ColumnViewerEditorActivationEvent.TRAVERSAL
            || event.eventType == ColumnViewerEditorActivationEvent.MOUSE_CLICK_SELECTION
            || (event.eventType == ColumnViewerEditorActivationEvent.KEY_PRESSED && event.keyCode == SWT.TAB)
            || event.eventType == ColumnViewerEditorActivationEvent.PROGRAMMATIC;
      }
    };

    TableViewerEditor.create(viewer, focusCellManager, actSupport,
        ColumnViewerEditor.TABBING_HORIZONTAL | ColumnViewerEditor.TABBING_MOVE_TO_ROW_NEIGHBOR
            | ColumnViewerEditor.TABBING_VERTICAL | ColumnViewerEditor.KEYBOARD_ACTIVATION);

    viewer.getColumnViewerEditor().addEditorActivationListener(new ColumnViewerEditorActivationListener() {

      @Override
      public void afterEditorActivated(ColumnViewerEditorActivationEvent event) {

      }

      @Override
      public void afterEditorDeactivated(ColumnViewerEditorDeactivationEvent event) {

      }

      @Override
      public void beforeEditorActivated(ColumnViewerEditorActivationEvent event) {
        ViewerCell cell = (ViewerCell) event.getSource();
        viewer.getTable().showColumn(viewer.getTable().getColumn(cell.getColumnIndex()));
      }

      @Override
      public void beforeEditorDeactivated(ColumnViewerEditorDeactivationEvent event) {

      }

    });
  }

  private void createColumns(TableViewer viewer, int nrColumns) {
    List<String> columnHeadings = new ArrayList<>();
    for (int i = 0; i < nrColumns; i++) {
      columnHeadings.add("");
      createColumn(viewer, columnHeadings.get(i), i);
    }
    testSelectCell(viewer);
    setColumnLayout(viewer, nrColumns);
  }

  protected void setColumnLayout(TableViewer viewer, int nrColumns) {
    TableColumnLayout layout = new TableColumnLayout();
    viewer.getControl().getParent().setLayout(layout);
    for (int i = 0; i < nrColumns; i++) {
      layout.setColumnData(viewer.getTable().getColumn(i), new ColumnWeightData(100));
    }
  }

  private TableViewerColumn createTableViewerColumn(final TableViewer viewer, final String title, final int bound) {
    final TableViewerColumn viewerColumn = new TableViewerColumn(viewer, SWT.NONE);
    final TableColumn column = viewerColumn.getColumn();
    column.setText(title);

    int wd = viewer.getTable().getClientArea().width;
    if (wd > 0) {
      column.setWidth(wd / columnsNumber);
    } else {
      column.setWidth(bound);
    }
    column.setResizable(true);
    return viewerColumn;
  }

  private void addMenu(final TableViewer v) {
    final MenuManager mgr = new MenuManager();

    final Action addRow = new Action("Add row") {
      @Override
      public void run() {
        refreshTableAddRows();

      }
    };

    final Action addColumnAfter = new Action("Add column after") {
      @Override
      public void run() {
        createColumn(v, "", v.getTable().getColumnCount());
        columnsNumber += 1;
        refreshTableColumns();
      }
    };

    final Action removeColumn = new Action("Remove column") {
      @Override
      public void run() {
        MessageBox messageBox = new MessageBox(getShell(), SWT.ICON_QUESTION | SWT.YES | SWT.NO);
        messageBox.setMessage("Are you sure you want to delete the entire column?");
        messageBox.setText("Confirm Delete");
        int response = messageBox.open();
        if (response == SWT.YES) {
          try {
            int columnToDelete = v.getColumnViewerEditor().getFocusCell().getColumnIndex();
            disposeColumns();
            columnsNumber -= 1;
            createColumns(v, columnsNumber);
            v.setInput(updateModelColumnsDelete(columnToDelete));
          } catch (Exception e) {

          }
        }
      }
    };

    final Action removeRow = new Action("Remove row") {
      @Override
      public void run() {
        MessageBox messageBox = new MessageBox(getShell(), SWT.ICON_QUESTION | SWT.YES | SWT.NO);
        messageBox.setMessage("Are you sure you want to delete the entire row?");
        messageBox.setText("Confirm Delete");
        int response = messageBox.open();
        if (response == SWT.YES) {
          try {
            refreshTableRemoveRow();

          } catch (Exception e) {

          }
        }
      }
    };

    mgr.setRemoveAllWhenShown(true);
    mgr.addMenuListener(manager -> {
      if (v.getTable().getColumnCount() >= 1 && rowsNumber >= 1) {
        manager.add(addColumnAfter);
        manager.add(removeColumn);
        manager.add(addRow);
        manager.add(removeRow);
      }
    });

    v.getControl().setMenu(mgr.createContextMenu(v.getControl()));
  }

  @Override
  protected void performDefaults() {
    super.performDefaults();
    columnsNumber = doGetPreferenceStore().getDefaultInt(COLUMNS_NUMBER_PREFERENCE_STORE);
    rowsNumber = doGetPreferenceStore().getDefaultInt(ROWS_NUMBER_PREFERENCE_STORE);
    tableContent = doGetPreferenceStore().getDefaultString(TABEL_CONTENT_PREFERENCE_STORE);
    // refreshTableColumns();
    disposeColumns();
    createColumns(v, columnsNumber);
    v.setInput(createModel(columnsNumber, rowsNumber));
    performOk();

    return;
  }

}
