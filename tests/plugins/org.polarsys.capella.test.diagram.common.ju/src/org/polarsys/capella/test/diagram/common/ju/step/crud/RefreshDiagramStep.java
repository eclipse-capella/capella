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
import org.polarsys.capella.test.diagram.common.ju.context.SessionContext;
import org.polarsys.capella.test.diagram.common.ju.step.Messages;
import org.polarsys.capella.test.diagram.common.ju.wrapper.utils.DiagramHelper;

/**
 * Test case that refreshes a given diagram.
 */
public class RefreshDiagramStep extends AbstractTestStep {

  /** The target diagram */
  protected DDiagram _diagram;
  
  public RefreshDiagramStep(SessionContext executionContext) {
    super(executionContext);
  }

  /**
   * @see org.polarsys.capella.test.common.AbstractExtendedTest#preTestRun()
   */
  protected void preRunTest() {
    super.preRunTest();
    Assert.assertNotNull(Messages.nullDiagram, _diagram);
  }

  /**
   * Implement a diagram creation.
   */
  protected void runTest() {
    boolean ret = DiagramHelper.refreshDiagram(_diagram);

    Assert.assertTrue(NLS.bind(Messages.failToRefreshDiagram, new Object[] { _diagram.getName() }), ret);
  }

  @Override
  public Object getResult() {
    return null;
  }
}
