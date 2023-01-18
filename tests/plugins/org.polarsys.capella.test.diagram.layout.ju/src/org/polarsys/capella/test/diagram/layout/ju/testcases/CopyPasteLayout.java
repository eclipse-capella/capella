/*******************************************************************************
 * Copyright (c) 2018, 2023 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.diagram.layout.ju.testcases;

import org.eclipse.sirius.business.api.session.Session;
import org.polarsys.capella.common.re.CatalogElement;
import org.polarsys.capella.test.diagram.common.ju.context.DiagramContext;
import org.polarsys.capella.test.diagram.common.ju.wrapper.utils.DiagramHelper;
import org.polarsys.capella.test.diagram.layout.ju.handlers.CompareLayoutHandler;
import org.polarsys.capella.test.diagram.layout.ju.model.ModelCopyLayout;
import org.polarsys.capella.test.framework.context.SessionContext;

public class CopyPasteLayout extends ModelCopyLayout {

  @Override
  public void test() throws Exception {

    DiagramHelper.setPreferenceAutoRefresh(true);
    DiagramHelper.setPrefereneRefreshOnOpening(true);
    DiagramHelper.setPreferenceCopyLayoutPrompt(false);
    DiagramHelper.setPreferenceCopyLayoutMode(true);
    
    Session session = getSessionForTestModel(getRequiredTestModels().get(0));
    SessionContext context = new SessionContext(session);

    checkFunctionLayout(context);
    checkComponentExchangeLayout(context);
    checkRECRPLLayout(context);
    checkTransitionLayout(context);
    checkFunctionalChains(context);
  }

  // A basic diagram with several functions
  protected void checkFunctionLayout(SessionContext context) {
    CompareLayoutHandler handler = new CompareLayoutHandler();

    DiagramContext source = DiagramContext.getDiagram(context, LAYOUT_A_SOURCE_FUNCTIONS);
    DiagramContext target = DiagramContext.getDiagram(context, LAYOUT_A_TARGET_FUNCTIONS);

    handler.copyPasteLayout(source.getDiagramDescriptor(), target.getDiagramDescriptor());
    handler.compare(source.getDiagramDescriptor(), target.getDiagramDescriptor(), false);

  }

  // A basic diagram with functional chains
  protected void checkFunctionalChains(SessionContext context) {
    CompareLayoutHandler handler = new CompareLayoutHandler();

    DiagramContext source = DiagramContext.getDiagram(context, LAYOUT_E_SOURCE_FUNCTIONAL_CHAINS);
    DiagramContext target = DiagramContext.getDiagram(context, LAYOUT_E_TARGET_FUNCTIONAL_CHAINS);

    handler.copyPasteLayout(source.getDiagramDescriptor(), target.getDiagramDescriptor());
    handler.compare(source.getDiagramDescriptor(), target.getDiagramDescriptor(), false);

  }

  // A copy layout between architectures displaying computed component exchanges
  protected void checkComponentExchangeLayout(SessionContext context) {
    CompareLayoutHandler handler = new CompareLayoutHandler();

    DiagramContext source = DiagramContext.getDiagram(context, LAYOUT_D_SOURCE_COMPONENT_EXCHANGES);
    DiagramContext target = DiagramContext.getDiagram(context, LAYOUT_D_TARGET_COMPONENT_EXCHANGES);

    handler.copyPasteLayout(source.getDiagramDescriptor(), target.getDiagramDescriptor());
    handler.compare(source.getDiagramDescriptor(), target.getDiagramDescriptor(), false);

  }

  // A copy-paste layout between a REC and a RPL
  protected void checkRECRPLLayout(SessionContext context) {
    CompareLayoutHandler handler = new CompareLayoutHandler();

    DiagramContext source = DiagramContext.getDiagram(context, LAYOUT_B_SOURCE_REC);
    DiagramContext target = DiagramContext.getDiagram(context, LAYOUT_B_TARGET_RPL);

    handler.getManager().getLayoutMatchPolicy().addMatchRPLtoREC(((CatalogElement) context.getSemanticElement(RPL)));
    handler.copyPasteLayout(source.getDiagramDescriptor(), target.getDiagramDescriptor());
    handler.compare(source.getDiagramDescriptor(), target.getDiagramDescriptor(), false);

  }

  // A copy-paste layout between a logical architecture and a physical architecture
  protected void checkTransitionLayout(SessionContext context) {
    CompareLayoutHandler handler = new CompareLayoutHandler();

    DiagramContext source = DiagramContext.getDiagram(context, LAYOUT_C_SOURCE_TRANSITION);
    DiagramContext target = DiagramContext.getDiagram(context, LAYOUT_C_TARGET_TRANSITION);

    handler.getManager().getLayoutMatchPolicy().addArchitectureBlankMappingMatches();
    handler.getManager().getLayoutMatchPolicy().addMatchForTransition(context.getSemanticElement(PHYSICAL_ARCHITECTURE));
    handler.copyPasteLayout(source.getDiagramDescriptor(), target.getDiagramDescriptor());
    handler.compare(source.getDiagramDescriptor(), target.getDiagramDescriptor(), false);

  }

}
