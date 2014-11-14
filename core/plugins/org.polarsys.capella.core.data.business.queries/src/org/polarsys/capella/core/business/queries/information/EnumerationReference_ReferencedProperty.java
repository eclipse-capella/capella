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
import org.polarsys.capella.core.business.abstractqueries.ExtendedBusinessQueryForLib;
import org.polarsys.capella.core.business.abstractqueries.RefactorDebugger;
import org.polarsys.capella.core.business.abstractqueries.RefactoredBusinessQuery;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.ctx.SystemAnalysis;
import org.polarsys.capella.core.data.epbs.EPBSArchitecture;
import org.polarsys.capella.core.data.information.datatype.DatatypePackage;
import org.polarsys.capella.core.data.information.datavalue.DatavaluePackage;
import org.polarsys.capella.core.data.information.datavalue.EnumerationReference;
import org.polarsys.capella.core.data.la.LogicalArchitecture;
import org.polarsys.capella.core.data.oa.OperationalAnalysis;
import org.polarsys.capella.core.data.pa.PhysicalArchitecture;
import org.polarsys.capella.core.model.helpers.DataPkgExt;
import org.polarsys.capella.core.model.helpers.SystemEngineeringExt;

/**
 * This is the query for enumeration references referenced properties.
 */
public class EnumerationReference_ReferencedProperty extends AbstractReference_ReferencedProperty implements ExtendedBusinessQueryForLib,
    RefactoredBusinessQuery {

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
    if (element_p instanceof EnumerationReference) {
      EnumerationReference reference = (EnumerationReference) element_p;
      if (reference.getReferencedProperty() != null) {
        currentElements.add(reference.getReferencedProperty());
      }
    }
    return currentElements;
  }

  public EClass getEClass() {
    return DatavaluePackage.Literals.ENUMERATION_REFERENCE;
  }

  public List<EReference> getEStructuralFeatures() {
    return Collections.singletonList(DatavaluePackage.Literals.ENUMERATION_REFERENCE__REFERENCED_PROPERTY);
  }

  @Override
  protected List<EClass> getAvailableEClassForPropertiesTypes() {
    return Collections.singletonList(DatatypePackage.Literals.ENUMERATION);
  }

  @Override
  public List<CapellaElement> getAvailableElements(CapellaElement element_p) {
    List<Object> parameters = new ArrayList<Object>();
    parameters.add(element_p);
    parameters.add(getAvailableEClassForPropertiesTypes());
    return RefactorDebugger.callAndTestQuery(
        "GetAvailable_Generic_ReferencedProperty__Lib", parameters, getOldAvailableElements(element_p), getEClass(), getClass());//$NON-NLS-1$
  }

  @Override
  public List<CapellaElement> getCurrentElements(CapellaElement element_p, boolean onlyGenerated_p) {
    return RefactorDebugger.callAndTestQuery(
        "GetCurrent_EnumerationReference_ReferencedProperty", element_p, getOldCurrentElements(element_p, false), getEClass(), getClass());//$NON-NLS-1$
  }
}
