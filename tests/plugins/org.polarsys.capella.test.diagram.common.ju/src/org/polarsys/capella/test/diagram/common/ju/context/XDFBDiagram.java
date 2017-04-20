/*******************************************************************************
 * Copyright (c) 2016, 2017 THALES GLOBAL SERVICES.
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
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt.Type;
import org.polarsys.capella.core.sirius.analysis.IDiagramNameConstants;
import org.polarsys.capella.core.sirius.analysis.constants.IToolNameConstants;
import org.polarsys.capella.test.diagram.common.ju.step.crud.CreateDiagramStep;
import org.polarsys.capella.test.diagram.common.ju.step.crud.OpenDiagramStep;
import org.polarsys.capella.test.diagram.common.ju.step.tools.CreateContainerTool;
import org.polarsys.capella.test.diagram.common.ju.step.tools.CreateDEdgeTool;
import org.polarsys.capella.test.diagram.common.ju.step.tools.InitializationFromExistingDiagramTool;
import org.polarsys.capella.test.diagram.common.ju.step.tools.InsertRemoveTool;
import org.polarsys.capella.test.framework.context.SessionContext;

public class XDFBDiagram extends DiagramContext {

  BlockArchitectureExt.Type type = null;

  public XDFBDiagram(BlockArchitectureExt.Type type, SessionContext context, DDiagram diagram) {
    super(context, diagram);
    this.type = type;
  }

  public static XDFBDiagram createDiagram(SessionContext executionContext, String targetIdentifier) {
    BlockArchitecture architecture = BlockArchitectureExt.getRootBlockArchitecture(executionContext
        .getSemanticElement(targetIdentifier));
    final BlockArchitectureExt.Type type = BlockArchitectureExt.getBlockArchitectureType(architecture);

    String name = null;
    if (type == Type.OA) {
      name = IDiagramNameConstants.OPERATIONAL_ACTIVITY_INTERACTION_BLANK_DIAGRAM_NAME;
    } else if (type == Type.SA) {
      name = IDiagramNameConstants.SYSTEM_DATA_FLOW_BLANK_DIAGRAM_NAME;
    } else if (type == Type.LA) {
      name = IDiagramNameConstants.LOGICAL_DATA_FLOW_BLANK_DIAGRAM_NAME;
    } else if (type == Type.PA) {
      name = IDiagramNameConstants.PHYSICAL_DATA_FLOW_BLANK_DIAGRAM_NAME;
    }

    return (XDFBDiagram) new CreateDiagramStep(executionContext, targetIdentifier, name) {
      @Override
      public DiagramContext getResult() {
        return new XDFBDiagram(type, getExecutionContext(), diagram);
      }
    }.run().open();
  }

  public static XDFBDiagram openDiagram(SessionContext executionContext, String name,
      final BlockArchitectureExt.Type type) {
    return (XDFBDiagram) new OpenDiagramStep(executionContext, name) {
      @Override
      public DiagramContext getResult() {
        return new XDFBDiagram(type, getExecutionContext(), diagram);
      }
    }.run().open();
  }

  public void createFunction(String id) {
    createFunction(id, getDiagramId());
  }

  public void createFunction(String id, String containerId) {
    String name = null;
    if (type == Type.OA) {
      name = IToolNameConstants.TOOL_OAIB_CREATE_OPERATIONAL_ACTIVITY;
    } else if (type == Type.SA) {
      name = IToolNameConstants.TOOL_SDFB_CREATE_SYSTEM_FUNCTION;
    } else if (type == Type.LA) {
      name = IToolNameConstants.TOOL_LDFB_CREATE_LOGICAL_FUNCTION;
    } else if (type == Type.PA) {
      name = IToolNameConstants.TOOL_PDFB_CREATE_PHYSICAL_FUNCTION;
    }
    new CreateContainerTool(this, name, containerId, id).run();
  }
  
  public void createActorFunction(String id) {
    createActorFunction(id, getDiagramId());
  }

  public void createActorFunction(String id, String containerId) {
    String name = null;
    if (type == Type.SA) {
      name = IToolNameConstants.TOOL_SDFB_CREATE_ACTOR_FUNCTION;
    } else if (type == Type.LA) {
      name = IToolNameConstants.TOOL_LDFB_CREATE_ACTOR_FUNCTION;
    } else if (type == Type.PA) {
      name = IToolNameConstants.TOOL_PDFB_CREATE_ACTOR_FUNCTION;
    }
    new CreateContainerTool(this, name, containerId, id).run();
  }
  
  public void createFunctionalExchange(String id, String sourceViewId, String targetViewId, String newSourceIdentifier, String newTargetIdentifier) {
    String name = null;
    if (type == Type.OA) {
      name = IToolNameConstants.TOOL_OAIB_CREATE_INTERACTION;
    } else if (type == Type.SA) {
      name = IToolNameConstants.TOOL_SDFB_CREATE_FUNCTIONAL_EXCHANGE;
    } else if (type == Type.LA) {
      name = IToolNameConstants.TOOL_LDFB_CREATE_FUNCTIONAL_EXCHANGE;
    } else if (type == Type.PA) {
      name = IToolNameConstants.TOOL_PDFB_CREATE_FUNCTIONAL_EXCHANGE;
    }
    new CreateDEdgeTool(this, name, sourceViewId, targetViewId, id, newSourceIdentifier, newTargetIdentifier).run();
  }
  
  public void createFunctionalExchange(String id, String sourceViewId, String targetViewId) {
    createFunctionalExchange(id, sourceViewId, targetViewId, null, null);
  }

  public void initializationFromExistingDiagram(DiagramContext existingContext) {
    new InitializationFromExistingDiagramTool(this, IToolNameConstants.TOOL_SDFB_INITIALIZATION_FROM_EXISTING_DIAGRAM, existingContext).insert(existingContext.getDiagramId());
  }
  
  public void insertFunction(String containerId, String id){
	  if (type == Type.SA) {
	  new InsertRemoveTool(this, IToolNameConstants.TOOL_SDFB_SHOW_HIDE_FUNCTIONS, containerId).insert(id);
    } else if (type == Type.LA) {
    	new InsertRemoveTool(this, IToolNameConstants.TOOL_LDFB_SHOW_HIDE_FUNCTIONS, containerId).insert(id);
    } else if (type == Type.PA) {
    	new InsertRemoveTool(this, IToolNameConstants.TOOL_PDFB_SHOW_HIDE_FUNCTIONS, containerId).insert(id);
    }
  }
  
  public void removeFunction(String containerId, String id){
	  if (type == Type.SA) {
		  new InsertRemoveTool(this, IToolNameConstants.TOOL_SDFB_SHOW_HIDE_FUNCTIONS, containerId).remove(id);
	    } else if (type == Type.LA) {
	    	new InsertRemoveTool(this, IToolNameConstants.TOOL_LDFB_SHOW_HIDE_FUNCTIONS, containerId).remove(id);
	    } else if (type == Type.PA) {
	    	new InsertRemoveTool(this, IToolNameConstants.TOOL_PDFB_SHOW_HIDE_FUNCTIONS, containerId).remove(id);
	    }
  }
}
