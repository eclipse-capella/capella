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
import org.polarsys.capella.common.queries.interpretor.QueryInterpretor;
import org.polarsys.capella.common.queries.queryContext.IQueryContext;
import org.polarsys.capella.core.business.queries.queries.cs.GetAvailable_Part_Type;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.libraries.model.CapellaModel;
import org.polarsys.capella.core.libraries.queries.QueryExt;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.model.helpers.queries.QueryIdentifierConstants;

@ExtendingQuery (extendingQuery = GetAvailable_Part_Type.class)
public class GetAvailable_Part_Type__Lib extends AbstractQuery {

	@Override
	public List<Object> execute(Object input, IQueryContext context) throws QueryException {
		List<Object> result = new ArrayList<Object>();
		EObject in = (EObject) input;
		IModel currentProject =  ILibraryManager.INSTANCE.getModel(in);
		Collection<IModel> libraries = LibraryManagerExt.getAllActivesReferences(currentProject);
		for (IModel library : libraries) {
			BlockArchitecture blockArchitecture = (BlockArchitecture) QueryExt.getCorrespondingElementInLibrary(in, (CapellaModel) library);
			for (BlockArchitecture currentBlockArchitecture : BlockArchitectureExt.getAllAllocatedArchitectures(blockArchitecture)) {
				result.addAll(QueryInterpretor.executeQuery(QueryIdentifierConstants.GET_ALL_SUB_DEFINED_COMPONENTS, currentBlockArchitecture, context));
			}
		}
		return result;
	}
}
