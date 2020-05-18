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
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt.Type;
import org.polarsys.capella.core.sirius.analysis.IDiagramNameConstants;
import org.polarsys.capella.core.sirius.analysis.constants.IDNDToolNameConstants;
import org.polarsys.capella.core.sirius.analysis.constants.IToolNameConstants;
import org.polarsys.capella.test.diagram.common.ju.step.crud.CreateDiagramStep;
import org.polarsys.capella.test.diagram.common.ju.step.crud.OpenDiagramStep;
import org.polarsys.capella.test.diagram.common.ju.step.tools.CreateContainerTool;
import org.polarsys.capella.test.diagram.common.ju.step.tools.InsertRemoveTool;
import org.polarsys.capella.test.diagram.common.ju.step.tools.xab.ReuseComponentTool;
import org.polarsys.capella.test.framework.context.SessionContext;

public class EABDiagram extends XABDiagram {

  public EABDiagram(SessionContext context, DDiagram diagram) {
    super(Type.EPBS, context, diagram);
  }

  public static EABDiagram openDiagram(SessionContext executionContext, String name) {
    return (EABDiagram) new OpenDiagramStep(executionContext, name) {
      @Override
      public DiagramContext getResult() {
        return new EABDiagram(getExecutionContext(), diagram);
      }
    }.run().open();
  }

  public static EABDiagram createDiagram(SessionContext executionContext, String targetIdentifier) {

    return (EABDiagram) new CreateDiagramStep(executionContext, targetIdentifier,
        IDiagramNameConstants.EPBS_ARCHITECTURE_BLANK_DIAGRAM_NAME) {
      @Override
      public DiagramContext getResult() {
        return new EABDiagram(getExecutionContext(), diagram);
      }
    }.run().open();
  }

  public String createConfigurationItem(String id, String containerId,
      BlockArchitectureExt.ConfigurationItemType ciType) {
    String name = null;
    if (type == Type.EPBS) {
      switch (ciType) {
      case COTS:
        name = IToolNameConstants.TOOL_EAB_CREATE_COTS;
        break;
      case CS:
        name = IToolNameConstants.TOOL_EAB_CREATE_CS;
        break;
      case HW:
        name = IToolNameConstants.TOOL_EAB_CREATE_HW;
        break;
      case INTERFACE:
        name = IToolNameConstants.TOOL_EAB_CREATE_INTERFACE;
        break;
      case NDI:
        name = IToolNameConstants.TOOL_EAB_CREATE_NDI;
        break;
      case PRIME_ITEM:
        name = IToolNameConstants.TOOL_EAB_CREATE_PRIME_ITEM;
        break;
      case SYSTEM:
        name = IToolNameConstants.TOOL_EAB_CREATE_SYSTEM;
        break;
      default:
        break;
      }
    }
    DDiagramElementContainer element = new CreateContainerTool(this, name, containerId, id).run();
    return ((CapellaElement) element.getTarget()).getId();
  }

  public void reuseConfigurationItem(String containerId, String... ids) {
    new ReuseComponentTool(this, IToolNameConstants.TOOL_EAB_REUSE_CONFIGURATION_ITEM, containerId).select(ids);
  }

  public void manageRealizedPhysicalArtifacts(String containerId, String... ids) {
    new InsertRemoveTool(this, IToolNameConstants.TOOL_EAB_MANAGE_REALIZED_PHYSICAL_ARTIFACTS, containerId).insert(ids);
  }

  public void insertConfigurationItems(String containerId, String... ids) {
    new InsertRemoveTool(this, IToolNameConstants.TOOL_EAB_INSERT_REMOVE_CONFIGURATION_ITEMS, containerId).insert(ids);
  }

  public void removeConfigurationItems(String containerId, String... ids) {
    new InsertRemoveTool(this, IToolNameConstants.TOOL_EAB_INSERT_REMOVE_CONFIGURATION_ITEMS, containerId).remove(ids);
  }

  public void insertRealizedPhysicalArtifacts(String containerId, String... ids) {
    new InsertRemoveTool(this, IToolNameConstants.TOOL_EAB_INSERT_REMOVE_REALIZED_PHYSICAL_ARTIFACTS, containerId)
        .insert(ids);
  }

  public void removeRealizedPhysicalArtifacts(String containerId, String... ids) {
    new InsertRemoveTool(this, IToolNameConstants.TOOL_EAB_INSERT_REMOVE_REALIZED_PHYSICAL_ARTIFACTS, containerId)
        .remove(ids);
  }

  public void dragAndDropConfigurationItemFromExplorer(String idDraggedElement, String containerId) {
    dragAndDropFromExplorer(idDraggedElement, containerId,
        IDNDToolNameConstants.TOOL_EAB_DND_CONFIGURATIONITEM_FROM_EXPLORER);
  }

  public void dragAndDropPhysicalAtifacts(String idDraggedElement, String containerId) {
    dragAndDrop(idDraggedElement, containerId, IDNDToolNameConstants.TOOL_EAB_DND_PHYSICAL_ARTIFACTS);
  }
}
