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
package org.polarsys.capella.core.business.queries.queries.cs;

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
import org.polarsys.capella.common.queries.filters.IQueryFilter;
import org.polarsys.capella.common.queries.filters.MultiFilter;
import org.polarsys.capella.common.queries.interpretor.QueryInterpretor;
import org.polarsys.capella.common.queries.queryContext.IQueryContext;
import org.polarsys.capella.core.business.queries.queries.cs.GetAvailable_Interface_InheritedInterfaces;
import org.polarsys.capella.core.data.cs.Interface;
import org.polarsys.capella.core.libraries.model.CapellaModel;
import org.polarsys.capella.core.libraries.queries.QueryExt;
import org.polarsys.capella.core.model.helpers.queries.filters.RemoveIndirectSuperTypesFilter;
import org.polarsys.capella.core.model.helpers.queries.filters.RemoveSubTypesFilter;

@ExtendingQuery (extendingQuery = GetAvailable_Interface_InheritedInterfaces.class)
public class GetAvailable_Interface_InheritedInterfaces__Lib extends AbstractQuery {

  public List<Object> execute(Object input, IQueryContext context) throws QueryException {
    List<Object> result = new ArrayList<Object>();
    Interface interfaze = (Interface) input;
    EObject in = (EObject) input;
    IModel currentProject =  ILibraryManager.INSTANCE.getModel(in);
    Collection<IModel> libraries = LibraryManagerExt.getAllActivesReferences(currentProject);
    for (IModel library : libraries) {
      EObject correspondingInput = QueryExt.getCorrespondingElementInLibrary(in, (CapellaModel) library);
      result.addAll(QueryInterpretor.executeQuery("GetAllInterfaces", correspondingInput, context));//$NON-NLS-1$
    }
    MultiFilter filter = new MultiFilter(new IQueryFilter[] { new RemoveSubTypesFilter(interfaze), new RemoveIndirectSuperTypesFilter(interfaze) });
    result = QueryInterpretor.executeFilter(result, filter);
    return result;
  }
}
