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

    String cots = diagram.createConfigurationItem("COTS", diagram.getDiagramId(), ConfigurationItemType.COTS);
    diagram.removeConfigurationItems(diagram.getDiagramId(), cots);
    diagram.dragAndDropConfigurationItemFromExplorer(cots, diagram.getDiagramId());

    String cs = diagram.createConfigurationItem("CS", diagram.getDiagramId(), ConfigurationItemType.CS);
    diagram.removeConfigurationItems(diagram.getDiagramId(), cs);
    diagram.dragAndDropConfigurationItemFromExplorer(cs, diagram.getDiagramId());

    String hw = diagram.createConfigurationItem("HW", diagram.getDiagramId(), ConfigurationItemType.HW);
    diagram.removeConfigurationItems(diagram.getDiagramId(), hw);
    diagram.dragAndDropConfigurationItemFromExplorer(hw, diagram.getDiagramId());

    String interf = diagram.createConfigurationItem("Interface", diagram.getDiagramId(),
        ConfigurationItemType.INTERFACE);
    diagram.removeConfigurationItems(diagram.getDiagramId(), interf);
    diagram.dragAndDropConfigurationItemFromExplorer(interf, diagram.getDiagramId());

    String ndi = diagram.createConfigurationItem("NDI", diagram.getDiagramId(), ConfigurationItemType.NDI);
    diagram.removeConfigurationItems(diagram.getDiagramId(), ndi);
    diagram.dragAndDropConfigurationItemFromExplorer(ndi, diagram.getDiagramId());

    String primeItem = diagram.createConfigurationItem("Prime Item", diagram.getDiagramId(),
        ConfigurationItemType.PRIME_ITEM);
    diagram.removeConfigurationItems(diagram.getDiagramId(), primeItem);
    diagram.dragAndDropConfigurationItemFromExplorer(primeItem, diagram.getDiagramId());

    String system = diagram.createConfigurationItem("System", diagram.getDiagramId(), ConfigurationItemType.SYSTEM);
    diagram.removeConfigurationItems(diagram.getDiagramId(), system);
    diagram.dragAndDropConfigurationItemFromExplorer(system, diagram.getDiagramId());

    diagram.manageRealizedPhysicalArtifacts(EPBS__EAB_CONFIGURATION_ITEM1, PA__PAB_COMPONENT_PC6);
    diagram.dragAndDropPhysicalAtifacts(PA__PAB_COMPONENT_PC6, cs);

    String constraint = diagram.createConstraint(GenericModel.CONSTRAINT_1);
    diagram.removeConstraint(constraint, diagram.getDiagramId());
    diagram.dragAndDropConstraintsFromExplorer(constraint, diagram.getDiagramId());

  }
}
