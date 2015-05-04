/*******************************************************************************
 * Copyright (c) 2006, 2015 THALES GLOBAL SERVICES.
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

import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.polarsys.capella.test.diagram.common.ju.context.DiagramContext;
import org.polarsys.capella.test.diagram.common.ju.wrapper.utils.ArgumentType;
import org.polarsys.capella.test.diagram.common.ju.wrapper.utils.DiagramHelper;

public class DragAndDropTool extends AbstractToolStep<DDiagramElement> {

  String containerView;
  String elementView;

  Collection<DDiagramElement> _elements;
  Collection<DDiagramElement> _newElements;

  public DragAndDropTool(DiagramContext context, String toolName, String elementView_p, String containerView_p) {
    super(context, toolName);
    containerView = containerView_p;
    elementView = elementView_p;
  }

  @Override
  protected void preRunTest() {
    super.preRunTest();
    DSemanticDecorator element = getExecutionContext().getView(containerView);
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
    DSemanticDecorator element = getExecutionContext().getView(containerView);
    _newElements = DiagramHelper.getOwnedElements(element);
    _newElements.removeAll(_elements);

    if (_newElements.size() != 1) {
      assertFalse(true);
    }

  }

  @Override
  public DDiagramElement getResult() {
    DDiagramElement view = _newElements.iterator().next();
    return view;
  }

  @Override
  protected void initToolArguments() {
    DSemanticDecorator droppedElement = getExecutionContext().getView(elementView);
    DSemanticDecorator element = getExecutionContext().getView(containerView);

    _toolWrapper.setArgumentValue(ArgumentType.CONTAINER_VIEW, element);
    _toolWrapper.setArgumentValue(ArgumentType.DROPPEDELEMENT, droppedElement);

  }
}
