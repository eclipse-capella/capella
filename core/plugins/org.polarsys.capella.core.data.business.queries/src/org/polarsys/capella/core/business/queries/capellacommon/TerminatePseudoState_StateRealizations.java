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
package org.polarsys.capella.core.business.queries.capellacommon;

import java.util.Collections;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EReference;
import org.polarsys.capella.common.queries.interpretor.QueryInterpretor;
import org.polarsys.capella.common.queries.queryContext.QueryContext;
import org.polarsys.capella.core.business.queries.IBusinessQuery;
import org.polarsys.capella.core.business.queries.QueryConstants;
import org.polarsys.capella.core.data.capellacommon.CapellacommonPackage;
import org.polarsys.capella.core.data.capellacore.CapellaElement;

/**
 */
public class TerminatePseudoState_StateRealizations extends AbstractState_AbstractStateRealizations implements IBusinessQuery {

	/**
	 * @see org.polarsys.capella.core.business.queries.IBusinessQuery#getEStructuralFeature()
	 */
	@Override
	public List<EReference> getEStructuralFeatures() {
		return Collections.singletonList(CapellacommonPackage.Literals.ABSTRACT_STATE__OWNED_ABSTRACT_STATE_REALIZATIONS);
	}

	/**
	 * @see org.polarsys.capella.core.business.queries.IBusinessQuery#getEClass()
	 */
	@Override
	public EClass getEClass() {
		return CapellacommonPackage.Literals.TERMINATE_PSEUDO_STATE;
	}

	@Override
	public List<CapellaElement> getAvailableElements(CapellaElement element_p) {
		QueryContext context = new QueryContext();
		context.putValue(QueryConstants.ECLASS_PARAMETER, getEClass());
		return QueryInterpretor.executeQuery(QueryConstants.GET_AVAILABLE__TERMINATE_PSEUDO_STATE__STATE_REALIZATIONS, element_p, context);
	}

	@Override
	public List<CapellaElement> getCurrentElements(CapellaElement element_p, boolean onlyGenerated_p) {
		QueryContext context = new QueryContext();
		context.putValue(QueryConstants.ECLASS_PARAMETER, getEClass());
		return QueryInterpretor.executeQuery(QueryConstants.GET_CURRENT__TERMINATE_PSEUDO_STATE__STATE_REALIZATIONS, element_p, context);
	}
}
