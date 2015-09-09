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
package org.polarsys.capella.core.business.queries.capellacommon;

import java.util.Collections;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EReference;
import org.polarsys.capella.common.queries.interpretor.QueryInterpretor;
import org.polarsys.capella.common.queries.queryContext.QueryContext;
import org.polarsys.capella.core.business.queries.IBusinessQuery;
import org.polarsys.capella.core.business.queries.QueryConstants;
import org.polarsys.capella.core.data.capellacommon.CapellacommonPackage;
import org.polarsys.capella.core.data.capellacore.CapellaElement;

public class TimeEvent_Expression implements IBusinessQuery {

  @Override
  public EClass getEClass() {
    return CapellacommonPackage.Literals.TIME_EVENT;
  }

  @Override
  public List<EReference> getEStructuralFeatures() {
    return Collections.singletonList(CapellacommonPackage.Literals.STATE_EVENT__EXPRESSION);
  }

  @Override
  public List<CapellaElement> getAvailableElements(CapellaElement element) {
    QueryContext context = new QueryContext();
    context.putValue(QueryConstants.ECLASS_PARAMETER, getEClass());
    return QueryInterpretor.executeQuery(QueryConstants.GET_AVAILABLE__TIME_EVENT__EXPRESSION, element, context);
  }

  @Override
  public List<CapellaElement> getCurrentElements(CapellaElement element, boolean onlyGenerated) {
    QueryContext context = new QueryContext();
    context.putValue(QueryConstants.ECLASS_PARAMETER, getEClass());
    return QueryInterpretor.executeQuery(QueryConstants.GET_CURRENT__TIME_EVENT__EXPRESSION, element, context);
  }

}
