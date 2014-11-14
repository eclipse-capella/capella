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
package org.polarsys.capella.core.business.queries.information;

import java.util.Collections;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EReference;
import org.polarsys.capella.common.queries.interpretor.QueryInterpretor;
import org.polarsys.capella.common.queries.queryContext.QueryContext;
import org.polarsys.capella.core.business.queries.IBusinessQuery;
import org.polarsys.capella.core.business.queries.QueryConstants;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.information.datatype.DatatypePackage;

/**
 * This is the query for the Physical quantity realized information
 */
public class PhysicalQuantity_RealizedPhysicalQuantity extends DataTypeElement_RealizedInformation implements IBusinessQuery {

	/**
	 * @see org.polarsys.capella.core.business.queries.capellacore.IBusinessQuery#getEStructuralFeatures()
	 */
	@Override
	public List<EReference> getEStructuralFeatures() {
		return Collections.singletonList(DatatypePackage.Literals.DATA_TYPE__OWNED_INFORMATION_REALIZATIONS);
	}

	/**
	 * @see org.polarsys.capella.core.business.queries.capellacore.IBusinessQuery#getEClass()
	 */
	@Override
	public EClass getEClass() {
		return DatatypePackage.Literals.PHYSICAL_QUANTITY;
	}

	@Override
	public List<CapellaElement> getAvailableElements(CapellaElement element_p) {
		QueryContext context = new QueryContext();
		context.putValue(QueryConstants.ECLASS_PARAMETER, getEClass());
		return QueryInterpretor.executeQuery(QueryConstants.GET_AVAILABLE__PHYSICAL_QUANTITY__REALIZED_PHYSICAL_QUANTITY, element_p, context);
	}

	@Override
	public List<CapellaElement> getCurrentElements(CapellaElement element_p, boolean onlyGenerated_p) {
		QueryContext context = new QueryContext();
		context.putValue(QueryConstants.ECLASS_PARAMETER, getEClass());
		return QueryInterpretor.executeQuery(QueryConstants.GET_CURRENT__PHYSICAL_QUANTITY__REALIZED_PHYSICAL_QUANTITY, element_p, context);
	}
}
