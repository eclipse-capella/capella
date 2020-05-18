/*******************************************************************************
 * Copyright (c) 2006, 2019 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.ui.properties.viewers;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.ICellModifier;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Item;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;
import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.core.data.capellacore.AbstractPropertyValue;
import org.polarsys.capella.core.ui.properties.CapellaUIPropertiesPlugin;

/**
 */
public abstract class AbstractDelegatedViewer implements IDelegatedViewer {

  /**
   * 
   */
  protected static final int DEFAULT_COLUMN_BOUND = 200;

  /**
   * 
   */
  private Composite _viewerGroup;
  protected ColumnViewer _columnViewer;
  protected TabbedPropertySheetWidgetFactory _widgetFactory;
  private ICellEditorProvider _cellEditorProvider;

  /**
   * 
   */
  private ISelection _selection;
  private List<ISelectionChangedListener> _listeners;

  /**
   * @param widgetFactory
   * @param cellEditorProvider
   */
  public AbstractDelegatedViewer(TabbedPropertySheetWidgetFactory widgetFactory, ICellEditorProvider cellEditorProvider) {
    _widgetFactory = widgetFactory;
    _cellEditorProvider = cellEditorProvider;
  }

  /**
   * @param composite
   */
  public void createCellEditors(final Composite composite) {
    if (null == getCellEditorProvider()) {
      return;
    }

    final CellEditor[] cellEditors = new CellEditor[getColumnProperties().length];
    for (int i=0; i<getColumnProperties().length; i++) {
      cellEditors[i] = getCellEditorProvider().getCellEditor(composite, i, null);
    }
    getColumnViewer().setCellEditors(cellEditors);
    getColumnViewer().setColumnProperties(getColumnProperties());
    getColumnViewer().setCellModifier(new ICellModifier() {
      public boolean canModify(Object element, String property) {
        if (getColumnProperties()[1].equals(property)) {
          // set the correct cell editor for this element
          cellEditors[1] = getCellEditorProvider().getCellEditor(composite, 1, element);
        }
        return element instanceof AbstractPropertyValue;
      }
      public Object getValue(Object element, String property) {
        for (int i=0; i<getColumnProperties().length; i++) {
          if (getColumnProperties()[i].equals(property))
            return getCellEditorProvider().getElementValue((EObject) element, i);
        }
        return null;
      }
      public void modify(Object element, String property, Object value) {
        for (int i=0; i<getColumnProperties().length; i++) {
          if (getColumnProperties()[i].equals(property))
            modifyElement((EObject) ((Item) element).getData(), i, value);
        }
      }
    });
  }

  /**
   * @return
   */
  public ColumnViewer getColumnViewer() {
    return _columnViewer;
  }

  /**
   * @return
   */
  public ICellEditorProvider getCellEditorProvider() {
    return _cellEditorProvider;
  }

  /**
   * 
   */
  protected void addViewerListeners() {
    if (null != _columnViewer) {
      _columnViewer.addDoubleClickListener(new IDoubleClickListener() {
        public void doubleClick(DoubleClickEvent event) {
          handleDoubleClick(event.getSelection());
        }
      });
      _columnViewer.addSelectionChangedListener(new ISelectionChangedListener() {
        public void selectionChanged(SelectionChangedEvent event) {
          setSelection(event.getSelection());
        }
      });
    }
  }

  /**
   * @return TRUE if columns have been created, FALSE otherwise
   */
  protected boolean createViewerColumns() {
    return false;
  }

  /**
   * @return
   */
  protected String[] getColumnProperties() {
    return null;
  }

  /**
   * @param element
   * @param column
   * @param value
   */
  protected void modifyElement(EObject element, int column, Object value) {
    // do nothing
  }

  /**
   * Create the composite that host the table with buttons to drive its content : add, remove, up and down actions.
   */
  public Composite getViewerGroup(Composite parent) {
    if (null == _viewerGroup) {
      _viewerGroup = _widgetFactory.createComposite(parent, SWT.NONE);
      _viewerGroup.setLayoutData(new GridData(GridData.FILL, GridData.FILL, true, true));
      GridLayout layout = new GridLayout(5 /* since 4 actions + 1 empty label */, false);
      layout.horizontalSpacing = 0;
      layout.verticalSpacing = 0;
      _viewerGroup.setLayout(layout);
    }
    return _viewerGroup;
  }

  /**
   * Handle double click.
   * @param selection current selection
   */
  protected void handleDoubleClick(ISelection selection) {
    if (selection instanceof StructuredSelection) {
      Object selectedElement = ((StructuredSelection) selection).getFirstElement();
      if (selectedElement instanceof ModelElement) {
        CapellaUIPropertiesPlugin.getDefault().openWizard((ModelElement) selectedElement);
        _columnViewer.refresh();
      }
    }
  }

  /**
   * {@inheritDoc}
   */
  public void addSelectionChangedListener(ISelectionChangedListener listener) {
    if (null == _listeners) {
      _listeners = new ArrayList<ISelectionChangedListener>();
    }
    _listeners.add(listener);
  }

  /**
   * {@inheritDoc}
   */
  public ISelection getSelection() {
    return _selection;
  }

  /**
   * {@inheritDoc}
   */
  public void removeSelectionChangedListener(ISelectionChangedListener listener) {
    if (null != _listeners) {
      _listeners.remove(listener);
    }
  }

  /**
   * {@inheritDoc}
   */
  public void setSelection(ISelection selection) {
    _selection = selection;
    for (ISelectionChangedListener listener : _listeners) {
      listener.selectionChanged(new SelectionChangedEvent(this, _selection));
    }
  }
}
