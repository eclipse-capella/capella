/*******************************************************************************
 * Copyright (c) 2017 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.test.diagram.tools.ju.diagram.actions;

import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.core.libraries.model.ICapellaModel;
import org.polarsys.capella.core.libraries.utils.ScopeModelWrapper;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt.Type;
import org.polarsys.capella.shared.id.handler.IScope;
import org.polarsys.capella.shared.id.handler.IdManager;
import org.polarsys.capella.test.diagram.common.ju.api.AbstractDiagramTestCase;
import org.polarsys.capella.test.diagram.common.ju.context.XABDiagram;
import org.polarsys.capella.test.framework.context.SessionContext;
import org.polarsys.capella.test.framework.helpers.GuiActions;

import junit.framework.Test;

/**
 * Verify that after the "Refresh All Sub Representations" action, the PC related to the diagram's contextual element reappears
 */
public class RefreshAllSubRepresentations extends AbstractDiagramTestCase {
  
  public static String PC_2 = "0522138e-b42d-4392-bb8f-ec0afa751794";
  public static String PAB_DIAGRAM = "[PAB] Physical System";
  
  @Override
  public String getRequiredTestModel() {
    return "DiagramAction";
  }
  
  @Override
  public void test() throws Exception {
    Session session = getSessionForTestModel(getRequiredTestModel());
    SessionContext context = new SessionContext(session);
    
    final XABDiagram xab = XABDiagram.openDiagram(context, PAB_DIAGRAM, Type.PA);
    
    ICapellaModel model = getTestModel(getRequiredTestModel());
    IScope scope = new ScopeModelWrapper(model);
    final DDiagramElement pc2View = xab.getView(IdManager.getInstance().getEObject(PC_2, scope));
    
    TransactionHelper.getExecutionManager(IdManager.getInstance().getEObject(PC_2, scope)).execute(new AbstractReadWriteCommand() {
      @Override
      public void run() {
        xab.getDiagram().getOwnedDiagramElements().remove(pc2View);
      }
    });
    
    GuiActions.refreshAllSubRepresentations(getAirdFileForLoadedModel(getRequiredTestModel()), session);
    GuiActions.flushASyncGuiThread();
    xab.hasView(PC_2);
  }

  public static Test suite() {
    return new RefreshAllSubRepresentations();
  }
}
