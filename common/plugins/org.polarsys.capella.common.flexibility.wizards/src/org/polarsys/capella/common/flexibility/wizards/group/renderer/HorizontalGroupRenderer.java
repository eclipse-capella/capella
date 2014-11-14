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
  protected Layout createGroupLayout(IPropertyGroup group_p, IRendererContext rendererContext_p) {

    int nbGroups = rendererContext_p.getRenderers().getGroups(rendererContext_p.getPropertyContext().getProperties(), group_p).size();
    int nbProperties = rendererContext_p.getRenderers().getItems(rendererContext_p.getPropertyContext().getProperties(), group_p).size();
    GridLayout layout = (GridLayout) super.createGroupLayout(group_p, rendererContext_p);
    layout.numColumns = nbGroups + nbProperties;

    return layout;
  }

}
