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
package org.polarsys.capella.test.diagram.tools.ju.xab;

import org.eclipse.sirius.business.api.session.Session;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt.ConfigurationItemType;
import org.polarsys.capella.test.diagram.common.ju.context.EABDiagram;
import org.polarsys.capella.test.framework.context.SessionContext;
import org.polarsys.capella.test.framework.model.GenericModel;

public class DragAndDropEABTest extends XABDiagramsProject {
  public static final String CONFIGURATION_ITEM = "CONFIGURATION_ITEM";

  @Override
  public void test() throws Exception {
    Session session = getSession(getRequiredTestModel());
    SessionContext context = new SessionContext(session);

    testOnXAB(context, EPBS__EAB_DIAGRAM);
  }

  public void testOnXAB(SessionContext context, String diagramName) {
    EABDiagram diagram = EABDiagram.openDiagram(context, diagramName);

    String cs = diagram.createConfigurationItem("CS", diagram.getDiagramId(), ConfigurationItemType.CS);
    diagram.removeConfigurationItems(diagram.getDiagramId(), cs);
    diagram.dragAndDropConfigurationItemFromExplorer(cs, diagram.getDiagramId());

    diagram.manageRealizedPhysicalArtifacts(EPBS__EAB_CONFIGURATION_ITEM1, PA__PAB_COMPONENT_PC6);
    diagram.dragAndDropPhysicalAtifacts(PA__PAB_COMPONENT_PC6, cs);

    String constraint = diagram.createConstraint(GenericModel.CONSTRAINT_1);
    diagram.removeConstraint(constraint, diagram.getDiagramId());
    diagram.dragAndDropConstraintsFromExplorer(constraint, diagram.getDiagramId());
  }
}
