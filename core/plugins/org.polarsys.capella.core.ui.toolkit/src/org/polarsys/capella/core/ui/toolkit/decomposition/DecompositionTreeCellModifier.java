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
package org.polarsys.capella.core.ui.toolkit.decomposition;

import org.eclipse.jface.viewers.ICellModifier;
import org.eclipse.jface.viewers.ITreeSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.TreeItem;

/**
 * Class <code>DecompositionTreeCellModifier</code>, the cell modifier for Decomposition Tree
 */
public class DecompositionTreeCellModifier implements ICellModifier {

  private TreeViewer _treeViewer;
  private boolean _enabled;

  /**
   * @param treeViewer_p
   */
  public DecompositionTreeCellModifier(TreeViewer treeViewer_p) {
    this._treeViewer = treeViewer_p;
    _enabled = false;
  }

  /**
   * @return the enabled
   */
  public boolean isEnabled() {
    return _enabled;
  }

  /**
   * @param enabled_p
   *          the enabled to set
   */
  public void setEnabled(boolean enabled_p) {
    _enabled = enabled_p;
  }

  /**
   * @return the treeViewer
   */
  public TreeViewer getTreeViewer() {
    return _treeViewer;
  }

  /**
   * @param treeViewer_p
   *          the treeViewer to set
   */
  public void setTreeViewer(TreeViewer treeViewer_p) {
    _treeViewer = treeViewer_p;
  }

  /**
   * @see org.eclipse.jface.viewers.ICellModifier#canModify(java.lang.Object, java.lang.String)
   */
  public boolean canModify(Object element_p, String property_p) {
    return isEnabled();
  }

  /**
   * @see org.eclipse.jface.viewers.ICellModifier#getValue(java.lang.Object, java.lang.String)
   */
  public Object getValue(Object element_p, String property_p) {
    return ((ITreeSelection) _treeViewer.getSelection()).getFirstElement().toString();
  }

  /**
   * @see org.eclipse.jface.viewers.ICellModifier#modify(java.lang.Object, java.lang.String, java.lang.Object)
   */
  public void modify(Object element_p, String property_p, Object value_p) {
    if (element_p instanceof TreeItem) {
      TreeItem item = (TreeItem) element_p;
      // get the domain object:
      DecompositionComponent comp = (DecompositionComponent) item.getData();

      // do changes on component, persist it
      String newName = value_p.toString();
      if (DecompositionUtil.isValidName(newName, comp.getParentDecomposition())) {
        comp.setName(newName);
        item.setText(newName);
        _treeViewer.update(comp, null);
        _treeViewer.refresh(true);
      }
      Event event = new Event();
      event.item = item;
      event.data = "rename"; //$NON-NLS-1$
      _treeViewer.getTree().notifyListeners(SWT.MouseDown, event);// to disable the editing
    }
  }
}
