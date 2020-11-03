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
package org.polarsys.capella.core.business.queries.information;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.polarsys.capella.common.queries.interpretor.QueryInterpretor;
import org.polarsys.capella.common.queries.queryContext.QueryContext;
import org.polarsys.capella.core.business.queries.IBusinessQuery;
import org.polarsys.capella.core.business.queries.QueryConstants;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.data.information.ExchangeItem;
import org.polarsys.capella.core.data.information.InformationPackage;
import org.polarsys.capella.core.model.helpers.queries.filters.RemoveFinalElement;

/**
 */
public class ExchangeItem_InheritedExchangeItem extends AbstractClassInheritedClasses implements IBusinessQuery {

	@Override
	public EClass getEClass() {
		return InformationPackage.Literals.EXCHANGE_ITEM;
	}

	@Override
	public List<EReference> getEStructuralFeatures() {
	  return Collections.singletonList(CapellacorePackage.Literals.GENERALIZABLE_ELEMENT__SUPER);
	}


	@Override
	public List<EObject> getAvailableElements(EObject element) {
		QueryContext context = new QueryContext();
		context.putValue(QueryConstants.ECLASS_PARAMETER, getEClass());
		List<EObject> result = new ArrayList<EObject>();
		if (element instanceof ExchangeItem) {
			List<CapellaElement> elements = QueryInterpretor.executeQuery(QueryConstants.GET_AVAILABLE__EXCHANGE_ITEM__INHERITED_EXCHANGE_ITEM___LIB, element, context, new RemoveFinalElement());
			result.addAll(elements);
		}
		result.remove(element);
		return result;
	}
	@Override
	public List<EObject> getCurrentElements(EObject element, boolean onlyGenerated) {
		QueryContext context = new QueryContext();
		context.putValue(QueryConstants.ECLASS_PARAMETER, getEClass());
		List<EObject> result = new ArrayList<EObject>();
		if (element instanceof ExchangeItem) {
			List<CapellaElement> elements = QueryInterpretor.executeQuery(QueryConstants.GET_CURRENT__EXCHANGE_ITEM__INHERITED_EXCHANGE_ITEM, element, context, new RemoveFinalElement());
			result.addAll(elements);
		}
		return result;
	}

}
