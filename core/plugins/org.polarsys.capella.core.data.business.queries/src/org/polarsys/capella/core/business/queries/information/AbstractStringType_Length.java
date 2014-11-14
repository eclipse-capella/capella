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
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.polarsys.capella.core.business.abstractqueries.CapellaElement_CurrentAndHigherLevelsQuery;
import org.polarsys.capella.core.business.abstractqueries.helpers.CapellaElementsHelperForBusinessQueries;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.information.DataPkg;
import org.polarsys.capella.core.data.information.datavalue.DatavaluePackage;

/**
 * This is an abstract query for String Type border values
 */
public abstract class AbstractStringType_Length extends CapellaElement_CurrentAndHigherLevelsQuery {

  /**
   * @see org.polarsys.capella.core.business.abstractqueries.CapellaElement_CurrentAndHigherLevelsQuery#getDataFromLevel(org.polarsys.capella.core.data.cs.BlockArchitecture,
   *      org.polarsys.capella.core.data.capellacore.CapellaElement)
   */
  @Override
  public List<CapellaElement> getDataFromLevel(DataPkg dataPkg_p, CapellaElement capellaElement_p) {
    List<CapellaElement> returnValue = new ArrayList<CapellaElement>();
    List<EClass> numericValuesAndExpressions = new ArrayList<EClass>();
    numericValuesAndExpressions.add(DatavaluePackage.Literals.NUMERIC_VALUE);
    numericValuesAndExpressions.add(DatavaluePackage.Literals.ABSTRACT_EXPRESSION_VALUE);
    //non typed numeric values and expressions
    returnValue.addAll(CapellaElementsHelperForBusinessQueries.getDataValuesInstancesOf(dataPkg_p, numericValuesAndExpressions, true, false));
    //Numeric Values and Expressions typed by a positive integer numeric type
    returnValue.addAll(CapellaElementsHelperForBusinessQueries.getPositiveIntergerNumValAndExpressionsInLevel(dataPkg_p));
    returnValue.addAll(CapellaElementsHelperForBusinessQueries.getApplicablePropertiesForStringBorderValuesInLevel(dataPkg_p, capellaElement_p));
    return returnValue;
  }
}
