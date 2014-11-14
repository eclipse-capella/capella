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
import org.eclipse.emf.ecore.EReference;
import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;
import org.polarsys.capella.core.business.abstractqueries.CapellaElement_CurrentAndHigherLevelsQuery;
import org.polarsys.capella.core.business.abstractqueries.ExtendedBusinessQueryForLib;
import org.polarsys.capella.core.business.abstractqueries.RefactorDebugger;
import org.polarsys.capella.core.business.abstractqueries.RefactoredBusinessQuery;
import org.polarsys.capella.core.business.abstractqueries.helpers.CapellaElementsHelperForBusinessQueries;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellacore.Type;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.ctx.SystemAnalysis;
import org.polarsys.capella.core.data.epbs.EPBSArchitecture;
import org.polarsys.capella.core.data.information.Collection;
import org.polarsys.capella.core.data.information.DataPkg;
import org.polarsys.capella.core.data.information.InformationPackage;
import org.polarsys.capella.core.data.information.datatype.DatatypePackage;
import org.polarsys.capella.core.data.la.LogicalArchitecture;
import org.polarsys.capella.core.data.oa.OperationalAnalysis;
import org.polarsys.capella.core.data.pa.PhysicalArchitecture;
import org.polarsys.capella.core.model.helpers.DataPkgExt;
import org.polarsys.capella.core.model.helpers.SystemEngineeringExt;

/**
 * This is the query for collections types.
 */
public class Collection_Type extends CapellaElement_CurrentAndHigherLevelsQuery implements ExtendedBusinessQueryForLib, RefactoredBusinessQuery {
  /**
	 * @see org.polarsys.capella.core.business.queries.IBusinessQuery#getAvailableElements(org.polarsys.capella.core.data.capellacore.CapellaElement)
	 */
	public List<CapellaElement> getOldAvailableElements(CapellaElement element_p) {
		List<CapellaElement> returnValue = new ArrayList<CapellaElement>();
		BlockArchitecture currentBlockArchitecture = DataPkgExt
				.getRootBlockArchitecture(element_p);
		SystemEngineering systemEngineering = SystemEngineeringExt
				.getSystemEngineering(element_p);
		OperationalAnalysis operationalAnalysis = SystemEngineeringExt
				.getOwnedOperationalAnalysis(systemEngineering);
		returnValue.addAll(getDataFromLevel(operationalAnalysis, element_p));
		if (!(currentBlockArchitecture instanceof OperationalAnalysis)) {
			SystemAnalysis systemAnalysis = SystemEngineeringExt
					.getOwnedSystemAnalysis(systemEngineering);
			returnValue.addAll(getDataFromLevel(systemAnalysis, element_p));
			if (!(currentBlockArchitecture instanceof SystemAnalysis)) {
				LogicalArchitecture logicalArchitecture = SystemEngineeringExt
						.getOwnedLogicalArchitecture(systemEngineering);
				returnValue.addAll(getDataFromLevel(logicalArchitecture,
						element_p));
				if (!(currentBlockArchitecture instanceof LogicalArchitecture)) {
					PhysicalArchitecture physicalArchitecture = SystemEngineeringExt
							.getOwnedPhysicalArchitecture(systemEngineering);
					returnValue.addAll(getDataFromLevel(physicalArchitecture,
							element_p));
					if (!(currentBlockArchitecture instanceof PhysicalArchitecture)) {
						EPBSArchitecture epbsArchitecture = SystemEngineeringExt
								.getEPBSArchitecture((systemEngineering));
						returnValue.addAll(getDataFromLevel(epbsArchitecture,
								element_p));
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

/**
   * Gets the type of current Collection
   * @see org.polarsys.capella.core.business.queries.capellacore.common.ui.business.queries.IBusinessQuery#getCurrentElements(org.polarsys.capella.core.common.model.CapellaElement,
   *      boolean)
   */
  public List<CapellaElement> getOldCurrentElements(CapellaElement element_p, boolean onlyGenerated_p) {
    List<CapellaElement> currentElements = new ArrayList<CapellaElement>();
    if (!systemEngineeringExists(element_p)) {
      return currentElements;
    }
    if (element_p instanceof Collection) {
      Collection collection = (Collection) element_p;
      Type type = collection.getType();
      if (null != type)
        currentElements.add(type);
    }
    return currentElements;
  }

  /**
   * @see org.polarsys.capella.core.business.queries.capellacore.IBusinessQuery#getEClass()
   */
  public EClass getEClass() {
    return InformationPackage.Literals.COLLECTION;
  }

  /**
   * @see org.polarsys.capella.core.business.queries.capellacore.IBusinessQuery#getEStructuralFeatures()
   */
  public List<EReference> getEStructuralFeatures() {
    List<EReference> list = new ArrayList<EReference>(1);
    list.add(InformationPackage.Literals.COLLECTION__TYPE);
    list.add(ModellingcorePackage.Literals.ABSTRACT_TYPED_ELEMENT__ABSTRACT_TYPE);
    return list;
  }

  /**
   * @see org.polarsys.capella.core.business.abstractqueries.CapellaElement_CurrentAndHigherLevelsQuery#getDataFromLevel(org.polarsys.capella.core.data.cs.BlockArchitecture,
   *      org.polarsys.capella.core.data.capellacore.CapellaElement)
   */
  @Override
  public List<CapellaElement> getDataFromLevel(DataPkg dataPkg_p, CapellaElement capellaElement_p) {
    // All data types
    List<CapellaElement> returnValue =
        CapellaElementsHelperForBusinessQueries.getCapellaElementsInstancesOf(dataPkg_p, DatatypePackage.Literals.DATA_TYPE, null);
    // all collections avoiding the current one
    List<CapellaElement> collections = CapellaElementsHelperForBusinessQueries.getCapellaElementsInstancesOf(dataPkg_p, InformationPackage.Literals.COLLECTION, capellaElement_p);
    returnValue.addAll(collections);
    // all classes and unions (Union extends Class, so using only the CLASS literal here will also bring up the unions)
    List<CapellaElement> classes = CapellaElementsHelperForBusinessQueries.getCapellaElementsInstancesOf(dataPkg_p, InformationPackage.Literals.CLASS, null);
    returnValue.addAll(classes);
    return returnValue;
  }

@Override
public List<CapellaElement> getAvailableElements(CapellaElement element_p) {
  return RefactorDebugger.callAndTestQuery("GetAvailable_Collection_Type__Lib", element_p, getOldAvailableElements(element_p), getEClass(), getClass());//$NON-NLS-1$
}

@Override
public List<CapellaElement> getCurrentElements(CapellaElement element_p, boolean onlyGenerated_p) {
  return RefactorDebugger.callAndTestQuery("GetCurrent_Collection_Type", element_p, getOldCurrentElements(element_p, false), getEClass(), getClass());//$NON-NLS-1$
}
}
