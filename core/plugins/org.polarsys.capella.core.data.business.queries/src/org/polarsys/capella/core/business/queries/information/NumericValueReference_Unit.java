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
import org.polarsys.capella.core.business.abstractqueries.RefactorDebugger;
import org.polarsys.capella.core.business.abstractqueries.RefactoredBusinessQuery;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellacore.ReuseLink;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.data.information.Unit;
import org.polarsys.capella.core.data.information.datavalue.DatavaluePackage;
import org.polarsys.capella.core.data.information.datavalue.NumericValue;
import org.polarsys.capella.core.data.sharedmodel.SharedPkg;
import org.polarsys.capella.core.model.helpers.SystemEngineeringExt;
import org.polarsys.capella.core.model.helpers.query.CapellaQueries;
import org.polarsys.capella.core.model.utils.ListExt;

public class NumericValueReference_Unit extends NumericValue_Unit implements RefactoredBusinessQuery {

  public List<CapellaElement> getOldCurrentElements(CapellaElement element_p, boolean onlyGenerated_p) {
    List<CapellaElement> currentElements = new ArrayList<CapellaElement>();
    SystemEngineering systemEngineering = CapellaQueries.getInstance().getRootQueries().getSystemEngineering(element_p);
    if (null == systemEngineering) {
      SharedPkg sharedPkg = SystemEngineeringExt.getSharedPkg(element_p);
      for (ReuseLink link : sharedPkg.getReuseLinks()) {
        if (SystemEngineeringExt.getSystemEngineering(link) != null) {
          systemEngineering = SystemEngineeringExt.getSystemEngineering(link);
          break;
        }
      }
      if (systemEngineering == null) {
        return currentElements;
      }
    }
    if (element_p instanceof NumericValue) {
      NumericValue currentLiteralNumericValue = (NumericValue) element_p;
      Unit link = currentLiteralNumericValue.getUnit();
      if (null != link) {
        currentElements.add(link);
      }
    }
    return currentElements;
  }

  public List<CapellaElement> getOldAvailableElements(CapellaElement element_p) {
    List<CapellaElement> availableElements = new ArrayList<CapellaElement>();
    SystemEngineering systemEngineering = CapellaQueries.getInstance().getRootQueries().getSystemEngineering(element_p);
    boolean isElementFromSharedPkg = false;
    if (null == systemEngineering) {
      SharedPkg sharedPkg = SystemEngineeringExt.getSharedPkg(element_p);
      for (ReuseLink link : sharedPkg.getReuseLinks()) {
        if (SystemEngineeringExt.getSystemEngineering(link) != null) {
          systemEngineering = SystemEngineeringExt.getSystemEngineering(link);
          isElementFromSharedPkg = true;
          break;
        }
      }
      if (systemEngineering == null) {
        return availableElements;
      }
    }
    if (element_p instanceof NumericValue) {
      NumericValue currentNumericValue = (NumericValue) element_p;
      if (!isElementFromSharedPkg) {
        availableElements.addAll(getRule_MQRY_NumericValue_Unit_11(currentNumericValue));
        availableElements.addAll(getRule_MQRY_NumericValue_Unit_12(currentNumericValue));
      }
      availableElements.addAll(getRule_MQRY_NumericValue_Unit_13(currentNumericValue, systemEngineering));
    }
    availableElements = ListExt.removeDuplicates(availableElements);
    return availableElements;
  }

  public EClass getEClass() {
    return DatavaluePackage.Literals.NUMERIC_REFERENCE;
  }

  public List<EReference> getEStructuralFeatures() {
    return Collections.singletonList(DatavaluePackage.Literals.NUMERIC_VALUE__UNIT);
  }

  @Override
  public List<CapellaElement> getAvailableElements(CapellaElement element_p) {
    return RefactorDebugger.callAndTestQuery("GetAvailable_NumericValue_Unit__Lib", element_p, getOldAvailableElements(element_p), getEClass(), getClass());//$NON-NLS-1$
  }

  @Override
  public List<CapellaElement> getCurrentElements(CapellaElement element_p, boolean onlyGenerated_p) {
    return RefactorDebugger.callAndTestQuery(
        "GetCurrent_NumericValueReference_Unit", element_p, getOldCurrentElements(element_p, false), getEClass(), getClass());//$NON-NLS-1$
  }
}
