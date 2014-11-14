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
import org.polarsys.capella.core.data.capellacore.GeneralizableElement;
import org.polarsys.capella.core.data.helpers.capellacore.services.GeneralizableElementExt;
import org.polarsys.capella.core.data.information.DataPkg;
import org.polarsys.capella.core.data.information.datatype.Enumeration;
import org.polarsys.capella.core.data.information.datavalue.DatavaluePackage;

/**
 * This is the abstract class for enumeration queries which all returns the same data for the available elements.
 */
public abstract class Enumeration_AbstractValue extends CapellaElement_CurrentAndHigherLevelsQuery {
  /**
   * @see org.polarsys.capella.core.business.abstractqueries.CapellaElement_CurrentAndHigherLevelsQuery#getUnlevelizedData(org.polarsys.capella.core.data.capellacore.CapellaElement)
   */
  @Override
  public List<CapellaElement> getUnlevelizedData(CapellaElement capellaElement_p) {
    // Gets the owned literals of the current boolean type root ancestors
    List<CapellaElement> returnValue = new ArrayList<CapellaElement>();
    if (capellaElement_p instanceof Enumeration) {
      List<GeneralizableElement> rootSupertypes = GeneralizableElementExt.getRootSupertypes((Enumeration) capellaElement_p);
      rootSupertypes.add((GeneralizableElement) capellaElement_p);
      for (CapellaElement melElem : rootSupertypes) {
        if (melElem instanceof Enumeration) {
          Enumeration rootBooleanType = (Enumeration) melElem;
          returnValue.addAll(rootBooleanType.getOwnedLiterals());
        }
      }
    }
    return returnValue;
  }

  /**
   * @see org.polarsys.capella.core.business.abstractqueries.CapellaElement_CurrentAndHigherLevelsQuery#getDataFromLevel(org.polarsys.capella.core.data.cs.BlockArchitecture,
   *      org.polarsys.capella.core.data.capellacore.CapellaElement)
   */
  @Override
  public List<CapellaElement> getDataFromLevel(DataPkg dataPkg_p, CapellaElement capellaElement_p) {
    if (capellaElement_p instanceof Enumeration) {
      List<CapellaElement> returnValue = new ArrayList<CapellaElement>();
      List<EClass> enumRefAndExpressionsEClasses = new ArrayList<EClass>();
      enumRefAndExpressionsEClasses.add(DatavaluePackage.Literals.ENUMERATION_REFERENCE);
      enumRefAndExpressionsEClasses.add(DatavaluePackage.Literals.ABSTRACT_EXPRESSION_VALUE);
      // non typed Enumeration references and expressions
      returnValue.addAll(CapellaElementsHelperForBusinessQueries.getDataValuesInstancesOf(dataPkg_p, enumRefAndExpressionsEClasses, true, false));
      // Enumeration references and Expressions typed by one of the current enumeration type ancestor
      returnValue.addAll(CapellaElementsHelperForBusinessQueries.getValuesTypedBy(dataPkg_p, (Enumeration) capellaElement_p, false, true,
          enumRefAndExpressionsEClasses, null));
      // Properties typed by one of the enumeration type ancestors
      returnValue.addAll(CapellaElementsHelperForBusinessQueries.getPropertiesTypedBy(dataPkg_p, (Enumeration) capellaElement_p, false));
      return returnValue;
    }
    return Collections.emptyList();
  }
}
