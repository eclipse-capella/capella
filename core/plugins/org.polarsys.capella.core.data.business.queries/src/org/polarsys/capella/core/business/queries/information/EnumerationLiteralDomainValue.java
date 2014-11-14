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
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.polarsys.capella.core.business.abstractqueries.CapellaElement_CurrentAndHigherLevelsQuery;
import org.polarsys.capella.core.business.abstractqueries.RefactorDebugger;
import org.polarsys.capella.core.business.abstractqueries.RefactoredBusinessQuery;
import org.polarsys.capella.core.business.abstractqueries.helpers.CapellaElementsHelperForBusinessQueries;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.ctx.SystemAnalysis;
import org.polarsys.capella.core.data.epbs.EPBSArchitecture;
import org.polarsys.capella.core.data.information.DataPkg;
import org.polarsys.capella.core.data.information.datatype.DataType;
import org.polarsys.capella.core.data.information.datatype.Enumeration;
import org.polarsys.capella.core.data.information.datavalue.DataValue;
import org.polarsys.capella.core.data.information.datavalue.DatavaluePackage;
import org.polarsys.capella.core.data.information.datavalue.EnumerationLiteral;
import org.polarsys.capella.core.data.la.LogicalArchitecture;
import org.polarsys.capella.core.data.oa.OperationalAnalysis;
import org.polarsys.capella.core.data.pa.PhysicalArchitecture;
import org.polarsys.capella.core.model.helpers.DataPkgExt;
import org.polarsys.capella.core.model.helpers.SystemEngineeringExt;

/**
 * This is the query for the enumeration literal integer value
 */
public class EnumerationLiteralDomainValue extends CapellaElement_CurrentAndHigherLevelsQuery implements RefactoredBusinessQuery {

  public List<CapellaElement> getOldAvailableElements(CapellaElement element_p) {
    List<CapellaElement> returnValue = new ArrayList<CapellaElement>();
    BlockArchitecture currentBlockArchitecture = DataPkgExt.getRootBlockArchitecture(element_p);
    SystemEngineering systemEngineering = SystemEngineeringExt.getSystemEngineering(element_p);
    OperationalAnalysis operationalAnalysis = SystemEngineeringExt.getOwnedOperationalAnalysis(systemEngineering);
    returnValue.addAll(getDataFromLevel(operationalAnalysis, element_p));
    if (!(currentBlockArchitecture instanceof OperationalAnalysis)) {
      SystemAnalysis systemAnalysis = SystemEngineeringExt.getOwnedSystemAnalysis(systemEngineering);
      returnValue.addAll(getDataFromLevel(systemAnalysis, element_p));
      if (!(currentBlockArchitecture instanceof SystemAnalysis)) {
        LogicalArchitecture logicalArchitecture = SystemEngineeringExt.getOwnedLogicalArchitecture(systemEngineering);
        returnValue.addAll(getDataFromLevel(logicalArchitecture, element_p));
        if (!(currentBlockArchitecture instanceof LogicalArchitecture)) {
          PhysicalArchitecture physicalArchitecture = SystemEngineeringExt.getOwnedPhysicalArchitecture(systemEngineering);
          returnValue.addAll(getDataFromLevel(physicalArchitecture, element_p));
          if (!(currentBlockArchitecture instanceof PhysicalArchitecture)) {
            EPBSArchitecture epbsArchitecture = SystemEngineeringExt.getEPBSArchitecture((systemEngineering));
            returnValue.addAll(getDataFromLevel(epbsArchitecture, element_p));
          }
        }
      }
    }
    returnValue.addAll(getUnlevelizedData(element_p));
    returnValue.addAll(getDataFromComponentHierarchy(element_p));
    returnValue.addAll(getDataFromRealizedComponentsHierarchy(element_p));
    returnValue.addAll(getTypesFromComponentHierarchy(element_p));
    returnValue = filterUnNamedElements(returnValue);
    return returnValue;
  }

  @Override
  public List<CapellaElement> getDataFromLevel(DataPkg dataPkg_p, CapellaElement capellaElement_p) {
    List<CapellaElement> returnValue = new ArrayList<CapellaElement>(1);
    if (null != dataPkg_p) {
      DataType domainType = null;
      EObject owner = capellaElement_p.eContainer();
      if (owner instanceof Enumeration) {
        domainType = ((Enumeration) owner).getDomainType();
      }

      // The values typed by one of the current domain type ancestors or the domain type itself
      returnValue
          .addAll(CapellaElementsHelperForBusinessQueries.getValuesTypedBy(dataPkg_p, domainType, true, true, DatavaluePackage.Literals.DATA_VALUE, null));

      // The properties typed by by one of the current domain type ancestors or the domain type itself
      returnValue.addAll(CapellaElementsHelperForBusinessQueries.getPropertiesTypedBy(dataPkg_p, domainType, true));
      // In case the domain type is undefined all numeric values are supported
      if (null == domainType) {
        returnValue.addAll(CapellaElementsHelperForBusinessQueries.getDataValuesInstancesOf(dataPkg_p, DatavaluePackage.Literals.NUMERIC_VALUE, true, true));
      }
    }

    return returnValue;
  }

  public List<CapellaElement> getOldCurrentElements(CapellaElement element_p, boolean onlyGenerated_p) {
    List<CapellaElement> currentElements = new ArrayList<CapellaElement>();
    if (!systemEngineeringExists(element_p)) {
      return currentElements;
    }
    if (element_p instanceof EnumerationLiteral) {
      EnumerationLiteral enumLiteral = (EnumerationLiteral) element_p;
      DataValue integerValue = enumLiteral.getDomainValue();
      if (integerValue != null) {
        currentElements.add(integerValue);
      }
    }
    return currentElements;
  }

  public EClass getEClass() {
    return DatavaluePackage.Literals.ENUMERATION_LITERAL;
  }

  public List<EReference> getEStructuralFeatures() {
    return Collections.singletonList(DatavaluePackage.Literals.ENUMERATION_LITERAL__DOMAIN_VALUE);
  }

  @Override
  public List<CapellaElement> getAvailableElements(CapellaElement element_p) {
    return RefactorDebugger.callAndTestQuery(
        "GetAvailable_EnumerationLiteralDomainValue__Lib", element_p, getOldAvailableElements(element_p), getEClass(), getClass());//$NON-NLS-1$
  }

  @Override
  public List<CapellaElement> getCurrentElements(CapellaElement element_p, boolean onlyGenerated_p) {
    return RefactorDebugger.callAndTestQuery(
        "GetCurrent_EnumerationLiteralDomainValue", element_p, getOldCurrentElements(element_p, false), getEClass(), getClass());//$NON-NLS-1$
  }
}