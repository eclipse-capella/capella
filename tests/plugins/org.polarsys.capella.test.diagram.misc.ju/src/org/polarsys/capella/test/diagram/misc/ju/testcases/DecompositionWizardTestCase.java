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
package org.polarsys.capella.test.diagram.misc.ju.testcases;

import java.util.Arrays;
import java.util.List;

import org.eclipse.sirius.business.api.session.Session;
import org.polarsys.capella.core.common.ui.wizards.LCDecompositionController;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.la.LogicalComponent;
import org.polarsys.capella.core.data.la.LogicalComponentPkg;
import org.polarsys.capella.core.ui.toolkit.decomposition.DecompositionComponent;
import org.polarsys.capella.test.diagram.common.ju.context.XABDiagram;
import org.polarsys.capella.test.framework.api.BasicTestCase;
import org.polarsys.capella.test.framework.context.SessionContext;
import org.polarsys.capella.test.framework.helpers.SkeletonHelper;

public class DecompositionWizardTestCase extends BasicTestCase {

  private static final String PROJECT_NAME = "component-breakdown";

  @Override
  public List<String> getRequiredTestModels() {
    return Arrays.asList(PROJECT_NAME);
  }

  @Override
  public void test() throws Exception {
    Session session = getSession(PROJECT_NAME);
    assertNotNull(session);

    String systemId = "2a3acffb-da23-4caf-a526-96887c94d447";
    SessionContext context = new SessionContext(session);
    XABDiagram diagram = XABDiagram.createDiagram(context, systemId);
    String idAct_01 = diagram.createActor("LA 00_01", diagram.getDiagramId());
    String idAct_02 = diagram.createActor("LA 00_02", diagram.getDiagramId());
    String idCmp_01 = diagram.createComponent("LC 00_01", diagram.getDiagramId());
    String idCmp_12 = diagram.createComponent("LC 01_02", idCmp_01);
    String idAct_13 = diagram.createActor("LA 01_03", diagram.getDiagramId());

    Part prtAct_01 = context.getSemanticElement(idAct_01);
    LogicalComponent logAct_01 = (LogicalComponent) prtAct_01.getAbstractType();
    Part prtAct_02 = context.getSemanticElement(idAct_02);
    LogicalComponent logAct_02 = (LogicalComponent) prtAct_02.getAbstractType();
    Part prtCmp_01 = context.getSemanticElement(idCmp_01);
    LogicalComponent logCmp_01 = (LogicalComponent) prtCmp_01.getAbstractType();
    Part prtCmp_11 = context.getSemanticElement(idCmp_12);
    LogicalComponent logCmp_11 = (LogicalComponent) prtCmp_11.getAbstractType();

    checkIsDecomposable(logAct_01, 0);
    checkIsDecomposable(logAct_02, 0);
    checkIsDecomposable(logCmp_01, 1);
    checkIsDecomposable(logCmp_11, 0);

    SkeletonHelper.createComponentPkg(logAct_01.getId(), "LogCompPkg_00_03", context);
    LogicalComponentPkg objPkg_03 = context.getSemanticElement("LogCompPkg_00_03");
    SkeletonHelper.moveObject(idAct_02, objPkg_03.getId(), context);
    SkeletonHelper.moveObject(idAct_13, objPkg_03.getId(), context);

    checkIsDecomposable(logAct_01, 2);
  }

  protected void checkIsDecomposable(LogicalComponent logicalComp, int numComponents) {
    LCDecompositionController controller = new LCDecompositionController();
    controller.createDecompositionModel(logicalComp);
    List<DecompositionComponent> listCmp = controller.getDecomposition(logicalComp).getTargetComponents();

    assertTrue(listCmp.size() == numComponents);
  }

}
