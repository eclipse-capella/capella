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
package org.polarsys.capella.test.diagram.common.ju.step.tools;

import static org.junit.Assert.assertFalse;

import org.eclipse.sirius.diagram.DNode;
import org.polarsys.capella.test.diagram.common.ju.context.DiagramContext;

public class CreateNodeTool extends CreateAbstractDNodeTool {

  public CreateNodeTool(DiagramContext context, String toolName, String containerView) {
    super(context, toolName, containerView);
  }

  public CreateNodeTool(DiagramContext context, String toolName, String newIdentifier, String containerView) {
    super(context, toolName, newIdentifier, containerView);
  }

  @Override
  protected void postRunTest() {
    super.postRunTest();

    if (!(_newElements.iterator().next() instanceof DNode)) {
      assertFalse(true);
    }

  }

}
