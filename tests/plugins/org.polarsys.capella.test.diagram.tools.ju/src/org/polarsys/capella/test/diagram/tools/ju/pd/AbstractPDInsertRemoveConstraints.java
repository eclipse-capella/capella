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
package org.polarsys.capella.test.diagram.tools.ju.pd;

import org.eclipse.sirius.business.api.session.Session;
import org.polarsys.capella.test.diagram.common.ju.api.AbstractDiagramTestCase;
import org.polarsys.capella.test.diagram.common.ju.context.PDDiagram;
import org.polarsys.capella.test.framework.context.SessionContext;
import org.polarsys.capella.test.framework.model.GenericModel;

public class AbstractPDInsertRemoveConstraints extends AbstractDiagramTestCase {

  protected String diagramName;

  @Override
  protected String getRequiredTestModel() {
    return "DiagramToolsModel";
  }

  @Override
  public void test() throws Exception {

    Session session = getSession(getRequiredTestModel());
    SessionContext context = new SessionContext(session);

    PDDiagram pd = PDDiagram.openDiagram(context, diagramName);
    String diagramId = pd.getDiagramId();

    pd.createConstraint(GenericModel.CONSTRAINT_1);
    pd.createConstraint(GenericModel.CONSTRAINT_2);
    pd.createConstraint(GenericModel.CONSTRAINT_3);

    pd.removeConstraint(GenericModel.CONSTRAINT_1, diagramId);
    pd.removeConstraint(GenericModel.CONSTRAINT_2, diagramId);
    pd.removeConstraint(GenericModel.CONSTRAINT_3, diagramId);

    pd.insertConstraint(GenericModel.CONSTRAINT_1, diagramId);
    pd.insertConstraint(GenericModel.CONSTRAINT_2, diagramId);
    pd.insertConstraint(GenericModel.CONSTRAINT_3, diagramId);
  }
}
