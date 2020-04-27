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
package org.polarsys.capella.test.diagram.tools.ju.reuse;

import org.eclipse.sirius.business.api.session.Session;
import org.polarsys.capella.test.diagram.common.ju.context.PABDiagram;
import org.polarsys.capella.test.framework.context.SessionContext;

public class ReusePhysicalActorTestCase extends ReuseOfComponentsProject {

  @Override
  protected String getRequiredTestModel() {
    return "ReuseOfComponentsModel";
  }

  @Override
  public void test() throws Exception {
    Session session = getSession(getRequiredTestModel());
    SessionContext context = new SessionContext(session);

    PABDiagram diagram = PABDiagram.openDiagram(context, PA__PAB_DIAGRAM);
    String diagramId = diagram.getDiagramId();

    // Reuse PC Node
    diagram.reuseActor(diagramId, PA__ACTOR_PA_1);
    diagram.reuseActor(diagramId, PA__ACTOR_PA_1, PA__ACTOR_PA_2, PA__ACTOR_PA_2_1);

    diagram.reuseActor(PA__PART_ACTOR_PA_1, PA__ACTOR_PA_2, PA__ACTOR_PA_2_1);

    diagram.reuseActor(diagramId, PA__ACTOR_PA_3);
    diagram.reuseActor(diagramId, PA__ACTOR_PA_3);
  }
}
