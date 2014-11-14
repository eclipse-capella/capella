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

import org.polarsys.capella.common.flexibility.properties.schema.IPropertyContext;
import org.polarsys.capella.common.flexibility.properties.schema.PropertiesSchemaConstants;

/**
 *
 */
public class StringPropertyPreference extends PropertyPreference implements IDefaultValueProperty {

  /**
   * {@inheritDoc}
   */
  @Override
  public Object getType() {
    return String.class;
  }

  /**
   * {@inheritDoc}
   */
  public Object getDefaultValue(IPropertyContext context_p) {
    String argument = getParameter(PropertiesSchemaConstants.PropertiesSchema_PROPERTY_PREFERENCE__DEFAULT);
    return toType(argument, context_p);
  }

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
}
