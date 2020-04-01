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

import java.util.Collection;

import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DDiagramElementContainer;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.description.DAnnotation;
import org.polarsys.capella.core.sirius.analysis.DiagramServices;
import org.polarsys.capella.test.diagram.common.ju.context.DiagramContext;
import org.polarsys.capella.test.diagram.common.ju.step.tools.CreateAbstractDNodeTool;

public class InsertLineInTitleBlockTool extends CreateAbstractDNodeTool<DDiagramElementContainer> {

  DAnnotation titleBlock;
  int linePrev;

  // add as param title block a + num lines
  public InsertLineInTitleBlockTool(DiagramContext context, String toolName, DAnnotation titleBlock, int linePrev,
      String containerId) {
    super(context, toolName, containerId);
    this.titleBlock = titleBlock;
    this.linePrev = linePrev;
  }

  protected Collection<DDiagramElement> getDiagramElements(DSemanticDecorator element) {
    return DiagramServices.getDiagramServices().getFlatOwnedDiagramElements(element);
  }

  @Override
  protected void preRunTest() {
    super.preRunTest();
  }

  @Override
  protected void postRunTest() {
    newElements = getDiagramElements(getContainerView());
    //
    // assertTrue(newElements.size() == 1);
    // // get linesof tb
    // prevLine = titleBlock.eGet(linePrev);
    //
    // newLine = titleBlock.eGet(linePRev + 1);
  }
}