/*******************************************************************************
 * Copyright (c) 2019 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.diagram.tools.ju.msm;

import org.eclipse.sirius.business.api.session.Session;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt.Type;
import org.polarsys.capella.test.diagram.common.ju.context.MSMDiagram;
import org.polarsys.capella.test.diagram.tools.ju.model.DiagramToolsModel;
import org.polarsys.capella.test.framework.context.SessionContext;
import org.polarsys.capella.test.framework.model.GenericModel;

public class MSMCreateConstrainedElement extends DiagramToolsModel {

  private final String diagramName = "SA Mode State Machine Test Diagram";

  private final String mode2Id = "9a8b5522-5ee7-4d4e-8916-485b93daebcd";
  private final String transitionMode2FinalPseudoState4Id = "67208dd6-51a1-4fd6-ad68-ff03215a1e8c";
  private final String finalPseudoState4Id = "5ef6f21a-533b-448e-8a83-b343e974b3f2";

  @Override
  public void test() throws Exception {

    Session session = getSession(getRequiredTestModel());
    SessionContext context = new SessionContext(session);

    MSMDiagram diagram = MSMDiagram.openDiagram(context, this.diagramName, Type.SA);

    diagram.createConstraint(GenericModel.CONSTRAINT_1);

    diagram.createConstrainedElement(GenericModel.CONSTRAINT_1, mode2Id);
    diagram.createConstrainedElement(GenericModel.CONSTRAINT_1, transitionMode2FinalPseudoState4Id);
    diagram.createConstrainedElement(GenericModel.CONSTRAINT_1, finalPseudoState4Id);
  }
}
