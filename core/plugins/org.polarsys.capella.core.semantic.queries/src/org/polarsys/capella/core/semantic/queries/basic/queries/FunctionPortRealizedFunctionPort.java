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

import org.polarsys.capella.core.data.fa.FunctionPort;
import org.polarsys.capella.core.data.information.Port;
import org.polarsys.capella.core.data.information.PortRealization;
import org.polarsys.capella.common.helpers.query.IQuery;

/**
 * return realized function ports current function port
 * 
 *
 */
public class FunctionPortRealizedFunctionPort implements IQuery {

  public FunctionPortRealizedFunctionPort() {
    // does nothing
  }

  /**
   * @see org.polarsys.capella.common.helpers.query.IQuery#compute(java.lang.Object)
   */
  public List<Object> compute(Object object) {
    List<Object> result = new ArrayList<Object>();
    if (object instanceof FunctionPort) {
      FunctionPort ele = (FunctionPort) object;
      EList<PortRealization> outgoingPortRealizations = ele.getOutgoingPortRealizations();
      for (PortRealization portRealization : outgoingPortRealizations) {
        Port realizedPort = portRealization.getRealizedPort();
        if (null != realizedPort) {
          result.add(realizedPort);
        }
      }
    }
    
    
    return result;
  }
}
