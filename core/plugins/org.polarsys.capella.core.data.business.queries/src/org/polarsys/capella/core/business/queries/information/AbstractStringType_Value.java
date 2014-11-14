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
import org.polarsys.capella.core.data.information.datatype.StringType;
import org.polarsys.capella.core.data.information.datavalue.DatavaluePackage;

/**
 * This is an abstract class for String Types values.
 */
public abstract class AbstractStringType_Value extends CapellaElement_CurrentAndHigherLevelsQuery {

  /**
   * @see org.polarsys.capella.core.business.abstractqueries.CapellaElement_CurrentAndHigherLevelsQuery#getDataFromLevel(org.polarsys.capella.core.data.cs.BlockArchitecture,
   *      org.polarsys.capella.core.data.capellacore.CapellaElement)
   */
  @Override
  public List<CapellaElement> getDataFromLevel(DataPkg dataPkg_p, CapellaElement capellaElement_p) {
    List<CapellaElement> returnValue = new ArrayList<CapellaElement>();
    if (capellaElement_p instanceof StringType) {
      StringType stringType = (StringType) capellaElement_p;
      List<EClass> stringValuesAndExpressions = new ArrayList<EClass>();
      stringValuesAndExpressions.add(DatavaluePackage.Literals.ABSTRACT_STRING_VALUE);
      stringValuesAndExpressions.add(DatavaluePackage.Literals.ABSTRACT_EXPRESSION_VALUE);
      // Gets the non typed expressions and string values
      returnValue.addAll(CapellaElementsHelperForBusinessQueries.getDataValuesInstancesOf(dataPkg_p, stringValuesAndExpressions, true, false));
      returnValue.addAll(CapellaElementsHelperForBusinessQueries.getValuesTypedBy(dataPkg_p, stringType, false, true, stringValuesAndExpressions, null));
      returnValue.addAll(CapellaElementsHelperForBusinessQueries.getPropertiesTypedBy(dataPkg_p, stringType, false));
    }
    return returnValue;
  }
}
