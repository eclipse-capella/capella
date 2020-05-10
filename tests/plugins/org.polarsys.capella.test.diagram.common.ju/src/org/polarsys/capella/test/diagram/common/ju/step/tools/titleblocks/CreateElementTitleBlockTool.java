/*******************************************************************************
 * Copyright (c) 2020 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.test.diagram.common.ju.step.tools.titleblocks;

import static org.junit.Assert.assertTrue;

import java.util.Iterator;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DDiagramElementContainer;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.description.DAnnotation;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.diagram.helpers.TitleBlockHelper;
import org.polarsys.capella.test.diagram.common.ju.context.DiagramContext;
import org.polarsys.capella.test.diagram.common.ju.step.tools.CreateAbstractDNodeTool;
import org.polarsys.capella.test.diagram.common.ju.wrapper.utils.DiagramHelper;

public class CreateElementTitleBlockTool extends CreateAbstractDNodeTool<DDiagramElementContainer> {
  String diagramID;

  public CreateElementTitleBlockTool(DiagramContext context, String toolName, String containerView, String diagramID) {
    super(context, toolName, containerView);
    this.diagramID = diagramID;
  }

  @Override
  protected void preRunTest() {
    super.preRunTest();
  }

  @Override
  protected DSemanticDecorator getContainerView() {
    return getDiagramContext().getView(diagramID);
  }

  @Override
  protected void postRunTest() {
    newElements = DiagramHelper.getOwnedElements(getContainerView());

    newElements.removeAll(elements);

    // contains an edge and a container TB
    assertTrue(newElements.size() == 2);

    Iterator<DDiagramElement> it = newElements.iterator();
    DDiagramElement element = it.next();
    assertTrue(element.getTarget() instanceof DAnnotation);

    DAnnotation elementTB = (DAnnotation) element.getTarget();

    assertTrue("A new Element Title Block should have been created.", elementTB.getSource().equals(TitleBlockHelper.ELEMENT_TITLE_BLOCK));

    DSemanticDecorator elementView = getDiagramContext().getView(containerView);

    assertTrue("The created Element Title Block does not have a reference to " + containerView,
        elementTB.getReferences().contains(elementView.getTarget()));
    
    DEdge edge = (DEdge) it.next();
    assertTrue("An edge to " + containerView + " should have been created.",
        containerView.equals(((CapellaElement) edge.getTarget()).getId()));
  }

  @Override
  public DDiagramElementContainer getResult() {
    return (DDiagramElementContainer) newElements.iterator().next();
  }
}