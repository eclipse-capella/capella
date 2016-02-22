/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.test.diagram.common.ju.step.tools;

import static org.junit.Assert.assertFalse;

import java.util.Collection;

import org.eclipse.sirius.diagram.AbstractDNode;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.polarsys.capella.test.diagram.common.ju.context.DiagramContext;
import org.polarsys.capella.test.diagram.common.ju.wrapper.utils.ArgumentType;
import org.polarsys.capella.test.diagram.common.ju.wrapper.utils.DiagramHelper;

/**
 * Test step to call an AbstractDNode (DDiagramElementContainer, DNode...) creation tool.
 */
public class CreateAbstractDNodeTool<T extends AbstractDNode> extends AbstractToolStep<T> {
  protected String newIdentifier;
  protected String containerView;
  protected Class<T> expectedDiagramElementType;
  
  protected Collection<DDiagramElement> innerElementsBefore;
  protected Collection<DDiagramElement> newElements;
  
  public CreateAbstractDNodeTool(DiagramContext context, String toolName, String containerView, Class<T> expectedNodeType) {
    super(context, toolName);
    this.containerView = containerView;
    this.expectedDiagramElementType = expectedNodeType;
  }

  public CreateAbstractDNodeTool(DiagramContext context, String toolName, String newIdentifier, String containerView_p, Class<T> expectedNodeType) {
    this(context, toolName, containerView_p, expectedNodeType);
    this.newIdentifier = newIdentifier;
  }

  @Override
  protected void preRunTest() {
    super.preRunTest();
    DSemanticDecorator element = getExecutionContext().getView(containerView);
    innerElementsBefore = DiagramHelper.getOwnedElements(element);
  }

  @Override
  protected void dispose() {
    super.dispose();
    innerElementsBefore = null;
    newElements = null;
  }

  @Override
  protected void postRunTest() {
    super.postRunTest();
    DSemanticDecorator element = getExecutionContext().getView(containerView);
    newElements = DiagramHelper.getOwnedElements(element);
    newElements.removeAll(innerElementsBefore);

    if (newElements.size() != 1) {
      assertFalse(true);
    }
    if (!(expectedDiagramElementType.isInstance(newElements.iterator().next()))) {
      assertFalse(true);
    }

  }

  @Override
  public T getResult() {
    T view = expectedDiagramElementType.cast(newElements.iterator().next());
    if (newIdentifier != null) {
      getExecutionContext().putSemanticElement(newIdentifier, view.getTarget());
      getExecutionContext().putView(newIdentifier, view);
    }
    return view;
  }

  @Override
  protected void initToolArguments() {
    DSemanticDecorator element = getExecutionContext().getView(containerView);
    _toolWrapper.setArgumentValue(ArgumentType.CONTAINER, element.getTarget());
    _toolWrapper.setArgumentValue(ArgumentType.CONTAINER_VIEW, element);
  }
}
