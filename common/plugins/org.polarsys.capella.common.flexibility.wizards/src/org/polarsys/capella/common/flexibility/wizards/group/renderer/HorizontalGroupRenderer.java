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

package org.polarsys.capella.common.flexibility.wizards.group.renderer;

import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Layout;

import org.polarsys.capella.common.flexibility.properties.schema.IPropertyGroup;
import org.polarsys.capella.common.flexibility.wizards.schema.IRendererContext;

/**
 *
 */
public class HorizontalGroupRenderer extends DefaultGroupRenderer {

  /**
   * {@inheritDoc}
   */
  @Override
  protected Layout createGroupLayout(IPropertyGroup group, IRendererContext rendererContext) {

    int nbGroups = rendererContext.getRenderers().getGroups(rendererContext.getPropertyContext().getProperties(), group).size();
    int nbProperties = rendererContext.getRenderers().getItems(rendererContext.getPropertyContext().getProperties(), group).size();
    GridLayout layout = (GridLayout) super.createGroupLayout(group, rendererContext);
    layout.numColumns = nbGroups + nbProperties;

    return layout;
  }

}
