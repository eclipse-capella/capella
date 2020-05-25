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
import org.polarsys.capella.core.data.interaction.InteractionUse;
import org.polarsys.capella.core.data.interaction.Scenario;
import org.polarsys.capella.core.data.interaction.ScenarioKind;
import org.polarsys.capella.core.model.helpers.SystemEngineeringExt;

public class GetAvailable_InteractionUse_ReferencedScenario extends AbstractQuery {

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
		Scenario scenario = getAvailableRelatedScenario(element);
		BlockArchitecture currentBlockArchitecture = SystemEngineeringExt.getRootBlockArchitecture(element);
		if (currentBlockArchitecture != null && scenario != null) {
			availableElements.addAll(getElementsFromBlockArchitecture(currentBlockArchitecture, scenario.getKind()));
			availableElements.remove(scenario);
		}
		return availableElements;
	}

	protected Scenario getAvailableRelatedScenario(CapellaElement element) {
		if (element instanceof InteractionUse) {
			return (Scenario) ((InteractionUse) element).eContainer();
		} else if (element instanceof Scenario) {
			return (Scenario) element;
		}
		return null;
	}

	/** 
	 * @param arch
	 * @return
	 */
	private List<CapellaElement> getElementsFromBlockArchitecture(BlockArchitecture arch, ScenarioKind kind) {
		List<CapellaElement> availableElements = new ArrayList<CapellaElement>();
		TreeIterator<Object> allContents = EcoreUtil.getAllContents(arch, true);
		while (allContents.hasNext()) {
			Object object = allContents.next();
			if ((object instanceof Scenario) && ((Scenario) object).getKind().equals(kind)) {
				availableElements.add((CapellaElement) object);
			}
		}
		return availableElements;
	}

	/** 
	 * @see org.polarsys.capella.core.business.queries.capellacore.IBusinessQuery#getCurrentElements(EObject,boolean)
	 */
	public List<CapellaElement> getCurrentElements(CapellaElement element, boolean onlyGenerated) {
		List<CapellaElement> currentElements = new ArrayList<CapellaElement>();
		Scenario scenario = getCurrentRelatedScenario(element);
		if (scenario != null) {
			currentElements.add(scenario);
		}
		return currentElements;
	}

	protected Scenario getCurrentRelatedScenario(CapellaElement element) {
		if (element instanceof InteractionUse) {
			return ((InteractionUse) element).getReferencedScenario();
		} else if (element instanceof Scenario) {
			return (Scenario) element;
		}
		return null;
	}

}