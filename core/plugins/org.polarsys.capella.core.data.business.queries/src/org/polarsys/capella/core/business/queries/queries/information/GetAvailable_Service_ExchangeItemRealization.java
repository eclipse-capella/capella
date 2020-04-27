/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
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
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
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
	 * @see org.polarsys.capella.core.business.queries.capellacore.IBusinessQuery#getAvailableElements(EObject)
	 */
	public List<CapellaElement> getAvailableElements(CapellaElement element) {
		List<CapellaElement> availableElements = new ArrayList<CapellaElement>();
		SystemEngineering systemEngineering = CapellaQueries.getInstance().getRootQueries().getSystemEngineering(element);
		if (null == systemEngineering) {
			return availableElements;
		}
		if (element instanceof Service) {
			Service service = (Service) element;
			availableElements.addAll(getRule_MQRY_Service_ItemRealization_11(systemEngineering, service));
		}
		return availableElements;
	}

	private List<CapellaElement> getRule_MQRY_Service_ItemRealization_11(SystemEngineering systemEng, Service currentProperty) {
		List<CapellaElement> availableElements = new ArrayList<CapellaElement>(1);
		BlockArchitecture arch = null;
		arch = SystemEngineeringExt.getRootBlockArchitecture(currentProperty);
		availableElements.addAll(getElementsFromBlockArchitecture(arch, currentProperty));
		if (!(arch instanceof OperationalAnalysis)) {
			availableElements.addAll(getRule_MQRY_Service_ItemRealization_11_1(systemEng, currentProperty));
		}
		return availableElements;
	}

	/** 
	 * @param currentProperty 
	 * @param oa
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private List<CapellaElement> getElementsFromBlockArchitecture(BlockArchitecture arch, Service currentProperty) {
		List<CapellaElement> availableElements = new ArrayList<CapellaElement>(1);
		if (arch != null) {
			DataPkg ownedDataPkg = arch.getOwnedDataPkg();
			if (null != ownedDataPkg) {
				availableElements.addAll((Collection<? extends CapellaElement>) AbstractExchangeItemPkgExt.getAllAbstractExchangeItems(ownedDataPkg));
				EList<ExchangeItemRealization> ownedExchangeItemRealisation = currentProperty.getOwnedExchangeItemRealizations();
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

	private List<CapellaElement> getRule_MQRY_Service_ItemRealization_11_1(SystemEngineering systemEng, Service currentProperty) {
		List<CapellaElement> availableElements = new ArrayList<CapellaElement>(1);
		BlockArchitecture arch = null;
		arch = SystemEngineeringExt.getRootBlockArchitecture(currentProperty);
		SystemEngineering sysEng = CapellaQueries.getInstance().getRootQueries().getSystemEngineering(currentProperty);
		OperationalAnalysis oa = SystemEngineeringExt.getOwnedOperationalAnalysis(sysEng);
		if (null != oa) {
			availableElements.addAll(getElementsFromBlockArchitecture(oa, currentProperty));
		} else {
			SystemAnalysis ca = SystemEngineeringExt.getOwnedSystemAnalysis(sysEng);
			availableElements.addAll(getElementsFromBlockArchitecture(ca, currentProperty));
		}
		if (null != arch) {
			if (null != oa && (arch instanceof LogicalArchitecture) || (arch instanceof PhysicalArchitecture) || (arch instanceof EPBSArchitecture)) {
				SystemAnalysis ctxArch = SystemEngineeringExt.getOwnedSystemAnalysis(sysEng);
				availableElements.addAll(getElementsFromBlockArchitecture(ctxArch, currentProperty));
			}
			if ((arch instanceof PhysicalArchitecture) || (arch instanceof EPBSArchitecture)) {
				LogicalArchitecture logArch = SystemEngineeringExt.getOwnedLogicalArchitecture(sysEng);
				availableElements.addAll(getElementsFromBlockArchitecture(logArch, currentProperty));
			}
			if ((arch instanceof EPBSArchitecture)) {
				PhysicalArchitecture physArch = SystemEngineeringExt.getOwnedPhysicalArchitecture(sysEng);
				availableElements.addAll(getElementsFromBlockArchitecture(physArch, currentProperty));
			}
		}
		return availableElements;
	}

}