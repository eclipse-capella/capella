/*******************************************************************************
 * Copyright (c) 2006, 2019 THALES GLOBAL SERVICES.
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 * 
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.business.queries.queries.information;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.data.modellingcore.AbstractType;
import org.polarsys.capella.common.queries.AbstractQuery;
import org.polarsys.capella.common.queries.queryContext.IQueryContext;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellacore.Classifier;
import org.polarsys.capella.core.data.capellacore.Feature;
import org.polarsys.capella.core.data.capellacore.ReuseLink;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.ctx.SystemAnalysis;
import org.polarsys.capella.core.data.epbs.EPBSArchitecture;
import org.polarsys.capella.core.data.information.DataPkg;
import org.polarsys.capella.core.data.information.Property;
import org.polarsys.capella.core.data.information.datatype.DataType;
import org.polarsys.capella.core.data.information.datatype.PhysicalQuantity;
import org.polarsys.capella.core.data.la.LogicalArchitecture;
import org.polarsys.capella.core.data.oa.OperationalAnalysis;
import org.polarsys.capella.core.data.pa.PhysicalArchitecture;
import org.polarsys.capella.core.data.sharedmodel.SharedPkg;
import org.polarsys.capella.core.model.helpers.DataPkgExt;
import org.polarsys.capella.core.model.helpers.DataValueExt;
import org.polarsys.capella.core.model.helpers.SystemEngineeringExt;
import org.polarsys.capella.core.model.helpers.query.CapellaQueries;
import org.polarsys.capella.core.model.utils.ListExt;

public class GetAvailable_PhysicalQuantity_Property extends AbstractQuery {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<Object> execute(Object input, IQueryContext context) {
		CapellaElement capellaElement = (CapellaElement) input;
		List<EObject> availableElements = getAvailableElements(capellaElement);
		return (List) availableElements;
	}

	/** 
	 * @see org.polarsys.capella.core.business.queries.capellacore.common.ui.business.queries.IBusinessQuery#getAvailableElements(EObject)
	 */
	public List<EObject> getAvailableElements(CapellaElement element) {
		List<EObject> availableElements = new ArrayList<EObject>();
		SystemEngineering systemEngineering = CapellaQueries.getInstance().getRootQueries().getSystemEngineering(element);
		boolean isElementFromSharedPkg = false;
		if (null == systemEngineering) {
			SharedPkg sharedPkg = SystemEngineeringExt.getSharedPkg(element);
			for (ReuseLink link : sharedPkg.getReuseLinks()) {
				if (SystemEngineeringExt.getSystemEngineering(link) != null) {
					systemEngineering = SystemEngineeringExt.getSystemEngineering(link);
					isElementFromSharedPkg = true;
					break;
				}
			}
			if (systemEngineering == null)
				return availableElements;
		}
		if (element instanceof PhysicalQuantity) {
			PhysicalQuantity currentBooleanType = (PhysicalQuantity) element;
			if (!isElementFromSharedPkg) {
				availableElements.addAll(getRule_MQRY_BooleanValue_Type_11(currentBooleanType));
			}
		}
		availableElements = ListExt.removeDuplicates(availableElements);
		return availableElements;
	}

	private List<CapellaElement> getRule_MQRY_BooleanValue_Type_11(PhysicalQuantity currentBooleanType) {
		List<CapellaElement> availableElements = new ArrayList<CapellaElement>();
		List<DataPkg> dataPkgList = DataValueExt.getDataPkgsFromParentHierarchy(currentBooleanType);
		for (DataPkg dataPkg : dataPkgList) {
			getAllPropertyFromDataPkg(availableElements, dataPkg, currentBooleanType);
		}
		availableElements.addAll(getRule_MQRY_BooleanValue_Type_11_1(currentBooleanType));
		return availableElements;
	}

	private void getAllPropertyFromDataPkg(List<CapellaElement> availableElements, DataPkg dataPkg, DataType type) {
		if (null != dataPkg) {
			for (EObject dataType : DataPkgExt.getAllClassifierFromDataPkg(dataPkg)) {
				if (dataType instanceof Classifier) {
					getAllPropertiesFromClassifier(availableElements, (Classifier) dataType, type);
				}
			}
		}
	}

	private List<CapellaElement> getRule_MQRY_BooleanValue_Type_11_1(PhysicalQuantity currentBooleanType) {
		List<CapellaElement> availableElements = new ArrayList<CapellaElement>();
		BlockArchitecture arch = DataPkgExt.getRootBlockArchitecture(currentBooleanType);
		SystemEngineering systemEngineering = CapellaQueries.getInstance().getRootQueries().getSystemEngineering(currentBooleanType);
		OperationalAnalysis oa = SystemEngineeringExt.getOwnedOperationalAnalysis(systemEngineering);
		if (null != oa) {
			availableElements.addAll(getElementsFromBlockArchitecture(oa, currentBooleanType));
		} else {
			SystemAnalysis ca = SystemEngineeringExt.getOwnedSystemAnalysis(systemEngineering);
			availableElements.addAll(getElementsFromBlockArchitecture(ca, currentBooleanType));
		}
		if (arch != null) {
			if (null != oa && (arch instanceof LogicalArchitecture) || (arch instanceof PhysicalArchitecture) || (arch instanceof EPBSArchitecture)) {
				SystemAnalysis ctxArch = SystemEngineeringExt.getOwnedSystemAnalysis(systemEngineering);
				availableElements.addAll(getElementsFromBlockArchitecture(ctxArch, currentBooleanType));
			}
			if ((arch instanceof PhysicalArchitecture) || (arch instanceof EPBSArchitecture)) {
				LogicalArchitecture logArch = SystemEngineeringExt.getOwnedLogicalArchitecture(systemEngineering);
				availableElements.addAll(getElementsFromBlockArchitecture(logArch, currentBooleanType));
			}
			if ((arch instanceof EPBSArchitecture)) {
				PhysicalArchitecture physArch = SystemEngineeringExt.getOwnedPhysicalArchitecture(systemEngineering);
				availableElements.addAll(getElementsFromBlockArchitecture(physArch, currentBooleanType));
			}
		}
		return availableElements;
	}

	private void getAllPropertiesFromClassifier(List<CapellaElement> availableElements, Classifier classifier, DataType type) {
		EList<Feature> ownedFeatures = classifier.getOwnedFeatures();
		for (Feature feature : ownedFeatures) {
			if (feature instanceof Property) {
				AbstractType abstractType = ((Property) feature).getAbstractType();
				if (null != abstractType)
					if (abstractType instanceof PhysicalQuantity) {
						availableElements.add(feature);
					}
			}
		}
	}

	private List<CapellaElement> getElementsFromBlockArchitecture(BlockArchitecture arch, DataType type) {
		List<CapellaElement> availableElements = new ArrayList<CapellaElement>(1);
		if (null != arch) {
			DataPkg dataPkg = DataPkgExt.getDataPkgOfBlockArchitecture(arch);
			getAllPropertyFromDataPkg(availableElements, dataPkg, type);
		}
		return availableElements;
	}

}