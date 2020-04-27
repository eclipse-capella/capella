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
package org.polarsys.capella.core.business.queries.information;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.polarsys.capella.common.queries.interpretor.QueryInterpretor;
import org.polarsys.capella.common.queries.queryContext.QueryContext;
import org.polarsys.capella.core.business.queries.IBusinessQuery;
import org.polarsys.capella.core.business.queries.QueryConstants;
import org.polarsys.capella.core.business.queries.capellacore.AbstractExchangeItem_RealizedInformations;
import org.polarsys.capella.core.data.information.InformationPackage;

/**
 */
public class ExchangeItem_RealizedInformations extends AbstractExchangeItem_RealizedInformations implements IBusinessQuery {

	@Override
	public EClass getEClass() {
		return InformationPackage.Literals.EXCHANGE_ITEM;
	}

	@Override
	public List<EReference> getEStructuralFeatures() {
		List<EReference> list = new ArrayList<EReference>(1);
		list.add(InformationPackage.Literals.EXCHANGE_ITEM__OWNED_INFORMATION_REALIZATIONS);
		return list;
	}

	@Override
	public List<EObject> getAvailableElements(EObject element) {
		QueryContext context = new QueryContext();
		context.putValue(QueryConstants.ECLASS_PARAMETER, getEClass());
		return QueryInterpretor.executeQuery(QueryConstants.GET_AVAILABLE__EXCHANGE_ITEM__REALIZED_INFORMATIONS, element, context);
	}

	@Override
	public List<EObject> getCurrentElements(EObject element, boolean onlyGenerated) {
		QueryContext context = new QueryContext();
		context.putValue(QueryConstants.ECLASS_PARAMETER, getEClass());
		return QueryInterpretor.executeQuery(QueryConstants.GET_CURRENT__EXCHANGE_ITEM__REALIZED_INFORMATIONS, element, context);
	}
}
