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
public class StringPropertyPreference extends AbstractProperty implements IEditableProperty, IDefaultValueProperty {

  public StringPropertyPreference() {
    super();
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
  public Object getValue(IPropertyContext context_p) {
    String scope = getParameter(PropertiesSchemaConstants.PropertiesSchema_PROPERTY_PREFERENCE__SCOPE);

    String preferenceId = getId();
    if (isArgumentSet(PropertiesSchemaConstants.PropertiesSchema_PROPERTY_PREFERENCE__PREFERENCE_ID)) {
      preferenceId = getParameter(PropertiesSchemaConstants.PropertiesSchema_PROPERTY_PREFERENCE__PREFERENCE_ID);
    }

    return ScopedCapellaPreferencesStore.getInstance(scope).getString(preferenceId);
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

    ScopedCapellaPreferencesStore.getInstance(scope).setValue(preferenceId, ((String) toType(value, context_p)));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IStatus validate(Object newValue, IPropertyContext context_p) {
    try {
      if (newValue != null) {
        String value = String.valueOf(newValue);

        String validOnEmpty = getParameter(PropertiesSchemaConstants.PropertiesSchema_STRING_PROPERTY__EMPTY_IS_VALID);
        if ((validOnEmpty != null) && "false".equals(validOnEmpty) && (value.length() == 0)) {
          return new Status(IStatus.ERROR, getId(), "Empty value isn't valid");
        }
      }

      return Status.OK_STATUS;

    } catch (Exception e) {
      return new Status(IStatus.ERROR, getId(), e.getMessage());
    }
  }

  /**
   * {@inheritDoc}
   */
  public String getDefaultValue(IPropertyContext context_p) {
    String argument = getParameter(PropertiesSchemaConstants.PropertiesSchema_PROPERTY_PREFERENCE__DEFAULT);
    return ((String) toType(argument, context_p));
  }
  
  @Override
  public void initializeDefaultValue(IPropertyContext context) {
    String scope = getParameter(PropertiesSchemaConstants.PropertiesSchema_PROPERTY_PREFERENCE__SCOPE);
    String preferenceId = getId();
    if (isArgumentSet(PropertiesSchemaConstants.PropertiesSchema_PROPERTY_PREFERENCE__PREFERENCE_ID)) {
      preferenceId = getParameter(PropertiesSchemaConstants.PropertiesSchema_PROPERTY_PREFERENCE__PREFERENCE_ID);
    }
    String value = getDefaultValue(context);
    ScopedCapellaPreferencesStore.getInstance(scope).setDefault(preferenceId, value);
  }

  @Override
  public Object toType(Object value_p, IPropertyContext context_p) {
    return value_p;
  }

}
