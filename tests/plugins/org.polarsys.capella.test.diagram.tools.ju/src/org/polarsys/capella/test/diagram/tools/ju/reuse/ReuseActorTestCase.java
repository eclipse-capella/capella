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
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.test.diagram.common.ju.context.XABDiagram;
import org.polarsys.capella.test.framework.context.SessionContext;

public class ReuseActorTestCase extends ReuseOfComponentsProject {

  @Override
  protected String getRequiredTestModel() {
    return "ReuseOfComponentsModel";
  }

  @Override
  public void test() throws Exception {
    Session session = getSession(getRequiredTestModel());
    SessionContext context = new SessionContext(session);

    XABDiagram diagram = XABDiagram.openDiagram(context, SA__SAB_DIAGRAM, BlockArchitectureExt.Type.SA);
    String diagramId = diagram.getDiagramId();

    // Reuse Logical Actor
    diagram.reuseActor(diagramId, SA__ACTOR_SA_1);
    diagram.reuseActor(diagramId, SA__ACTOR_SA_1, SA__ACTOR_SA_1_1, SA__ACTOR_SA_2);

    diagram.reuseActor(SA__PART_ACTOR_SA_1, SA__ACTOR_SA_1_1, SA__ACTOR_SA_2);

    diagram.reuseActor(SA__PART_ACTOR_SA_1_1, SA__ACTOR_SA_2);

    diagram.reuseActor(diagramId, SA__ACTOR_SA_3);
    diagram.reuseActor(diagramId, SA__ACTOR_SA_3);

    // show/hide reused actors
    diagram.removeActor(SA__PART_ACTOR_SA_1, SA__PART_ACTOR_SA_1_1);
    diagram.removeActor(SA__PART_ACTOR_SA_1);
    diagram.insertActor(SA__PART_ACTOR_SA_1);
    diagram.insertActor(SA__PART_ACTOR_SA_1, SA__PART_ACTOR_SA_1_1);
  }
}
