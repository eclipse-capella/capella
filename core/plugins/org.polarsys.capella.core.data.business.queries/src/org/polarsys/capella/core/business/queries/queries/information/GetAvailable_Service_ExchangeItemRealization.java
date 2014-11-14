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
package org.polarsys.capella.core.business.queries.queries.information;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.polarsys.capella.common.data.modellingcore.AbstractExchangeItem;
import org.polarsys.capella.common.data.modellingcore.TraceableElement;
import org.polarsys.capella.common.queries.AbstractQuery;
import org.polarsys.capella.common.queries.queryContext.IQueryContext;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.ctx.SystemAnalysis;
import org.polarsys.capella.core.data.epbs.EPBSArchitecture;
import org.polarsys.capella.core.data.information.DataPkg;
import org.polarsys.capella.core.data.information.ExchangeItemRealization;
import org.polarsys.capella.core.data.information.Service;
import org.polarsys.capella.core.data.la.LogicalArchitecture;
import org.polarsys.capella.core.data.oa.OperationalAnalysis;
import org.polarsys.capella.core.data.pa.PhysicalArchitecture;
import org.polarsys.capella.core.model.helpers.AbstractExchangeItemPkgExt;
import org.polarsys.capella.core.model.helpers.SystemEngineeringExt;
import org.polarsys.capella.core.model.helpers.query.CapellaQueries;

public class GetAvailable_Service_ExchangeItemRealization extends AbstractQuery {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<Object> execute(Object input, IQueryContext context) {
		CapellaElement capellaElement = (CapellaElement) input;
		List<CapellaElement> availableElements = getAvailableElements(capellaElement);
		return (List) availableElements;
	}

	/** 
	 * @see org.polarsys.capella.core.business.queries.capellacore.IBusinessQuery#getAvailableElements(org.polarsys.capella.core.data.capellacore.CapellaElement)
	 */
	public List<CapellaElement> getAvailableElements(CapellaElement element_p) {
		List<CapellaElement> availableElements = new ArrayList<CapellaElement>();
		SystemEngineering systemEngineering = CapellaQueries.getInstance().getRootQueries().getSystemEngineering(element_p);
		if (null == systemEngineering) {
			return availableElements;
		}
		if (element_p instanceof Service) {
			Service service = (Service) element_p;
			availableElements.addAll(getRule_MQRY_Service_ItemRealization_11(systemEngineering, service));
		}
		return availableElements;
	}

	private List<CapellaElement> getRule_MQRY_Service_ItemRealization_11(SystemEngineering systemEng_p, Service currentProperty_p) {
		List<CapellaElement> availableElements = new ArrayList<CapellaElement>(1);
		BlockArchitecture arch = null;
		arch = SystemEngineeringExt.getRootBlockArchitecture(currentProperty_p);
		availableElements.addAll(getElementsFromBlockArchitecture(arch, currentProperty_p));
		if (!(arch instanceof OperationalAnalysis)) {
			availableElements.addAll(getRule_MQRY_Service_ItemRealization_11_1(systemEng_p, currentProperty_p));
		}
		return availableElements;
	}

	/** 
	 * @param currentProperty_p_p 
	 * @param oa_p
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private List<CapellaElement> getElementsFromBlockArchitecture(BlockArchitecture arch_p, Service currentProperty_p) {
		List<CapellaElement> availableElements = new ArrayList<CapellaElement>(1);
		if (arch_p != null) {
			DataPkg ownedDataPkg = arch_p.getOwnedDataPkg();
			if (null != ownedDataPkg) {
				availableElements.addAll((Collection<? extends CapellaElement>) AbstractExchangeItemPkgExt.getAllAbstractExchangeItems(ownedDataPkg));
				EList<ExchangeItemRealization> ownedExchangeItemRealisation = currentProperty_p.getOwnedExchangeItemRealizations();
				for (ExchangeItemRealization exchangeItemRealisation : ownedExchangeItemRealisation) {
					TraceableElement targetElement = exchangeItemRealisation.getTargetElement();
					if (null != targetElement && targetElement instanceof AbstractExchangeItem) {
						availableElements.remove(targetElement);
					}
				}
			}
		}
		return availableElements;
	}

	private List<CapellaElement> getRule_MQRY_Service_ItemRealization_11_1(SystemEngineering systemEng_p, Service currentProperty_p) {
		List<CapellaElement> availableElements = new ArrayList<CapellaElement>(1);
		BlockArchitecture arch = null;
		arch = SystemEngineeringExt.getRootBlockArchitecture(currentProperty_p);
		SystemEngineering sysEng = CapellaQueries.getInstance().getRootQueries().getSystemEngineering(currentProperty_p);
		OperationalAnalysis oa = SystemEngineeringExt.getOwnedOperationalAnalysis(sysEng);
		if (null != oa) {
			availableElements.addAll(getElementsFromBlockArchitecture(oa, currentProperty_p));
		} else {
			SystemAnalysis ca = SystemEngineeringExt.getOwnedSystemAnalysis(sysEng);
			availableElements.addAll(getElementsFromBlockArchitecture(ca, currentProperty_p));
		}
		if (null != arch) {
			if (null != oa && (arch instanceof LogicalArchitecture) || (arch instanceof PhysicalArchitecture) || (arch instanceof EPBSArchitecture)) {
				SystemAnalysis ctxArch = SystemEngineeringExt.getOwnedSystemAnalysis(sysEng);
				availableElements.addAll(getElementsFromBlockArchitecture(ctxArch, currentProperty_p));
			}
			if ((arch instanceof PhysicalArchitecture) || (arch instanceof EPBSArchitecture)) {
				LogicalArchitecture logArch = SystemEngineeringExt.getOwnedLogicalArchitecture(sysEng);
				availableElements.addAll(getElementsFromBlockArchitecture(logArch, currentProperty_p));
			}
			if ((arch instanceof EPBSArchitecture)) {
				PhysicalArchitecture physArch = SystemEngineeringExt.getOwnedPhysicalArchitecture(sysEng);
				availableElements.addAll(getElementsFromBlockArchitecture(physArch, currentProperty_p));
			}
		}
		return availableElements;
	}

}