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

  private boolean enabled;
  private Tree tree;

  /**
   * @param treeViewer
   */
  public EIAllocationTreeViewerCellModifier(Tree tree) {
    this.enabled = false;
    this.tree = tree;
  }

  /**
   * @return the enabled
   */
  public boolean isEnabled() {
    return enabled;
  }

  /**
   * @param enabled
   *          the enabled to set
   */
  public void setEnabled(boolean enabled) {
    this.enabled = enabled;
  }

  /**
   * @see org.eclipse.jface.viewers.ICellModifier#canModify(java.lang.Object, java.lang.String)
   */
  public boolean canModify(Object element, String property) {
    return isEnabled();
  }

  /**
   * @see org.eclipse.jface.viewers.ICellModifier#getValue(java.lang.Object, java.lang.String)
   */
  public Object getValue(Object element, String property) {
    if (element instanceof AbstractNamedElement) {
      return ((AbstractNamedElement) element).getName();
    }
    return null;
  }

  /**
   * @see org.eclipse.jface.viewers.ICellModifier#modify(java.lang.Object, java.lang.String, java.lang.Object)
   */
  public void modify(Object element, String property, Object value) {
    if (element instanceof TreeItem) {
      Object data = ((TreeItem) element).getData();
      if (data instanceof AbstractNamedElement) {
        String newName = value.toString();
        rename((AbstractNamedElement) data, newName);
        sendNotification((TreeItem) element, data);
      }
    }
  }

  /**
   *
   */
  public void rename(AbstractNamedElement element, String name) {
    if (!element.getName().equals(name)) {
      element.setName(name);
    }
  }

  /**
   * Send a notification to disable the editing
   */
  public void sendNotification(TreeItem item, Object data) {
    Event event = new Event();
    event.item = item;
    event.data = data;
    event.text = RENAMED_NOTIFICATION;
    tree.notifyListeners(SWT.MouseDown, event);
  }
}
