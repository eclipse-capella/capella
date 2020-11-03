/*******************************************************************************
 * Copyright (c) 2019, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.diagram.tools.ju.sdfb;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.sirius.business.api.session.Session;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;
import org.polarsys.capella.core.libraries.model.ICapellaModel;
import org.polarsys.capella.core.libraries.utils.ScopeModelWrapper;
import org.polarsys.capella.shared.id.handler.IScope;
import org.polarsys.capella.shared.id.handler.IdManager;
import org.polarsys.capella.test.diagram.common.ju.context.DiagramContext;
import org.polarsys.capella.test.diagram.common.ju.step.crud.OpenDiagramStep;
import org.polarsys.capella.test.diagram.tools.ju.model.SwitchCategory;
import org.polarsys.capella.test.framework.context.SessionContext;
import org.polarsys.capella.test.framework.helpers.TestHelper;

import junit.framework.Test;

/**
 * Category without any exchange behind should not be displayed
 *
 */
public class RemoveCategoryWithoutExchange extends SwitchCategory {

  @Override
  public void test() throws Exception {
    Session session = getSessionForTestModel(getRequiredTestModel());
    SessionContext context = new SessionContext(session);
    DiagramContext diagramContext = new OpenDiagramStep(context, LDFB_ROOT_LOGICAL_FUNCTION).run();
    ICapellaModel model = getTestModel(getRequiredTestModel());
    IScope scope = new ScopeModelWrapper(model);

    EObject fe1 = IdManager.getInstance().getEObject(FUNCTIONALEXCHANGE_1, scope);
    TestHelper.getExecutionManager(session).execute(new AbstractReadWriteCommand() {
      @Override
      public void run() {
        EcoreUtil.delete(fe1);
      }
    });
    diagramContext.refreshDiagram();
    // A category without any exchange behind it should not be displayed
    diagramContext.hasntView(EXCHANGECATEGORY_1);
    
    DiagramContext pabDiagramContext = new OpenDiagramStep(context, PAB_Category_Delegation_Test).run();
    EObject pl1 = IdManager.getInstance().getEObject(PL_1, scope);
    EObject ce1 = IdManager.getInstance().getEObject(C_1, scope);
    TestHelper.getExecutionManager(session).execute(new AbstractReadWriteCommand() {
      @Override
      public void run() {
        EcoreUtil.delete(pl1);
        EcoreUtil.delete(ce1);
      }
    });
    pabDiagramContext.refreshDiagram();
    pabDiagramContext.hasntView(PHYSICALLINKCATEGORY_1);
    pabDiagramContext.hasntView(COMPONENTEXCHANGECATEGORY_1);
  }

  public static Test suite() {
    return new RemoveCategoryWithoutExchange();
  }
}
