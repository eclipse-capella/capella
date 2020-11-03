/*******************************************************************************
 * Copyright (c) 2019, 2020 THALES GLOBAL SERVICES.
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

public class DragAndDropTest extends AbstractDiagramTestCase {

  protected String diagramName;

  protected String pkg1ID;
  protected String pkg2ID;
  protected String childPkg1_1ID;
  protected String childPkg1_2ID;
  protected String childPkg2_1ID;
  protected String childPkg2_1_1ID;

  @Override
  protected String getRequiredTestModel() {
    return "DiagramToolsModel";
  }

  @Override
  public void test() throws Exception {

    Session session = getSession(getRequiredTestModel());
    SessionContext context = new SessionContext(session);

    PDDiagram pd = PDDiagram.openDiagram(context, diagramName);

    DependencyScenario1(context, pd);
  }

  private final void DependencyScenario1(SessionContext context, PDDiagram pd) {

    pd.insertDataPackages(pd.getDiagramId(), pkg1ID, pkg2ID);
  }

}
