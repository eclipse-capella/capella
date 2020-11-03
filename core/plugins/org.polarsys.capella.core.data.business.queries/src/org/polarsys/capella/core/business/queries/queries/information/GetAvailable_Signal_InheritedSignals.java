/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
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

import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.queries.AbstractQuery;
import org.polarsys.capella.common.queries.queryContext.IQueryContext;
import org.polarsys.capella.core.business.queries.IBusinessQuery;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellacore.ReuseLink;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.ComponentArchitecture;
import org.polarsys.capella.core.data.helpers.capellacore.services.GeneralizableElementExt;
import org.polarsys.capella.core.data.information.DataPkg;
import org.polarsys.capella.core.data.information.communication.Signal;
import org.polarsys.capella.core.data.la.LogicalComponent;
import org.polarsys.capella.core.data.sharedmodel.SharedPkg;
import org.polarsys.capella.core.model.helpers.DataPkgExt;
import org.polarsys.capella.core.model.helpers.SignalExt;
import org.polarsys.capella.core.model.helpers.SystemEngineeringExt;
import org.polarsys.capella.core.model.helpers.query.CapellaQueries;
import org.polarsys.capella.core.model.utils.ListExt;

public class GetAvailable_Signal_InheritedSignals extends AbstractQuery {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<Object> execute(Object input, IQueryContext context) {
		CapellaElement capellaElement = (CapellaElement) input;
		List<EObject> availableElements = getAvailableElements(capellaElement);
		return (List) availableElements;
	}

	/** 
	 * <p>
	 * Gets all the signals contained by the signal package (and all sub
	 * packages) of the current element's parent, and all the signals contained
	 * by the signal package (and all sub packages) of the current element's
	 * parent hierarchy
	 * </p>
	 * <p>
	 * Except the current signal and the signals in the inheritance hierarchy of
	 * the current signal
	 * </p>
	 * <p>
	 * Refer MQRY_Signal_Inherited_1
	 * </p>
	 * @see IBusinessQuery#getAvailableElements(EObject)
	 */
	public List<EObject> getAvailableElements(CapellaElement element) {
		List<EObject> availableElements = new ArrayList<EObject>();
		SystemEngineering systemEngineering = CapellaQueries.getInstance().getRootQueries().getSystemEngineering(element);
		boolean isSignalFromSharedPkg = false;
		if (null == systemEngineering) {
			SharedPkg sharedPkg = SystemEngineeringExt.getSharedPkg(element);
			for (ReuseLink link : sharedPkg.getReuseLinks()) {
				if (SystemEngineeringExt.getSystemEngineering(link) != null) {
					systemEngineering = SystemEngineeringExt.getSystemEngineering(link);
					isSignalFromSharedPkg = true;
					break;
				}
			}
			if (systemEngineering == null)
				return availableElements;
		}
		if (element instanceof Signal) {
			if (!isSignalFromSharedPkg) {
				availableElements.addAll(getRule_MQRY_Signal_Inherited_11((Signal) element, systemEngineering));
				availableElements.addAll(getRule_MQRY_Signal_Inherited_12((Signal) element, systemEngineering));
				availableElements.addAll(getRule_MQRY_Signal_Inherited_13((Signal) element, systemEngineering));
			}
		}
		availableElements = ListExt.removeDuplicates(availableElements);
		availableElements.remove(element);
		return availableElements;
	}

	/** 
	 * All the Signals contained by the Signal Package (and all of its
	 * sub-packages) of the current Element.
	 */
	private List<CapellaElement> getRule_MQRY_Signal_Inherited_11(Signal currentSignal, SystemEngineering systemEngineering) {
		List<CapellaElement> availableElements = new ArrayList<CapellaElement>();
		DataPkg signalPkg = SignalExt.getRootOwnerDataPkg(currentSignal);
		if (signalPkg != null) {
			for (Signal signal : DataPkgExt.getAllSignals(signalPkg)) {
				if ((signal == null) || (signal.equals(currentSignal)))
					continue;
				if (!GeneralizableElementExt.getAllSuperGeneralizableElements(currentSignal).contains(signal)
						&& !GeneralizableElementExt.getAllSuperGeneralizableElements(signal).contains(currentSignal)) {
					availableElements.add(signal);
				}
			}
		}
		return availableElements;
	}

	/** 
	 * All the Signals contained by the Signal Package (and all of its
	 * sub-packages) of the current Element's parent (can be a Component, a
	 * Component Architecture Decomposition package, or a Component Architecture
	 * root package).
	 */
	private List<CapellaElement> getRule_MQRY_Signal_Inherited_12(Signal currentSignal, SystemEngineering systemEngineering) {
		List<CapellaElement> availableElements = new ArrayList<CapellaElement>();
		DataPkg signalPkg = SignalExt.getRootOwnerDataPkg(currentSignal);
		if (signalPkg != null) {
			ComponentArchitecture compArch = DataPkgExt.getRootComponentArchitecture(signalPkg);
			if (null != compArch) {
				DataPkg dataPkg = DataPkgExt.getDataPkgOfComponentArchitecture(compArch);
				if (null != dataPkg) {
					for (Signal signal : DataPkgExt.getAllSignals(dataPkg)) {
						if ((signal == null) || (signal.equals(currentSignal)))
							continue;
						if (!GeneralizableElementExt.getAllSuperGeneralizableElements(currentSignal).contains(signal)
								&& !GeneralizableElementExt.getAllSuperGeneralizableElements(signal).contains(currentSignal)) {
							availableElements.add(signal);
						}
					}
				}
			}
			Component parentComponent = DataPkgExt.getRootComponent(signalPkg);
			if (null != parentComponent) {
				if (parentComponent instanceof LogicalComponent) {
					DataPkg dataPkg = ((LogicalComponent) parentComponent).getOwnedDataPkg();
					if (null != dataPkg) {
						for (Signal signal : DataPkgExt.getAllSignals(dataPkg)) {
							if ((signal == null) || (signal.equals(currentSignal)))
								continue;
							if (!GeneralizableElementExt.getAllSuperGeneralizableElements(currentSignal).contains(signal)
									&& !GeneralizableElementExt.getAllSuperGeneralizableElements(signal).contains(currentSignal)) {
								availableElements.add(signal);
							}
						}
					}
				}
			}
		}
		return availableElements;
	}

	/** 
	 * All the Signals contained by the Signal Package (and all of its
	 * sub-packages) of the current Element's parents hierarchy.
	 */
	private List<CapellaElement> getRule_MQRY_Signal_Inherited_13(Signal currentSignal, SystemEngineering systemEngineering) {
		List<CapellaElement> availableElements = new ArrayList<CapellaElement>();
		DataPkg signalPkg = SignalExt.getRootOwnerDataPkg(currentSignal);
		for (Signal signal : DataPkgExt.getSignalsFromParentHierarchy(signalPkg)) {
			if ((signal == null) || (signal.equals(currentSignal)))
				continue;
			if (!GeneralizableElementExt.getAllSuperGeneralizableElements(currentSignal).contains(signal)
					&& !GeneralizableElementExt.getAllSuperGeneralizableElements(signal).contains(currentSignal)) {
				availableElements.add(signal);
			}
		}
		return availableElements;
	}

}