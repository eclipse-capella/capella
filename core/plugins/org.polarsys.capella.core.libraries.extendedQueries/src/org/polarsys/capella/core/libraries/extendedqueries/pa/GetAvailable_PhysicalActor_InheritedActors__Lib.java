/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/

package org.polarsys.capella.core.libraries.extendedqueries.pa;

import java.util.ArrayList;
import java.util.List;

import org.polarsys.capella.common.libraries.IModel;
import org.polarsys.capella.common.libraries.ILibraryManager;
import org.polarsys.capella.common.libraries.manager.LibraryManagerExt;
import org.polarsys.capella.common.queries.AbstractQuery;
import org.polarsys.capella.common.queries.queryContext.IQueryContext;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.helpers.capellacore.services.GeneralizableElementExt;
import org.polarsys.capella.core.data.helpers.ctx.services.ActorPkgExt;
import org.polarsys.capella.core.data.pa.PhysicalActor;
import org.polarsys.capella.core.data.pa.PhysicalActorPkg;
import org.polarsys.capella.core.data.pa.PhysicalArchitecture;
import org.polarsys.capella.core.libraries.model.CapellaModel;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.queries.helpers.QueryExt;

public class GetAvailable_PhysicalActor_InheritedActors__Lib extends AbstractQuery {

  @SuppressWarnings({ "rawtypes", "unchecked" })
  @Override
  public List<Object> execute(Object input, IQueryContext context) {
    List<CapellaElement> availableElements = new ArrayList<CapellaElement>();
    if (input instanceof PhysicalActor) {
      PhysicalActor currentActor = (PhysicalActor) input;
      CapellaElement element = (CapellaElement) input;
      BlockArchitecture blockArchitectureInProject = BlockArchitectureExt.getRootBlockArchitecture(element);
      IModel currentProject =  ILibraryManager.INSTANCE.getModel(element);
      for (IModel library : LibraryManagerExt.getAllActivesReferences(currentProject)) {
        BlockArchitecture currentBlockArchitecture = QueryExt.getCorrespondingBlockArchitectureFromLibrary(blockArchitectureInProject, (CapellaModel) library);
        if (currentBlockArchitecture instanceof PhysicalArchitecture) {
          PhysicalArchitecture pa = (PhysicalArchitecture) currentBlockArchitecture;
          PhysicalActorPkg paActorPkg = pa.getOwnedPhysicalActorPkg();
          if (paActorPkg != null) {
            for (PhysicalActor actor : ActorPkgExt.getAllActors(paActorPkg)) {
              if ((actor == null) || currentActor.equals(actor)) {
                continue;
              }
              if (!GeneralizableElementExt.getAllSuperGeneralizableElements(currentActor).contains(actor)
                  && !GeneralizableElementExt.getAllSuperGeneralizableElements(actor).contains(currentActor)) {
                availableElements.add(actor);
              }
            }
          }
        }
      }
    }
    return (List) availableElements;
  }
}