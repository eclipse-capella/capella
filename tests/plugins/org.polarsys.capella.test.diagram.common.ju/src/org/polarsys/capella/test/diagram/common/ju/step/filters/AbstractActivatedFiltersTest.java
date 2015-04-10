/*******************************************************************************
 * Copyright (c) 2006, 2015 THALES GLOBAL SERVICES.
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
import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.eclipse.emf.common.util.EList;
import org.eclipse.sirius.diagram.description.filter.FilterDescription;
import org.polarsys.capella.test.diagram.common.ju.context.DiagramOpenExecutionContext;
import org.polarsys.capella.test.diagram.common.ju.step.AbstractDiagramStepWithDelta;

/**
 * Check the list of activated filters to check if they are still activated addition, deletion
 */
public abstract class AbstractActivatedFiltersTest extends AbstractDiagramStepWithDelta {

  /**
   * @param checkDelta_p
   */
  public AbstractActivatedFiltersTest(DiagramOpenExecutionContext context, boolean checkDelta_p) {
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
    EList<FilterDescription> activatedFilters = ((DiagramOpenExecutionContext) getExecutionContext()).getDiagram().getActivatedFilters();

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
    Assert.assertEquals(MessageFormat.format(Messages.filtersNotActivated, activatedFiltersNamesList, expectedActivatedFilters),
        expectedActivatedFilters.size(), activatedFilters.size());
    Assert.assertTrue(MessageFormat.format(Messages.filtersNotActivated, activatedFiltersNamesList, expectedActivatedFilters), found);
    super.postRunTest();
  }
}
