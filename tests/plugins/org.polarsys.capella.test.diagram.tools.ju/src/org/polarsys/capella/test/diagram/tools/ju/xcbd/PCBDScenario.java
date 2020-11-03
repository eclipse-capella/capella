/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.diagram.tools.ju.xcbd;

import org.polarsys.capella.core.data.pa.PhysicalComponentNature;
import org.polarsys.capella.test.diagram.common.ju.context.XBreakdownDiagram;
import org.polarsys.capella.test.diagram.tools.ju.model.EmptyProject;
import org.polarsys.capella.test.framework.context.SessionContext;
import org.polarsys.capella.test.framework.helpers.SkeletonHelper;
import org.polarsys.capella.test.framework.helpers.TestHelper;
import org.polarsys.capella.test.framework.model.GenericModel;

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

    actorsTestScenarios(context);
    // Set the Project multi-parts
    TestHelper.setReusableComponents(diagram.getSessionContext().getSemanticElement(GenericModel.PC_1), true);

    diagram.createPart(GenericModel.PC_1, GenericModel.PC_3, diagram.getDiagramId());

    String constraint = diagram.createConstraint(GenericModel.CONSTRAINT_2);
    diagram.removeConstraint(constraint, diagram.getDiagramId());
    diagram.dragAndDropConstraintsFromExplorer(constraint, diagram.getDiagramId());

  }

  public void actorsTestScenarios(SessionContext context) {
    SkeletonHelper.createComponentPkg(PA__PHYSICAL_SYSTEM, "PKG1", context);
    SkeletonHelper.createComponentPkg("PKG1", "PKG1_1", context);
    
    actorsTestScenario(context, PA__PHYSICAL_SYSTEM);
    actorsTestScenario(context, "PKG1");
    actorsTestScenario(context, "PKG1_1");

  }

  public void actorsTestScenario(SessionContext context, String diagramContainerId) {
    XBreakdownDiagram diagram = XBreakdownDiagram.createCBDiagram(context, diagramContainerId);
    diagram.createActor(GenericModel.PA_1, diagram.getDiagramId(), false);
    diagram.createActor(GenericModel.PA_2, diagram.getDiagramId(), true);
    diagram.createActor(GenericModel.PA_1_1, diagram.getDiagramId(), true);
    diagram.createActor(GenericModel.PA_3, diagram.getDiagramId(), false);
    diagram.createActor(GenericModel.PA_4, diagram.getDiagramId(), true);

    diagram.createComponent(GenericModel.PC_1, 1, PhysicalComponentNature.NODE.getLiteral(), diagram.getDiagramId(),
        diagram.getDiagramId());
    diagram.createComponent(GenericModel.PC_2, 1, PhysicalComponentNature.BEHAVIOR.getLiteral(), diagram.getDiagramId(),
        diagram.getDiagramId());

    diagram.createCContainedIn(GenericModel.PA_1_1, GenericModel.PA_1);
    diagram.createCContainedIn(GenericModel.PC_1, GenericModel.PA_1);    

    diagram.createCContainedIn(GenericModel.PA_3, GenericModel.PC_1);    

    diagram.createCContainedIn(GenericModel.PA_4, GenericModel.PC_1);    

  }

}