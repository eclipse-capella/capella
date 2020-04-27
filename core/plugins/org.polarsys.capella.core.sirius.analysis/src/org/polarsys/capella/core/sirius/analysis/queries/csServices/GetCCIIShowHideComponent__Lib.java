/*******************************************************************************
 * Copyright (c) 2006, 2019 THALES GLOBAL SERVICES.
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
import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.libraries.ILibraryManager;
import org.polarsys.capella.common.libraries.IModel;
import org.polarsys.capella.common.libraries.manager.LibraryManagerExt;
import org.polarsys.capella.common.queries.AbstractQuery;
import org.polarsys.capella.common.queries.ExtendingQuery;
import org.polarsys.capella.common.queries.interpretor.QueryInterpretor;
import org.polarsys.capella.common.queries.queryContext.IQueryContext;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.libraries.model.CapellaModel;
import org.polarsys.capella.core.libraries.queries.QueryExt;
import org.polarsys.capella.core.model.helpers.queries.QueryIdentifierConstants;

@ExtendingQuery(extendingQuery = GetCCIIShowHideComponent.class)
public class GetCCIIShowHideComponent__Lib extends AbstractQuery {

  @Override
  public List<Object> execute(Object input, IQueryContext context) {
    List<Component> result = new ArrayList<>();
    EObject element = (EObject) input;
    IModel currentProject = ILibraryManager.INSTANCE.getModel(element);
    Collection<IModel> libraries = LibraryManagerExt.getAllActivesReferences(currentProject);

    for (IModel library : libraries) {
      EObject correspondingInput = QueryExt.getCorrespondingElementInLibrary(element, (CapellaModel) library);
      List<Component> components = QueryInterpretor.executeQuery(QueryIdentifierConstants.GET_COMPONENTS,
          correspondingInput, context);

      result.addAll(components);
    }

    return filter(result).stream().filter(c -> !c.getRepresentingParts().isEmpty()).collect(Collectors.toList());
  }

  protected List<Component> filter(List<Component> components) {
    return components;
  }
}