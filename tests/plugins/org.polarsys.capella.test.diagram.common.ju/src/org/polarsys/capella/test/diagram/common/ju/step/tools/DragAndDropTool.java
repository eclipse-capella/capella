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

import static org.junit.Assert.assertEquals;

import java.util.Collection;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.polarsys.capella.test.diagram.common.ju.context.DiagramContext;
import org.polarsys.capella.test.diagram.common.ju.wrapper.utils.ArgumentType;
import org.polarsys.capella.test.diagram.common.ju.wrapper.utils.DiagramHelper;

public class DragAndDropTool extends AbstractToolStep<DDiagramElement> {

  protected String containerView;
  protected String elementView;

  protected Collection<DDiagramElement> _elements;
  protected Collection<DDiagramElement> _newElements;

  public DragAndDropTool(DiagramContext context, String toolName, String containerView) {
    super(context, toolName);
    this.containerView = containerView;
  }

  public DragAndDropTool(DiagramContext context, String toolName, String elementView, String containerView) {
    this(context, toolName, containerView);
    this.elementView = elementView;
  }

  @Override
  protected void preRunTest() {
    super.preRunTest();
    DSemanticDecorator element = getDiagramContext().getView(containerView);
    _elements = DiagramHelper.getOwnedElements(element);
  }

  @Override
  protected void dispose() {
    super.dispose();
    _elements = null;
    _newElements = null;
  }

  @Override
  protected void postRunTest() {
    super.postRunTest();
    DSemanticDecorator element = getDiagramContext().getView(containerView);
    _newElements = DiagramHelper.getOwnedElements(element);
    _newElements.removeAll(_elements);

    validateNewElements(_newElements);
  }

  /**
   * This default implementation checks if there is exactly one new element, but subclasses may override.
   * 
   * @param newElements
   *          the new elements after the drop
   */
  protected void validateNewElements(Collection<DDiagramElement> newElements) {
    assertEquals(1, _newElements.size());
  }

  @Override
  public DDiagramElement getResult() {
    DDiagramElement view = _newElements.iterator().next();

    if (elementView != null) {
      getExecutionContext().putSemanticElement(elementView, view.getTarget());
      getDiagramContext().putView(elementView, view);
    }

    return view;
  }

  @Override
  protected void initToolArguments() {
    DSemanticDecorator droppedElement = getDiagramContext().getView(elementView);
    DSemanticDecorator element = getDiagramContext().getView(containerView);
    EObject droppedElementSemantic = getExecutionContext().getSemanticElement(elementView);

    _toolWrapper.setArgumentValue(ArgumentType.CONTAINER_VIEW, element);
    if (droppedElement != null)
      _toolWrapper.setArgumentValue(ArgumentType.DROPPEDELEMENT, droppedElement);
    else if (droppedElementSemantic != null)
      _toolWrapper.setArgumentValue(ArgumentType.DROPPEDELEMENT, droppedElementSemantic);
  }

}
