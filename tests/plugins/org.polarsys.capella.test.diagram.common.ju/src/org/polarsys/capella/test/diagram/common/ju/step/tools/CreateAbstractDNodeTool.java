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
  protected String targetContainerView;
  protected String containerView;
  protected Class<T> expectedDiagramElementType;

  protected Collection<DDiagramElement> elements;
  protected Collection<DDiagramElement> newElements;

  public CreateAbstractDNodeTool(DiagramContext context, String toolName, String containerView) {
    this(context, toolName, containerView, (Class<T>) null);
  }

  public CreateAbstractDNodeTool(DiagramContext context, String toolName, String containerView,
      Class<T> expectedNodeType) {
    super(context, toolName);
    this.targetContainerView = containerView;
    this.containerView = containerView;
    this.expectedDiagramElementType = expectedNodeType;
  }

  public CreateAbstractDNodeTool(DiagramContext context, String toolName, String containerView, String newIdentifier,
      Class<T> expectedNodeType) {
    this(context, toolName, containerView, expectedNodeType);
    this.newIdentifier = newIdentifier;
  }

  public CreateAbstractDNodeTool(DiagramContext context, String toolName, String targetContainerView, String containerView,
      String newIdentifier, Class<T> expectedNodeType) {
    this(context, toolName, targetContainerView, expectedNodeType);
    this.newIdentifier = newIdentifier;
    this.containerView = containerView;
  }
  
  public CreateAbstractDNodeTool(DiagramContext context, String toolName, String containerView, String newIdentifier) {
    this(context, toolName, containerView, newIdentifier, null);
  }

  public CreateAbstractDNodeTool(DiagramContext context, String[] toolIdentifier, String containerView,
      Class<T> expectedNodeType) {
    super(context, toolIdentifier[0], toolIdentifier[1]);
    this.targetContainerView = containerView;
    this.containerView = containerView;
    this.expectedDiagramElementType = expectedNodeType;
  }

  public CreateAbstractDNodeTool(DiagramContext context, String[] toolIdentifier, String containerView) {
    this(context, toolIdentifier, containerView, (Class<T>) null);
  }

  public CreateAbstractDNodeTool(DiagramContext context, String[] toolIdentifier, String containerView,
      String newIdentifier, Class<T> expectedNodeType) {
    this(context, toolIdentifier, containerView, expectedNodeType);
    this.newIdentifier = newIdentifier;
  }

  public CreateAbstractDNodeTool(DiagramContext context, String[] toolIdentifier, String containerView,
      String newIdentifier) {
    this(context, toolIdentifier, containerView, newIdentifier, null);
  }

  @Override
  protected void preRunTest() {
    super.preRunTest();
    DSemanticDecorator element = getExecutionContext().getView(containerView);
    elements = DiagramHelper.getOwnedElements(element);
  }

  @Override
  protected void dispose() {
    super.dispose();
    elements = null;
    newElements = null;
  }

  @Override
  protected void postRunTest() {
    super.postRunTest();
    DSemanticDecorator element = getExecutionContext().getView(containerView);
    newElements = DiagramHelper.getOwnedElements(element);
    newElements.removeAll(elements);

    if (newElements.size() != 1) {
      assertFalse(true);
    }
    if ((expectedDiagramElementType != null) && !(expectedDiagramElementType.isInstance(newElements.iterator().next()))) {
      assertFalse(true);
    }

  }

  @Override
  public T getResult() {
    T view = (T) newElements.iterator().next();
    if (newIdentifier != null) {
      getExecutionContext().putSemanticElement(newIdentifier, view.getTarget());
      getExecutionContext().putView(newIdentifier, view);
    }
    return view;
  }

  @Override
  protected void initToolArguments() {
    DSemanticDecorator element = getExecutionContext().getView(targetContainerView);
    _toolWrapper.setArgumentValue(ArgumentType.CONTAINER, element.getTarget());
    _toolWrapper.setArgumentValue(ArgumentType.CONTAINER_VIEW, element);
  }
}
