/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
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
  public void performRender(Composite parent, final IRendererContext rendererContext) {

    final IProperty property = rendererContext.getProperty(this);
    IPropertyContext propertyContext = rendererContext.getPropertyContext();

    options = new Group(parent, SWT.NONE);
    options.setText(property.getName());
    options.setData(property);
    options.setEnabled(property.isEnabled(propertyContext));

    if (parent.getLayout() instanceof GridLayout) {
      options.setLayout(new GridLayout(getNumColumns(property), false));
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

        public void widgetSelected(SelectionEvent e) {
          Button b = (Button) e.widget;
          if (b.getSelection()) {
            Object newValue = b.getData();
            if ((newValue != null) && (newValue instanceof IPropertyOption)) {
              String value = ((IPropertyOption) newValue).getValue();
              changeValue(property, rendererContext, value);
              updatedValue(property, rendererContext, value);
            }
          }
        }

        public void widgetDefaultSelected(SelectionEvent e) {
          // Nothing here
        }
      });
      buttons.add(dataExport);
    }
  }
  
  protected int getNumColumns(IProperty property) {
    return 1;
  }

  public void initialize(IProperty property, IRendererContext propertyContext) {
    Object value = propertyContext.getPropertyContext().getDefaultValue(property);
    updatedValue(property, propertyContext, value);
  }

  @Override
  public void updatedValue(IProperty property, IRendererContext propertyContext, Object newValue) {
    if (property.equals(propertyContext.getProperty(this))) {
      for (Button button : buttons) {
        if ((button.getData() != null) && (button.getData() instanceof IPropertyOption)) {
          IPropertyOption option = (IPropertyOption) button.getData();
          button.setSelection(option.getValue().equals(newValue));
        }
      }
    }
  }

  @Override
  public void dispose(IRendererContext context) {
    options.dispose();
  }

}
