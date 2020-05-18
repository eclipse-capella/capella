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
package org.polarsys.capella.test.diagram.common.ju.step.crud;

import org.eclipse.sirius.viewpoint.DRepresentation;
import org.junit.Assert;
import org.polarsys.capella.test.diagram.common.ju.context.DiagramContext;
import org.polarsys.capella.test.diagram.common.ju.step.Messages;
import org.polarsys.capella.test.diagram.common.ju.wrapper.utils.DiagramHelper;
import org.polarsys.capella.test.framework.api.step.AbstractTestStep;
import org.polarsys.capella.test.framework.context.SessionContext;

public class OpenDTableStep extends AbstractTestStep {

  /** Is refresh operation included during the test? */
  private boolean _mustBeRefreshed;

  protected DiagramContext diagramContext;
  protected DRepresentation representation;

  public OpenDTableStep(DiagramContext diagramContext_p) {
    this(diagramContext_p, false);
  }

  public OpenDTableStep(DiagramContext diagramContext, boolean mustBeRefreshed) {
    super(diagramContext.getSessionContext());

    this._mustBeRefreshed = mustBeRefreshed;
    representation = diagramContext.getDiagram();
  }

  public OpenDTableStep(SessionContext context_p, String diagramName, boolean mustBeRefreshed) {
    super(context_p);

    this.representation = DiagramHelper.getDRepresentation(context_p.getSession(), diagramName);
    _mustBeRefreshed = mustBeRefreshed;
  }

  public OpenDTableStep(SessionContext context_p, String diagramName) {
    this(context_p, diagramName, false);
  }

  /**
   * @see org.polarsys.capella.test.common.AbstractExtendedTest#preTestRun()
   */
  @Override
  protected void preRunTest() {
    super.preRunTest();
    Assert.assertNotNull(Messages.nullDiagram, representation);
  }

  /**
   * Implement a diagram open operation.
   */
  @Override
  protected void runTest() {
    DiagramHelper.opendiagramEditor(getExecutionContext().getSession(), representation);
  }

  @Override
  public Object getResult() {
    return representation;
  }

}
