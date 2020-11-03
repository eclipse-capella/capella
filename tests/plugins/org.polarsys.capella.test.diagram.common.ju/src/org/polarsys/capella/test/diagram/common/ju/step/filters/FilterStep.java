/*******************************************************************************
 * Copyright (c) 2016, 2020 THALES GLOBAL SERVICES.
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

import static org.junit.Assert.assertFalse;

import org.polarsys.capella.test.diagram.common.ju.context.DiagramContext;
import org.polarsys.capella.test.diagram.common.ju.step.AbstractDiagramStep;
import org.polarsys.capella.test.diagram.common.ju.wrapper.utils.FilterOnDiagramHelper;

public class FilterStep extends AbstractDiagramStep<DiagramContext> {

  boolean initialized = false;
  boolean activate = false;

  String filterName;

  String containerId;
  String[] insertedElements;
  String[] removedElements;

  public FilterStep(DiagramContext context, String filterName) {
    super(context);
    this.filterName = filterName;
  }

  protected void initialize(boolean activate, String[] insertedElements_input, String[] removedElements_input) {
    this.activate = activate;

    insertedElements = insertedElements_input;
    if (insertedElements == null) {
      insertedElements = new String[0];
    }
    removedElements = removedElements_input;
    if (removedElements == null) {
      removedElements = new String[0];
    }
    initialized = true;
  }

  @Override
  public DiagramContext run() {
    if (!initialized) {
      assertFalse("Please use insert/remove methods instead of run.", true);
    }
    return super.run();
  }

  public void activate(String... toRemove) {
    initialize(true, null, toRemove);
    run();
  }

  public void desactivate(String... toInsert) {
    initialize(false, toInsert, null);
    run();
  }

  /**
   * @see org.polarsys.capella.test.common.AbstractExtendedTest#postTestRun()
   */
  @Override
  protected void postRunTest() {
    super.postRunTest();

    for (String identifier : insertedElements) {
      getDiagramContext().hasView(identifier);
    }
    for (String identifier : removedElements) {
      getDiagramContext().hasFilteredView(identifier);
    }
  }

  @Override
  public DiagramContext getResult() {
    return getDiagramContext();
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.polarsys.capella.test.diagram.common.ju.api.AbstractTestStep#runTest()
   */
  @Override
  protected void runTest() {
    if (activate) {
      FilterOnDiagramHelper.applyFilterOnDiagram(getDiagramContext().getDiagram(), filterName);
    } else {
      FilterOnDiagramHelper.removeFilterOnDiagram(getDiagramContext().getDiagram(), filterName);
    }
  }

}
