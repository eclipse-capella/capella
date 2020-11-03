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
import org.eclipse.sirius.diagram.DDiagramElement;
import org.junit.Assert;
import org.polarsys.capella.core.data.fa.FunctionalChainInvolvementLink;
import org.polarsys.capella.core.sirius.analysis.FunctionalChainServices;
import org.polarsys.capella.test.diagram.common.ju.api.AbstractDiagramTestCase;
import org.polarsys.capella.test.diagram.common.ju.context.DiagramContext;
import org.polarsys.capella.test.diagram.common.ju.step.crud.OpenDiagramStep;
import org.polarsys.capella.test.framework.context.SessionContext;

public class FCDReferenceHierarchyTest extends AbstractDiagramTestCase {

  public static final String DIAGRAM_NAME = "[OPD] OperationalProcess 12"; //$NON-NLS-1$
  public static final String FUNCTIONAL_CHAIN_INVOLVEMENT_LINK_TO_INTERACTION_1 = "8bff7379-6210-4bc2-b8a6-caeb72302eac"; //$NON-NLS-1$

  public static final String OA1_NODE_IN_OP4_Uid = "_3V6bAEDUEemAlKyHZTSwUw";
  public static final String OA1_NODE_IN_OP1_Uid = "_sZGn1kDXEemAlKyHZTSwUw";
  public static final String OA1_NODE_IN_DIAGRAM_Uid = "_1ZlRMEDUEemAlKyHZTSwUw";

  public static final String OA2_NODE_IN_OP8_Uid = "_3V83Q0DUEemAlKyHZTSwUw";
  public static final String OA2_NODE_IN_OP6_Uid = "_sZH19EDXEemAlKyHZTSwUw";
  public static final String OA2_NODE_IN_DIAGRAM_Uid = "_1Zl4QEDUEemAlKyHZTSwUw";
  
  
  public static final String FCR_TO_OP1_IN_OP4_Uid = "5a778644-c540-44e3-8f9f-bd0e4aba7dcb"; //$NON-NLS-1$
  public static final String FCR_TO_OP1_IN_DIAGRAM_Uid = "9bf142c3-33c8-4348-9a5c-be275fbcbc22"; //$NON-NLS-1$
  public static final String FCR_TO_OP6_IN_OP8_Uid = "13d0ce43-6f0f-44c7-a780-a33a8a3bbdf7"; //$NON-NLS-1$
  public static final String FCR_TO_OP6_IN_DIAGRAM_Uid = "7b82f33c-6908-45b8-a599-1a7bc835fc94"; //$NON-NLS-1$

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

    FunctionalChainInvolvementLink link = context
        .getSemanticElement(FUNCTIONAL_CHAIN_INVOLVEMENT_LINK_TO_INTERACTION_1);

    FunctionalChainServices functionalChainServices = FunctionalChainServices.getFunctionalChainServices();

    DDiagramElement OA1_NODE_IN_OP4 = findDiagramElementByUid(diagram, OA1_NODE_IN_OP4_Uid);
    DDiagramElement OA1_NODE_IN_OP1 = findDiagramElementByUid(diagram, OA1_NODE_IN_OP1_Uid);
    DDiagramElement OA1_NODE_IN_DIAGRAM = findDiagramElementByUid(diagram, OA1_NODE_IN_DIAGRAM_Uid);

    DDiagramElement OA2_NODE_IN_OP8 = findDiagramElementByUid(diagram, OA2_NODE_IN_OP8_Uid);
    DDiagramElement OA2_NODE_IN_OP6 = findDiagramElementByUid(diagram, OA2_NODE_IN_OP6_Uid);
    DDiagramElement OA2_NODE_IN_DIAGRAM = findDiagramElementByUid(diagram, OA2_NODE_IN_DIAGRAM_Uid);
    
    Assert.assertTrue(functionalChainServices.checkRefHierarchyOfLink(link, OA1_NODE_IN_OP4, OA2_NODE_IN_OP8));
    
    Assert.assertFalse(functionalChainServices.checkRefHierarchyOfLink(link, OA1_NODE_IN_OP4, OA2_NODE_IN_OP6));
    Assert.assertFalse(functionalChainServices.checkRefHierarchyOfLink(link, OA1_NODE_IN_OP4, OA2_NODE_IN_DIAGRAM));
    
    Assert.assertFalse(functionalChainServices.checkRefHierarchyOfLink(link, OA1_NODE_IN_OP1, OA2_NODE_IN_OP8));
    Assert.assertFalse(functionalChainServices.checkRefHierarchyOfLink(link, OA1_NODE_IN_OP1, OA2_NODE_IN_OP6));
    Assert.assertFalse(functionalChainServices.checkRefHierarchyOfLink(link, OA1_NODE_IN_OP1, OA2_NODE_IN_DIAGRAM));
    
    Assert.assertFalse(functionalChainServices.checkRefHierarchyOfLink(link, OA1_NODE_IN_DIAGRAM, OA2_NODE_IN_OP8));
    Assert.assertFalse(functionalChainServices.checkRefHierarchyOfLink(link, OA1_NODE_IN_DIAGRAM, OA2_NODE_IN_OP6));
    Assert.assertFalse(functionalChainServices.checkRefHierarchyOfLink(link, OA1_NODE_IN_DIAGRAM, OA2_NODE_IN_DIAGRAM));
    
    DDiagramElement FCR_TO_OP1_IN_OP4 = (DDiagramElement) diagramContext.getView(FCR_TO_OP1_IN_OP4_Uid);
    DDiagramElement FCR_TO_OP1_IN_DIAGRAM = (DDiagramElement) diagramContext.getView(FCR_TO_OP1_IN_DIAGRAM_Uid);
    DDiagramElement FCR_TO_OP6_IN_OP8 = (DDiagramElement) diagramContext.getView(FCR_TO_OP6_IN_OP8_Uid);
    DDiagramElement FCR_TO_OP6_IN_DIAGRAM = (DDiagramElement) diagramContext.getView(FCR_TO_OP6_IN_DIAGRAM_Uid);
    
    Assert.assertTrue(functionalChainServices.checkRefHierarchyOfLink(link, FCR_TO_OP1_IN_OP4, FCR_TO_OP6_IN_OP8)); // between 2 collapsed container
    Assert.assertTrue(functionalChainServices.checkRefHierarchyOfLink(link, OA1_NODE_IN_OP4, FCR_TO_OP6_IN_OP8)); // a node and a collapsed container
    Assert.assertTrue(functionalChainServices.checkRefHierarchyOfLink(link, FCR_TO_OP1_IN_OP4, OA2_NODE_IN_OP8)); // a collapsed container and a node
    
    Assert.assertFalse(functionalChainServices.checkRefHierarchyOfLink(link, FCR_TO_OP1_IN_OP4, FCR_TO_OP6_IN_DIAGRAM));
    Assert.assertFalse(functionalChainServices.checkRefHierarchyOfLink(link, FCR_TO_OP1_IN_OP4, OA2_NODE_IN_DIAGRAM));
    
    Assert.assertFalse(functionalChainServices.checkRefHierarchyOfLink(link, FCR_TO_OP1_IN_DIAGRAM, FCR_TO_OP6_IN_OP8));
    Assert.assertFalse(functionalChainServices.checkRefHierarchyOfLink(link, FCR_TO_OP1_IN_DIAGRAM, FCR_TO_OP6_IN_DIAGRAM));
    Assert.assertFalse(functionalChainServices.checkRefHierarchyOfLink(link, FCR_TO_OP1_IN_DIAGRAM, OA2_NODE_IN_DIAGRAM));
    
    Assert.assertFalse(functionalChainServices.checkRefHierarchyOfLink(link, OA1_NODE_IN_DIAGRAM, FCR_TO_OP6_IN_OP8));
    Assert.assertFalse(functionalChainServices.checkRefHierarchyOfLink(link, OA1_NODE_IN_DIAGRAM, FCR_TO_OP6_IN_DIAGRAM));
    
  }

  public DDiagramElement findDiagramElementByUid(DDiagram diagram, String uid) {
    return diagram.getDiagramElements().stream().filter(e -> uid.equals(e.getUid())).findFirst().orElse(null);
  }
}
