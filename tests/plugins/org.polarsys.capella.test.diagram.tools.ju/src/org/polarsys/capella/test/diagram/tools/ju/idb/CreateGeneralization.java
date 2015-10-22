/*******************************************************************************
 * Copyright (c) 2006, 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.test.diagram.tools.ju.idb;

import org.eclipse.sirius.business.api.session.Session;
import org.polarsys.capella.core.commands.preferences.service.AbstractPreferencesInitializer;
import org.polarsys.capella.core.model.preferences.IInheritancePreferences;
import org.polarsys.capella.core.sirius.analysis.IDiagramNameConstants;
import org.polarsys.capella.core.sirius.analysis.constants.IToolNameConstants;
import org.polarsys.capella.test.diagram.common.ju.context.DiagramContext;
import org.polarsys.capella.test.diagram.common.ju.context.SessionContext;
import org.polarsys.capella.test.diagram.common.ju.step.crud.CreateDiagramStep;
import org.polarsys.capella.test.diagram.common.ju.step.crud.OpenDiagramStep;
import org.polarsys.capella.test.diagram.common.ju.step.tools.CreateContainerTool;
import org.polarsys.capella.test.diagram.common.ju.step.tools.CreateDEdgeTool;
import org.polarsys.capella.test.diagram.tools.ju.model.EmptyProject;
import org.polarsys.capella.test.diagram.tools.ju.model.GenericModel;

public class CreateGeneralization extends EmptyProject {

  @Override
  public void test() throws Exception {
    Session session = getSession(getRequiredTestModel());
    SessionContext context = new SessionContext(session);

    DiagramContext diagramContext = new CreateDiagramStep(context, LA__LOGICAL_SYSTEM,
        IDiagramNameConstants.INTERFACES_BLANK_DIAGRAM_NAME).run();

    new OpenDiagramStep(diagramContext).run();

    testInterfaces(diagramContext);

    testComponents(diagramContext);
  }

  /**
   * Test basic preference generalization between components
   */
  private void testComponents(DiagramContext diagramContext) {

    new CreateContainerTool(diagramContext, IToolNameConstants.TOOL_IDB_CREATE_COMPONENT, GenericModel.LC_1,
        diagramContext.getDiagramId()).run();

    new CreateContainerTool(diagramContext, IToolNameConstants.TOOL_IDB_CREATE_COMPONENT, GenericModel.LC_2,
        diagramContext.getDiagramId()).run();

    new CreateContainerTool(diagramContext, IToolNameConstants.TOOL_IDB_CREATE_COMPONENT, GenericModel.LC_3,
        diagramContext.getDiagramId()).run();

    AbstractPreferencesInitializer.preferencesManager.setValue(
        IInheritancePreferences.PREFS_ALLOW_COMPONENT_INHERITANCE, false);

    AbstractPreferencesInitializer.preferencesManager.setValue(
        IInheritancePreferences.PREFS_ALLOW_MULTIPLE_INHERITANCE, false);

    new CreateDEdgeTool(diagramContext, IToolNameConstants.TOOL_IDB_CREATE_GENERALIZATION,
        GenericModel.GENERALIZATION_1, GenericModel.LC_1, GenericModel.LC_2).cannotRun();

    AbstractPreferencesInitializer.preferencesManager.setValue(
        IInheritancePreferences.PREFS_ALLOW_COMPONENT_INHERITANCE, true);

    new CreateDEdgeTool(diagramContext, IToolNameConstants.TOOL_IDB_CREATE_GENERALIZATION,
        GenericModel.GENERALIZATION_1, GenericModel.LC_1, GenericModel.LC_2).run();

    new CreateDEdgeTool(diagramContext, IToolNameConstants.TOOL_IDB_CREATE_GENERALIZATION,
        GenericModel.GENERALIZATION_1, GenericModel.LC_1, GenericModel.LC_2).cannotRun();

    new CreateDEdgeTool(diagramContext, IToolNameConstants.TOOL_IDB_CREATE_GENERALIZATION,
        GenericModel.GENERALIZATION_2, GenericModel.LC_1, GenericModel.LC_3).cannotRun();

    AbstractPreferencesInitializer.preferencesManager.setValue(
        IInheritancePreferences.PREFS_ALLOW_MULTIPLE_INHERITANCE, true);

    new CreateDEdgeTool(diagramContext, IToolNameConstants.TOOL_IDB_CREATE_GENERALIZATION,
        GenericModel.GENERALIZATION_1, GenericModel.LC_1, GenericModel.LC_3).run();

    new CreateDEdgeTool(diagramContext, IToolNameConstants.TOOL_IDB_CREATE_GENERALIZATION,
        GenericModel.GENERALIZATION_1, GenericModel.LC_1, GenericModel.LC_3).cannotRun();
  }

  /**
   * Test basic cycle generalization between interfaces
   */
  private void testInterfaces(DiagramContext diagramContext) {

    new CreateContainerTool(diagramContext, IToolNameConstants.TOOL_IDB_CREATE_INTERFACE, GenericModel.INTERFACE_1,
        diagramContext.getDiagramId()).run();

    new CreateContainerTool(diagramContext, IToolNameConstants.TOOL_IDB_CREATE_INTERFACE, GenericModel.INTERFACE_2,
        diagramContext.getDiagramId()).run();

    new CreateContainerTool(diagramContext, IToolNameConstants.TOOL_IDB_CREATE_INTERFACE, GenericModel.INTERFACE_3,
        diagramContext.getDiagramId()).run();

    new CreateDEdgeTool(diagramContext, IToolNameConstants.TOOL_IDB_CREATE_GENERALIZATION, GenericModel.INTERFACE_1,
        GenericModel.INTERFACE_2).run();

    new CreateDEdgeTool(diagramContext, IToolNameConstants.TOOL_IDB_CREATE_GENERALIZATION, GenericModel.INTERFACE_2,
        GenericModel.INTERFACE_3).run();

    new CreateDEdgeTool(diagramContext, IToolNameConstants.TOOL_IDB_CREATE_GENERALIZATION, GenericModel.INTERFACE_3,
        GenericModel.INTERFACE_1).cannotRun();

    new CreateDEdgeTool(diagramContext, IToolNameConstants.TOOL_IDB_CREATE_GENERALIZATION, GenericModel.INTERFACE_1,
        GenericModel.INTERFACE_2).cannotRun();

    new CreateDEdgeTool(diagramContext, IToolNameConstants.TOOL_IDB_CREATE_GENERALIZATION, GenericModel.INTERFACE_2,
        GenericModel.INTERFACE_3).cannotRun();

  }
}
