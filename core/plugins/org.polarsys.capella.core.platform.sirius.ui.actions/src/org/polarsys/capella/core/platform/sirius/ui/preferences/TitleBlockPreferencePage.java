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
package org.polarsys.capella.core.platform.sirius.ui.preferences;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.layout.TableColumnLayout;
import org.eclipse.jface.preference.BooleanFieldEditor;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.IntegerFieldEditor;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;
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
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.polarsys.capella.core.commands.preferences.service.AbstractDefaultPreferencePage;
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
  public static final String ADD = "Add";
  public static final String EDIT = "Edit";
  public static final String REMOVE = "Remove";
  public static final String DELETE_SELECTION = "Delete Selection";
  public static final String NAME = "Name";
  public static final String CONTENT = "Content";
  public static final String GROUP_LABEL = "Number of columns in TitleBlock";
  public static final String TOOLTIP_GROUP = "Tooltip group";
  private IntegerFieldEditor columnsFieldEditor;
  private IntegerFieldEditor rowsFieldEditor;

  TableViewer v;
  private Table table;

  Button button1;
  Button add_button;
  Button edit_button;
  Button remove_button;
  BooleanFieldEditor enableMonitoringFieldEditor;
  int columnsNumber;
  int rowsNumber;

  public TitleBlockPreferencePage() {
    super(PAGE_ID);
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
    createGroupForNumberOfColumns();

    Composite top = new Composite(getFieldEditorParent(), SWT.NONE);
    GridLayout top_layout = new GridLayout();
    top_layout.numColumns = 2;
    top_layout.marginHeight = 2;
    top_layout.marginWidth = 2;
    top.setLayout(top_layout);
    top.setLayoutData(new GridData(GridData.FILL_BOTH));

    createTable(top);

    // createButtons(top);

  }

  private void createCheckBox() {
    button1 = new Button(getFieldEditorParent(), SWT.CHECK);
    button1.setText("Add by default Diagram TitleBlock");
    button1.setSelection(doGetPreferenceStore().getBoolean("defaultTitleBlock"));
    /*
     * enableMonitoringFieldEditor = new BooleanFieldEditor("defaultTitleBlock", "Add by default Diagram Title Block",
     * getFieldEditorParent()) {
     * 
     * @SuppressWarnings("synthetic-access")
     * 
     * @Override protected void valueChanged(boolean oldValue_p, boolean newValue_p) { super.valueChanged(oldValue_p,
     * newValue_p); } };
     */
    // enableMonitoringFieldEditor.setEnabled(doGetPreferenceStore().getBoolean("defaultTitleBlock"),getFieldEditorParent());
    /*
     * Composite compositeCheckBox = new Composite(top, SWT.NONE); Button buttonCheckBox = new Button(compositeCheckBox,
     * SWT.CHECK); buttonCheckBox.setText("Add by default Diagram Title Block");
     */
  }

  private Button createButton(Composite top, Composite container, String text, boolean enabled) {
    Button button = new Button(container, SWT.PUSH);
    button.setFont(top.getFont());
    button.setText(text);
    button.setEnabled(enabled);
    this.setButtonLayoutData(button);
    return button;
  }

  private void createButtons(Composite top) {

    Composite container = new Composite(top, SWT.NONE);
    GridLayout container_layout = new GridLayout();
    container_layout.marginHeight = 0;
    container_layout.marginWidth = 0;
    container.setLayout(container_layout);
    container.setLayoutData(new GridData(GridData.FILL_VERTICAL));
    container.setFont(top.getFont());

    add_button = createButton(top, container, ADD, true);

    edit_button = createButton(top, container, EDIT, false);

    remove_button = createButton(top, container, REMOVE, false);

    add_button.addListener(SWT.Selection, new Listener() {
      @Override
      public void handleEvent(Event e) {
        switch (e.type) {
        case SWT.Selection:
          TitleBlockDialog dialog = new TitleBlockDialog(getShell());
          dialog.setCurrentName("");
          dialog.setCurrentContent("");
          dialog.create();
          if (dialog.open() == Window.OK) {
            TableItem item = new TableItem(table, SWT.NULL);
            item.setText(0, dialog.getName());
            item.setText(1, dialog.getContent());
          }
          break;
        }
      }
    });
    remove_button.addListener(SWT.Selection, new Listener() {
      @Override
      public void handleEvent(Event e) {
        switch (e.type) {
        case SWT.Selection:
          table.remove(table.getSelectionIndices());
          edit_button.setEnabled(false);
          remove_button.setEnabled(false);
          break;
        }
      }
    });

    edit_button.addListener(SWT.Selection, new Listener() {
      @Override
      public void handleEvent(Event e) {
        switch (e.type) {
        case SWT.Selection:
          int index = table.getSelectionIndex();
          String currentName = table.getItem(index).getText(0);
          String currentContent = table.getItem(index).getText(1);
          TitleBlockDialog dialog = new TitleBlockDialog(getShell());

          dialog.setCurrentName(currentName);
          dialog.setCurrentContent(currentContent);
          dialog.create();
          if (dialog.open() == Window.OK) {
            table.getItem(index).setText(0, dialog.getName());
            table.getItem(index).setText(1, dialog.getContent());
            edit_button.setEnabled(false);
            remove_button.setEnabled(false);
          }
        }
      }
    });

  }

  private void createTable(Composite top) {
    v = new TableViewer(top, SWT.MULTI | SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL | SWT.FULL_SELECTION);

    createColumns(v, columnsNumber);
    v.setContentProvider(ArrayContentProvider.getInstance());
    v.setInput(createModel(columnsNumber, rowsNumber));
    v.getTable().setLinesVisible(true);
    v.getTable().setHeaderVisible(true);
    GridData gridData = new GridData(GridData.FILL_BOTH);
    gridData.grabExcessVerticalSpace = true;
    gridData.grabExcessHorizontalSpace = true;
    gridData.verticalAlignment = GridData.FILL;
    gridData.horizontalAlignment = GridData.FILL;
    gridData.widthHint = 200;
    gridData.horizontalSpan = 1;
    v.getTable().setLayoutData(gridData);

    System.out.println("Lalal:" + v.getTable().getGridLineWidth());
    System.out.println("Border: " + v.getTable().getBorderWidth());

    Listener treeListener = new Listener() {
      @Override
      public void handleEvent(Event event) {
        switch (event.type) {
        case SWT.MouseDoubleClick: {
          Point coords = new Point(event.x, event.y);

          ViewerCell cell = v.getCell(coords);
          int index = cell.getColumnIndex();
          System.out.println(cell.getElement());
          List<TitleBlockCell> list = (List<TitleBlockCell>) cell.getElement();
          String name = list.get(index).name;
          String content = list.get(index).content;
          System.out.println(name + " " + content);
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

  private void refreshTableColumns() {
    TableColumn[] columns = v.getTable().getColumns();
    System.out.println(columns);
    // v.getTable().removeAll();
    for (TableColumn tc : columns) {
      tc.dispose();

    }
    for (TableColumn tc : columns) {
      System.out.print(tc);
    }

    createColumns(v, columnsNumber);
    // v.setContentProvider(ArrayContentProvider.getInstance());
    v.setInput(createModel(columnsNumber, rowsNumber));
  }

  private void refreshTableRows() {
    v.setContentProvider(ArrayContentProvider.getInstance());
    v.setInput(createModel(columnsNumber, rowsNumber));
  }

  private void createGroupForNumberOfColumns() {
    final Group group = createGroup(GROUP_LABEL, TOOLTIP_GROUP, getFieldEditorParent());
    columnsFieldEditor = new IntegerFieldEditor("columnField", "Columns", group, 2);
    columnsFieldEditor.setValidRange(1, 50);

    columnsNumber = doGetPreferenceStore().getInt("columnField1");
    columnsFieldEditor.setStringValue(String.valueOf(columnsNumber));
    columnsFieldEditor.setPropertyChangeListener(new IPropertyChangeListener() {

      @Override
      public void propertyChange(PropertyChangeEvent event) {
        System.out.println(event.getNewValue());
        Class<? extends Object> a = event.getNewValue().getClass();
        if (event.getNewValue() instanceof String) {
          if (!event.getNewValue().toString().isEmpty()) {
            columnsNumber = Integer.parseInt((String) event.getNewValue());
            refreshTableColumns();
          }
        }

      }

    });

    /*
     * addField(columnsFieldEditor, UserProfileModeEnum.Expert, group);
     */
    // Label labelControl = columnsFieldEditor.getLabelControl(group);
    // GridData layoutData = new GridData(SWT.FILL, SWT.FILL, false, false);
    // labelControl.setLayoutData(layoutData);
    // layoutData.horizontalIndent = 15;
    rowsFieldEditor = new IntegerFieldEditor("rowField", "Rows", group, 2);
    rowsFieldEditor.setValidRange(1, 50);
    rowsNumber = doGetPreferenceStore().getInt("rowField");
    rowsFieldEditor.setStringValue(String.valueOf(rowsNumber));
    rowsFieldEditor.setPropertyChangeListener(new IPropertyChangeListener() {

      @Override
      public void propertyChange(PropertyChangeEvent event) {
        System.out.println(event.getNewValue());
        Class<? extends Object> a = event.getNewValue().getClass();
        if (event.getNewValue() instanceof String) {
          if (!event.getNewValue().toString().isEmpty()) {
            rowsNumber = Integer.parseInt((String) event.getNewValue());
            refreshTableRows();
          }
        }

      }

    });

  }

  @Override
  public boolean performOk() {
    boolean x = button1.getSelection();
    doGetPreferenceStore().setValue("defaultTitleBlock", button1.getSelection());
    doGetPreferenceStore().setValue("columnField1", columnsFieldEditor.getIntValue());
    doGetPreferenceStore().setValue("rowField", rowsFieldEditor.getIntValue());
    return super.performOk();
  }
  /*
   * private TableViewerColumn createTableViewerColumn(final TableViewer viewer, final String title, final int bound) {
   * final TableViewerColumn viewerColumn = new TableViewerColumn(viewer, SWT.NONE); final TableColumn column =
   * viewerColumn.getColumn(); column.setText(title); column.setWidth(bound); column.setResizable(true); return
   * viewerColumn; }
   */

  private List<List<TitleBlockCell>> createModel(int nrCol, int nrRows) {

    List<List<TitleBlockCell>> tccMatrix = new ArrayList<>();
    for (int i = 0; i < nrRows; i++) {
      List<TitleBlockCell> tbcCell = new ArrayList<>();
      for (int j = 0; j < nrCol; j++) {
        tbcCell.add(new TitleBlockCell("insert", "content"));
      }
      tccMatrix.add(tbcCell);
    }

    // return persons;
    return tccMatrix;
  }
  /*
   * private void createColumns(TableViewer viewer, int nrColumns) { Point size = viewer.getTable().getSize();
   * System.out.println("X este: " + size.x); System.out.println("Y este: " + size.y);
   * 
   * final int[] bounds = { 100, 100, 100, 100 }; List<String> columnHeadings = new ArrayList<>(); for (int i = 0; i <
   * nrColumns; i++) { columnHeadings.add(""); createColumn(viewer, columnHeadings.get(i), bounds[0], i); }
   * testSelectCell(viewer); }
   */

  private void createColumn(TableViewer v, final String title, final int bound, int index) {
    // TableViewerColumn column = createTableViewerColumn(v, title, bound);
    TableViewerColumn column = createTableViewerColumn(v, title, 200);

    column.setLabelProvider(new StyledCellLabelProvider() {
      @Override
      public void update(final ViewerCell cell) {
        List<TitleBlockCell> lst = (List<TitleBlockCell>) cell.getElement();
        // if (index < columnsNumber) {

        final TitleBlockCell tbcell = lst.get(index);
        final String cellText = String.valueOf(tbcell);
        cell.setText(cellText);
      }
      // }
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
          System.out.println("test");
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
    Point size = viewer.getTable().getSize();
    System.out.println("X este: " + size.x);
    System.out.println("Y este: " + size.y);

    final int[] bounds = { 100, 100, 100, 100 };
    List<String> columnHeadings = new ArrayList<>();
    for (int i = 0; i < nrColumns; i++) {
      columnHeadings.add("");
      createColumn(viewer, columnHeadings.get(i), bounds[0], i);
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

}
