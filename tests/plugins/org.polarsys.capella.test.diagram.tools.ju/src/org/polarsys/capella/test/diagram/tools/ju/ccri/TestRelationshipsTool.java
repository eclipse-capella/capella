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

public class TestRelationshipsTool extends AbstractDiagramTestCase {

  private String PHYSICAL_CCRI_DIAGRAM = "Physical Contextual Capability Realization Involvement Diagram";

  private String relationshipActorID = "891fe554-6197-4aac-875e-c00654c4988f";
  private String relationshipSourceCapabilityID = "5976aae5-748a-44c9-a17f-5c3e49836d17";
  private String relationshipInvolvementID = "f54130b4-ba40-42e2-a3d9-975cab54adb0";

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
        physicalDiagramContext.getDiagramId()).remove(relationshipActorID);

    new InsertRemoveTool(physicalDiagramContext, IToolNameConstants.TOOL_CCRI_INSERT_RELATIONSHIP_EDGE,
        relationshipSourceCapabilityID).insert(relationshipInvolvementID);
  }
}
