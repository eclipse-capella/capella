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

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.polarsys.capella.common.queries.AbstractQuery;
import org.polarsys.capella.common.queries.queryContext.IQueryContext;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.epbs.ConfigurationItem;
import org.polarsys.capella.core.data.epbs.EPBSArchitecture;
import org.polarsys.capella.core.data.pa.PhysicalArchitecture;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.model.helpers.SystemEngineeringExt;
import org.polarsys.capella.core.model.helpers.query.CapellaQueries;
import org.polarsys.capella.core.model.utils.ListExt;

public class GetAvailable_ConfigurationItem_RealizedPhysicalArtifacts extends AbstractQuery {

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
			availableElements.addAll(getRule_MQRY_ConfigurationItem_ImplementedPC_11(currentCI, systemEngineering));
			availableElements.addAll(getRule_MQRY_ConfigurationItem_ImplementedPLink_11(currentCI));
			availableElements.addAll(getRule_MQRY_ConfigurationItem_ImplementedPPort_11(currentCI));
		}
		availableElements = ListExt.removeDuplicates(availableElements);
		return availableElements;
	}

	/** 
	 * Gets all the PhysicalComponents contained in the Physical Architecture Layer. Except those that are already implemented by a ConfigurationItem (unique
	 * implementor)
	 */
	private List<CapellaElement> getRule_MQRY_ConfigurationItem_ImplementedPC_11(ConfigurationItem currentConfigurationItem,
			SystemEngineering systemEngineering) {
		List<CapellaElement> availableElements = new ArrayList<CapellaElement>(1);
		
		PhysicalArchitecture physicalArch = SystemEngineeringExt.getOwnedPhysicalArchitecture(systemEngineering);
		if (null != physicalArch) {
		  availableElements.addAll(BlockArchitectureExt.getAllComponents(physicalArch));
		}
		return availableElements;
	}

	/** 
	 */
	private List<CapellaElement> getRule_MQRY_ConfigurationItem_ImplementedPLink_11(ConfigurationItem configurationItem) {
		return getElementsFromBlockArchitecture(configurationItem, CsPackage.Literals.PHYSICAL_LINK);
	}

	/** 
	 */
	private List<CapellaElement> getRule_MQRY_ConfigurationItem_ImplementedPPort_11(ConfigurationItem configurationItem) {
		return getElementsFromBlockArchitecture(configurationItem, CsPackage.Literals.PHYSICAL_PORT);
	}

	/** 
	 */
	private List<CapellaElement> getElementsFromBlockArchitecture(ConfigurationItem configurationItem, EClass cls) {
		List<CapellaElement> availableElements = new ArrayList<CapellaElement>(1);
		EPBSArchitecture arch = (EPBSArchitecture) SystemEngineeringExt.getRootBlockArchitecture(configurationItem);
		if (arch != null) {
			for (BlockArchitecture block : arch.getAllocatedArchitectures()) {
				TreeIterator<Object> allContents = EcoreUtil.getAllContents(block, false);
				while (allContents.hasNext()) {
					EObject object = (EObject) allContents.next();
					if (object.eClass().equals(cls)) {
							availableElements.add((CapellaElement) object);
					}
				}
			}
		}
		return availableElements;
	}

}