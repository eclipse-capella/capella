/*******************************************************************************
 * Copyright (c) 2018 THALES GLOBAL SERVICES.
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
import org.polarsys.capella.core.sirius.analysis.IDiagramNameConstants;
import org.polarsys.capella.core.sirius.analysis.constants.IToolNameConstants;
import org.polarsys.capella.test.diagram.common.ju.context.SequenceDiagram.InsertRemoveInstanceRoleTool;
import org.polarsys.capella.test.diagram.common.ju.context.SequenceDiagram.InsertRemovePartTool;
import org.polarsys.capella.test.diagram.common.ju.context.SequenceDiagram.InsertRemoveStateFragmentAbstractFunctionTool;
import org.polarsys.capella.test.diagram.common.ju.step.crud.CreateDiagramStep;
import org.polarsys.capella.test.diagram.common.ju.step.crud.OpenDiagramStep;
import org.polarsys.capella.test.diagram.common.ju.step.tools.InsertRemoveTool;
import org.polarsys.capella.test.diagram.common.ju.step.tools.sequence.TimerCreationTool;
import org.polarsys.capella.test.framework.context.SessionContext;

public class FSDiagram extends SequenceDiagram {

  public FSDiagram(BlockArchitectureExt.Type type, SessionContext context, DDiagram diagram) {
    super(type, context, diagram);
  }
  
  public static FSDiagram createDiagram(SessionContext executionContext, String targetIdentifier) {
    BlockArchitecture architecture = BlockArchitectureExt
        .getRootBlockArchitecture(executionContext.getSemanticElement(targetIdentifier));
    final BlockArchitectureExt.Type type = BlockArchitectureExt.getBlockArchitectureType(architecture);
    
    String name = IDiagramNameConstants.FUNCTIONAL_SCENARIO;
    if(type == BlockArchitectureExt.Type.OA) {
      name = IDiagramNameConstants.OPERATIONAL_ACTIVITY_INTERACTION_SCENARIO_DIAGRAM_NAME;
    }
    return (FSDiagram) new CreateDiagramStep(executionContext, targetIdentifier, name) {
      @Override
      public DiagramContext getResult() {
        return new FSDiagram(type, getExecutionContext(), diagram);
      }
    }.run().open();
  }

  public static FSDiagram openDiagram(SessionContext executionContext, String name, final BlockArchitectureExt.Type type) {
    return (FSDiagram) new OpenDiagramStep(executionContext, name) {
      @Override
      public DiagramContext getResult() {
        return new FSDiagram(type, getExecutionContext(), diagram);
      }
    }.run().open();
  }
  
  public String createFunction() {
    return createNodeElement(getDiagramId(), IToolNameConstants.TOOL_CREATE_FUNCTION);
  }
  
  public String createFunction(String containerId) {
    return createNodeElement(containerId, IToolNameConstants.TOOL_CREATE_FUNCTION);
  }
  
  public void insertFunction(String id) {
    //new InsertRemoveTool(this, IToolNameConstants.TOOL_INSERT_REMOVE_FUNCTIONS).insert(id);
    new InsertRemoveInstanceRoleTool(this, IToolNameConstants.TOOL_INSERT_REMOVE_FUNCTIONS).insert(id);
  }
  
  public void removeFunction(String id) {
    //new InsertRemoveTool(this, IToolNameConstants.TOOL_INSERT_REMOVE_FUNCTIONS).remove(id);
    new InsertRemoveInstanceRoleTool(this, IToolNameConstants.TOOL_INSERT_REMOVE_FUNCTIONS).remove(id);
  }
  
  public String createFunction(BlockArchitectureExt.FunctionType functionType) {
    String name = "";
    switch (functionType) {
    case DUPLICATE:
      name = IToolNameConstants.TOOL_CREATE_FUNCTION_DUPLICATE;
      break;
    case GATHER:
      name = IToolNameConstants.TOOL_CREATE_FUNCTION_GATHER;
      break;
    case ROUTE:
      name = IToolNameConstants.TOOL_CREATE_FUNCTION_ROUTE;
      break;
    case SELECT:
      name = IToolNameConstants.TOOL_CREATE_FUNCTION_SELECT;
      break;
    case SPLIT:
      name = IToolNameConstants.TOOL_CREATE_FUNCTION_SPLIT;
      break;
    default:
      name = IToolNameConstants.TOOL_CREATE_FUNCTION;
      break;
    }
    return createNodeElement(getDiagramId(), name);
  }
  
  // Unsupported opperations
  @Override
  public void addMultipleLifeLinesForExistingComponent(String id) {
    throw new UnsupportedOperationException();
  }
  
  @Override
  public void createArmTimer(String source, String target) {
    throw new UnsupportedOperationException();
  }

  @Override
  public void cancelArmTimer(String source, String target) {
    throw new UnsupportedOperationException();
  }
  
  @Override
  public String createActor() {
    throw new UnsupportedOperationException();
  }

  @Override
  public void insertActor(String id) {
    throw new UnsupportedOperationException();
  }

  @Override
  public void removeActor(String id) {
    throw new UnsupportedOperationException();
  }
  
  @Override
  public String insertAllocatedFunction(String containerId, String id) {
    throw new UnsupportedOperationException();
  }
}
