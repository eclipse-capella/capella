/*******************************************************************************
 * Copyright (c) 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.diagram.common.ju.step.tools.titleblocks;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Collection;
import java.util.List;

import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DDiagramElementContainer;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.description.DAnnotation;
import org.polarsys.capella.core.diagram.helpers.TitleBlockHelper;
import org.polarsys.capella.core.sirius.analysis.DiagramServices;
import org.polarsys.capella.core.sirius.analysis.TitleBlockServices;
import org.polarsys.capella.core.sirius.analysis.preferences.TitleBlockPreferencesInitializer;
import org.polarsys.capella.test.diagram.common.ju.context.DiagramContext;
import org.polarsys.capella.test.diagram.common.ju.step.tools.CreateAbstractDNodeTool;

public class CreateDiagramTitleBlockTool extends CreateAbstractDNodeTool<DDiagramElementContainer> {

  public CreateDiagramTitleBlockTool(DiagramContext context, String toolName, String containerView) {
    super(context, toolName, containerView);
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
    newElements.removeAll(elements);

    assertFalse(newElements.isEmpty());

    DDiagramElement element = newElements.stream().filter(x -> x.getTarget() instanceof DAnnotation
        && ((DAnnotation) (x.getTarget())).getSource().equals(TitleBlockHelper.DIAGRAM_TITLE_BLOCK)).findFirst().get();
    assertTrue(element != null);
    assertTrue(element.getTarget() instanceof DAnnotation);

    DAnnotation titleBlock = (DAnnotation) element.getTarget();

    assertTrue(titleBlock.getSource().equals(TitleBlockHelper.DIAGRAM_TITLE_BLOCK));

    int numLines = TitleBlockPreferencesInitializer.getLinesNumber();
    int numCols = TitleBlockPreferencesInitializer.getColumnsNumber();

    List<DAnnotation> lines = TitleBlockHelper.getTitleBlockLines(titleBlock);
    assertTrue(numLines == lines.size());

    List<DAnnotation> columns = TitleBlockHelper.getTitleBlockCells(lines.get(0));
    assertTrue(numCols == columns.size());
  }

  @Override
  public DDiagramElementContainer getResult() {
    return (DDiagramElementContainer) newElements.iterator().next();
  }

  @Override
  public void contextOk() {
    super.contextOk();
    assertTrue(TitleBlockServices.getService().isValidCreateDiagramTitleBlock(getDiagramContext().getDiagram()));
  }

  public void checkAutocreate() {
    super.contextOk();
    assertTrue(TitleBlockServices.getService().hasADiagramTitleBlock(getDiagramContext().getDiagram())
        && !TitleBlockServices.getService().getVisibleDiagramTitleBlocks(getDiagramContext().getDiagram()).isEmpty());
}
}