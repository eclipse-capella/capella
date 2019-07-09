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
package org.polarsys.capella.core.sirius.analysis.queries.csServices;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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
import org.polarsys.capella.core.model.helpers.queries.GetBrotherComponents;
import org.polarsys.capella.core.model.helpers.queries.filters.RemoveActorsFilter;
import org.polarsys.capella.core.model.helpers.queries.filters.RemoveContextFilter;

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
      result.addAll(QueryInterpretor.executeQuery("GetCCIIInsertComponent", correspondingInput, context, new RemoveActorsFilter()));//$NON-NLS-1$
    }
    result = QueryInterpretor.executeFilter(result, new RemoveContextFilter());
    return result;
  }
}
