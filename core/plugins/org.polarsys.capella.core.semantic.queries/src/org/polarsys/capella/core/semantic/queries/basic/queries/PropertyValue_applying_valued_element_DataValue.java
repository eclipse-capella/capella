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
import org.polarsys.capella.core.data.information.datavalue.LiteralBooleanValue;
import org.polarsys.capella.core.data.information.datavalue.LiteralNumericValue;
import org.polarsys.capella.core.data.information.datavalue.LiteralStringValue;

public class PropertyValue_applying_valued_element_DataValue implements IQuery {

  /**
   * Constructor.
   */
  public PropertyValue_applying_valued_element_DataValue() {
  }

  @Override
  public List<Object> compute(Object object) {
    List<Object> result = new ArrayList<>();
    if (object instanceof LiteralStringValue) {
      LiteralStringValue pv = (LiteralStringValue) object;
      result.add(pv.getValue());
    }
    else if (object instanceof LiteralNumericValue) {
      LiteralNumericValue pv = (LiteralNumericValue) object;
      result.add(pv.getValue());
    }
    else if (object instanceof LiteralBooleanValue) {
      LiteralBooleanValue pv = (LiteralBooleanValue) object;
      result.add(pv.isValue());
    }

    return result;
  }
}
