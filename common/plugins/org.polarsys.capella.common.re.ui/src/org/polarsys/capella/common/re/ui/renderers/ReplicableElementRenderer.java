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
package org.polarsys.capella.common.re.ui.renderers;

import org.polarsys.capella.common.flexibility.properties.schema.IProperty;
import org.polarsys.capella.common.flexibility.wizards.renderer.BrowseRenderer;
import org.polarsys.capella.common.flexibility.wizards.schema.IRendererContext;
import org.polarsys.capella.common.re.CatalogElement;

/**
 */
public class ReplicableElementRenderer extends BrowseRenderer {

  @Override
  protected boolean isImage() {
    return false;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected boolean isEditable(IProperty property_p, IRendererContext context_p) {
    return super.isEditable(property_p, context_p);
  }

  @Override
  protected String getEditableText(IRendererContext context_p) {
    Object currentValue = context_p.getPropertyContext().getCurrentValue(context_p.getProperty(this));
    if (currentValue instanceof CatalogElement) {
      return ((CatalogElement) currentValue).getName();
    }
    return super.getEditableText(context_p);
  }

  @Override
  protected boolean isDeleteButton() {
    return false;
  }

}
