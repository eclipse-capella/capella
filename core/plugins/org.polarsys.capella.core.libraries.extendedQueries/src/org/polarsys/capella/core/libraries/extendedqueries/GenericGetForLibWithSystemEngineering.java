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
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.polarsys.capella.common.libraries.ILibraryManager;
import org.polarsys.capella.common.libraries.IModel;
import org.polarsys.capella.common.libraries.manager.LibraryManagerExt;
import org.polarsys.capella.common.queries.AbstractQuery;
import org.polarsys.capella.common.queries.exceptions.QueryException;
import org.polarsys.capella.common.queries.interpretor.QueryInterpretor;
import org.polarsys.capella.common.queries.queryContext.IQueryContext;
import org.polarsys.capella.core.libraries.model.CapellaModel;
import org.polarsys.capella.core.queries.helpers.QueryExt;

public class GenericGetForLibWithSystemEngineering extends AbstractQuery {

  public List<Object> execute(Object input_p, IQueryContext context_p) throws QueryException {
    List<Object> result = new ArrayList<Object>();
    if ((input_p != null) && (input_p instanceof EObject)) {
      EObject input = (EObject) input_p;
      IModel currentProject = ILibraryManager.INSTANCE.getModel(input);

      if (currentProject != null) {
        Collection<IModel> libraries = LibraryManagerExt.getActivesReferences(currentProject);
        for (IModel library : libraries) {
          EObject correspondingInput = QueryExt.getSystemEngineeringFromLibrary(TransactionUtil.getEditingDomain(input), (CapellaModel) library);
          if (correspondingInput != null) {
            result.addAll(QueryInterpretor.executeQuery(getIdentifier(), correspondingInput, context_p));
          }
        }
      }
    }
    return result;
  }
}
