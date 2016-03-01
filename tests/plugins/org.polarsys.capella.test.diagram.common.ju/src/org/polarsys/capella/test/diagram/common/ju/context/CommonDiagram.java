/*******************************************************************************
 * Copyright (c) 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.test.diagram.common.ju.context;

import org.eclipse.sirius.diagram.DDiagram;
import org.polarsys.capella.core.sirius.analysis.constants.IToolNameConstants;
import org.polarsys.capella.test.diagram.common.ju.step.tools.CreateAbstractDNodeTool;
import org.polarsys.capella.test.diagram.common.ju.step.tools.CreateDEdgeTool;
import org.polarsys.capella.test.diagram.common.ju.step.tools.InsertRemoveTool;

public class CommonDiagram extends DiagramContext {

  public CommonDiagram(SessionContext context, DDiagram diagram) {
    super(context, diagram);
  }

  public void createConstraint(String id, String containerId) {
    // All diagrams shared the same tool
    String name = IToolNameConstants.TOOL_CC_CREATE_CONSTRAINT;
    new CreateAbstractDNodeTool(this, name, containerId, id).run();
  }

  public void createConstrainedElement(String sourceId, String targetId) {
    // All diagrams shared the same tool
    String name = IToolNameConstants.TOOL_CC_CREATE_CONSTRAINTELEMENT;
    new CreateDEdgeTool(this, name, sourceId, targetId).run();
  }

  public void removeConstraint(String id, String containerId) {
    new InsertRemoveTool(this, IToolNameConstants.TOOL_CC_INSERT_REMOVE_CONSTRAINTS, containerId).remove(id);
  }

  public void insertConstraint(String id, String containerId) {
    new InsertRemoveTool(this, IToolNameConstants.TOOL_CC_INSERT_REMOVE_CONSTRAINTS, containerId).insert(id);
  }
}
