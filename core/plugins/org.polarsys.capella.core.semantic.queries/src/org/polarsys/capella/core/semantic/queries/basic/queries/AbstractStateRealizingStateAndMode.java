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

import org.polarsys.capella.core.data.capellacommon.AbstractState;
import org.polarsys.capella.core.data.capellacommon.AbstractStateRealization;
import org.polarsys.capella.common.data.modellingcore.AbstractTrace;
import org.polarsys.capella.common.data.modellingcore.IState;
import org.polarsys.capella.common.data.modellingcore.TraceableElement;
import org.polarsys.capella.common.helpers.query.IQuery;

/**
 * Return all the available Elements of State & Mode
 *
 */
public abstract class AbstractStateRealizingStateAndMode implements IQuery {

  public AbstractStateRealizingStateAndMode() {
    // does nothing
  }

  /**
   * @see org.polarsys.capella.common.helpers.query.IQuery#compute(java.lang.Object)
   */
  public List<Object> compute(Object object) {
    List<Object> result = new ArrayList<Object>();
    if (object instanceof AbstractState) {
      AbstractState state = (AbstractState) object;
      EList<AbstractTrace> traces = state.getIncomingTraces();
      for (AbstractTrace trace : traces) {
        if (trace instanceof AbstractStateRealization ) {
          TraceableElement targetElement = getTargetOrSourceElement((AbstractStateRealization)trace);
          if (targetElement instanceof IState) {
            if (isValidElement((IState) targetElement)) {
              result.add(targetElement);
            } 
          }
        }
        
      }
    }
    return result;
  }
  
  public abstract boolean isValidElement(IState abstractState);
  
  public abstract TraceableElement getTargetOrSourceElement(AbstractStateRealization realization);
  
}
