/*******************************************************************************
 * Copyright (c) 2019, 2020 THALES GLOBAL SERVICES.
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
import org.polarsys.capella.core.sirius.analysis.constants.IToolNameConstants;
import org.polarsys.capella.test.diagram.common.ju.api.AbstractDiagramTestCase;
import org.polarsys.capella.test.diagram.common.ju.context.DiagramContext;
import org.polarsys.capella.test.diagram.common.ju.step.crud.OpenDiagramStep;
import org.polarsys.capella.test.diagram.common.ju.step.tools.InsertRemoveTool;
import org.polarsys.capella.test.framework.context.SessionContext;

public class TestInsertTool extends AbstractDiagramTestCase {

  private String PHYSICAL_CCRI_DIAGRAM = "Physical Contextual Capability Realization Involvement Diagram";
  private String LOGICAL_CRI_DIAGRAM = "Logical Contextual Capability Realization Involvement Diagram";

  private final String physicalActorOutsideDiagramID = "64768d41-dd56-471b-b942-1e82a4cc1975";
  private final String physicalComponentOutsideDiagramID = "13c5efcb-bd46-4c4d-a22a-84f31d5dd2fb";
  private final String physicalCapabilityRealizationOutsideDiagramID = "84c42672-f5ad-4cb9-bbd9-4131cf32d267";

  private final String logicalActorOutsideDiagramID = "64768d41-dd56-471b-b942-1e82a4cc1975";
  private final String logicalComponentOutsideDiagramID = "b792ec2b-c8a7-47b6-93f4-e35e5caec06d";
  private final String logicalCapabilityRealizationOutsideDiagramID = "f3a072ae-fdcf-4470-87b0-db28a51a3e16";

  @Override
  protected String getRequiredTestModel() {
    return "DiagramAction";
  }

  @Override
  public void test() throws Exception {

    Session session = getSession(getRequiredTestModel());
    SessionContext context = new SessionContext(session);

    DiagramContext physicalDiagramContext = new OpenDiagramStep(context, PHYSICAL_CCRI_DIAGRAM).run();

    new InsertRemoveTool(physicalDiagramContext, IToolNameConstants.TOOL_CCRI_INSERT_ACTOR_NODE,
        physicalDiagramContext.getDiagramId()).insert(physicalActorOutsideDiagramID);
    new InsertRemoveTool(physicalDiagramContext, IToolNameConstants.TOOL_CCRI_INSERT_COMPONENT_NODE,
        physicalDiagramContext.getDiagramId()).insert(physicalComponentOutsideDiagramID);
    new InsertRemoveTool(physicalDiagramContext, IToolNameConstants.TOOL_CCRI_INSERT_CAPABILITY_REALIZATION_NODE,
        physicalDiagramContext.getDiagramId()).insert(physicalCapabilityRealizationOutsideDiagramID);

    DiagramContext logicalDiagramContext = new OpenDiagramStep(context, LOGICAL_CRI_DIAGRAM).run();

    new InsertRemoveTool(logicalDiagramContext, IToolNameConstants.TOOL_CCRI_INSERT_ACTOR_NODE,
        logicalDiagramContext.getDiagramId()).insert(logicalActorOutsideDiagramID);
    new InsertRemoveTool(logicalDiagramContext, IToolNameConstants.TOOL_CCRI_INSERT_COMPONENT_NODE,
        logicalDiagramContext.getDiagramId()).insert(logicalComponentOutsideDiagramID);
    new InsertRemoveTool(logicalDiagramContext, IToolNameConstants.TOOL_CCRI_INSERT_CAPABILITY_REALIZATION_NODE,
        logicalDiagramContext.getDiagramId()).insert(logicalCapabilityRealizationOutsideDiagramID);
  }

}
