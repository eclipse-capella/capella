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
package org.polarsys.capella.core.business.queries.queries.la;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
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
		List<EObject> availableElements = getAvailableElements(capellaElement);
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
	 * @see org.polarsys.capella.core.business.queries.IBusinessQuery#getAvailableElements(EObject)
	 */
	public List<EObject> getAvailableElements(CapellaElement element) {
		List<EObject> availableElements = new ArrayList<EObject>();
		BlockArchitecture currentBlockArchitecture = SystemEngineeringExt.getRootBlockArchitecture(element);
		if (null == currentBlockArchitecture) {
			return availableElements;
		}
		if (element instanceof CapabilityRealization) {
			CapabilityRealization currentCapabilityUseCase = (CapabilityRealization) element;
			availableElements.addAll(getRule_MQRY_CapabilityRealizationUseCase_Inherited_11(currentBlockArchitecture, currentCapabilityUseCase, true));
		} else if (element instanceof CapabilityRealizationPkg) {
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
	 * @param blockArchthe system engineering
	 * @param currentCapabilityUseCasethe current capability
	 * @param isFilterRequiredflag for checking with current capability
	 * @return list of {@link CapabilityRealizationUseCase}
	 */
	private List<CapellaElement> getRule_MQRY_CapabilityRealizationUseCase_Inherited_11(BlockArchitecture blockArch,
			CapabilityRealization currentCapabilityUseCase, boolean isFilterRequired) {
		List<CapellaElement> availableElements = new ArrayList<CapellaElement>(1);
		for (CapabilityRealization capabilityUseCase : CapellaElementExt.getAllCapabilityRealization(blockArch)) {
			if (capabilityUseCase instanceof CapabilityRealization) {
				if (isFilterRequired) {
					if (currentCapabilityUseCase.equals(capabilityUseCase)) {
						continue;
					}
					if (!AbstractCapabilityExt.isSuperCapability(currentCapabilityUseCase, capabilityUseCase)
							&& !AbstractCapabilityExt.isSuperCapability(capabilityUseCase, currentCapabilityUseCase)) {
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