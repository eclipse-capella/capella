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
package org.polarsys.capella.test.diagram.tools.ju.es;

import org.eclipse.sirius.business.api.session.Session;
import org.polarsys.capella.core.data.pa.PhysicalComponentNature;
import org.polarsys.capella.test.diagram.common.ju.context.ESDiagram;
import org.polarsys.capella.test.diagram.common.ju.context.PA_ESDiagram;
import org.polarsys.capella.test.diagram.common.ju.context.SA_ESDiagram;
import org.polarsys.capella.test.diagram.tools.ju.model.ESProject;
import org.polarsys.capella.test.framework.context.SessionContext;
import org.polarsys.capella.test.framework.model.GenericModel;

/**
 * Test for each architecture phases some basic insert remove on ES diagrams
 */
public class InsertRemoveScenario extends ESProject {

  @Override
  public void test() throws Exception {

    Session session = getSession(getRequiredTestModel());
    SessionContext context = new SessionContext(session);

    testOnESDiagram(context, OA__OPERATIONAL_CAPABILITIES__OPERATIONALCAPABILITY_1__OES_SCENARIO_1);
    testOnESDiagram(context, SA__CAPABILITIES__CAPABILITY_1__ES_SCENARIO_1);
    testOnESDiagram(context, LA__CAPABILITIES__CAPABILITYREALIZATION_1__ES_SCENARIO_1);
    testOnESDiagram(context, PA__CAPABILITIES__CAPABILITYREALIZATION_1__ES_SCENARIO_1);
  }

  protected void testOnESDiagram(SessionContext context, String sourceId) {

    ESDiagram diagramContext = ESDiagram.createDiagram(context, sourceId);

    diagramContext.createActor(GenericModel.ACTOR_1);

    // Actor
    diagramContext.removeActor(GenericModel.ACTOR_1);
    diagramContext.insertActor(GenericModel.ACTOR_1);

    if (diagramContext instanceof PA_ESDiagram) {

    //Insert/Remove tool does not exist for SA
      ((PA_ESDiagram) diagramContext).createComponent(GenericModel.COMPONENT_1, PhysicalComponentNature.BEHAVIOR);
      ((PA_ESDiagram) diagramContext).createComponent(GenericModel.COMPONENT_2, PhysicalComponentNature.NODE);

      // Behavior component
      diagramContext.removeComponent(GenericModel.COMPONENT_1);
      diagramContext.insertComponent(GenericModel.COMPONENT_1);

      // Node component
      diagramContext.removeComponent(GenericModel.COMPONENT_2);
      diagramContext.insertComponent(GenericModel.COMPONENT_2);     

    } else if (!(diagramContext instanceof SA_ESDiagram)) {

      diagramContext.createComponent(GenericModel.COMPONENT_1);

      // Insert/Remove tool does not exist for SA (but maybe it should)
      diagramContext.removeComponent(GenericModel.COMPONENT_1);
      diagramContext.insertComponent(GenericModel.COMPONENT_1);
    }
  }
}
