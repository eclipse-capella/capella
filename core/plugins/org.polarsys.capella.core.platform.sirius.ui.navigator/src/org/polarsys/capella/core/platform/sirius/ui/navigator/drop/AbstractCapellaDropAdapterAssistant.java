/*******************************************************************************
 * Copyright (c) 2001, 2020 Corporation and others.
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 * 
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *    IBM - Initial API and implementation 
 *    Jens Lukowski/Innoopract - Initial renaming/restructuring
 *    Thales - Contributor
 *******************************************************************************/
package org.polarsys.capella.core.platform.sirius.ui.navigator.drop;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.util.LocalSelectionTransfer;
import org.eclipse.swt.dnd.DropTargetEvent;
import org.eclipse.swt.dnd.TransferData;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.ui.navigator.CommonDropAdapter;
import org.eclipse.ui.navigator.CommonDropAdapterAssistant;

/**
 * Base class to implement common drop adapter assistant.
 */
public abstract class AbstractCapellaDropAdapterAssistant extends CommonDropAdapterAssistant implements IDropAssistant {
  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isSupportedType(TransferData transferType_p) {
    return LocalSelectionTransfer.getTransfer().isSupportedType(transferType_p);
  }

  @Override
  public IStatus handleDrop(CommonDropAdapter dropAdapter_p, DropTargetEvent dropTargetEvent_p, Object target_p) {
    return handleDrop(target_p, dropAdapter_p.getCurrentOperation(), dropTargetEvent_p);
  }

  /**
   * This returns the location of the mouse in the vertical direction, relative to the item widget, from 0 (top) to 1 (bottom).
   */
  protected float getLocation(DropTargetEvent event) {
    if (event.item instanceof TreeItem) {
      TreeItem treeItem = (TreeItem) event.item;
      Control control = treeItem.getParent();
      Point point = control.toControl(new Point(event.x, event.y));
      Rectangle bounds = treeItem.getBounds();
      return (float) (point.y - bounds.y) / (float) bounds.height;
    } else if (event.item instanceof TableItem) {
      TableItem tableItem = (TableItem) event.item;
      Control control = tableItem.getParent();
      Point point = control.toControl(new Point(event.x, event.y));
      Rectangle bounds = tableItem.getBounds(0);
      return (float) (point.y - bounds.y) / (float) bounds.height;
    } else {
      return 0.0F;
    }
  }
}
