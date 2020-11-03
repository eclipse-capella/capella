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
package org.polarsys.capella.core.sirius.analysis.actions.extensions;

import java.util.Collection;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.core.data.cs.ExchangeItemAllocation;
import org.polarsys.capella.core.data.information.AbstractEventOperation;
import org.polarsys.capella.core.data.interaction.Scenario;
import org.polarsys.capella.core.data.interaction.ScenarioKind;
import org.polarsys.capella.core.data.interaction.SequenceMessage;
import org.polarsys.capella.core.data.interaction.properties.controllers.DataFlowHelper;
import org.polarsys.capella.core.data.interaction.properties.controllers.InterfaceHelper;
import org.polarsys.capella.core.sirius.analysis.SequenceDiagramServices;
import org.polarsys.capella.core.model.helpers.ComponentExt;
import org.polarsys.capella.core.model.helpers.ScenarioExt;

/**
 * 
 */
public class AffectToMessage2 extends AbstractExternalJavaAction {

	/**
	 * constant for action parameter
	 */
	private static final String PORT_STRATEGY = "portStrategy"; //$NON-NLS-1$
	/**
	 * constant for action parameter
	 */
	private static final String AFFECTED = "affected"; //$NON-NLS-1$

	/**
	 * @see org.eclipse.sirius.tools.api.ui.IExternalJavaAction#execute(java.util.Collection,
	 *      java.util.Map)
	 */
	public void execute(Collection<? extends EObject> selections_p,
			Map<String, Object> parameters_p) {
		SequenceMessage message = (SequenceMessage) parameters_p.get(MESSAGE);
		EObject affectedElement = (EObject) parameters_p.get(AFFECTED);
		Boolean portStrategy = (Boolean) parameters_p.get(PORT_STRATEGY);

		Scenario scenario = (Scenario) message.eContainer();

		if (portStrategy == null)
			portStrategy = Boolean.FALSE;

		if (ScenarioExt.isFunctionalScenario(scenario)) {
			DataFlowHelper.affectDataflowToMessage(message,
					(AbstractEventOperation) affectedElement);
			return;
		}
		switch (scenario.getKind().getValue()) {
		case ScenarioKind.INTERFACE_VALUE:
			Object targetOnExchangeItem = ScenarioExt
					.getTargetOnExchangeItem(parameters_p
							.get(TARGET_ON_EXCHANGE_ITEM));
			InterfaceHelper.affectExchangeItem(message,
					(ExchangeItemAllocation) affectedElement);
			ComponentExt
					.ensureUseAndImplementsForOperation(message,
							(ExchangeItemAllocation) affectedElement,
							portStrategy.booleanValue(),
							(EObject) targetOnExchangeItem);
			break;
		case ScenarioKind.DATA_FLOW_VALUE:
		case ScenarioKind.INTERACTION_VALUE:
			DataFlowHelper.affectDataflowToMessage(message,
					(AbstractEventOperation) affectedElement);
			break;
		}
		if (! ScenarioExt.checkOrdering(scenario)) {
			SequenceDiagramServices.orderingError(scenario);

		}
	}
}
