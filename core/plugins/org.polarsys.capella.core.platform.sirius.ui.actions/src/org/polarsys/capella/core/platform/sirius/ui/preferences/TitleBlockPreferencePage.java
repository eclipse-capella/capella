package org.polarsys.capella.core.platform.sirius.ui.preferences;

import org.eclipse.jface.preference.BooleanFieldEditor;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.IntegerFieldEditor;
import org.eclipse.jface.viewers.ColumnLayoutData;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
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

  private IntegerFieldEditor _delayFieldEditor;

  private TableViewer viewer;
  private Table table;

  Button edit_button;
  Button remove_button;

  public TitleBlockPreferencePage() {
    super(PAGE_ID);
    // TODO Auto-generated constructor stub
  }

  @Override
  protected IPreferenceStore doGetPreferenceStore() {
    return Activator.getDefault().getPreferenceStore();
  }

  @Override
  protected String getPageTitle() {
    return "Title Block";
  }

  @Override
  protected String getPageDescription() {
    // TODO Auto-generated method stub
    return "Description Title Block";
  }

  @Override
  protected void createFieldEditors() {
    final Group scmGroup = createGroup("Titlu Grup", "Tooltip grup", getFieldEditorParent());
    // Enable monitoring editor.
    BooleanFieldEditor enableMonitoringFieldEditor = new BooleanFieldEditor("Enable Editor", "Enable title", scmGroup) {
      /**
       * {@inheritDoc}
       */
      @SuppressWarnings("synthetic-access")
      @Override
      protected void valueChanged(boolean oldValue_p, boolean newValue_p) {
        super.valueChanged(oldValue_p, newValue_p);
        _delayFieldEditor.setEnabled(newValue_p, scmGroup);
      }
    };
    addField(enableMonitoringFieldEditor, UserProfileModeEnum.Expert, scmGroup);
    _delayFieldEditor = new IntegerFieldEditor("delayField", "delayFieldTitle", scmGroup, 3);
    _delayFieldEditor.setValidRange(1, 999);
    addField(_delayFieldEditor, UserProfileModeEnum.Expert, scmGroup);
    // Customize label layout data.
    Label labelControl = _delayFieldEditor.getLabelControl(scmGroup);
    GridData layoutData = new GridData(SWT.FILL, SWT.FILL, false, false);
    labelControl.setLayoutData(layoutData);
    layoutData.horizontalIndent = 15;
    // Depending on enablement of enableMonitoring editor, the text is editable or not.
    _delayFieldEditor.setEnabled(doGetPreferenceStore().getBoolean("EnableFileSyncMonitoring"), scmGroup);

    // scmGroup.pack();

    Composite top = new Composite(getFieldEditorParent(), SWT.NONE);
    // Grid Layout for top
    GridLayout top_layout = new GridLayout();
    top_layout.numColumns = 2;
    top_layout.marginHeight = 2;
    top_layout.marginWidth = 2;
    top.setLayout(top_layout);

    top.setLayoutData(new GridData(GridData.FILL_BOTH));

    TableLayout tableLayout = new TableLayout();

    Table table = new Table(top, SWT.MULTI | SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL | SWT.FULL_SELECTION);
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
    column.setText("Feature");

    TableViewer viewer = new TableViewer(table);
    viewer.setUseHashlookup(true);
    viewer.setColumnProperties(new String[] { "Nume_coloana", "Locatie coloana", "Status coloana" });

    for (int loopIndex = 0; loopIndex < 24; loopIndex++) {
      TableItem item = new TableItem(table, SWT.NULL);
      item.setText("Item " + loopIndex);
      item.setText(0, "Item " + loopIndex);
      item.setText(1, "Feature " + loopIndex);
    }
    TableItem item = table.getItem(1);
    System.out.println(item.getText(0));
    System.out.println(item.getText(1));
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
    Composite container = new Composite(top, SWT.NONE);
    GridLayout container_layout = new GridLayout();
    container_layout.marginHeight = 0;
    container_layout.marginWidth = 0;
    container.setLayout(container_layout);
    container.setLayoutData(new GridData(GridData.FILL_VERTICAL));
    container.setFont(top.getFont());

    Button add_button = new Button(container, SWT.PUSH);
    add_button.setFont(top.getFont());
    add_button.setText("Add");
    // add_button.addSelectionListener(this);
    this.setButtonLayoutData(add_button);
    edit_button = new Button(container, SWT.PUSH);
    edit_button.setFont(top.getFont());
    edit_button.setText("Edit");
    edit_button.setEnabled(false);
    this.setButtonLayoutData(edit_button);
    remove_button = new Button(container, SWT.PUSH);
    remove_button.setFont(top.getFont());
    remove_button.setText("Remove");
    remove_button.setEnabled(false);
    this.setButtonLayoutData(remove_button);

    add_button.addListener(SWT.Selection, new Listener() {
      @Override
      public void handleEvent(Event e) {
        switch (e.type) {
        case SWT.Selection:
          TitleBlockDialog dialog = new TitleBlockDialog(getShell());
          dialog.create();
          if (dialog.open() == Window.OK) {
            System.out.println(dialog.getFirstName());
            System.out.println(dialog.getLastName());

            TableItem item = new TableItem(table, SWT.NULL);
            item.setText("Item ");
            item.setText(0, dialog.getFirstName());
            item.setText(1, dialog.getLastName());
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
          try {
            int index = table.getSelectionIndex();
            if (index == -1) {
              throw new Exception();
            }
            System.out.println(index);

            TitleBlockDialog dialog = new TitleBlockDialog(getShell());
            dialog.create();
            if (dialog.open() == Window.OK) {

              System.out.println(dialog.getFirstName());
              System.out.println(dialog.getLastName());

              table.getItem(index).setText(0, dialog.getFirstName());
              table.getItem(index).setText(1, dialog.getLastName());
              edit_button.setEnabled(false);
              remove_button.setEnabled(false);
            }
          } catch (Exception ex) {
            TitleBlockDialog dialog = new TitleBlockDialog(getShell()) {
              @Override
              protected Control createDialogArea(Composite parent) {
                Composite area = (Composite) super.createDialogArea(parent);
                Composite container = new Composite(area, SWT.NONE);
                container.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
                GridLayout layout = new GridLayout(2, false);
                container.setLayout(layout);
                Label lbError = new Label(container, SWT.NONE);
                lbError.setText("Select a raw first!");
                return area;
              }

            };
            dialog.create();
          }

          break;
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

}
