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
package org.polarsys.capella.core.business.queries.queries.la;

import java.util.ArrayList;
import java.util.List;

import org.polarsys.capella.common.queries.AbstractQuery;
import org.polarsys.capella.common.queries.queryContext.IQueryContext;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.helpers.interaction.services.AbstractCapabilityExt;
import org.polarsys.capella.core.data.la.CapabilityRealization;
import org.polarsys.capella.core.data.la.CapabilityRealizationPkg;
import org.polarsys.capella.core.model.helpers.CapellaElementExt;
import org.polarsys.capella.core.model.helpers.SystemEngineeringExt;
import org.polarsys.capella.core.model.utils.ListExt;

public class GetAvailable_CapabilityRealization_InheritedCapabilities extends AbstractQuery {

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
	 * hierarchy of the current Capability from the system engineering package.
	 * </p>
	 * <p>
	 * Refer MQRY_Capability_Inherited_1
	 * </p>
	 * @see org.polarsys.capella.core.business.queries.IBusinessQuery#getAvailableElements(org.polarsys.capella.core.data.capellacore.CapellaElement)
	 */
	public List<CapellaElement> getAvailableElements(CapellaElement element_p) {
		List<CapellaElement> availableElements = new ArrayList<CapellaElement>();
		BlockArchitecture currentBlockArchitecture = SystemEngineeringExt.getRootBlockArchitecture(element_p);
		if (null == currentBlockArchitecture) {
			return availableElements;
		}
		if (element_p instanceof CapabilityRealization) {
			CapabilityRealization currentCapabilityUseCase = (CapabilityRealization) element_p;
			availableElements.addAll(getRule_MQRY_CapabilityRealizationUseCase_Inherited_11(currentBlockArchitecture, currentCapabilityUseCase, true));
		} else if (element_p instanceof CapabilityRealizationPkg) {
			availableElements.addAll(getRule_MQRY_CapabilityRealizationUseCase_Inherited_11(currentBlockArchitecture, null, false));
		}
		availableElements = ListExt.removeDuplicates(availableElements);
		return availableElements;
	}

	/** 
	 * <p>
	 * Gets all the capabilities except those that are in the inheritance
	 * hierarchy of the current Capability from the system engineering
	 * package.
	 * </p>
	 * <p>
	 * Refer MQRY_Capability_Inherited_11
	 * </p>
	 * @param blockArch_pthe system engineering
	 * @param currentCapabilityUseCase_pthe current capability
	 * @param isFilterRequiredflag for checking with current capability
	 * @return list of {@link CapabilityRealizationUseCase}
	 */
	private List<CapellaElement> getRule_MQRY_CapabilityRealizationUseCase_Inherited_11(BlockArchitecture blockArch_p,
			CapabilityRealization currentCapabilityUseCase_p, boolean isFilterRequired) {
		List<CapellaElement> availableElements = new ArrayList<CapellaElement>(1);
		for (CapabilityRealization capabilityUseCase : CapellaElementExt.getAllCapabilityRealization(blockArch_p)) {
			if (capabilityUseCase instanceof CapabilityRealization) {
				if (isFilterRequired) {
					if (currentCapabilityUseCase_p.equals(capabilityUseCase)) {
						continue;
					}
					if (!AbstractCapabilityExt.isSuperCapability(currentCapabilityUseCase_p, capabilityUseCase)
							&& !AbstractCapabilityExt.isSuperCapability(capabilityUseCase, currentCapabilityUseCase_p)) {
						availableElements.add(capabilityUseCase);
					}
				} else {
					availableElements.add(capabilityUseCase);
				}
			}
		}
		return availableElements;
	}

}