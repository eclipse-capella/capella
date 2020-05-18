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
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;
import org.polarsys.capella.common.queries.interpretor.QueryInterpretor;
import org.polarsys.capella.common.queries.queryContext.QueryContext;
import org.polarsys.capella.core.business.queries.IBusinessQuery;
import org.polarsys.capella.core.business.queries.QueryConstants;
import org.polarsys.capella.core.data.information.datatype.DatatypePackage;
import org.polarsys.capella.core.data.information.datavalue.DatavaluePackage;

/**
 * This is the query for string references types.
 */
public class StringReference_AbstractType extends AbstractValue_Type implements IBusinessQuery {

  /**
	 * @see org.polarsys.capella.core.business.queries.information.StringValue_Type#getEStructuralFeatures()
	 */
	@Override
	public List<EReference> getEStructuralFeatures() {
		return Collections.singletonList(ModellingcorePackage.Literals.ABSTRACT_TYPED_ELEMENT__ABSTRACT_TYPE);
	}

	@Override
  public EClass getEClass() {
    return DatavaluePackage.Literals.STRING_REFERENCE;
  }
  
  @Override
  public List<EObject> getAvailableElements(EObject element) {
    List<Object> parameters = new ArrayList<Object>();
    parameters.add(element);
    parameters.add(Collections.singletonList(DatatypePackage.Literals.STRING_TYPE));    
		QueryContext context = new QueryContext();
		context.putValue(QueryConstants.ECLASS_PARAMETER, getEClass());
		return QueryInterpretor.executeQuery(QueryConstants.GET_AVAILABLE__DATA_VALUE__ABSTRACT_TYPE___LIB, parameters, context);
  }

  @Override
  public List<EObject> getCurrentElements(EObject element, boolean onlyGenerated) {
    QueryContext context = new QueryContext();
		context.putValue(QueryConstants.ECLASS_PARAMETER, getEClass());
		return QueryInterpretor.executeQuery(QueryConstants.GET_CURRENT__STRING_REFERENCE__ABSTRACT_TYPE, element, context);
  }
}
