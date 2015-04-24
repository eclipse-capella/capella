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
package org.polarsys.capella.test.diagram.common.ju.step.crud;

import junit.framework.Assert;

import org.eclipse.osgi.util.NLS;
import org.eclipse.sirius.diagram.DDiagram;
import org.polarsys.capella.test.diagram.common.ju.api.AbstractTestStep;
import org.polarsys.capella.test.diagram.common.ju.context.DiagramContext;
import org.polarsys.capella.test.diagram.common.ju.context.SessionContext;
import org.polarsys.capella.test.diagram.common.ju.step.Messages;
import org.polarsys.capella.test.diagram.common.ju.wrapper.utils.DiagramHelper;

/**
 * Test case that open a diagram i.e its editor. choice about refresh included, could be really useful default behavior of this test kept via the default
 * constructor.
 */
public class OpenDiagramStep extends AbstractTestStep<DiagramContext> {

  /** Is refresh operation included during the test? */
  private boolean _mustBeRefreshed;

  private DDiagram diagram;

  public OpenDiagramStep(SessionContext context_p, DDiagram diagram_p, boolean mustBeRefreshed) {
    super(context_p);
    diagram = diagram_p;
    _mustBeRefreshed = false;
  }

  public OpenDiagramStep(DiagramContext diagramContext_p) {
    this(diagramContext_p, false);
  }

  public OpenDiagramStep(SessionContext context_p, String diagramName) {
    this(context_p, (DDiagram) DiagramHelper.getDRepresentation(context_p.getSession(), diagramName), false);
  }

  public OpenDiagramStep(DiagramContext executionContext, boolean mustBeRefreshed) {
    this(executionContext, executionContext.getDiagram(), mustBeRefreshed);
  }

  public OpenDiagramStep(SessionContext context_p, DDiagram diagram_p) {
    this(context_p, diagram_p, false);
  }

  /**
   * @see org.polarsys.capella.test.common.AbstractExtendedTest#preTestRun()
   */
  @Override
  protected void preRunTest() {
    super.preRunTest();
    Assert.assertNotNull(Messages.nullDiagram, diagram);
  }

  /**
   * @see org.polarsys.capella.test.common.AbstractExtendedTest#getResult()
   */
  @Override
  public DiagramContext getResult() {
    if (getExecutionContext() instanceof DiagramContext) {
      return (DiagramContext) getExecutionContext();
    }
    return new DiagramContext(diagram);
  }

  /**
   * Implement a diagram open operation.
   */
  @Override
  protected void runTest() {
    DiagramHelper.opendiagramEditor(getExecutionContext().getSession(), diagram);
    if (_mustBeRefreshed || diagram.getOwnedRepresentationElements().isEmpty()) {
      boolean ret = DiagramHelper.refreshDiagram(diagram);
      Assert.assertTrue(NLS.bind(Messages.failToRefreshDiagram, new Object[] { diagram.getName() }), ret);
    }
  }
}
