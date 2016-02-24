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
import org.polarsys.capella.test.diagram.common.ju.step.crud.CreateDiagramStep;
import org.polarsys.capella.test.diagram.common.ju.step.crud.OpenDiagramStep;
import org.polarsys.capella.test.diagram.common.ju.step.tools.cc.InsertRemoveActorsTool;
import org.polarsys.capella.test.diagram.common.ju.step.tools.cc.InsertRemoveCapabilitiesTool;
import org.polarsys.capella.test.diagram.common.ju.step.tools.cc.InsertRemoveMissionsTool;

public class CCDiagram extends DiagramContext {

  public CCDiagram(SessionContext context, DDiagram diagram) {
    super(context, diagram);
  }

  public static CCDiagram openDiagram(final SessionContext context, String diagramName)
  {
    return (CCDiagram) new OpenDiagramStep(context, diagramName){
      @Override
      public DiagramContext getResult() {
        return new CCDiagram(context, diagram);
      }
    }.run();
  }
  
  public static CCDiagram createDiagram(final SessionContext executionContext, String targetIdentifier, String diagramDescription)
  {
    return (CCDiagram) new CreateDiagramStep(executionContext, targetIdentifier, diagramDescription){
      @Override
      public DiagramContext getResult() {
        return new CCDiagram(executionContext, _diagram);
      }
    }.run();
  }
  
  public void insertActor(String id){
    new InsertRemoveActorsTool(this).insert(id);
  }
  
  public void removeActor(String id){
    new InsertRemoveActorsTool(this).remove(id);
  }
  
  public void insertCapability(String id)
  {
    new InsertRemoveCapabilitiesTool(this).insert(id);
  }
  
  public void removeCapability(String id)
  {
    new InsertRemoveCapabilitiesTool(this).remove(id);
  }
  
  public void insertMission(String id)
  {
    new InsertRemoveMissionsTool(this).insert(id);
  }
  
  public void removeMission(String id)
  { 
    new InsertRemoveMissionsTool(this).remove(id);
  }
}
