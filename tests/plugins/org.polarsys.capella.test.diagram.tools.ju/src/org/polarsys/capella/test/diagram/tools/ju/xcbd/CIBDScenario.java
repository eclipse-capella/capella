/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.diagram.tools.ju.xcbd;

import org.polarsys.capella.core.data.epbs.ConfigurationItemKind;
import org.polarsys.capella.test.diagram.common.ju.context.XBreakdownDiagram;
import org.polarsys.capella.test.diagram.tools.ju.model.EmptyProject;
import org.polarsys.capella.test.framework.context.SessionContext;
import org.polarsys.capella.test.framework.model.GenericModel;

public class CIBDScenario extends EmptyProject {

  @Override
  public void test() throws Exception {
    SessionContext context = new SessionContext(getSession(getRequiredTestModel()));

    XBreakdownDiagram diagram = XBreakdownDiagram.createCBDiagram(context, EPBS__SYSTEMCI_SYSTEM);

    diagram.createComponent(GenericModel.CI_1, 1, ConfigurationItemKind.COTSCI.getLiteral(), diagram.getDiagramId(),
        diagram.getDiagramId());
    diagram.createComponent(GenericModel.CI_1_1, 2, ConfigurationItemKind.COTSCI.getLiteral(), GenericModel.CI_1,
        diagram.getDiagramId());
    diagram.createComponent(GenericModel.CI_2, 1, ConfigurationItemKind.COTSCI.getLiteral(), diagram.getDiagramId(),
        diagram.getDiagramId());

    diagram.createCContainedIn(GenericModel.CI_2, GenericModel.CI_1_1);

    diagram.createConstraint(GenericModel.CONSTRAINT_1);
    diagram.createConstrainedElement(GenericModel.CONSTRAINT_1, GenericModel.CI_2);

    diagram.createComponent(GenericModel.CI_3, 1, ConfigurationItemKind.HWCI.getLiteral(), diagram.getDiagramId(),
        diagram.getDiagramId());
    diagram.createComponent(GenericModel.CI_4, 1, ConfigurationItemKind.CSCI.getLiteral(), diagram.getDiagramId(),
        diagram.getDiagramId());
    diagram.createComponent(GenericModel.CI_5, 1, ConfigurationItemKind.INTERFACE_CI.getLiteral(),
        diagram.getDiagramId(), diagram.getDiagramId());
    diagram.createComponent(GenericModel.CI_6, 1, ConfigurationItemKind.NDICI.getLiteral(), diagram.getDiagramId(),
        diagram.getDiagramId());
    diagram.createComponent(GenericModel.CI_7, 1, ConfigurationItemKind.PRIME_ITEM_CI.getLiteral(),
        diagram.getDiagramId(), diagram.getDiagramId());
    diagram.createComponent(GenericModel.CI_8, 1, ConfigurationItemKind.SYSTEM_CI.getLiteral(), diagram.getDiagramId(),
        diagram.getDiagramId());

    String constraint = diagram.createConstraint(GenericModel.CONSTRAINT_2);
    diagram.removeConstraint(constraint, diagram.getDiagramId());
    diagram.dragAndDropConstraintsFromExplorer(constraint, diagram.getDiagramId());
  }
}