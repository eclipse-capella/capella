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

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.business.api.session.Session;
import org.polarsys.capella.core.sirius.analysis.constants.IDNDToolNameConstants;
import org.polarsys.capella.test.diagram.common.ju.api.AbstractDiagramTestCase;
import org.polarsys.capella.test.diagram.common.ju.context.DiagramContext;
import org.polarsys.capella.test.diagram.common.ju.context.XDFBDiagram;
import org.polarsys.capella.test.diagram.common.ju.step.crud.OpenDiagramStep;
import org.polarsys.capella.test.diagram.common.ju.step.tools.DragAndDropFromProjectExplorerTool;
import org.polarsys.capella.test.framework.context.SessionContext;
import org.polarsys.capella.test.framework.model.GenericModel;

public class DragAndDropTest extends AbstractDiagramTestCase {

  private String LOGICAL_CRI_DIAGRAM = "Logical Contextual Capability Realization Involvement Diagram";
  private final String LF_ROOT_FUNCTION = "9b4196d4-9b43-4ea1-8b23-9daa23bbd447";

  @Override
  protected String getRequiredTestModel() {
    return "DiagramAction";
  }

  @Override
  public void test() throws Exception {

    Session session = getSession(getRequiredTestModel());
    SessionContext context = new SessionContext(session);

    DiagramContext logicalDiagramContext = new OpenDiagramStep(context, LOGICAL_CRI_DIAGRAM).run();
    XDFBDiagram xdfb = XDFBDiagram.createDiagram(context, LF_ROOT_FUNCTION);
    String constraint = xdfb.createConstraint(GenericModel.CONSTRAINT_1);
    EObject droppedElementSemantic = context.getSemanticElement(constraint);
    new DragAndDropFromProjectExplorerTool(logicalDiagramContext,
        IDNDToolNameConstants.TOOL_CRI_DND_CONSTRAINT_FROM_EXPLORER, droppedElementSemantic,
        logicalDiagramContext.getDiagramId()).run();
  }
}
