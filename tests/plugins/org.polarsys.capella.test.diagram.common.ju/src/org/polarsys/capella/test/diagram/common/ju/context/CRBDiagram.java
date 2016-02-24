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
import org.polarsys.capella.test.diagram.common.ju.step.tools.crb.InsertRemoveActorsTool;
import org.polarsys.capella.test.diagram.common.ju.step.tools.crb.InsertRemoveCapabilitiesTool;

public class CRBDiagram extends DiagramContext {

  public CRBDiagram(SessionContext context, DDiagram diagram) {
    super(context, diagram);
  }

  public static CRBDiagram openDiagram(final SessionContext context, String diagramName)
  {
    return (CRBDiagram) new OpenDiagramStep(context, diagramName){
      @Override
      public DiagramContext getResult() {
        return new CRBDiagram(context, diagram);
      }
    }.run();
  }
  
  public static CRBDiagram createDiagram(final SessionContext executionContext, String targetIdentifier, String diagramDescription)
  {
    return (CRBDiagram) new CreateDiagramStep(executionContext, targetIdentifier, diagramDescription){
      @Override
      public DiagramContext getResult() {
        return new CRBDiagram(executionContext, _diagram);
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
}
