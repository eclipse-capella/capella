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
package org.polarsys.capella.core.business.queries.queries.pa;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.polarsys.capella.common.queries.AbstractQuery;
import org.polarsys.capella.common.queries.queryContext.IQueryContext;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.model.helpers.SystemEngineeringExt;
import org.polarsys.capella.core.model.helpers.query.CapellaQueries;

public class GetAvailable_PhysicalComponent_RealizedComponents extends AbstractQuery {

  @Override
  public List<Object> execute(Object input, IQueryContext context) {
    if (input instanceof Component) {
      Component element = (Component) input;
      SystemEngineering sysEng = CapellaQueries.getInstance().getRootQueries().getSystemEngineering(element);
      return BlockArchitectureExt.getAllComponents(SystemEngineeringExt.getLogicalArchitecture(sysEng)).stream()
          .filter(x -> (element.isActor() == x.isActor()))
          .collect(Collectors.toList());
    }
    return Collections.emptyList();
  }

}