/*******************************************************************************
 * Copyright (c) 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.test.diagram.tools.ju.mcb;

import org.eclipse.sirius.business.api.session.Session;
import org.polarsys.capella.core.sirius.analysis.IDiagramNameConstants;
import org.polarsys.capella.test.diagram.common.ju.context.CapabilityDiagram;
import org.polarsys.capella.test.diagram.common.ju.context.SessionContext;
import org.polarsys.capella.test.diagram.tools.ju.model.EmptyProject;
import org.polarsys.capella.test.diagram.tools.ju.model.GenericModel;

public class OCBScenario extends EmptyProject {

  @Override
  public void test() throws Exception {
    Session session = getSession(getRequiredTestModel());
    SessionContext context = new SessionContext(session);

    CapabilityDiagram diagram = CapabilityDiagram.createDiagram(context, OA__OPERATIONAL_CAPABILITIES,
        IDiagramNameConstants.OPERATIONAL_CAPABILITIES_ENTITYIES_BLANK_DIAGRAM_NAME);

    diagram.createComponent(GenericModel.COMPONENT_2);
    diagram.createComponent(GenericModel.COMPONENT_2_1, GenericModel.COMPONENT_2);
    diagram.createComponent(GenericModel.COMPONENT_3);

    diagram.createActor(GenericModel.ACTOR_5, GenericModel.COMPONENT_2);
    diagram.createActor(GenericModel.ACTOR_6);

    diagram.createCapability(GenericModel.CAPABILITY_1);
    diagram.createCapability(GenericModel.CAPABILITY_2);

    diagram.createComponentExchange(GenericModel.COMPONENT_2, GenericModel.ACTOR_6);
    diagram.createComponentExchange(GenericModel.ACTOR_5, GenericModel.COMPONENT_2_1);
    diagram.createComponentExchange(GenericModel.COMPONENT_2, GenericModel.COMPONENT_3, GenericModel.CL_1);

    diagram.createCapabilityInvolvement(GenericModel.CAPABILITY_1, GenericModel.COMPONENT_3, GenericModel.CL_4);
    diagram.createCapabilityInvolvement(GenericModel.CAPABILITY_1, GenericModel.COMPONENT_2_1, GenericModel.CL_3);
    diagram.createCapabilityInvolvement(GenericModel.CAPABILITY_2, GenericModel.ACTOR_5, GenericModel.CL_3);
    diagram.createCapabilityInvolvement(GenericModel.CAPABILITY_2, GenericModel.ACTOR_6, GenericModel.CL_3);

    diagram.removeComponent(GenericModel.COMPONENT_2_1, GenericModel.COMPONENT_2);
    diagram.insertComponent(GenericModel.COMPONENT_2_1, GenericModel.COMPONENT_2);

    diagram.removeComponent(GenericModel.ACTOR_5, GenericModel.COMPONENT_2);
    diagram.insertComponent(GenericModel.ACTOR_5, GenericModel.COMPONENT_2);

    diagram.removeCapability(GenericModel.CAPABILITY_1);
    diagram.insertCapability(GenericModel.CAPABILITY_1);

    // diagram.deleteFromModel(GenericModel.CL_1)
  }

}