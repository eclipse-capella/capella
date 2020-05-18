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
    return getDiagramContext();
  }

  @Override
  protected void runTest() {

    TestHelper.getExecutionManager(getExecutionContext().getSession()).execute(new AbstractReadWriteCommand() {
      public void run() {
        getDiagramContext().getDiagram().setSynchronized(_synchronized);
        if (_synchronized) {
          Assert.assertTrue(getDiagramContext().getDiagram().isSynchronized());
        } else {
          Assert.assertTrue(!getDiagramContext().getDiagram().isSynchronized());
        }
      }
    });

  }
}
