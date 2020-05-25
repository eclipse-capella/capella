/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.business.queries.queries.capellacommon;

import java.util.ArrayList;
import java.util.List;

import org.polarsys.capella.common.data.behavior.AbstractEvent;
import org.polarsys.capella.common.queries.AbstractQuery;
import org.polarsys.capella.common.queries.queryContext.IQueryContext;
import org.polarsys.capella.core.data.capellacommon.StateTransition;
import org.polarsys.capella.core.data.capellacore.CapellaElement;

public class GetCurrent_StateTransitionTrigger extends AbstractQuery {

  @Override
  public List<Object> execute(Object input, IQueryContext context) {
    CapellaElement inputElement = (CapellaElement) input;
    List<CapellaElement> currentElements = new ArrayList<CapellaElement>();
    if (inputElement instanceof StateTransition) {
      for (AbstractEvent evt : ((StateTransition) inputElement).getTriggers()) {
        if (evt != null) {
          currentElements.add((CapellaElement) evt);
        }
      }
    }
    return (List) currentElements;
  }

}
