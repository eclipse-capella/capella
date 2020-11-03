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
package org.polarsys.capella.core.business.queries.queries.ctx;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.queries.AbstractQuery;
import org.polarsys.capella.common.queries.queryContext.IQueryContext;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.data.ctx.Capability;
import org.polarsys.capella.core.data.ctx.CapabilityPkg;
import org.polarsys.capella.core.data.helpers.interaction.services.AbstractCapabilityExt;
import org.polarsys.capella.core.model.helpers.CapellaElementExt;
import org.polarsys.capella.core.model.helpers.query.CapellaQueries;
import org.polarsys.capella.core.model.utils.ListExt;

public class GetAvailable_Capability_ExtendedCapabilities extends AbstractQuery {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<Object> execute(Object input, IQueryContext context) {
		CapellaElement capellaElement = (CapellaElement) input;
		List<EObject> availableElements = getAvailableElements(capellaElement);
		return (List) availableElements;
	}

	/** 
	 * <p>
	 * Gets all the capabilities except those that are already
	 * extended/included/inherited by the current capability.
	 * </p>
	 * <p>
	 * Refer MQRY_Capability_Extended_1
	 * </p>
	 * @deprecated
	 * @see org.polarsys.capella.core.business.queries.capellacore.core.business.queries.IBusinessQuery#getAvailableElements(EObject)
	 */
	@Deprecated
	public List<EObject> getAvailableElements(CapellaElement element) {
		List<EObject> availableElements = new ArrayList<EObject>();
		SystemEngineering systemEngineering = CapellaQueries.getInstance().getRootQueries().getSystemEngineering(element);
		if (null == systemEngineering) {
			return availableElements;
		}
		if (element instanceof Capability) {
			Capability currentCapabilityUseCase = (Capability) element;
			availableElements.addAll(getRule_MQRY_Capability_Extended_11(systemEngineering, currentCapabilityUseCase, true));
		} else if (element instanceof CapabilityPkg) {
			availableElements.addAll(getRule_MQRY_Capability_Extended_11(systemEngineering, null, false));
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
	 * Refer MQRY_Capability_Extended_11
	 * </p>
	 * @deprecated
	 * @param sysEngthe system engineering
	 * @param currentCapabilityUseCasethe current capability
	 * @param isFilterRequiredflag for checking with current capability
	 * @return list of {@link CapabilityUseCase}
	 */
	@Deprecated
	private List<CapellaElement> getRule_MQRY_Capability_Extended_11(SystemEngineering sysEng, Capability currentCapabilityUseCase, boolean isFilterRequired) {
		List<CapellaElement> availableElements = new ArrayList<CapellaElement>(1);
		for (Capability capabilityUseCase : CapellaElementExt.getAllCapabilities(sysEng)) {
			if (isFilterRequired) {
				if (currentCapabilityUseCase.equals(capabilityUseCase))
					continue;
				if (!AbstractCapabilityExt.isSuperCapability(currentCapabilityUseCase, capabilityUseCase)
						&& !AbstractCapabilityExt.isSuperCapability(capabilityUseCase, currentCapabilityUseCase)) {
					availableElements.add(capabilityUseCase);
				}
			} else {
				availableElements.add(capabilityUseCase);
			}
		}
		return availableElements;
	}

}