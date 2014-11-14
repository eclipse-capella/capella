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
package org.polarsys.capella.core.flexibility.commands.menus.provider;

import org.eclipse.core.commands.Category;
import org.eclipse.core.commands.Command;
import org.eclipse.core.commands.common.NotDefinedException;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.CellLabelProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.ui.commands.ICommandImageService;

import org.polarsys.capella.core.flexibility.commands.Activator;
import org.polarsys.capella.core.flexibility.commands.actions.DefaultAction;

/**
 *
 */
public class CommandsLabelProvider extends CellLabelProvider implements ILabelProvider, ITableLabelProvider {

  /**
   * @see org.eclipse.jface.viewers.CellLabelProvider#getToolTipDisplayDelayTime(java.lang.Object)
   */
  @Override
  public int getToolTipDisplayDelayTime(Object object_p) {
    return 200;
  }

  /**
   * @see org.eclipse.jface.viewers.CellLabelProvider#getToolTipShift(java.lang.Object)
   */
  @Override
  public Point getToolTipShift(Object object_p) {
    return new Point(5, 5);
  }

  @Override
  public Image getToolTipImage(Object object_p) {
    if (getToolTipText(object_p) != null) {
      return getColumnImage(object_p, 0);
    }
    return null;
  }

  /**
   * @see org.eclipse.jface.viewers.CellLabelProvider#getToolTipTimeDisplayed(java.lang.Object)
   */
  @Override
  public int getToolTipTimeDisplayed(Object object_p) {
    return 7000;
  }

  @Override
  public String getToolTipText(Object element) {
    if (element instanceof DefaultAction) {
      DefaultAction action = (DefaultAction) element;
      if (action.getDescription() != null) {
        return action.getDescription();
      }
      if (action.getToolTipText() != null) {
        return action.getToolTipText();
      }
      return null;
    }
    return null;
  }

  /**
   * {@inheritDoc}
   */
  public Color getForeground(Object element_p, int columnIndex_p) {
    return null;
  }

  /**
   * {@inheritDoc}
   */
  public Color getBackground(Object element_p, int columnIndex_p) {
    return null;
  }

  /**
   * {@inheritDoc}
   */
  public Image getColumnImage(Object element_p, int columnIndex_p) {
    switch (columnIndex_p) {
      case 0:
        if (element_p instanceof Command) {
          ICommandImageService service = (ICommandImageService) Activator.getDefault().getWorkbench().getService(ICommandImageService.class);
          ImageDescriptor descriptor = service.getImageDescriptor(((Command) element_p).getId());
          if (descriptor != null) {
            return descriptor.createImage();
          }
          return Activator.getDefault().getImageDescriptor("process.gif").createImage();
        }
        if (element_p instanceof Category) {
          return Activator.getDefault().getImageDescriptor("process.gif").createImage();
        }
        if (element_p instanceof MenuInput) {
          return Activator.getDefault().getImageDescriptor("process.gif").createImage();
        }
        return null;
      default:
        return null;
    }
  }

  public String getCategoryText(DefaultAction element) {
    return element.getCategory();
  }

  /**
   * {@inheritDoc}
   */
  public String getColumnText(Object element_p, int columnIndex_p) {
    try {

      switch (columnIndex_p) {
        case 0:
          if (element_p instanceof Command) {
            return ((Command) element_p).getName();

          } else if (element_p instanceof Category) {
            return ((Category) element_p).getName();
          }

        break;
        case 1:
          if (element_p instanceof Command) {
            return ((Command) element_p).getDescription();

          } else if (element_p instanceof Category) {
            return ((Category) element_p).getDescription();
          }
        break;
      }

    } catch (NotDefinedException exception_p) {
      //Do nothing.
    }
    return element_p.toString();

  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void update(ViewerCell cell_p) {
    cell_p.setText(getColumnText(cell_p.getElement(), cell_p.getColumnIndex()));
    cell_p.setImage(getColumnImage(cell_p.getElement(), cell_p.getColumnIndex()));
  }

  /**
   * {@inheritDoc}
   */
  public Image getImage(Object element_p) {
    return getColumnImage(element_p, 0);
  }

  /**
   * {@inheritDoc}
   */
  public String getText(Object element_p) {
    return getColumnText(element_p, 0);
  }

}
