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
import org.polarsys.capella.core.diagram.helpers.naming.DiagramDescriptionConstants;
import org.polarsys.capella.core.sirius.analysis.constants.IToolNameConstants;
import org.polarsys.capella.test.diagram.common.ju.step.crud.CreateDiagramStep;
import org.polarsys.capella.test.diagram.common.ju.step.tools.CreateContainerTool;
import org.polarsys.capella.test.diagram.common.ju.step.tools.InsertRemoveTool;

public class CDBDiagram extends DiagramContext {

  public CDBDiagram(SessionContext context, DDiagram diagram) {
    super(context, diagram);
  }

  public static CDBDiagram createDiagram(SessionContext executionContext, String targetIdentifier) {
    return (CDBDiagram) new CreateDiagramStep(executionContext, targetIdentifier,
        DiagramDescriptionConstants.CLASS_BLANK_DIAGRAM_NAME) {
      @Override
      public DiagramContext getResult() {
        return new CDBDiagram(getExecutionContext(), diagram);
      }
    }.run().open();
  }

  public void createClass(String id) {
    new CreateContainerTool(this, IToolNameConstants.TOOL_CDB_CREATE_CLASS, getDiagramId(), id).run();
  }

  public void removeType(String... ids) {
    new InsertRemoveTool(this, IToolNameConstants.TOOL_CDB_INSERT_REMOVE_TYPE).remove(ids);
  }

  public void insertType(String... ids) {
    new InsertRemoveTool(this, IToolNameConstants.TOOL_CDB_INSERT_REMOVE_TYPE).insert(ids);
  }

}
