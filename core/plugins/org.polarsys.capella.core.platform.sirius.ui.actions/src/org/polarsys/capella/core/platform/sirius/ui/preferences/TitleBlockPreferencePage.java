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

  Button add_button;
  Button edit_button;
  Button remove_button;
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
    /*
     * Menu menu = new Menu(getShell(), SWT.POP_UP); table.setMenu(menu); MenuItem menu_item = new MenuItem(menu,
     * SWT.PUSH); menu_item.setText(DELETE_SELECTION); menu_item.addListener(SWT.Selection, new Listener() {
     * 
     * @Override public void handleEvent(Event event) { table.remove(table.getSelectionIndices()); } });
     */
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
    // gridData.heightHint = table.getItemHeight();
    gridData.horizontalSpan = 1;
    v.getTable().setLayoutData(gridData);

    // v.getTable().setBounds();
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
  /*
   * TableLayout tableLayout = new TableLayout();
   * 
   * table = new Table(top, SWT.MULTI | SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL | SWT.FULL_SELECTION);
   * table.setLayout(tableLayout); table.setHeaderVisible(true); // table.setLinesVisible(true);
   * table.setLinesVisible(true);
   * 
   * table.setFont(top.getFont());
   * 
   * GridData gridData = new GridData(GridData.FILL_BOTH); gridData.grabExcessVerticalSpace = true;
   * gridData.grabExcessHorizontalSpace = true; gridData.verticalAlignment = GridData.FILL; gridData.horizontalAlignment
   * = GridData.FILL; gridData.widthHint = 200; gridData.heightHint = table.getItemHeight(); gridData.horizontalSpan =
   * 1; table.setLayoutData(gridData);
   */

  /*
   * int nrOfColumns = doGetPreferenceStore().getInt("columnField1");
   * 
   * column1.setWidth(150); column2.setWidth(150);
   * 
   * for (int i = 0; i < 2; i++) { TableItem item = new TableItem(table, SWT.NONE); item.setText(new String[] { "", ""
   * }); Color color = Display.getDefault().getSystemColor(SWT.COLOR_GRAY);
   * 
   * item.setBackground(color);
   * 
   * /* TableEditor editorw = new TableEditor(table); editorw = new TableEditor(table); Text txtLang = new Text(table,
   * SWT.BORDER); editorw.grabHorizontal = true; editorw.setEditor(txtLang, item, 0); editorw = new TableEditor(table);
   * Text txtLang2 = new Text(table, SWT.BORDER); editorw.grabHorizontal = true; editorw.setEditor(txtLang2, item, 1);
   */

  // column1.pack();
  // column2.pack();
  /*
   * final TableEditor editor = new TableEditor(table); // The editor must have the same size as the cell and must //
   * not be any smaller than 50 pixels. editor.horizontalAlignment = SWT.LEFT; editor.grabHorizontal = true;
   * editor.minimumWidth = 50; // editing the second column
   */
  /*
   * table.addListener(SWT.MouseDown, new Listener() {
   * 
   * @Override public void handleEvent(Event event) { Point pt = new Point(event.x, event.y); TableItem item =
   * table.getItem(pt); if (item != null) { for (int col = 0; col < table.getColumnCount(); col++) { Rectangle rect =
   * item.getBounds(col); if (rect.contains(pt)) { System.out.println("item clicked."); System.out.println("column is "
   * + col); System.out.println(item.getText(col)); String itemText = item.getText(col); String currentName = ""; String
   * currentContent = ""; if (itemText != "") { itemText = itemText.replace(" ", ""); currentName =
   * itemText.split("-")[0]; currentContent = itemText.split("-")[1]; } // int index = table.getSelectionIndex();
   * 
   * TitleBlockDialog dialog = new TitleBlockDialog(getShell());
   * 
   * dialog.setCurrentName(currentName); dialog.setCurrentContent(currentContent); dialog.create(); if (dialog.open() ==
   * Window.OK) { item.setText(col, dialog.getName() + " - " + dialog.getContent()); table.setFocus();
   * 
   * } }
   * 
   * } } }
   * 
   * });
   */

  // 1.

  /*
   * for (int i = 0; i < 4; i++) { TableColumn column = new TableColumn(table, SWT.NONE); column.setWidth(100); }
   * 
   * TableItem x1 = new TableItem(table, SWT.NONE); TableEditor editorw = new TableEditor(table);
   * 
   * Label lblName = new Label(table, SWT.NONE); lblName.setText("Language"); editorw.grabHorizontal = true;
   * editorw.setEditor(lblName, x1, 0);
   * 
   * editorw = new TableEditor(table); Text txtLang = new Text(table, SWT.BORDER); editorw.grabHorizontal = true;
   * editorw.setEditor(txtLang, x1, 0);
   * 
   * editorw = new TableEditor(table); Label lblReference = new Label(table, SWT.NONE); lblReference.setText("Value");
   * editorw.grabHorizontal = true; editorw.setEditor(lblReference, x1, 2);
   * 
   * editorw = new TableEditor(table); Text txtValue = new Text(table, SWT.BORDER);
   * 
   * editorw.grabHorizontal = true; editorw.setEditor(txtValue, x1, 1);
   * 
   * ///// table row 2 TableItem x2 = new TableItem(table, SWT.NONE); editorw = new TableEditor(table);
   * 
   * Label lblName2 = new Label(table, SWT.NONE);
   * 
   * lblName2.setText("Language"); editorw.grabHorizontal = true; editorw.setEditor(lblName2, x2, 0);
   * 
   * editorw = new TableEditor(table); Text txtLang2 = new Text(table, SWT.BORDER); editorw.grabHorizontal = true;
   * editorw.setEditor(txtLang2, x2, 1);
   * 
   * editorw = new TableEditor(table); Label lblReference2 = new Label(table, SWT.NONE); lblReference2.setText("Value");
   * editorw.grabHorizontal = true; editorw.setEditor(lblReference2, x2, 2);
   * 
   * editorw = new TableEditor(table); Text txtValue2 = new Text(table, SWT.BORDER); editorw.grabHorizontal = true;
   * editorw.setEditor(txtValue2, x2, 3);
   */
  /*
   * ColumnLayoutData[] fTableColumnLayouts = { new ColumnWeightData(165), new ColumnWeightData(165), new
   * ColumnWeightData(165), new ColumnWeightData(165), new ColumnWeightData(165), new ColumnWeightData(165) };
   * 
   * TableColumn column;
   * 
   * tableLayout.addColumnData(fTableColumnLayouts[0]); column = new TableColumn(table, SWT.NONE, 0);
   * column.setResizable(fTableColumnLayouts[0].resizable); column.setText(NAME);
   * 
   * tableLayout.addColumnData(fTableColumnLayouts[1]); column = new TableColumn(table, SWT.NONE, 1);
   * column.setResizable(fTableColumnLayouts[1].resizable); column.setText(CONTENT);
   * 
   * tableLayout.addColumnData(fTableColumnLayouts[2]); column = new TableColumn(table, SWT.NONE, 2);
   * column.setResizable(fTableColumnLayouts[1].resizable); column.setText("COloana3");
   * tableLayout.addColumnData(fTableColumnLayouts[3]); column = new TableColumn(table, SWT.NONE, 2);
   * column.setResizable(fTableColumnLayouts[1].resizable); column.setText("COloana4");
   * tableLayout.addColumnData(fTableColumnLayouts[4]); column = new TableColumn(table, SWT.NONE, 2);
   * column.setResizable(fTableColumnLayouts[1].resizable); // column.setText("COloana5");
   * tableLayout.addColumnData(fTableColumnLayouts[5]); column = new TableColumn(table, SWT.NONE, 2);
   * column.setResizable(fTableColumnLayouts[1].resizable); // column.setText("COloana6");
   * 
   * String[] currentTableItems = doGetPreferenceStore().getString("tableTitleBlock").split("#"); if
   * (currentTableItems.length >= 2) { for (int i = 1; i < currentTableItems.length; i += 2) { TableItem item = new
   * TableItem(table, SWT.NULL); item.setText(0, currentTableItems[i]); item.setText(1, currentTableItems[i + 1]); } }
   */

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
    /*
     * BooleanFieldEditor enableMonitoringFieldEditor = new BooleanFieldEditor("Enable Editor", "Custom value", group) {
     * 
     * @SuppressWarnings("synthetic-access")
     * 
     * @Override protected void valueChanged(boolean oldValue_p, boolean newValue_p) { super.valueChanged(oldValue_p,
     * newValue_p); columnsFieldEditor.setEnabled(newValue_p, group); } }; addField(enableMonitoringFieldEditor,
     * UserProfileModeEnum.Expert, group);
     */
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

    // labelControl = rowsFieldEditor.getLabelControl(group);
    // labelControl.setLayoutData(layoutData);
    // layoutData.horizontalIndent = 15;

    // columnsFieldEditor.setEnabled(doGetPreferenceStore().getBoolean("EnableCustomValue"), group);

  }

  @Override
  public boolean performOk() {

    doGetPreferenceStore().setValue("columnField1", columnsFieldEditor.getIntValue());
    doGetPreferenceStore().setValue("rowField", rowsFieldEditor.getIntValue());
    return super.performOk();
  }

  private TableViewerColumn createTableViewerColumn(final TableViewer viewer, final String title, final int bound) {
    final TableViewerColumn viewerColumn = new TableViewerColumn(viewer, SWT.NONE);
    final TableColumn column = viewerColumn.getColumn();
    column.setText(title);
    column.setWidth(bound);
    column.setResizable(true);
    return viewerColumn;
  }

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
  }

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

}
