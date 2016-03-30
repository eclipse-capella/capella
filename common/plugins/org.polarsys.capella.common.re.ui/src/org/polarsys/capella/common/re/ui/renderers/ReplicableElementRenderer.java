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
  protected boolean isEditable(IProperty property, IRendererContext context) {
    return super.isEditable(property, context);
  }

  @Override
  protected String getEditableText(IRendererContext context) {
    Object currentValue = context.getPropertyContext().getCurrentValue(context.getProperty(this));
    if (currentValue instanceof CatalogElement) {
      return ((CatalogElement) currentValue).getName();
    }
    return super.getEditableText(context);
  }

  @Override
  protected boolean isDeleteButton() {
    return false;
  }

}
