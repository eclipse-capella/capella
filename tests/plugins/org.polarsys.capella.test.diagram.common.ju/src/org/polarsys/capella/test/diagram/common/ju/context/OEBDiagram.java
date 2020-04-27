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
import org.eclipse.sirius.diagram.DNode;
import org.polarsys.capella.core.sirius.analysis.constants.IToolNameConstants;
import org.polarsys.capella.test.diagram.common.ju.step.crud.OpenDiagramStep;
import org.polarsys.capella.test.diagram.common.ju.step.tools.CreateAbstractDNodeTool;
import org.polarsys.capella.test.framework.context.SessionContext;

public class OEBDiagram extends CommonDiagram {

  public OEBDiagram(SessionContext context, DDiagram diagram) {
    super(context, diagram);
  }

  public static OEBDiagram openDiagram(SessionContext executionContext, String description) {
    return (OEBDiagram) new OpenDiagramStep(executionContext, description) {
      @Override
      public DiagramContext getResult() {
        return new OEBDiagram(getExecutionContext(), diagram);
      }
    }.run().open();
  }

  @Override
  protected String createNodeElement(String containerId, String toolName) {
    DNode graphicalElement = new CreateAbstractDNodeTool<DNode>(this, toolName, containerId) {
      @Override
      protected int expectedNewElements() {
        return 2;
      }
    }.run();

    return getSemanticIdFromView(graphicalElement);
  }

  public String createOperationalEntity(String containerId) {
    return createNodeElement(containerId, IToolNameConstants.TOOL_OEBD_CREATE_OE);
  }

  public String createOperationalActor(String containerId) {
    return createNodeElement(containerId, IToolNameConstants.TOOL_OEBD_CREATE_OA);
  }

  public String createContainedIn(String containerId, String sourceId, String targetId) {
    return createEdgeElement(sourceId, targetId, IToolNameConstants.TOOL_OEBD_CONTAINED_IN);
  }

}
