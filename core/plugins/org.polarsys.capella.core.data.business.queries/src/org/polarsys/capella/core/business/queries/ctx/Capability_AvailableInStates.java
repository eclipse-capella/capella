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
package org.polarsys.capella.core.business.queries.ctx;

import java.util.Collections;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EReference;
import org.polarsys.capella.common.queries.interpretor.QueryInterpretor;
import org.polarsys.capella.common.queries.queryContext.QueryContext;
import org.polarsys.capella.core.business.queries.IBusinessQuery;
import org.polarsys.capella.core.business.queries.QueryConstants;
import org.polarsys.capella.core.business.queries.interaction.AbstractCapability_AvailableInStates;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.ctx.CtxPackage;
import org.polarsys.capella.core.data.interaction.InteractionPackage;

/**
 * Return current and available States and Modes of AbstractCapability
 */
public class Capability_AvailableInStates extends AbstractCapability_AvailableInStates implements IBusinessQuery {
	/**
	 * @see org.polarsys.capella.core.business.queries.capellacore.IBusinessQuery#getEStructuralFeature()
	 */
	@Override
	public List<EReference> getEStructuralFeatures() {
		return Collections.singletonList(InteractionPackage.Literals.ABSTRACT_CAPABILITY__AVAILABLE_IN_STATES);
	}

	/**
	 * @see org.polarsys.capella.core.business.queries.capellacore.IBusinessQuery#getEClass()
	 */
	@Override
	public EClass getEClass() {
		return CtxPackage.Literals.CAPABILITY;
	}

	@Override
	public List<CapellaElement> getAvailableElements(CapellaElement element_p) {
		QueryContext context = new QueryContext();
		context.putValue(QueryConstants.ECLASS_PARAMETER, getEClass());
		return QueryInterpretor.executeQuery(QueryConstants.GET_AVAILABLE__CAPABILITY__AVAILABLE_IN_STATES, element_p, context);
	}

	@Override
	public List<CapellaElement> getCurrentElements(CapellaElement element_p, boolean onlyGenerated_p) {
		QueryContext context = new QueryContext();
		context.putValue(QueryConstants.ECLASS_PARAMETER, getEClass());
		return QueryInterpretor.executeQuery(QueryConstants.GET_CURRENT__CAPABILITY__AVAILABLE_IN_STATES, element_p, context);
	}
}
