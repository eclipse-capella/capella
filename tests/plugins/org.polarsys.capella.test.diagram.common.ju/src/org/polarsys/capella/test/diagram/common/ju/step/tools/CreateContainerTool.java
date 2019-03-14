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
package org.polarsys.capella.test.diagram.common.ju.step.tools;

import org.eclipse.sirius.diagram.DDiagramElementContainer;
import org.polarsys.capella.test.diagram.common.ju.context.DiagramContext;

public class CreateContainerTool extends CreateAbstractDNodeTool<DDiagramElementContainer> {

  public CreateContainerTool(DiagramContext context, String toolName, String containerView) {
    super(context, toolName, containerView, DDiagramElementContainer.class);
  }

  /*
   * Constructors deprecated due to the use of the "newIdentifier" variable 
   * This should not be used as it introduces problems with the Insert/Remove actions
   */
  @Deprecated
  public CreateContainerTool(DiagramContext context, String toolName, String containerView, String newIdentifier) {
    super(context, toolName, containerView, newIdentifier, DDiagramElementContainer.class);
  }

  @Deprecated
  public CreateContainerTool(DiagramContext context, String[] toolIdentifier, String containerView) {
    super(context, toolIdentifier, containerView, DDiagramElementContainer.class);
  }

  @Deprecated
  public CreateContainerTool(DiagramContext context, String[] toolIdentifier, String containerView,
      String newIdentifier) {
    super(context, toolIdentifier, containerView, newIdentifier, DDiagramElementContainer.class);
  }
}
