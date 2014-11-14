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
package org.polarsys.capella.common.flexibility.properties.property;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.core.runtime.preferences.IScopeContext;
import org.eclipse.core.runtime.preferences.InstanceScope;

import org.polarsys.capella.common.flexibility.properties.schema.IEditableProperty;
import org.polarsys.capella.common.flexibility.properties.schema.IPropertyContext;
import org.polarsys.capella.common.flexibility.properties.schema.PropertiesSchemaConstants;

/**
 */
public class BooleanPropertyPreference extends AbstractProperty implements IEditableProperty, IDefaultValueProperty {

  /**
   * {@inheritDoc}
   */
  public Object getType() {
    return Boolean.class;
  }

  public IScopeContext getScope() {
    return new InstanceScope();
  }

  /**
   * {@inheritDoc}
   */
  public Object getValue(IPropertyContext context_p) {
    IEclipsePreferences preference = getScope().getNode(getParameter(PropertiesSchemaConstants.PropertiesSchema_PROPERTY_PREFERENCE__SCOPE));
    String argument = getParameter(PropertiesSchemaConstants.PropertiesSchema_PROPERTY_PREFERENCE__DEFAULT);
    String preferenceId = getId();
    if (isArgumentSet(PropertiesSchemaConstants.PropertiesSchema_PROPERTY_PREFERENCE__PREFERENCE_ID)) {
      preferenceId = getParameter(PropertiesSchemaConstants.PropertiesSchema_PROPERTY_PREFERENCE__PREFERENCE_ID);
    }
    boolean value = preference.getBoolean(preferenceId, ((Boolean) toType(argument, context_p)).booleanValue());
    return Boolean.valueOf(value);
  }

  /**
   * {@inheritDoc}
   */
  public Object toType(Object value_p, IPropertyContext context_p) {
    Boolean value = Boolean.TRUE;
    try {
      if (value_p instanceof Boolean) {
        value = (Boolean) value_p;
      } else if (value_p instanceof String) {
        value = Boolean.valueOf((String) value_p);
      }
    } catch (Exception e) {
      //Nothing here
    }
    return value;
  }

  /**
   * {@inheritDoc}
   */
  public IStatus validate(Object newValue_p, IPropertyContext context_p) {
    if (newValue_p == null) {
      return Status.CANCEL_STATUS;
    }
    return Status.OK_STATUS;
  }

  /**
   * {@inheritDoc}
   */
  public void setValue(IPropertyContext context_p) {
    IEclipsePreferences preference = getScope().getNode(getParameter(PropertiesSchemaConstants.PropertiesSchema_PROPERTY_PREFERENCE__SCOPE));
    Object value = context_p.getCurrentValue(this);
    if (value != null) {
      preference.put(getId(), value.toString());
    }
  }

  /**
   * {@inheritDoc}
   */
  public Object getDefaultValue(IPropertyContext context_p) {
    String argument = getParameter(PropertiesSchemaConstants.PropertiesSchema_PROPERTY_PREFERENCE__DEFAULT);
    return ((Boolean) toType(argument, context_p)).booleanValue();
  }
}
