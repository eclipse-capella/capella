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
package org.polarsys.capella.core.transition.common.ui.viewer;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.CheckboxCellEditor;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.swt.widgets.Composite;

import org.polarsys.capella.core.transition.common.handlers.filter.AbstractFilterItem.FilterAction;
import org.polarsys.capella.core.transition.common.handlers.filter.FilteringDifferencesHandlerHelper;
import org.polarsys.capella.core.transition.common.handlers.log.IDiffModelViewer;

public class DiffTreeCellModifier extends EditingSupport {

  private DiffTreeViewer diffTreeViewer;

  public DiffTreeCellModifier(DiffTreeViewer diffTableViewer_p, ColumnViewer viewer_p) {
    super(viewer_p);
    this.diffTreeViewer = diffTableViewer_p;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected CellEditor getCellEditor(Object element_p) {
    IDiffModelViewer diff = (IDiffModelViewer) element_p;
    return new CheckboxCellEditor((Composite) getViewer().getControl());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected boolean canEdit(Object element_p) {
    IDiffModelViewer diff = (IDiffModelViewer) element_p;
    return !diff.isReadOnly();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected Object getValue(Object element_p) {
    IDiffModelViewer diff = (IDiffModelViewer) element_p;
    return Boolean.valueOf(((diff.getActionDiff() != FilterAction.NO_ACTION) && (diff.getActionDiff() != null)));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void setValue(Object element_p, Object value_p) {
    IDiffModelViewer diff = (IDiffModelViewer) element_p;
    boolean decoche = !((Boolean) value_p).booleanValue();

    if (decoche) {
      FilteringDifferencesHandlerHelper.getInstance(diffTreeViewer.context).uncheck(diffTreeViewer.context, diff);
    } else {
      FilteringDifferencesHandlerHelper.getInstance(diffTreeViewer.context).check(diffTreeViewer.context, diff);
    }

    diffTreeViewer.getViewerResult().refresh();
  }
}
