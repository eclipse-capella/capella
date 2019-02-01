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

import static org.junit.Assert.fail;

import java.util.Collections;
import java.util.List;

import org.eclipse.sirius.diagram.DNodeList;
import org.eclipse.sirius.diagram.DNodeListElement;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.polarsys.capella.test.diagram.common.ju.context.DiagramContext;
import org.polarsys.capella.test.diagram.common.ju.wrapper.utils.DiagramHelper;

public class CreateNodeElementTool extends CreateAbstractDNodeTool<DNodeListElement> {

  public CreateNodeElementTool(DiagramContext context, String toolName, String containerView) {
    super(context, toolName, containerView, DNodeListElement.class);
  }

  public CreateNodeElementTool(DiagramContext context, String toolName, String containerView, String newIdentifier) {
    super(context, toolName, containerView, newIdentifier, DNodeListElement.class);
  }
  
  public CreateNodeElementTool(DiagramContext context, String toolName, String targetContainerView, String containerView, String newIdentifier) {
    super(context, toolName, targetContainerView, containerView, newIdentifier, DNodeListElement.class);
  }

  @Override
  protected void preRunTest() {
    super.preRunTest();
    
    DSemanticDecorator element = getContainerView();
    elements.addAll(getNodeElements(element));
  }

  public List<DNodeListElement> getNodeElements(DSemanticDecorator element) {
    if (element instanceof DNodeList) {
      DNodeList nodeList = (DNodeList) element;
      return nodeList.getOwnedElements();
    }
    return Collections.emptyList();
  }

  @Override
  protected void postRunTest() {
    DSemanticDecorator element = getContainerView();
    newElements = DiagramHelper.getOwnedElements(element);
    newElements.addAll(getNodeElements(element));
    newElements.removeAll(elements);

    if (newElements.size() != expectedNewElements()) {
      fail();
    }
    
    if ((expectedDiagramElementType != null) && !(expectedDiagramElementType.isInstance(newElements.iterator().next()))) {
      fail();
    }

    if (!(newElements.iterator().next() instanceof DNodeListElement)) {
      fail();
    }
  }

}
