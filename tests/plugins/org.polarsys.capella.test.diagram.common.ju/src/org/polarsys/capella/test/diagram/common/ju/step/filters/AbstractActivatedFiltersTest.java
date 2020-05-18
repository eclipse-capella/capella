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
import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.sirius.diagram.description.filter.FilterDescription;
import org.junit.Assert;
import org.polarsys.capella.test.diagram.common.ju.context.DiagramContext;
import org.polarsys.capella.test.diagram.common.ju.step.AbstractDiagramStepWithDelta;

/**
 * Check the list of activated filters to check if they are still activated addition, deletion
 */
@Deprecated
public abstract class AbstractActivatedFiltersTest extends AbstractDiagramStepWithDelta {

  /**
   * @param checkDelta_p
   */
  public AbstractActivatedFiltersTest(DiagramContext context, boolean checkDelta_p) {
    super(context, checkDelta_p);
  }

  @Override
  protected int getNumberofExpectedNewElement() {
    // In this test, no new element is expected
    return 0;
  }

  public void testActivatedFilters() {
    // DO NOTHING
  }

  protected abstract List<String> expectedActivatedFiltersList();

  /**
   * {@inheritDoc}
   */
  @Override
  protected void postRunTest() {
    List<String> expectedActivatedFilters = expectedActivatedFiltersList();
    expectedActivatedFilters.add("ModelExtensionFilter"); //$NON-NLS-1$ Filter activated for every diagram
    EList<FilterDescription> activatedFilters = getDiagramContext().getDiagram().getActivatedFilters();

    List<String> activatedFiltersNamesList = new ArrayList<String>();

    boolean found = false;
    for (FilterDescription filter : activatedFilters) {
      activatedFiltersNamesList.add(filter.getName());
    }
    for (FilterDescription filter : activatedFilters) {
      if (expectedActivatedFilters.contains(filter.getName()) || expectedActivatedFilters.contains(filter.getLabel())) {
        found = true;
      } else {
        found = false;
        break;
      }
    }
    Assert.assertEquals(
        MessageFormat.format(Messages.filtersNotActivated, activatedFiltersNamesList, expectedActivatedFilters),
        expectedActivatedFilters.size(), activatedFilters.size());
    Assert.assertTrue(
        MessageFormat.format(Messages.filtersNotActivated, activatedFiltersNamesList, expectedActivatedFilters), found);
    super.postRunTest();
  }
}
