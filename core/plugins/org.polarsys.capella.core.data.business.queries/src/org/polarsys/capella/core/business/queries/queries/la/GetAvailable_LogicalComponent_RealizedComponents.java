/*******************************************************************************
 * Copyright (c) 2006, 2019 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.business.queries.queries.la;

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

public class GetAvailable_LogicalComponent_RealizedComponents extends AbstractQuery {

  @Override
  public List<Object> execute(Object input, IQueryContext context) {
    if (input instanceof Component) {
      Component element = (Component) input;
      SystemEngineering sysEng = CapellaQueries.getInstance().getRootQueries().getSystemEngineering(element);
      return BlockArchitectureExt.getAllComponents(SystemEngineeringExt.getSystemAnalysis(sysEng)).stream()
          .filter(
              x -> element.isActor() ? x.isActor() : BlockArchitectureExt.isRootComponent(x))
          .collect(Collectors.toList());
    }
    return Collections.emptyList();
  }
}