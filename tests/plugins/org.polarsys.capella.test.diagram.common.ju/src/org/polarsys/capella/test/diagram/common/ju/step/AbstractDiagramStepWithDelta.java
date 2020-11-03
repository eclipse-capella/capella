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
package org.polarsys.capella.test.diagram.common.ju.step;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.emf.common.util.EList;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.GraphicalFilter;
import org.eclipse.sirius.diagram.HideLabelFilter;
import org.junit.Assert;
import org.polarsys.capella.core.data.information.Port;
import org.polarsys.capella.core.sirius.analysis.IDiagramNameConstants;
import org.polarsys.capella.test.diagram.common.ju.context.DiagramContext;

/**
 * This class must not be used. It test only how many elements have been displayed/hidden, not which elements
 */
@Deprecated
public abstract class AbstractDiagramStepWithDelta extends AbstractDiagramStep {

  /**
   * flag in order to enable computation of new {@link DDiagramElement} on the target {@link DDiagram}
   */
  protected boolean _isDeltaOnElementsMustBeReturned;

  /** pure internal attribute */
  private List<DDiagramElement> _preExecutionList = null;
  /** pure internal attribute */
  private List<DDiagramElement> _delta = null;

  /**
   * Constructor
   * 
   * @param checkDelta
   */
  public AbstractDiagramStepWithDelta(DiagramContext context, boolean checkDelta) {
    super(context);
    _isDeltaOnElementsMustBeReturned = checkDelta;
  }

  /**
   * @see org.polarsys.capella.test.common.AbstractExtendedTest#preTestRun()
   */
  @Override
  protected void preRunTest() {
    if (_isDeltaOnElementsMustBeReturned) {
      _preExecutionList = new ArrayList<DDiagramElement>();
      _preExecutionList.addAll(getDiagramContext().getDiagram().getDiagramElements());

    }
    super.preRunTest();
  }

  /**
   * @see org.polarsys.capella.test.common.AbstractExtendedTest#postTestRun()
   */
  @Override
  protected void postRunTest() {
    super.postRunTest();

    // Get delta on diagram, if needed
    if (_isDeltaOnElementsMustBeReturned) {
      List<DDiagramElement> postExecutionlist = new ArrayList<DDiagramElement>();
      postExecutionlist.addAll(getDiagramContext().getDiagram().getDiagramElements());
      List<DDiagramElement> addedElements = new ArrayList<DDiagramElement>(
          getDiagramContext().getDiagram().getDiagramElements());
      addedElements.removeAll(_preExecutionList);

      if (postExecutionlist.size() > _preExecutionList.size()) {
        // Some elements have been added
        postExecutionlist.removeAll(_preExecutionList);
        _delta = postExecutionlist;
      } else if (postExecutionlist.size() < _preExecutionList.size()) {
        // Some elements have been deleted
        _preExecutionList.removeAll(postExecutionlist);
        _delta = _preExecutionList;
      } else { // No changes
        _delta = new ArrayList<DDiagramElement>();
      }

      // For port added elements, check whether the label is hidden
      if (addedElements != null) {
        for (DDiagramElement currentElement : addedElements) {
          if ((currentElement.getTarget() instanceof Port) && (currentElement instanceof DNode)
              && !(getDiagramContext().getDiagram().getDescription().getName()
                  .equals(IDiagramNameConstants.EPBS_ARCHITECTURE_BLANK_DIAGRAM_NAME))) {
            // foe EAB diagram, the label of ports is visible
            boolean hasLabelFilter = false;
            EList<GraphicalFilter> graphicalFilters = currentElement.getGraphicalFilters();
            for (GraphicalFilter filter : graphicalFilters) {
              if (filter instanceof HideLabelFilter) {
                hasLabelFilter = true;
                break;
              }
            }
            Assert.assertTrue(MessageFormat.format(Messages.visiblePortLabelError, currentElement.getName()),
                hasLabelFilter);
          }
        }
      }
    }
  }

  /**
   * get delta about contained {@link DDiagramElement}. Note that Delta hereby means added or deleted element on the
   * target diagram. on current {@link DDiagram} .
   * 
   * @return
   */
  public List<DDiagramElement> getDeltaOnDiagramElement() {
    Assert.assertTrue(Messages.cannotGetDeltaOnDiagramElement, _isDeltaOnElementsMustBeReturned);
    return _delta;
  }

  /**
   * @return number of expected new elements after the invoked action
   */
  protected abstract int getNumberofExpectedNewElement();

  /**
   * Assert that the number of elements expected match the number of elements effectively created in a diagram
   */
  protected void checkDeltaNumberOfElementsCreated() {
    Assert.assertEquals(getNumberofExpectedNewElement(), getDeltaOnDiagramElement().size());
  }

  protected boolean CompareActualAndExpectedLists(final List<Point> actualPointsList_p,
      final List<Point> expectedPointsList_p) {
    if (expectedPointsList_p.containsAll(actualPointsList_p) && actualPointsList_p.containsAll(expectedPointsList_p)) {
      return true;
    }

    List<Point> actualPointsList = new ArrayList<Point>(actualPointsList_p);
    actualPointsList.removeAll(expectedPointsList_p);

    List<Point> expectedPointsList = new ArrayList<Point>(expectedPointsList_p);
    expectedPointsList.removeAll(actualPointsList_p);

    for (Point actualPoint : actualPointsList) {
      boolean same;
      int x = actualPoint.x;
      int y = actualPoint.y;

      same = expectedPointsList.contains(actualPoint) || expectedPointsList.contains(new Point(x - 1, y - 1))
          || expectedPointsList.contains(new Point(x - 1, y)) || expectedPointsList.contains(new Point(x - 1, y + 1))
          || expectedPointsList.contains(new Point(x, y - 1)) || expectedPointsList.contains(new Point(x, y + 1))
          || expectedPointsList.contains(new Point(x + 1, y - 1)) || expectedPointsList.contains(new Point(x + 1, y))
          || expectedPointsList.contains(new Point(x + 1, y + 1));
      if (same == false) {
        return false;
      }
    }
    return true;
  }
}
