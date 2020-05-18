/*******************************************************************************
 * Copyright (c) 2006, 2019 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.diagram.common.ju.step.filters;

import java.text.MessageFormat;

import org.eclipse.emf.common.util.EList;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.description.filter.CompositeFilterDescription;
import org.eclipse.sirius.diagram.description.filter.FilterDescription;
import org.junit.Assert;
import org.polarsys.capella.test.diagram.common.ju.context.DiagramContext;
import org.polarsys.capella.test.diagram.common.ju.step.AbstractDiagramStep;
import org.polarsys.capella.test.diagram.common.ju.wrapper.utils.FilterOnDiagramHelper;

/**
 * Change (ADD or REMOVE) filter (on a diagram) test step.
 */
public class ChangeFilter extends AbstractDiagramStep<DDiagramElement> {
  // ADD/REMOVE the given filter
  public static enum ChangeType {
    ADD, REMOVE
  }

  protected String filterName;
  protected ChangeType changeType;
  protected int nbOfexpectedHiddenElements;

  public ChangeFilter(DiagramContext context, String filterName, ChangeType changeType,
      int nbOfExpectedHiddenElements) {
    super(context);
    this.filterName = filterName;
    this.changeType = changeType;
    this.nbOfexpectedHiddenElements = nbOfExpectedHiddenElements;
  }

  @Override
  public DDiagramElement getResult() {
    return null;
  }

  @Override
  protected void runTest() {
    int nbElementsBeforeFilter = getDiagramContext().getDiagram().getDiagramElements().size();

    // Apply/Remove filter
    FilterDescription filterDescription;
    if (ChangeType.ADD == changeType) {
      filterDescription = FilterOnDiagramHelper.applyFilterOnDiagram(getDiagramContext().getDiagram(), filterName);
    } else {
      filterDescription = FilterOnDiagramHelper.removeFilterOnDiagram(getDiagramContext().getDiagram(), filterName);
    }
    Assert.assertNotNull(MessageFormat.format("Filter \"{0}\" not found for diagram \"{1}\"", filterName,
        getDiagramContext().getDiagramDescriptor().getName()), filterDescription);

    // Count hidden elements
    int numberOfHiddenElements = 0;
    EList<DDiagramElement> diagramElements = getDiagramContext().getDiagram().getDiagramElements();
    for (DDiagramElement dDiagramElement : diagramElements) {
      if (!dDiagramElement.isVisible()) {
        numberOfHiddenElements++;
      }
    }
    // Check number of hidden elements
    Assert.assertEquals(numberOfHiddenElements, nbOfexpectedHiddenElements);
    if (ChangeType.ADD == changeType) {
      FilterOnDiagramHelper.checkFilteredElementsInDiagram(getDiagramContext().getDiagram(),
          (CompositeFilterDescription) filterDescription);
    } else {
      FilterOnDiagramHelper.checkShowElementsInDiagram(getDiagramContext().getDiagram(),
          (CompositeFilterDescription) filterDescription);
    }
    // Check diagram still contains the same number of elements
    int nbElementsAfterFilter = getDiagramContext().getDiagram().getDiagramElements().size();
    Assert.assertEquals(nbElementsBeforeFilter, nbElementsAfterFilter);
  }
}
