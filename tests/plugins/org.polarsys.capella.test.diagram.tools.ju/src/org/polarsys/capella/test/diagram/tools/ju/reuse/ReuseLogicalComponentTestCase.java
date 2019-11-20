/*******************************************************************************
 * Copyright (c) 2019 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.test.diagram.tools.ju.reuse;

import org.eclipse.sirius.business.api.session.Session;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.test.diagram.common.ju.context.XABDiagram;
import org.polarsys.capella.test.framework.context.SessionContext;

public class ReuseLogicalComponentTestCase extends ReuseOfComponentsProject {

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

    // Reuse Logical Component
    diagram.reuseComponent(diagramId, LA__LC_1);
    diagram.reuseComponent(diagramId, LA__LC_1, LA__LC_2);
    diagram.reuseComponent(diagramId, LA__LC_1_1, LA__LC_3);

    diagram.reuseComponent(LA__PART_1_LC_1, LA__LC_1_1, LA__LC_2);

    diagram.reuseComponent(LA__PART_1_LC_1_1, LA__LC_2);

    diagram.reuseComponent(diagramId, LA__LC_3);

    // show/hide reused components
    diagram.removeComponent(LA__REUSE_PART_2_LC_2_LC_1, LA__PART_1_LC_1);
    diagram.insertComponent(LA__REUSE_PART_2_LC_2_LC_1, LA__PART_1_LC_1);
    diagram.removeComponent(LA__PART_1_LC_1_1, LA__PART_1_LC_1);
    diagram.insertComponent(LA__PART_1_LC_1_1, LA__PART_1_LC_1);
    diagram.removeComponent(LA__PART_1_LC_1);
    diagram.insertComponent(LA__PART_1_LC_1);
  }
}
