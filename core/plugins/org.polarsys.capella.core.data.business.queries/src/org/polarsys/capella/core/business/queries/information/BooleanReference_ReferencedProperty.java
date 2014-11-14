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

import java.util.ArrayList;
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
import org.polarsys.capella.core.data.information.datavalue.DatavaluePackage;

/**
 * This is the query for boolean references referenced properties.
 */
public class BooleanReference_ReferencedProperty extends AbstractReference_ReferencedProperty implements IBusinessQuery {

  @Override
	public EClass getEClass() {
    return DatavaluePackage.Literals.BOOLEAN_REFERENCE;
  }

  @Override
	public List<EReference> getEStructuralFeatures() {
    return Collections.singletonList(DatavaluePackage.Literals.BOOLEAN_REFERENCE__REFERENCED_PROPERTY);
  }

  @Override
  public List<CapellaElement> getAvailableElements(CapellaElement element_p) {
    List<Object> parameters = new ArrayList<Object>();
    parameters.add(element_p);
    parameters.add(Collections.singletonList(DatatypePackage.Literals.BOOLEAN_TYPE));
		QueryContext context = new QueryContext();
		context.putValue(QueryConstants.ECLASS_PARAMETER, getEClass());
		return QueryInterpretor.executeQuery(QueryConstants.GET_AVAILABLE__GENERIC__REFERENCED_PROPERTY___LIB, parameters, context);
  }

  @Override
  public List<CapellaElement> getCurrentElements(CapellaElement element_p, boolean onlyGenerated_p) {
    QueryContext context = new QueryContext();
		context.putValue(QueryConstants.ECLASS_PARAMETER, getEClass());
		return QueryInterpretor.executeQuery(QueryConstants.GET_CURRENT__BOOLEAN_REFERENCE__REFERENCED_PROPERTY, element_p, context);
  }
}
