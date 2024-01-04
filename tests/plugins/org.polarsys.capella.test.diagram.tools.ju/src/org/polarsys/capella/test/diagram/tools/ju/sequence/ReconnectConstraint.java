/*******************************************************************************
 * Copyright (c) 2024 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.diagram.tools.ju.sequence;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.diagram.DEdge;
import org.polarsys.capella.core.sirius.analysis.constants.IToolNameConstants;
import org.polarsys.capella.test.diagram.common.ju.context.ActivityScenarioDiagram;
import org.polarsys.capella.test.diagram.common.ju.context.ESDiagram;
import org.polarsys.capella.test.diagram.common.ju.context.EntityScenarioDiagram;
import org.polarsys.capella.test.diagram.common.ju.context.FSDiagram;
import org.polarsys.capella.test.diagram.common.ju.context.ISDiagram;
import org.polarsys.capella.test.diagram.common.ju.context.SequenceDiagram;
import org.polarsys.capella.test.diagram.common.ju.step.tools.CreateDEdgeTool;
import org.polarsys.capella.test.diagram.tools.ju.model.SequenceDiagramProject;
import org.polarsys.capella.test.diagram.tools.ju.sequence.SequenceTest.SequenceType;
import org.polarsys.capella.test.framework.context.SessionContext;

/**
 *
 */
public class ReconnectConstraint extends SequenceDiagramProject {

  protected Session session;
  protected SessionContext context;
  
  public static final String constraint1 = "CONSTRAINT_1";
  
  public static final String OA_SCENARIO_1 = "bca5b9df-da49-48eb-b7f0-b8c8f6a2969b";
  public static final String OA_SCENARIO_2   = "94725bd5-50de-4ff8-8e56-3fe5a39bc63e";
  
  public static final String SA_SCENARIO_1 = "7863d76b-be72-4050-b34c-072ef8645581";
  public static final String SA_SCENARIO_2 = "bf975a07-fbd5-41c7-b493-d33f090e51fd";
  public static final String SA_SCENARIO_3 = "cac09d02-c680-46fa-a4f5-abe94f11d2c9";
  
  public static final String LA_SCENARIO_1 = "a6589bba-06df-4a85-9148-e4657da6c946";
  public static final String LA_SCENARIO_2 = "0eccbf4f-e4cc-4232-bb4d-256483013bf3";
  public static final String LA_SCENARIO_3 = "2003f286-efd1-40db-a1f3-5f206384dfc8";
  
  public static final String PA_SCENARIO_1 = "182f0c3e-ef06-4150-9a0f-0ef91bdd9303";
  public static final String PA_SCENARIO_2 = "e0e2293a-95ab-4a3b-b387-30a4d7fc73c9";
  public static final String PA_SCENARIO_3 = "54198d02-1584-44eb-8f80-bd9261de3a20";
  
  public static final String EPBS_SCENARIO_1 = "f45291ed-85b6-4f9f-bf27-d259cf6d1c8d";
  
  public ReconnectConstraint() {
  }
  
  @Override
  public void test() throws Exception {
    initTest();
    testScenarios();
    session.close(new NullProgressMonitor());
  }
  
  protected void initTest() {
    session = getSession(getRequiredTestModel());
    context = new SessionContext(session);
  }

  public void testScenarios() {
    oaTest();
    saTest();
    laTest();
    paTest();
    epbsTest();
  }
  
  protected void oaTest() {    
    testEntityScenario(OA_SCENARIO_1);
    testActivityScenario(OA_SCENARIO_2);
  }
  
  protected void saTest() {  
    testES(SA_SCENARIO_1);
    testIS(SA_SCENARIO_2);    
    testFS(SA_SCENARIO_3);
  }
  
  protected void laTest() { 
    testES(LA_SCENARIO_1);
    testIS(LA_SCENARIO_2);
    testFS(LA_SCENARIO_3);
  }
  
  protected void paTest() { 
    testES(PA_SCENARIO_1);
    testIS(PA_SCENARIO_2);
    testFS(PA_SCENARIO_3);
  }
  
  protected void epbsTest() {  
    // testIS(EPBS_SCENARIO_1); Disabled -> not possible to create actors, just predefined instance roles 
  }
  
  
  protected void testIS(String source) {    
    ISDiagram is = ISDiagram.createDiagram(context, source);
    String actor1 = is.createActor();
    is.createConstraint(constraint1);
    String actor2 = is.createActor();
    String constraintLink = is.createConstrainedElement(constraint1, actor1);
    
    is.reconnectConstraint(getReconnectTargetConstraintName(SequenceType.IS), constraintLink, actor1, actor2);
  }
  
  protected void testES(String source) {    
    ESDiagram es = ESDiagram.createDiagram(context, source);
    String actor1 = es.createActor();
    es.createConstraint(constraint1);
    String actor2 = es.createActor();
    String constraintLink = es.createConstrainedElement(constraint1, actor1);
    
    es.reconnectConstraint(getReconnectTargetConstraintName(SequenceType.ES),constraintLink, actor1, actor2);
  }
  
  protected void testEntityScenario(String source) {    
    EntityScenarioDiagram es = EntityScenarioDiagram.createDiagram(context, source);
    String actor1 = es.createActor();
    es.createConstraint(constraint1);
    String actor2 = es.createActor();
    String constraintLink = es.createConstrainedElement(constraint1, actor1);
    
    es.reconnectConstraint(getReconnectTargetConstraintName(SequenceType.OES),constraintLink, actor1, actor2);
  }
  
  protected void testActivityScenario(String source) {    
    ActivityScenarioDiagram oas = ActivityScenarioDiagram.createDiagram(context, source);
    String activity1 = oas.createActivity();
    oas.createConstraint(constraint1);
    String activity2 = oas.createActivity();
    String constraintLink = createScenarioConstrainedElement(oas, constraint1, activity1);
    
    oas.reconnectConstraint(getReconnectTargetConstraintName(SequenceType.OAS), constraintLink, activity1, activity2);
  }
  
  protected void testFS(String source) {    
    FSDiagram fs = FSDiagram.createDiagram(context, source);
    // String actor1 = fs.createActor(); Actor not supported
    String func1 = fs.createFunction();
    fs.createConstraint(constraint1);
    String func2 = fs.createFunction();
    String constraintLink = fs.createConstrainedElement(constraint1, func1);
    
    fs.reconnectConstraint(getReconnectTargetConstraintName(SequenceType.FS), constraintLink, func1, func2);
  }  

  protected String getReconnectTargetConstraintName(SequenceType seqType) {
    if (seqType == SequenceType.ES) {
      return IToolNameConstants.TOOL_ES_RECONNECT_CONSTRAINT;
    } else if (seqType == SequenceType.FS) {
      return IToolNameConstants.TOOL_FS_RECONNECT_CONSTRAINT;
    } else if (seqType == SequenceType.IS) {
      return IToolNameConstants.TOOL_IS_RECONNECT_CONSTRAINT;
    } else if (seqType == SequenceType.OES) {
      return IToolNameConstants.TOOL_OES_RECONNECT_CONSTRAINT;
    } else if (seqType == SequenceType.OAS) {
      return IToolNameConstants.TOOL_OAS_RECONNECT_CONSTRAINT;
    }

    return "";
  }
  
  public String createScenarioConstrainedElement(SequenceDiagram diagram,String sourceId, String targetId) {
    String name = IToolNameConstants.TOOL_OAS_CREATE_CONSTRAINTELEMENTSCENARIO;
    DEdge edge =  new CreateDEdgeTool(diagram, name, sourceId, targetId).run();
    
    return diagram.getSemanticIdFromView(edge);
  }
}
