/*******************************************************************************
 * Copyright (c) 2006, 2018 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.transition.system.topdown.preferences;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.polarsys.capella.common.flexibility.properties.property.AbstractProperty;
import org.polarsys.capella.common.flexibility.properties.property.IDefaultValueProperty;
import org.polarsys.capella.common.flexibility.properties.schema.IEditableProperty;
import org.polarsys.capella.common.flexibility.properties.schema.IPropertyContext;
import org.polarsys.capella.common.flexibility.properties.schema.PropertiesSchemaConstants;
import org.polarsys.capella.core.commands.preferences.service.ScopedCapellaPreferencesStore;

/**
 */
public class BooleanPropertyPreference extends AbstractProperty implements IEditableProperty, IDefaultValueProperty {

  public BooleanPropertyPreference() {
    super();
  }

  /**
   * {@inheritDoc}
   */
  public Object getType() {
    return Boolean.class;
  }

  /**
   * {@inheritDoc}
   */
  public Object getValue(IPropertyContext context_p) {
    String scope = getParameter(PropertiesSchemaConstants.PropertiesSchema_PROPERTY_PREFERENCE__SCOPE);

    String preferenceId = getId();
    if (isArgumentSet(PropertiesSchemaConstants.PropertiesSchema_PROPERTY_PREFERENCE__PREFERENCE_ID)) {
      preferenceId = getParameter(PropertiesSchemaConstants.PropertiesSchema_PROPERTY_PREFERENCE__PREFERENCE_ID);
    }

    return ScopedCapellaPreferencesStore.getInstance(scope).getBoolean(preferenceId);
  }

  /**
   * {@inheritDoc}
   */
  public void setValue(IPropertyContext context_p) {
    String scope = getParameter(PropertiesSchemaConstants.PropertiesSchema_PROPERTY_PREFERENCE__SCOPE);
    Object value = context_p.getCurrentValue(this);

    String preferenceId = getId();
    if (isArgumentSet(PropertiesSchemaConstants.PropertiesSchema_PROPERTY_PREFERENCE__PREFERENCE_ID)) {
      preferenceId = getParameter(PropertiesSchemaConstants.PropertiesSchema_PROPERTY_PREFERENCE__PREFERENCE_ID);
    }

    ScopedCapellaPreferencesStore.getInstance(scope).setValue(preferenceId,
        ((Boolean) toType(value, context_p)).booleanValue());
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
      // Nothing here
    }
    return value;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IStatus validate(Object newValue_p, IPropertyContext context_p) {
    if (newValue_p == null) {
      return Status.CANCEL_STATUS;
    }
    return Status.OK_STATUS;
  }

  /**
   * {@inheritDoc}
   */
  public Boolean getDefaultValue(IPropertyContext context_p) {
    String argument = getParameter(PropertiesSchemaConstants.PropertiesSchema_PROPERTY_PREFERENCE__DEFAULT);
    return ((Boolean) toType(argument, context_p));
  }
  
  @Override
  public void initializeDefaultValue(IPropertyContext context) {
    String scope = getParameter(PropertiesSchemaConstants.PropertiesSchema_PROPERTY_PREFERENCE__SCOPE);
    String preferenceId = getId();
    if (isArgumentSet(PropertiesSchemaConstants.PropertiesSchema_PROPERTY_PREFERENCE__PREFERENCE_ID)) {
      preferenceId = getParameter(PropertiesSchemaConstants.PropertiesSchema_PROPERTY_PREFERENCE__PREFERENCE_ID);
    }
    Boolean value = getDefaultValue(context);
    ScopedCapellaPreferencesStore.getInstance(scope).setDefault(preferenceId, value);
  }
}
