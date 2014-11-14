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
   * @param element_p
   */
  @Override
  protected void unsetPropertyValue(NamedElement element_p) {
    PropertiesServices.unsetProperty(element_p, getPathProperty());
  }

  /**
   * @param element_p
   * @param intValue_p
   */
  @Override
  protected void setPropertyValue(NamedElement element_p, String value_p) {
    PropertiesServices.setPropertyValue(element_p, getPathProperty(), value_p, true);
  }

  /**
   * @param element_p
   * @return
   */
  @Override
  protected String getPropertyValue(NamedElement element_p) {
    return PropertiesServices.getPropertyValue(element_p, getPathProperty(), ICommonConstants.EMPTY_STRING);
  }

  /**
   * @see org.polarsys.capella.common.flexibility.properties.schema.sirius.analysis.weightprice.properties.IProperty#getType()
   */
  @Override
  public Object getType() {
    return String.class;
  }

  @Override
  public Object toType(Object value_p, IPropertyContext context_p) {
    if ((value_p != null) && !(ICommonConstants.EMPTY_STRING.equals(value_p))) {
      return String.valueOf(value_p.toString());
    }
    return null;
  }

  @Override
  public Object getValue(IPropertyContext context_p) {
    NamedElement element = (NamedElement) getSource(context_p);
    return new String(getPropertyValue(element));
  }

  /**
   * @see org.polarsys.capella.common.flexibility.properties.schema.sirius.analysis.weightprice.properties.IProperty#setValue(org.polarsys.capella.common.flexibility.properties.schema.sirius.analysis.weightprice.properties.IPropertyContext)
   */
  @Override
  public void setValue(IPropertyContext context_p) {
    Object value = context_p.getCurrentValue(this);
    NamedElement element = (NamedElement) getSource(context_p);

    if ((value != null) && (value instanceof String)) {
      setPropertyValue(element, ((String) value));
    } else {
      unsetPropertyValue(element);
    }
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
