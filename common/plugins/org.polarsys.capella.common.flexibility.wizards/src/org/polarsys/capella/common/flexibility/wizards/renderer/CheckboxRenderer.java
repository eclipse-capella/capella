/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/

package org.polarsys.capella.common.flexibility.wizards.renderer;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.polarsys.capella.common.flexibility.properties.schema.IProperty;
import org.polarsys.capella.common.flexibility.wizards.schema.IRendererContext;

/**
 */
public class CheckboxRenderer extends AbstractRenderer {
  Button dataExport;

  /**
   * @see org.polarsys.capella.common.flexibility.wizards.schema.IRenderer#render(org.eclipse.swt.widgets.Composite)
   */
  @Override
  public void performRender(Composite parent, final IRendererContext rendererContext) {

    final IProperty property = rendererContext.getProperty(this);
    dataExport = new Button(parent, SWT.CHECK);
    dataExport.setText(property.getName());
    dataExport.setToolTipText(property.getDescription());

    dataExport.setData(property);
    dataExport.setEnabled(property.isEnabled(rendererContext.getPropertyContext()));

    dataExport.addSelectionListener(new SelectionListener() {

      public void widgetSelected(SelectionEvent e) {
        boolean newValue = ((Button) e.widget).getSelection();
        Boolean value = Boolean.valueOf(newValue);
        changeValue(property, rendererContext, value);
        updatedValue(property, rendererContext, value);
      }

      public void widgetDefaultSelected(SelectionEvent e) {
        // Nothing here
      }
    });
  }

  public void initialize(IProperty property, IRendererContext propertyContext) {
    Object value = propertyContext.getPropertyContext().getDefaultValue(property);
    updatedValue(property, propertyContext, value);
  }

  @Override
  public void updatedValue(IProperty property, IRendererContext rendererContext, Object newValue) {
    if (isDisposed()) {
      return;
    }
    IProperty prop = rendererContext.getProperty(this);
    if (property.equals(prop)) {
      dataExport.setSelection(Boolean.valueOf(newValue.toString()).booleanValue());
      dataExport.setEnabled(prop.isEnabled(rendererContext.getPropertyContext()));
    }
  }

  @Override
  public void dispose(IRendererContext context) {
    super.dispose(context);
    dataExport.dispose();
  }

}
