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
  public void performRender(Composite parent_p, final IRendererContext rendererContext_p) {

    final IProperty property = rendererContext_p.getProperty(this);
    dataExport = new Button(parent_p, SWT.CHECK);
    dataExport.setText(property.getName());
    dataExport.setToolTipText(property.getDescription());

    dataExport.setData(property);
    dataExport.setEnabled(property.isEnabled(rendererContext_p.getPropertyContext()));

    dataExport.addSelectionListener(new SelectionListener() {

      public void widgetSelected(SelectionEvent e_p) {
        boolean newValue = ((Button) e_p.widget).getSelection();
        Boolean value = Boolean.valueOf(newValue);
        changeValue(property, rendererContext_p, value);
        updatedValue(property, rendererContext_p, value);
      }

      public void widgetDefaultSelected(SelectionEvent e_p) {
        // Nothing here
      }
    });
  }

  public void initialize(IProperty property_p, IRendererContext propertyContext_p) {
    Object value = propertyContext_p.getPropertyContext().getDefaultValue(property_p);
    updatedValue(property_p, propertyContext_p, value);
  }

  @Override
  public void updatedValue(IProperty property_p, IRendererContext rendererContext_p, Object newValue_p) {
    if (isDisposed()) {
      return;
    }
    IProperty property = rendererContext_p.getProperty(this);
    if (property_p.equals(property)) {
      dataExport.setSelection(Boolean.valueOf(newValue_p.toString()).booleanValue());
      dataExport.setEnabled(property.isEnabled(rendererContext_p.getPropertyContext()));
    }
  }

  @Override
  public void dispose(IRendererContext context_p) {
    dataExport.dispose();
  }

}
