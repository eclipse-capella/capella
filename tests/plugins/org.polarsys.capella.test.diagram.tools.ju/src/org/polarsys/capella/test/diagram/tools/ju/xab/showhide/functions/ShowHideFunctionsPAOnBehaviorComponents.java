/*******************************************************************************
 * Copyright (c) 2019, THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.diagram.tools.ju.xab.showhide.functions;

import org.eclipse.sirius.business.api.session.Session;
import org.polarsys.capella.test.diagram.common.ju.context.PABDiagram;
import org.polarsys.capella.test.diagram.common.ju.context.XABDiagram;
import org.polarsys.capella.test.diagram.tools.ju.model.EmptyProject;
import org.polarsys.capella.test.framework.context.SessionContext;

/**
 * This class tests the same scenarios as {@link AbstractShowHideFunctions} but for <strong>behavior</strong>
 * components.
 * 
 */
public final class ShowHideFunctionsPAOnBehaviorComponents extends AbstractShowHideFunctions {

  @Override
  public void test() throws Exception {
    Session session = getSession(getRequiredTestModel());
    SessionContext context = new SessionContext(session);

    String rootSF = EmptyProject.PA__ROOT_PF;
    String rootCPS = EmptyProject.PA__PHYSICAL_SYSTEM;

    initialize(context, rootSF, rootCPS);
    testCommonScenarios(context, rootSF, rootCPS);
  }

  @Override
  protected void createComponent(XABDiagram xab, String id, String containerId) {
    ((PABDiagram) xab).createBehaviorComponent(id, containerId);
  }

  @Override
  protected void insertComponent(XABDiagram xab, String id, String containerId) {
    ((PABDiagram) xab).insertBehaviorComponent(id, containerId);
  }

  @Override
  protected void removeComponent(XABDiagram xab, String id, String containerId) {
    ((PABDiagram) xab).removeBehaviorComponent(id, containerId);
  }

}
