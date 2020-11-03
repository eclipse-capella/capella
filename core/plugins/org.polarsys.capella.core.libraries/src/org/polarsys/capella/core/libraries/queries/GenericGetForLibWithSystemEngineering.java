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
package org.polarsys.capella.core.libraries.queries;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.common.libraries.ILibraryManager;
import org.polarsys.capella.common.libraries.IModel;
import org.polarsys.capella.common.libraries.manager.LibraryManagerExt;
import org.polarsys.capella.common.queries.AbstractQuery;
import org.polarsys.capella.common.queries.exceptions.QueryException;
import org.polarsys.capella.common.queries.interpretor.QueryInterpretor;
import org.polarsys.capella.common.queries.queryContext.IQueryContext;
import org.polarsys.capella.core.libraries.model.CapellaModel;

public class GenericGetForLibWithSystemEngineering extends AbstractQuery {

  public List<Object> execute(Object input, IQueryContext context) throws QueryException {
    List<Object> result = new ArrayList<Object>();
    if ((input != null) && (input instanceof EObject)) {
      EObject in = (EObject) input;
      IModel currentProject = ILibraryManager.INSTANCE.getModel(in);

      if (currentProject != null) {
        Collection<IModel> libraries = LibraryManagerExt.getActivesReferences(currentProject);
        for (IModel library : libraries) {
          EObject correspondingInput = QueryExt.getSystemEngineeringFromLibrary(TransactionHelper.getEditingDomain(in), (CapellaModel) library);
          if (correspondingInput != null) {
            result.addAll(QueryInterpretor.executeQuery(getIdentifier(), correspondingInput, context));
          }
        }
      }
    }
    return result;
  }
}
