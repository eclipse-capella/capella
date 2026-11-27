/*******************************************************************************
 * Copyright (c) 2019, 2026 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.diagram.tools.ju.sequence;

import org.polarsys.capella.core.data.interaction.AbstractCapability;
import org.polarsys.capella.core.data.interaction.InteractionPackage;
import org.polarsys.capella.core.data.interaction.Scenario;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.test.diagram.common.ju.context.ESDiagram;
import org.polarsys.capella.test.diagram.common.ju.context.FSDiagram;
import org.polarsys.capella.test.diagram.common.ju.context.SequenceDiagram;
import org.polarsys.capella.test.framework.helpers.SkeletonHelper;

public class CreateReference extends SequenceTest {

	@Override
	public void test() throws Exception {
		BlockArchitectureExt.Type[] types = { BlockArchitectureExt.Type.OA, BlockArchitectureExt.Type.SA,
				BlockArchitectureExt.Type.LA, BlockArchitectureExt.Type.PA };
		BlockArchitectureExt.Type[] typesIS = { BlockArchitectureExt.Type.SA, BlockArchitectureExt.Type.LA,
				BlockArchitectureExt.Type.PA };

		testOnAllLevels(types, SequenceType.ES);
		testOnAllLevels(typesIS, SequenceType.IS);
		testOnAllLevels(types, SequenceType.FS);
	}

	@Override
	public void test(SequenceDiagram diagram) {
		setUpDiagram(diagram);

		// Tool to create a Reference does not allow selecting the current Scenario, so
		// we have to create our own. Let's create it as a sibling.
		final Scenario currentScenario = getScenario(diagram);
		final AbstractCapability capability = (AbstractCapability) currentScenario.eContainer();
		final String scenarioId = "createdScenarioId";
		createScenario(context, scenarioId, capability.getId());
		final Scenario createdScenario = context.getSemanticElement(scenarioId);
		SkeletonHelper.setAttribute(createdScenario, currentScenario.getKind(),
				InteractionPackage.Literals.SCENARIO__KIND, context);

		if (diagram instanceof FSDiagram) {
			diagram.createReference(scenarioId, function1);
			diagram.createReference(scenarioId, function2);
		} else {
			diagram.createReference(scenarioId, actor1);
			diagram.createReference(scenarioId, actor2);

			if (diagram instanceof ESDiagram && diagram.getDiagramBlockArchitecture() != BlockArchitectureExt.Type.SA) {
				diagram.createReference(scenarioId, component1);
			}
		}
	}
}
