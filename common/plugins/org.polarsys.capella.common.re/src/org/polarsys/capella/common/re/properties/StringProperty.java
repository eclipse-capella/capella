/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
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

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

import org.polarsys.capella.common.flexibility.properties.property.AbstractProperty;
import org.polarsys.capella.common.flexibility.properties.schema.IEditableProperty;
import org.polarsys.capella.common.flexibility.properties.schema.IPropertyContext;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 *
 */
public class StringProperty extends AbstractProperty implements IEditableProperty {

  private String _value;

  /**
   * {@inheritDoc}
   */
  public Object getValue(IPropertyContext context) {
    if (_value == null) {
      _value = "";
    }
    return _value;
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
    return String.class;
  }

  /**
   * {@inheritDoc}
   */
  public Object toType(Object value, IPropertyContext context) {
    return value.toString();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setValue(IPropertyContext context) {
    _value = (String) context.getCurrentValue(this);
    IContext ctx = (IContext) context.getSource();
    ctx.put(getId(), _value);
  }

}
