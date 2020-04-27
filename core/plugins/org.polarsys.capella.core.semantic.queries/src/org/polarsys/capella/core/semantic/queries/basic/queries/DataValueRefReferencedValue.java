/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
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
  public List<Object> compute(Object object) {
    List<Object> result = new ArrayList<Object>();
    if ((object instanceof DataValue) && DataValueExt.isDataValueReferenceType(object)) {
      result.add(DataValueExt.getReferencedValue((DataValue) object));
    }
    return result;
  }
}
