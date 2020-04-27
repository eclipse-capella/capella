/*******************************************************************************
 * Copyright (c) 2016, 2019 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.diagram.tools.ju.sdfb;

import java.util.Collections;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.intro.IIntroPart;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;
import org.polarsys.capella.core.data.fa.FunctionalExchange;
import org.polarsys.capella.core.platform.sirius.ui.actions.CreateCategoriesController;
import org.polarsys.capella.core.sirius.analysis.constants.IToolNameConstants;
import org.polarsys.capella.test.diagram.common.ju.availableXDFBDiagramTools.XDFBCreateContainerTools;
import org.polarsys.capella.test.diagram.common.ju.availableXDFBDiagramTools.XDFBCreateEdgeTools;
import org.polarsys.capella.test.diagram.common.ju.context.XDFBDiagram;
import org.polarsys.capella.test.diagram.common.ju.step.tools.InsertRemoveTool;
import org.polarsys.capella.test.diagram.common.ju.wrapper.utils.DiagramHelper;
import org.polarsys.capella.test.diagram.tools.ju.model.EmptyProject;
import org.polarsys.capella.test.framework.context.SessionContext;
import org.polarsys.capella.test.framework.model.GenericModel;

public class UndoOnHideSystemFunction extends EmptyProject {
  @Override
  public void test() throws Exception {
    // Close Intro, else Undo action does not work.
    IIntroPart introViewPart = PlatformUI.getWorkbench().getIntroManager().getIntro();
    if (introViewPart != null) {
      PlatformUI.getWorkbench().getIntroManager().closeIntro(introViewPart);
    }

    Session session = getSession(getRequiredTestModel());
    final SessionContext context = new SessionContext(session);

    // Create a SDFB diagram
    XDFBDiagram sdfbDiagram = XDFBDiagram.createDiagram(context, SA__ROOT_SF);

    // Create a main SF
    String function1Id = sdfbDiagram.createContainer(sdfbDiagram.getDiagramId(),
        XDFBCreateContainerTools.CREATE_FUNCTION);

    // Create 2 sub SFs under the main SF
    String function1_1Id = sdfbDiagram.createContainer(function1Id, XDFBCreateContainerTools.CREATE_FUNCTION);
    String function1_2Id = sdfbDiagram.createContainer(function1Id, XDFBCreateContainerTools.CREATE_FUNCTION);

    // Create a FE between the 2 bus SFs
    String feId = sdfbDiagram.createEdge(function1_1Id, function1_2Id, XDFBCreateEdgeTools.CREATE_FUNCTIONAL_EXCHANGE);

    final FunctionalExchange fe1 = sdfbDiagram.getSessionContext().getSemanticElement(feId);

    // Create an Exchange Category referencing the FE
    AbstractReadWriteCommand createExchangeCategory = new AbstractReadWriteCommand() {
      @Override
      public void run() {
        List<EObject> feList = Collections.singletonList((EObject) fe1);
        CreateCategoriesController createCategoryController = CreateCategoriesController
            .createCreateCategoriesController(feList);
        createCategoryController.createAndAttachCategory(feList, GenericModel.EXCHANGE_CATEGORY_1);
      }
    };
    context.getExecutionManager().execute(createExchangeCategory);

    // Hide the second sub SF
    new InsertRemoveTool(sdfbDiagram, IToolNameConstants.TOOL_SDFB_SHOW_HIDE_FUNCTIONS, function1Id)
        .remove(function1_2Id);

    // Undo Hide (Ctrl + Z) - Two Undos are needed (first: undo the refresh diagram - done by the test tool, second:
    // recover the sub SF).
    IEditorPart editorPart = DiagramHelper.getDiagramEditor(session, sdfbDiagram.getDiagram());
    IActionBars actionBars = editorPart.getEditorSite().getActionBars();
    actionBars.getGlobalActionHandler(ActionFactory.UNDO.getId()).run();
    actionBars.getGlobalActionHandler(ActionFactory.UNDO.getId()).run();

    // Check outgoingEdges
    DSemanticDecorator functionalExchangeDEdge = sdfbDiagram.getView(feId);
    assertTrue("Functional Exchange must be represented by a DEdge in a diagram",
        functionalExchangeDEdge instanceof DEdge);
    assertEquals("Only 1 DEdge is expected on the output port", 1,
        ((DEdge) functionalExchangeDEdge).getSourceNode().getOutgoingEdges().size());
  }
}
