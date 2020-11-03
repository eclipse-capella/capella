/*******************************************************************************
 * Copyright (c) 2018, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.diagram.tools.ju.es;

import static org.polarsys.capella.test.diagram.common.ju.context.ESDiagram.createDiagram;
import static  org.polarsys.capella.test.diagram.common.ju.step.tools.sequence.MultiInstanceRoleTool.newInstanceRole;

import org.eclipse.sirius.business.api.session.Session;
import org.polarsys.capella.test.diagram.common.ju.api.AbstractDiagramTestCase;
import org.polarsys.capella.test.diagram.common.ju.context.DiagramContext;
import org.polarsys.capella.test.diagram.common.ju.context.ESDiagram;
import org.polarsys.capella.test.diagram.common.ju.context.ISDiagram;
import org.polarsys.capella.test.framework.context.SessionContext;

import junit.framework.Test;

/**
 * Test the Multi-InstanceRole Tool on IS, ES and OES diagrams
 */
public class MultiInstanceRoleTest extends AbstractDiagramTestCase {

  protected SessionContext sc;
  protected Session s;

  private static final String OA_SCENARIO__FLIGHT_PHASES = "d04ae481-72c3-432e-b4e7-b2d66264d3b0"; //$NON-NLS-1$
  private static final String CTX_SCENARIO__START_VOD_SERVICE = "58d8d80a-34ab-4e60-aee2-fb8eb885583a"; //$NON-NLS-1$
  private static final String CTX_SCENARIO__PROVIDE_MOVING_MAP_SERVICES = "c3d663a9-3a4c-4e36-ac88-d5163b11d3ff"; //$NON-NLS-1$
  private static final String LA_SCENARIO__PERFORM_AUDIO_ANNOUNCEMENT = "2c5abb7e-3281-4651-8458-39d383b9dcc0"; //$NON-NLS-1$
  private static final String LA_SCENARIO__PROVIDE_CONFIGURATION_MEANS = "5bf082b5-2050-438c-a203-f1eb03cbcecd"; //$NON-NLS-1$
  private static final String PA_SCENARIO__PROVIDE_TESTING_INTERFACE = "7b2fa095-18cb-483f-87be-73f78c1cc88f"; //$NON-NLS-1$
  private static final String PA_SCENARIO__PROVIDE_CONFIGURATION_MEANS = "9ef4fd22-b504-4013-9edb-fb9e7ed1d97d";

  // watch out, these IDs are part IDs
  private static final String OA_ACTOR__GROUND_OPERATOR = "8dec32f0-0393-4da9-b9a1-1f1a3a9a8128"; //$NON-NLS-1$
  private static final String OA_ENTITY__AIRCRAFT = "bb2375f0-8f2d-4f6d-b469-42b275f8795a"; //$NON-NLS-1$
  private static final String CTX_ACTOR__PASSENGER = "e9c81b65-e5f7-4edd-be4d-4e6a08f5e825"; //$NON-NLS-1$
  private static final String CTX_SYSTEM__IFE_SYSTEM = "cb4b8f2d-7663-4d35-bc44-20080a8d3133"; //$NON-NLS-1$
  private static final String LA_ACTOR__CABIN_CREW = "0bbbd1de-dd06-4860-90a5-41a026de9601"; //$NON-NLS-1$
  private static final String LA_COMPONENT__IFE_SYSTEM = "ce1400b3-f97e-4c62-8187-562b72134923"; //$NON-NLS-1$
  private static final String LA_COMPONENT__CABIN_SCREEN ="bd03d965-9e76-4ec0-b298-c1b8f497f5a4"; //$NON-NLS-1$
  private static final String PA_COMPONENT__AIRCRAFT_FRONT_SERVERS = "f3fc69a5-507d-43c5-a4a1-4a5fd9e6b347"; //$NON-NLS-1$
  private static final String PA_COMPONENT__IFE_SYSTEM = "1371e1d0-8401-4b88-8d3a-cc72d34f1132"; //$NON-NLS-1$
  private static final String PA_ACTOR__AIRCRAFT = "369cbf55-7250-4e93-9656-0f3cee9a5d0e"; //$NON-NLS-1$

  private Session session;
  private SessionContext context;

  @Override
  protected String getRequiredTestModel() {
    return "In-Flight Entertainment System"; //$NON-NLS-1$
  }


  @Override
  public void test() throws Exception {

    session = getSession(getRequiredTestModel());
    context = new SessionContext(session);

    oaTest();
    saTest();
    laTest();
    paTest();
  }

  private void oaTest() {
    ESDiagram diagram = createDiagram(context, OA_SCENARIO__FLIGHT_PHASES);
    newInstanceRole(diagram, OA_ACTOR__GROUND_OPERATOR);
    newInstanceRole(diagram, OA_ACTOR__GROUND_OPERATOR);
    newInstanceRole(diagram, OA_ENTITY__AIRCRAFT);
    newInstanceRole(diagram, OA_ENTITY__AIRCRAFT);
  }

  private void saTest() {
    DiagramContext diagram = ESDiagram.createDiagram(context, CTX_SCENARIO__START_VOD_SERVICE);
    newInstanceRole(diagram, CTX_ACTOR__PASSENGER);
    newInstanceRole(diagram, CTX_ACTOR__PASSENGER);
    newInstanceRole(diagram, CTX_SYSTEM__IFE_SYSTEM);
    newInstanceRole(diagram, CTX_SYSTEM__IFE_SYSTEM);

    diagram = ISDiagram.createDiagram(context, CTX_SCENARIO__PROVIDE_MOVING_MAP_SERVICES);
    newInstanceRole(diagram, CTX_ACTOR__PASSENGER);
    newInstanceRole(diagram, CTX_ACTOR__PASSENGER);
    newInstanceRole(diagram, CTX_SYSTEM__IFE_SYSTEM);
    newInstanceRole(diagram, CTX_SYSTEM__IFE_SYSTEM);
  }

  private void laTest() {
    DiagramContext diagram = ESDiagram.createDiagram(context, LA_SCENARIO__PERFORM_AUDIO_ANNOUNCEMENT);
    newInstanceRole(diagram, LA_ACTOR__CABIN_CREW);
    newInstanceRole(diagram, LA_ACTOR__CABIN_CREW);
    newInstanceRole(diagram, LA_COMPONENT__CABIN_SCREEN);
    newInstanceRole(diagram, LA_COMPONENT__CABIN_SCREEN);
    newInstanceRole(diagram, LA_COMPONENT__IFE_SYSTEM);
    newInstanceRole(diagram, LA_COMPONENT__IFE_SYSTEM);

    diagram = ISDiagram.createDiagram(context, LA_SCENARIO__PROVIDE_CONFIGURATION_MEANS);
    newInstanceRole(diagram, LA_ACTOR__CABIN_CREW);
    newInstanceRole(diagram, LA_ACTOR__CABIN_CREW);
    newInstanceRole(diagram, LA_COMPONENT__CABIN_SCREEN);
    newInstanceRole(diagram, LA_COMPONENT__CABIN_SCREEN);
    newInstanceRole(diagram, LA_COMPONENT__IFE_SYSTEM);
    newInstanceRole(diagram, LA_COMPONENT__IFE_SYSTEM);

  }

  private void paTest() {
    DiagramContext diagram = ESDiagram.createDiagram(context, PA_SCENARIO__PROVIDE_TESTING_INTERFACE);
    newInstanceRole(diagram, PA_COMPONENT__IFE_SYSTEM);
    newInstanceRole(diagram, PA_COMPONENT__IFE_SYSTEM);
    newInstanceRole(diagram, PA_COMPONENT__AIRCRAFT_FRONT_SERVERS);
    newInstanceRole(diagram, PA_COMPONENT__AIRCRAFT_FRONT_SERVERS);
    newInstanceRole(diagram, PA_ACTOR__AIRCRAFT);
    newInstanceRole(diagram, PA_ACTOR__AIRCRAFT);

    diagram = ISDiagram.createDiagram(context, PA_SCENARIO__PROVIDE_CONFIGURATION_MEANS);
    newInstanceRole(diagram, PA_COMPONENT__IFE_SYSTEM);
    newInstanceRole(diagram, PA_COMPONENT__IFE_SYSTEM);
    newInstanceRole(diagram, PA_COMPONENT__AIRCRAFT_FRONT_SERVERS);
    newInstanceRole(diagram, PA_COMPONENT__AIRCRAFT_FRONT_SERVERS);
    newInstanceRole(diagram, PA_ACTOR__AIRCRAFT);
    newInstanceRole(diagram, PA_ACTOR__AIRCRAFT);

  }

  public static Test suite() {
    return new MultiInstanceRoleTest();
  }
}
