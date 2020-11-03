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

import org.eclipse.emf.edit.ui.dnd.LocalTransfer;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.dnd.DragSourceEvent;
import org.eclipse.swt.dnd.DragSourceListener;
import org.eclipse.swt.widgets.TreeItem;

/**
 */
public class EIAllocationDragListener implements DragSourceListener {
  /** */
  private final TreeViewer viewer;

  /**
   * Default constructor
   */
  public EIAllocationDragListener(TreeViewer viewer) {
    this.viewer = viewer;
  }

  /**
   * {@inheritDoc}
   */
  public void dragSetData(DragSourceEvent event) {
    if (LocalTransfer.getInstance().isSupportedType(event.dataType)) {
      TreeItem[] selectedItems = viewer.getTree().getSelection();
      if (selectedItems.length > 0) {
        event.data = selectedItems;
      }
    }
  }

  /**
   * {@inheritDoc}
   */
  public void dragStart(DragSourceEvent event) {
  }

  /**
   * {@inheritDoc}
   */
  public void dragFinished(DragSourceEvent event) {
  }
}
