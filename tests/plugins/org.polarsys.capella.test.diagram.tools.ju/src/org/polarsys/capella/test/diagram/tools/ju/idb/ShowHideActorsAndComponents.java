/*******************************************************************************
 * Copyright (c) 2019, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.diagram.tools.ju.idb;

import org.eclipse.sirius.business.api.session.Session;
import org.polarsys.capella.test.diagram.common.ju.context.IDBDiagram;
import org.polarsys.capella.test.diagram.tools.ju.model.EmptyProject;
import org.polarsys.capella.test.framework.context.SessionContext;

public class ShowHideActorsAndComponents extends EmptyProject {

  @Override
  public void test() throws Exception {
    Session session = getSession(getRequiredTestModel());
    SessionContext context = new SessionContext(session);

    IDBDiagram diagram = IDBDiagram.createDiagram(context, LA__LOGICAL_CONTEXT);
    
    diagram.insertComponents(LA__LOGICAL_SYSTEM);
    
    String ACTOR_1 = diagram.createSubActor(LA__LOGICAL_SYSTEM);
    String ACTOR_11 = diagram.createSubActor(ACTOR_1);
    String COMPONENT_12 = diagram.createSubComponent(ACTOR_1);
    
    String COMPONENT_2 = diagram.createSubComponent(LA__LOGICAL_SYSTEM);
    String ACTOR_21 = diagram.createSubActor(COMPONENT_2);
    String COMPONENT_22 = diagram.createSubComponent(COMPONENT_2);
    
    String ACTOR_3 = diagram.createActor();
    String ACTOR_31 = diagram.createSubActor(ACTOR_3);
    String COMPONENT_32 = diagram.createSubComponent(ACTOR_3);
    
    String COMPONENT_4 = diagram.createComponent();
    String ACTOR_41 = diagram.createSubActor(COMPONENT_4);
    String COMPONENT_42 = diagram.createSubComponent(COMPONENT_4);
    
    diagram.removeActors(ACTOR_1, ACTOR_3);
    diagram.removeComponents(COMPONENT_2, COMPONENT_4);
    
    diagram.insertSubActors(LA__LOGICAL_SYSTEM, ACTOR_1);
    diagram.insertSubActors(ACTOR_1, ACTOR_11);
    diagram.insertSubComponents(ACTOR_1, COMPONENT_12);
    diagram.insertSubComponents(LA__LOGICAL_SYSTEM, COMPONENT_2);
    diagram.insertSubActors(COMPONENT_2, ACTOR_21);
    diagram.insertSubComponents(COMPONENT_2, COMPONENT_22);
    diagram.insertActors(ACTOR_3);
    diagram.insertSubActors(ACTOR_3, ACTOR_31);
    diagram.insertSubComponents(ACTOR_3, COMPONENT_32);
    diagram.insertComponents(COMPONENT_4);
    diagram.insertSubActors(COMPONENT_4, ACTOR_41);
    diagram.insertSubComponents(COMPONENT_4, COMPONENT_42);
  }
}
