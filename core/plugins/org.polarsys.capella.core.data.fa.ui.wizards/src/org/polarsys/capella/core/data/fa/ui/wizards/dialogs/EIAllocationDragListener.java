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

import org.eclipse.emf.edit.ui.dnd.LocalTransfer;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.dnd.DragSourceEvent;
import org.eclipse.swt.dnd.DragSourceListener;
import org.eclipse.swt.widgets.TreeItem;

/**
 */
public class EIAllocationDragListener implements DragSourceListener {
  /** */
  private final TreeViewer _viewer;

  /**
   * Default constructor
   */
  public EIAllocationDragListener(TreeViewer viewer_p) {
    _viewer = viewer_p;
  }

  /**
   * {@inheritDoc}
   */
  public void dragSetData(DragSourceEvent event_p) {
    if (LocalTransfer.getInstance().isSupportedType(event_p.dataType)) {
      TreeItem[] selectedItems = _viewer.getTree().getSelection();
      if (selectedItems.length > 0) {
        event_p.data = selectedItems;
      }
    }
  }

  /**
   * {@inheritDoc}
   */
  public void dragStart(DragSourceEvent event_p) {
  }

  /**
   * {@inheritDoc}
   */
  public void dragFinished(DragSourceEvent event_p) {
  }
}
