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
package org.polarsys.capella.test.diagram.tools.ju.xfbd;

import org.polarsys.capella.core.data.fa.FunctionKind;
import org.polarsys.capella.test.diagram.common.ju.context.XBreakdownDiagram;
import org.polarsys.capella.test.diagram.tools.ju.model.EmptyProject;
import org.polarsys.capella.test.framework.context.SessionContext;
import org.polarsys.capella.test.framework.model.GenericModel;

public class SFBDScenario extends EmptyProject {

  @Override
  public void test() throws Exception {
    SessionContext context = new SessionContext(getSession(getRequiredTestModel()));

    XBreakdownDiagram diagram = XBreakdownDiagram.createFBDiagram(context, SA__ROOT_SF);

    diagram.createFunction(GenericModel.FUNCTION_1, 1, FunctionKind.FUNCTION.getLiteral(), diagram.getDiagramId(),
        diagram.getDiagramId());
    diagram.createFunction(GenericModel.FUNCTION_1_1, 2, FunctionKind.FUNCTION.getLiteral(), GenericModel.FUNCTION_1,
        diagram.getDiagramId());
    diagram.createFunction(GenericModel.FUNCTION_2, 1, FunctionKind.FUNCTION.getLiteral(), diagram.getDiagramId(),
        diagram.getDiagramId());

    diagram.createFContainedIn(GenericModel.FUNCTION_2, GenericModel.FUNCTION_1_1);

    diagram.createConstraint(GenericModel.CONSTRAINT_1);
    diagram.createConstrainedElement(GenericModel.CONSTRAINT_1, GenericModel.FUNCTION_2);

    diagram.createFunction(GenericModel.FUNCTION_3, 1, FunctionKind.DUPLICATE.getLiteral(), diagram.getDiagramId(),
        diagram.getDiagramId());
    diagram.createFunction(GenericModel.FUNCTION_4, 1, FunctionKind.GATHER.getLiteral(), diagram.getDiagramId(),
        diagram.getDiagramId());
    diagram.createFunction(GenericModel.FUNCTION_5, 1, FunctionKind.ROUTE.getLiteral(), diagram.getDiagramId(),
        diagram.getDiagramId());
    diagram.createFunction(GenericModel.FUNCTION_6, 1, FunctionKind.SELECT.getLiteral(), diagram.getDiagramId(),
        diagram.getDiagramId());
    diagram.createFunction(GenericModel.FUNCTION_7, 1, FunctionKind.SPLIT.getLiteral(), diagram.getDiagramId(),
        diagram.getDiagramId());

    String constraint = diagram.createConstraint(GenericModel.CONSTRAINT_2);
    diagram.removeConstraint(constraint, diagram.getDiagramId());
    diagram.dragAndDropConstraintsFromExplorer(constraint, diagram.getDiagramId());
  }
}