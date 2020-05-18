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

package org.polarsys.capella.core.data.helpers.information.delegates;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EStructuralFeature;

import org.polarsys.capella.core.data.helpers.capellacore.delegates.NamedElementHelper;
import org.polarsys.capella.core.data.information.InformationPackage;
import org.polarsys.capella.core.data.information.Port;
import org.polarsys.capella.core.data.information.PortAllocation;
import org.polarsys.capella.core.data.information.PortRealization;
import org.polarsys.capella.common.data.modellingcore.AbstractTrace;

public class PortHelper {
	private static PortHelper instance;
	
	private PortHelper() {
    // do nothing
	}
	
	public static PortHelper getInstance(){
		if(instance == null)
			instance = new PortHelper();
		return instance;
	}
	
	public Object doSwitch(Port element, EStructuralFeature feature) {
		Object ret = null;

		if (feature.equals(InformationPackage.Literals.PORT__INCOMING_PORT_REALIZATIONS)) {
			ret = getIncomingPortRealizations(element);
		}
		else if (feature.equals(InformationPackage.Literals.PORT__OUTGOING_PORT_REALIZATIONS)) {
			ret = getOutgoingPortRealizations(element);
		}
		else if (feature.equals(InformationPackage.Literals.PORT__INCOMING_PORT_ALLOCATIONS)) {
			ret = getIncomingPortAllocations(element);
		}
		else if (feature.equals(InformationPackage.Literals.PORT__OUTGOING_PORT_ALLOCATIONS)) {
			ret = getOutgoingPortAllocations(element);
		}

		// no helper found... searching in super classes...
		if(null == ret) {
			ret = NamedElementHelper.getInstance().doSwitch(element, feature);
		}

		return ret;
	}

	protected List<PortAllocation> getIncomingPortAllocations(Port element){
		List<AbstractTrace> traces = element.getIncomingTraces();
		List<PortAllocation> ret = new ArrayList<>();
		
		for (AbstractTrace trace : traces) {
			if(trace instanceof PortAllocation) {
				ret.add((PortAllocation)trace);
			}
		}

		return ret;
	}
	
	protected List<PortAllocation> getOutgoingPortAllocations(Port element){
		List<AbstractTrace> traces = element.getOutgoingTraces();
		List<PortAllocation> ret = new ArrayList<>();
		
		for (AbstractTrace trace : traces) {
			if(trace instanceof PortAllocation) {
				ret.add((PortAllocation)trace);
			}
		}
		
		return ret;
	}
	
	protected List<PortRealization> getIncomingPortRealizations(Port element){
		List<AbstractTrace> traces = element.getIncomingTraces();
		List<PortRealization> ret = new ArrayList<>();
		
		for (AbstractTrace trace : traces) {
			if(trace instanceof PortRealization) {
				ret.add((PortRealization)trace);
			}
		}
		
		return ret;
	}
	
	protected List<PortRealization> getOutgoingPortRealizations(Port element){
		List<AbstractTrace> traces = element.getOutgoingTraces();
		List<PortRealization> ret = new ArrayList<>();
		
		for (AbstractTrace trace : traces) {
			if(trace instanceof PortRealization) {
				ret.add((PortRealization)trace);
			}
		}
		
		return ret;
	}
}
