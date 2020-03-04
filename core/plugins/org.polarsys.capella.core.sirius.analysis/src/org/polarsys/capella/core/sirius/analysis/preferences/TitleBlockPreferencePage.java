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
import org.eclipse.jface.viewers.ColumnViewerEditorActivationStrategy;
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
    private String name;
    private String content;

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
  public static final String CONTENT_LOWERCASE = "content";
  public static final String DEFAULT_TITLEBLOCK_PREFERENCE_STORE = "defaultTitleBlock";
  public static final String ADD_BY_DEFAULT = "Add by default Diagram Title Block";
  public static final String TABEL_CONTENT_PREFERENCE_STORE = "tableTitleBlock";
  public static final String COLUMNS_NUMBER_PREFERENCE_STORE = "columnsNumberTitleBlock";
  public static final String ROWS_NUMBER_PREFERENCE_STORE = "rowsNumberTitleBlock";
  public static final String ESCAPED_SEPARATOR = "\\+";
  public static final String SEPARATOR = "+";
  public static final String EMPTY_STRING = "";
  public static final int BOUND = 200;
  public static final String ADD_ROW = "Add row";
  public static final String ADD_ROW_AFTER = "Add row after";
  public static final String ADD_COLUMN = "Add column";
  public static final String ADD_COLUMN_AFTER = "Add column after";
  public static final String REMOVE_ROW = "Remove row";
  public static final String REMOVE_COLUMN = "Remove column";
  public static final String ROW_DELETE_MESSAGE = "Are you sure you want to delete the entire row?";
  public static final String COLUMN_DELETE_MESSAGE = "Are you sure you want to delete the entire column?";
  public static final String CONFIRM_DELETE = "Confirm Delete";
  public static final String TitleBlockPreferencePage_Title = "Title Block";
  public static final String TitleBlockPreferencePage_Description = "Preferences related to Title Block";

  private TableViewer v;
  private PreferenceField defaultTitleBlockFieldEditor;
  private List<List<TitleBlockCell>> tccMatrix;
  private int columnsNumber;
  private int rowsNumber;
  private String tableContent;

  public TitleBlockPreferencePage() {
    super(PAGE_ID);
    tableContent = doGetPreferenceStore().getString(TABEL_CONTENT_PREFERENCE_STORE);
    columnsNumber = doGetPreferenceStore().getInt(COLUMNS_NUMBER_PREFERENCE_STORE);
    rowsNumber = doGetPreferenceStore().getInt(ROWS_NUMBER_PREFERENCE_STORE);
  }

  @Override
  protected IPreferenceStore doGetPreferenceStore() {
    return Activator.getDefault().getPreferenceStore();
  }

  @Override
  protected String getPageTitle() {
    return TitleBlockPreferencePage_Title;
  }

  @Override
  protected String getPageDescription() {
    return TitleBlockPreferencePage_Description;
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
    defaultTitleBlockFieldEditor = new PreferenceField(DEFAULT_TITLEBLOCK_PREFERENCE_STORE, ADD_BY_DEFAULT,
        getFieldEditorParent());
    addField(defaultTitleBlockFieldEditor, UserProfileModeEnum.Expert, getFieldEditorParent(), ProjectScope.class);
  }

  private void createTable(Composite top) {
    v = new TableViewer(top, SWT.MULTI | SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL | SWT.FULL_SELECTION);
    createColumns(v, columnsNumber);
    v.setContentProvider(ArrayContentProvider.getInstance());
    v.setInput(createModel());
    v.getTable().setLinesVisible(true);
    v.getTable().setHeaderVisible(false);
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
          if (content.equals(CONTENT_LOWERCASE)) {
            dialog.setCurrentName(EMPTY_STRING);
            dialog.setCurrentContent(EMPTY_STRING);
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

  private void refreshTableColumns(int index) {
    disposeColumns();
    createColumns(v, columnsNumber);
    v.setInput(addNewEmptyColumn(index));
  }

  private void refreshTableRemoveColumn(int index) {
    disposeColumns();
    columnsNumber -= 1;
    createColumns(v, columnsNumber);
    v.setInput(removeSelectedColumn(index));
  }

  private void refreshTableAddRows(int index) {
    rowsNumber += 1;
    v.setInput(addNewEmptyRow(index));
  }

  private void refreshTableRemoveRow() {
    int rowToDelete = v.getTable().getSelectionIndex();
    v.setInput(removeSelectedRow(rowToDelete));
  }

  @Override
  public boolean performOk() {
    doGetPreferenceStore().setValue(DEFAULT_TITLEBLOCK_PREFERENCE_STORE,
        defaultTitleBlockFieldEditor.getBooleanValue());
    doGetPreferenceStore().setValue(COLUMNS_NUMBER_PREFERENCE_STORE, columnsNumber);
    doGetPreferenceStore().setValue(ROWS_NUMBER_PREFERENCE_STORE, rowsNumber);
    String currentTableContent = "";
    for (int i = 0; i < rowsNumber; i++) {
      for (int j = 0; j < columnsNumber; j++) {
        if (i == rowsNumber - 1 && j == columnsNumber - 1) {
          if (tccMatrix.get(i).get(j).name.equals(EMPTY_STRING)
              || tccMatrix.get(i).get(j).content.equals(EMPTY_STRING)) {
            return false;
          }
          currentTableContent = currentTableContent + tccMatrix.get(i).get(j).name + SEPARATOR
              + tccMatrix.get(i).get(j).content;
        } else {
          if (tccMatrix.get(i).get(j).name.equals(EMPTY_STRING)
              || tccMatrix.get(i).get(j).content.equals(EMPTY_STRING)) {
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

  private List<List<TitleBlockCell>> addNewEmptyColumn(int index) {
    if (index == -1) {
      for (int i = 0; i < rowsNumber; i++) {
        List<TitleBlockCell> tbcCell = tccMatrix.get(i);
        tbcCell.add(new TitleBlockCell(EMPTY_STRING, EMPTY_STRING));
      }
    } else {
      for (int i = 0; i < rowsNumber; i++) {
        List<TitleBlockCell> tbcCell = tccMatrix.get(i);
        tbcCell.add(index, new TitleBlockCell(EMPTY_STRING, EMPTY_STRING));
      }
    }
    return tccMatrix;
  }

  private List<List<TitleBlockCell>> addNewEmptyRow(int index) {
    List<TitleBlockCell> tbcCell = new ArrayList<>();
    for (int i = 0; i < columnsNumber; i++) {
      tbcCell.add(new TitleBlockCell(EMPTY_STRING, EMPTY_STRING));
    }
    if (index == -1) {
      tccMatrix.add(tbcCell);
    } else {
      tccMatrix.add(index, tbcCell);
    }
    return tccMatrix;
  }

  private List<List<TitleBlockCell>> removeSelectedRow(int rowToDelete) {
    tccMatrix.remove(rowToDelete);
    rowsNumber -= 1;
    return tccMatrix;
  }

  private List<List<TitleBlockCell>> removeSelectedColumn(int columnToDelete) {
    for (int i = 0; i < rowsNumber; i++) {
      tccMatrix.get(i).remove(columnToDelete);
    }
    return tccMatrix;
  }

  private List<List<TitleBlockCell>> createModel() {
    String[] cellsNameAndContent = tableContent.split(ESCAPED_SEPARATOR);
    int currentIndex = 0;
    tccMatrix = new ArrayList<>();
    for (int i = 0; i < rowsNumber; i++) {
      List<TitleBlockCell> tbcCell = new ArrayList<>();
      for (int j = 0; j < columnsNumber; j++) {
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
        cell.setBackground(getShell().getDisplay().getSystemColor(SWT.COLOR_GRAY));
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
        return false;
      }
    };

    TableViewerEditor.create(viewer, focusCellManager, actSupport,
        ColumnViewerEditor.TABBING_HORIZONTAL | ColumnViewerEditor.TABBING_MOVE_TO_ROW_NEIGHBOR
            | ColumnViewerEditor.TABBING_VERTICAL | ColumnViewerEditor.KEYBOARD_ACTIVATION);

  }

  private void createColumns(TableViewer viewer, int nrColumns) {
    List<String> columnHeadings = new ArrayList<>();
    for (int i = 0; i < nrColumns; i++) {
      columnHeadings.add(EMPTY_STRING);
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

    final Action addRowAfter = new Action(ADD_ROW_AFTER) {
      @Override
      public void run() {
        refreshTableAddRows(-1);
      }
    };

    final Action addRow = new Action(ADD_ROW) {
      @Override
      public void run() {
        int rowToAdd = v.getTable().getSelectionIndex();
        refreshTableAddRows(rowToAdd);
      }
    };

    final Action addColumnAfter = new Action(ADD_COLUMN_AFTER) {
      @Override
      public void run() {
        createColumn(v, EMPTY_STRING, v.getTable().getColumnCount());
        columnsNumber += 1;
        refreshTableColumns(-1);
      }
    };

    final Action addColumn = new Action(ADD_COLUMN) {
      @Override
      public void run() {
        createColumn(v, EMPTY_STRING, v.getTable().getColumnCount());
        columnsNumber += 1;
        int columnToAdd = v.getColumnViewerEditor().getFocusCell().getColumnIndex();
        refreshTableColumns(columnToAdd);
      }
    };

    final Action removeColumn = new Action(REMOVE_COLUMN) {
      @Override
      public void run() {
        MessageBox messageBox = new MessageBox(getShell(), SWT.ICON_QUESTION | SWT.YES | SWT.NO);
        messageBox.setMessage(COLUMN_DELETE_MESSAGE);
        messageBox.setText(CONFIRM_DELETE);
        int response = messageBox.open();
        if (response == SWT.YES) {
          int columnToDelete = v.getColumnViewerEditor().getFocusCell().getColumnIndex();
          refreshTableRemoveColumn(columnToDelete);
        }
      }
    };

    final Action removeRow = new Action(REMOVE_ROW) {
      @Override
      public void run() {
        int rowToDelete = v.getTable().getSelectionIndex();
        if (rowToDelete != -1) {
          MessageBox messageBox = new MessageBox(getShell(), SWT.ICON_QUESTION | SWT.YES | SWT.NO);
          messageBox.setMessage(ROW_DELETE_MESSAGE);
          messageBox.setText(CONFIRM_DELETE);
          int response = messageBox.open();
          if (response == SWT.YES) {
            refreshTableRemoveRow();
          }
        }

      }
    };

    mgr.setRemoveAllWhenShown(true);
    mgr.addMenuListener(manager -> {
      if (v.getTable().getColumnCount() >= 1 && rowsNumber >= 1) {
        manager.add(addColumnAfter);
        manager.add(addColumn);
        manager.add(removeColumn);
        manager.add(addRowAfter);
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
    disposeColumns();
    createColumns(v, columnsNumber);
    v.setInput(createModel());
    performOk();
  }

}
