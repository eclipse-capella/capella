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
  public void performRender(Composite parent, IRendererContext context) {
    IProperty property = context.getProperty(this);
    dataExport = new Label(parent, SWT.NONE);

    if (parent.getLayout() instanceof GridLayout) {
      dataExport.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
    }

    dataExport.setText(property.getName());
    dataExport.setToolTipText(property.getDescription());
    dataExport.setData(property);
  }

  public void initialize(IProperty property, IRendererContext rendererContext) {
    Object value = rendererContext.getPropertyContext().getDefaultValue(property);
    updatedValue(property, rendererContext, value);
  }

  @Override
  public void updatedValue(IProperty property, IRendererContext rendererContext, Object newValue) {
    if (isDisposed()) {
      return;
    }

    IProperty prop = rendererContext.getProperty(this);
    IPropertyContext propertyContext = rendererContext.getPropertyContext();
    if (property.equals(prop)) {
      dataExport.setEnabled(prop.isEnabled(propertyContext));
      IStatus diag = property.validate(property.toType(newValue, propertyContext), propertyContext);

      dataExport.setText(prop.getName() + " : " + rendererContext.getLabelProvider().getText(diag.getMessage()));

      if (IStatus.INFO == diag.getSeverity()) {
        dataExport.setForeground(dataExport.getShell().getDisplay().getSystemColor(SWT.COLOR_DARK_BLUE));
      } else if (IStatus.OK == diag.getSeverity()) {
        dataExport.setForeground(dataExport.getShell().getDisplay().getSystemColor(SWT.COLOR_DARK_GREEN));
      } else if (IStatus.WARNING == diag.getSeverity()) {
        dataExport.setForeground(dataExport.getShell().getDisplay().getSystemColor(SWT.COLOR_DARK_YELLOW));
      } else if (IStatus.ERROR == diag.getSeverity()) {
        dataExport.setForeground(dataExport.getShell().getDisplay().getSystemColor(SWT.COLOR_DARK_RED));
      }
    }
  }

  @Override
  public void dispose(IRendererContext context) {
    dataExport.dispose();
  }

}
