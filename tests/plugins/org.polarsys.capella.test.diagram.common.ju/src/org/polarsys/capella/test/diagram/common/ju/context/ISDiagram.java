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
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.sirius.analysis.IDiagramNameConstants;
import org.polarsys.capella.test.diagram.common.ju.step.crud.CreateDiagramStep;
import org.polarsys.capella.test.diagram.common.ju.step.crud.OpenDiagramStep;
import org.polarsys.capella.test.framework.context.SessionContext;

public class ISDiagram extends DiagramContext {

  public ISDiagram(SessionContext context, DDiagram diagram) {
    super(context, diagram);
  }

  public static ISDiagram createDiagram(SessionContext executionContext, String targetIdentifier) {
    String name = IDiagramNameConstants.INTERFACE_SCENARIO_DIAGRAM_NAME;
    return (ISDiagram) new CreateDiagramStep(executionContext, targetIdentifier, name) {
      @Override
      public DiagramContext getResult() {
        return new ISDiagram(getExecutionContext(), diagram);
      }
    }.run().open();
  }

  public static ISDiagram openDiagram(SessionContext executionContext, String name, final BlockArchitectureExt.Type type) {
    return (ISDiagram) new OpenDiagramStep(executionContext, name) {
      @Override
      public DiagramContext getResult() {
        return new ISDiagram(getExecutionContext(), diagram);
      }
    }.run().open();
  }

}
