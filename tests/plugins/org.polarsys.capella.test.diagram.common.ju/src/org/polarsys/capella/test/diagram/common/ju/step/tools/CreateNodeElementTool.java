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

  /*
   * Constructors deprecated due to the use of the "newIdentifier" variable
   * This should not be used as it introduces problems with the Insert/Remove actions
   */
  @Deprecated
  public CreateNodeElementTool(DiagramContext context, String toolName, String containerView, String newIdentifier) {
    super(context, toolName, containerView, newIdentifier, DNodeListElement.class);
  }

  @Deprecated
  public CreateNodeElementTool(DiagramContext context, String toolName, String targetContainerView,
      String containerView, String newIdentifier) {
    super(context, toolName, targetContainerView, containerView, newIdentifier, DNodeListElement.class);
  }

  @Override
  protected void preRunTest() {
    super.preRunTest();

    DSemanticDecorator element = getContainerView();
    elements.addAll(getNodeElements(element));
  }

  public List<DNodeListElement> getNodeElements(DSemanticDecorator parentContainer) {

    if (parentContainer instanceof DNodeList) {
      DNodeList nodeList = (DNodeList) parentContainer;
      return nodeList.getOwnedElements();
    }
    return Collections.emptyList();
  }

  @Override
  protected void postRunTest() {

    DSemanticDecorator parentContainerView = getContainerView();

    newElements = DiagramHelper.getOwnedElements(parentContainerView);
    newElements.addAll(getNodeElements(parentContainerView));
    newElements.removeAll(elements);

    if (newElements.size() != expectedNewElements()) {
      fail();
    }

    if ((expectedDiagramElementType != null)
        && !(expectedDiagramElementType.isInstance(newElements.iterator().next()))) {
      fail();
    }
  }

}
