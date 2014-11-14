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
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import org.polarsys.capella.common.flexibility.wizards.schema.IRendererContext;
import org.polarsys.capella.common.flexibility.properties.schema.IProperty;

/**
 *
 */
public class NullRenderer extends AbstractRenderer {
  Label label;

  /**
   * {@inheritDoc}
   */
  @Override
  public void performRender(Composite parent_p, IRendererContext rendererContext_p) {
    if (true) {
      return;
    }
    //Nothing here
    Composite composite = new Composite(parent_p, SWT.NONE);

    if (parent_p.getLayout() instanceof GridLayout) {
      GridData data = new GridData(GridData.FILL_HORIZONTAL);
      GridLayout layout = new GridLayout();
      layout.numColumns = 1;
      composite.setBackground(parent_p.getShell().getDisplay().getSystemColor(SWT.COLOR_DARK_RED));
      layout.makeColumnsEqualWidth = false;
      layout.marginWidth = 1;
      layout.marginHeight = 1;
      composite.setLayoutData(data);
      composite.setLayout(layout);
    } else {
      composite.setLayout(new FillLayout(SWT.HORIZONTAL));
    }

    label = new Label(composite, 0);
    GridData data = new GridData(GridData.FILL_HORIZONTAL);
    GridLayout layout = new GridLayout();
    layout.numColumns = 1;
    layout.makeColumnsEqualWidth = false;
    layout.marginWidth = 1;
    layout.marginHeight = 1;
    label.setLayoutData(data);

  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void updatedValue(IProperty property_p, IRendererContext propertyContext_p, Object newValue_p) {
    //Nothing here
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void dispose(IRendererContext context_p) {

  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void initialize(IProperty property_p, IRendererContext rendererContext_p) {
    if (label != null) {
      label.setText(property_p.getId());
    }
  }

}
