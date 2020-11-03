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
package org.polarsys.capella.core.ui.properties.property;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;
import org.polarsys.capella.core.data.capellacore.NamedElement;
import org.polarsys.capella.common.flexibility.properties.property.AbstractProperty;
import org.polarsys.capella.common.flexibility.properties.schema.IEditableProperty;
import org.polarsys.capella.common.flexibility.properties.schema.IPropertyContext;
import org.polarsys.capella.common.flexibility.properties.schema.IPropertyValueProperty;

/**
 */
public abstract class PropertyValueProperty extends AbstractProperty implements IEditableProperty, IPropertyValueProperty {

  protected abstract String[] getPathProperty();

  /**
   * @param element
   */
  protected void unsetPropertyValue(NamedElement element) {
    PropertiesServices.unsetProperty(element, getPathProperty());
  }

  /**
   * @param element
   * @param intValue
   */
  protected void setPropertyValue(NamedElement element, String value) {
    PropertiesServices.setPropertyValue(element, getPathProperty(), value, true);
  }

  /**
   * @param element
   * @return
   */
  protected String getPropertyValue(NamedElement element) {
    return PropertiesServices.getPropertyValue(element, getPathProperty(), ICommonConstants.EMPTY_STRING);
  }

  /**
   * @see org.polarsys.capella.common.flexibility.properties.schema.sirius.analysis.weightprice.properties.IProperty#getType()
   */
  public Object getType() {
    return Integer.class;
  }

  public Object toType(Object value, IPropertyContext context) {
    if ((value != null) && !(ICommonConstants.EMPTY_STRING.equals(value))) {
      return Integer.valueOf(value.toString());
    }
    return null;
  }

  public Object getValue(IPropertyContext context) {
    NamedElement element = (NamedElement) getSource(context);
    return Integer.valueOf(getPropertyValue(element));
  }

  /**
   * @see org.polarsys.capella.common.flexibility.properties.schema.sirius.analysis.weightprice.properties.IProperty#setValue(org.polarsys.capella.common.flexibility.properties.schema.sirius.analysis.weightprice.properties.IPropertyContext)
   */
  public void setValue(IPropertyContext context) {
    Object value = context.getCurrentValue(this);
    NamedElement element = (NamedElement) getSource(context);

    if ((value != null) && (value instanceof Integer)) {
      setPropertyValue(element, ((Integer) value).toString());
    } else {
      unsetPropertyValue(element);
    }
  }

  public IStatus validate(Object newValue, IPropertyContext context) {
    try {
      if ((newValue != null) && !(ICommonConstants.EMPTY_STRING.equals(newValue))) {
        Integer.parseInt(newValue.toString());
      }
      return Status.OK_STATUS;

    } catch (Exception e) {
      return new Status(IStatus.ERROR, getId(), e.getMessage());
    }
  }
}
