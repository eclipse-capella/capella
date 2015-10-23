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
package org.polarsys.capella.test.diagram.common.ju.context;

import org.eclipse.sirius.diagram.DDiagram;
import org.polarsys.capella.core.sirius.analysis.IDiagramNameConstants;
import org.polarsys.capella.core.sirius.analysis.constants.IToolNameConstants;
import org.polarsys.capella.test.diagram.common.ju.step.crud.CreateDiagramStep;
import org.polarsys.capella.test.diagram.common.ju.step.tools.CreateContainerTool;
import org.polarsys.capella.test.diagram.common.ju.step.tools.CreateDEdgeTool;
import org.polarsys.capella.test.diagram.common.ju.step.tools.CreateNodeTool;
import org.polarsys.capella.test.diagram.common.ju.step.tools.idb.InsertRemoveComponentTool;
import org.polarsys.capella.test.diagram.common.ju.step.tools.idb.InsertRemoveRelationshipsTool;

/**
 * 
 */
public class IDBDiagram extends DiagramContext {

  public IDBDiagram(SessionContext context, DDiagram diagram) {
    super(context, diagram);
  }

  public static IDBDiagram createDiagram(SessionContext executionContext, String targetIdentifier) {
    return (IDBDiagram) new CreateDiagramStep(executionContext, targetIdentifier,
        IDiagramNameConstants.INTERFACES_BLANK_DIAGRAM_NAME) {
      @Override
      public DiagramContext getResult() {
        return new IDBDiagram(getExecutionContext(), _diagram);
      }
    }.run().open();
  }

  public void createActor(String id) {
    new CreateContainerTool(this, IToolNameConstants.TOOL_IDB_CREATE_ACTOR, getDiagramId(), id).run();
  }

  public void createComponent(String idContainer, String id) {
    new CreateContainerTool(this, IToolNameConstants.TOOL_IDB_CREATE_COMPONENT, idContainer, id).run();
  }

  public void createComponent(String id) {
    createComponent(getDiagramId(), id);
  }

  public void createEvent(String idContainer, String id) {
    new CreateContainerTool(this, IToolNameConstants.TOOL_IDB_CREATE_EVENT, idContainer, id).run();
  }

  public void createEvent(String id) {
    createEvent(getDiagramId(), id);
  }

  public void insertComponents(String... id) {
    new InsertRemoveComponentTool(this).insert(id);
  }

  public void removeComponent(String... id) {
    new InsertRemoveComponentTool(this).remove(id);
  }

  public void insertRelationship(String container, String... id) {
    new InsertRemoveRelationshipsTool(this, container).insert(id);
  }

  public void removeRelationship(String container, String... id) {
    new InsertRemoveRelationshipsTool(this, container).remove(id);
  }

  public void createCommunicationLinkAcquire(String idSource, String idTarget, String id) {
    new CreateDEdgeTool(this, IToolNameConstants.TOOL_IDB_CREATE_COMMUNICATIONLINK_ACQUIRE, idSource, idTarget, id)
        .run();
  }

  public void createCommunicationLinkTransmit(String idSource, String idTarget, String id) {
    new CreateDEdgeTool(this, IToolNameConstants.TOOL_IDB_CREATE_COMMUNICATIONLINK_TRANSMIT, idSource, idTarget, id)
        .run();
  }

  public void createStandardPort(String idContainer, String id) {
    new CreateNodeTool(this, IToolNameConstants.TOOL_IDB_CREATE_STANDARD_PORT, idContainer, id).run();
  }

  public void createInterface(String id) {
    createInterface(getDiagramId(), id);
  }

  public void createInterface(String idContainer, String id) {
    new CreateContainerTool(this, IToolNameConstants.TOOL_IDB_CREATE_INTERFACE, idContainer, id).run();
  }

  public void createGeneralization(String idTarget, String idSource) {
    new CreateDEdgeTool(this, IToolNameConstants.TOOL_IDB_CREATE_GENERALIZATION, idSource, idTarget).run();
  }

  public void createGeneralization(String idSource, String idTarget, String id) {
    new CreateDEdgeTool(this, IToolNameConstants.TOOL_IDB_CREATE_GENERALIZATION, idSource, idTarget, id).run();
  }

  public void createGeneralizationNotEnabled(String idSource, String idTarget) {
    new CreateDEdgeTool(this, IToolNameConstants.TOOL_IDB_CREATE_GENERALIZATION, idSource, idTarget).cannotRun();
  }

  public void createImplements(String idSource, String idTarget, String id) {
    new CreateDEdgeTool(this, IToolNameConstants.TOOL_IDB_CREATE_IMPLEMENTS, idSource, idTarget, id).run();
  }

  public void createImplementsNotEnabled(String idSource, String idTarget) {
    new CreateDEdgeTool(this, IToolNameConstants.TOOL_IDB_CREATE_IMPLEMENTS, idSource, idTarget).cannotRun();
  }

  public void createProvides(String idSource, String idTarget, String id, String idNewSource) {
    new CreateDEdgeTool(this, IToolNameConstants.TOOL_IDB_CREATE_PROVIDES, idSource, idTarget, id, idNewSource, null)
        .run();
  }

  public void createProvidesNotEnabled(String idSource, String idTarget) {
    new CreateDEdgeTool(this, IToolNameConstants.TOOL_IDB_CREATE_PROVIDES, idSource, idTarget).cannotRun();
  }

  public void createRequiresNotEnabled(String idSource, String idTarget) {
    new CreateDEdgeTool(this, IToolNameConstants.TOOL_IDB_CREATE_REQUIRES, idSource, idTarget).cannotRun();
  }

  public void createRequires(String idSource, String idTarget, String id, String idNewSource) {
    new CreateDEdgeTool(this, IToolNameConstants.TOOL_IDB_CREATE_REQUIRES, idSource, idTarget, id, idNewSource, null)
        .run();
  }

  public void createUses(String idSource, String idTarget, String id) {
    new CreateDEdgeTool(this, IToolNameConstants.TOOL_IDB_CREATE_USES, idSource, idTarget, id).run();
  }

  public void createUsesNotEnabled(String idSource, String idTarget, String id) {
    new CreateDEdgeTool(this, IToolNameConstants.TOOL_IDB_CREATE_USES, idSource, idTarget, id).cannotRun();
  }

}
