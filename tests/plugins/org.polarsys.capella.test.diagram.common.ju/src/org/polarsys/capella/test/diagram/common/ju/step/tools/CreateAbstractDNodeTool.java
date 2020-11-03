/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.diagram.common.ju.step.tools;

import static org.junit.Assert.assertFalse;

import java.util.Collection;

import org.eclipse.sirius.diagram.AbstractDNode;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.test.diagram.common.ju.context.DiagramContext;
import org.polarsys.capella.test.diagram.common.ju.wrapper.utils.ArgumentType;
import org.polarsys.capella.test.diagram.common.ju.wrapper.utils.DiagramHelper;

/**
 * Test step to call an AbstractDNode (DDiagramElementContainer, DNode...) creation tool.
 */
public class CreateAbstractDNodeTool<T extends AbstractDNode> extends AbstractToolStep<T> {

  /*
   * The use of the newIdentifier variable is discouraged as it introduces problems with certain tools, mainly
   * Insert/Remove tools
   */
  @Deprecated
  protected String newIdentifier;

  protected String containerViewTarget;
  protected String containerView;
  protected Class<T> expectedDiagramElementType;
  protected Class<? extends CapellaElement> expectedTargetType;

  protected Collection<DDiagramElement> elements;
  protected Collection<DDiagramElement> newElements;

  public CreateAbstractDNodeTool(DiagramContext context, String toolName, String containerView) {
    this(context, toolName, containerView, (Class<T>) null);
  }

  public CreateAbstractDNodeTool(DiagramContext context, String toolName, String containerView,
      Class<T> expectedNodeType) {

    super(context, toolName);

    this.containerViewTarget = containerView;
    this.containerView = containerView;
    this.expectedDiagramElementType = expectedNodeType;
  }

  public CreateAbstractDNodeTool(DiagramContext context, String toolName, String containerViewTarget,
      String containerView, Class<T> expectedNodeType, Class<? extends CapellaElement> targetType) {

    this(context, toolName, containerView, expectedNodeType);
    this.expectedTargetType = targetType;
    this.containerViewTarget = containerViewTarget;
  }

  @Deprecated
  public CreateAbstractDNodeTool(DiagramContext context, String toolName, String containerView, String newIdentifier,
      Class<T> expectedNodeType) {
    this(context, toolName, containerView, expectedNodeType);
    this.newIdentifier = newIdentifier;
  }

  @Deprecated
  public CreateAbstractDNodeTool(DiagramContext context, String toolName, String targetContainerView,
      String containerView, String newIdentifier, Class<T> expectedNodeType) {
    this(context, toolName, targetContainerView, expectedNodeType);
    this.newIdentifier = newIdentifier;
    this.containerView = containerView;
    this.containerViewTarget = targetContainerView;
  }

  @Deprecated
  public CreateAbstractDNodeTool(DiagramContext context, String toolName, String containerView, String newIdentifier) {
    this(context, toolName, containerView, newIdentifier, null);
  }

  @Deprecated
  public CreateAbstractDNodeTool(DiagramContext context, String[] toolIdentifier, String containerView,
      Class<T> expectedNodeType) {

    super(context, toolIdentifier[0], toolIdentifier[1]);
    this.containerViewTarget = containerView;
    this.containerView = containerView;
    this.expectedDiagramElementType = expectedNodeType;
  }

  @Deprecated
  public CreateAbstractDNodeTool(DiagramContext context, String[] toolIdentifier, String containerView) {
    this(context, toolIdentifier, containerView, (Class<T>) null);
  }

  @Deprecated
  public CreateAbstractDNodeTool(DiagramContext context, String[] toolIdentifier, String containerView,
      String newIdentifier, Class<T> expectedNodeType) {
    this(context, toolIdentifier, containerView, expectedNodeType);
    this.newIdentifier = newIdentifier;
  }

  @Deprecated
  public CreateAbstractDNodeTool(DiagramContext context, String[] toolIdentifier, String containerView,
      String newIdentifier) {
    this(context, toolIdentifier, containerView, newIdentifier, null);
  }

  @Override
  protected void preRunTest() {
    super.preRunTest();
    DSemanticDecorator element = getContainerView();
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
    DSemanticDecorator element = getContainerView();
    newElements = DiagramHelper.getOwnedElements(element);
    newElements.removeAll(elements);

    if (newElements.size() != expectedNewElements()) {
      assertFalse(true);
    }
    if ((expectedDiagramElementType != null)
        && !(expectedDiagramElementType.isInstance(newElements.iterator().next()))) {
      assertFalse(true);
    }
    if ((expectedTargetType != null) && !(expectedTargetType.isInstance(newElements.iterator().next().getTarget()))) {
      assertFalse(true);
    }
  }

  protected DSemanticDecorator getContainerView() {
    return getDiagramContext().getView(containerView);
  }

  protected int expectedNewElements() {
    return 1;
  }

  @SuppressWarnings("unchecked")
  @Override
  public T getResult() {

    T createdElementView = (T) newElements.iterator().next();

    if (null != newIdentifier) {
      getExecutionContext().putSemanticElement(newIdentifier, createdElementView.getTarget());
      getDiagramContext().putView(newIdentifier, createdElementView);
    }

    else {

      String createdElementId = ((CapellaElement) createdElementView.getTarget()).getId();

      getExecutionContext().putSemanticElement(createdElementId, createdElementView.getTarget());
      getDiagramContext().putView(createdElementId, createdElementView);
    }

    return createdElementView;
  }

  @Override
  protected void initToolArguments() {

    DSemanticDecorator parentContainerView = getDiagramContext().getView(containerViewTarget);

    _toolWrapper.setArgumentValue(ArgumentType.CONTAINER, parentContainerView.getTarget());
    _toolWrapper.setArgumentValue(ArgumentType.CONTAINER_VIEW, parentContainerView);
  }
}
