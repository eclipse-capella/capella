/*******************************************************************************
 * Copyright (c) 2019, THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.test.diagram.tools.ju.xab.showhide.functions;

import org.eclipse.sirius.business.api.session.Session;
import org.polarsys.capella.test.diagram.tools.ju.model.EmptyProject;
import org.polarsys.capella.test.framework.context.SessionContext;

public final class ShowHideFunctionsLA extends AbstractShowHideFunctions {

  @Override
  public void test() throws Exception {
    Session session = getSession(getRequiredTestModel());
    SessionContext context = new SessionContext(session);

    String rootSF = EmptyProject.LA__ROOT_LF;
    String rootCPS = EmptyProject.LA__LOGICAL_SYSTEM;

    initialize(context, rootSF, rootCPS);
    testCommonScenarios(context, rootSF, rootCPS);
  }

}
