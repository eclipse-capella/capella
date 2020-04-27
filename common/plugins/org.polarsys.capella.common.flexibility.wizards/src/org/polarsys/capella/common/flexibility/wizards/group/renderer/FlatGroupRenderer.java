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

package org.polarsys.capella.common.flexibility.wizards.group.renderer;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;

import org.polarsys.capella.common.flexibility.properties.schema.IPropertyContext;
import org.polarsys.capella.common.flexibility.properties.schema.IPropertyGroup;
import org.polarsys.capella.common.flexibility.wizards.schema.IRendererContext;

/**
 *
 */
public class FlatGroupRenderer extends DefaultGroupRenderer {

  /**
   * 
   */
  @Override
  protected Composite createGroup(Composite parent, IPropertyGroup group, IPropertyContext context, IRendererContext rendererContext) {

    Composite parentComposite = null;

    parentComposite = new Composite(parent, SWT.NONE);
    GridLayout gridLayout = new GridLayout();
    gridLayout.marginHeight = 0;
    gridLayout.marginWidth = 0;
    parentComposite.setLayout(gridLayout);
    parentComposite.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
    return parentComposite;
  }

}
