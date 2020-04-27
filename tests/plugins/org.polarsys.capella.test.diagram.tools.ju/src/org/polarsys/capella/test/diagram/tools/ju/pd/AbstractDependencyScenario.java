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
import org.polarsys.capella.core.data.capellacore.AbstractDependenciesPkg;
import org.polarsys.capella.test.diagram.common.ju.context.PDDiagram;
import org.polarsys.capella.test.diagram.tools.ju.model.DiagramToolsModel;
import org.polarsys.capella.test.framework.context.SessionContext;

public class AbstractDependencyScenario extends DiagramToolsModel {

  protected String diagramName;

  protected String pkg1ID;
  protected String pkg2ID;
  protected String childPkg1_1ID;
  protected String childPkg1_2ID;
  protected String childPkg2_1ID;
  protected String childPkg2_1_1ID;

  @Override
  public void test() throws Exception {

    Session session = getSession(getRequiredTestModel());
    SessionContext context = new SessionContext(session);

    PDDiagram pd = PDDiagram.openDiagram(context, diagramName);

    DependencyScenario1(context, pd);
    DependencyScenario2(context, pd);
    DependencyScenario3(context, pd);
    DependencyScenario4(context, pd);
    DependencyScenario5(context, pd);
    DependencyScenario6(context, pd);
    DependencyScenario7(context, pd);
    DependencyScenario8(context, pd);
    DependencyScenario9(context, pd);
  }

  private final void DependencyScenario1(SessionContext context, PDDiagram pd) {

    pd.insertDataPackages(pd.getDiagramId(), pkg1ID, pkg2ID);

    testOnDependencyLink(context, pd, pkg1ID, pkg2ID);

    pd.clearDiagram();
  }

  private final void DependencyScenario2(SessionContext context, PDDiagram pd) {

    pd.insertDataPackages(pd.getDiagramId(), pkg1ID, pkg2ID, childPkg2_1ID);

    testOnDependencyLink(context, pd, pkg1ID, childPkg2_1ID);

    pd.clearDiagram();
  }

  private final void DependencyScenario3(SessionContext context, PDDiagram pd) {

    pd.insertDataPackages(pd.getDiagramId(), pkg1ID, pkg2ID, childPkg2_1ID, childPkg2_1_1ID);

    testOnDependencyLink(context, pd, pkg1ID, childPkg2_1_1ID);

    pd.clearDiagram();
  }

  private final void DependencyScenario4(SessionContext context, PDDiagram pd) {

    pd.insertDataPackages(pd.getDiagramId(), childPkg1_1ID, pkg2ID);

    testOnDependencyLink(context, pd, childPkg1_1ID, pkg2ID);

    pd.clearDiagram();
  }

  private final void DependencyScenario5(SessionContext context, PDDiagram pd) {

    pd.insertDataPackages(pd.getDiagramId(), childPkg1_1ID, childPkg2_1ID, pkg2ID);

    testOnDependencyLink(context, pd, childPkg1_1ID, childPkg2_1ID);

    pd.clearDiagram();
  }

  private final void DependencyScenario6(SessionContext context, PDDiagram pd) {

    pd.insertDataPackages(pd.getDiagramId(), pkg1ID, childPkg1_1ID, pkg2ID);

    testOnDependencyLink(context, pd, childPkg1_1ID, pkg2ID);
    testOnDependencyLink(context, pd, pkg1ID, pkg2ID);

    pd.clearDiagram();
  }

  private final void DependencyScenario7(SessionContext context, PDDiagram pd) {

    pd.insertDataPackages(pd.getDiagramId(), pkg1ID, childPkg1_1ID, pkg2ID, childPkg2_1ID);

    testOnDependencyLink(context, pd, pkg1ID, childPkg2_1ID);
    testOnDependencyLink(context, pd, childPkg1_1ID, childPkg2_1ID);

    pd.clearDiagram();
  }

  private final void DependencyScenario8(SessionContext context, PDDiagram pd) {

    pd.insertDataPackages(pd.getDiagramId(), childPkg1_1ID, childPkg1_2ID, pkg2ID, childPkg2_1ID, childPkg2_1_1ID);

    testOnDependencyLink(context, pd, childPkg1_1ID, childPkg2_1_1ID);
    testOnDependencyLink(context, pd, childPkg1_2ID, childPkg2_1_1ID);

    pd.clearDiagram();
  }

  private final void DependencyScenario9(SessionContext context, PDDiagram pd) {

    pd.insertDataPackages(pd.getDiagramId(), pkg1ID, childPkg1_1ID, childPkg1_2ID, pkg2ID, childPkg2_1ID,
        childPkg2_1_1ID);

    testOnDependencyLink(context, pd, childPkg1_1ID, childPkg2_1_1ID);
    testOnDependencyLink(context, pd, childPkg1_2ID, childPkg2_1_1ID);

    pd.clearDiagram();
  }

  private final void testOnDependencyLink(SessionContext context, PDDiagram pd, String subPackageId,
      String superPackageId) {

    AbstractDependenciesPkg semanticSubPkg = context.getSemanticElement(subPackageId);
    AbstractDependenciesPkg semanticSuperPkg = context.getSemanticElement(superPackageId);

    pd.checkIfDependencyIsCorrect(semanticSubPkg, semanticSuperPkg);
  }
}
