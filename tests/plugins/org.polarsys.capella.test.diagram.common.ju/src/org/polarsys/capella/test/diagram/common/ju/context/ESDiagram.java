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
import org.polarsys.capella.core.data.interaction.InstanceRole;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt.Type;
import org.polarsys.capella.core.sirius.analysis.IDiagramNameConstants;
import org.polarsys.capella.core.sirius.analysis.constants.IToolNameConstants;
import org.polarsys.capella.test.diagram.common.ju.step.crud.CreateDiagramStep;
import org.polarsys.capella.test.diagram.common.ju.step.crud.OpenDiagramStep;
import org.polarsys.capella.test.diagram.common.ju.step.tools.AbstractToolStep;
import org.polarsys.capella.test.diagram.common.ju.step.tools.CreateNodeTool;
import org.polarsys.capella.test.diagram.common.ju.step.tools.InsertRemoveTool;

public class ESDiagram extends DiagramContext {

  BlockArchitectureExt.Type type = null;

  public ESDiagram(BlockArchitectureExt.Type type, SessionContext context, DDiagram diagram) {
    super(context, diagram);
    this.type = type;
  }

  public static ESDiagram createDiagram(SessionContext executionContext, String targetIdentifier) {
    BlockArchitecture architecture = BlockArchitectureExt.getRootBlockArchitecture(executionContext
        .getSemanticElement(targetIdentifier));
    final BlockArchitectureExt.Type type = BlockArchitectureExt.getBlockArchitectureType(architecture);

    String name = null;
    if (type == Type.OA) {
      name = IDiagramNameConstants.OPERATIONAL_INTERACTION_SCENARIO_DIAGRAM_NAME;
    } else {
      name = IDiagramNameConstants.DATA_FLOW_SCENARIO_DIAGRAM_NAME;
    }

    if (type == Type.SA)
      return (SA_ESDiagram) new CreateDiagramStep(executionContext, targetIdentifier, name) {
        @Override
        public DiagramContext getResult() {
          return new SA_ESDiagram(type, getExecutionContext(), diagram);
        }
      }.run().open();
    else if (type == Type.PA)
      return (PA_ESDiagram) new CreateDiagramStep(executionContext, targetIdentifier, name) {
        @Override
        public DiagramContext getResult() {
          return new PA_ESDiagram(type, getExecutionContext(), diagram);
        }
      }.run().open();

    return (ESDiagram) new CreateDiagramStep(executionContext, targetIdentifier, name) {
      @Override
      public DiagramContext getResult() {
        return new ESDiagram(type, getExecutionContext(), diagram);
      }
    }.run().open();
  }

  public static ESDiagram openDiagram(SessionContext executionContext, String name, final BlockArchitectureExt.Type type) {
    return (ESDiagram) new OpenDiagramStep(executionContext, name) {
      @Override
      public DiagramContext getResult() {
        return new ESDiagram(type, getExecutionContext(), diagram);
      }
    }.run().open();
  }

  public void createActor(String id) {
    String name = null;
    if (type == Type.OA) {
      name = IToolNameConstants.TOOL_OES_CREATE_OA;
    } else {
      name = IToolNameConstants.TOOL_ES_CREATE_ACTOR;
    }
    new CreateNodeTool(this, name, getDiagramId(), id).run();
  }

  public void insertActor(String id) {
    String name = null;
    if (type == Type.OA) {
      name = IToolNameConstants.TOOL_OES_INSERT_REMOVE_OPERATIONAL_ENTITIES_ROLES;
    } else {
      name = IToolNameConstants.TOOL_ES_INSERT_ACTOR;
    }
    new InsertRemoveTool(this, name).insert(id);
  }
  
  public void removeActor(String id) {
    String name = null;
    if (type == Type.OA) {
      name = IToolNameConstants.TOOL_OES_INSERT_REMOVE_OPERATIONAL_ENTITIES_ROLES;
    } else {
      name = IToolNameConstants.TOOL_ES_INSERT_ACTOR;
    }
    new InsertRemoveTool(this, name).remove(id);
  }

  public void createComponent(String id) {
    String name = null;
    if (type == Type.OA) {
      name = IToolNameConstants.TOOL_OES_CREATE_OE;
    } else {
      name = IToolNameConstants.TOOL_ES_CREATE_COMPONENT;
    }
    new CreateNodeTool(this, name, getDiagramId(), id).run();
  }

  public void insertComponent(String id) {
    String name = null;
    if (type == Type.OA) {
      name = IToolNameConstants.TOOL_OES_INSERT_REMOVE_OPERATIONAL_ENTITIES_ROLES;
    } else {
      name = IToolNameConstants.TOOL_ES_INSERT_REMOVE_COMPONENTS;
    }
    new InsertRemoveTool(this, name).insert(id);
  }
  
  public void removeComponent(String id) {
    String name = null;
    if (type == Type.OA) {
      name = IToolNameConstants.TOOL_OES_INSERT_REMOVE_OPERATIONAL_ENTITIES_ROLES;
    } else {
      name = IToolNameConstants.TOOL_ES_INSERT_REMOVE_COMPONENTS;
    }
    new InsertRemoveTool(this, name).remove(id);
  }
  
  @Override
  //Override this method to switch between InstanceRole (appear in the diagram) and Part (appear in the Transfer wizard)
  public Collection<EObject> adaptTool(AbstractToolStep tool, Map<String, Object> parameters,
      Collection<EObject> semanticElements) {
    Collection<EObject> result = new ArrayList<EObject>();
    for (EObject element : semanticElements) {
      if ((element instanceof InstanceRole)) {
        result.add(((InstanceRole) element).getRepresentedInstance());
      } else {
        result.add(element);
      }
    }
    return result;
  }
}
