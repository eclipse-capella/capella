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

import org.polarsys.capella.core.data.capellacore.AbstractPropertyValue;
import org.polarsys.capella.core.ui.properties.CapellaUIPropertiesPlugin;
import org.polarsys.capella.common.data.modellingcore.ModelElement;

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
   * @param widgetFactory_p
   * @param cellEditorProvider_p
   */
  public AbstractDelegatedViewer(TabbedPropertySheetWidgetFactory widgetFactory_p, ICellEditorProvider cellEditorProvider_p) {
    _widgetFactory = widgetFactory_p;
    _cellEditorProvider = cellEditorProvider_p;
  }

  /**
   * @param composite_p
   */
  public void createCellEditors(final Composite composite_p) {
    if (null == getCellEditorProvider()) {
      return;
    }

    final CellEditor[] cellEditors = new CellEditor[getColumnProperties().length];
    for (int i=0; i<getColumnProperties().length; i++) {
      cellEditors[i] = getCellEditorProvider().getCellEditor(composite_p, i, null);
    }
    getColumnViewer().setCellEditors(cellEditors);
    getColumnViewer().setColumnProperties(getColumnProperties());
    getColumnViewer().setCellModifier(new ICellModifier() {
      public boolean canModify(Object element_p, String property_p) {
        if (getColumnProperties()[1].equals(property_p)) {
          // set the correct cell editor for this element
          cellEditors[1] = getCellEditorProvider().getCellEditor(composite_p, 1, element_p);
        }
        return element_p instanceof AbstractPropertyValue;
      }
      public Object getValue(Object element_p, String property_p) {
        for (int i=0; i<getColumnProperties().length; i++) {
          if (getColumnProperties()[i].equals(property_p))
            return getCellEditorProvider().getElementValue((EObject) element_p, i);
        }
        return null;
      }
      public void modify(Object element_p, String property_p, Object value_p) {
        for (int i=0; i<getColumnProperties().length; i++) {
          if (getColumnProperties()[i].equals(property_p))
            modifyElement((EObject) ((Item) element_p).getData(), i, value_p);
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
        public void doubleClick(DoubleClickEvent event_p) {
          handleDoubleClick(event_p.getSelection());
        }
      });
      _columnViewer.addSelectionChangedListener(new ISelectionChangedListener() {
        public void selectionChanged(SelectionChangedEvent event_p) {
          setSelection(event_p.getSelection());
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
   * @param element_p
   * @param column_p
   * @param value_p
   */
  protected void modifyElement(EObject element_p, int column_p, Object value_p) {
    // do nothing
  }

  /**
   * Create the composite that host the table with buttons to drive its content : add, remove, up and down actions.
   */
  public Composite getViewerGroup(Composite parent_p) {
    if (null == _viewerGroup) {
      _viewerGroup = new Composite(parent_p, SWT.NONE);
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
   * @param selection_p current selection
   */
  protected void handleDoubleClick(ISelection selection_p) {
    if (selection_p instanceof StructuredSelection) {
      Object selection = ((StructuredSelection) selection_p).getFirstElement();
      if (selection instanceof ModelElement) {
        CapellaUIPropertiesPlugin.getDefault().openWizard((ModelElement) selection);
      }
    }
  }

  /**
   * {@inheritDoc}
   */
  public void addSelectionChangedListener(ISelectionChangedListener listener_p) {
    if (null == _listeners) {
      _listeners = new ArrayList<ISelectionChangedListener>();
    }
    _listeners.add(listener_p);
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
  public void removeSelectionChangedListener(ISelectionChangedListener listener_p) {
    if (null != _listeners) {
      _listeners.remove(listener_p);
    }
  }

  /**
   * {@inheritDoc}
   */
  public void setSelection(ISelection selection_p) {
    _selection = selection_p;
    for (ISelectionChangedListener listener : _listeners) {
      listener.selectionChanged(new SelectionChangedEvent(this, _selection));
    }
  }
}
