/*******************************************************************************
 * Copyright (c) 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.sirius.analysis.preferences;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.ProjectScope;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.layout.GridDataFactory;
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
import org.eclipse.swt.custom.StyleRange;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.polarsys.capella.core.commands.preferences.service.AbstractDefaultPreferencePage;
import org.polarsys.capella.core.commands.preferences.service.PreferenceField;
import org.polarsys.capella.core.commands.preferences.service.UserProfileModeEnum;
import org.polarsys.capella.core.preferences.Activator;
import org.polarsys.capella.core.sirius.analysis.activator.SiriusViewActivator;

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
      return name+"\n"+content; //$NON-NLS-1$
    }
  }

  public static final int MIN_CELL_WIDTH = 100;
  public static final String PAGE_ID = "org.polarsys.capella.core.platform.sirius.ui.actions.preferences.TitleBlockPage"; //$NON-NLS-1$
  public static final String DEFAULT_TITLEBLOCK_PREFERENCE_STORE = "defaultTitleBlock"; //$NON-NLS-1$
  public static final String TABEL_CONTENT_PREFERENCE_STORE = "tableTitleBlock"; //$NON-NLS-1$
  public static final String COLUMNS_NUMBER_PREFERENCE_STORE = "columnsNumberTitleBlock"; //$NON-NLS-1$
  public static final String LINES_NUMBER_PREFERENCE_STORE = "linesNumberTitleBlock"; //$NON-NLS-1$
  public static final String SEPARATOR = "SEPARATOR"; //$NON-NLS-1$
  public static final String EMPTY_STRING = ""; //$NON-NLS-1$
  public static final int BOUND = 200;

  public static final String IMAGE_LINE = "icons/full/obj16/TitleBlockLine.gif"; //$NON-NLS-1$
  public static final String IMAGE_COLUMN = "icons/full/obj16/TitleBlockColumn.gif"; //$NON-NLS-1$
  public static final String IMAGE_LINE_REMOVE = "icons/full/obj16/TitleBlockLineRemove.gif"; //$NON-NLS-1$
  public static final String IMAGE_COLUMN_REMOVE = "icons/full/obj16/TitleBlockColumnRemove.gif"; //$NON-NLS-1$
  
  private TableViewer v;
  private PreferenceField defaultTitleBlockFieldEditor;
  private List<List<TitleBlockCell>> tccMatrix;
  private int columnsNumber;
  private int linesNumber;
  private String tableContent;

  public TitleBlockPreferencePage() {
    super(PAGE_ID);
    tableContent = doGetPreferenceStore().getString(TABEL_CONTENT_PREFERENCE_STORE);
    columnsNumber = doGetPreferenceStore().getInt(COLUMNS_NUMBER_PREFERENCE_STORE);
    linesNumber = doGetPreferenceStore().getInt(LINES_NUMBER_PREFERENCE_STORE);
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
    createExplanationLabel();
    
  }

  private void createExplanationLabel() {
    Label explanation_label = new Label(getFieldEditorParent(), SWT.NONE);
    explanation_label.setText(Messages.TitleBlockPreferencePage_Message);
    Font font = new Font(getShell().getDisplay(), "", 9, SWT.ITALIC); //$NON-NLS-1$
    explanation_label.setFont(font);
  }

  private void createCheckBox() {
    defaultTitleBlockFieldEditor = new PreferenceField(DEFAULT_TITLEBLOCK_PREFERENCE_STORE, Messages.TitleBlockPreferencePage_AddDefaultTitleBlock,
        getFieldEditorParent());
    addField(defaultTitleBlockFieldEditor, UserProfileModeEnum.Expert, getFieldEditorParent(), ProjectScope.class);
  }

  private void createTable(Composite top) {
    v = new TableViewer(top, SWT.MULTI | SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL | SWT.FULL_SELECTION);
    createColumns(v, columnsNumber);
    
    v.setContentProvider(ArrayContentProvider.getInstance());
    v.getTable().setLinesVisible(true);
    v.getTable().setHeaderVisible(false);
    v.getTable().setLayout(new GridLayout());
    v.getTable().setLayoutData(GridDataFactory.swtDefaults().grab(true, true).create());

    addMenu(v);

    Listener tableListener = new Listener() {
      @Override
      public void handleEvent(Event event) {
        switch (event.type) {
        case SWT.MouseDoubleClick: {
          Point coords = new Point(event.x, event.y);
          ViewerCell cell = v.getCell(coords);
          if (cell != null) {
            int index = cell.getColumnIndex();
            List<TitleBlockCell> list = (List<TitleBlockCell>) cell.getElement();
            TitleBlockDialog dialog = new TitleBlockDialog(getShell());
            dialog.setCurrentName(list.get(index).name);
            dialog.setCurrentContent(list.get(index).content);
            dialog.create();
            if (dialog.open() == Window.OK) {
              list.get(index).name = dialog.getName();
              list.get(index).content = dialog.getContent();
              cell.setText(list.get(index).toString());
            }
          }
          break;
        }
        }
      }
    };

    v.getTable().addListener(SWT.MouseDoubleClick, tableListener);
    v.getTable().addListener(SWT.MouseDown, tableListener);
    
    Listener paintListener = new Listener() {
      public void handleEvent(Event event) {
        switch (event.type) {
        case SWT.MeasureItem: {
          TableItem item = (TableItem) event.item;
          String text = getText(item, event.index);
          Point size = event.gc.textExtent(text);
          event.height = Math.max(event.height, size.y + 10);
          break;
        }
        }
      }

      String getText(TableItem item, int column) {
        String text = item.getText(column);
        return text;
      }
    };
    v.getTable().addListener(SWT.MeasureItem, paintListener);
    v.setInput(createModel());
  }

  private void disposeColumns() {
    TableColumn[] columns = v.getTable().getColumns();
    for (TableColumn tc : columns) {
      tc.dispose();
    }
  }

  private void refreshTableInsertColumns(int index) {
    disposeColumns();
    columnsNumber += 1;
    createColumns(v, columnsNumber);
    v.setInput(insertNewEmptyColumn(index));
  }

  private void refreshTableRemoveColumn(int index) {
    disposeColumns();
    columnsNumber -= 1;
    createColumns(v, columnsNumber);
    v.setInput(removeSelectedColumn(index));
  }

  private void refreshTableInsertLines(int index) {
    v.setInput(insertNewEmptyLine(index));
  }

  private void refreshTableRemoveLine() {
    int lineToDelete = v.getTable().getSelectionIndex();
    v.setInput(removeSelectedLine(lineToDelete));
  }

  @Override
  public boolean performOk() {

    String currentTableContent = EMPTY_STRING;
    for (int i = 0; i < linesNumber; i++) {
      for (int j = 0; j < columnsNumber; j++) {
        if (i == linesNumber - 1 && j == columnsNumber - 1) {
          if (tccMatrix.get(i).get(j).content.equals(EMPTY_STRING)) {
        	  tccMatrix.get(i).get(j).content = " ";
          }
          currentTableContent = currentTableContent + tccMatrix.get(i).get(j).name + SEPARATOR
              + tccMatrix.get(i).get(j).content;
        } else {             	 
          currentTableContent = currentTableContent + tccMatrix.get(i).get(j).name + SEPARATOR
              + tccMatrix.get(i).get(j).content + SEPARATOR;
        }
      }
    }
    doGetPreferenceStore().setValue(DEFAULT_TITLEBLOCK_PREFERENCE_STORE,
        defaultTitleBlockFieldEditor.getBooleanValue());
    doGetPreferenceStore().setValue(COLUMNS_NUMBER_PREFERENCE_STORE, columnsNumber);
    doGetPreferenceStore().setValue(LINES_NUMBER_PREFERENCE_STORE, linesNumber);
    doGetPreferenceStore().setValue(TABEL_CONTENT_PREFERENCE_STORE, currentTableContent);
    return super.performOk();
  }

  private List<List<TitleBlockCell>> insertNewEmptyColumn(int index) {
    if (index == -1) {
      for (int i = 0; i < linesNumber; i++) {
        List<TitleBlockCell> tbcCell = tccMatrix.get(i);
        tbcCell.add(new TitleBlockCell(EMPTY_STRING, EMPTY_STRING));
      }
    } else {
      for (int i = 0; i < linesNumber; i++) {
        List<TitleBlockCell> tbcCell = tccMatrix.get(i);
        tbcCell.add(index, new TitleBlockCell(EMPTY_STRING, EMPTY_STRING));
      }
    }
    return tccMatrix;
  }

  private List<List<TitleBlockCell>> insertNewEmptyLine(int index) {
    linesNumber += 1;
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

  private List<List<TitleBlockCell>> removeSelectedLine(int lineToDelete) {
    tccMatrix.remove(lineToDelete);
    linesNumber -= 1;
    return tccMatrix;
  }

  private List<List<TitleBlockCell>> removeSelectedColumn(int columnToDelete) {
    for (int i = 0; i < linesNumber; i++) {
      tccMatrix.get(i).remove(columnToDelete);
    }
    return tccMatrix;
  }

  private List<List<TitleBlockCell>> createModel() {
    String[] cellsNameAndContent = tableContent.split(SEPARATOR);
    int currentIndex = 0;
    tccMatrix = new ArrayList<>();
    for (int i = 0; i < linesNumber; i++) {
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
        if (cellText != null && !cellText.trim().isEmpty()) {
          cell.setText(cellText);
          int multiLine = cellText.indexOf("\n"); //$NON-NLS-1$
          if (multiLine > 0) {
            cell.setStyleRanges(new StyleRange[] {
                new StyleRange(multiLine, cellText.length(), getShell().getDisplay().getSystemColor(SWT.COLOR_DARK_GRAY), null)
            });
          }
        } else {
            cell.setText(Messages.TitleBlockPreferencePage_EmptyMessage);
            cell.setForeground(getShell().getDisplay().getSystemColor(SWT.COLOR_DARK_GRAY));
        }
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
      int columnWidth = wd / columnsNumber;
      if (columnWidth > MIN_CELL_WIDTH) {
        column.setWidth(columnWidth);
      } else {
        column.setWidth(MIN_CELL_WIDTH);
      }
    } else {
      column.setWidth(bound);
    }
    column.setResizable(true);
    return viewerColumn;
  }

  private void addMenu(final TableViewer v) {
    final MenuManager mgr = new MenuManager();

    final Action insertLine = new Action(Messages.TitleBlockPreferencePage_InsertLine) {
      @Override
      public void run() {
        int lineToInsert = v.getTable().getSelectionIndex() + 1;
        if (lineToInsert != 0) {
          refreshTableInsertLines(lineToInsert);
        }
      }
    };
    insertLine.setImageDescriptor(SiriusViewActivator.imageDescriptorFromPlugin(SiriusViewActivator.ID, IMAGE_LINE));

    final Action insertColumn = new Action(Messages.TitleBlockPreferencePage_InsertColumn) {
      @Override
      public void run() {
        if (v.getColumnViewerEditor().getFocusCell() != null) {
          createColumn(v, EMPTY_STRING, v.getTable().getColumnCount());
          int columnToInsert = v.getColumnViewerEditor().getFocusCell().getColumnIndex() + 1;
          refreshTableInsertColumns(columnToInsert);
        }
      }
    };
    insertColumn.setImageDescriptor(SiriusViewActivator.imageDescriptorFromPlugin(SiriusViewActivator.ID, IMAGE_COLUMN));

    final Action removeLine = new Action(Messages.TitleBlockPreferencePage_RemoveLine) {
      @Override
      public void run() {
        if (linesNumber == 1) {
          createMessageBox(Messages.TitleBlockPreferencePage_RemoveLastLineError, EMPTY_STRING);
        } else if (v.getColumnViewerEditor().getFocusCell() != null) {

          int lineToDelete = v.getTable().getSelectionIndex();
          if (lineToDelete != -1) {
            MessageBox messageBox = new MessageBox(getShell(), SWT.ICON_QUESTION | SWT.YES | SWT.NO);
            messageBox.setMessage(Messages.TitleBlockPreferencePage_DeleteEntireRow);
            messageBox.setText(Messages.TitleBlockPreferencePage_ConfirmDeletion);
            int response = messageBox.open();
            if (response == SWT.YES) {
              refreshTableRemoveLine();
            }
          }
        }
      }
    };
    removeLine.setImageDescriptor(SiriusViewActivator.imageDescriptorFromPlugin(SiriusViewActivator.ID, IMAGE_LINE_REMOVE));

    final Action removeColumn = new Action(Messages.TitleBlockPreferencePage_RemoveColumn) {
      @Override
      public void run() {
        if (columnsNumber == 1) {
          createMessageBox(Messages.TitleBlockPreferencePage_RemoveLastColumnError, EMPTY_STRING);
        }
        if (v.getColumnViewerEditor().getFocusCell() != null && columnsNumber > 1) {

          MessageBox messageBox = new MessageBox(getShell(), SWT.ICON_QUESTION | SWT.YES | SWT.NO);
          messageBox.setMessage(Messages.TitleBlockPreferencePage_DeleteEntireColumn);
          messageBox.setText(Messages.TitleBlockPreferencePage_ConfirmDeletion);
          int response = messageBox.open();
          if (response == SWT.YES) {
            int columnToDelete = v.getColumnViewerEditor().getFocusCell().getColumnIndex();
            refreshTableRemoveColumn(columnToDelete);
          }
        }
      }
    };
    removeColumn.setImageDescriptor(SiriusViewActivator.imageDescriptorFromPlugin(SiriusViewActivator.ID, IMAGE_COLUMN_REMOVE));

    mgr.setRemoveAllWhenShown(true);
    mgr.addMenuListener(manager -> {
      if (v.getTable().getColumnCount() >= 1 && linesNumber >= 1) {
        manager.add(insertLine);
        manager.add(insertColumn);
        manager.add(removeLine);
        manager.add(removeColumn);
      }
    });
    v.getControl().setMenu(mgr.createContextMenu(v.getControl()));
  }

  private void createMessageBox(String messageToDisplay, String textBox) {
    MessageBox box = new MessageBox(getShell(), SWT.ICON_WARNING | SWT.OK);
    box.setMessage(messageToDisplay);
    box.setText(textBox);
    box.open();
  }

  @Override
  protected void performDefaults() {
    super.performDefaults();
    columnsNumber = doGetPreferenceStore().getDefaultInt(COLUMNS_NUMBER_PREFERENCE_STORE);
    linesNumber = doGetPreferenceStore().getDefaultInt(LINES_NUMBER_PREFERENCE_STORE);
    tableContent = doGetPreferenceStore().getDefaultString(TABEL_CONTENT_PREFERENCE_STORE);
    disposeColumns();
    createColumns(v, columnsNumber);
    v.setInput(createModel());
  }

}
