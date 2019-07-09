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

import static org.polarsys.capella.core.business.abstractqueries.helpers.CapellaElementsHelperForBusinessQueries.isGoodSupertypeCandidate;

import java.util.ArrayList;
import java.util.List;

import org.polarsys.capella.common.libraries.ILibraryManager;
import org.polarsys.capella.common.libraries.IModel;
import org.polarsys.capella.common.libraries.manager.LibraryManagerExt;
import org.polarsys.capella.common.queries.AbstractQuery;
import org.polarsys.capella.common.queries.ExtendingQuery;
import org.polarsys.capella.common.queries.queryContext.IQueryContext;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.la.LogicalArchitecture;
import org.polarsys.capella.core.data.la.LogicalComponent;
import org.polarsys.capella.core.libraries.model.CapellaModel;
import org.polarsys.capella.core.libraries.queries.QueryExt;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;

@ExtendingQuery (extendingQuery = GetAvailable_LogicalComponent_Super.class)
public class GetAvailable_LogicalComponent_Super__Lib extends AbstractQuery {

  @SuppressWarnings({ "rawtypes", "unchecked" })
  @Override
  public List<Object> execute(Object input, IQueryContext context) {
    List<CapellaElement> availableElements = new ArrayList<CapellaElement>();
    if (input instanceof LogicalComponent) {
      LogicalComponent currentActor = (LogicalComponent) input;
      CapellaElement element = (CapellaElement) input;
      BlockArchitecture blockArchitectureInProject = BlockArchitectureExt.getRootBlockArchitecture(element);
      IModel currentProject =  ILibraryManager.INSTANCE.getModel(element);
      for (IModel library : LibraryManagerExt.getAllActivesReferences(currentProject)) {
        BlockArchitecture currentBlockArchitecture = QueryExt.getCorrespondingBlockArchitectureFromLibrary(blockArchitectureInProject, (CapellaModel) library);
        if (currentBlockArchitecture instanceof LogicalArchitecture) {
          LogicalArchitecture la = (LogicalArchitecture) currentBlockArchitecture;
          for (Component component : BlockArchitectureExt.getAllComponents(la)) {
            if (isGoodSupertypeCandidate(currentActor, component)) {
              availableElements.add(component);
            }
          }
        }
      }
    }
    return (List) availableElements;
  }
}