/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
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

package org.polarsys.capella.core.model.helpers.queries;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.helpers.EObjectExt;
import org.polarsys.capella.common.queries.AbstractQuery;
import org.polarsys.capella.common.queries.exceptions.QueryException;
import org.polarsys.capella.common.queries.queryContext.IQueryContext;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.information.datatype.DatatypePackage;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;

/**
 */
public class GetAllBooleanTypes extends AbstractQuery {

  @Override
  public List<Object> execute(Object input, IQueryContext context) throws QueryException {
    List<Object> returnedCollections = new ArrayList<Object>();
    for (BlockArchitecture aBlockArchitecture : BlockArchitectureExt.getRootAndPreviousBlockArchitectures((EObject) input)) {
      for (EObject aCollection : EObjectExt.getAll(aBlockArchitecture, DatatypePackage.Literals.BOOLEAN_TYPE)) {
        returnedCollections.add(aCollection);
      }
    }
    return returnedCollections;
  }

}
