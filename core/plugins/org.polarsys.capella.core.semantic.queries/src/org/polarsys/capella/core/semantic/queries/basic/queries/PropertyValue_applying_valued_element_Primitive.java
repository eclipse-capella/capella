/*******************************************************************************
 * Copyright (c) 2019, 2020 THALES GLOBAL SERVICES.
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 * 
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 * Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.semantic.queries.basic.queries;

import java.util.ArrayList;
import java.util.List;

import org.polarsys.capella.common.helpers.query.IQuery;
import org.polarsys.capella.core.data.capellacore.BooleanPropertyValue;
import org.polarsys.capella.core.data.capellacore.FloatPropertyValue;
import org.polarsys.capella.core.data.capellacore.IntegerPropertyValue;
import org.polarsys.capella.core.data.capellacore.StringPropertyValue;

public class PropertyValue_applying_valued_element_Primitive implements IQuery {

  /**
   * Constructor.
   */
  public PropertyValue_applying_valued_element_Primitive() {
    // Do nothing...
  }

  /**
   * Compute the query that results in displaying the elements the property value is applied on.
   * 
   * @param object
   *          the selected element (property value) to apply the query on.
   * 
   * @return the list of elements the property value is applied on.
   * 
   * @see org.polarsys.capella.common.helpers.query.IQuery#compute(java.lang. Object)
   */
  @Override
  public List<Object> compute(Object object) {
    List<Object> result = new ArrayList<>();
    if (object instanceof IntegerPropertyValue) {
      IntegerPropertyValue pv = (IntegerPropertyValue) object;
      result.add(pv.getValue());
    } else if (object instanceof StringPropertyValue) {
      StringPropertyValue pv = (StringPropertyValue) object;
      result.add(pv.getValue());
    } else if (object instanceof BooleanPropertyValue) {
      BooleanPropertyValue pv = (BooleanPropertyValue) object;
      result.add(pv.isValue());
    } else if (object instanceof FloatPropertyValue) {
      FloatPropertyValue pv = (FloatPropertyValue) object;
      result.add(pv.getValue());
    }
    return result;
  }
}
