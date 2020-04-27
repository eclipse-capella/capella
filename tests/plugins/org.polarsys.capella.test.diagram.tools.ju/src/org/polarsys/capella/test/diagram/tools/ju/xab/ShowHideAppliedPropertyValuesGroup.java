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

import org.eclipse.sirius.business.api.session.Session;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;

import org.polarsys.capella.test.diagram.common.ju.context.XABDiagram;
import org.polarsys.capella.test.framework.context.SessionContext;

public class ShowHideAppliedPropertyValuesGroup extends XABDiagramsProject {

  @Override
  public void test() throws Exception {
    Session session = getSession(getRequiredTestModel());
    SessionContext context = new SessionContext(session);

    testOnXAB(context, EPBS__EAB_DIAGRAM, BlockArchitectureExt.Type.EPBS,
        EPBS__EAB_PROPERTY_VALUE1, EPBS__EAB_PROPERTY_VALUE_GROUP1,
        EPBS__EAB_CONFIGURATION_ITEM2);
    testOnXAB(context, OA__OAB_DIAGRAM, BlockArchitectureExt.Type.OA,
        OA__OAB_PROPERTY_VALUE1, OA__OAB_PROPERTY_VALUE_GROUP1,
        OA__OAB_ROLE1);
    testOnXAB(context, SA__SAB_DIAGRAM, BlockArchitectureExt.Type.SA,
        SA__SAB_PROPERTY_VALUE1, SA__SAB_PROPERTY_VALUE_GROUP1,
        SA__SAB_A1);
    testOnXAB(context, LA__LAB_DIAGRAM, BlockArchitectureExt.Type.LA,
        LA__LAB_PROPERTY_VALUE1, LA__LAB_PROPERTY_VALUE_GROUP1,
        LA__LAB_CONSTRAINT);
    testOnXAB(context, PA__PAB_DIAGRAM, BlockArchitectureExt.Type.PA,
        PA__PAB_PROPERTY_VALUE1, PA__PAB_PROPERTY_VALUE_GROUP1,
        PA__PAB_FUNCTION_2);
  }

  public void testOnXAB(SessionContext context, String diagramName, BlockArchitectureExt.Type type,
      String propertyValueId, String propertyValueGroupId, String containerId) {
    XABDiagram diagram = XABDiagram.openDiagram(context, diagramName, type);

    // show/hide applied property values
    diagram.insertPV(propertyValueId, containerId);
    diagram.removePV(propertyValueId, containerId);

    // show/hide applied property values group
    diagram.insertPVG(propertyValueGroupId, containerId);
    diagram.removePVG(propertyValueGroupId, containerId);
  }

  
}
