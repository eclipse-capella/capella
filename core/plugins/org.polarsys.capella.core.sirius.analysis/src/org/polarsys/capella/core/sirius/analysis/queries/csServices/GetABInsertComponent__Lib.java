/*******************************************************************************
 * Copyright (c) 2019, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.sirius.analysis.queries.csServices;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.libraries.ILibraryManager;
import org.polarsys.capella.common.libraries.IModel;
import org.polarsys.capella.common.libraries.manager.LibraryManagerExt;
import org.polarsys.capella.common.queries.ExtendingQuery;
import org.polarsys.capella.common.queries.exceptions.QueryException;
import org.polarsys.capella.common.queries.queryContext.IQueryContext;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.libraries.model.CapellaModel;
import org.polarsys.capella.core.libraries.queries.QueryExt;

@ExtendingQuery(extendingQuery = GetABInsertComponent.class)
public class GetABInsertComponent__Lib extends GetABInsertComponentActor {

  @Override
  public List<Object> execute(Object input, IQueryContext context) throws QueryException {
    Set<Component> components = new HashSet<>();
    EObject in = (EObject) input;
    IModel currentProject = ILibraryManager.INSTANCE.getModel(in);
    Collection<IModel> libraries = LibraryManagerExt.getAllActivesReferences(currentProject);
    for (IModel library : libraries) {
      BlockArchitecture architecture = (BlockArchitecture) QueryExt.getCorrespondingElementInLibrary(in,
          (CapellaModel) library);
      components.addAll(getComponentsToInsertFromArchitecture(input, architecture));
    }
    return new ArrayList<>(components);
  }
}
