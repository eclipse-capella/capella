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
package org.polarsys.capella.test.diagram.common.ju.step.filters;

import java.text.MessageFormat;

import junit.framework.Assert;

import org.eclipse.emf.common.util.EList;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.description.filter.CompositeFilterDescription;
import org.eclipse.sirius.diagram.description.filter.FilterDescription;
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

  public ChangeFilter(DiagramContext context, String filterName, ChangeType changeType, int nbOfExpectedHiddenElements) {
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
    int nbElementsBeforeFilter = getExecutionContext().getDiagram().getDiagramElements().size();

    // Apply/Remove filter
    FilterDescription filterDescription;
    if (ChangeType.ADD == changeType) {
      filterDescription = FilterOnDiagramHelper.applyFilterOnDiagram(getExecutionContext().getDiagram(), filterName);
    } else {
      filterDescription = FilterOnDiagramHelper.removeFilterOnDiagram(getExecutionContext().getDiagram(), filterName);
    }
    Assert.assertNotNull(MessageFormat.format("Filter \"{0}\" not found for diagram \"{1}\"", filterName,
        getExecutionContext().getDiagram().getName()), filterDescription);

    // Count hidden elements
    int numberOfHiddenElements = 0;
    EList<DDiagramElement> diagramElements = getExecutionContext().getDiagram().getDiagramElements();
    for (DDiagramElement dDiagramElement : diagramElements) {
      if (!dDiagramElement.isVisible()) {
        numberOfHiddenElements++;
      }
    }
    // Check number of hidden elements
    Assert.assertEquals(numberOfHiddenElements, nbOfexpectedHiddenElements);
    if (ChangeType.ADD == changeType) {
      FilterOnDiagramHelper.checkFilteredElementsInDiagram(getExecutionContext().getDiagram(),
          (CompositeFilterDescription) filterDescription);
    } else {
      FilterOnDiagramHelper.checkShowElementsInDiagram(getExecutionContext().getDiagram(),
          (CompositeFilterDescription) filterDescription);
    }
    // Check diagram still contains the same number of elements
    int nbElementsAfterFilter = getExecutionContext().getDiagram().getDiagramElements().size();
    Assert.assertEquals(nbElementsBeforeFilter, nbElementsAfterFilter);
  }
}
