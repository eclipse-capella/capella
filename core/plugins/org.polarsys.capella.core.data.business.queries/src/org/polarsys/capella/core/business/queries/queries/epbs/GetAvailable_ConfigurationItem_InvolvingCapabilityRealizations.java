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
package org.polarsys.capella.core.business.queries.queries.epbs;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.queries.AbstractQuery;
import org.polarsys.capella.common.queries.queryContext.IQueryContext;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.data.epbs.ConfigurationItem;
import org.polarsys.capella.core.data.epbs.EPBSArchitecture;
import org.polarsys.capella.core.data.epbs.EPBSArchitecturePkg;
import org.polarsys.capella.core.data.la.CapabilityRealization;
import org.polarsys.capella.core.model.helpers.CapellaElementExt;
import org.polarsys.capella.core.model.helpers.SystemEngineeringExt;
import org.polarsys.capella.core.model.helpers.query.CapellaQueries;
import org.polarsys.capella.core.model.utils.ListExt;

public class GetAvailable_ConfigurationItem_InvolvingCapabilityRealizations extends AbstractQuery {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<Object> execute(Object input, IQueryContext context) {
		CapellaElement capellaElement = (CapellaElement) input;
		List<EObject> availableElements = getAvailableElements(capellaElement);
		return (List) availableElements;
	}

	/** 
	 * @see org.polarsys.capella.core.business.queries.capellacore.core.business.queries.IBusinessQuery#getAvailableElements(EObject)
	 */
	public List<EObject> getAvailableElements(CapellaElement element) {
		SystemEngineering systemEngineering = CapellaQueries.getInstance().getRootQueries().getSystemEngineering(element);
		List<EObject> availableElements = new ArrayList<EObject>();
		if (null == systemEngineering) {
			return availableElements;
		}
		if (element instanceof ConfigurationItem) {
			ConfigurationItem currentCI = (ConfigurationItem) element;
			availableElements.addAll(getRule_MQRY_ConfigurationItem_Realizations_11(currentCI, systemEngineering));
		}
		availableElements = ListExt.removeDuplicates(availableElements);
		return availableElements;
	}

	private List<CapellaElement> getRule_MQRY_ConfigurationItem_Realizations_11(ConfigurationItem currentConfigurationItem,
			SystemEngineering systemEngineering) {
		List<CapellaElement> availableElements = new ArrayList<CapellaElement>(1);
		EPBSArchitecturePkg epbsArchPkg = SystemEngineeringExt.getEPBSArchitecturePkg(systemEngineering);
		if (null != epbsArchPkg) {
			for (EPBSArchitecture epbsArch : epbsArchPkg.getOwnedEPBSArchitectures()) {
				for (CapabilityRealization realization : CapellaElementExt.getAllCapabilityRealizationInvolvedWith(epbsArch)) {
					availableElements.add(realization);
				}
			}
		}
		EPBSArchitecture epbsArch = SystemEngineeringExt.getEPBSArchitecture(systemEngineering);
		if (null != epbsArch) {
			for (CapabilityRealization realization : CapellaElementExt.getAllCapabilityRealizationInvolvedWith(epbsArch)) {
				availableElements.add(realization);
			}
		}
		return availableElements;
	}

}