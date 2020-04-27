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

public class ReuseLogicalActorTestCase extends ReuseOfComponentsProject {

  @Override
  protected String getRequiredTestModel() {
    return "ReuseOfComponentsModel";
  }

  @Override
  public void test() throws Exception {
    Session session = getSession(getRequiredTestModel());
    SessionContext context = new SessionContext(session);

    XABDiagram diagram = XABDiagram.openDiagram(context, LA__LAB_DIAGRAM, BlockArchitectureExt.Type.LA);
    String diagramId = diagram.getDiagramId();

    // Reuse Logical Actor
    diagram.reuseActor(diagramId, LA__LA_1);
    diagram.reuseActor(diagramId, LA__LA_1, LA__LA_1_1, LA__LA_2);

    diagram.reuseActor(LA__PART_2_LA_1, LA__LA_1_1, LA__LA_2);

    diagram.reuseActor(LA__PART_1_LA_1_1, LA__LA_2);

    diagram.reuseActor(diagramId, LA__LA_3);

    // show/hide reused actors
    diagram.removeActor(LA__PART_2_LA_1, LA__REUSE_PART_2_LA_2_LA_1);
    diagram.insertActor(LA__PART_2_LA_1, LA__REUSE_PART_2_LA_2_LA_1);
    diagram.removeActor(diagramId, LA__PART_3_LA_2);
    diagram.insertActor(diagramId, LA__PART_3_LA_2);
  }
}
