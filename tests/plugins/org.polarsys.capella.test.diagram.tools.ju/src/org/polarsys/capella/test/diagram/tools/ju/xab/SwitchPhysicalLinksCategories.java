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
package org.polarsys.capella.test.diagram.tools.ju.xab;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.business.api.session.Session;
import org.junit.Assert;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;

import org.polarsys.capella.test.diagram.common.ju.context.XABDiagram;
import org.polarsys.capella.test.framework.context.SessionContext;

public class SwitchPhysicalLinksCategories extends XABDiagramsProject {

  @Override
  public void test() throws Exception {
    Session session = getSession(getRequiredTestModel());
    SessionContext context = new SessionContext(session);

    testOnXAB(context, SA__SAB_DIAGRAM, BlockArchitectureExt.Type.SA, SA__SAB_PHYSICAL_LINK_CATEGORY1,
        SA__SAB_PHYSICAL_LINK_PL1, SA__SAB_PHYSICAL_PATH1, SA__SAB_PHYSICAL_PORT_A2_PP1, SA__SAB_PHYSICAL_PORT_A2_PP3);
    testOnXAB(context, LA__LAB_DIAGRAM, BlockArchitectureExt.Type.LA, LA__LAB_PHYSICAL_LINK_CATEGORY1,
        LA__LAB_PHYSICAL_LINK_PL1, LA__LAB_PHYSICAL_PATH1, LA__LAB_PHYSICAL_PORT_A2_PP1, LA__LAB_PHYSICAL_PORT_A2_PP3);
    testOnXAB(context, PA__PAB_DIAGRAM, BlockArchitectureExt.Type.PA, PA__PAB_PHYSICAL_LINK_CATEGORY1,
        PA__PAB_PHYSICAL_LINK_PL1, PA__PAB_PHYSICAL_PATH1, PA__PAB_PHYSICAL_PORT_PC2_PP1,
        PA__PAB_PHYSICAL_PORT_PC2_PP4);
  }

  public void testOnXAB(SessionContext context, String diagramName, BlockArchitectureExt.Type type, String categoryId,
      String linkId, String pathId, String source, String target) {
    XABDiagram diagram = XABDiagram.openDiagram(context, diagramName, type);

    diagram.switchPhysicalLinksCategories(categoryId, true);

    diagram.refreshDiagram();

    diagram.hasntView(linkId);

    EObject sElement = diagram.getSessionContext().getSemanticElement(source);
    EObject tElement = diagram.getSessionContext().getSemanticElement(target);
    if (sElement != null && tElement != null && diagram.getView(sElement) != null
        && diagram.getView(tElement) != null) {
      // The internal functional chain should be removed
      Assert.assertFalse("The internal physical path should be removed", diagram.hasEdge(source, target, pathId));
    }
    diagram.switchPhysicalLinksCategories(categoryId, false);

    // The internal functional chain should be added
    Assert.assertTrue("The internal physical path should be added", diagram.hasEdge(source, target, pathId));

    diagram.hasntView(categoryId);

    diagram.refreshDiagram();

    diagram.hasntView(categoryId);
  }

}
