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

package org.polarsys.capella.common.flexibility.wizards.renderer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.FrameworkUtil;
import org.polarsys.capella.common.flexibility.properties.schema.IProperty;
import org.polarsys.capella.common.flexibility.properties.schema.IPropertyContext;
import org.polarsys.capella.common.flexibility.properties.schema.IRestraintProperty;
import org.polarsys.capella.common.flexibility.wizards.schema.IRendererContext;
import org.polarsys.capella.common.ui.toolkit.dialogs.SelectElementsDialog;
import org.polarsys.capella.common.ui.toolkit.dialogs.TransferTreeListDialog;

public class BrowseRenderer extends TextRenderer {

  ToolItem browse;
  ToolItem delete;

  protected boolean isDeleteButton() {
    return false;
  }

  protected boolean isBrowseButton() {
    return true;
  }

  protected void setBrowseEnabled(boolean enabled) {
    if (browse != null) {
      browse.setEnabled(enabled);
    }
  }

  protected void setDeleteEnabled(boolean enabled) {
    if (delete != null) {
      delete.setEnabled(enabled);
    }
  }

  @Override
  protected boolean isEditable(IProperty property, IRendererContext context) {
    return false;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected int getNbColumn() {
    return super.getNbColumn() + (isBrowseButton() ? 1 : 0) + (isDeleteButton() ? 1 : 0);
  }

  @Override
  protected void initializeControls(final Composite parent, final IRendererContext context) {
    super.initializeControls(parent, context);

    ToolBar toolbar = new ToolBar(parent, SWT.HORIZONTAL);
    final String ICONS_PATH = "icons/"; //$NON-NLS-1$
    if (isBrowseButton()) {
      browse = new ToolItem(toolbar, SWT.PUSH);
      browse.setToolTipText(getBrowseText());
      browse.setImage(AbstractUIPlugin.imageDescriptorFromPlugin(FrameworkUtil.getBundle(this.getClass()).getSymbolicName(), ICONS_PATH + "full/etool16/browse.gif").createImage());
      browse.addSelectionListener(new SelectionListener() {

        public void widgetSelected(SelectionEvent event) {
          proceedBrowse(parent.getShell(), context);
        }

        public void widgetDefaultSelected(SelectionEvent e) {
          // Nothing to do
        }
      });
    }

    if (isDeleteButton()) {
      delete = new ToolItem(toolbar, SWT.PUSH);
      delete.setToolTipText("Delete all elements");
      delete.setImage(AbstractUIPlugin.imageDescriptorFromPlugin(FrameworkUtil.getBundle(this.getClass()).getSymbolicName(), ICONS_PATH + "full/etool16/delete_edit.gif").createImage());
      delete.addSelectionListener(new SelectionListener() {

        public void widgetSelected(SelectionEvent event) {
          proceedDelete(parent.getShell(), context);
        }

        public void widgetDefaultSelected(SelectionEvent e) {
          // Nothing to do
        }
      });
    }
  }

  /**
   * @return
   */
  protected String getBrowseText() {
    return "Browse available elements";
  }

  protected void proceedDelete(Shell shell, IRendererContext context) {
    IProperty property = context.getProperty(this);
    if (property instanceof IRestraintProperty) {
      IRestraintProperty restraintProperty = (IRestraintProperty) property;

      if (!restraintProperty.isMany()) {
        changeValue(property, context, context.getPropertyContext().getDefaultValue(property));
        updatedValue(property, context, context.getPropertyContext().getDefaultValue(property));
      } else {
        ArrayList<Object> result = new ArrayList<Object>();
        changeValue(property, context, result);
        updatedValue(property, context, result);
      }
    }
  }

  /**
   * @param shell
   */
  @SuppressWarnings({ "unchecked", "rawtypes" })
  protected void proceedBrowse(Shell shell, IRendererContext context) {
    IProperty property = context.getProperty(this);
    if (property instanceof IRestraintProperty) {
      IPropertyContext propertyContext = context.getPropertyContext();
      IRestraintProperty restraintProperty = (IRestraintProperty) property;
      if (!restraintProperty.isMany()) {
        Collection<EObject> scope = new HashSet<EObject>();
        EObject current = (EObject) propertyContext.getCurrentValue(context.getProperty(this));
        scope.add(current);
        scope.add((EObject) restraintProperty.getValue(propertyContext));
        scope.addAll((Collection) restraintProperty.getChoiceValues(propertyContext));
        scope.remove(null);

        SelectElementsDialog dialog =
            new SelectElementsDialog(shell, "Selection wizard", //$NON-NLS-1$
                "Select element.", //$NON-NLS-1$
                new ArrayList<EObject>(scope));
        if (dialog.open() == Window.OK) {
          List<?> dialogResult = dialog.getResult();
          if (dialogResult != null) {
            changeValue(property, context, dialogResult.get(0));
            updatedValue(property, context, dialogResult.get(0));
          }
        }

      } else {
        Collection<EObject> current = (Collection) propertyContext.getCurrentValue(restraintProperty);
        Collection<EObject> left = new HashSet<EObject>();
        left.addAll((Collection) restraintProperty.getValue(propertyContext));
        left.addAll((Collection) restraintProperty.getChoiceValues(propertyContext));
        left.removeAll(current);
        left.remove(null);
        Collection<EObject> right = new HashSet<EObject>();
        right.addAll(current);
        right.remove(null);

        TransferTreeListDialog dialog = new TransferTreeListDialog(shell, "Selection wizard", "Select elements.");//$NON-NLS-2$
        dialog.setLeftInput(new ArrayList<EObject>(left), null);
        dialog.setRightInput(new ArrayList<EObject>(right), null);
        if (dialog.open() == Window.OK) {
          Object[] dialogResult = dialog.getResult();
          ArrayList<Object> result = new ArrayList<Object>();
          if (dialogResult != null) {
            for (Object res : dialogResult) {
              result.add(res);
            }
          }
          changeValue(property, context, result);
          updatedValue(property, context, result);
        }
      }
    }
  }

  @Override
  public void dispose(IRendererContext context) {
    super.dispose(context);
    if (browse != null) {
      browse.dispose();
    }
    if (delete != null) {
      delete.dispose();
    }
  }

}
