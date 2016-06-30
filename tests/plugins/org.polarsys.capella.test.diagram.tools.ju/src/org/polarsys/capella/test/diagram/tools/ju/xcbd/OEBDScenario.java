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

import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;
import org.polarsys.capella.core.data.oa.OaPackage;
import org.polarsys.capella.test.diagram.common.ju.context.SessionContext;
import org.polarsys.capella.test.diagram.common.ju.context.XABDiagram;
import org.polarsys.capella.test.diagram.common.ju.context.XBreakdownDiagram;
import org.polarsys.capella.test.diagram.tools.ju.model.EmptyProject;
import org.polarsys.capella.test.diagram.tools.ju.model.GenericModel;

public class OEBDScenario extends EmptyProject {

  @Override
  public void test() throws Exception {
    SessionContext context = new SessionContext(getSession(getRequiredTestModel()));

    XABDiagram archBlank = XABDiagram.createDiagram(context, OA__OPERATIONAL_CONTEXT);
    archBlank.createComponent(GenericModel.ENTITY_1, archBlank.getDiagramId());

    XBreakdownDiagram diagram = XBreakdownDiagram.createCBDiagram(context, GenericModel.ENTITY_1);

    diagram.createComponent(GenericModel.ENTITY_1_1, 2, ICommonConstants.EMPTY_STRING, GenericModel.ENTITY_1, diagram.getDiagramId());
    diagram.createComponent(GenericModel.ENTITY_2, 2, ICommonConstants.EMPTY_STRING, diagram.getDiagramId(), diagram.getDiagramId());

    diagram.createComponent(GenericModel.ACTOR_1, 2, OaPackage.Literals.OPERATIONAL_ACTOR.getName(), diagram.getDiagramId(), diagram.getDiagramId());

    diagram.createCContainedIn(GenericModel.ENTITY_2, GenericModel.ENTITY_1_1);

    diagram.createConstraint(GenericModel.CONSTRAINT_1);
    diagram.createConstrainedElement(GenericModel.CONSTRAINT_1, GenericModel.ENTITY_2);
  }
}