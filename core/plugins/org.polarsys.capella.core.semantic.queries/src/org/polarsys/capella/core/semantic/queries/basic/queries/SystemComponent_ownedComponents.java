/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
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

import org.polarsys.capella.core.data.la.LogicalComponent;
import org.polarsys.capella.core.data.pa.PhysicalComponent;
import static org.polarsys.capella.core.model.helpers.ModelHelpers.ComponentExt;
import org.polarsys.capella.common.helpers.query.IQuery;

/**
 * 
 */

/**
 *
 */
public class SystemComponent_ownedComponents implements IQuery {

  /**
   * 
   */
  public SystemComponent_ownedComponents() {
    // do nothing
  }

  /**
   * 
   * ownedComponents
   * 
   * @see org.polarsys.capella.common.helpers.query.IQuery#compute(java.lang.Object)
   */
  public List<Object> compute(Object object) {
    List<Object> result = new ArrayList<Object>();

    if (object instanceof LogicalComponent) {
      LogicalComponent lc = (LogicalComponent) object;
      result.addAll(ComponentExt.getSubDefinedComponents(lc));

    } else if (object instanceof PhysicalComponent) {
      PhysicalComponent lc = (PhysicalComponent) object;
      result.addAll(ComponentExt.getSubDefinedComponents(lc));
    }
    //TODO other subtypes ?? 

    return result;
  }

}
