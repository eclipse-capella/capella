/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.test.diagram.tools.ju.pab;

import java.util.List;

import junit.framework.Assert;
import junit.framework.Test;

import org.eclipse.osgi.util.NLS;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.business.api.componentization.DiagramComponentizationManager;
import org.eclipse.sirius.viewpoint.description.tool.AbstractToolDescription;
import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.core.sirius.analysis.constants.IToolNameConstants;
import org.polarsys.capella.test.diagram.common.ju.api.AbstractDiagramTestCase;
import org.polarsys.capella.test.diagram.common.ju.context.DiagramContext;
import org.polarsys.capella.test.diagram.common.ju.context.SessionContext;
import org.polarsys.capella.test.diagram.common.ju.step.crud.OpenDiagramStep;
import org.polarsys.capella.test.diagram.common.ju.step.tools.DeleteElementTool;
import org.polarsys.capella.test.diagram.common.ju.step.tools.InsertRemoveContainerCreation;
import org.polarsys.capella.test.diagram.common.ju.wrapper.Messages;
import org.polarsys.capella.test.diagram.common.ju.wrapper.factory.ToolWrapperFactory;
import org.polarsys.capella.test.diagram.common.ju.wrapper.utils.DiagramHelper;
import org.polarsys.capella.test.diagram.common.ju.wrapper.utils.ToolHelper;
import org.polarsys.kitalpha.emde.model.Element;

/**
 * Test the deletion of a constraint link in PAB diagram
 */
public class DeleteConstraintLinkTestCase extends AbstractDiagramTestCase {
  public static String CONSTRAINT_LINK = "4e13c35a-a2f6-48fa-9e96-cf24ac1b1b61";
  public static String PAB_DIAGRAM = "[PAB] Physical System - Physical Architecture Blank";
  public static String CDB_DIAGRAM = "[CDB] Data - Class Diagram Blank";

  @Override
  public void test() throws Exception {
    Session session = getSessionForTestModel(getRequiredTestModel());
    SessionContext context = new SessionContext(session);

    final DiagramContext diagramContext = new OpenDiagramStep(context, PAB_DIAGRAM).run();
    final DiagramContext cdbDiagramContext = new OpenDiagramStep(context, CDB_DIAGRAM).run();

    List<DEdge> edgeList = DiagramHelper.getEdges(diagramContext.getDiagram(), CONSTRAINT_LINK);
    for (DEdge edge : edgeList) {
      new DeleteElementTool(diagramContext, cdbDiagramContext, IToolNameConstants.TOOL_CDB_DELETE_CONSTRAINT_ELEMENT)
          .delete(edge);
    }

    edgeList = DiagramHelper.getEdges(diagramContext.getDiagram(), CONSTRAINT_LINK);
    assertTrue("Constraint link is not removed from the diagram after a deletion", edgeList.size() == 0);
  }

  public static Test suite() {
    return new DeleteConstraintLinkTestCase();
  }

  @Override
  protected String getRequiredTestModel() {
    return "PABDiagramModel";
  }
}
