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
package org.polarsys.capella.test.diagram.tools.ju.xab;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.viewpoint.RGBValues;

import org.polarsys.capella.test.diagram.common.ju.context.DiagramContext;
import org.polarsys.capella.test.diagram.common.ju.step.crud.OpenDiagramStep;
import org.polarsys.capella.test.diagram.common.ju.step.tools.DragAndDropTool;
import org.polarsys.capella.test.framework.context.SessionContext;

/*
 * This test case tests the drag and drop of LF from Logical System to LA does not change the shape and the color of LF
 */
public class DragAndDropFunction extends XABDiagramsProject {

  @Override
  public void test() throws Exception {
    Session session = getSession(getRequiredTestModel());
    SessionContext context = new SessionContext(session);
    
    String[] oaIds = {OA__OAB_FUNCTION_2, OA__OAB_FUNCTION_3};
    String[] saIds = {SA__SAB_FUNCTION_1};
    String[] laIds = {LA__LAB_FUNCTION_1, LA__LAB_FUNCTION_3};
    String[] paIds = {PA__PAB_FUNCTION_1, PA__PAB_FUNCTION_3};

    testOnXAB(context, OA__OAB_DIAGRAM, OA__OAB_ENTITY1, oaIds);
    testOnXAB(context, SA__SAB_DIAGRAM, SA__SAB_A2, saIds);
    testOnXAB(context, LA__LAB_DIAGRAM, LA__LAB_A2, laIds);
    testOnXAB(context, PA__PAB_DIAGRAM, PA__PAB_PHYSICAL_ACTOR1, paIds);
  }

  public void testOnXAB(SessionContext context, String diagramName, String containerId, String... ids) {
    DiagramContext diagramContext = new OpenDiagramStep(context, diagramName).run();

    for (String id : ids) {
      // Casting to avoid The method getView(String) is ambiguous for the type DiagramContext
      DNode beforeDnDLFNode = (DNode) diagramContext.getView((EObject) context.getSemanticElement(id));

      RGBValues beforeDnDRgb = beforeDnDLFNode.getOwnedStyle().getBorderColor();
      int beforeDnDRed = beforeDnDRgb.getRed();
      int beforeDnDGreen = beforeDnDRgb.getGreen();
      int beforeDnDBlue = beforeDnDRgb.getBlue();

      int beforeDnDHeight = beforeDnDLFNode.getHeight();
      int beforeDnDWidth = beforeDnDLFNode.getWidth();

      new DragAndDropTool(diagramContext, "DnD AB AbstractFunction Allocation", id,
          containerId).run();
      // Casting to avoid The method getView(String) is ambiguous for the type DiagramContext
      DNode afterDnDLFNode = (DNode) diagramContext.getView((EObject) context.getSemanticElement(id));
      RGBValues afterDnDRgb = afterDnDLFNode.getOwnedStyle().getBorderColor();
      int afterDnDRed = afterDnDRgb.getRed();
      int afterDnDGreen = afterDnDRgb.getGreen();
      int afterDnDBlue = afterDnDRgb.getBlue();
      int afterDnDHeight = afterDnDLFNode.getHeight();
      int afterDnDWidth = afterDnDLFNode.getWidth();

      assertTrue("LF must have the same size after the DnD from Logical System to LA",
          beforeDnDHeight == afterDnDHeight && beforeDnDWidth == afterDnDWidth);
      assertTrue("LF must have the same color after the DnD from Logical System to LA",
          beforeDnDRed == afterDnDRed && beforeDnDGreen == afterDnDGreen && beforeDnDBlue == afterDnDBlue);
    }
  }
}
