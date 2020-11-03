/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.libraries.ui.views.libraryManager.accessPolicyManager;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.ComboBoxViewerCellEditor;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.polarsys.capella.common.libraries.AccessPolicy;
import org.polarsys.capella.common.libraries.IModel;
import org.polarsys.capella.core.libraries.properties.LibraryManagerModel;

public final class AccessPolicyEditingSupport extends EditingSupport {

  private ComboBoxViewerCellEditor cellEditor = null;
  private ColumnViewer columnViewer;
  private LibraryManagerModel model;
  private AccessPolicyManagerWidget widget;

  public AccessPolicyEditingSupport(ColumnViewer viewer, LibraryManagerModel model_p, AccessPolicyManagerWidget widget_p) {
    super(viewer);
    model = model_p;
    columnViewer = viewer;
    widget = widget_p;
    cellEditor = new ComboBoxViewerCellEditor((Composite) getViewer().getControl(), SWT.READ_ONLY);
    cellEditor.setLabelProvider(new LabelProvider());
    cellEditor.setContenProvider(new ArrayContentProvider());
    cellEditor.setInput(AccessPolicy.values());
  }

  @Override
  protected CellEditor getCellEditor(Object element) {
    return cellEditor;
  }

  @Override
  protected boolean canEdit(Object element) {
    return model.isAccessPolicyModifiable((IModel) element);
  }

  @Override
  protected Object getValue(Object element) {
    if (element instanceof IModel) {
      IModel library = (IModel) element;
      return model.getAccessPolicy(library).getLiteral();
    }
    return null;
  }

  @Override
  protected void setValue(Object element, Object value) {
    if ((element instanceof IModel) && (value instanceof AccessPolicy)) {
      IModel library = (IModel) element;
      AccessPolicy newValue = (AccessPolicy) value;
      /* only set new value if it differs from old one */
      if (!model.getAccessPolicy(library).equals(newValue)) {
        model.setAccessPolicy(library, newValue);
        columnViewer.update(element, null);
        widget.notifyValueModification();
      }
    }
  }
}
