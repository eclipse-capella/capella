/*******************************************************************************
 * Copyright (c) 2020, 2022 THALES GLOBAL SERVICES.
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

import static org.junit.Assert.assertTrue;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DNodeContainer;
import org.eclipse.sirius.viewpoint.description.DAnnotation;
import org.polarsys.capella.core.diagram.helpers.TitleBlockHelper;
import org.polarsys.capella.core.sirius.analysis.DiagramServices;
import org.polarsys.capella.test.diagram.common.ju.context.DiagramContext;

public class InsertColumnInTitleBlockTool extends InsertRemoveColumnInTitleBlockTool {

  public InsertColumnInTitleBlockTool(DiagramContext context, String toolName, String diagramID, DAnnotation titleBlock,
      int colNo) {
    super(context, toolName, diagramID, titleBlock, colNo);
  }

  @Override
  protected void postRunTest() {
    // a new col is added under the current selected column
    int newColsNo = TitleBlockHelper.getNumOfColumns(titleBlock);
    assertTrue(newColsNo == currentColsNo + 1);

    DNodeContainer titleBlockSpec = (DNodeContainer) DiagramServices.getDiagramServices()
        .getDiagramElement(getDiagramContext().getDiagram(), titleBlock);

    EList<DDiagramElement> diagramElements = titleBlockSpec.getOwnedDiagramElements();
    // Check that each cell of the column is at the same position in both the feature and the diagram

    int i = 0;
    for (EObject aReference : titleBlock.getReferences()) {
      if (aReference instanceof DAnnotation) {
        DNodeContainer newLineDiagramElement = (DNodeContainer) diagramElements.get(i);
        DDiagramElement newCellInThisLine = newLineDiagramElement.getOwnedDiagramElements().get(currentColNo);

        DAnnotation annotation = (DAnnotation) aReference;
        assertTrue(annotation.getReferences().get(currentColNo) == newCellInThisLine.getTarget());
        i++;
      }
    }
  }
}