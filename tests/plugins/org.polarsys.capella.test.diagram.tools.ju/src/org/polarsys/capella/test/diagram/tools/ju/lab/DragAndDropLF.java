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
package org.polarsys.capella.test.diagram.tools.ju.lab;

import junit.framework.Test;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.viewpoint.RGBValues;
import org.polarsys.capella.test.diagram.common.ju.api.AbstractDiagramTestCase;
import org.polarsys.capella.test.diagram.common.ju.context.DiagramContext;
import org.polarsys.capella.test.diagram.common.ju.step.crud.OpenDiagramStep;
import org.polarsys.capella.test.diagram.common.ju.step.tools.DragAndDropTool;
import org.polarsys.capella.test.framework.context.SessionContext;

/*
 * This test case tests the drag and drop of LF from Logical System to LA does not change the shape and the color of LF
 */
public class DragAndDropLF extends AbstractDiagramTestCase {
  public static String LAB_DIAGRAM = "[LAB] Diagram for testing DnD"; //$NON-NLS-1$
  public static String LA__ROOT_LF__LF_5 = "21bcc81f-9f70-4a32-8b64-534dad7cdb9e"; //$NON-NLS-1$
  public static String LA__LOGICAL_CONTEXT__PART_LA_2__LA_2 = "9aa3bcbd-2c7d-486e-aaa0-56053dc0bdab"; //$NON-NLS-1$ 

  @Override
  public void test() throws Exception {
    Session session = getSession(getRequiredTestModel());
    SessionContext context = new SessionContext(session);

    DiagramContext diagramContext = new OpenDiagramStep(context, LAB_DIAGRAM).run();
    // Casting to avoid The method getView(String) is ambiguous for the type DiagramContext
    DNode beforeDnDLFNode = (DNode) diagramContext.getView((EObject) context.getSemanticElement(LA__ROOT_LF__LF_5));

    RGBValues beforeDnDRgb = beforeDnDLFNode.getOwnedStyle().getBorderColor();
    int beforeDnDRed = beforeDnDRgb.getRed();
    int beforeDnDGreen = beforeDnDRgb.getGreen();
    int beforeDnDBlue = beforeDnDRgb.getBlue();
    
    int beforeDnDHeight = beforeDnDLFNode.getHeight();
    int beforeDnDWidth = beforeDnDLFNode.getWidth();

    new DragAndDropTool(diagramContext, "DnD AB AbstractFunction Allocation", LA__ROOT_LF__LF_5,
        LA__LOGICAL_CONTEXT__PART_LA_2__LA_2).run();
    // Casting to avoid The method getView(String) is ambiguous for the type DiagramContext
    DNode afterDnDLFNode = (DNode) diagramContext.getView((EObject) context.getSemanticElement(LA__ROOT_LF__LF_5));
    RGBValues afterDnDRgb = afterDnDLFNode.getOwnedStyle().getBorderColor();
    int afterDnDRed = afterDnDRgb.getRed();
    int afterDnDGreen = afterDnDRgb.getGreen();
    int afterDnDBlue = afterDnDRgb.getBlue();
    int afterDnDHeight = afterDnDLFNode.getHeight();
    int afterDnDWidth = afterDnDLFNode.getWidth();

    assertTrue("LF must have the same size after the DnD from Logical System to LA", beforeDnDHeight == afterDnDHeight
        && beforeDnDWidth == afterDnDWidth);
    assertTrue("LF must have the same color after the DnD from Logical System to LA", beforeDnDRed == afterDnDRed
        && beforeDnDGreen == afterDnDGreen && beforeDnDBlue == afterDnDBlue);
    
  }

  public static Test suite() {
    return new DragAndDropLF();
  }

  @Override
  protected String getRequiredTestModel() {
    return "SwitchCategory";
  }
}
