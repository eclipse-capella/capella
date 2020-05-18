/*******************************************************************************
 * Copyright (c) 2006, 2019 THALES GLOBAL SERVICES.
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
import org.eclipse.core.runtime.Status;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.polarsys.capella.common.flexibility.properties.schema.IProperty;
import org.polarsys.capella.common.flexibility.wizards.constants.ICommonConstants;
import org.polarsys.capella.common.flexibility.wizards.schema.IRendererContext;
import org.polarsys.capella.common.flexibility.wizards.ui.util.StatusLabelHelper;

/**
 */
public class CheckboxRenderer extends AbstractRenderer {
  Button dataExport;
  protected Label validateControl;
  protected Composite rootControl;

  /**
   * @see org.polarsys.capella.common.flexibility.wizards.schema.IRenderer#render(org.eclipse.swt.widgets.Composite)
   */
  @Override
  public void performRender(Composite parent, final IRendererContext rendererContext) {

    final IProperty property = rendererContext.getProperty(this);

    rootControl = new Composite(parent, SWT.NONE);

    if (parent.getLayout() instanceof GridLayout) {
      GridLayout layout = new GridLayout();
      layout.numColumns = 2;
      layout.makeColumnsEqualWidth = false;
      layout.marginWidth = 0;
      layout.marginHeight = 0;
      GridData data = new GridData(GridData.END);
      rootControl.setLayoutData(data);
      rootControl.setLayout(layout);
    } else {
      rootControl.setLayout(new FillLayout(SWT.HORIZONTAL));
    }

    dataExport = new Button(rootControl, SWT.CHECK);

    if (!Boolean.FALSE.equals(rendererContext.getParameter(ICommonConstants.PARAMETER_RENDER_LABEL))) {
      dataExport.setText(property.getName());
    }

    dataExport.setToolTipText(property.getDescription());
    dataExport.setData(property);
    dataExport.setEnabled(property.isEnabled(rendererContext.getPropertyContext()));

    dataExport.addSelectionListener(new SelectionListener() {

      @Override
      public void widgetSelected(SelectionEvent e) {
        boolean newValue = ((Button) e.widget).getSelection();
        Boolean value = Boolean.valueOf(newValue);
        changeValue(property, rendererContext, value);
        updatedValue(property, rendererContext, value);
      }

      @Override
      public void widgetDefaultSelected(SelectionEvent e) {
        // Nothing here
      }
    });

    validateControl = createImageControl(rootControl);
    StatusLabelHelper.updateImage(Status.OK_STATUS, validateControl);
  }

  private Label createImageControl(Composite parent) {
    return new Label(parent, SWT.NONE);
  }

  @Override
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
      Object value = property.toType(newValue, rendererContext.getPropertyContext());
      IStatus status = property.validate(value, rendererContext.getPropertyContext());

      StatusLabelHelper.updateTooltip(status, validateControl, false);
      StatusLabelHelper.updateImage(status, validateControl);
      
      if (!dataExport.isDisposed()) {
        dataExport.setSelection(Boolean.valueOf(newValue.toString()).booleanValue());
        dataExport.setEnabled(prop.isEnabled(rendererContext.getPropertyContext()));
      }
    }
  }

  @Override
  public void dispose(IRendererContext context) {
    super.dispose(context);
    if (dataExport != null) {
      dataExport.dispose();
    }
    if (validateControl != null) {
      validateControl.dispose();
    }
  }

}
