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
import org.polarsys.capella.core.business.abstractqueries.CapellaElement_CurrentAndHigherLevelsQuery;
import org.polarsys.capella.core.business.abstractqueries.helpers.CapellaElementsHelperForBusinessQueries;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.information.DataPkg;
import org.polarsys.capella.core.data.information.datatype.NumericType;
import org.polarsys.capella.core.data.information.datavalue.DatavaluePackage;

/**
 * This class is an abstract class containing the business behavior for the queries returning the min, max, default and null value for numeric types.
 */
public abstract class NumericType_AbstractValue extends CapellaElement_CurrentAndHigherLevelsQuery {
  /**
   * @see org.polarsys.capella.core.business.abstractqueries.CapellaElement_CurrentAndHigherLevelsQuery#getDataFromLevel(org.polarsys.capella.core.data.cs.BlockArchitecture,
   *      org.polarsys.capella.core.data.capellacore.CapellaElement)
   */
  @Override
  public List<CapellaElement> getDataFromLevel(DataPkg dataPkg_p, CapellaElement dataType_p) {
    if (dataType_p instanceof NumericType) {
      List<CapellaElement> returnValue = new ArrayList<CapellaElement>();
      List<EClass> numericValueAndExpressionEClasses = new ArrayList<EClass>();
      numericValueAndExpressionEClasses.add(DatavaluePackage.Literals.NUMERIC_VALUE);
      numericValueAndExpressionEClasses.add(DatavaluePackage.Literals.ABSTRACT_EXPRESSION_VALUE);
      // Gets only numeric values and expressions
      returnValue.addAll(CapellaElementsHelperForBusinessQueries.getValuesTypedBy(dataPkg_p, (NumericType) dataType_p, true, true,
          numericValueAndExpressionEClasses, null));
      // Also gets the properties with the correct type
      returnValue.addAll(CapellaElementsHelperForBusinessQueries.getPropertiesTypedBy(dataPkg_p, (NumericType) dataType_p, true));
      return returnValue;
    }
    return Collections.emptyList();
  }
}
