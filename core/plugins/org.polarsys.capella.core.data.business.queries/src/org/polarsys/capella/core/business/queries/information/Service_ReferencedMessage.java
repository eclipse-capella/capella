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
import org.polarsys.capella.core.business.queries.IBusinessQuery;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellacore.ReuseLink;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.data.cs.ComponentArchitecture;
import org.polarsys.capella.core.data.ctx.SystemAnalysis;
import org.polarsys.capella.core.data.epbs.EPBSArchitecture;
import org.polarsys.capella.core.data.information.DataPkg;
import org.polarsys.capella.core.data.information.InformationPackage;
import org.polarsys.capella.core.data.information.Service;
import org.polarsys.capella.core.data.information.communication.MessageReference;
import org.polarsys.capella.core.data.pa.PhysicalArchitecture;
import org.polarsys.capella.core.data.sharedmodel.GenericPkg;
import org.polarsys.capella.core.data.sharedmodel.SharedPkg;
import org.polarsys.capella.core.model.helpers.DataPkgExt;
import org.polarsys.capella.core.model.helpers.GenericPkgExt;
import org.polarsys.capella.core.model.helpers.ServiceExt;
import org.polarsys.capella.core.model.helpers.SystemEngineeringExt;
import org.polarsys.capella.core.model.helpers.query.CapellaQueries;
import org.polarsys.capella.core.model.utils.ListExt;

/**
 */
public class Service_ReferencedMessage implements IBusinessQuery {

	/**
	 * Gets All the Messages contained by the Message Package (and all of its
	 * subpackages) of the current Element's parent (can be a Component, a
	 * Component Architecture Decomposition package, or a Component Architecture
	 * root package).
	 * 
	 * @return list of Messages
	 */
	private List<CapellaElement> getRule_MQRY_Service_Ref_11(Service currentService_p) {
		List<CapellaElement> availableElements = new ArrayList<CapellaElement>(1);
		// get all the messages from RootComponentArchitecture
		availableElements.addAll(ServiceExt.getFilteredMessages(currentService_p, ServiceExt.getMessageFromRootComponentArchitecture(currentService_p)));

		// get all the messages from RootComponent
		availableElements.addAll(ServiceExt.getFilteredMessages(currentService_p, ServiceExt.getMessageFromRootComponent(currentService_p)));
		return availableElements;
	}

	/**
	 * All the Messages contained by the Message Packages (and all of its
	 * subpackages) of the current Element's parents hierarchy according to
	 * layer visibility and multiple decomposition rules.
	 * 
	 * @return
	 */
	private List<CapellaElement> getRule_MQRY_Service_Ref_12(Service currentService_p) {
		List<CapellaElement> availableElements = new ArrayList<CapellaElement>(1);
		availableElements.addAll(ServiceExt.getFilteredMessages(currentService_p, ServiceExt.getMessagesFromParentHierarchy(currentService_p)));
		availableElements.addAll(getRule_MQRY_Service_Ref_12_1(currentService_p));
		return availableElements;
	}

	/*
	 * layer visibility
	 */
	private List<CapellaElement> getRule_MQRY_Service_Ref_12_1(Service currentService_p) {
		List<CapellaElement> availableElements = new ArrayList<CapellaElement>(1);
		ComponentArchitecture arch = ServiceExt.getRootComponentArchitecture(currentService_p);
		SystemEngineering systemEngineering = CapellaQueries.getInstance().getRootQueries().getSystemEngineering(currentService_p);
		SystemAnalysis ca = SystemEngineeringExt.getOwnedSystemAnalysis(systemEngineering);
		availableElements.addAll(ServiceExt.getMessagesFromComponentArchitecture(ca));
		if (arch != null) {
			if ((arch instanceof PhysicalArchitecture) || (arch instanceof EPBSArchitecture)) {
				availableElements.addAll(ServiceExt.getMessagesFromComponentArchitecture(SystemEngineeringExt.getOwnedLogicalArchitecture(systemEngineering)));
			}
			if (arch instanceof EPBSArchitecture) {
				availableElements.addAll(ServiceExt.getMessagesFromComponentArchitecture(SystemEngineeringExt.getOwnedPhysicalArchitecture(systemEngineering)));
			}
		}
		return availableElements;
	}

	/**
	 * All the Messages contained by the Message Package (and all of its
	 * subpackages) of the SharedPkg
	 * 
	 * @return
	 */
	private List<CapellaElement> getRule_MQRY_Service_Ref_13(Service currentService_p, SystemEngineering sysEng_p) {
		List<CapellaElement> availableElements = new ArrayList<CapellaElement>(1);
		for (SharedPkg sharedPkg : SystemEngineeringExt.getSharedPkgs(sysEng_p)) {
			GenericPkg pkg = sharedPkg.getOwnedGenericPkg();
			if (pkg != null) {
				availableElements.addAll(ServiceExt.getFilteredMessages(currentService_p, GenericPkgExt.getAllMessages(pkg)));
			}
			DataPkg dataPkg = sharedPkg.getOwnedDataPkg();
			if (dataPkg != null) {
				availableElements.addAll(ServiceExt.getFilteredMessages(currentService_p, DataPkgExt.getAllMessages(dataPkg)));
			}
		}
		return availableElements;
	}

	/**
	 * @see org.polarsys.capella.core.business.queries.capellacore.core.business.queries.IBusinessQuery#getAvailableElements(org.polarsys.capella.core.common.model.CapellaElement)
	 */
	public List<CapellaElement> getAvailableElements(CapellaElement element_p) {
		List<CapellaElement> availableElements = new ArrayList<CapellaElement>();

		SystemEngineering systemEngineering = CapellaQueries.getInstance().getRootQueries().getSystemEngineering(element_p);
		boolean isServiceFromSharedPkg = false;
		if (null == systemEngineering) {
			SharedPkg sharedPkg = SystemEngineeringExt.getSharedPkg(element_p);
      if (sharedPkg != null) {
  			for (ReuseLink link : sharedPkg.getReuseLinks()) {
  				if (SystemEngineeringExt.getSystemEngineering(link) != null) {
  					systemEngineering = SystemEngineeringExt.getSystemEngineering(link);
  					isServiceFromSharedPkg = true;
  					break;
  				}
  			}
      }
			if (systemEngineering == null)
				return availableElements;
		}

		if (element_p instanceof Service) {
			Service currentService = (Service) element_p;
			if (!isServiceFromSharedPkg) {
				availableElements.addAll(getRule_MQRY_Service_Ref_11(currentService));
				availableElements.addAll(getRule_MQRY_Service_Ref_12(currentService));
			}
			availableElements.addAll(getRule_MQRY_Service_Ref_13(currentService, systemEngineering));
		}
		availableElements = ListExt.removeDuplicates(availableElements);
		return availableElements;
	}

	/**
	 * @see org.polarsys.capella.core.business.queries.capellacore.core.business.queries.IBusinessQuery#getCurrentElements(org.polarsys.capella.core.common.model.CapellaElement,
	 *      boolean)
	 */
	public List<CapellaElement> getCurrentElements(CapellaElement element_p, boolean onlyGenerated_p) {
		List<CapellaElement> currentElements = new ArrayList<CapellaElement>(1);

		SystemEngineering systemEngineering = CapellaQueries.getInstance().getRootQueries().getSystemEngineering(element_p);
		if (null == systemEngineering) {
			SharedPkg sharedPkg = SystemEngineeringExt.getSharedPkg(element_p);
      if (sharedPkg != null) {
  			for (ReuseLink link : sharedPkg.getReuseLinks()) {
  				if (SystemEngineeringExt.getSystemEngineering(link) != null) {
  					systemEngineering = SystemEngineeringExt.getSystemEngineering(link);
  					break;
  				}
  			}
      }
			if (systemEngineering == null)
				return currentElements;
		}

		if (element_p instanceof Service) {
			Service currentService = (Service) element_p;
			for (MessageReference ref : currentService.getMessageReferences()) {
				currentElements.add(ref.getMessage());
			}
		}
		return currentElements;
	}

	public EClass getEClass() {
		return InformationPackage.Literals.SERVICE;
	}

	public List<EReference> getEStructuralFeatures() {
    return Collections.singletonList(InformationPackage.Literals.SERVICE__MESSAGE_REFERENCES);
	}
}
