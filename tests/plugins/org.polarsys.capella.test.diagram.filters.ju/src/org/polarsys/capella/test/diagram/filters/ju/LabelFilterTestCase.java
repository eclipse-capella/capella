/*******************************************************************************
 * Copyright (c) 2019 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.test.diagram.filters.ju;

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.junit.Assert;
import org.polarsys.capella.common.helpers.EObjectLabelProviderHelper;
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

    for (DDiagramElement elt : diagram.getOwnedDiagramElements()) {

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

    // check that each graphical element has its new corresponding expected label
    int numberOfGivenLabels = expectedElementLabels.size();
    for (int i = 0; i < numberOfGivenLabels; i++) {

      DDiagramElement currentObject = toBeFiltered.keySet().iterator().next();
      String currentLabel = EObjectLabelProviderHelper.getText(currentObject);

      boolean labelIsAsExpected = expectedElementLabels.contains(currentLabel);

      Assert.assertTrue(
          "The expected label " + expectedElementLabels.get(i) + " is not equal to the current label " + currentLabel,
          labelIsAsExpected);
    }
  }
}
