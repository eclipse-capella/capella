/*******************************************************************************
 * Copyright (c) 2019 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.diagram.filters.ju;

import java.util.HashMap;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DEdge;
import org.junit.Assert;
import org.polarsys.capella.common.helpers.EObjectExt;
import org.polarsys.capella.core.data.capellacore.CapellaElement;

public abstract class LabelFilterTestCase extends DiagramObjectFilterTestCase {

  protected List<String> expectedElementLabels = getExpectedElementLabels();

  /**
   * return a list of what the labels of the selected elements should be after the filter is applied the element inside
   * the getFilteredObjectIDs list should have the same index as its corresponding new label
   * 
   * @return
   */
  protected abstract List<String> getExpectedElementLabels();

  @Override
  protected void preRunTest() {

    int numberOfGivenElements = filteredObjetIDs.size();
    int numberOfGivenLabels = expectedElementLabels.size();

    Assert.assertTrue("The number of elements differs from the number of labels",
        numberOfGivenElements == numberOfGivenLabels);

    getCurrentDiagram();

    for (DDiagramElement elt : diagram.getDiagramElements()) {

      EObject target = elt.getTarget();
      if (target != null && target instanceof CapellaElement) {
        String targetId = ((CapellaElement) target).getId();
        if (filteredObjetIDs.contains(targetId)) {
          toBeFiltered.put(elt, targetId);
        }
      }
    }
  }

  @Override
  public void test() {

    preRunTest();
    checkAndInsertFilter();
    postRunTest();
  }

  @Override
  protected void postRunTest() {

    HashMap<String, String> elementIdToNewLabel = new HashMap<>();
    for (int i = 0; i < filteredObjetIDs.size(); i++) {
      elementIdToNewLabel.put(filteredObjetIDs.get(i), expectedElementLabels.get(i));
    }
    
    // check that each graphical element has its new corresponding expected label
    for (DDiagramElement currentObject : toBeFiltered.keySet()) {

      String currentLabel = null;
      
      /* Edges have 3 labels: beginLabel, centerLabel, endLabel
       * Those 3 labels will be merged with ";" as separator to create the final label
      */
      if (currentObject instanceof DEdge) {
        
        DEdge currentEdgeObject = (DEdge) currentObject;
        
        String beginLabelName = currentEdgeObject.getBeginLabel();
        String endLabelName = currentEdgeObject.getEndLabel();
        String centerLabelName = EObjectExt.getText(currentEdgeObject);
        
        currentLabel = beginLabelName + ";" + centerLabelName + ";" + endLabelName;
      }
      
      else {
        currentLabel = EObjectExt.getText(currentObject);
      }

      CapellaElement currentTarget = (CapellaElement) currentObject.getTarget();
      boolean labelIsAsExpected = elementIdToNewLabel.get(currentTarget.getId()).equals(currentLabel);

      Assert.assertTrue(
          "The current label " + currentLabel + " is not among the provided labels",
          labelIsAsExpected);
    }
  }
}
