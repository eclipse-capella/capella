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
import org.polarsys.capella.test.diagram.common.ju.step.tools.mb.InsertRemoveActorsTool;
import org.polarsys.capella.test.diagram.common.ju.step.tools.mb.InsertRemoveCapabilitiesTool;
import org.polarsys.capella.test.diagram.common.ju.step.tools.mb.InsertRemoveMissionsTool;

public class MBDiagram extends DiagramContext {

  public MBDiagram(SessionContext context, DDiagram diagram) {
    super(context, diagram);
  }

  public static MBDiagram openDiagram(final SessionContext context, String diagramName)
  {
    return (MBDiagram) new OpenDiagramStep(context, diagramName){
      @Override
      public DiagramContext getResult() {
        return new MBDiagram(context, diagram);
      }
    }.run();
  }
  
  public static MBDiagram createDiagram(final SessionContext executionContext, String targetIdentifier, String diagramDescription)
  {
    return (MBDiagram) new CreateDiagramStep(executionContext, targetIdentifier, diagramDescription){
      @Override
      public DiagramContext getResult() {
        return new MBDiagram(executionContext, _diagram);
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
