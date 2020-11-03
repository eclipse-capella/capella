/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
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
 * return realizing function ports to current function port
 * 
 *
 */
public class FunctionPortRealizingFunctionPort implements IQuery {

  public FunctionPortRealizingFunctionPort() {
    // does nothing
  }

  /**
   * @see org.polarsys.capella.common.helpers.query.IQuery#compute(java.lang.Object)
   */
  public List<Object> compute(Object object) {
    List<Object> result = new ArrayList<Object>();
    if (object instanceof FunctionPort) {
      FunctionPort ele = (FunctionPort) object;
      EList<PortRealization> incomingPortRealizations = ele.getIncomingPortRealizations();
      for (PortRealization portRealization : incomingPortRealizations) {
        Port realizingPort = portRealization.getRealizingPort();
        if (null != realizingPort) {
          result.add(realizingPort);
        }
      }
    }
    
    return result;
  }
}
