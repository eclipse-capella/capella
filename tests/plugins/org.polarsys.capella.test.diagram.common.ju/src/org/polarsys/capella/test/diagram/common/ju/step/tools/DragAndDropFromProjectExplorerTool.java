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

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.polarsys.capella.test.diagram.common.ju.context.DiagramContext;
import org.polarsys.capella.test.diagram.common.ju.wrapper.utils.ArgumentType;

public class DragAndDropFromProjectExplorerTool extends DragAndDropTool {
  EObject droppedElement;

  public DragAndDropFromProjectExplorerTool(DiagramContext context, String toolName, EObject droppedElement,
      String containerView) {
    super(context, toolName, containerView);
    this.droppedElement = droppedElement;
  }

  @Override
  public DDiagramElement getResult() {
    DDiagramElement view = _newElements.iterator().next();
    elementView = view.getName();
    return super.getResult();
  }

  @Override
  protected void initToolArguments() {
    DSemanticDecorator element = getExecutionContext().getView(containerView);

    _toolWrapper.setArgumentValue(ArgumentType.CONTAINER_VIEW, element);
    _toolWrapper.setArgumentValue(ArgumentType.DROPPEDELEMENT, droppedElement);

  }
}
