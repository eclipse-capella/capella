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
import org.polarsys.capella.common.queries.exceptions.QueryException;
import org.polarsys.capella.common.queries.interpretor.QueryInterpretor;
import org.polarsys.capella.common.queries.queryContext.IQueryContext;
import org.polarsys.capella.core.libraries.model.CapellaModel;
import org.polarsys.capella.core.libraries.queries.QueryExt;
import org.polarsys.capella.core.model.helpers.ComponentExt;
import org.polarsys.capella.core.model.helpers.queries.GetBrotherComponents;

@ExtendingQuery (extendingQuery = GetBrotherComponents.class)
public class GetCCEIShowHideComponent__Lib extends AbstractQuery {

  @Override
  public List<Object> execute(Object input, IQueryContext context) throws QueryException {
    List<Object> result = new ArrayList<Object>();
    EObject in = (EObject) input;
    IModel currentProject =  ILibraryManager.INSTANCE.getModel(in);
    Collection<IModel> libraries = LibraryManagerExt.getAllActivesReferences(currentProject);
    for (IModel library : libraries) {
      EObject correspondingInput = QueryExt.getCorrespondingElementInLibrary(in, (CapellaModel) library);
      // !! TODO !!
      // CALL BY QueryIdentifierConstants
      // The refactoring of QueryIdentifierConstants is necessary
      List<Object> components = QueryInterpretor.executeQuery("GetCCIIInsertComponent", correspondingInput, context);
      components = filter(components);
      result.addAll(components);//$NON-NLS-1$
    }
    return result;
  }
  
  protected List<Object> filter(List<Object> components) {
    return components.stream().filter(o -> o instanceof EObject && !ComponentExt.isActor((EObject) o))
        .collect(Collectors.toList());
  }
}
