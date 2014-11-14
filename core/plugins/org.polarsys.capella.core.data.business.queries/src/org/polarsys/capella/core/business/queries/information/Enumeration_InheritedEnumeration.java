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
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.data.information.datatype.DatatypePackage;

/**
 * This is the query for enumerations inherited types
 */
public class Enumeration_InheritedEnumeration extends GeneralizableElement_AbstractInheritedType implements IBusinessQuery {
  /**
   * @see org.polarsys.capella.core.business.queries.capellacore.IBusinessQuery#getEClass()
   */
  @Override
	public EClass getEClass() {
    return DatatypePackage.Literals.ENUMERATION;
  }
  
  /**
   * @see org.polarsys.capella.core.business.queries.capellacore.IBusinessQuery#getEStructuralFeatures()
   */
  @Override
	public List<EReference> getEStructuralFeatures() {
    return Collections.singletonList(CapellacorePackage.Literals.GENERALIZABLE_ELEMENT__SUPER_GENERALIZATIONS);
  }

  @Override
public List<CapellaElement> getAvailableElements(CapellaElement element_p) {
  QueryContext context = new QueryContext();
	context.putValue(QueryConstants.ECLASS_PARAMETER, getEClass());
	return QueryInterpretor.executeQuery(QueryConstants.GET_AVAILABLE__ENUMERATION__INHERITED_ENUMERATION___LIB, element_p, context);
}

@Override
public List<CapellaElement> getCurrentElements(CapellaElement element_p, boolean onlyGenerated_p) {
  QueryContext context = new QueryContext();
	context.putValue(QueryConstants.ECLASS_PARAMETER, getEClass());
	return QueryInterpretor.executeQuery(QueryConstants.GET_CURRENT__ENUMERATION__INHERITED_ENUMERATION, element_p, context);
}
}
