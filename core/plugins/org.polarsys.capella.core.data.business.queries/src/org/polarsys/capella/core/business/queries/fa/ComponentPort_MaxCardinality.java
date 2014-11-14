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
package org.polarsys.capella.core.business.queries.fa;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EReference;

import org.polarsys.capella.core.business.queries.IBusinessQuery;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.ComponentArchitecture;
import org.polarsys.capella.core.data.fa.ComponentPort;
import org.polarsys.capella.core.data.fa.FaPackage;
import org.polarsys.capella.core.data.information.DataPkg;
import org.polarsys.capella.core.data.information.InformationPackage;
import org.polarsys.capella.core.data.information.datavalue.DataValue;
import org.polarsys.capella.core.data.information.datavalue.NumericValue;
import org.polarsys.capella.core.data.la.LogicalComponent;
import org.polarsys.capella.core.data.capellacore.Classifier;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellacore.ReuseLink;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.data.sharedmodel.SharedPkg;
import org.polarsys.capella.core.model.helpers.ClassifierExt;
import org.polarsys.capella.core.model.helpers.DataPkgExt;
import org.polarsys.capella.core.model.helpers.SystemEngineeringExt;
import org.polarsys.capella.core.model.helpers.query.CapellaQueries;
import org.polarsys.capella.core.model.utils.ListExt;

/**
 * 
 */
public class ComponentPort_MaxCardinality implements IBusinessQuery {

	/**
	 * All the NumericValues contained by the Data Package (and all of its
	 * subpackages) of the current Element's parent (can be a Component, a
	 * Component Architecture Decomposition package, or a Component Architecture
	 * root package).
	 */
	private List<CapellaElement> getRule_MQRY_StandardPort_MaxCard_11(ComponentPort currentProperty_p) {
		List<CapellaElement> availableElements = new ArrayList<CapellaElement>(1);

		Classifier classifier = (Classifier) currentProperty_p.eContainer();
		NumericValue maxCard = currentProperty_p.getOwnedMaxCard();
		ComponentArchitecture arch = ClassifierExt.getRootComponentArchitecture(classifier);
		if (null != arch) {
			DataPkg dataPkg = DataPkgExt.getDataPkgOfComponentArchitecture(arch);
			if (null != dataPkg) {
				for (DataValue value : DataPkgExt.getAllDataValues(dataPkg)) {
					if (value instanceof NumericValue) {
						if (value.equals(maxCard))
							continue;
						availableElements.add(value);
					}
				}
			}
		}
		Component comp = ClassifierExt.getRootComponent(classifier);
		if (null != comp) {
			if (comp instanceof LogicalComponent) {
				DataPkg dataPkg = ((LogicalComponent) comp).getOwnedDataPkg();
				if (null != dataPkg) {
					for (DataValue value : DataPkgExt.getAllDataValues(dataPkg)) {
						if (value instanceof NumericValue) {
							if (value.equals(maxCard))
								continue;
							availableElements.add(value);
						}
					}
				}
			}
		}
		return availableElements;
	}

	/**
	 * All the NumericValues contained by the Data Packages (and all of its
	 * subpackages) of the current Element's parents hierarchy according to
	 * layer visibility and multiple decomposition rules.
	 */
	private List<CapellaElement> getRule_MQRY_StandardPort_MaxCard_12(ComponentPort currentProperty_p) {
		List<CapellaElement> availableElements = new ArrayList<CapellaElement>(1);

		Classifier classifier = (Classifier) currentProperty_p.eContainer();
		NumericValue maxCard = currentProperty_p.getOwnedMaxCard();

		List<DataPkg> dataPkgList = ClassifierExt.getDataPkgsFromParentHierarchy(classifier);
		for (DataPkg dataPkg : dataPkgList) {
			if (null != dataPkg) {
				for (DataValue value : DataPkgExt.getAllDataValues(dataPkg)) {
					if (value instanceof NumericValue) {
						if (value.equals(maxCard))
							continue;
						availableElements.add(value);
					}
				}
			}
		}
		return availableElements;
	}

	/**
	 * All the NumericValues contained by the Data Package (and all of its
	 * subpackages) of the Shared Assets Package.
	 */
	private List<CapellaElement> getRule_MQRY_StandardPort_MaxCard_13(ComponentPort currentProperty_p, SystemEngineering systemEngineering_p) {
		List<CapellaElement> availableElements = new ArrayList<CapellaElement>(1);

		NumericValue maxCard = currentProperty_p.getOwnedMaxCard();

		for (SharedPkg sharedPkg : SystemEngineeringExt.getSharedPkgs(systemEngineering_p)) {
			DataPkg dataPkg = sharedPkg.getOwnedDataPkg();
			if (null != dataPkg) {
				for (DataValue value : DataPkgExt.getAllDataValues(dataPkg)) {
					if (value instanceof NumericValue) {
						if (value.equals(maxCard))
							continue;
						availableElements.add(value);
					}
				}
			}
		}
		return availableElements;
	}

	/**
	 * @see org.polarsys.capella.core.business.queries.capellacore.core.business.queries.IBusinessQuery#getAvailableElements(org.polarsys.capella.core.common.model.CapellaElement)
	 */
	public List<CapellaElement> getAvailableElements(CapellaElement element_p) {
		SystemEngineering systemEngineering = CapellaQueries.getInstance().getRootQueries().getSystemEngineering(element_p);
		List<CapellaElement> availableElements = new ArrayList<CapellaElement>();
		boolean isPropertyFromSharedPkg = false;
		if (null == systemEngineering) {
			SharedPkg sharedPkg = SystemEngineeringExt.getSharedPkg(element_p);
			for (ReuseLink link : sharedPkg.getReuseLinks()) {
				if (SystemEngineeringExt.getSystemEngineering(link) != null) {
					systemEngineering = SystemEngineeringExt.getSystemEngineering(link);
					isPropertyFromSharedPkg = true;
					break;
				}
			}
			if (systemEngineering == null)
				return availableElements;
		}

		if (element_p instanceof ComponentPort) {
		  ComponentPort property = (ComponentPort) element_p;
			if (!isPropertyFromSharedPkg) {
				availableElements.addAll(getRule_MQRY_StandardPort_MaxCard_11(property));
				availableElements.addAll(getRule_MQRY_StandardPort_MaxCard_12(property));
			}
			availableElements.addAll(getRule_MQRY_StandardPort_MaxCard_13(property, systemEngineering));
		}
		availableElements = ListExt.removeDuplicates(availableElements);
		return availableElements;
	}

	/**
	 * @see org.polarsys.capella.core.business.queries.capellacore.core.business.queries.IBusinessQuery#getCurrentElements(org.polarsys.capella.core.common.model.CapellaElement,
	 *      boolean)
	 */
	public List<CapellaElement> getCurrentElements(CapellaElement element_p, boolean onlyGenerated_p) {
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
			if (systemEngineering == null)
				return currentElements;
		}

		if (element_p instanceof ComponentPort) {
		  ComponentPort property = (ComponentPort) element_p;
			if (property.getOwnedMaxCard() != null) {
				currentElements.add(property.getOwnedMaxCard());
			}
		}
		return currentElements;
	}

	public EClass getEClass() {
		return FaPackage.Literals.COMPONENT_PORT;
	}

	public List<EReference> getEStructuralFeatures() {
    return Collections.singletonList(InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_MAX_CARD);
  }
	
}
