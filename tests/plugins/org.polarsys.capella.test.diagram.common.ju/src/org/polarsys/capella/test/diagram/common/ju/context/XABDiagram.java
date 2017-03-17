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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.diagram.DDiagram;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt.Type;
import org.polarsys.capella.core.sirius.analysis.IDiagramNameConstants;
import org.polarsys.capella.core.sirius.analysis.actions.extensions.AbstractExternalJavaAction;
import org.polarsys.capella.core.sirius.analysis.constants.IToolNameConstants;
import org.polarsys.capella.test.diagram.common.ju.step.crud.CreateDiagramStep;
import org.polarsys.capella.test.diagram.common.ju.step.crud.OpenDiagramStep;
import org.polarsys.capella.test.diagram.common.ju.step.tools.AbstractToolStep;
import org.polarsys.capella.test.diagram.common.ju.step.tools.CreateContainerTool;
import org.polarsys.capella.test.diagram.common.ju.step.tools.CreateDEdgeTool;
import org.polarsys.capella.test.diagram.common.ju.step.tools.InsertRemoveTool;
import org.polarsys.capella.test.framework.context.SessionContext;

public class XABDiagram extends CommonDiagram {

  BlockArchitectureExt.Type type = null;

  public XABDiagram(BlockArchitectureExt.Type type, SessionContext context, DDiagram diagram) {
    super(context, diagram);
    this.type = type;
  }

  public static XABDiagram createDiagram(SessionContext executionContext, String targetIdentifier) {
    BlockArchitecture architecture = BlockArchitectureExt.getRootBlockArchitecture(executionContext.getSemanticElement(targetIdentifier));
    final BlockArchitectureExt.Type type = BlockArchitectureExt.getBlockArchitectureType(architecture);

    String name = null;
    if (type == Type.OA) {
      name = IDiagramNameConstants.OPERATIONAL_ENTITY_BLANK_DIAGRAM_NAME;
    } else if (type == Type.SA) {
      name = IDiagramNameConstants.SYSTEM_ARCHITECTURE_BLANK_DIAGRAM_NAME;
    } else if (type == Type.LA) {
      name = IDiagramNameConstants.LOGICAL_ARCHITECTURE_BLANK_DIAGRAM_NAME;
    } else if (type == Type.PA) {
      name = IDiagramNameConstants.PHYSICAL_ARCHITECTURE_BLANK_DIAGRAM_NAME;
    }

    if (type == Type.PA) {
      return PABDiagram.createDiagram(executionContext, targetIdentifier);
    }

    return (XABDiagram) new CreateDiagramStep(executionContext, targetIdentifier, name) {
      @Override
      public DiagramContext getResult() {
        return new XABDiagram(type, getExecutionContext(), diagram);
      }
    }.run().open();
  }

  public static XABDiagram openDiagram(SessionContext executionContext, String name,
      final BlockArchitectureExt.Type type) {
    return (XABDiagram) new OpenDiagramStep(executionContext, name) {
      @Override
      public DiagramContext getResult() {
        return new XABDiagram(type, getExecutionContext(), diagram);
      }
    }.run().open();
  }

  public void createActor(String id) {
    String name = null;
    if (type == Type.OA) {
      name = IToolNameConstants.TOOL_OAB_CREATE_OA;
    } else if (type == Type.SA) {
      name = IToolNameConstants.TOOL_SAB_CREATE_ACTOR;
    } else if (type == Type.LA) {
      name = IToolNameConstants.TOOL_LAB_CREATE_LOGICAL_ACTOR;
    } else if (type == Type.PA) {
      name = IToolNameConstants.TOOL_PAB_CREATE_PHYSICAL_ACTOR;
    }
    new CreateContainerTool(this, name, getDiagramId(), id).run();
  }

  public void removeActor(String id) {
    String name = null;
    if (type == Type.OA) {
      name = IToolNameConstants.TOOL_OAB_INSERT_REMOVE_OPERATIONAL_ENTITIES;
    } else if (type == Type.SA) {
      name = IToolNameConstants.TOOL_SAB_INSERT_ACTOR;
    } else if (type == Type.LA) {
      name = IToolNameConstants.TOOL_LAB_INSERT_ACTOR;
    } else if (type == Type.PA) {
      name = IToolNameConstants.TOOL_PAB_INSERT_PHYSICAL_ACTOR;
    }
    new InsertRemoveTool(this, name).remove(id);
  }

  public void removeComponent(String id) {
    removeComponent(id, getDiagramId());
  }

  public void removeComponent(String id, String containerId) {
    String name = null;
    if (type == Type.OA) {
      name = IToolNameConstants.TOOL_OAB_INSERT_REMOVE_OPERATIONAL_ENTITIES;
      new InsertRemoveTool(this, name).remove(id);
    } else if (type == Type.LA) {
      new InsertRemoveTool(this, new String[] { IToolNameConstants.TOOL_XAB_INSERT_REMOVE_COMPONENTS_MONOPART,
          IToolNameConstants.TOOL_LAB_INSERT_REMOVE_COMPONENTS }, containerId).remove(id);
    }

  }

  public void createComponent(String id, String containerId) {
    String name = null;
    if (type == Type.OA) {
      name = IToolNameConstants.TOOL_OAB_CREATE_OE;

    } else if (type == Type.LA) {
      name = IToolNameConstants.TOOL_LAB_CREATE_COMPONENT;
    }
    new CreateContainerTool(this, name, containerId, id).run();
  }

  public void createComponentExchange(String idSource, String idTarget, String id) {
    String name = null;
    if (type == Type.OA) {
      name = IToolNameConstants.TOOL_OAB_CREATE_COMMUNICATION_MEAN;
    } else if (type == Type.SA) {
      name = IToolNameConstants.TOOL_SAB_CREATE_COMPONENT_EXCHANGE;
    } else if (type == Type.LA) {
      name = IToolNameConstants.TOOL_LAB_CREATE_COMPONENT_EXCHANGE;
    } else if (type == Type.PA) {
      name = IToolNameConstants.TOOL_PAB_CREATE_COMPONENT_EXCHANGE;
    }
    new CreateDEdgeTool(this, name, idSource, idTarget, id).run();
  }

  public void createComponentExchangeDelegation(String idSource, String idTarget, String id) {
    String name = null;
    if (type == Type.LA) {
      name = IToolNameConstants.TOOL_LAB_CREATE_DELEGATION;
    } else if (type == Type.PA) {
      name = IToolNameConstants.TOOL_PAB_CREATE_DELEGATION;
    }
    new CreateDEdgeTool(this, name, idSource, idTarget, id).run();
  }

  public void createFunctionalExchange(String idSource, String idTarget, String id) {
    String name = null;
    if (type == Type.OA) {
      name = IToolNameConstants.TOOL_OAB_CREATE_INTERACTION;
    } else if (type == Type.SA) {
      name = IToolNameConstants.TOOL_SAB_CREATE_FUNCTIONAL_EXCHANGE;
    } else if (type == Type.LA) {
      name = IToolNameConstants.TOOL_LAB_CREATE_FUNCTIONAL_EXCHANGE;
    } else if (type == Type.PA) {
      name = IToolNameConstants.TOOL_PAB_CREATE_FUNCTIONAL_EXCHANGE;
    }
    new CreateDEdgeTool(this, name, idSource, idTarget, id).run();
  }
  
  public void createPhysicalLink(String idSource, String idTarget, String id) {
    String name = null;
    if (type == Type.SA) {
      name = IToolNameConstants.TOOL_SAB_CREATE_PHYSICAL_LINK;
    } else if (type == Type.LA) {
      name = IToolNameConstants.TOOL_LAB_CREATE_PHYSICAL_LINK;
    } else if (type == Type.PA) {
      name = IToolNameConstants.TOOL_PAB_CREATE_PHYSICAL_LINK;
    }
    new CreateDEdgeTool(this, name, idSource, idTarget, id).run();
  }

  public void insertComponentExchange(String id, String containerId) {
    String name = null;
    if (type == Type.OA) {
      name = IToolNameConstants.TOOL_OAB_SHOW_HIDE_COMMUNICATION_MEAN;
    } else if (type == Type.SA) {
      name = IToolNameConstants.TOOL_SAB_SHOW_HIDE_COMPONENT_EXCHANGE;
    } else if (type == Type.LA) {
      name = IToolNameConstants.TOOL_LAB_SHOW_HIDE_COMPONENT_EXCHANGE;
    } else if (type == Type.PA) {
      name = IToolNameConstants.TOOL_PAB_SHOW_HIDE_COMPONENT_EXCHANGE;
    }
    new InsertRemoveTool(this, name, containerId).insert(id);
  }

  public void insertComponent(String toInsertId, String containerId) {
    String name = null;
    if (type == Type.OA) {
      name = IToolNameConstants.TOOL_OAB_INSERT_REMOVE_OPERATIONAL_ENTITIES;
    } else if (type == Type.LA) {
      name = IToolNameConstants.TOOL_XAB_INSERT_REMOVE_COMPONENTS_MONOPART;
    } else if (type == Type.PA) {
      // Nothing here.. need to specify which kind of component
    }

    new InsertRemoveTool(this, name, containerId).insert(toInsertId);
  }

  public void insertPhysicalLink(String id, String containerId) {
    String name = null;
    if (type == Type.SA) {
      name = IToolNameConstants.TOOL_SAB_SHOW_HIDE_PHYSICAL_LINK;
    } else if (type == Type.LA) {
      name = IToolNameConstants.TOOL_LAB_SHOW_HIDE_PHYSICAL_LINK;
    } else if (type == Type.PA) {
      name = IToolNameConstants.TOOL_PAB_SHOW_HIDE_PHYSICAL_LINK;
    }
    new InsertRemoveTool(this, name, containerId).insert(id);
  }

  public void manageAllocatedFunction(String id, String containerId) {
    String name = null;
    if (type == Type.OA) {
      name = IToolNameConstants.TOOL_OAB_MANAGE_ACTIVITY_ALLOCATION;
    } else if (type == Type.SA) {
      name = IToolNameConstants.TOOL_SAB_MANAGE_FUNCTION_ALLOCATION;
    } else if (type == Type.LA) {
      name = IToolNameConstants.TOOL_LAB_MANAGE_FUNCTION_ALLOCATION;
    } else if (type == Type.PA) {
      name = IToolNameConstants.TOOL_PAB_MANAGE_FUNCTION_ALLOCATION;
    }
    new InsertRemoveTool(this, name, containerId).insert(id);
  }

  public void insertAllocatedFunction(String id, String containerId) {
    String name = null;
    if (type == Type.OA) {
      name = IToolNameConstants.TOOL_OAB_INSERT_REMOVE_ALLOCATED_ACTIVITIES;
    } else if (type == Type.SA) {
      name = IToolNameConstants.TOOL_SAB_INSERT_REMOVE_ALLOCATED_FUNCTIONS;
    } else if (type == Type.LA) {
      name = IToolNameConstants.TOOL_LAB_INSERT_REMOVE_ALLOCATED_FUNCTIONS;
    } else if (type == Type.PA) {
      name = IToolNameConstants.TOOL_PAB_INSERT_REMOVE_ALLOCATED_FUNCTIONS;
    }
    new InsertRemoveTool(this, name, containerId).insert(id);
  }

  public void removeAllocatedFunction(String id, String containerId) {
    String name = null;
    if (type == Type.OA) {
      name = IToolNameConstants.TOOL_OAB_INSERT_REMOVE_ALLOCATED_ACTIVITIES;
    } else if (type == Type.SA) {
      name = IToolNameConstants.TOOL_SAB_INSERT_REMOVE_ALLOCATED_FUNCTIONS;
    } else if (type == Type.LA) {
      name = IToolNameConstants.TOOL_LAB_INSERT_REMOVE_ALLOCATED_FUNCTIONS;
    } else if (type == Type.PA) {
      name = IToolNameConstants.TOOL_PAB_INSERT_REMOVE_ALLOCATED_FUNCTIONS;
    }
    new InsertRemoveTool(this, name, containerId).remove(id);
  }

  @Override
  public Collection<EObject> adaptTool(AbstractToolStep tool, Map<String, Object> parameters,
      Collection<EObject> semanticElements) {
    Collection<EObject> scope = AbstractExternalJavaAction.getScope(parameters);
    if (scope.isEmpty()) {
      return semanticElements;
    }

    // If tool show component in wizard but display parts in diagrams, or the opposite, we switch between them
    EObject scopeElement = scope.iterator().next();
    Collection<EObject> result = new ArrayList<EObject>();
    for (EObject element : semanticElements) {
      if ((element instanceof Part) && (scopeElement instanceof Component)) {
        result.add(((Part) element).getAbstractType());
      } else if ((element instanceof Component) && (scopeElement instanceof Part)) {
        result.add(((Component) element).getRepresentingPartitions().get(0));
      } else {
        result.add(element);
      }
    }
    return result;
  }

  public void insertFunctionalExchange(String id, String containerId, boolean autoRefresh) {
    String name = null;
    if (type == Type.SA) {
      name = IToolNameConstants.TOOL_SAB_SHOW_HIDE_FUNCTIONAL_EXCHANGES;
    } else if (type == Type.LA) {
      name = IToolNameConstants.TOOL_LAB_SHOW_HIDE_FUNCTIONAL_EXCHANGES;
    } else if (type == Type.PA) {
      name = IToolNameConstants.TOOL_PAB_SHOW_HIDE_FUNCTIONAL_EXCHANGES;
    }
    new InsertRemoveTool(this, name, containerId, autoRefresh).insert(id);
  }
}
