/*******************************************************************************
 * Copyright (c) 2006, 2015 THALES GLOBAL SERVICES.
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
import org.polarsys.capella.core.data.information.InformationPackage;

/**
 * Query for Parameter default value.
 */
public class Parameter_DefaultValue extends AbstractMultiplicityElement_DefaultValue implements IBusinessQuery {

  /**
   * @see org.polarsys.capella.core.business.queries.capellacore.IBusinessQuery#getEStructuralFeatures()
   */
  @Override
  public List<EReference> getEStructuralFeatures() {
    return Collections.singletonList(InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_DEFAULT_VALUE);
  }

  /**
   * @see org.polarsys.capella.core.business.queries.capellacore.IBusinessQuery#getEClass()
   */
  @Override
  public EClass getEClass() {
    return InformationPackage.Literals.PARAMETER;
  }

  @Override
  public List<CapellaElement> getAvailableElements(CapellaElement element) {
    QueryContext context = new QueryContext();
    context.putValue(QueryConstants.ECLASS_PARAMETER, getEClass());
    return QueryInterpretor.executeQuery(QueryConstants.GET_AVAILABLE__PROPERTY__NULL_VALUE___LIB, element, context);
  }

  @Override
  public List<CapellaElement> getCurrentElements(CapellaElement element, boolean onlyGenerated) {
    QueryContext context = new QueryContext();
    context.putValue(QueryConstants.ECLASS_PARAMETER, getEClass());
    return QueryInterpretor.executeQuery(QueryConstants.GET_CURRENT__PARAMETER__DEFAULT_VALUE, element, context);
  }
}
