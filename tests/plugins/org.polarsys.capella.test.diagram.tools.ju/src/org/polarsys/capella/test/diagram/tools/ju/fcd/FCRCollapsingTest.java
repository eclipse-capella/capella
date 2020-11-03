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
package org.polarsys.capella.test.diagram.tools.ju.fcd;

import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DNodeContainer;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.junit.Assert;
import org.polarsys.capella.core.diagram.helpers.DiagramHelper;
import org.polarsys.capella.core.sirius.analysis.FunctionalChainServices;
import org.polarsys.capella.test.diagram.common.ju.api.AbstractDiagramTestCase;
import org.polarsys.capella.test.diagram.common.ju.context.DiagramContext;
import org.polarsys.capella.test.diagram.common.ju.step.crud.OpenDiagramStep;
import org.polarsys.capella.test.framework.context.SessionContext;

public class FCRCollapsingTest extends AbstractDiagramTestCase {

  public static final String FUNCTIONAL_CHAIN_REFERENCE_TO_FUNCTIONALCHAIN_8 = "e5d500f1-a29f-4fae-9807-03cf0314d1db"; //$NON-NLS-1$
  public static final String FUNCTIONAL_CHAIN_REFERENCE_TO_FUNCTIONALCHAIN_4 = "4887b279-264b-47f6-be03-93b9f5075ca9"; //$NON-NLS-1$
  public static final String FUNCTIONAL_CHAIN_REFERENCE_TO_FUNCTIONALCHAIN_3 = "101d7e2a-714e-42cc-893e-74263b03da74"; //$NON-NLS-1$
  public static final String FUNCTIONAL_CHAIN_REFERENCE_TO_FUNCTIONALCHAIN_2 = "4f335695-e0cc-4711-94ac-106d76bad8dd"; //$NON-NLS-1$
  public static final String FUNCTIONAL_CHAIN_REFERENCE_TO_FUNCTIONALCHAIN_1 = "5ed06f2c-8929-4721-b1b1-62e1df26c67e"; //$NON-NLS-1$
  public static final String FUNCTIONAL_CHAIN_INVOLVEMENT_LINK_TO_FUNCTIONALEXCHANGE_3 = "79e21610-74d2-4e46-a1f7-ee3e0715a8e3"; //$NON-NLS-1$
  public static final String FUNCTIONAL_CHAIN_INVOLVEMENT_LINK_TO_FUNCTIONALEXCHANGE_2 = "f18c4ee7-3000-4370-90f1-dfef8f588a3f"; //$NON-NLS-1$
  public static final String FUNCTIONAL_CHAIN_INVOLVEMENT_LINK_TO_FUNCTIONALEXCHANGE_1 = "021b0c24-58d3-4418-a977-40fedc14c2c2"; //$NON-NLS-1$
  public static final String FUNCTIONAL_CHAIN_INVOLVEMENT_FUNCTION_TO_LOGICALFUNCTION_5 = "90592133-5ba5-4d82-89ad-f4e32f28deb3"; //$NON-NLS-1$
  public static final String FUNCTIONAL_CHAIN_INVOLVEMENT_FUNCTION_TO_LOGICALFUNCTION_3 = "39566c07-5b8a-4fd5-baa3-32cb69ee7daf"; //$NON-NLS-1$
  public static final String FUNCTIONAL_CHAIN_INVOLVEMENT_FUNCTION_TO_LOGICALFUNCTION_2 = "b1eed137-511a-435b-8e8f-f7274c46016a"; //$NON-NLS-1$
  public static final String FUNCTIONAL_CHAIN_INVOLVEMENT_FUNCTION_TO_LOGICALFUNCTION_1 = "f2a12f38-f199-40f1-b970-53a66f34902a"; //$NON-NLS-1$
  public static final String FUNCTIONALCHAIN_8 = "a0561c8a-f066-4e95-9755-bbaa60af9717"; //$NON-NLS-1$
  public static final String FUNCTIONALCHAIN_4 = "2fabc693-6c2a-4505-8c7d-efeeb820c30e"; //$NON-NLS-1$
  public static final String FUNCTIONALCHAIN_3 = "1537d57d-0218-4a5f-9d34-51d997e076ba"; //$NON-NLS-1$
  public static final String FUNCTIONALCHAIN_2 = "3d1d095c-d192-43de-8c19-227a9a907c87"; //$NON-NLS-1$
  public static final String FUNCTIONALCHAIN_1 = "20ebe9be-232f-479f-a810-5fc88972edc6"; //$NON-NLS-1$
  public static final String DIAGRAM_NAME = "[LFCD] FunctionalChain 5"; //$NON-NLS-1$

  Session session;
  SessionContext context;
  TransactionalEditingDomain ted;
  DiagramContext diagramContext;
  DDiagram diagram;

  @Override
  protected String getRequiredTestModel() {
    return "FCDCollapsingTest";
  }

  @Override
  public void test() throws Exception {
    session = getSessionForTestModel(getRequiredTestModel());
    context = new SessionContext(session);
    ted = session.getTransactionalEditingDomain();
    diagramContext = new OpenDiagramStep(context, DIAGRAM_NAME).run();
    diagram = diagramContext.getDiagram();
    // At the end of each test case, remember to un-collapse all containers that are programmatically collapsed.
    testCase0();
    testCase1();
    testCase2();
    testCase3();
    testCase4();
    testCase5();
    testCase6();
    testCase7();
    testCase8();
  }

  /**
   * Source and target in the same collapsed parent.
   */
  public void testCase8() {
    DNodeContainer fc1Container = (DNodeContainer) diagramContext.getView(FUNCTIONALCHAIN_1);
    DNodeContainer fc2Container = (DNodeContainer) diagramContext.getView(FUNCTIONALCHAIN_2);
    DNodeContainer fc3Container = (DNodeContainer) diagramContext.getView(FUNCTIONALCHAIN_3);

    DiagramHelper.collapseContainer(ted, fc1Container);
    DiagramHelper.collapseContainer(ted, fc2Container);
    DiagramHelper.collapseContainer(ted, fc3Container);
    diagramContext.refreshDiagram();

    Assert.assertEquals("Existing edge from FCR1 to FCR2", true,
        diagramContext.hasEdge(FUNCTIONAL_CHAIN_REFERENCE_TO_FUNCTIONALCHAIN_1,
            FUNCTIONAL_CHAIN_REFERENCE_TO_FUNCTIONALCHAIN_2,
            FUNCTIONAL_CHAIN_INVOLVEMENT_LINK_TO_FUNCTIONALEXCHANGE_1));

    DiagramHelper.unCollapseContainer(ted, fc1Container);
    DiagramHelper.unCollapseContainer(ted, fc2Container);
    diagramContext.refreshDiagram();

    Assert.assertEquals("No edge from FCR1 to FCR2", false,
        diagramContext.hasEdge(FUNCTIONAL_CHAIN_REFERENCE_TO_FUNCTIONALCHAIN_1,
            FUNCTIONAL_CHAIN_REFERENCE_TO_FUNCTIONALCHAIN_2,
            FUNCTIONAL_CHAIN_INVOLVEMENT_LINK_TO_FUNCTIONALEXCHANGE_1));

    DiagramHelper.unCollapseContainer(ted, fc3Container);
    diagramContext.refreshDiagram();
  }

  /**
   * Source collapsed, target collapsed, target parent not collapsed
   */
  public void testCase7() {
    DNodeContainer fc2Container = (DNodeContainer) diagramContext.getView(FUNCTIONALCHAIN_2);
    DNodeContainer fc8Container = (DNodeContainer) diagramContext.getView(FUNCTIONALCHAIN_8);

    Assert.assertEquals("No edge from FCR8 to FCR2", false,
        diagramContext.hasEdge(FUNCTIONAL_CHAIN_REFERENCE_TO_FUNCTIONALCHAIN_8,
            FUNCTIONAL_CHAIN_REFERENCE_TO_FUNCTIONALCHAIN_2,
            FUNCTIONAL_CHAIN_INVOLVEMENT_LINK_TO_FUNCTIONALEXCHANGE_3));

    DiagramHelper.collapseContainer(ted, fc2Container); // target is collapsed but its parent (FCR3) is not collapsed.
    DiagramHelper.collapseContainer(ted, fc8Container); // source
    diagramContext.refreshDiagram();

    Assert.assertEquals("Existing edge from FCR8 to FCR2", true,
        diagramContext.hasEdge(FUNCTIONAL_CHAIN_REFERENCE_TO_FUNCTIONALCHAIN_8,
            FUNCTIONAL_CHAIN_REFERENCE_TO_FUNCTIONALCHAIN_2,
            FUNCTIONAL_CHAIN_INVOLVEMENT_LINK_TO_FUNCTIONALEXCHANGE_3));

    DiagramHelper.unCollapseContainer(ted, fc2Container); // target
    diagramContext.refreshDiagram();

    Assert.assertEquals("No edge from FCR8 to FCR2", false,
        diagramContext.hasEdge(FUNCTIONAL_CHAIN_REFERENCE_TO_FUNCTIONALCHAIN_8,
            FUNCTIONAL_CHAIN_REFERENCE_TO_FUNCTIONALCHAIN_2,
            FUNCTIONAL_CHAIN_INVOLVEMENT_LINK_TO_FUNCTIONALEXCHANGE_3));

    DiagramHelper.unCollapseContainer(ted, fc8Container); // source
    diagramContext.refreshDiagram();
  }

  /**
   * Source collapsed, target parent collapsed
   */
  public void testCase6() {
    DNodeContainer fc3Container = (DNodeContainer) diagramContext.getView(FUNCTIONALCHAIN_3);
    DNodeContainer fc8Container = (DNodeContainer) diagramContext.getView(FUNCTIONALCHAIN_8);

    Assert.assertEquals("No edge from FCR8 to FCR3", false,
        diagramContext.hasEdge(FUNCTIONAL_CHAIN_REFERENCE_TO_FUNCTIONALCHAIN_8,
            FUNCTIONAL_CHAIN_REFERENCE_TO_FUNCTIONALCHAIN_3,
            FUNCTIONAL_CHAIN_INVOLVEMENT_LINK_TO_FUNCTIONALEXCHANGE_3));

    DiagramHelper.collapseContainer(ted, fc8Container); // source
    DiagramHelper.collapseContainer(ted, fc3Container); // target parent
    diagramContext.refreshDiagram();

    Assert.assertEquals("Existing edge from FCR8 to FCR3", true,
        diagramContext.hasEdge(FUNCTIONAL_CHAIN_REFERENCE_TO_FUNCTIONALCHAIN_8,
            FUNCTIONAL_CHAIN_REFERENCE_TO_FUNCTIONALCHAIN_3,
            FUNCTIONAL_CHAIN_INVOLVEMENT_LINK_TO_FUNCTIONALEXCHANGE_3));

    DiagramHelper.unCollapseContainer(ted, fc3Container); // target parent
    diagramContext.refreshDiagram();

    Assert.assertEquals("No edge from FCR8 to FCR3", false,
        diagramContext.hasEdge(FUNCTIONAL_CHAIN_REFERENCE_TO_FUNCTIONALCHAIN_8,
            FUNCTIONAL_CHAIN_REFERENCE_TO_FUNCTIONALCHAIN_3,
            FUNCTIONAL_CHAIN_INVOLVEMENT_LINK_TO_FUNCTIONALEXCHANGE_3));

    DiagramHelper.unCollapseContainer(ted, fc8Container);
    diagramContext.refreshDiagram();
  }

  /**
   * Source parent not collapsed, source collapsed, target collapsed
   */
  public void testCase5() {
    DNodeContainer fc2Container = (DNodeContainer) diagramContext.getView(FUNCTIONALCHAIN_2);
    DNodeContainer fc4Container = (DNodeContainer) diagramContext.getView(FUNCTIONALCHAIN_4);

    Assert.assertEquals("No edge from FCR2 to FCR4", false,
        diagramContext.hasEdge(FUNCTIONAL_CHAIN_REFERENCE_TO_FUNCTIONALCHAIN_2,
            FUNCTIONAL_CHAIN_REFERENCE_TO_FUNCTIONALCHAIN_4,
            FUNCTIONAL_CHAIN_INVOLVEMENT_LINK_TO_FUNCTIONALEXCHANGE_2));

    DiagramHelper.collapseContainer(ted, fc2Container); // source is collapsed but its parent (FCR3) is not collapsed.
    DiagramHelper.collapseContainer(ted, fc4Container); // target
    diagramContext.refreshDiagram();

    Assert.assertEquals("Existing edge from FCR2 to FCR4", true,
        diagramContext.hasEdge(FUNCTIONAL_CHAIN_REFERENCE_TO_FUNCTIONALCHAIN_2,
            FUNCTIONAL_CHAIN_REFERENCE_TO_FUNCTIONALCHAIN_4,
            FUNCTIONAL_CHAIN_INVOLVEMENT_LINK_TO_FUNCTIONALEXCHANGE_2));

    DiagramHelper.unCollapseContainer(ted, fc4Container); // target
    diagramContext.refreshDiagram();

    Assert.assertEquals("No edge from FCR2 to FCR4", false,
        diagramContext.hasEdge(FUNCTIONAL_CHAIN_REFERENCE_TO_FUNCTIONALCHAIN_2,
            FUNCTIONAL_CHAIN_REFERENCE_TO_FUNCTIONALCHAIN_4,
            FUNCTIONAL_CHAIN_INVOLVEMENT_LINK_TO_FUNCTIONALEXCHANGE_2));

    DiagramHelper.unCollapseContainer(ted, fc2Container); // source
    diagramContext.refreshDiagram();
  }

  /**
   * Source parent collapsed, target collapsed
   */
  public void testCase4() {
    DNodeContainer fc3Container = (DNodeContainer) diagramContext.getView(FUNCTIONALCHAIN_3);
    DNodeContainer fc4Container = (DNodeContainer) diagramContext.getView(FUNCTIONALCHAIN_4);

    Assert.assertEquals("No edge from FCR3 to FCR4", false,
        diagramContext.hasEdge(FUNCTIONAL_CHAIN_REFERENCE_TO_FUNCTIONALCHAIN_3,
            FUNCTIONAL_CHAIN_REFERENCE_TO_FUNCTIONALCHAIN_4,
            FUNCTIONAL_CHAIN_INVOLVEMENT_LINK_TO_FUNCTIONALEXCHANGE_2));

    DiagramHelper.collapseContainer(ted, fc3Container); // source parent
    DiagramHelper.collapseContainer(ted, fc4Container); // target
    diagramContext.refreshDiagram();

    Assert.assertEquals("Existing edge from FCR3 to FCR4", true,
        diagramContext.hasEdge(FUNCTIONAL_CHAIN_REFERENCE_TO_FUNCTIONALCHAIN_3,
            FUNCTIONAL_CHAIN_REFERENCE_TO_FUNCTIONALCHAIN_4,
            FUNCTIONAL_CHAIN_INVOLVEMENT_LINK_TO_FUNCTIONALEXCHANGE_2));

    DiagramHelper.unCollapseContainer(ted, fc4Container); // source parent
    diagramContext.refreshDiagram();

    Assert.assertEquals("Existing edge from FCR3 to FCIF3", true,
        diagramContext.hasEdge(FUNCTIONAL_CHAIN_REFERENCE_TO_FUNCTIONALCHAIN_3,
            FUNCTIONAL_CHAIN_INVOLVEMENT_FUNCTION_TO_LOGICALFUNCTION_3,
            FUNCTIONAL_CHAIN_INVOLVEMENT_LINK_TO_FUNCTIONALEXCHANGE_2));

    DiagramHelper.unCollapseContainer(ted, fc3Container); // target
    diagramContext.refreshDiagram();
  }

  /**
   * Source collapsed, target collapsed
   */
  public void testCase3() {
    DNodeContainer fc1Container = (DNodeContainer) diagramContext.getView(FUNCTIONALCHAIN_1);
    DNodeContainer fc2Container = (DNodeContainer) diagramContext.getView(FUNCTIONALCHAIN_2);

    Assert.assertEquals("No edge from FCR1 to FCR2", false,
        diagramContext.hasEdge(FUNCTIONAL_CHAIN_REFERENCE_TO_FUNCTIONALCHAIN_1,
            FUNCTIONAL_CHAIN_REFERENCE_TO_FUNCTIONALCHAIN_2,
            FUNCTIONAL_CHAIN_INVOLVEMENT_LINK_TO_FUNCTIONALEXCHANGE_1));

    DiagramHelper.collapseContainer(ted, fc1Container); // source
    DiagramHelper.collapseContainer(ted, fc2Container); // target
    diagramContext.refreshDiagram();

    Assert.assertEquals("Existing edge from FCR1 to FCR2", true,
        diagramContext.hasEdge(FUNCTIONAL_CHAIN_REFERENCE_TO_FUNCTIONALCHAIN_1,
            FUNCTIONAL_CHAIN_REFERENCE_TO_FUNCTIONALCHAIN_2,
            FUNCTIONAL_CHAIN_INVOLVEMENT_LINK_TO_FUNCTIONALEXCHANGE_1));

    DiagramHelper.unCollapseContainer(ted, fc1Container); // source
    DiagramHelper.unCollapseContainer(ted, fc2Container); // target
    diagramContext.refreshDiagram();
    Assert.assertEquals("No edge from FCR1 to FCR2", false,
        diagramContext.hasEdge(FUNCTIONAL_CHAIN_REFERENCE_TO_FUNCTIONALCHAIN_1,
            FUNCTIONAL_CHAIN_REFERENCE_TO_FUNCTIONALCHAIN_2,
            FUNCTIONAL_CHAIN_INVOLVEMENT_LINK_TO_FUNCTIONALEXCHANGE_1));
  }

  /**
   * Source not collapsed; target collapsed
   */
  public void testCase2() {
    DNodeContainer fc2Container = (DNodeContainer) diagramContext.getView(FUNCTIONALCHAIN_2);

    Assert.assertEquals("No edge from FCIF1 to FCR2", false,
        diagramContext.hasEdge(FUNCTIONAL_CHAIN_INVOLVEMENT_FUNCTION_TO_LOGICALFUNCTION_1,
            FUNCTIONAL_CHAIN_REFERENCE_TO_FUNCTIONALCHAIN_2,
            FUNCTIONAL_CHAIN_INVOLVEMENT_LINK_TO_FUNCTIONALEXCHANGE_1));

    DiagramHelper.collapseContainer(ted, fc2Container); // target
    diagramContext.refreshDiagram();

    Assert.assertEquals("Existing edge from FCIF1 to FCR2", true,
        diagramContext.hasEdge(FUNCTIONAL_CHAIN_INVOLVEMENT_FUNCTION_TO_LOGICALFUNCTION_1,
            FUNCTIONAL_CHAIN_REFERENCE_TO_FUNCTIONALCHAIN_2,
            FUNCTIONAL_CHAIN_INVOLVEMENT_LINK_TO_FUNCTIONALEXCHANGE_1));

    DiagramHelper.unCollapseContainer(ted, fc2Container); // target
    diagramContext.refreshDiagram();
    Assert.assertEquals("No edge from FCIF1 to FCR2", false,
        diagramContext.hasEdge(FUNCTIONAL_CHAIN_INVOLVEMENT_FUNCTION_TO_LOGICALFUNCTION_1,
            FUNCTIONAL_CHAIN_REFERENCE_TO_FUNCTIONALCHAIN_2,
            FUNCTIONAL_CHAIN_INVOLVEMENT_LINK_TO_FUNCTIONALEXCHANGE_1));
  }

  /**
   * Source collapsed, target not collapsed
   */
  public void testCase1() {
    DNodeContainer fc1Container = (DNodeContainer) diagramContext.getView(FUNCTIONALCHAIN_1);

    Assert.assertEquals("No edge from FCR1 to FCIF2", false,
        diagramContext.hasEdge(FUNCTIONAL_CHAIN_REFERENCE_TO_FUNCTIONALCHAIN_1,
            FUNCTIONAL_CHAIN_INVOLVEMENT_FUNCTION_TO_LOGICALFUNCTION_2,

            FUNCTIONAL_CHAIN_INVOLVEMENT_LINK_TO_FUNCTIONALEXCHANGE_1));
    DiagramHelper.collapseContainer(ted, fc1Container); // source
    diagramContext.refreshDiagram();

    Assert.assertEquals("Existing edge from FCR1 to FCIF2", true,
        diagramContext.hasEdge(FUNCTIONAL_CHAIN_REFERENCE_TO_FUNCTIONALCHAIN_1,
            FUNCTIONAL_CHAIN_INVOLVEMENT_FUNCTION_TO_LOGICALFUNCTION_2,
            FUNCTIONAL_CHAIN_INVOLVEMENT_LINK_TO_FUNCTIONALEXCHANGE_1));

    DiagramHelper.unCollapseContainer(ted, fc1Container); // source
    diagramContext.refreshDiagram();
    Assert.assertEquals("No edge from FCR1 to FCIF2", false,
        diagramContext.hasEdge(FUNCTIONAL_CHAIN_REFERENCE_TO_FUNCTIONALCHAIN_1,
            FUNCTIONAL_CHAIN_INVOLVEMENT_FUNCTION_TO_LOGICALFUNCTION_2,
            FUNCTIONAL_CHAIN_INVOLVEMENT_LINK_TO_FUNCTIONALEXCHANGE_1));
  }

  public void testCase0() {
    DSemanticDecorator fcr3View = diagramContext.getView(FUNCTIONAL_CHAIN_REFERENCE_TO_FUNCTIONALCHAIN_3);
    DSemanticDecorator fc3View = diagramContext.getView(FUNCTIONALCHAIN_3);
    if (fc3View instanceof DNodeContainer && fcr3View instanceof DNodeContainer) {
      DNodeContainer fc3Container = (DNodeContainer) fc3View;
      DNodeContainer fcr3Container = (DNodeContainer) fcr3View;

      String fcr3ContainerNameBeforeCollapsing = fcr3Container.getName();
      String fcr3ContainerNameAfterCollapsing = fcr3ContainerNameBeforeCollapsing
          + FunctionalChainServices.FCR_CONTAINER_COLLAPSED_INDICATOR;

      DiagramHelper.collapseContainer(ted, fc3Container);
      diagramContext.refreshDiagram();
      Assert.assertEquals("The label of collapsed FCR container must be followed by the indicator",
          fcr3ContainerNameAfterCollapsing, fcr3Container.getName());

      DiagramHelper.unCollapseContainer(ted, fc3Container);
      diagramContext.refreshDiagram();
      Assert.assertEquals("The label of un-collapsed FCR container must not be followed by the indicator",
          fcr3ContainerNameBeforeCollapsing, fcr3Container.getName());
    }
  }
  
  @Override
  protected void undoAllChanges() {
    // TODO Undo all changes does not work with this test
  }
}
