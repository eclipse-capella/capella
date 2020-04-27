/*******************************************************************************
 * Copyright (c) 2006, 2015 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.ui.properties.property;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;
import org.polarsys.capella.core.data.capellacore.NamedElement;
import org.polarsys.capella.common.flexibility.properties.schema.IPropertyContext;
import org.polarsys.capella.common.flexibility.properties.schema.PropertiesSchemaConstants;

/**
 */
public abstract class StringPropertyValueProperty extends PropertyValueProperty {

  @Override
  protected abstract String[] getPathProperty();

  /**
   * @param element
   */
  @Override
  protected void unsetPropertyValue(NamedElement element) {
    PropertiesServices.unsetProperty(element, getPathProperty());
  }

  /**
   * @param element
   * @param intValue
   */
  @Override
  protected void setPropertyValue(NamedElement element, String value) {
    PropertiesServices.setPropertyValue(element, getPathProperty(), value, true);
  }

  /**
   * @param element
   * @return
   */
  @Override
  protected String getPropertyValue(NamedElement element) {
    return PropertiesServices.getPropertyValue(element, getPathProperty(), ICommonConstants.EMPTY_STRING);
  }

  /**
   * @see org.polarsys.capella.common.flexibility.properties.schema.sirius.analysis.weightprice.properties.IProperty#getType()
   */
  @Override
  public Object getType() {
    return String.class;
  }

  @Override
  public Object toType(Object value, IPropertyContext context) {
    if ((value != null) && !(ICommonConstants.EMPTY_STRING.equals(value))) {
      return String.valueOf(value.toString());
    }
    return null;
  }

  @Override
  public Object getValue(IPropertyContext context) {
    NamedElement element = (NamedElement) getSource(context);
    return new String(getPropertyValue(element));
  }

  /**
   * @see org.polarsys.capella.common.flexibility.properties.schema.sirius.analysis.weightprice.properties.IProperty#setValue(org.polarsys.capella.common.flexibility.properties.schema.sirius.analysis.weightprice.properties.IPropertyContext)
   */
  @Override
  public void setValue(IPropertyContext context) {
    Object value = context.getCurrentValue(this);
    NamedElement element = (NamedElement) getSource(context);

    if ((value != null) && (value instanceof String)) {
      setPropertyValue(element, ((String) value));
    } else {
      unsetPropertyValue(element);
    }
  }

  @Override
  public IStatus validate(Object newValue, IPropertyContext context) {
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
