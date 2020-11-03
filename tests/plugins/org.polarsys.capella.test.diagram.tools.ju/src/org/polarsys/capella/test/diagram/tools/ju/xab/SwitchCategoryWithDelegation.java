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
package org.polarsys.capella.test.diagram.tools.ju.xab;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.description.DiagramElementMapping;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.polarsys.capella.core.libraries.model.ICapellaModel;
import org.polarsys.capella.core.libraries.utils.ScopeModelWrapper;
import org.polarsys.capella.core.sirius.analysis.DDiagramContents;
import org.polarsys.capella.core.sirius.analysis.constants.IToolNameConstants;
import org.polarsys.capella.core.sirius.analysis.constants.MappingConstantsHelper;
import org.polarsys.capella.shared.id.handler.IScope;
import org.polarsys.capella.shared.id.handler.IdManager;
import org.polarsys.capella.test.diagram.common.ju.context.DiagramContext;
import org.polarsys.capella.test.diagram.common.ju.step.crud.OpenDiagramStep;
import org.polarsys.capella.test.diagram.common.ju.step.tools.SwitchTool;
import org.polarsys.capella.test.diagram.tools.ju.model.SwitchCategory;
import org.polarsys.capella.test.framework.context.SessionContext;

import junit.framework.Test;

/**
 * Delegation links should be replaced by delegation categories when a Switch is applied
 *
 */
public class SwitchCategoryWithDelegation extends SwitchCategory {

  @Override
  public void test() throws Exception {
    // Prepare test model
    Session session = getSessionForTestModel(getRequiredTestModel());
    SessionContext context = new SessionContext(session);
    DiagramContext diagramContext = new OpenDiagramStep(context, PAB_Category_Delegation_Test).run();
    DDiagramContents diagramContents = new DDiagramContents(diagramContext.getDiagram());
    ICapellaModel model = getTestModel(getRequiredTestModel());
    IScope scope = new ScopeModelWrapper(model);

    // Get test model elements
    EObject physicalCategory = IdManager.getInstance().getEObject(PHYSICALLINKCATEGORY_1, scope);
    EObject pp1OfPC11 = IdManager.getInstance().getEObject(PC_11_PP_1, scope);
    EObject pp1OfPC21 = IdManager.getInstance().getEObject(PC_21_PP_1, scope);
    EObject componentCategory = IdManager.getInstance().getEObject(COMPONENTEXCHANGECATEGORY_1, scope);
    EObject cp1OfPC5 = IdManager.getInstance().getEObject(PC_5_CP_1, scope);
    EObject cp1OfPC6 = IdManager.getInstance().getEObject(PC_6_CP_1, scope);
    EObject cp1OfPC7 = IdManager.getInstance().getEObject(PC_7_CP_1, scope);
    EObject cp1OfPC8 = IdManager.getInstance().getEObject(PC_8_CP_1, scope);

    // Do the Switch
    new SwitchTool(diagramContext, IToolNameConstants.TOOL_PAB_INSERT_REMOVE_PHYSICAL_LINKS_CATEGORIES)
        .insert(PHYSICALLINKCATEGORY_1);
    new SwitchTool(diagramContext, IToolNameConstants.TOOL_PAB_INSERT_REMOVE_COMPONENT_EXCHANGES_CATEGORIES)
        .insert(COMPONENTEXCHANGECATEGORY_1);

    diagramContext.refreshDiagram();

    DiagramElementMapping physicalCategoryMapping = diagramContents
        .getMapping(MappingConstantsHelper.getMappingABPhysicalCategory(diagramContext.getDiagram()));

    boolean pl1Switched = false;
    boolean pl1DelegationToPC1Switched = false;
    boolean pl1DelegationToPC2Switched = false;
    boolean pl1DelegationToPC7Switched = false;
    boolean pl1DelegationToPC8Switched = false;

    for (DDiagramElement diagramElement : diagramContents.getDiagramElements(physicalCategoryMapping)) {
      if (diagramElement.getTarget() == physicalCategory) {
        DEdge physicalCategoryEdge = (DEdge) diagramElement;
        DSemanticDecorator sourceNode = (DSemanticDecorator) physicalCategoryEdge.getSourceNode();
        DSemanticDecorator targetNode = (DSemanticDecorator) physicalCategoryEdge.getTargetNode();
        if (sourceNode.getTarget() == physicalCategory && targetNode.getTarget() == physicalCategory)
          pl1Switched = true;
        else if (sourceNode.getTarget() == physicalCategory && targetNode.getTarget() == pp1OfPC11)
          pl1DelegationToPC1Switched = true;
        else if (sourceNode.getTarget() == physicalCategory && targetNode.getTarget() == pp1OfPC21)
          pl1DelegationToPC2Switched = true;
        else if (sourceNode.getTarget() == physicalCategory && targetNode.getTarget() == cp1OfPC7)
          pl1DelegationToPC7Switched = true;
        else if (sourceNode.getTarget() == physicalCategory && targetNode.getTarget() == cp1OfPC8)
          pl1DelegationToPC8Switched = true;
      }
    }

    // Verify that physical categories for delegation links have been created
    assertTrue("There isn't a Physical Category between PC 1 and PC 2", pl1Switched);
    assertTrue("There isn't a Physical Category between PC 1 and PC 11", pl1DelegationToPC1Switched);
    assertTrue("There isn't a Physical Category between PC 2 and PC 21", pl1DelegationToPC2Switched);
    assertTrue("There isn't a Physical Category between PC 1 and PC 7", pl1DelegationToPC7Switched);
    assertTrue("There isn't a Physical Category between PC 2 and PC 8", pl1DelegationToPC8Switched);

    DiagramElementMapping componentCategoryMapping = diagramContents
        .getMapping(MappingConstantsHelper.getMappingABComponentCategory(diagramContext.getDiagram()));

    boolean ce1Switched = false;
    boolean ce1DelegationToPC5Switched = false;
    boolean ce1DelegationToPC6Switched = false;

    for (DDiagramElement diagramElement : diagramContents.getDiagramElements(componentCategoryMapping)) {
      if (diagramElement.getTarget() == componentCategory) {
        DEdge physicalCategoryEdge = (DEdge) diagramElement;
        DSemanticDecorator sourceNode = (DSemanticDecorator) physicalCategoryEdge.getSourceNode();
        DSemanticDecorator targetNode = (DSemanticDecorator) physicalCategoryEdge.getTargetNode();
        if (sourceNode.getTarget() == componentCategory && targetNode.getTarget() == componentCategory)
          ce1Switched = true;
        else if (sourceNode.getTarget() == componentCategory && targetNode.getTarget() == cp1OfPC5)
          ce1DelegationToPC5Switched = true;
        else if (sourceNode.getTarget() == componentCategory && targetNode.getTarget() == cp1OfPC6)
          ce1DelegationToPC6Switched = true;
      }
    }

    // Verify that component categories for delegation component exchanges have been created
    assertTrue("There isn't a Component Category between PC 3 and PC 4", ce1Switched);
    assertTrue("There isn't a Component Category between PC 3 and PC 5", ce1DelegationToPC5Switched);
    assertTrue("There isn't a Component Category between PC 4 and PC 6", ce1DelegationToPC6Switched);

    // Verify that delegation links have been removed from the diagram
    diagramContext.hasntViews(PL_11, PL_21, COMPONENT_PORT_ALLOCATION_TO_PC_7_CP_1,
        COMPONENT_PORT_ALLOCATION_TO_PC_8_CP_1, D_2, D_3);
  }

  public static Test suite() {
    return new SwitchCategoryWithDelegation();
  }
}
