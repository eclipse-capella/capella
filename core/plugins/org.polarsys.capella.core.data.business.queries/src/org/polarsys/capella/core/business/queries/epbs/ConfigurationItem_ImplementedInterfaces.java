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
package org.polarsys.capella.core.business.queries.epbs;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EReference;
import org.polarsys.capella.common.queries.interpretor.QueryInterpretor;
import org.polarsys.capella.common.queries.queryContext.QueryContext;
import org.polarsys.capella.core.business.queries.IBusinessQuery;
import org.polarsys.capella.core.business.queries.QueryConstants;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.epbs.EpbsPackage;

public class ConfigurationItem_ImplementedInterfaces implements IBusinessQuery {

	@Override
	public EClass getEClass() {
		return EpbsPackage.Literals.CONFIGURATION_ITEM;
	}

	@Override
	public List<EReference> getEStructuralFeatures() {
	    List<EReference> list = new ArrayList<EReference>(2);
	    list.add(CsPackage.Literals.COMPONENT__IMPLEMENTED_INTERFACES);
	    list.add(CsPackage.Literals.COMPONENT__OWNED_INTERFACE_IMPLEMENTATIONS);
	    return list;
	}

	@Override
	public List<CapellaElement> getAvailableElements(CapellaElement element_p) {
	  QueryContext context = new QueryContext();
		context.putValue(QueryConstants.ECLASS_PARAMETER, getEClass());
		return QueryInterpretor.executeQuery(QueryConstants.GET_AVAILABLE__CONFIGURATION_ITEM__IMPLEMENTED_INTERFACES___LIB, element_p, context);
	}

	@Override
	public List<CapellaElement> getCurrentElements(CapellaElement element_p, boolean onlyGenerated_p) {
	  QueryContext context = new QueryContext();
		context.putValue(QueryConstants.ECLASS_PARAMETER, getEClass());
		return QueryInterpretor.executeQuery(QueryConstants.GET_CURRENT__CONFIGURATION_ITEM__IMPLEMENTED_INTERFACES, element_p, context);
	}
}
