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
import org.polarsys.capella.test.diagram.common.ju.step.tools.CreateDEdgeTool;
import org.polarsys.capella.test.diagram.common.ju.step.tools.CreateNodeElementTool;
import org.polarsys.capella.test.diagram.common.ju.step.tools.InsertRemoveTool;
import org.polarsys.capella.test.framework.context.SessionContext;

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

  public void createAssociation(String classSourceId, String classTargetId, String id) {
    new CreateDEdgeTool(this, IToolNameConstants.TOOL_CDB_CREATE_ASSOCIATION, classSourceId, classTargetId, id).run();
  }
  
  public void createDataPackage(String id) {
    new CreateContainerTool(this, IToolNameConstants.TOOL_CDB_CREATE_DATA_PACKAGE, getDiagramId(), id).run();
  }
  
  public void createEnumeration(String id) {
    new CreateContainerTool(this, IToolNameConstants.TOOL_CDB_CREATE_ENUMERATION, getDiagramId(), id).run();
  }
  
  public void createEnumerationLiteral(String id, String containerId) {
    new CreateNodeElementTool(this, IToolNameConstants.TOOL_CDB_CREATE_ENUMERATION_LITERAL, containerId, id).run();
  }
  
  public void createBooleanType(String id) {
    new CreateContainerTool(this, IToolNameConstants.TOOL_CDB_CREATE_BOOLEAN_TYPE, getDiagramId(), id).run();
  }
  
  public void createLiteralBooleanValue(String id, String containerId) {
    new CreateNodeElementTool(this, IToolNameConstants.TOOL_CDB_CREATE_BOOLEAN_LITERAL, containerId, id).run();
  }

  public void removeType(String... ids) {
    new InsertRemoveTool(this, IToolNameConstants.TOOL_CDB_INSERT_REMOVE_TYPE).remove(ids);
  }

  public void insertType(String... ids) {
    new InsertRemoveTool(this, IToolNameConstants.TOOL_CDB_INSERT_REMOVE_TYPE).insert(ids);
  }
  
  public void removeDataValues(String id, String containerId) {
    new InsertRemoveTool(this, IToolNameConstants.TOOL_CDB_INSERT_REMOVE_DATA_VALUES, containerId).remove(id);
  }

  public void insertDataValues(String id, String containerId) {
    new InsertRemoveTool(this, IToolNameConstants.TOOL_CDB_INSERT_REMOVE_DATA_VALUES, containerId).insert(id);
  }

}
