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
package org.polarsys.capella.core.business.queries.la;

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
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.la.LaPackage;
import org.polarsys.capella.core.data.pa.PaPackage;

/**
 */
public class LogicalComponent_RealizedComponents implements IBusinessQuery {

	@Override
	public EClass getEClass() {
		return LaPackage.Literals.LOGICAL_COMPONENT;
	}

	@Override
	public List<EReference> getEStructuralFeatures() {
    List<EReference> returnedList = new ArrayList<EReference>();
    returnedList.add(CsPackage.Literals.COMPONENT__OWNED_COMPONENT_REALIZATIONS);
    returnedList.add(CsPackage.Literals.COMPONENT__REALIZED_COMPONENTS);
    returnedList.add(LaPackage.Literals.LOGICAL_COMPONENT__REALIZED_SYSTEM_COMPONENTS);
    return returnedList;
	}

	@Override
	public List<EObject> getAvailableElements(EObject element) {
		QueryContext context = new QueryContext();
		context.putValue(QueryConstants.ECLASS_PARAMETER, getEClass());
		return QueryInterpretor.executeQuery(QueryConstants.GET_AVAILABLE__LOGICAL_COMPONENT__REALIZED_COMPONENTS, element, context);
	}

	@Override
	public List<EObject> getCurrentElements(EObject element, boolean onlyGenerated) {
		QueryContext context = new QueryContext();
		context.putValue(QueryConstants.ECLASS_PARAMETER, getEClass());
		return QueryInterpretor.executeQuery(QueryConstants.GET_CURRENT__LOGICAL_COMPONENT__REALIZED_COMPONENTS, element, context);
	}
}
