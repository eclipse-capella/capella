/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
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

import org.eclipse.jface.fieldassist.ControlDecoration;
import org.eclipse.jface.fieldassist.FieldDecoration;
import org.eclipse.jface.fieldassist.FieldDecorationRegistry;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseWheelListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.events.TreeEvent;
import org.eclipse.swt.events.TreeListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;

/**
 */
public class DecompositionControlDecoration extends ControlDecoration {

  private int offset = 0;
  private FieldDecoration fieldDecoration;

  /**
   * @param control_p
   * @param position_p
   */
  public DecompositionControlDecoration(Control control_p, int position_p) {
    super(control_p, position_p);
    fieldDecoration = FieldDecorationRegistry.getDefault().getFieldDecoration(FieldDecorationRegistry.DEC_WARNING);

    final Tree tree = (Tree) control_p;

    tree.addSelectionListener(new SelectionListener() {

      public void widgetDefaultSelected(SelectionEvent e_p) {
        // do nothing
      }

      public void widgetSelected(SelectionEvent e_p) {
        refresh();
      }

    });

    tree.addTreeListener(new TreeListener() {

      public void treeCollapsed(TreeEvent e_p) {
        refresh();
      }

      public void treeExpanded(TreeEvent e_p) {
        refresh();
      }
    });

    tree.addMouseWheelListener(new MouseWheelListener() {

      public void mouseScrolled(MouseEvent e_p) {
        refresh();
      }
    });
  }
  
  public void refresh() {
    update();
    getControl().getParent().redraw();
  }

  /**
   * @see org.eclipse.jface.fieldassist.ControlDecoration#getDecorationRectangle(org.eclipse.swt.widgets.Control)
   */
  @Override
  protected Rectangle getDecorationRectangle(Control targetControl_p) {
    Rectangle oldRect = super.getDecorationRectangle(targetControl_p);
    Tree tree = (Tree) getControl();
    if (tree.getSelection().length > 0) {
      TreeItem item = tree.getSelection()[0];
      offset = item.getBounds().height * 2;
      Image img = getImage();
      offset -= (img != null) ? img.getBounds().height : 0;
      oldRect.y = item.getBounds().y + offset;

      Rectangle clientArea = tree.getBounds();
      if (oldRect.y > clientArea.height + clientArea.y) {
        oldRect.y = clientArea.height + clientArea.y - offset / 2;
      }
      if (oldRect.y < clientArea.y) {
        oldRect.y = clientArea.y;
      }
    }
    return oldRect;
  }

  /**
   * @see org.eclipse.jface.fieldassist.ControlDecoration#getImage()
   */
  @Override
  public Image getImage() {
    Image img = null;
    DecompositionItem decompositionItem = getSelectedDecompositionItem();
    if (null == decompositionItem)
      return img;
    img = fieldDecoration.getImage();
    return img;
  }

  /**
   * @see org.eclipse.jface.fieldassist.ControlDecoration#getDescriptionText()
   */
  @Override
  public String getDescriptionText() {
    String description = super.getDescriptionText();
    DecompositionItem decompositionItem = getSelectedDecompositionItem();
    if (null == decompositionItem)
      return description;

    switch (decompositionItem.getStatus()) {
      case DecompositionItem.ASSIGNED:
        description = DecompositionUtil.ONCE_USED;
      break;
      case DecompositionItem.AMBIGUOUS:
        description = DecompositionUtil.MULTIPLE_USED;
      break;
      case DecompositionItem.UNASSIGNED:
        description = DecompositionUtil.UNUSED;
      break;
      default:
        description = super.getDescriptionText();
      break;
    }
    return description;
  }

  private DecompositionItem getSelectedDecompositionItem() {
    Tree tree = (Tree) getControl();
    if (tree.getSelection().length > 0) {
      TreeItem item = tree.getSelection()[0];
      Object data = item.getData();
      if (data instanceof DecompositionItem) {
        return (DecompositionItem) data;
      }
    }
    return null;
  }
}
