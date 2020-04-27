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
  public void render(Composite parent, IRendererContext context) {
    performRender(parent, context);
    setDisposed(false);
    initialize(context.getProperty(this), context);
  }

  public void performRender(Composite parent, IRendererContext context) {

  }

  public void setDisposed(boolean disposed) {
    this.disposed = disposed;
  }

  protected boolean isDisposed() {
    return disposed;
  }

  protected ILabelProvider getLabelProvider(IRendererContext context) {
    return context.getLabelProvider();
  }

  public void changeValue(IProperty property, IRendererContext context, Object newValue) {
    if (property instanceof IEditableProperty) {
      context.getPropertyContext().setCurrentValue(property, newValue);
    }
  }

  public void updatedValue(IProperty property, IRendererContext propertyContext, Object newValue) {

  }

  public void dispose(IRendererContext context) {
    setDisposed(true);
  }

}
