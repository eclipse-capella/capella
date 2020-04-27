/*******************************************************************************
 * Copyright (c) 2016, 2019 THALES GLOBAL SERVICES.
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

import java.util.ArrayList;

import org.eclipse.osgi.util.NLS;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.junit.Assert;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;
import org.polarsys.capella.test.diagram.common.ju.context.DiagramContext;
import org.polarsys.capella.test.diagram.common.ju.step.AbstractDiagramStep;
import org.polarsys.capella.test.diagram.common.ju.step.Messages;
import org.polarsys.capella.test.diagram.common.ju.wrapper.utils.DiagramHelper;
import org.polarsys.capella.test.framework.helpers.TestHelper;

/**
 * Test case that refreshes a given diagram.
 */
public class ClearDiagramStep extends AbstractDiagramStep<DiagramContext> {

  public ClearDiagramStep(DiagramContext executionContext) {
    super(executionContext);
  }

  /**
   * @see org.polarsys.capella.test.common.AbstractExtendedTest#preTestRun()
   */
  @Override
  protected void preRunTest() {
    super.preRunTest();
    Assert.assertNotNull(Messages.nullDiagram, getDiagramContext().getDiagram());
  }

  /**
   * Implement a diagram creation.
   */
  @Override
  protected void runTest() {
    TestHelper.getExecutionManager(getExecutionContext().getSession()).execute(new AbstractReadWriteCommand() {
      public void run() {
        for (DDiagramElement element : new ArrayList<DDiagramElement>(
            getDiagramContext().getDiagram().getOwnedDiagramElements())) {
          DiagramHelper.removeView(element);
        }
        boolean ret = getDiagramContext().getDiagram().getOwnedDiagramElements().isEmpty();
        Assert.assertTrue(NLS.bind(Messages.emptyDiagram, new Object[] { getDiagramContext().getDiagramDescriptor().getName() }),
            ret);
      }
    });

  }

  @Override
  public DiagramContext getResult() {
    return getDiagramContext();
  }
}
