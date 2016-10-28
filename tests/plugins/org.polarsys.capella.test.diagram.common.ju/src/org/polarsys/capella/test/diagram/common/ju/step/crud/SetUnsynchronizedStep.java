/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.test.diagram.common.ju.step.crud;

import org.junit.Assert;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;
import org.polarsys.capella.test.diagram.common.ju.context.DiagramContext;
import org.polarsys.capella.test.diagram.common.ju.step.AbstractDiagramStep;
import org.polarsys.capella.test.framework.helpers.TestHelper;

public class SetUnsynchronizedStep extends AbstractDiagramStep<DiagramContext> {

  boolean _synchronized = false;

  public SetUnsynchronizedStep(DiagramContext context) {
    this(context, false);
  }

  public SetUnsynchronizedStep(DiagramContext context, boolean _synchronized) {
    super(context);
    this._synchronized = _synchronized;
  }

  @Override
  public DiagramContext getResult() {
    return getExecutionContext();
  }

  @Override
  protected void runTest() {

    TestHelper.getExecutionManager(getExecutionContext().getSession()).execute(new AbstractReadWriteCommand() {
      public void run() {
        getExecutionContext().getDiagram().setSynchronized(_synchronized);
        if (_synchronized) {
          Assert.assertTrue(getExecutionContext().getDiagram().isSynchronized());
        } else {
          Assert.assertTrue(!getExecutionContext().getDiagram().isSynchronized());
        }
      }
    });

  }
}
