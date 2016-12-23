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
import org.polarsys.capella.test.diagram.tools.ju.model.EmptyProject;
import org.polarsys.capella.test.framework.context.SessionContext;
import org.polarsys.capella.test.framework.model.GenericModel;

/*
 * Test case: Hiding Operational Entity should not hide other Operational Actors
 */
public class OCBHideEntityTestCase extends EmptyProject {

  @Override
  public void test() throws Exception {
    Session session = getSession(getRequiredTestModel());
    SessionContext context = new SessionContext(session);

    CapabilityDiagram diagram = CapabilityDiagram.createDiagram(context, OA__OPERATIONAL_CAPABILITIES,
        IDiagramNameConstants.OPERATIONAL_CAPABILITIES_ENTITYIES_BLANK_DIAGRAM_NAME);
    
    diagram.createActor(GenericModel.ACTOR_1);
    diagram.createComponent(GenericModel.ENTITY_1);
    diagram.removeComponent(GenericModel.ENTITY_1);
    diagram.hasView(GenericModel.ACTOR_1);
  }

}