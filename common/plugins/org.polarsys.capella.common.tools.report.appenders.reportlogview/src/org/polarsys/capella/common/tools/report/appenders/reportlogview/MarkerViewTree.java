/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
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

package org.polarsys.capella.common.tools.report.appenders.reportlogview;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;

/**
 * A proposed workaround to speed up Trees wich
 * have SWT.Virtual and SWT.Multi set.
 * 
 * See https://bugs.eclipse.org/bugs/show_bug.cgi?id=259141
 */
public class MarkerViewTree extends Tree {

  Set<TreeItem> cachedSelection = new HashSet<TreeItem>();
  
  /**
   * @param parent
   * @param style
   */
  public MarkerViewTree(Composite parent, int style) {
    super(parent, style);
    addSelectionListener(new SelectionListener() {
      public void widgetSelected(final SelectionEvent e) {
        if (e.item instanceof TreeItem) {
          TreeItem treeItem = (TreeItem) e.item;
          // Ctrl+Click : add or remove from selection
          if ((e.stateMask & SWT.CONTROL) != 0
              || (e.stateMask & SWT.COMMAND) != 0) {
            if (cachedSelection.contains(treeItem)) {
              cachedSelection.remove(treeItem);
            } else {
              cachedSelection.add(treeItem);
            }
          }
          // Shift+Click : select a range of items
          else if ((e.stateMask & SWT.SHIFT) != 0) {
            TreeItem[] selection = getSystemSelection();
            cachedSelection.clear();
            for (TreeItem selected : selection) {
              cachedSelection.add(selected);
            }
          }
          // simple click with no modifiers : selection of a
          // single item
          else {
            cachedSelection.clear();
            cachedSelection.add(treeItem);
          }
        }
      }
      public void widgetDefaultSelected(final SelectionEvent e) {
      }
    });
  }

  public TreeItem[] getSystemSelection() {
    return super.getSelection();
  }

  @Override
  public TreeItem[] getSelection() {
    //We need to filter out disposed controls to avoid many "Widget is disposed" Eclipse bugs (e.g. Bug 320854)
    List<TreeItem> tempSelections = new ArrayList<>();
    for (TreeItem treeItem : cachedSelection)
      if (treeItem != null && !treeItem.isDisposed())
        tempSelections.add(treeItem);
    return tempSelections.toArray(new TreeItem[tempSelections.size()]);
  }

  @Override
  public void setSelection(final TreeItem item) {
    this.cachedSelection.clear();
    this.cachedSelection.add(item);
    super.setSelection(item);
  }

  @Override
  public void setSelection(final TreeItem[] items) {
    this.cachedSelection.clear();
    for (TreeItem treeItem : items) {
      this.cachedSelection.add(treeItem);
    }
    super.setSelection(items);
  }

  @Override
  protected void checkSubclass() {
    // allow sub-classing
  }

}
