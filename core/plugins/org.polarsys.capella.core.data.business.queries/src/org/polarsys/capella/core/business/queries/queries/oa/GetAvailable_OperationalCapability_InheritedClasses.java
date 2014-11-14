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
package org.polarsys.capella.core.business.queries.queries.oa;

import java.util.ArrayList;
import java.util.List;

import org.polarsys.capella.common.queries.AbstractQuery;
import org.polarsys.capella.common.queries.queryContext.IQueryContext;
import org.polarsys.capella.core.data.capellacommon.AbstractCapabilityPkg;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.helpers.interaction.services.AbstractCapabilityExt;
import org.polarsys.capella.core.data.interaction.AbstractCapability;
import org.polarsys.capella.core.data.oa.OperationalCapability;
import org.polarsys.capella.core.model.helpers.AbstractCapabilityPkgExt;
import org.polarsys.capella.core.model.helpers.SystemEngineeringExt;
import org.polarsys.capella.core.model.helpers.query.CapellaQueries;
import org.polarsys.capella.core.model.utils.ListExt;

public class GetAvailable_OperationalCapability_InheritedClasses extends AbstractQuery {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<Object> execute(Object input, IQueryContext context) {
		CapellaElement capellaElement = (CapellaElement) input;
		List<CapellaElement> availableElements = getAvailableElements(capellaElement);
		return (List) availableElements;
	}

	/** 
	 * <p>
	 * Gets all the capabilities except those that are in the inheritance
	 * hierarchy of the current Capability from teh system engineering package.
	 * </p>
	 * <p>
	 * Refer MQRY_Capability_Inherited_1
	 * </p>
	 * @see org.polarsys.capella.core.business.queries.core.business.queries.IBusinessQuery#getAvailableElements(org.polarsys.capella.core.common.model.CapellaElement)
	 */
	public List<CapellaElement> getAvailableElements(CapellaElement element_p) {
		List<CapellaElement> availableElements = new ArrayList<CapellaElement>();
		SystemEngineering systemEngineering = CapellaQueries.getInstance().getRootQueries().getSystemEngineering(element_p);
		if (null == systemEngineering) {
			return availableElements;
		}
		if (element_p instanceof OperationalCapability) {
			OperationalCapability currentCapabilityUseCase = (OperationalCapability) element_p;
			availableElements.addAll(getRule_MQRY_CapabiiltyUseCase_Inherited_11(systemEngineering, currentCapabilityUseCase, true));
		}
		availableElements = ListExt.removeDuplicates(availableElements);
		return availableElements;
	}

	/** 
	 * <p>
	 * Gets all the capabilities except those that are in the inheritance
	 * hierarchy of the current Capability and those that are already
	 * extended/included by the current capability from the system engineering
	 * package.
	 * </p>
	 * <p>
	 * Refer MQRY_Capability_Inherited_11
	 * </p>
	 * @param sysEng_pthe system engineering
	 * @param currentCapabilityUseCase_pthe current capability
	 * @param isFilterRequiredflag for checking with current capability
	 * @return list of {@link CapabilityUseCase}
	 */
	private List<CapellaElement> getRule_MQRY_CapabiiltyUseCase_Inherited_11(SystemEngineering sysEng_p, OperationalCapability currentCapabilityUseCase_p,
			boolean isFilterRequired) {
		List<CapellaElement> availableElements = new ArrayList<CapellaElement>(1);
		if (currentCapabilityUseCase_p != null) {
			BlockArchitecture rootBlockArchitecture = SystemEngineeringExt.getRootBlockArchitecture(currentCapabilityUseCase_p);
			AbstractCapabilityPkg ownedAbstractCapabilityPkg = rootBlockArchitecture.getOwnedAbstractCapabilityPkg();
			List<AbstractCapability> absCap = AbstractCapabilityPkgExt.getAllCapabilities(ownedAbstractCapabilityPkg);
			for (AbstractCapability capabilityUseCase : absCap) {
				if (currentCapabilityUseCase_p.equals(capabilityUseCase)) {
					continue;
				}
				if (!AbstractCapabilityExt.isSuperCapability(currentCapabilityUseCase_p, capabilityUseCase)
						&& !AbstractCapabilityExt.isSuperCapability(capabilityUseCase, currentCapabilityUseCase_p)) {
					availableElements.add(capabilityUseCase);
				} else {
					availableElements.add(capabilityUseCase);
				}
			}
		}
		return availableElements;
	}

}