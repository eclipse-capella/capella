/*******************************************************************************
 * Copyright (c) 2006, 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.common.re.properties;

import org.polarsys.capella.common.flexibility.properties.property.AbstractProperty;
import org.polarsys.capella.common.flexibility.properties.schema.IEditableProperty;
import org.polarsys.capella.common.flexibility.properties.schema.IModifiedProperty;
import org.polarsys.capella.common.flexibility.properties.schema.IPropertyContext;
import org.polarsys.capella.common.re.CatalogElement;
import org.polarsys.capella.common.re.constants.IReConstants;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

public class SuffixProperty extends AbstractProperty implements IEditableProperty, IModifiedProperty {

  @Override
  public Object getValue(IPropertyContext context_p) {

    CatalogElement element = (CatalogElement) context_p.getCurrentValue(context_p.getProperties().getProperty(
        IReConstants.PROPERTY__REPLICABLE_ELEMENT__INITIAL_TARGET));

    if (element != null) {
      String suffix = element.getSuffix();
      return suffix == null ? "" : suffix;
    }

    return "";
  }

  @Override
  public Object getType() {
    return String.class;
  }

  @Override
  public Object toType(Object value_p, IPropertyContext context_p) {
    return value_p.toString();
  }

  @Override
  public boolean isModified(IPropertyContext context_p) {
    return true;
  }

  @Override
  public void setValue(IPropertyContext context_p) {
    
    CatalogElement element = (CatalogElement) context_p.getCurrentValue(context_p.getProperties().getProperty(
        IReConstants.PROPERTY__REPLICABLE_ELEMENT__INITIAL_TARGET));
    
    if (element != null) {
      Object value = context_p.getCurrentValue(this);
      if (value instanceof String) {
        String suffix = (String) value;
        
        element.setSuffix(suffix);
        
        IContext context = (IContext) context_p.getSource();
        context.put(getId(), suffix);
      }
    }
  }
}
