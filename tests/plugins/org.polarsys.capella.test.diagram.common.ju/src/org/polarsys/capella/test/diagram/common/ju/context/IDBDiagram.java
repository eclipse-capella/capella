/*******************************************************************************
 * Copyright (c) 2006, 2017 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.test.diagram.common.ju.context;

import java.util.Arrays;

import org.eclipse.sirius.diagram.DDiagram;
import org.polarsys.capella.core.sirius.analysis.IDiagramNameConstants;
import org.polarsys.capella.core.sirius.analysis.constants.IToolNameConstants;
import org.polarsys.capella.test.diagram.common.ju.step.crud.CreateDiagramStep;
import org.polarsys.capella.test.diagram.common.ju.step.tools.CreateContainerTool;
import org.polarsys.capella.test.diagram.common.ju.step.tools.CreateDEdgeTool;
import org.polarsys.capella.test.diagram.common.ju.step.tools.CreateNodeTool;
import org.polarsys.capella.test.diagram.common.ju.step.tools.InsertRemoveTool;
import org.polarsys.capella.test.framework.context.SessionContext;

/**
 * 
 */
public class IDBDiagram extends DiagramContext {

  String type = null;
  
  public IDBDiagram(SessionContext context, DDiagram diagram) {
    super(context, diagram);
  }

  public static IDBDiagram createDiagram(SessionContext executionContext, String diagramKind, String targetIdentifier) {
    if (!Arrays.asList(IDiagramNameConstants.INTERFACES_BLANK_DIAGRAM_NAME,
        IDiagramNameConstants.CONTEXTUAL_COMPONENT_DETAILED_INTERFACES_DIAGRAM_NAME,
        IDiagramNameConstants.CONTEXTUAL_COMPONENT_EXTERNAL_INTERFACES_DIAGRAM_NAME,
        IDiagramNameConstants.CONTEXTUAL_COMPONENT_INTERNAL_INTERFACES_DIAGRAM_NAME).contains(diagramKind)) {
      throw new RuntimeException("this diagram is not compatible with IDBDiagram API");
    }
    
    return (IDBDiagram) new CreateDiagramStep(executionContext, targetIdentifier, diagramKind) {
      @Override
      public DiagramContext getResult() {
        return new IDBDiagram(getExecutionContext(), diagram);
      }
    }.run().open();
  }

  public static IDBDiagram createDiagram(SessionContext executionContext, String targetIdentifier) {
    return createDiagram(executionContext, IDiagramNameConstants.INTERFACES_BLANK_DIAGRAM_NAME, targetIdentifier);
  }

  public void createActor(String id) {
    String toolName = null;
    if (IDiagramNameConstants.INTERFACES_BLANK_DIAGRAM_NAME.equals(getType())) {
      toolName = IToolNameConstants.TOOL_IDB_CREATE_ACTOR;
    } else if (IDiagramNameConstants.CONTEXTUAL_COMPONENT_DETAILED_INTERFACES_DIAGRAM_NAME.equals(getType())) {
      throw new UnsupportedOperationException();
    } else if (IDiagramNameConstants.CONTEXTUAL_COMPONENT_EXTERNAL_INTERFACES_DIAGRAM_NAME.equals(getType())) {
      toolName = IToolNameConstants.TOOL_CEI_CREATE_ACTOR;
    } else if (IDiagramNameConstants.CONTEXTUAL_COMPONENT_INTERNAL_INTERFACES_DIAGRAM_NAME.equals(getType())) {
      throw new UnsupportedOperationException();
    }
    new CreateContainerTool(this, toolName, getDiagramId(), id).run();
  }

  public void createComponent(String idContainer, String id) {
    String toolName = null;
    if (IDiagramNameConstants.INTERFACES_BLANK_DIAGRAM_NAME.equals(getType())) {
      toolName = IToolNameConstants.TOOL_IDB_CREATE_COMPONENT;
    } else if (IDiagramNameConstants.CONTEXTUAL_COMPONENT_DETAILED_INTERFACES_DIAGRAM_NAME.equals(getType())) {
      throw new UnsupportedOperationException();
    } else if (IDiagramNameConstants.CONTEXTUAL_COMPONENT_EXTERNAL_INTERFACES_DIAGRAM_NAME.equals(getType())) {
      toolName = IToolNameConstants.TOOL_CEI_CREATE_COMPONENT;
    } else if (IDiagramNameConstants.CONTEXTUAL_COMPONENT_INTERNAL_INTERFACES_DIAGRAM_NAME.equals(getType())) {
      toolName = IToolNameConstants.TOOL_LCCII_CREATE_LOGICAL_COMPONENT;
    }
    new CreateContainerTool(this, toolName, idContainer, id).run();
  }

  public void createComponent(String id) {
    createComponent(getDiagramId(), id);
  }

  public void createEvent(String idContainer, String id) {
    String toolName = null;
    if (IDiagramNameConstants.INTERFACES_BLANK_DIAGRAM_NAME.equals(getType())) {
      toolName = IToolNameConstants.TOOL_IDB_CREATE_EVENT;
    } else if (IDiagramNameConstants.CONTEXTUAL_COMPONENT_DETAILED_INTERFACES_DIAGRAM_NAME.equals(getType())) {
      toolName = IToolNameConstants.TOOL_CDI_CREATE_EVENT;
    } else if (IDiagramNameConstants.CONTEXTUAL_COMPONENT_EXTERNAL_INTERFACES_DIAGRAM_NAME.equals(getType())) {
      toolName = IToolNameConstants.TOOL_CEI_CREATE_EVENT;
    } else if (IDiagramNameConstants.CONTEXTUAL_COMPONENT_INTERNAL_INTERFACES_DIAGRAM_NAME.equals(getType())) {
      toolName = IToolNameConstants.TOOL_LCCII_CREATE_ECHANGE_ITEM_EVENT;
    }
    new CreateContainerTool(this, toolName, idContainer, id).run();
  }

  public void createEvent(String id) {
    createEvent(getDiagramId(), id);
  }


  public String getToolInsertComponents() {
    String toolName = null;
    if (IDiagramNameConstants.INTERFACES_BLANK_DIAGRAM_NAME.equals(getType())) {
      toolName = IToolNameConstants.TOOL_IDB_INSERT_REMOVE_COMPONENTS;
    } else if (IDiagramNameConstants.CONTEXTUAL_COMPONENT_DETAILED_INTERFACES_DIAGRAM_NAME.equals(getType())) {
      throw new UnsupportedOperationException();
    } else if (IDiagramNameConstants.CONTEXTUAL_COMPONENT_EXTERNAL_INTERFACES_DIAGRAM_NAME.equals(getType())) {
      toolName = IToolNameConstants.TOOL_CEI_INSERT_REMOVE_COMPONENTS;
    } else if (IDiagramNameConstants.CONTEXTUAL_COMPONENT_INTERNAL_INTERFACES_DIAGRAM_NAME.equals(getType())) {
      toolName = IToolNameConstants.TOOL_LCCII_INSERT_REMOVE_COMPONENTS;
    }
    return toolName;
  }
  
  public String getToolInsertRelationship() {
    String toolName = null;
    if (IDiagramNameConstants.INTERFACES_BLANK_DIAGRAM_NAME.equals(getType())) {
      toolName = IToolNameConstants.TOOL_IDB_INSERT_REMOVE_RELATIONSHIPS;
    } else {
      throw new UnsupportedOperationException();
    }
    return toolName;
  }
  
  public void insertComponents(String... id) {
    new InsertRemoveTool(this, getToolInsertComponents()).insert(id);
  }

  public void removeComponent(String... id) {
    new InsertRemoveTool(this, getToolInsertComponents()).remove(id);
  }

  public void insertRelationship(String container, String... id) {
    new InsertRemoveTool(this, getToolInsertRelationship(), container).insert(id);
  }

  public void removeRelationship(String container, String... id) {
    new InsertRemoveTool(this, getToolInsertRelationship(), container).remove(id);
  }

  public void createCommunicationLinkAcquire(String idSource, String idTarget, String id) {
    String toolName = null;
    if (IDiagramNameConstants.INTERFACES_BLANK_DIAGRAM_NAME.equals(getType())) {
      toolName = IToolNameConstants.TOOL_IDB_CREATE_COMMUNICATIONLINK_ACQUIRE;
    } else if (IDiagramNameConstants.CONTEXTUAL_COMPONENT_DETAILED_INTERFACES_DIAGRAM_NAME.equals(getType())) {
      toolName = IToolNameConstants.TOOL_CDI_CREATE_ACQUIRE;
    } else if (IDiagramNameConstants.CONTEXTUAL_COMPONENT_EXTERNAL_INTERFACES_DIAGRAM_NAME.equals(getType())) {
      toolName = IToolNameConstants.TOOL_CEI_CREATE_ACQUIRE;
    } else if (IDiagramNameConstants.CONTEXTUAL_COMPONENT_INTERNAL_INTERFACES_DIAGRAM_NAME.equals(getType())) {
      toolName = IToolNameConstants.TOOL_LCCII_CREATE_ACQUIRE;
    }
    new CreateDEdgeTool(this, toolName, idSource, idTarget, id)
        .run();
  }

  public void createCommunicationLinkTransmit(String idSource, String idTarget, String id) {
    String toolName = null;
    if (IDiagramNameConstants.INTERFACES_BLANK_DIAGRAM_NAME.equals(getType())) {
      toolName = IToolNameConstants.TOOL_IDB_CREATE_TRANSMIT;
    } else if (IDiagramNameConstants.CONTEXTUAL_COMPONENT_DETAILED_INTERFACES_DIAGRAM_NAME.equals(getType())) {
      toolName = IToolNameConstants.TOOL_CDI_CREATE_TRANSMIT;
    } else if (IDiagramNameConstants.CONTEXTUAL_COMPONENT_EXTERNAL_INTERFACES_DIAGRAM_NAME.equals(getType())) {
      toolName = IToolNameConstants.TOOL_CEI_CREATE_TRANSMIT;
    } else if (IDiagramNameConstants.CONTEXTUAL_COMPONENT_INTERNAL_INTERFACES_DIAGRAM_NAME.equals(getType())) {
      toolName = IToolNameConstants.TOOL_LCCII_CREATE_TRANSMIT;
    }
    new CreateDEdgeTool(this, toolName, idSource, idTarget, id)
        .run();
  }

  public void createStandardPort(String idContainer, String id) {
    String toolName = null;
    if (IDiagramNameConstants.INTERFACES_BLANK_DIAGRAM_NAME.equals(getType())) {
      toolName = IToolNameConstants.TOOL_IDB_CREATE_STANDARD_PORT;
    } else if (IDiagramNameConstants.CONTEXTUAL_COMPONENT_DETAILED_INTERFACES_DIAGRAM_NAME.equals(getType())) {
      toolName = IToolNameConstants.TOOL_CDI_CREATE_STANDARD_PORT;
    } else if (IDiagramNameConstants.CONTEXTUAL_COMPONENT_EXTERNAL_INTERFACES_DIAGRAM_NAME.equals(getType())) {
      toolName = IToolNameConstants.TOOL_CEI_CREATE_STANDARD_PORT;
    } else if (IDiagramNameConstants.CONTEXTUAL_COMPONENT_INTERNAL_INTERFACES_DIAGRAM_NAME.equals(getType())) {
      toolName = IToolNameConstants.TOOL_LCCII_CREATE_STANDARD_PORT;
    }
    new CreateNodeTool(this, toolName, idContainer, id).run();
  }

  public void createInterface(String id) {
    createInterface(getDiagramId(), id);
  }

  public void createInterface(String idContainer, String id) {
    String toolName = null;
    if (IDiagramNameConstants.INTERFACES_BLANK_DIAGRAM_NAME.equals(getType())) {
      toolName = IToolNameConstants.TOOL_IDB_CREATE_INTERFACE;
    } else if (IDiagramNameConstants.CONTEXTUAL_COMPONENT_DETAILED_INTERFACES_DIAGRAM_NAME.equals(getType())) {
      toolName = IToolNameConstants.TOOL_CDI_CREATE_INTERFACE;
    } else if (IDiagramNameConstants.CONTEXTUAL_COMPONENT_EXTERNAL_INTERFACES_DIAGRAM_NAME.equals(getType())) {
      toolName = IToolNameConstants.TOOL_CEI_CREATE_INTERFACE;
    } else if (IDiagramNameConstants.CONTEXTUAL_COMPONENT_INTERNAL_INTERFACES_DIAGRAM_NAME.equals(getType())) {
      toolName = IToolNameConstants.TOOL_LCCII_CREATE_INTERFACE;
    }
    new CreateContainerTool(this, toolName, idContainer, id).run();
  }

  public void createGeneralization(String idTarget, String idSource) {
    String toolName = null;
    if (IDiagramNameConstants.INTERFACES_BLANK_DIAGRAM_NAME.equals(getType())) {
      toolName = IToolNameConstants.TOOL_IDB_CREATE_GENERALIZATION;
    } else if (IDiagramNameConstants.CONTEXTUAL_COMPONENT_DETAILED_INTERFACES_DIAGRAM_NAME.equals(getType())) {
      toolName = IToolNameConstants.TOOL_CDI_CREATE_GENERALIZATION;
    } else if (IDiagramNameConstants.CONTEXTUAL_COMPONENT_EXTERNAL_INTERFACES_DIAGRAM_NAME.equals(getType())) {
      toolName = IToolNameConstants.TOOL_CEI_CREATE_GENERALIZATION;
    } else if (IDiagramNameConstants.CONTEXTUAL_COMPONENT_INTERNAL_INTERFACES_DIAGRAM_NAME.equals(getType())) {
      toolName = IToolNameConstants.TOOL_LCCII_CREATE_GENERALIZATION;
    }
    new CreateDEdgeTool(this, toolName, idSource, idTarget).run();
  }

  public void createGeneralization(String idSource, String idTarget, String id) {
    String toolName = null;
    if (IDiagramNameConstants.INTERFACES_BLANK_DIAGRAM_NAME.equals(getType())) {
      toolName = IToolNameConstants.TOOL_IDB_CREATE_GENERALIZATION;
    } else if (IDiagramNameConstants.CONTEXTUAL_COMPONENT_DETAILED_INTERFACES_DIAGRAM_NAME.equals(getType())) {
      toolName = IToolNameConstants.TOOL_CDI_CREATE_GENERALIZATION;
    } else if (IDiagramNameConstants.CONTEXTUAL_COMPONENT_EXTERNAL_INTERFACES_DIAGRAM_NAME.equals(getType())) {
      toolName = IToolNameConstants.TOOL_CEI_CREATE_GENERALIZATION;
    } else if (IDiagramNameConstants.CONTEXTUAL_COMPONENT_INTERNAL_INTERFACES_DIAGRAM_NAME.equals(getType())) {
      toolName = IToolNameConstants.TOOL_LCCII_CREATE_GENERALIZATION;
    }
    new CreateDEdgeTool(this, toolName, idSource, idTarget, id).run();
  }

  public void createGeneralizationNotEnabled(String idSource, String idTarget) {
    String toolName = null;
    if (IDiagramNameConstants.INTERFACES_BLANK_DIAGRAM_NAME.equals(getType())) {
      toolName = IToolNameConstants.TOOL_IDB_CREATE_GENERALIZATION;
    } else if (IDiagramNameConstants.CONTEXTUAL_COMPONENT_DETAILED_INTERFACES_DIAGRAM_NAME.equals(getType())) {
      toolName = IToolNameConstants.TOOL_CDI_CREATE_GENERALIZATION;
    } else if (IDiagramNameConstants.CONTEXTUAL_COMPONENT_EXTERNAL_INTERFACES_DIAGRAM_NAME.equals(getType())) {
      toolName = IToolNameConstants.TOOL_CEI_CREATE_GENERALIZATION;
    } else if (IDiagramNameConstants.CONTEXTUAL_COMPONENT_INTERNAL_INTERFACES_DIAGRAM_NAME.equals(getType())) {
      toolName = IToolNameConstants.TOOL_LCCII_CREATE_GENERALIZATION;
    }
    new CreateDEdgeTool(this, toolName, idSource, idTarget).cannotRun();
  }

  public void createImplements(String idSource, String idTarget, String id) {
    String toolName = null;
    if (IDiagramNameConstants.INTERFACES_BLANK_DIAGRAM_NAME.equals(getType())) {
      toolName = IToolNameConstants.TOOL_IDB_CREATE_IMPLEMENTS;
    } else if (IDiagramNameConstants.CONTEXTUAL_COMPONENT_DETAILED_INTERFACES_DIAGRAM_NAME.equals(getType())) {
      toolName = IToolNameConstants.TOOL_CDI_CREATE_IMPLEMENTS;
    } else if (IDiagramNameConstants.CONTEXTUAL_COMPONENT_EXTERNAL_INTERFACES_DIAGRAM_NAME.equals(getType())) {
      toolName = IToolNameConstants.TOOL_CEI_CREATE_IMPLEMENTS;
    } else if (IDiagramNameConstants.CONTEXTUAL_COMPONENT_INTERNAL_INTERFACES_DIAGRAM_NAME.equals(getType())) {
      toolName = IToolNameConstants.TOOL_LCCII_CREATE_IMPLEMENTS;
    }
    new CreateDEdgeTool(this, toolName, idSource, idTarget, id).run();
  }

  public void createImplementsNotEnabled(String idSource, String idTarget) {
    String toolName = null;
    if (IDiagramNameConstants.INTERFACES_BLANK_DIAGRAM_NAME.equals(getType())) {
      toolName = IToolNameConstants.TOOL_IDB_CREATE_IMPLEMENTS;
    } else if (IDiagramNameConstants.CONTEXTUAL_COMPONENT_DETAILED_INTERFACES_DIAGRAM_NAME.equals(getType())) {
      toolName = IToolNameConstants.TOOL_CDI_CREATE_IMPLEMENTS;
    } else if (IDiagramNameConstants.CONTEXTUAL_COMPONENT_EXTERNAL_INTERFACES_DIAGRAM_NAME.equals(getType())) {
      toolName = IToolNameConstants.TOOL_CEI_CREATE_IMPLEMENTS;
    } else if (IDiagramNameConstants.CONTEXTUAL_COMPONENT_INTERNAL_INTERFACES_DIAGRAM_NAME.equals(getType())) {
      toolName = IToolNameConstants.TOOL_LCCII_CREATE_IMPLEMENTS;
    }
    new CreateDEdgeTool(this, toolName, idSource, idTarget).cannotRun();
  }

  public void createProvides(String idSource, String idTarget, String id, String idNewSource) {
    String toolName = null;
    if (IDiagramNameConstants.INTERFACES_BLANK_DIAGRAM_NAME.equals(getType())) {
      toolName = IToolNameConstants.TOOL_IDB_CREATE_PROVIDES;
    } else if (IDiagramNameConstants.CONTEXTUAL_COMPONENT_DETAILED_INTERFACES_DIAGRAM_NAME.equals(getType())) {
      toolName = IToolNameConstants.TOOL_CDI_CREATE_PROVIDES;
    } else if (IDiagramNameConstants.CONTEXTUAL_COMPONENT_EXTERNAL_INTERFACES_DIAGRAM_NAME.equals(getType())) {
      toolName = IToolNameConstants.TOOL_CEI_CREATE_PROVIDES;
    } else if (IDiagramNameConstants.CONTEXTUAL_COMPONENT_INTERNAL_INTERFACES_DIAGRAM_NAME.equals(getType())) {
      toolName = IToolNameConstants.TOOL_LCCII_CREATE_PROVIDES;
    }
    new CreateDEdgeTool(this, toolName, idSource, idTarget, id, idNewSource, null)
        .run();
  }

  public void createProvidesNotEnabled(String idSource, String idTarget) {
    String toolName = null;
    if (IDiagramNameConstants.INTERFACES_BLANK_DIAGRAM_NAME.equals(getType())) {
      toolName = IToolNameConstants.TOOL_IDB_CREATE_PROVIDES;
    } else if (IDiagramNameConstants.CONTEXTUAL_COMPONENT_DETAILED_INTERFACES_DIAGRAM_NAME.equals(getType())) {
      toolName = IToolNameConstants.TOOL_CDI_CREATE_PROVIDES;
    } else if (IDiagramNameConstants.CONTEXTUAL_COMPONENT_EXTERNAL_INTERFACES_DIAGRAM_NAME.equals(getType())) {
      toolName = IToolNameConstants.TOOL_CEI_CREATE_PROVIDES;
    } else if (IDiagramNameConstants.CONTEXTUAL_COMPONENT_INTERNAL_INTERFACES_DIAGRAM_NAME.equals(getType())) {
      toolName = IToolNameConstants.TOOL_LCCII_CREATE_PROVIDES;
    }
    new CreateDEdgeTool(this, toolName, idSource, idTarget).cannotRun();
  }

  public void createRequiresNotEnabled(String idSource, String idTarget) {
    String toolName = null;
    if (IDiagramNameConstants.INTERFACES_BLANK_DIAGRAM_NAME.equals(getType())) {
      toolName = IToolNameConstants.TOOL_IDB_CREATE_REQUIRES;
    } else if (IDiagramNameConstants.CONTEXTUAL_COMPONENT_DETAILED_INTERFACES_DIAGRAM_NAME.equals(getType())) {
      toolName = IToolNameConstants.TOOL_CDI_CREATE_REQUIRES;
    } else if (IDiagramNameConstants.CONTEXTUAL_COMPONENT_EXTERNAL_INTERFACES_DIAGRAM_NAME.equals(getType())) {
      toolName = IToolNameConstants.TOOL_CEI_CREATE_REQUIRES;
    } else if (IDiagramNameConstants.CONTEXTUAL_COMPONENT_INTERNAL_INTERFACES_DIAGRAM_NAME.equals(getType())) {
      toolName = IToolNameConstants.TOOL_LCCII_CREATE_REQUIRES;
    }
    new CreateDEdgeTool(this, toolName, idSource, idTarget).cannotRun();
  }

  public void createRequires(String idSource, String idTarget, String id, String idNewSource) {
    String toolName = null;
    if (IDiagramNameConstants.INTERFACES_BLANK_DIAGRAM_NAME.equals(getType())) {
      toolName = IToolNameConstants.TOOL_IDB_CREATE_REQUIRES;
    } else if (IDiagramNameConstants.CONTEXTUAL_COMPONENT_DETAILED_INTERFACES_DIAGRAM_NAME.equals(getType())) {
      toolName = IToolNameConstants.TOOL_CDI_CREATE_REQUIRES;
    } else if (IDiagramNameConstants.CONTEXTUAL_COMPONENT_EXTERNAL_INTERFACES_DIAGRAM_NAME.equals(getType())) {
      toolName = IToolNameConstants.TOOL_CEI_CREATE_REQUIRES;
    } else if (IDiagramNameConstants.CONTEXTUAL_COMPONENT_INTERNAL_INTERFACES_DIAGRAM_NAME.equals(getType())) {
      toolName = IToolNameConstants.TOOL_LCCII_CREATE_REQUIRES;
    }
    new CreateDEdgeTool(this, toolName, idSource, idTarget, id, idNewSource, null)
        .run();
  }

  public void createUses(String idSource, String idTarget, String id) {
    String toolName = null;
    if (IDiagramNameConstants.INTERFACES_BLANK_DIAGRAM_NAME.equals(getType())) {
      toolName = IToolNameConstants.TOOL_IDB_CREATE_USES;
    } else if (IDiagramNameConstants.CONTEXTUAL_COMPONENT_DETAILED_INTERFACES_DIAGRAM_NAME.equals(getType())) {
      toolName = IToolNameConstants.TOOL_CDI_CREATE_USES;
    } else if (IDiagramNameConstants.CONTEXTUAL_COMPONENT_EXTERNAL_INTERFACES_DIAGRAM_NAME.equals(getType())) {
      toolName = IToolNameConstants.TOOL_CEI_CREATE_USES;
    } else if (IDiagramNameConstants.CONTEXTUAL_COMPONENT_INTERNAL_INTERFACES_DIAGRAM_NAME.equals(getType())) {
      toolName = IToolNameConstants.TOOL_LCCII_CREATE_USES;
    }
    new CreateDEdgeTool(this, toolName, idSource, idTarget, id).run();
  }

  public void createUsesNotEnabled(String idSource, String idTarget, String id) {
    String toolName = null;
    if (IDiagramNameConstants.INTERFACES_BLANK_DIAGRAM_NAME.equals(getType())) {
      toolName = IToolNameConstants.TOOL_IDB_CREATE_USES;
    } else if (IDiagramNameConstants.CONTEXTUAL_COMPONENT_DETAILED_INTERFACES_DIAGRAM_NAME.equals(getType())) {
      toolName = IToolNameConstants.TOOL_CDI_CREATE_USES;
    } else if (IDiagramNameConstants.CONTEXTUAL_COMPONENT_EXTERNAL_INTERFACES_DIAGRAM_NAME.equals(getType())) {
      toolName = IToolNameConstants.TOOL_CEI_CREATE_USES;
    } else if (IDiagramNameConstants.CONTEXTUAL_COMPONENT_INTERNAL_INTERFACES_DIAGRAM_NAME.equals(getType())) {
      toolName = IToolNameConstants.TOOL_LCCII_CREATE_USES;
    }
    new CreateDEdgeTool(this, toolName, idSource, idTarget, id).cannotRun();
  }

}
