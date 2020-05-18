/*******************************************************************************
 * Copyright (c) 2019 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.diagram.tools.ju.ccri;

import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.polarsys.capella.common.ef.ExecutionManager;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.core.data.capellacore.NamedElement;
import org.polarsys.capella.core.sirius.analysis.constants.IToolNameConstants;
import org.polarsys.capella.test.diagram.common.ju.api.AbstractDiagramTestCase;
import org.polarsys.capella.test.diagram.common.ju.context.DiagramContext;
import org.polarsys.capella.test.diagram.common.ju.step.crud.OpenDiagramStep;
import org.polarsys.capella.test.diagram.common.ju.step.tools.CreateEdgeTool;
import org.polarsys.capella.test.diagram.common.ju.step.tools.CreateNodeTool;
import org.polarsys.capella.test.framework.context.SessionContext;

public class CreateEdges extends AbstractDiagramTestCase {

  private String PHYSICAL_CCRI_DIAGRAM = "Physical Contextual Capability Realization Involvement Diagram";
  private String LOGICAL_CRI_DIAGRAM = "Logical Contextual Capability Realization Involvement Diagram";

  private String mainPhysicalCapabilityRealizationID = "a75a0f75-102e-464d-8ea3-c846002da06b";
  private String mainLogicalCapabilityRealizationID = "2e82c298-dfdd-4a30-9bd7-6d50ecab3df7";

  private String createdPhysicalActorNameAndID = "NewPhysicalActor";
  private String createdLogicalActorNameAndID = "NewLogicalActor";

  private String createdPhysicalComponentNameAndID = "NewPhysicalComponent";
  private String createdLogicalComponentNameAndID = "NewLogicalComponent";

  private void setName(ExecutionManager executionManager, NamedElement object, String newName) {
    final String finalNewName = newName;
    final NamedElement finalObject = object;
    executionManager.execute(new AbstractReadWriteCommand() {
      public void run() {
        finalObject.setName(finalNewName);
      }
    });
  }

  private void createElementAndSetName(DiagramContext diagramContext, String toolName, String elementNameAndID) {

    DDiagramElement diagramElement = new CreateNodeTool(diagramContext, toolName, diagramContext.getDiagramId(),
        elementNameAndID).run();
    ExecutionManager executionManager = TransactionHelper.getExecutionManager(diagramElement.getTarget());
    setName(executionManager, (NamedElement) diagramElement.getTarget(), elementNameAndID);
  }

  @Override
  protected String getRequiredTestModel() {
    return "DiagramAction";
  }

  @Override
  public void test() throws Exception {

    Session session = getSession(getRequiredTestModel());
    SessionContext context = new SessionContext(session);

    // Create an Actor and a Component to serve as targets for the edges in both
    // diagrams
    DiagramContext physicalDiagramContext = new OpenDiagramStep(context, PHYSICAL_CCRI_DIAGRAM).run();

    createElementAndSetName(physicalDiagramContext, IToolNameConstants.TOOL_CCRI_CREATE_ACTOR_NODE,
        createdPhysicalActorNameAndID);
    createElementAndSetName(physicalDiagramContext, IToolNameConstants.TOOL_CCRI_CREATE_COMPONENT_NODE,
        createdPhysicalComponentNameAndID);

    new CreateEdgeTool(physicalDiagramContext,
        IToolNameConstants.TOOL_CCRI_CREATE_CAPABILITY_REALIZATION_INVOLVEMENT_EDGE, "INV 1",
        mainPhysicalCapabilityRealizationID, createdPhysicalActorNameAndID, 1).run();
    new CreateEdgeTool(physicalDiagramContext,
        IToolNameConstants.TOOL_CCRI_CREATE_CAPABILITY_REALIZATION_INVOLVEMENT_EDGE, "INV 2",
        mainPhysicalCapabilityRealizationID, createdPhysicalComponentNameAndID, 1).run();

    // Same for the Logical level diagram
    DiagramContext logicalDiagramContext = new OpenDiagramStep(context, LOGICAL_CRI_DIAGRAM).run();

    createElementAndSetName(logicalDiagramContext, IToolNameConstants.TOOL_CCRI_CREATE_ACTOR_NODE,
        createdLogicalActorNameAndID);
    createElementAndSetName(logicalDiagramContext, IToolNameConstants.TOOL_CCRI_CREATE_COMPONENT_NODE,
        createdLogicalComponentNameAndID);

    new CreateEdgeTool(logicalDiagramContext,
        IToolNameConstants.TOOL_CCRI_CREATE_CAPABILITY_REALIZATION_INVOLVEMENT_EDGE, "INV 1",
        mainLogicalCapabilityRealizationID, createdLogicalActorNameAndID, 1).run();
    new CreateEdgeTool(logicalDiagramContext,
        IToolNameConstants.TOOL_CCRI_CREATE_CAPABILITY_REALIZATION_INVOLVEMENT_EDGE, "INV 2",
        mainLogicalCapabilityRealizationID, createdLogicalComponentNameAndID, 1).run();
  }

}
