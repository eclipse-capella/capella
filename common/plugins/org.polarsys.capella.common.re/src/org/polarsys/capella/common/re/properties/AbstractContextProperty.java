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
package org.polarsys.capella.common.re.properties;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.polarsys.capella.common.flexibility.properties.schema.IEditableProperty;
import org.polarsys.capella.common.flexibility.properties.schema.IPropertyContext;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

public abstract class AbstractContextProperty extends AbstractReProperty implements IEditableProperty {

  /**
   * {@inheritDoc}
   */
  @Override
  public Object getValue(IPropertyContext context_p) {
    IContext context = getContext(context_p);
    Object element = context.get(getUniqueIdentifier());

    if (element == null) {
      element = getInitialValue(context_p);
      if (element != null) {
        context.put(getUniqueIdentifier(), element);
      }
    }

    return element;
  }

  @Override
  public Object getType() {
    return Object.class;
  }

  /**
   * @param context_p
   * @return
   */
  protected Object getInitialValue(IPropertyContext context_p) {
    Object element = null;
    return element;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Object toType(Object value_p, IPropertyContext context_p) {
    return value_p;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setValue(IPropertyContext context_p) {
    IContext context = getContext(context_p);
    Object element = context_p.getCurrentValue(this);
    context.put(getUniqueIdentifier(), element);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IStatus validate(Object newValue_p, IPropertyContext context_p) {
    return Status.OK_STATUS;
  }

}
