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
package org.polarsys.capella.core.business.queries.queries.interaction;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.polarsys.capella.common.queries.AbstractQuery;
import org.polarsys.capella.common.queries.queryContext.IQueryContext;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.interaction.Scenario;
import org.polarsys.capella.core.data.interaction.ScenarioKind;
import org.polarsys.capella.core.data.interaction.ScenarioRealization;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.model.helpers.ScenarioExt;
import org.polarsys.capella.core.model.helpers.SystemEngineeringExt;

public class GetAvailable_Scenario_RealizedScenario extends AbstractQuery {

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
		BlockArchitecture currentBlockArchitecture = SystemEngineeringExt.getRootBlockArchitecture(element);
		if (currentBlockArchitecture != null) {
			List<BlockArchitecture> previousBlockArchitectures = BlockArchitectureExt.getPreviousBlockArchitectures(currentBlockArchitecture);
			if (!previousBlockArchitectures.isEmpty()) {
				availableElements
						.addAll(getElementsFromBlockArchitecture(previousBlockArchitectures.get(previousBlockArchitectures.size() - 1), ((Scenario) element)));
			}
			availableElements.addAll(getElementsFromBlockArchitecture(currentBlockArchitecture, (Scenario) element));
		}
		availableElements.remove(element);
		return availableElements;
	}

	/** 
	 * @param arch
	 * @return
	 */
	private List<CapellaElement> getElementsFromBlockArchitecture(BlockArchitecture arch, Scenario scenario) {
		List<CapellaElement> availableElements = new ArrayList<CapellaElement>();
		ScenarioKind kind = scenario.getKind();
		if (kind == ScenarioKind.UNSET) {
			return availableElements;
		}
		TreeIterator<EObject> allContents = EcoreUtil.getAllContents(arch, true);
		EObject eobject = null;
		while (allContents.hasNext()) {
			eobject = allContents.next();
			if (eobject instanceof Scenario) {
				if (ScenarioExt.canRealize(scenario, (Scenario) eobject)) {
					availableElements.add((CapellaElement) eobject);
				}
			}
		}
		return availableElements;
	}

	/** 
	 * @see org.polarsys.capella.core.business.queries.capellacore.IBusinessQuery#getCurrentElements(EObject,boolean)
	 */
	public List<CapellaElement> getCurrentElements(CapellaElement element, boolean onlyGenerated) {
		List<CapellaElement> currentElements = new ArrayList<CapellaElement>();
		if (element instanceof Scenario) {
			for (ScenarioRealization realization : ((Scenario) element).getOwnedScenarioRealization()) {
				currentElements.add((CapellaElement) realization.getTargetElement());
			}
		}
		return currentElements;
	}

}