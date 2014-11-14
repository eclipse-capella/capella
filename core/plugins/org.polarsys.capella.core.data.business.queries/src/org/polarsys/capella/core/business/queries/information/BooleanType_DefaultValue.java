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
import org.polarsys.capella.core.business.abstractqueries.CapellaElement_CurrentAndHigherLevelsQuery;
import org.polarsys.capella.core.business.abstractqueries.ExtendedBusinessQueryForLib;
import org.polarsys.capella.core.business.abstractqueries.RefactorDebugger;
import org.polarsys.capella.core.business.abstractqueries.RefactoredBusinessQuery;
import org.polarsys.capella.core.business.abstractqueries.helpers.CapellaElementsHelperForBusinessQueries;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellacore.GeneralizableElement;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.ctx.SystemAnalysis;
import org.polarsys.capella.core.data.epbs.EPBSArchitecture;
import org.polarsys.capella.core.data.helpers.capellacore.services.GeneralizableElementExt;
import org.polarsys.capella.core.data.information.DataPkg;
import org.polarsys.capella.core.data.information.datatype.BooleanType;
import org.polarsys.capella.core.data.information.datatype.DatatypePackage;
import org.polarsys.capella.core.data.information.datavalue.DatavaluePackage;
import org.polarsys.capella.core.data.la.LogicalArchitecture;
import org.polarsys.capella.core.data.oa.OperationalAnalysis;
import org.polarsys.capella.core.data.pa.PhysicalArchitecture;
import org.polarsys.capella.core.model.helpers.DataPkgExt;
import org.polarsys.capella.core.model.helpers.SystemEngineeringExt;

/**
 * This query allows to get the available values for a boolean type default value using this rule:
 * <ul>
 * <li>Returns the elements typed by all the root super types of the current boolean type</li>
 * </ul>
 */
public class BooleanType_DefaultValue extends CapellaElement_CurrentAndHigherLevelsQuery implements ExtendedBusinessQueryForLib, RefactoredBusinessQuery {

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

  public List<CapellaElement> getOldCurrentElements(CapellaElement element_p, boolean onlyGenerated_p) {
    List<CapellaElement> currentElements = new ArrayList<CapellaElement>();
    if (!systemEngineeringExists(element_p)) {
      return currentElements;
    }
    if (element_p instanceof BooleanType) {
      BooleanType currentBooleanType = (BooleanType) element_p;
      if (currentBooleanType.getOwnedDefaultValue() != null) {
        currentElements.add(currentBooleanType.getOwnedDefaultValue());
      }
    }
    return currentElements;
  }

  public EClass getEClass() {
    return DatatypePackage.Literals.BOOLEAN_TYPE;
  }

  public List<EReference> getEStructuralFeatures() {
    return Collections.singletonList(DatatypePackage.Literals.BOOLEAN_TYPE__OWNED_DEFAULT_VALUE);
  }

  @Override
  public List<CapellaElement> getUnlevelizedData(CapellaElement capellaElement_p) {
    // Gets the owned literals of the current boolean type root ancestors
    List<CapellaElement> returnValue = new ArrayList<CapellaElement>();
    if (capellaElement_p instanceof BooleanType) {
      List<GeneralizableElement> rootSupertypes = GeneralizableElementExt.getRootSupertypes((BooleanType) capellaElement_p);
      rootSupertypes.add((GeneralizableElement) capellaElement_p);
      for (CapellaElement melElem : rootSupertypes) {
        if (melElem instanceof BooleanType) {
          BooleanType rootBooleanType = (BooleanType) melElem;
          returnValue.addAll(rootBooleanType.getOwnedLiterals());
        }
      }
    }
    return returnValue;
  }

  @Override
  public List<CapellaElement> getDataFromLevel(DataPkg dataPkg_p, CapellaElement dataType_p) {
    if (dataType_p instanceof BooleanType) {
      List<EClass> searchedUntypedEclasses = new ArrayList<EClass>();
      BooleanType booleanType = (BooleanType) dataType_p;
      searchedUntypedEclasses.add(DatavaluePackage.Literals.BOOLEAN_REFERENCE);
      searchedUntypedEclasses.add(DatavaluePackage.Literals.ABSTRACT_EXPRESSION_VALUE);
      // The non typed Boolean references and Expressions
      List<CapellaElement> returnValue = CapellaElementsHelperForBusinessQueries.getDataValuesInstancesOf(dataPkg_p, searchedUntypedEclasses, true, false);
      // The boolean references typed by one of the current boolean type ancestors or the boolean type itself
      returnValue.addAll(CapellaElementsHelperForBusinessQueries.getValuesTypedBy(dataPkg_p, booleanType, false, true,
          DatavaluePackage.Literals.BOOLEAN_REFERENCE, null));
      // The properties typed by by one of the current boolean type ancestors or the boolean type itself
      returnValue.addAll(CapellaElementsHelperForBusinessQueries.getPropertiesTypedBy(dataPkg_p, booleanType, false));
      return returnValue;
    }
    return Collections.emptyList();
  }

  @Override
  public List<CapellaElement> getAvailableElements(CapellaElement element_p) {
    return RefactorDebugger.callAndTestQuery(
        "GetAvailable_BooleanType_DefaultValue__Lib", element_p, getOldAvailableElements(element_p), getEClass(), getClass());//$NON-NLS-1$
  }

  @Override
  public List<CapellaElement> getCurrentElements(CapellaElement element_p, boolean onlyGenerated_p) {
    return RefactorDebugger
        .callAndTestQuery("GetCurrent_BooleanType_DefaultValue", element_p, getOldCurrentElements(element_p, false), getEClass(), getClass());//$NON-NLS-1$
  }
}