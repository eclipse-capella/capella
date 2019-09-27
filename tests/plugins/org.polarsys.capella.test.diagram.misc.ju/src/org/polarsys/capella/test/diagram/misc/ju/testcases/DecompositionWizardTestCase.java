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
package org.polarsys.capella.test.diagram.misc.ju.testcases;

import java.util.Arrays;
import java.util.List;

import org.eclipse.sirius.business.api.session.Session;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;
import org.polarsys.capella.core.common.ui.wizards.LCDecompositionController;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.la.LogicalComponent;
import org.polarsys.capella.core.data.la.LogicalComponentPkg;
import org.polarsys.capella.core.ui.toolkit.decomposition.DecompositionComponent;
import org.polarsys.capella.test.diagram.common.ju.context.XABDiagram;
import org.polarsys.capella.test.framework.api.BasicTestCase;
import org.polarsys.capella.test.framework.context.SessionContext;
import org.polarsys.capella.test.framework.helpers.SkeletonHelper;
import org.polarsys.capella.test.framework.helpers.TestHelper;

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
    String logAct_01 = diagram.createActor("LA 00_01", diagram.getDiagramId());
    String logAct_02 = diagram.createActor("LA 00_02", diagram.getDiagramId());
    String logCmp_01 = diagram.createComponent("LC 00_01", diagram.getDiagramId());
    String logCmp_11 = diagram.createComponent("LC 01_01", logCmp_01);

    Part prtAct_01 = context.getSemanticElement(logAct_01);
    LogicalComponent logicalAct1 = (LogicalComponent) prtAct_01.getAbstractType();
    Part prtAct_02 = context.getSemanticElement(logAct_02);
    LogicalComponent logicalAct2 = (LogicalComponent) prtAct_02.getAbstractType();
    Part prtCmp_01 = context.getSemanticElement(logCmp_01);
    LogicalComponent logicalComp1 = (LogicalComponent) prtCmp_01.getAbstractType();
    Part prtCmp_02 = context.getSemanticElement(logCmp_11);
    LogicalComponent logicalComp2 = (LogicalComponent) prtCmp_02.getAbstractType();

    checkIsDecomposable(logicalAct1, 0);
    checkIsDecomposable(logicalAct2, 0);
    checkIsDecomposable(logicalComp1, 1);
    checkIsDecomposable(logicalComp2, 0);

    SkeletonHelper.createComponentPkg(logicalAct1.getId(), "LogCompPkg_00_03", context);
    LogicalComponentPkg objPkg_03 = context.getSemanticElement("LogCompPkg_00_03");
    String idPkg_03 = objPkg_03.getId();

    SkeletonHelper.moveObject(logicalAct2.getId(), idPkg_03, context);
  }

  protected void setFlagsOnComponent(SessionContext context, Part part, boolean isActor, boolean isHuman) {
    LogicalComponent component = (LogicalComponent) part.getAbstractType();
    TestHelper.getExecutionManager(context.getSession()).execute(new AbstractReadWriteCommand() {
      @Override
      public void run() {
        component.setHuman(isHuman);
        component.setActor(isActor);
      }
    });
  }


  protected void checkIsDecomposable(LogicalComponent logicalComp, int numComponents) {
    LCDecompositionController controller = new LCDecompositionController();
    controller.createDecompositionModel(logicalComp);
    List<DecompositionComponent> listCmp = controller.getDecomposition(logicalComp).getTargetComponents();

    assertTrue(listCmp.size() == numComponents);
  }

}
