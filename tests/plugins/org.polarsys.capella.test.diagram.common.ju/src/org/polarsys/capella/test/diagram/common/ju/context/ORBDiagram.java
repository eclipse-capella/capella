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

import static org.junit.Assert.fail;

import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.sirius.diagram.DDiagram;
import org.polarsys.capella.core.data.oa.OperationalActivity;
import org.polarsys.capella.core.data.oa.Role;
import org.polarsys.capella.core.sirius.analysis.IDiagramNameConstants;
import org.polarsys.capella.core.sirius.analysis.constants.IDNDToolNameConstants;
import org.polarsys.capella.core.sirius.analysis.constants.IToolNameConstants;
import org.polarsys.capella.test.diagram.common.ju.step.crud.CreateDiagramStep;
import org.polarsys.capella.test.diagram.common.ju.step.crud.OpenDiagramStep;
import org.polarsys.capella.test.framework.context.SessionContext;

public class ORBDiagram extends CommonDiagram {

  public ORBDiagram(SessionContext context, DDiagram diagram) {
    super(context, diagram);
  }

  public static ORBDiagram createDiagram(SessionContext executionContext, String targetIdentifier) {

    String name = IDiagramNameConstants.OPERATIONAL_ROLE_BLANK_DIAGRAM_NAME;

    return (ORBDiagram) new CreateDiagramStep(executionContext, targetIdentifier, name) {
      @Override
      public DiagramContext getResult() {
        return new ORBDiagram(getExecutionContext(), diagram);
      }
    }.run().open();
  }

  public static ORBDiagram openDiagram(SessionContext executionContext, String name) {
    return (ORBDiagram) new OpenDiagramStep(executionContext, name) {
      @Override
      public DiagramContext getResult() {
        return new ORBDiagram(getExecutionContext(), diagram);
      }
    }.run().open();
  }

  public String createOperationalRole(String containerId) {
    return createContainerElement(containerId, IToolNameConstants.TOOL_CREATE_OPERATIONAL_ROLE);
  }

  public String createOperationalActivity(String containerId) {

    String createdOpActivityId = createNodeElement(containerId, IToolNameConstants.TOOL_CREATE_OPERATIONAL_ACTIVITY);

    Role parentOpRole = getSessionContext().getSemanticElement(containerId);
    OperationalActivity semanticOperationalActivity = getSessionContext().getSemanticElement(createdOpActivityId);

    if (!parentOpRole.getAllocatedOperationalActivities().contains(semanticOperationalActivity)) {
      fail("Activity with ID: " + createdOpActivityId + " should be allocated to Role with ID: " + containerId);
    }

    return createdOpActivityId;
  }

  public String createInteraction(String sourceId, String targetId) {
    return createEdgeElement(sourceId, targetId, IToolNameConstants.TOOL_CREATE_INTERACTION, 1);
  }

  public void insertOperationalRoles(String containerId, String... elementsToInsertIds) {
    insertElements(containerId, IToolNameConstants.TOOL_INSERT_REMOVE_OPERATIONAL_ROLES, elementsToInsertIds);
  }

  public void removeOperationalRoles(String containerId, String... elementsToRemoveIds) {
    removeElements(containerId, IToolNameConstants.TOOL_INSERT_REMOVE_OPERATIONAL_ROLES, elementsToRemoveIds);
  }

  public void insertInteractions(String targetOperationalActivityId, String... elementsToInsertIds) {
    insertElements(targetOperationalActivityId, IToolNameConstants.TOOL_INSERT_REMOVE_INTERACTIONS,
        elementsToInsertIds);
  }

  public void removeInteractions(String targetOperationalActivityId, String... elementsToRemoveIds) {
    removeElements(targetOperationalActivityId, IToolNameConstants.TOOL_INSERT_REMOVE_INTERACTIONS,
        elementsToRemoveIds);
  }

  public void insertAllocatedActivities(String targetOperationalRoleId, String... elementsToInsertIds) {
    insertElements(targetOperationalRoleId, IToolNameConstants.TOOL_INSERT_REMOVE_ALLOCATED_ACTIVITIES,
        elementsToInsertIds);
  }

  public void removeAllocatedActivities(String targetOperationalRoleId, String... elementsToRemoveIds) {
    removeElements(targetOperationalRoleId, IToolNameConstants.TOOL_INSERT_REMOVE_ALLOCATED_ACTIVITIES,
        elementsToRemoveIds);
  }

  public void insertAllAllocatedActivities(String targetOperationalRoleId) {

    Role opRole = (Role) getSessionContext().getSemanticElement(targetOperationalRoleId);
    List<String> allocatedActivitiesIDs = opRole.getAllocatedOperationalActivities().stream()
        .map(OperationalActivity::getId).collect(Collectors.toList());

    // Conversion from an Object Array (.toArray() function) to a String array
    insertElements(targetOperationalRoleId, IToolNameConstants.TOOL_ORB_INSERT_ALL_ALLOCATED_ACTIVITIES,
        allocatedActivitiesIDs.toArray(new String[0]));
  }

  public void manageActivityAllocation(String targetOperationalRoleId, String[] activitiesToAllocate,
      String[] activitiesToDeallocate) {

    if (null != activitiesToAllocate && activitiesToAllocate.length != 0) {
      insertElements(targetOperationalRoleId, IToolNameConstants.TOOL_MANAGE_ACTIVITY_ALLOCATION, activitiesToAllocate);

      Role opRole = getSessionContext().getSemanticElement(targetOperationalRoleId);
      List<String> allocatedActivitiesIds = opRole.getAllocatedOperationalActivities().stream()
          .map(OperationalActivity::getId).collect(Collectors.toList());

      for (String allocatedActivityId : activitiesToAllocate) {
        if (!allocatedActivitiesIds.contains(allocatedActivityId)) {
          fail("Activity with ID: " + allocatedActivityId + " should be allocated to target Role with ID "
              + targetOperationalRoleId);
        }
      }
    }

    if (null != activitiesToDeallocate && activitiesToDeallocate.length != 0) {
      removeElements(targetOperationalRoleId, IToolNameConstants.TOOL_MANAGE_ACTIVITY_ALLOCATION,
          activitiesToDeallocate);

      Role opRole = getSessionContext().getSemanticElement(targetOperationalRoleId);
      List<String> allocatedActivitiesIds = opRole.getAllocatedOperationalActivities().stream()
          .map(OperationalActivity::getId).collect(Collectors.toList());

      for (String deallocatedActivityId : activitiesToDeallocate) {
        if (allocatedActivitiesIds.contains(deallocatedActivityId)) {
          fail("Activity with ID: " + deallocatedActivityId + " should not be allocated to target Role with ID "
              + targetOperationalRoleId);
        }
      }
    }
  }

  public void dragAndDropChangeAllocation(String idDraggedElement, String containerId) {
    dragAndDrop(idDraggedElement, containerId, IDNDToolNameConstants.TOOL_ORB_CHANGE_ALLOCATION);
  }

  public void dragAndDropOperationalActivitiesFromExplorer(String idDraggedElement, String containerId) {
    dragAndDropFromExplorer(idDraggedElement, containerId,
        IDNDToolNameConstants.TOOL_ORB_DND_OPERATIONALACTIVITIES_FROM_EXPLORER);
  }

  public void dragAndDropRoleFromExplorer(String idDraggedElement, String containerId) {
    dragAndDropFromExplorer(idDraggedElement, containerId, IDNDToolNameConstants.TOOL_ORB_DND_ROLE_FOM_EXPLORER);
  }
}
