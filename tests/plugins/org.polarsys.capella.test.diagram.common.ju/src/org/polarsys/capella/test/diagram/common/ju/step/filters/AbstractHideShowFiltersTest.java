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

import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.diagram.DDiagram;
import org.junit.Assert;
import org.polarsys.capella.test.diagram.common.ju.context.DiagramContext;
import org.polarsys.capella.test.diagram.common.ju.step.AbstractDiagramStepWithDelta;

/**
 * An abstract test class to call on an hide/show filter in a diagram with delta support on diagram.
 */
@Deprecated
public abstract class AbstractHideShowFiltersTest extends AbstractDiagramStepWithDelta {

  protected String _filterName;
  protected int _numberOfElementFilter;

  /**
   * @param filterName_p
   * @param checkDelta_p
   */
  public AbstractHideShowFiltersTest(DiagramContext context, String filterName_p, boolean checkDelta_p) {
    super(context, checkDelta_p);
    _filterName = filterName_p;
    _numberOfElementFilter = 0;
  }

  public String getFilterName() {
    return _filterName;
  }

  public void testHideShowFilters() {
    // DO NOTHING
  }

  /**
   * Get the diagram context
   * 
   * @return
   */
  protected abstract DDiagram getDiagram();

  /**
   * Get the map of context objects
   * 
   * @return
   */
  protected abstract Map<String, EObject> getObjects();

  /**
   * Assert that no element have been created or removed
   */
  @Override
  protected void checkDeltaNumberOfElementsCreated() {
    Assert.assertEquals(getNumberofExpectedNewElement(), getDeltaOnDiagramElement().size());
  }

  /**
   * Show/Hide filter action shouldn't be deleted the element in Diagram
   * 
   * @see org.polarsys.capella.test.common.diagram.AbstractDiagramTestWithDelta#getNumberofExpectedNewElement()
   */
  @Override
  protected int getNumberofExpectedNewElement() {
    int expectedDeltaNumberOfElements = 0;
    return expectedDeltaNumberOfElements;
  }

  protected abstract int getNumberOfFilterElementsExpected();

  protected void checkNumberOfElementsFilter() {
    Assert.assertEquals(Messages.expectedAmountNotFound, getNumberOfFilterElementsExpected(), _numberOfElementFilter);
  }
}
