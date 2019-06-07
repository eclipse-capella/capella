/*******************************************************************************
 * Copyright (c) 2006, 2019 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.test.diagram.tools.ju.xcbd;

import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;
import org.polarsys.capella.test.diagram.common.ju.context.XBreakdownDiagram;
import org.polarsys.capella.test.diagram.tools.ju.model.EmptyProject;
import org.polarsys.capella.test.framework.context.SessionContext;
import org.polarsys.capella.test.framework.helpers.TestHelper;
import org.polarsys.capella.test.framework.model.GenericModel;

public class LCBDScenario extends EmptyProject {

  @Override
  public void test() throws Exception {
    SessionContext context = new SessionContext(getSession(getRequiredTestModel()));

    XBreakdownDiagram diagram = XBreakdownDiagram.createCBDiagram(context, LA__LOGICAL_SYSTEM);

    diagram.createComponent(GenericModel.LC_1, 1, ICommonConstants.EMPTY_STRING, diagram.getDiagramId(),
        diagram.getDiagramId());
    diagram.createComponent(GenericModel.LC_1_1, 2, ICommonConstants.EMPTY_STRING, GenericModel.LC_1,
        diagram.getDiagramId());
    diagram.createComponent(GenericModel.LC_2, 1, ICommonConstants.EMPTY_STRING, diagram.getDiagramId(),
        diagram.getDiagramId());

    diagram.createCContainedIn(GenericModel.LC_2, GenericModel.LC_1_1);

    diagram.createConstraint(GenericModel.CONSTRAINT_1);
    diagram.createConstrainedElement(GenericModel.CONSTRAINT_1, GenericModel.LC_2);

    diagram.createComponent(GenericModel.LC_3, 1, ICommonConstants.EMPTY_STRING, diagram.getDiagramId(),
        diagram.getDiagramId());

    // Set the Project multi-parts
    TestHelper.setReusableComponents(diagram.getSessionContext().getSemanticElement(GenericModel.LC_3), true);

    diagram.createPart(GenericModel.LC_1, GenericModel.LC_3, diagram.getDiagramId());

    String constraint = diagram.createConstraint(GenericModel.CONSTRAINT_2);
    diagram.removeConstraint(constraint, diagram.getDiagramId());
    diagram.dragAndDropConstraintsFromExplorer(constraint, diagram.getDiagramId());
  }
}