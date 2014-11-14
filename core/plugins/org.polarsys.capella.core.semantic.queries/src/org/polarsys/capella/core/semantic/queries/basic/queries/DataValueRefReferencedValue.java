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

import org.polarsys.capella.core.data.information.datavalue.DataValue;
import org.polarsys.capella.core.model.helpers.DataValueExt;
import org.polarsys.capella.common.helpers.query.IQuery;

/**
 * Return DataValue Reference --> Referenced Value
 */
public class DataValueRefReferencedValue implements IQuery {

  /**
	 * 
	 */
  public DataValueRefReferencedValue() {
    // do nothing
  }

  /**
   * current.incomingPortRealisation.realizingport
   * @see org.polarsys.capella.common.helpers.query.IQuery#compute(java.lang.Object)
   */
  public List<Object> compute(Object object_p) {
    List<Object> result = new ArrayList<Object>();
    if ((object_p instanceof DataValue) && DataValueExt.isDataValueReferenceType(object_p)) {
      result.add(DataValueExt.getReferencedValue((DataValue) object_p));
    }
    return result;
  }
}
