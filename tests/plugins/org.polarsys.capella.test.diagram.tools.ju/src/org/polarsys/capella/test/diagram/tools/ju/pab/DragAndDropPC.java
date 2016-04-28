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

import junit.framework.Test;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.business.api.session.Session;
import org.polarsys.capella.test.diagram.common.ju.api.AbstractDiagramTestCase;
import org.polarsys.capella.test.diagram.common.ju.context.DiagramContext;
import org.polarsys.capella.test.diagram.common.ju.context.SessionContext;
import org.polarsys.capella.test.diagram.common.ju.step.crud.OpenDiagramStep;
import org.polarsys.capella.test.diagram.common.ju.step.tools.DragAndDropFromProjectExplorerTool;

/*
 * This test case tests the drag and drop of LCs (PC 1, PC 2, PC 11) from Project Explorer to a diagram created from a PC (PC 11)
 */
public class DragAndDropPC extends AbstractDiagramTestCase {
  public static String PC_1_PAB_DIAGRAM = "[PAB] PC 1 - Physical Architecture Blank"; //$NON-NLS-1$
  public static String PA__PART_PC_1__PC_1 = "fee669f6-d5ea-492c-afd7-e4ca7d419193"; //$NON-NLS-1$
  public static String PA__PART_PC_2__PC_2 = "a6991f53-69f1-44f9-a294-6a83c9239670"; //$NON-NLS-1$ 
  public static String PA__PC_1__PART_PC_11__PC_11 = "3e09cbe9-01ff-4135-bdb2-e94d357bd455"; //$NON-NLS-1$
  public static String PA__PC_1 = "PC 1"; //$NON-NLS-1$

  @Override
  public void test() throws Exception {
    Session session = getSession(getRequiredTestModel());
    SessionContext context = new SessionContext(session);
    DiagramContext diagramContext = new OpenDiagramStep(context, PC_1_PAB_DIAGRAM).run();

    EObject pc1 = context.getSemanticElement(PA__PART_PC_1__PC_1);
    EObject pc2 = context.getSemanticElement(PA__PART_PC_2__PC_2);
    EObject pc11 = context.getSemanticElement(PA__PC_1__PART_PC_11__PC_11);

    new DragAndDropFromProjectExplorerTool(diagramContext, "D&D Components From Project Explorer", pc1,
        PC_1_PAB_DIAGRAM).run();
    new DragAndDropFromProjectExplorerTool(diagramContext, "D&D Components From Project Explorer", pc2,
        PC_1_PAB_DIAGRAM).run();
    new DragAndDropFromProjectExplorerTool(diagramContext, "D&D Components From Project Explorer", pc11, PA__PC_1)
        .run();
  }

  public static Test suite() {
    return new DragAndDropPC();
  }

  @Override
  protected String getRequiredTestModel() {
    return "PABDiagramModel";
  }
}
