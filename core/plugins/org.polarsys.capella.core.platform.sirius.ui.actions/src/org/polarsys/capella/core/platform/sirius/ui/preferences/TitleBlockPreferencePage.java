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

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.IntegerFieldEditor;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.polarsys.capella.core.commands.preferences.service.AbstractDefaultPreferencePage;
import org.polarsys.capella.core.preferences.Activator;

public class TitleBlockPreferencePage extends AbstractDefaultPreferencePage {

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

  private Table table;

  Button add_button;
  Button edit_button;
  Button remove_button;

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
    createButtons(top);

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

    Menu menu = new Menu(getShell(), SWT.POP_UP);
    table.setMenu(menu);
    MenuItem menu_item = new MenuItem(menu, SWT.PUSH);
    menu_item.setText(DELETE_SELECTION);
    menu_item.addListener(SWT.Selection, new Listener() {

      @Override
      public void handleEvent(Event event) {
        table.remove(table.getSelectionIndices());
      }
    });

  }

  private void createTable(Composite top) {
    TableLayout tableLayout = new TableLayout();

    table = new Table(top, SWT.MULTI | SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL | SWT.FULL_SELECTION);
    table.setLayout(tableLayout);
    table.setHeaderVisible(true);
    // table.setLinesVisible(true);
    table.setLinesVisible(true);

    table.setFont(top.getFont());

    GridData gridData = new GridData(GridData.FILL_BOTH);
    gridData.grabExcessVerticalSpace = true;
    gridData.grabExcessHorizontalSpace = true;
    gridData.verticalAlignment = GridData.FILL;
    gridData.horizontalAlignment = GridData.FILL;
    gridData.widthHint = 200;
    gridData.heightHint = table.getItemHeight();
    gridData.horizontalSpan = 1;
    table.setLayoutData(gridData);

    TableColumn column1 = new TableColumn(table, SWT.NONE);
    TableColumn column2 = new TableColumn(table, SWT.NONE);
    int nrOfColumns = doGetPreferenceStore().getInt("columnField1");

    column1.setWidth(150);
    column2.setWidth(150);

    for (int i = 0; i < 2; i++) {
      TableItem item = new TableItem(table, SWT.NONE);
      item.setText(new String[] { "", "" });
      /*
       * TableEditor editorw = new TableEditor(table); editorw = new TableEditor(table); Text txtLang = new Text(table,
       * SWT.BORDER); editorw.grabHorizontal = true; editorw.setEditor(txtLang, item, 0); editorw = new
       * TableEditor(table); Text txtLang2 = new Text(table, SWT.BORDER); editorw.grabHorizontal = true;
       * editorw.setEditor(txtLang2, item, 1);
       */

    }

    // column1.pack();
    // column2.pack();
    /*
     * final TableEditor editor = new TableEditor(table); // The editor must have the same size as the cell and must //
     * not be any smaller than 50 pixels. editor.horizontalAlignment = SWT.LEFT; editor.grabHorizontal = true;
     * editor.minimumWidth = 50; // editing the second column
     */

    table.addListener(SWT.MouseDown, new Listener() {

      @Override
      public void handleEvent(Event event) {
        Point pt = new Point(event.x, event.y);
        TableItem item = table.getItem(pt);
        if (item != null) {
          for (int col = 0; col < table.getColumnCount(); col++) {
            Rectangle rect = item.getBounds(col);
            if (rect.contains(pt)) {
              System.out.println("item clicked.");
              System.out.println("column is " + col);
              System.out.println(item.getText(col));
              String itemText = item.getText(col);
              String currentName = "";
              String currentContent = "";
              if (itemText != "") {
                itemText = itemText.replace(" ", "");
                currentName = itemText.split("-")[0];
                currentContent = itemText.split("-")[1];
              } //
              int index = table.getSelectionIndex();

              TitleBlockDialog dialog = new TitleBlockDialog(getShell());

              dialog.setCurrentName(currentName);
              dialog.setCurrentContent(currentContent);
              dialog.create();
              if (dialog.open() == Window.OK) {
                item.setText(col, dialog.getName() + " - " + dialog.getContent());

              }
            }

          }
        }
      }

    });

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
     * editorw = new TableEditor(table); Label lblReference2 = new Label(table, SWT.NONE);
     * lblReference2.setText("Value"); editorw.grabHorizontal = true; editorw.setEditor(lblReference2, x2, 2);
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
    int x = doGetPreferenceStore().getInt("columnField");
    columnsFieldEditor.setStringValue(String.valueOf(x));

    /*
     * addField(columnsFieldEditor, UserProfileModeEnum.Expert, group);
     */
    // Label labelControl = columnsFieldEditor.getLabelControl(group);
    // GridData layoutData = new GridData(SWT.FILL, SWT.FILL, false, false);
    // labelControl.setLayoutData(layoutData);
    // layoutData.horizontalIndent = 15;
    IntegerFieldEditor rowsFieldEditor = new IntegerFieldEditor("rowField", "Rows", group, 2);
    rowsFieldEditor.setValidRange(1, 50);
    int y = doGetPreferenceStore().getInt("rowField");
    rowsFieldEditor.setStringValue(String.valueOf(y));

    // labelControl = rowsFieldEditor.getLabelControl(group);
    // labelControl.setLayoutData(layoutData);
    // layoutData.horizontalIndent = 15;

    // columnsFieldEditor.setEnabled(doGetPreferenceStore().getBoolean("EnableCustomValue"), group);

  }

  @Override
  public boolean performOk() {
    doGetPreferenceStore().setValue("tableTitleBlock", "");
    String tableValuesToString = new String("");
    for (TableItem item : table.getItems()) {
      tableValuesToString = tableValuesToString + "#" + item.getText(0) + "#" + item.getText(1);
    }
    doGetPreferenceStore().setValue("tableTitleBlock", tableValuesToString);
    return super.performOk();
  }

}
