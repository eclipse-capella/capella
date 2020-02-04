package org.polarsys.capella.core.platform.sirius.ui.preferences;

import org.eclipse.jface.preference.BooleanFieldEditor;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.IntegerFieldEditor;
import org.eclipse.jface.viewers.ColumnLayoutData;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.polarsys.capella.core.commands.preferences.service.AbstractDefaultPreferencePage;
import org.polarsys.capella.core.commands.preferences.service.UserProfileModeEnum;
import org.polarsys.capella.core.preferences.Activator;

public class TitleBlockPreferencePage extends AbstractDefaultPreferencePage {

  public static final String PAGE_ID = "org.polarsys.capella.core.platform.sirius.ui.actions.preferences.TitleBlockPage";

  private IntegerFieldEditor _columnsFieldEditor;

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
    // TODO Auto-generated method stub
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

    add_button = createButton(top, container, "Add", true);

    edit_button = createButton(top, container, "Edit", false);

    remove_button = createButton(top, container, "Remove", false);

    add_button.addListener(SWT.Selection, new Listener() {
      @Override
      public void handleEvent(Event e) {
        switch (e.type) {
        case SWT.Selection:
          TitleBlockDialog dialog = new TitleBlockDialog(getShell());
          dialog.create();
          if (dialog.open() == Window.OK) {
            System.out.println(dialog.getName());
            System.out.println(dialog.getContent());

            TableItem item = new TableItem(table, SWT.NULL);
            item.setText("Item ");
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
          TitleBlockDialog dialog = new TitleBlockDialog(getShell());
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
    menu_item.setText("Delete Selection");
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
    ColumnLayoutData[] fTableColumnLayouts = { new ColumnWeightData(165), new ColumnWeightData(165), };

    TableColumn column;

    tableLayout.addColumnData(fTableColumnLayouts[0]);
    column = new TableColumn(table, SWT.NONE, 0);
    column.setResizable(fTableColumnLayouts[0].resizable);
    column.setText("Name");

    tableLayout.addColumnData(fTableColumnLayouts[1]);
    column = new TableColumn(table, SWT.NONE, 1);
    column.setResizable(fTableColumnLayouts[1].resizable);
    column.setText("Content");

    table.addListener(SWT.Selection, new Listener() {
      @Override
      public void handleEvent(Event event) {
        int index = table.getSelectionIndex();
        if (index != -1) {
          edit_button.setEnabled(true);
          remove_button.setEnabled(true);
        }
      }
    });

  }

  private void createGroupForNumberOfColumns() {
    final Group group = createGroup("Number of columns in TitleBlock", "Tooltip grup", getFieldEditorParent());
    BooleanFieldEditor enableMonitoringFieldEditor = new BooleanFieldEditor("Enable Editor", "Custom value", group) {
      @SuppressWarnings("synthetic-access")
      @Override
      protected void valueChanged(boolean oldValue_p, boolean newValue_p) {
        super.valueChanged(oldValue_p, newValue_p);
        _columnsFieldEditor.setEnabled(newValue_p, group);
      }
    };
    addField(enableMonitoringFieldEditor, UserProfileModeEnum.Expert, group);
    _columnsFieldEditor = new IntegerFieldEditor("columnField", "NrOfColumns", group, 3);
    _columnsFieldEditor.setValidRange(1, 50);
    addField(_columnsFieldEditor, UserProfileModeEnum.Expert, group);
    Label labelControl = _columnsFieldEditor.getLabelControl(group);
    GridData layoutData = new GridData(SWT.FILL, SWT.FILL, false, false);
    labelControl.setLayoutData(layoutData);
    layoutData.horizontalIndent = 15;
    _columnsFieldEditor.setEnabled(doGetPreferenceStore().getBoolean("EnableFileSyncMonitoring"), group);

  }

}
