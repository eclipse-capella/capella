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

import org.polarsys.capella.common.ui.toolkit.dialogs.SelectElementsDialog;
import org.polarsys.capella.common.ui.toolkit.dialogs.TransferTreeListDialog;
import org.polarsys.capella.common.flexibility.properties.schema.IProperty;
import org.polarsys.capella.common.flexibility.properties.schema.IPropertyContext;
import org.polarsys.capella.common.flexibility.properties.schema.IRestraintProperty;
import org.polarsys.capella.common.flexibility.wizards.Activator;
import org.polarsys.capella.common.flexibility.wizards.schema.IRendererContext;
import org.polarsys.capella.common.helpers.adapters.MDEAdapterFactory;

/**
 */
public class BrowseRenderer extends TextRenderer {

  ToolItem browse;
  ToolItem delete;

  protected boolean isDeleteButton() {
    return false;
  }

  protected boolean isBrowseButton() {
    return true;
  }

  protected void setBrowseEnabled(boolean enabled_p) {
    if (browse != null) {
      browse.setEnabled(enabled_p);
    }
  }

  protected void setDeleteEnabled(boolean enabled_p) {
    if (delete != null) {
      delete.setEnabled(enabled_p);
    }
  }

  @Override
  protected boolean isEditable(IProperty property_p, IRendererContext context_p) {
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
  protected void initializeControls(final Composite parent_p, final IRendererContext context_p) {
    super.initializeControls(parent_p, context_p);

    ToolBar toolbar = new ToolBar(parent_p, SWT.HORIZONTAL);
    if (isBrowseButton()) {
      browse = new ToolItem(toolbar, SWT.PUSH);
      browse.setToolTipText(getBrowseText());
      browse.setImage(Activator.getDefault().getImage("full/etool16/browse.gif"));
      browse.addSelectionListener(new SelectionListener() {

        public void widgetSelected(SelectionEvent event_p) {
          proceedBrowse(parent_p.getShell(), context_p);
        }

        public void widgetDefaultSelected(SelectionEvent e_p) {
          // Nothing to do
        }
      });
    }

    if (isDeleteButton()) {
      delete = new ToolItem(toolbar, SWT.PUSH);
      delete.setToolTipText("Delete all elements");
      delete.setImage(Activator.getDefault().getImage("full/etool16/delete_edit.gif"));
      delete.addSelectionListener(new SelectionListener() {

        public void widgetSelected(SelectionEvent event_p) {
          proceedDelete(parent_p.getShell(), context_p);
        }

        public void widgetDefaultSelected(SelectionEvent e_p) {
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

  protected void proceedDelete(Shell shell_p, IRendererContext context_p) {
    IProperty property = context_p.getProperty(this);
    if (property instanceof IRestraintProperty) {
      IRestraintProperty restraintProperty = (IRestraintProperty) property;

      if (!restraintProperty.isMany()) {
        changeValue(property, context_p, context_p.getPropertyContext().getDefaultValue(property));
        updatedValue(property, context_p, context_p.getPropertyContext().getDefaultValue(property));
      } else {
        ArrayList<Object> result = new ArrayList<Object>();
        changeValue(property, context_p, result);
        updatedValue(property, context_p, result);
      }
    }
  }

  /**
   * @param shell_p
   */
  protected void proceedBrowse(Shell shell_p, IRendererContext context_p) {
    IProperty property = context_p.getProperty(this);
    if (property instanceof IRestraintProperty) {
      IPropertyContext propertyContext = context_p.getPropertyContext();
      IRestraintProperty restraintProperty = (IRestraintProperty) property;
      if (!restraintProperty.isMany()) {
        Collection<EObject> scope = new HashSet<EObject>();
        EObject current = (EObject) propertyContext.getCurrentValue(context_p.getProperty(this));
        scope.add(current);
        scope.add((EObject) restraintProperty.getValue(propertyContext));
        scope.addAll((Collection) restraintProperty.getChoiceValues(propertyContext));
        scope.remove(null);

        SelectElementsDialog dialog =
            new SelectElementsDialog(shell_p, MDEAdapterFactory.getEditingDomain(), MDEAdapterFactory.getAdapterFactory(), "Selection wizard", //$NON-NLS-1$
                "Select element.", //$NON-NLS-1$
                new ArrayList<EObject>(scope), false, null);
        if (dialog.open() == Window.OK) {
          List<?> dialogResult = dialog.getResult();
          if (dialogResult != null) {
            changeValue(property, context_p, dialogResult.get(0));
            updatedValue(property, context_p, dialogResult.get(0));
          }
        }

      } else {
        TransferTreeListDialog dialog = new TransferTreeListDialog(shell_p, "Selection wizard", //$NON-NLS-1$
            "Select elements.", //$NON-NLS-1$
            MDEAdapterFactory.getEditingDomain(), MDEAdapterFactory.getAdapterFactory());
        Collection<EObject> left = new HashSet<EObject>();
        Collection<EObject> right = new HashSet<EObject>();

        Collection<EObject> current = (Collection) propertyContext.getCurrentValue(restraintProperty);
        left.addAll((Collection) restraintProperty.getValue(propertyContext));
        left.addAll((Collection) restraintProperty.getChoiceValues(propertyContext));
        left.removeAll(current);
        right.addAll(current);
        left.remove(null);
        right.remove(null);

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
          changeValue(property, context_p, result);
          updatedValue(property, context_p, result);
        }
      }
    }
  }

  @Override
  public void dispose(IRendererContext context_p) {
    super.dispose(context_p);
    if (browse != null) {
      browse.dispose();
    }
    if (delete != null) {
      delete.dispose();
    }
  }

}
