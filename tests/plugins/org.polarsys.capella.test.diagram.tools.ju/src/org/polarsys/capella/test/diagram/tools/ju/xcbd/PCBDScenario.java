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
package org.polarsys.capella.test.diagram.tools.ju.xcbd;

import org.polarsys.capella.core.data.pa.PhysicalComponentNature;
import org.polarsys.capella.test.diagram.common.ju.context.SessionContext;
import org.polarsys.capella.test.diagram.common.ju.context.XBreakdownDiagram;
import org.polarsys.capella.test.diagram.tools.ju.model.EmptyProject;
import org.polarsys.capella.test.diagram.tools.ju.model.GenericModel;
import org.polarsys.capella.test.framework.helpers.TestHelper;

public class PCBDScenario extends EmptyProject {

  @Override
  public void test() throws Exception {
    SessionContext context = new SessionContext(getSession(getRequiredTestModel()));

    XBreakdownDiagram diagram = XBreakdownDiagram.createCBDiagram(context, PA__PHYSICAL_SYSTEM);

    diagram.createComponent(GenericModel.PC_1, 1, PhysicalComponentNature.NODE.getLiteral(), diagram.getDiagramId(),
        diagram.getDiagramId());
    diagram.createComponent(GenericModel.PC_1_1, 2, PhysicalComponentNature.NODE.getLiteral(), GenericModel.PC_1,
        diagram.getDiagramId());
    diagram.createComponent(GenericModel.PC_2, 1, PhysicalComponentNature.NODE.getLiteral(), diagram.getDiagramId(),
        diagram.getDiagramId());

    diagram.createCContainedIn(GenericModel.PC_2, GenericModel.PC_1_1);

    diagram.createConstraint(GenericModel.CONSTRAINT_1);
    diagram.createConstrainedElement(GenericModel.CONSTRAINT_1, GenericModel.PC_2);

    diagram.createComponent(GenericModel.PC_3, 1, PhysicalComponentNature.NODE.getLiteral(), diagram.getDiagramId(),
        diagram.getDiagramId());
    diagram.createComponent(GenericModel.PC_4, 1, PhysicalComponentNature.BEHAVIOR.getLiteral(), diagram.getDiagramId(),
        diagram.getDiagramId());

    // Set the Project multi-parts
    TestHelper.setReusableComponents(diagram.getSemanticElement(GenericModel.PC_1), true);

    diagram.createPart(GenericModel.PC_1, GenericModel.PC_3, diagram.getDiagramId());
  }
}