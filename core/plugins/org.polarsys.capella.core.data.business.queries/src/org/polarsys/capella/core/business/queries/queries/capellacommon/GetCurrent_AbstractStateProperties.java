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
package org.polarsys.capella.core.business.queries.queries.capellacommon;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EReference;
import org.polarsys.capella.common.data.behavior.AbstractEvent;
import org.polarsys.capella.common.queries.AbstractQuery;
import org.polarsys.capella.common.queries.queryContext.IQueryContext;
import org.polarsys.capella.core.business.queries.QueryConstants;
import org.polarsys.capella.core.data.capellacommon.CapellacommonPackage;
import org.polarsys.capella.core.data.capellacommon.State;
import org.polarsys.capella.core.data.capellacore.CapellaElement;

public class GetCurrent_AbstractStateProperties extends AbstractQuery {

  @Override
  public List<Object> execute(Object input, IQueryContext context) {
    List<CapellaElement> currentElements = new ArrayList<CapellaElement>();

    Object property = context.getValue(QueryConstants.ESTRUCTURAL_FEATURE_PARAMETER);
    EReference ref= (EReference) property;
    if (CapellacommonPackage.Literals.STATE__DO_ACTIVITY.equals(ref)) {
      for (AbstractEvent evt : ((State) input).getDoActivity()) {
        currentElements.add((CapellaElement) evt);
      }
      
    } else if (CapellacommonPackage.Literals.STATE__ENTRY.equals(ref)){
      for (AbstractEvent evt : ((State) input).getEntry()) {
        currentElements.add((CapellaElement) evt);
      }
      
    } else if (CapellacommonPackage.Literals.STATE__EXIT.equals(ref)){
      for (AbstractEvent evt : ((State) input).getExit()) {
        currentElements.add((CapellaElement) evt);
      }
    }
    return (List) currentElements;
  }

}
