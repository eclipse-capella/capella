/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.diagram.common.ju.step.tools;

import org.eclipse.sirius.diagram.DNode;
import org.polarsys.capella.test.diagram.common.ju.context.DiagramContext;

public class CreateNodeTool extends CreateAbstractDNodeTool<DNode> {

  /*
   * Constructors deprecated due to the use of the "newIdentifier" variable 
   * This should not be used as it introduces problems with the Insert/Remove actions
   */
  public CreateNodeTool(DiagramContext context, String toolName, String containerView) {
    super(context, toolName, containerView, DNode.class);
  }

  @Deprecated
  public CreateNodeTool(DiagramContext context, String toolName, String containerView, String newIdentifier) {
    super(context, toolName, containerView, newIdentifier, DNode.class);
  }

  @Deprecated
  public CreateNodeTool(DiagramContext context, String toolName, String targetContainerView, String containerView,
      String newIdentifier) {
    super(context, toolName, targetContainerView, containerView, newIdentifier, DNode.class);
  }
}
