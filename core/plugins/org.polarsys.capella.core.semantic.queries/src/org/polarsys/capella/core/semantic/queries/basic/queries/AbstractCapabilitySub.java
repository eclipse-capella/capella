/*******************************************************************************
 * Copyright (c) 2006, 2018 THALES GLOBAL SERVICES.
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

import org.polarsys.capella.core.data.interaction.AbstractCapability;
import org.polarsys.capella.common.helpers.query.IQuery;
import org.polarsys.capella.core.data.helpers.interaction.services.AbstractCapabilityExt;

/**
 * Recursively Return Sub AbstractCapabilities of current AbstractCapability
 */
public class AbstractCapabilitySub implements IQuery {

  /**
   * @see org.polarsys.capella.common.helpers.query.IQuery#compute(java.lang.Object)
   */
  public List<Object> compute(Object object) {

    List<Object> result = new ArrayList<Object>();
    if (object instanceof AbstractCapability) {

      AbstractCapability rootCap = (AbstractCapability) object;
      
      // Recursively get all inheriting generalizable elements (capabilities)
      List<AbstractCapability> subCaps = AbstractCapabilityExt.getSubInheritanceHierarchy(rootCap);
      if (!subCaps.isEmpty()) {
        result.addAll(subCaps);
      }
    }

    return result;
  }
}
