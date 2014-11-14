/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.libraries.extendedqueries;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.libraries.IAbstractLibrary;
import org.polarsys.capella.common.libraries.IAbstractModel;
import org.polarsys.capella.common.libraries.ILibraryManager;
import org.polarsys.capella.common.queries.AbstractQuery;
import org.polarsys.capella.common.queries.exceptions.QueryException;
import org.polarsys.capella.common.queries.interpretor.QueryInterpretor;
import org.polarsys.capella.common.queries.queryContext.IQueryContext;
import org.polarsys.capella.core.libraries.capellaModel.CapellaLibrary;
import org.polarsys.capella.core.queries.helpers.QueryExt;

public class GenericGetForLibWithCorrespondingArchitectureElement extends AbstractQuery {

  public List<Object> execute(Object input_p, IQueryContext context_p) throws QueryException {
    List<Object> result = new ArrayList<Object>();
    EObject input = (EObject) input_p;
    IAbstractModel currentProject = ILibraryManager.INSTANCE.getAbstractModel(input);
    Collection<IAbstractLibrary> libraries = ILibraryManager.INSTANCE.getAllReferencedLibraries(currentProject, true);
    for (IAbstractLibrary library : libraries) {
      EObject correspondingInput = QueryExt.getCorrespondingElementInLibrary(input, (CapellaLibrary) library);
      result.addAll(QueryInterpretor.executeQuery(getExtendedQueryIdentifier(), correspondingInput, context_p));
    }
    return result;
  }
}
