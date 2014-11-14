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
package org.polarsys.capella.core.data.fa.ui.wizards.dialogs;

import org.eclipse.jface.viewers.ICellModifier;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;

import org.polarsys.capella.common.data.modellingcore.AbstractNamedElement;

/**
 * Class <code>EIAllocationTreeViewerCellModifier</code>, the cell modifier for EI Allocation Tree Viewers
 * 
 */
public class EIAllocationTreeViewerCellModifier implements ICellModifier {

  public final static String RENAMED_NOTIFICATION = "renamed_notification"; //$NON-NLS-1$

  private boolean _enabled;
  private Tree _tree;

  /**
   * @param treeViewer_p
   */
  public EIAllocationTreeViewerCellModifier(Tree tree_p) {
    _enabled = false;
    _tree = tree_p;
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
   * @see org.eclipse.jface.viewers.ICellModifier#canModify(java.lang.Object, java.lang.String)
   */
  public boolean canModify(Object element_p, String property_p) {
    return isEnabled();
  }

  /**
   * @see org.eclipse.jface.viewers.ICellModifier#getValue(java.lang.Object, java.lang.String)
   */
  public Object getValue(Object element_p, String property_p) {
    if (element_p instanceof AbstractNamedElement) {
      return ((AbstractNamedElement) element_p).getName();
    }
    return null;
  }

  /**
   * @see org.eclipse.jface.viewers.ICellModifier#modify(java.lang.Object, java.lang.String, java.lang.Object)
   */
  public void modify(Object element_p, String property_p, Object value_p) {
    if (element_p instanceof TreeItem) {
      Object data = ((TreeItem) element_p).getData();
      if (data instanceof AbstractNamedElement) {
        String newName = value_p.toString();
        rename((AbstractNamedElement) data, newName);
        sendNotification((TreeItem) element_p, data);
      }
    }
  }

  /**
   *
   */
  public void rename(AbstractNamedElement element_p, String name_p) {
    if (!element_p.getName().equals(name_p)) {
      element_p.setName(name_p);
    }
  }

  /**
   * Send a notification to disable the editing
   */
  public void sendNotification(TreeItem item_p, Object data) {
    Event event = new Event();
    event.item = item_p;
    event.data = data;
    event.text = RENAMED_NOTIFICATION;
    _tree.notifyListeners(SWT.MouseDown, event);
  }
}
