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

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

import org.polarsys.capella.common.flexibility.properties.property.AbstractProperty;
import org.polarsys.capella.common.flexibility.properties.schema.IEditableProperty;
import org.polarsys.capella.common.flexibility.properties.schema.IPropertyContext;

/**
 *
 */
public class CollectionProperty extends AbstractProperty implements IEditableProperty {

  private Collection<Object> _objects;

  /**
   * {@inheritDoc}
   */
  public Object getValue(IPropertyContext context_p) {
    if (_objects == null) {
      _objects = new ArrayList<Object>();
    }
    return _objects;
  }

  /**
   * {@inheritDoc}
   */
  public IStatus validate(Object newValue_p, IPropertyContext context_p) {
    return Status.OK_STATUS;
  }

  /**
   * {@inheritDoc}
   */
  public Object getType() {
    return Collection.class;
  }

  /**
   * {@inheritDoc}
   */
  public Object toType(Object value_p, IPropertyContext context_p) {
    if (value_p instanceof Collection) {
      return value_p;
    }
    ArrayList<Object> result = new ArrayList();
    result.add(value_p);
    return result;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setValue(IPropertyContext context_p) {
    _objects = (Collection) context_p.getCurrentValue(this);
  }

}
