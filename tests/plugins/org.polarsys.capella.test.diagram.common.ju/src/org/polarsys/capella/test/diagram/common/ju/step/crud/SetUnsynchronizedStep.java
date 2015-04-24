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

import org.polarsys.capella.test.diagram.common.ju.context.DiagramContext;
import org.polarsys.capella.test.diagram.common.ju.step.AbstractDiagramStep;

public class SetUnsynchronizedStep extends AbstractDiagramStep<DiagramContext> {

  boolean _synchronized = false;

  public SetUnsynchronizedStep(DiagramContext context) {
    this(context, false);
  }

  public SetUnsynchronizedStep(DiagramContext context, boolean synchronized_p) {
    super(context);
  }

  @Override
  public DiagramContext getResult() {
    return getExecutionContext();
  }

  @Override
  protected void runTest() {
    getExecutionContext().getDiagram().setSynchronized(_synchronized);
    if (_synchronized) {
      Assert.assertTrue(getExecutionContext().getDiagram().isSynchronized());
    } else {
      Assert.assertTrue(!getExecutionContext().getDiagram().isSynchronized());
    }
  }
}
