/*******************************************************************************
 * Copyright (c) 2006, 2017 THALES GLOBAL SERVICES.
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
  public int getToolTipDisplayDelayTime(Object object) {
    return 200;
  }

  /**
   * @see org.eclipse.jface.viewers.CellLabelProvider#getToolTipShift(java.lang.Object)
   */
  @Override
  public Point getToolTipShift(Object object) {
    return new Point(5, 5);
  }

  @Override
  public Image getToolTipImage(Object object) {
    if (getToolTipText(object) != null) {
      return getColumnImage(object, 0);
    }
    return null;
  }

  /**
   * @see org.eclipse.jface.viewers.CellLabelProvider#getToolTipTimeDisplayed(java.lang.Object)
   */
  @Override
  public int getToolTipTimeDisplayed(Object object) {
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
  public Color getForeground(Object element, int columnIndex) {
    return null;
  }

  /**
   * {@inheritDoc}
   */
  public Color getBackground(Object element, int columnIndex) {
    return null;
  }

  /**
   * {@inheritDoc}
   */
  public Image getColumnImage(Object element, int columnIndex) {
    switch (columnIndex) {
      case 0:
        if (element instanceof Command) {
          ICommandImageService service = (ICommandImageService) Activator.getDefault().getWorkbench().getService(ICommandImageService.class);
          ImageDescriptor descriptor = service.getImageDescriptor(((Command) element).getId());
          if (descriptor != null) {
            return descriptor.createImage();
          }
          return Activator.getDefault().getImageDescriptor("process.gif").createImage();
        }
        if (element instanceof Category) {
          return Activator.getDefault().getImageDescriptor("process.gif").createImage();
        }
        if (element instanceof MenuInput) {
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
  public String getColumnText(Object element, int columnIndex) {
    try {

      switch (columnIndex) {
        case 0:
          if (element instanceof Command) {
            return ((Command) element).getName();

          } else if (element instanceof Category) {
            return ((Category) element).getName();
          }

        break;
        case 1:
          if (element instanceof Command) {
            return ((Command) element).getDescription();

          } else if (element instanceof Category) {
            return ((Category) element).getDescription();
          }
        break;
      }

    } catch (NotDefinedException exception) {
      //Do nothing.
    }
    return element.toString();

  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void update(ViewerCell cell) {
    cell.setText(getColumnText(cell.getElement(), cell.getColumnIndex()));
    cell.setImage(getColumnImage(cell.getElement(), cell.getColumnIndex()));
  }

  /**
   * {@inheritDoc}
   */
  public Image getImage(Object element) {
    return getColumnImage(element, 0);
  }

  /**
   * {@inheritDoc}
   */
  public String getText(Object element) {
    return getColumnText(element, 0);
  }

}
