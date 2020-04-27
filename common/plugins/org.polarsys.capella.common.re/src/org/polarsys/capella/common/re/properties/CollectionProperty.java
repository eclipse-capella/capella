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
  public Object getValue(IPropertyContext context) {
    if (_objects == null) {
      _objects = new ArrayList<Object>();
    }
    return _objects;
  }

  /**
   * {@inheritDoc}
   */
  public IStatus validate(Object newValue, IPropertyContext context) {
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
  public Object toType(Object value, IPropertyContext context) {
    if (value instanceof Collection) {
      return value;
    }
    ArrayList<Object> result = new ArrayList();
    result.add(value);
    return result;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setValue(IPropertyContext context) {
    _objects = (Collection) context.getCurrentValue(this);
  }

}
