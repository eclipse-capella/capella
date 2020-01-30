package org.polarsys.capella.core.platform.sirius.ui.preferences;

import org.eclipse.jface.preference.BooleanFieldEditor;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.IntegerFieldEditor;
import org.eclipse.jface.viewers.ColumnLayoutData;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.polarsys.capella.core.commands.preferences.service.AbstractDefaultPreferencePage;
import org.polarsys.capella.core.commands.preferences.service.UserProfileModeEnum;
import org.polarsys.capella.core.preferences.Activator;

public class TitleBlockPreferencePage extends AbstractDefaultPreferencePage {

  public static final String PAGE_ID = "org.polarsys.capella.core.platform.sirius.ui.actions.preferences.TitleBlockPage";

  private IntegerFieldEditor _delayFieldEditor;

  private TableViewer viewer;
  private Table table;

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
    Button edit_button = new Button(container, SWT.PUSH);
    edit_button.setFont(top.getFont());
    edit_button.setText("Edit");
    this.setButtonLayoutData(edit_button);
    Button remove_button = new Button(container, SWT.PUSH);
    remove_button.setFont(top.getFont());
    remove_button.setText("Remove");
    this.setButtonLayoutData(remove_button);

    // viewer.setContentProvider(new ICContentProvider());
    // viewer.setLabelProvider(new ICLabelProvider());

    /*
     * Composite composite1 = new Composite(getFieldEditorParent(), SWT.NONE); GridLayout layout1 = new GridLayout();
     * layout1.numColumns = 3; composite1.setLayout(layout1);
     * 
     * Composite composite2 = new Composite(composite1, SWT.FILL); GridLayout layout = new GridLayout();
     * layout.numColumns = 2; layout.marginHeight = 2; layout.marginWidth = 2; composite2.setLayout(layout); GridData
     * gridData_c1 = new GridData(); gridData_c1.horizontalAlignment = GridData.FILL; gridData_c1.horizontalSpan = 2;
     * composite2.setLayoutData(gridData_c1);
     * 
     * Table table = new Table(composite2, SWT.MULTI | SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL | SWT.FULL_SELECTION);
     * TableLayout tableLayout = new TableLayout(); table.setLayout(tableLayout); table.setHeaderVisible(true);
     * table.setLinesVisible(true); table.setFont(composite2.getFont());
     * 
     * GridData gridData = new GridData(GridData.FILL_BOTH); gridData.grabExcessVerticalSpace = true;
     * gridData.grabExcessHorizontalSpace = true; gridData.verticalAlignment = GridData.FILL;
     * gridData.horizontalAlignment = GridData.FILL; gridData.widthHint = 200; gridData.heightHint =
     * table.getItemHeight(); gridData.horizontalSpan = 2; table.setLayoutData(gridData);
     * 
     * ColumnLayoutData[] fTableColumnLayouts = { new ColumnWeightData(400), new ColumnWeightData(400) }; TableColumn
     * column;
     * 
     * tableLayout.addColumnData(fTableColumnLayouts[0]); column = new TableColumn(table, SWT.NONE, 0);
     * column.setResizable(fTableColumnLayouts[0].resizable); column.setText("Name");
     * 
     * tableLayout.addColumnData(fTableColumnLayouts[1]); column = new TableColumn(table, SWT.NONE, 1);
     * column.setResizable(fTableColumnLayouts[1].resizable); column.setText("Feature");
     * 
     * Composite container3 = new Composite(composite1, SWT.NONE); GridLayout layout2 = new GridLayout();
     * layout.marginHeight = 0; layout.marginWidth = 0; container3.setLayout(layout); container3.setLayoutData(new
     * GridData(GridData.FILL_VERTICAL)); container3.setFont(composite1.getFont());
     * 
     * Button button = new Button(container3, SWT.PUSH); button.setFont(container3.getFont()); button.setText("Add");
     * GridData data = new GridData(GridData.HORIZONTAL_ALIGN_FILL); int widthHint =
     * convertHorizontalDLUsToPixels(IDialogConstants.BUTTON_WIDTH); Point minSize = button.computeSize(SWT.DEFAULT,
     * SWT.DEFAULT, true); data.widthHint = Math.max(widthHint, minSize.x); button.setLayoutData(data);
     * 
     * 
     */

    /*
     * GridLayout gridLayout = new GridLayout();
     * 
     * gridLayout.numColumns = 3;
     * 
     * composite2.setLayout(gridLayout);
     * 
     * viewer = new TableViewer(composite2, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL | SWT.FULL_SELECTION | SWT.BORDER);
     * TableViewerColumn col = createTableViewerColumn("Nume", 20, 0); col = createTableViewerColumn("Feature", 20, 0);
     * table = viewer.getTable(); table.setHeaderVisible(true); table.setLinesVisible(true);
     * 
     * GridData gridData = new GridData(); gridData.verticalAlignment = GridData.FILL; gridData.horizontalSpan = 2;
     * gridData.grabExcessHorizontalSpace = true; gridData.grabExcessVerticalSpace = true; gridData.horizontalAlignment
     * = GridData.FILL; viewer.getControl().setLayoutData(gridData);
     * 
     * new Button(composite2, SWT.PUSH).setText("Add");
     * 
     * new Button(composite2, SWT.PUSH).setText("Remove");
     */

  }

  private TableViewerColumn createTableViewerColumn(String string, int i, int j) {
    TableViewerColumn viewerColumn = new TableViewerColumn(viewer, SWT.NONE);
    TableColumn column = viewerColumn.getColumn();
    column.setText(string);
    column.setWidth(i);
    column.setResizable(true);
    column.setMoveable(true);
    return viewerColumn;
  }

}
