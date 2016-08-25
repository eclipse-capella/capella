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

package org.polarsys.capella.common.flexibility.properties.property;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.preferences.IScopeContext;
import org.eclipse.core.runtime.preferences.InstanceScope;

import org.polarsys.capella.common.flexibility.properties.schema.IPropertyContext;
import org.polarsys.capella.common.flexibility.properties.schema.PropertiesSchemaConstants;

/**
 * A property which returns always its default boolean value (can be useful when used with inheritancy mechanism)
 */
public class BooleanDefaultValueProperty extends AbstractProperty implements IDefaultValueProperty {

  /**
   * {@inheritDoc}
   */
  public Object getType() {
    return Boolean.class;
  }

  public IScopeContext getScope() {
    return InstanceScope.INSTANCE;
  }

  /**
   * {@inheritDoc}
   */
  public Object getValue(IPropertyContext context) {
    String argument = getParameter(PropertiesSchemaConstants.PropertiesSchema_PROPERTY_PREFERENCE__DEFAULT);
    return Boolean.valueOf(argument);
  }

  /**
   * {@inheritDoc}
   */
  public Object toType(Object value, IPropertyContext context) {
    Boolean result = Boolean.TRUE;
    try {
      if (value instanceof Boolean) {
        result = (Boolean) value;
      } else if (value instanceof String) {
        result = Boolean.valueOf((String) value);
      }
    } catch (Exception e) {
      //Nothing here
    }
    return result;
  }

  /**
   * {@inheritDoc}
   */
  public IStatus validate(Object newValue, IPropertyContext context) {
    if (newValue == null) {
      return Status.CANCEL_STATUS;
    }
    return Status.OK_STATUS;
  }

  /**
   * {@inheritDoc}
   */
  public Object getDefaultValue(IPropertyContext context) {
    String argument = getParameter(PropertiesSchemaConstants.PropertiesSchema_PROPERTY_PREFERENCE__DEFAULT);
    return ((Boolean) toType(argument, context)).booleanValue();
  }
}
