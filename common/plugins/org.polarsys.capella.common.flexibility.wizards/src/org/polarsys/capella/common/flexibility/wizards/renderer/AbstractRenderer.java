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

import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.swt.widgets.Composite;

import org.polarsys.capella.common.flexibility.properties.schema.IEditableProperty;
import org.polarsys.capella.common.flexibility.properties.schema.IProperty;
import org.polarsys.capella.common.flexibility.wizards.schema.IPropertyRenderer;
import org.polarsys.capella.common.flexibility.wizards.schema.IRendererContext;

/**
 */
public abstract class AbstractRenderer implements IPropertyRenderer {

  private boolean disposed = true;

  /**
   * {@inheritDoc}
   */
  @Override
  public void render(Composite parent_p, IRendererContext context_p) {
    performRender(parent_p, context_p);
    setDisposed(false);
    initialize(context_p.getProperty(this), context_p);
  }

  public void performRender(Composite parent_p, IRendererContext context_p) {

  }

  public void setDisposed(boolean disposed_p) {
    disposed = disposed_p;
  }

  protected boolean isDisposed() {
    return disposed;
  }

  protected ILabelProvider getLabelProvider(IRendererContext context_p) {
    return context_p.getLabelProvider();
  }

  public void changeValue(IProperty property_p, IRendererContext context_p, Object newValue_p) {
    if (property_p instanceof IEditableProperty) {
      context_p.getPropertyContext().setCurrentValue(property_p, newValue_p);
    }
  }

  public void updatedValue(IProperty property_p, IRendererContext propertyContext_p, Object newValue_p) {

  }

  public void dispose(IRendererContext context_p) {
    setDisposed(true);
  }

}
