/*******************************************************************************
 * Copyright (c) 2017 THALES GLOBAL SERVICES.
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
import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.core.runtime.preferences.IScopeContext;
import org.eclipse.core.runtime.preferences.InstanceScope;
import org.polarsys.capella.common.flexibility.properties.property.AbstractProperty;
import org.polarsys.capella.common.flexibility.properties.property.IDefaultValueProperty;
import org.polarsys.capella.common.flexibility.properties.schema.IEditableProperty;
import org.polarsys.capella.common.flexibility.properties.schema.IPropertyContext;
import org.polarsys.capella.common.flexibility.properties.schema.IPropertyOption;
import org.polarsys.capella.common.flexibility.properties.schema.PropertiesSchemaConstants;

/**
 * This stores the current value of the parent locator option in the eclipse preferences.
 */
public class ParentLocatorProperty extends AbstractProperty implements IEditableProperty, IDefaultValueProperty {

  /**
   * {@inheritDoc}
   */
  @Override
  public Object getType() {
    return String.class;
  }

  public IScopeContext getScope() {
    return InstanceScope.INSTANCE;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Object getValue(IPropertyContext context) {
    IEclipsePreferences preference = getScope().getNode(getParameter(PropertiesSchemaConstants.PropertiesSchema_PROPERTY_PREFERENCE__SCOPE));
    String defaultValue = getParameter(PropertiesSchemaConstants.PropertiesSchema_PROPERTY_PREFERENCE__DEFAULT);
    String preferenceId = getId();
    if (isArgumentSet(PropertiesSchemaConstants.PropertiesSchema_PROPERTY_PREFERENCE__PREFERENCE_ID)) {
      preferenceId = getParameter(PropertiesSchemaConstants.PropertiesSchema_PROPERTY_PREFERENCE__PREFERENCE_ID);
    }
    return preference.get(preferenceId, defaultValue);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Object toType(Object value, IPropertyContext context) {
    return value.toString();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IStatus validate(Object newValue, IPropertyContext context) {
    for (IPropertyOption option : getOptions()) {
      if (option.getValue().equals(newValue)){
        return Status.OK_STATUS;
      }
    }
    return Status.CANCEL_STATUS;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setValue(IPropertyContext context) {
    IEclipsePreferences preference = getScope().getNode(getParameter(PropertiesSchemaConstants.PropertiesSchema_PROPERTY_PREFERENCE__SCOPE));
    Object value = context.getCurrentValue(this);
    if (value != null) {
      preference.put(getId(), value.toString());
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Object getDefaultValue(IPropertyContext context) {
    return getParameter(PropertiesSchemaConstants.PropertiesSchema_PROPERTY_PREFERENCE__DEFAULT);
  }

  @Override
  public void initializeDefaultValue(IPropertyContext context) {

  }

}
