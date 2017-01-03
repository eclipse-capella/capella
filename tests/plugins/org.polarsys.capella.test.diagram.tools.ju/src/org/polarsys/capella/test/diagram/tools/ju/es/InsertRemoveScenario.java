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

import junit.framework.Test;

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

    // OperationalAnalysis
    createOperationalAnalysis(context);
    testOn(context, OA__OPERATIONAL_CAPABILITIES__OPERATIONALCAPABILITY_1__OES_SCENARIO_1);

    // SystemAnalysis
    createSystemAnalysis(context);
    testOn(context, SA__CAPABILITIES__CAPABILITY_1__ES_SCENARIO_1);

    // LogicalArchitecture
    createLogicalArchitecture(context);
    testOn(context, LA__CAPABILITIES__CAPABILITYREALIZATION_1__ES_SCENARIO_1);

    // PhysicalArchitecture
    createPhysicalArchitecture(context);
    testOn(context, PA__CAPABILITIES__CAPABILITYREALIZATION_1__ES_SCENARIO_1);

  }

  protected void testOn(SessionContext context, String sourceId) {
    ESDiagram diagramContext = ESDiagram.createDiagram(context, sourceId);
    //Actor
    diagramContext.insertActor(GenericModel.ACTOR_1);
    diagramContext.removeActor(GenericModel.ACTOR_1);

    if (diagramContext instanceof PA_ESDiagram) {
      //Behavior component
      diagramContext.insertComponent(GenericModel.COMPONENT_2);
      diagramContext.removeComponent(GenericModel.COMPONENT_2);
      //Node component
      diagramContext.insertComponent(GenericModel.COMPONENT_3);
      diagramContext.removeComponent(GenericModel.COMPONENT_3);
    } else if (!(diagramContext instanceof SA_ESDiagram)) {
      //Insert/Remove tool does not exist for SA
      diagramContext.insertComponent(GenericModel.COMPONENT_1);
      diagramContext.removeComponent(GenericModel.COMPONENT_1);
    }
  }

  protected void createOperationalAnalysis(SessionContext context) {
    ESDiagram diagram = ESDiagram.createDiagram(context,
        OA__OPERATIONAL_CAPABILITIES__OPERATIONALCAPABILITY_1__OES_SCENARIO_1);
    diagram.createActor(GenericModel.ACTOR_1);
    diagram.createComponent(GenericModel.COMPONENT_1);
  }

  protected void createSystemAnalysis(SessionContext context) {
    SA_ESDiagram diagram = (SA_ESDiagram) ESDiagram.createDiagram(context,
        SA__CAPABILITIES__CAPABILITY_1__ES_SCENARIO_1);
    diagram.createActor(GenericModel.ACTOR_1);
  }

  protected void createLogicalArchitecture(SessionContext context) {
    ESDiagram diagram = ESDiagram.createDiagram(context, LA__CAPABILITIES__CAPABILITYREALIZATION_1__ES_SCENARIO_1);
    diagram.createActor(GenericModel.ACTOR_1);
    diagram.createComponent(GenericModel.COMPONENT_1);
  }

  protected void createPhysicalArchitecture(SessionContext context) {
    PA_ESDiagram diagram = (PA_ESDiagram) ESDiagram.createDiagram(context,
        PA__CAPABILITIES__CAPABILITYREALIZATION_1__ES_SCENARIO_1);
    diagram.createActor(GenericModel.ACTOR_1);
    diagram.createComponent(GenericModel.COMPONENT_2, PhysicalComponentNature.BEHAVIOR);
    diagram.createComponent(GenericModel.COMPONENT_3, PhysicalComponentNature.NODE);
  }

  public static Test suite() {
    return new InsertRemoveScenario();
  }
}
