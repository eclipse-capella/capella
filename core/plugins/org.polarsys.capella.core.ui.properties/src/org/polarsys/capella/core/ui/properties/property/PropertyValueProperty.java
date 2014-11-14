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
import org.polarsys.capella.common.flexibility.properties.property.AbstractProperty;
import org.polarsys.capella.common.flexibility.properties.schema.IEditableProperty;
import org.polarsys.capella.common.flexibility.properties.schema.IPropertyContext;
import org.polarsys.capella.common.flexibility.properties.schema.IPropertyValueProperty;

/**
 */
public abstract class PropertyValueProperty extends AbstractProperty implements IEditableProperty, IPropertyValueProperty {

  protected abstract String[] getPathProperty();

  /**
   * @param element_p
   */
  protected void unsetPropertyValue(NamedElement element_p) {
    PropertiesServices.unsetProperty(element_p, getPathProperty());
  }

  /**
   * @param element_p
   * @param intValue_p
   */
  protected void setPropertyValue(NamedElement element_p, String value_p) {
    PropertiesServices.setPropertyValue(element_p, getPathProperty(), value_p, true);
  }

  /**
   * @param element_p
   * @return
   */
  protected String getPropertyValue(NamedElement element_p) {
    return PropertiesServices.getPropertyValue(element_p, getPathProperty(), ICommonConstants.EMPTY_STRING);
  }

  /**
   * @see org.polarsys.capella.common.flexibility.properties.schema.sirius.analysis.weightprice.properties.IProperty#getType()
   */
  public Object getType() {
    return Integer.class;
  }

  public Object toType(Object value_p, IPropertyContext context_p) {
    if ((value_p != null) && !(ICommonConstants.EMPTY_STRING.equals(value_p))) {
      return Integer.valueOf(value_p.toString());
    }
    return null;
  }

  public Object getValue(IPropertyContext context_p) {
    NamedElement element = (NamedElement) getSource(context_p);
    return new Integer(getPropertyValue(element));
  }

  /**
   * @see org.polarsys.capella.common.flexibility.properties.schema.sirius.analysis.weightprice.properties.IProperty#setValue(org.polarsys.capella.common.flexibility.properties.schema.sirius.analysis.weightprice.properties.IPropertyContext)
   */
  public void setValue(IPropertyContext context_p) {
    Object value = context_p.getCurrentValue(this);
    NamedElement element = (NamedElement) getSource(context_p);

    if ((value != null) && (value instanceof Integer)) {
      setPropertyValue(element, ((Integer) value).toString());
    } else {
      unsetPropertyValue(element);
    }
  }

  public IStatus validate(Object newValue, IPropertyContext context_p) {
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
