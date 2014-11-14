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

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;

import org.polarsys.capella.common.flexibility.properties.schema.IProperty;
import org.polarsys.capella.common.flexibility.properties.schema.IPropertyContext;
import org.polarsys.capella.common.flexibility.properties.schema.IPropertyOption;
import org.polarsys.capella.common.flexibility.wizards.schema.IRendererContext;

/**
 */
public class OptionRenderer extends AbstractRenderer {

  Group options;
  Collection<Button> buttons;

  /**
   * @see org.polarsys.capella.common.flexibility.wizards.schema.IRenderer#render(org.eclipse.swt.widgets.Composite)
   */
  @Override
  public void performRender(Composite parent_p, final IRendererContext rendererContext_p) {

    final IProperty property = rendererContext_p.getProperty(this);
    IPropertyContext propertyContext = rendererContext_p.getPropertyContext();

    options = new Group(parent_p, SWT.NONE);
    options.setText(property.getName());
    options.setData(property);
    options.setEnabled(property.isEnabled(propertyContext));

    if (parent_p.getLayout() instanceof GridLayout) {
      options.setLayout(new GridLayout(1, false));
      options.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
    }

    buttons = new ArrayList<Button>();
    for (IPropertyOption option : property.getOptions()) {
      Button dataExport = new Button(options, SWT.RADIO);
      dataExport.setText(option.getName());
      dataExport.setData(option);
      dataExport.setEnabled(option.isEnabled() && property.isEnabled(propertyContext));
      dataExport.setToolTipText(option.getDescription());
      dataExport.addSelectionListener(new SelectionListener() {

        public void widgetSelected(SelectionEvent e_p) {
          Object newValue = ((Button) e_p.widget).getData();
          if ((newValue != null) && (newValue instanceof IPropertyOption)) {
            String value = ((IPropertyOption) newValue).getValue();
            changeValue(property, rendererContext_p, value);
            updatedValue(property, rendererContext_p, value);
          }
        }

        public void widgetDefaultSelected(SelectionEvent e_p) {
          // Nothing here
        }
      });
      buttons.add(dataExport);
    }
  }

  public void initialize(IProperty property_p, IRendererContext propertyContext_p) {
    Object value = propertyContext_p.getPropertyContext().getDefaultValue(property_p);
    updatedValue(property_p, propertyContext_p, value);
  }

  @Override
  public void updatedValue(IProperty property_p, IRendererContext propertyContext_p, Object newValue_p) {
    if (property_p.equals(propertyContext_p.getProperty(this))) {
      for (Button button : buttons) {
        if ((button.getData() != null) && (button.getData() instanceof IPropertyOption)) {
          IPropertyOption option = (IPropertyOption) button.getData();
          button.setSelection(option.getValue().equals(newValue_p));
        }
      }
    }
  }

  @Override
  public void dispose(IRendererContext context_p) {
    options.dispose();
  }

}
