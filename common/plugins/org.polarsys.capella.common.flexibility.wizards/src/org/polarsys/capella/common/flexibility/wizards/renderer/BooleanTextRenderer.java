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

import org.eclipse.core.runtime.IStatus;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import org.polarsys.capella.common.flexibility.properties.schema.IProperty;
import org.polarsys.capella.common.flexibility.properties.schema.IPropertyContext;
import org.polarsys.capella.common.flexibility.wizards.schema.IRendererContext;

/**
 */
public class BooleanTextRenderer extends AbstractRenderer {
  Label dataExport;

  /**
   * @see org.polarsys.capella.common.flexibility.wizards.schema.IRenderer#render(org.eclipse.swt.widgets.Composite)
   */
  @Override
  public void performRender(Composite parent_p, IRendererContext context_p) {
    IProperty property = context_p.getProperty(this);
    dataExport = new Label(parent_p, SWT.NONE);

    if (parent_p.getLayout() instanceof GridLayout) {
      dataExport.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
    }

    dataExport.setText(property.getName());
    dataExport.setToolTipText(property.getDescription());
    dataExport.setData(property);
  }

  public void initialize(IProperty property_p, IRendererContext rendererContext_p) {
    Object value = rendererContext_p.getPropertyContext().getDefaultValue(property_p);
    updatedValue(property_p, rendererContext_p, value);
  }

  @Override
  public void updatedValue(IProperty property_p, IRendererContext rendererContext_p, Object newValue_p) {
    if (isDisposed()) {
      return;
    }

    IProperty property = rendererContext_p.getProperty(this);
    IPropertyContext propertyContext = rendererContext_p.getPropertyContext();
    if (property_p.equals(property)) {
      dataExport.setEnabled(property.isEnabled(propertyContext));
      IStatus diag_p = property_p.validate(property_p.toType(newValue_p, propertyContext), propertyContext);

      dataExport.setText(property.getName() + " : " + rendererContext_p.getLabelProvider().getText(diag_p.getMessage()));

      if (IStatus.INFO == diag_p.getSeverity()) {
        dataExport.setForeground(dataExport.getShell().getDisplay().getSystemColor(SWT.COLOR_DARK_BLUE));
      } else if (IStatus.OK == diag_p.getSeverity()) {
        dataExport.setForeground(dataExport.getShell().getDisplay().getSystemColor(SWT.COLOR_DARK_GREEN));
      } else if (IStatus.WARNING == diag_p.getSeverity()) {
        dataExport.setForeground(dataExport.getShell().getDisplay().getSystemColor(SWT.COLOR_DARK_YELLOW));
      } else if (IStatus.ERROR == diag_p.getSeverity()) {
        dataExport.setForeground(dataExport.getShell().getDisplay().getSystemColor(SWT.COLOR_DARK_RED));
      }
    }
  }

  @Override
  public void dispose(IRendererContext context_p) {
    dataExport.dispose();
  }

}
