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

import org.eclipse.emf.common.util.EList;

import org.polarsys.capella.core.data.fa.FunctionalChain;
import org.polarsys.capella.core.data.interaction.AbstractCapability;
import org.polarsys.capella.core.data.interaction.FunctionalChainAbstractCapabilityInvolvement;
import org.polarsys.capella.core.data.capellacore.Involvement;
import org.polarsys.capella.common.helpers.query.IQuery;

/**
 * Return involving Capabilities of current functional chain
 */
public class FunctionalChainInvolvingCapability implements IQuery {

  /**
   * 
   */
  public FunctionalChainInvolvingCapability() {
    // do nothing
  }

  /**
   * current.getEnactedFunctions
   * @see org.polarsys.capella.common.helpers.query.IQuery#compute(java.lang.Object)
   */
  public List<Object> compute(Object object) {
    List<Object> result = new ArrayList<Object>();
    if (object instanceof FunctionalChain) {
      FunctionalChain chain = (FunctionalChain) object;
      EList<Involvement> involving = chain.getInvolvingInvolvements();
      for (Involvement involvement : involving) {
        if (involvement instanceof FunctionalChainAbstractCapabilityInvolvement) {
          FunctionalChainAbstractCapabilityInvolvement link = (FunctionalChainAbstractCapabilityInvolvement) involvement;
          AbstractCapability capability = link.getCapability();
          if (null != capability) {
            result.add(capability);
          }
        }
      }
    }
    return result;
  }
}
