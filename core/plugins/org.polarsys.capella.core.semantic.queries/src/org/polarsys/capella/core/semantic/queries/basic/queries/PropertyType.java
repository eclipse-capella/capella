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
package org.polarsys.capella.core.semantic.queries.basic.queries;

import java.util.ArrayList;
import java.util.List;

import org.polarsys.capella.core.data.information.InformationPackage;
import org.polarsys.capella.core.data.information.Property;
import org.polarsys.capella.core.data.capellacore.Type;
import org.polarsys.capella.common.helpers.query.IQuery;

/**
 * Return type of current Property
 *
 */
public class PropertyType implements IQuery {

  /**
   * Constructor
   */
  public PropertyType() {
    // does nothing
  }

  /**
   * @see org.polarsys.capella.common.helpers.query.IQuery#compute(java.lang.Object)
   */
  public List<Object> compute(Object object_p) {
    List<Object> result = new ArrayList<Object>();
    if (object_p instanceof Property) {
      Property property = (Property) object_p;
      if (property.eClass().getName().equalsIgnoreCase(InformationPackage.Literals.PROPERTY.getName())
          || property.eClass().getName().equalsIgnoreCase(InformationPackage.Literals.UNION_PROPERTY.getName())) {
        Type type = property.getType();
        if(null != type)
            result.add(type);
      }
    }
    return result;
  }
}
