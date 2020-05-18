/*******************************************************************************
 * Copyright (c) 2019 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.diagram.common.ju.context;

import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElementContainer;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt.Type;
import org.polarsys.capella.core.sirius.analysis.IDiagramNameConstants;
import org.polarsys.capella.core.sirius.analysis.constants.IDNDToolNameConstants;
import org.polarsys.capella.core.sirius.analysis.constants.IToolNameConstants;
import org.polarsys.capella.test.diagram.common.ju.step.crud.CreateDiagramStep;
import org.polarsys.capella.test.diagram.common.ju.step.crud.OpenDiagramStep;
import org.polarsys.capella.test.diagram.common.ju.step.tools.CreateContainerTool;
import org.polarsys.capella.test.diagram.common.ju.step.tools.InsertRemoveTool;
import org.polarsys.capella.test.framework.context.SessionContext;

public class OABDiagram extends XABDiagram {

  public OABDiagram(SessionContext context, DDiagram diagram) {
    super(Type.OA, context, diagram);
  }

  public static OABDiagram openDiagram(SessionContext executionContext, String name) {
    return (OABDiagram) new OpenDiagramStep(executionContext, name) {
      @Override
      public DiagramContext getResult() {
        return new OABDiagram(getExecutionContext(), diagram);
      }
    }.run().open();
  }

  public static OABDiagram createDiagram(SessionContext executionContext, String targetIdentifier) {

    return (OABDiagram) new CreateDiagramStep(executionContext, targetIdentifier,
        IDiagramNameConstants.OPERATIONAL_ENTITY_BLANK_DIAGRAM_NAME) {
      @Override
      public DiagramContext getResult() {
        return new OABDiagram(getExecutionContext(), diagram);
      }
    }.run().open();
  }

  public String createRole(String id, String containerId) {
    DDiagramElementContainer element = new CreateContainerTool(this, IToolNameConstants.TOOL_OAB_CREATE_ROLE,
        containerId, id).run();

    return ((CapellaElement) element.getTarget()).getId();
  }

  public void insertRole(String id, String containerId) {
    new InsertRemoveTool(this, IToolNameConstants.TOOL_OAB_INSERT_REMOVE_ALLOCATED_ROLES, containerId).insert(id);
  }

  public void removeRole(String id, String containerId) {
    new InsertRemoveTool(this, IToolNameConstants.TOOL_OAB_INSERT_REMOVE_ALLOCATED_ROLES, containerId).remove(id);
  }

  public void manageAllocatedRolesInsert(String id, String containerId) {
    new InsertRemoveTool(this, IToolNameConstants.TOOL_OAB_MANAGE_ROLE_ALLOCATION, containerId).insert(id);
  }

  public void manageAllocatedRolesRemove(String id, String containerId) {
    new InsertRemoveTool(this, IToolNameConstants.TOOL_OAB_MANAGE_ROLE_ALLOCATION, containerId).remove(id);
  }

  public void dragAndDropRolesFromExplorer(String idDraggedElement, String containerId) {
    dragAndDropFromExplorer(idDraggedElement, containerId, IDNDToolNameConstants.TOOL_OAB_DND_ROLES_FROM_EXPLORER);
  }

  public void dragAndDropOperationalActivitiesFromExplorer(String idDraggedElement, String containerId) {
    dragAndDropFromExplorer(idDraggedElement, containerId,
        IDNDToolNameConstants.TOOL_OAB_DND_OPERATIONALACTIVITIES_FROM_EXPLORER);
  }

  public void dragAndDropOperationalActivitiesFromExplorerToRole(String idDraggedElement, String containerId) {
    dragAndDropFromExplorer(idDraggedElement, containerId,
        IDNDToolNameConstants.TOOL_OAB_DND_OPERATIONALACTIVITIES_FROM_EXPLORER_TO_ROLE);
  }
}
