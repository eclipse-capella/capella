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

import org.polarsys.capella.core.data.fa.ComponentExchangeEnd;
import org.polarsys.capella.core.data.information.Partition;
import org.polarsys.capella.common.data.modellingcore.AbstractType;
import org.polarsys.capella.common.helpers.query.IQuery;

/**
 * This query returns connection end Part -> Component if any
 */
public class ConnectionEndComponent implements IQuery {

  /**
   * 
   */
  public ConnectionEndComponent() {
    // do nothing
  }

  /**
   * current.ownerElement
   * @see org.polarsys.capella.common.helpers.query.IQuery#compute(java.lang.Object)
   */
  public List<Object> compute(Object object_p) {
    List<Object> result = new ArrayList<Object>();
    if (object_p instanceof ComponentExchangeEnd) {
      ComponentExchangeEnd p = (ComponentExchangeEnd) object_p;
      Partition part = p.getPart();
      if (null != part) {
        AbstractType abstractType = part.getAbstractType();
        if (abstractType != null) {
          result.add(abstractType);
        } else {
          result.add(part);
        }
      }
    }
    return result;
  }
}
