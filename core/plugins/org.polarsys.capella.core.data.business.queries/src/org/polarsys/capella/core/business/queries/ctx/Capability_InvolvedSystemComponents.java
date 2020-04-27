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

package org.polarsys.capella.core.business.queries.ctx;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.polarsys.capella.common.queries.interpretor.QueryInterpretor;
import org.polarsys.capella.common.queries.queryContext.QueryContext;
import org.polarsys.capella.core.business.queries.IBusinessQuery;
import org.polarsys.capella.core.business.queries.QueryConstants;
import org.polarsys.capella.core.data.ctx.CtxPackage;

/**
 */
public class Capability_InvolvedSystemComponents implements IBusinessQuery {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public EClass getEClass() {
		return CtxPackage.Literals.CAPABILITY;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<EReference> getEStructuralFeatures() {
		List<EReference> returnedList = new ArrayList<EReference>();
		returnedList.add(CtxPackage.Literals.CAPABILITY__INVOLVED_SYSTEM_COMPONENTS);
		returnedList.add(CtxPackage.Literals.CAPABILITY__OWNED_CAPABILITY_INVOLVEMENTS);
		return returnedList;
	}

	@Override
	public List<EObject> getAvailableElements(EObject element) {
		QueryContext context = new QueryContext();
		context.putValue(QueryConstants.ECLASS_PARAMETER, getEClass());
		return QueryInterpretor.executeQuery(QueryConstants.GET_AVAILABLE__CAPABILITY__INVOLVED_SYSTEM_COMPONENTS, element, context);
	}

	@Override
	public List<EObject> getCurrentElements(EObject element, boolean onlyGenerated) {
		QueryContext context = new QueryContext();
		context.putValue(QueryConstants.ECLASS_PARAMETER, getEClass());
		return QueryInterpretor.executeQuery(QueryConstants.GET_CURRENT__CAPABILITY__INVOLVED_SYSTEM_COMPONENTS, element, context);
	}
}
