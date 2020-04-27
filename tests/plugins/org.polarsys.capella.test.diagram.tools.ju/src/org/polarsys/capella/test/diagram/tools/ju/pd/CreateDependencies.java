/*******************************************************************************
 * Copyright (c) 2006, 2019 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.diagram.tools.ju.pd;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DDiagramElementContainer;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.DNodeContainer;
import org.polarsys.capella.common.ef.ExecutionManager;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.core.data.capellacore.NamedElement;
import org.polarsys.capella.core.diagram.helpers.naming.DiagramDescriptionConstants;
import org.polarsys.capella.core.sirius.analysis.IDiagramNameConstants;
import org.polarsys.capella.core.sirius.analysis.constants.IToolNameConstants;
import org.polarsys.capella.test.diagram.common.ju.context.DiagramContext;
import org.polarsys.capella.test.diagram.common.ju.step.crud.CreateDiagramStep;
import org.polarsys.capella.test.diagram.common.ju.step.crud.OpenDiagramStep;
import org.polarsys.capella.test.diagram.common.ju.step.tools.CreateAbstractDNodeTool;
import org.polarsys.capella.test.diagram.common.ju.step.tools.CreateEdgeTool;
import org.polarsys.capella.test.diagram.common.ju.step.tools.InsertRemoveTool;
import org.polarsys.capella.test.diagram.tools.ju.model.CDBCommunication;
import org.polarsys.capella.test.diagram.tools.ju.model.settings.CDBProjectSettings;
import org.polarsys.capella.test.framework.model.GenericModel;

public class CreateDependencies extends CDBCommunication {

  public CreateDependencies(CDBProjectSettings settings) {
    super(settings);
  }

  private static final String DATAPKG_1 = "1";
  private static final String DATAPKG_1_1 = "1_1";
  private static final String DATAPKG_2 = "2";
  private static final String DATAPKG_2_1 = "2_1";
  private static final String DATAPKG_3 = "3";
  private static final String DATAPKG_3_1 = "3_1";

  @Override
  protected void testCDB() {
    testDependencies();
  }

  private void setName(ExecutionManager executionManager, NamedElement object, String newName) {
    final String finalNewName = newName;
    final NamedElement finalObject = object;
    executionManager.execute(new AbstractReadWriteCommand() {
      @Override
      public void run() {
        finalObject.setName(finalNewName);
      }
    });
  }

  protected void testDependencies() {
    DiagramContext diagramContext = new CreateDiagramStep(context, settings.DATAPKG,
        DiagramDescriptionConstants.CLASS_BLANK_DIAGRAM_NAME).run();

    new OpenDiagramStep(diagramContext).run();

    DDiagramElementContainer diagramElement = new CreateAbstractDNodeTool<DDiagramElementContainer>(diagramContext,
        IToolNameConstants.TOOL_CDB_CREATE_DATA_PACKAGE, diagramContext.getDiagramId(), DATAPKG_1,
        DDiagramElementContainer.class).run();
    ExecutionManager executionManager = TransactionHelper.getExecutionManager(diagramElement.getTarget());
    setName(executionManager, (NamedElement) diagramElement.getTarget(), DATAPKG_1);

    diagramElement = new CreateAbstractDNodeTool<DDiagramElementContainer>(diagramContext,
        IToolNameConstants.TOOL_CDB_CREATE_CLASS, DATAPKG_1, GenericModel.CLASS_4, DDiagramElementContainer.class)
            .run();
    setName(executionManager, (NamedElement) diagramElement.getTarget(), GenericModel.CLASS_4);

    diagramElement = new CreateAbstractDNodeTool<DDiagramElementContainer>(diagramContext,
        IToolNameConstants.TOOL_CDB_CREATE_DATA_PACKAGE, DATAPKG_1, DATAPKG_1_1, DDiagramElementContainer.class).run();
    setName(executionManager, (NamedElement) diagramElement.getTarget(), DATAPKG_1_1);

    diagramElement = new CreateAbstractDNodeTool<DDiagramElementContainer>(diagramContext,
        IToolNameConstants.TOOL_CDB_CREATE_CLASS, DATAPKG_1_1, GenericModel.CLASS_1, DDiagramElementContainer.class)
            .run();
    setName(executionManager, (NamedElement) diagramElement.getTarget(), GenericModel.CLASS_1);

    diagramElement = new CreateAbstractDNodeTool<DDiagramElementContainer>(diagramContext,
        IToolNameConstants.TOOL_CDB_CREATE_DATA_PACKAGE, diagramContext.getDiagramId(), DATAPKG_2,
        DDiagramElementContainer.class).run();
    setName(executionManager, (NamedElement) diagramElement.getTarget(), DATAPKG_2);
    diagramElement = new CreateAbstractDNodeTool<DDiagramElementContainer>(diagramContext,
        IToolNameConstants.TOOL_CDB_CREATE_DATA_PACKAGE, DATAPKG_2, DATAPKG_2_1, DDiagramElementContainer.class).run();
    setName(executionManager, (NamedElement) diagramElement.getTarget(), DATAPKG_2_1);
    diagramElement = new CreateAbstractDNodeTool<DDiagramElementContainer>(diagramContext,
        IToolNameConstants.TOOL_CDB_CREATE_CLASS, DATAPKG_2_1, GenericModel.CLASS_2, DDiagramElementContainer.class)
            .run();
    setName(executionManager, (NamedElement) diagramElement.getTarget(), GenericModel.CLASS_2);

    diagramElement = new CreateAbstractDNodeTool<DDiagramElementContainer>(diagramContext,
        IToolNameConstants.TOOL_CDB_CREATE_DATA_PACKAGE, diagramContext.getDiagramId(), DATAPKG_3,
        DDiagramElementContainer.class).run();
    setName(executionManager, (NamedElement) diagramElement.getTarget(), DATAPKG_3);
    diagramElement = new CreateAbstractDNodeTool<DDiagramElementContainer>(diagramContext,
        IToolNameConstants.TOOL_CDB_CREATE_DATA_PACKAGE, DATAPKG_3, DATAPKG_3_1, DDiagramElementContainer.class).run();
    setName(executionManager, (NamedElement) diagramElement.getTarget(), DATAPKG_3_1);
    diagramElement = new CreateAbstractDNodeTool<DDiagramElementContainer>(diagramContext,
        IToolNameConstants.TOOL_CDB_CREATE_CLASS, DATAPKG_3_1, GenericModel.CLASS_3, DDiagramElementContainer.class)
            .run();
    setName(executionManager, (NamedElement) diagramElement.getTarget(), GenericModel.CLASS_3);

    new CreateEdgeTool(diagramContext, IToolNameConstants.TOOL_CDB_CREATE_ASSOCIATION, "ASSOC 1", GenericModel.CLASS_3,
        GenericModel.CLASS_2, 1).run();
    new CreateEdgeTool(diagramContext, IToolNameConstants.TOOL_CDB_CREATE_ASSOCIATION, "ASSOC 2", GenericModel.CLASS_3,
        GenericModel.CLASS_1, 1).run();
    new CreateEdgeTool(diagramContext, IToolNameConstants.TOOL_CDB_CREATE_ASSOCIATION, "ASSOC 3", GenericModel.CLASS_2,
        GenericModel.CLASS_1, 1).run();
    new CreateEdgeTool(diagramContext, IToolNameConstants.TOOL_CDB_CREATE_ASSOCIATION, "ASSOC 4", GenericModel.CLASS_4,
        GenericModel.CLASS_1, 1).run();

    DDiagram classDiagram = diagramContext.getDiagram();
    assertEquals("Wrong number of DiagramElements in the Class Diagram", 14, classDiagram.getDiagramElements().size());

    DiagramContext pdDiagramContext = new CreateDiagramStep(context, DATAPKG_1,
        IDiagramNameConstants.PACKAGE_DEPENDENCIES_DIAGRAM_NAME).run();

    new OpenDiagramStep(pdDiagramContext).run();
    new InsertRemoveTool(pdDiagramContext, IToolNameConstants.TOOL_CDB_SHOW_HIDE_DATAPKGS).insert(DATAPKG_1, DATAPKG_2,
        DATAPKG_3);

    DDiagram dependenciesDiagram = pdDiagramContext.getDiagram();

    session.save(new NullProgressMonitor());

    // Test we have 6 diagram elements 3 nodes and 3 edges
    assertEquals("Wrong number of DiagramElements in the Dependencies Diagram", 6,
        dependenciesDiagram.getDiagramElements().size());

    // Check the created Edges are the ones expected 3->2, 3->1 and 2->1
    for (DDiagramElement element : dependenciesDiagram.getOwnedDiagramElements()) {
      if (element instanceof DEdge) {
        DEdge edge = (DEdge) element;
        String sourceNodeName = ((DNodeContainer) edge.getSourceNode()).getName();
        String targetNodeName = ((DNodeContainer) edge.getTargetNode()).getName();
        assertTrue("Wrong Edge created " + sourceNodeName + " -> " + targetNodeName,
            (DATAPKG_3.equals(sourceNodeName) && DATAPKG_2.equals(targetNodeName))
                || (DATAPKG_3.equals(sourceNodeName) && DATAPKG_1.equals(targetNodeName))
                || (DATAPKG_2.equals(sourceNodeName) && DATAPKG_1.equals(targetNodeName)));
      }
    }

  }
}
