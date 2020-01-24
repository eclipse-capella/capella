/*******************************************************************************
 * Copyright (c) 2020 THALES GLOBAL SERVICES.
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

import org.polarsys.capella.common.helpers.query.IQuery;
import org.polarsys.capella.core.data.information.Property;
import org.polarsys.capella.core.data.information.datavalue.DataValue;
import org.polarsys.capella.core.model.helpers.DataValueExt;


public class DataValueRefReferencedProperty implements IQuery {

  @Override
  public List<Object> compute(Object object) {
    List<Object> result = new ArrayList<>();
   
    if ((object instanceof DataValue) && DataValueExt.isDataValueReferenceType(object)) {
      DataValue dataValue = (DataValue) object;
      Property referencedProperty = DataValueExt.getReferencedProperty(dataValue);
      
      if(referencedProperty != null) {
        result.add(referencedProperty);
      }
    }
    return result;
  }
}
