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
import org.polarsys.capella.core.data.information.InformationPackage;
import org.polarsys.capella.core.data.information.datavalue.DatavaluePackage;

/**
 * This is the query for Complex Value Reference referenced properties.
 */
public class ComplexValueRefReferencedProperty extends AbstractReference_ReferencedProperty implements IBusinessQuery {

	@Override
	public EClass getEClass() {
    return DatavaluePackage.Literals.COMPLEX_VALUE_REFERENCE;
  }

  @Override
	public List<EReference> getEStructuralFeatures() {
    return Collections.singletonList(DatavaluePackage.Literals.COMPLEX_VALUE_REFERENCE__REFERENCED_PROPERTY);
  }

  @Override
  public List<EObject> getAvailableElements(EObject element) {
    List<Object> parameters = new ArrayList<Object>();
    parameters.add(element);
    List<EClass> availableEClassForPropertiesTypes = new ArrayList<EClass>();
    availableEClassForPropertiesTypes.add(InformationPackage.Literals.COLLECTION);
    availableEClassForPropertiesTypes.add(InformationPackage.Literals.CLASS);
    parameters.add(availableEClassForPropertiesTypes);
		QueryContext context = new QueryContext();
		context.putValue(QueryConstants.ECLASS_PARAMETER, getEClass());
		return QueryInterpretor.executeQuery(QueryConstants.GET_AVAILABLE__GENERIC__REFERENCED_PROPERTY___LIB, parameters, context);
  }

  @Override
  public List<EObject> getCurrentElements(EObject element, boolean onlyGenerated) {
    QueryContext context = new QueryContext();
		context.putValue(QueryConstants.ECLASS_PARAMETER, getEClass());
		return QueryInterpretor.executeQuery(QueryConstants.GET_CURRENT__COMPLEX_VALUE_REF_REFERENCED_PROPERTY, element, context);
  }
}
