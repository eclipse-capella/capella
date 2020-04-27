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
package org.polarsys.capella.common.re.properties;

import org.polarsys.capella.common.flexibility.properties.property.AbstractProperty;
import org.polarsys.capella.common.flexibility.properties.schema.ICompoundProperty;
import org.polarsys.capella.common.flexibility.properties.schema.IEditableProperty;
import org.polarsys.capella.common.flexibility.properties.schema.IModifiedProperty;
import org.polarsys.capella.common.flexibility.properties.schema.IProperty;
import org.polarsys.capella.common.flexibility.properties.schema.IPropertyContext;
import org.polarsys.capella.common.re.CatalogElement;
import org.polarsys.capella.common.re.constants.IReConstants;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

public class SuffixProperty extends AbstractProperty implements IEditableProperty, IModifiedProperty, ICompoundProperty {

  @Override
  public Object getValue(IPropertyContext context1) {

    CatalogElement element =
        (CatalogElement) context1.getCurrentValue(context1.getProperties().getProperty(IReConstants.PROPERTY__REPLICABLE_ELEMENT__INITIAL_TARGET));

    if (element != null) {
      String suffix = element.getSuffix();
      if (suffix == null) {
        suffix = "";
      }

      IContext context = (IContext) context1.getSource();
      context.put(IReConstants.ORIGINAL_SUFFIX, suffix);
      return suffix;
    }
    return "";
  }

  @Override
  public Object getType() {
    return String.class;
  }

  @Override
  public String[] getRelatedProperties() {
    return new String[] { IReConstants.PROPERTY__REPLICABLE_ELEMENT__INITIAL_TARGET };
  }

  @Override
  public Object toType(Object value, IPropertyContext context) {
    return value.toString();
  }

  @Override
  public boolean isModified(IPropertyContext context) {
    return true;
  }

  @Override
  public void setValue(IPropertyContext context1) {

    CatalogElement element =
        (CatalogElement) context1.getCurrentValue(context1.getProperties().getProperty(IReConstants.PROPERTY__REPLICABLE_ELEMENT__INITIAL_TARGET));

    if (element != null) {
      Object defaultValue = context1.getDefaultValue(this);
      Object value = context1.getCurrentValue(this);
      if (value instanceof String) {
        String suffix = (String) value;
        element.setSuffix(suffix);

        IContext context = (IContext) context1.getSource();
        context.put(IReConstants.ORIGINAL_SUFFIX, defaultValue);
      }
    }
  }

  @Override
  public void updatedValue(IProperty property, IPropertyContext context) {

  }
}
