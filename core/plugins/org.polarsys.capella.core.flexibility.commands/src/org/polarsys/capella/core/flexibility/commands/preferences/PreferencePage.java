/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.flexibility.commands.preferences;

import org.eclipse.core.commands.Category;
import org.eclipse.core.commands.Command;
import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.viewers.CellLabelProvider;
import org.eclipse.jface.viewers.CheckStateChangedEvent;
import org.eclipse.jface.viewers.CheckboxTreeViewer;
import org.eclipse.jface.viewers.ColumnPixelData;
import org.eclipse.jface.viewers.ColumnViewerToolTipSupport;
import org.eclipse.jface.viewers.ICheckStateListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.TreeViewerColumn;
import org.eclipse.jface.window.ToolTip;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.TreeColumn;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import org.polarsys.capella.core.flexibility.commands.Activator;
import org.polarsys.capella.core.flexibility.commands.dynamic.DynamicCommandsContentProvider;
import org.polarsys.capella.core.flexibility.commands.menus.provider.CommandsContentProvider;
import org.polarsys.capella.core.flexibility.commands.menus.provider.CommandsLabelProvider;
import org.polarsys.capella.core.flexibility.commands.menus.provider.MenuInput;
import org.polarsys.capella.core.flexibility.commands.menus.ui.PerspectiveOverrides;

/**
 * This class represents a preference page that is contributed to the Preferences dialog. By subclassing
 * <samp>FieldEditorPreferencePage</samp>, we can use the field support built into JFace that allows us to create a
 * page that is small and knows how to save, restore and apply itself.
 * <p>
 * This page is used to modify preferences only. They are stored in the preference store that belongs to the main
 * plug-in class. That way, preferences can be accessed directly via the preference store.
 */

public class PreferencePage extends FieldEditorPreferencePage implements IWorkbenchPreferencePage {

  public PreferencePage() {
    super(GRID);
    setPreferenceStore(Activator.getDefault().getPreferenceStore());
    setDescription(NLS.bind("All registered commands (v{0})", Activator.getDefault().getBundle().getVersion().toString()));
    setTitle("Flexibility");
    setMessage("Any changes will most likely take immediate effect regardless of OK/Cancel/Apply buttons", IMessageProvider.WARNING);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean performOk() {

    return super.performOk();
  }

  @Override
  protected void adjustGridLayout() {
    //
  }

  public static GridLayout createGridLayout(int numColumns, int marginBottom, int marginTop, int marginRight, int marginLeft, int marginHeight,
      int marginWidth, Integer verticalSpacing, Integer horizontalSpacing) {
    GridLayout gridLayout = new GridLayout();
    gridLayout.marginBottom = marginBottom;
    gridLayout.marginTop = marginTop;
    gridLayout.marginRight = marginRight;
    gridLayout.marginLeft = marginLeft;
    gridLayout.marginHeight = marginHeight;
    gridLayout.marginWidth = marginWidth;
    if (verticalSpacing != null) {
      gridLayout.verticalSpacing = verticalSpacing;
    }
    if (horizontalSpacing != null) {
      gridLayout.horizontalSpacing = horizontalSpacing;
    }
    gridLayout.numColumns = numColumns;
    return gridLayout;
  }

  public static GridData createGridData(int horizontalAlignment, int verticalAlignment, boolean grabExcessHorizontalSpace, boolean grabExcessVerticalSpace,
      int horizontalSpan, int verticalSpan, Integer widthHint, Integer heightHint) {
    GridData gridData = new GridData(horizontalAlignment, verticalAlignment, grabExcessHorizontalSpace, grabExcessVerticalSpace, horizontalSpan, verticalSpan);
    if (widthHint != null) {
      gridData.widthHint = widthHint;
    }
    if (heightHint != null) {
      gridData.heightHint = heightHint;
    }
    return gridData;
  }

  /**
   * Creates the field editors. Field editors are abstractions of the common GUI blocks needed to manipulate various
   * types of preferences. Each field editor knows how to save and restore itself.
   */
  TreeViewer viewer = null;

  @Override
  public void createFieldEditors() {

    Composite composite = getFieldEditorParent();

    createCommandPart(composite);

  }

  /**
   * @param composite_p
   */
  private void createCommandPart(Composite composite_p) {

    final CommandsContentProvider commandProvider = new DynamicCommandsContentProvider();

    CellLabelProvider labelProvider = new CommandsLabelProvider();

    //viewer = new TreeViewer(composite_p, SWT.FULL_SELECTION);
    viewer = new CheckboxTreeViewer(composite_p, SWT.FULL_SELECTION);
    viewer.setContentProvider(commandProvider);
    TableLayout layout = new TableLayout();

    ColumnViewerToolTipSupport.enableFor(viewer, ToolTip.NO_RECREATE);

    viewer.getTree().setLinesVisible(true);
    //Column 1
    TreeViewerColumn column = new TreeViewerColumn(viewer, SWT.LEFT);
    column.setLabelProvider(labelProvider);
    layout.addColumnData(new ColumnPixelData(220, true, true));
    column.getColumn().setText("Command");

    //Column 2
    TreeViewerColumn column2 = new TreeViewerColumn(viewer, SWT.LEFT);
    column2.setLabelProvider(labelProvider);
    layout.addColumnData(new ColumnPixelData(280, true, true));
    column2.getColumn().setText("Description");

    TreeColumn[] a = viewer.getTree().getColumns();

    viewer.getTree().setLayoutData(createGridData(SWT.FILL, SWT.FILL, true, true, 0, 0, null, 250));
    viewer.getTree().setLayout(createGridLayout(1, 0, 0, 0, 0, 0, 0, 0, 0));

    viewer.getTree().setLayout(layout);
    viewer.getTree().setHeaderVisible(true);
    viewer.getTree().setLinesVisible(true);

    viewer.setInput(new MenuInput(Activator.getDefault().getWorkbench(), new ISelectionProvider() {

      public void setSelection(ISelection selection_p) {
        //Do nothing.
      }

      public void removeSelectionChangedListener(ISelectionChangedListener listener_p) {
        //Do nothing.
      }

      public ISelection getSelection() {
        return new StructuredSelection();
      }

      public void addSelectionChangedListener(ISelectionChangedListener listener_p) {
        //Do nothing.
      }
    }));

    if (viewer instanceof CheckboxTreeViewer) {
      ((CheckboxTreeViewer) viewer).setCheckStateProvider(commandProvider);
      ((CheckboxTreeViewer) viewer).addCheckStateListener(new ICheckStateListener() {
        public void checkStateChanged(CheckStateChangedEvent event) {
          Object element = event.getElement();
          if (element instanceof Command) {
            if (event.getChecked()) {
              PerspectiveOverrides.unregisterHiddenMenu(((Command) element).getId());
            } else {
              PerspectiveOverrides.registerHiddenMenu(((Command) element).getId());
            }
          }
          if (element instanceof Category) {
            for (Object object : commandProvider.getElements(element)) {
              ((CheckboxTreeViewer) viewer).setChecked(object, event.getChecked());

              checkStateChanged(new CheckStateChangedEvent((CheckboxTreeViewer) viewer, object, event.getChecked()));
            }
          }
        }
      });
    }

    //CellEditor[] editors = new CellEditor[2];
    //editors[0] = new CheckboxCellEditor(viewer.getTree());
    //editors[1] = new TextCellEditor(viewer.getTree(), SWT.NONE);
    //viewer.setCellEditors(editors);
    //viewer.setColumnProperties(new String[] { column.getColumn().getText(), column2.getColumn().getText() });

  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.ui.IWorkbenchPreferencePage#init(org.eclipse.ui.IWorkbench)
   */
  public void init(IWorkbench workbench) {
  }

}
