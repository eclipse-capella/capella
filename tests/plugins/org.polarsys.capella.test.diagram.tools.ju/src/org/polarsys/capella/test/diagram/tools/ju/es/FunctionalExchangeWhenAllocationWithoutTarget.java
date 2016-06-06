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
package org.polarsys.capella.test.diagram.tools.ju.es;

import junit.framework.Test;

import org.eclipse.sirius.business.api.session.Session;
import org.polarsys.capella.test.diagram.common.ju.context.ESDiagram;
import org.polarsys.capella.test.diagram.common.ju.context.SessionContext;
import org.polarsys.capella.test.diagram.tools.ju.model.ESProject;

/**
 * Test for each architecture phases some basic insert remove on ES diagrams
 */
public class FunctionalExchangeWhenAllocationWithoutTarget extends ESProject {

  @Override
  public void test() throws Exception {
    Session session = getSession(getRequiredTestModel());
    SessionContext context = new SessionContext(session);

    ESDiagram diagram = ESDiagram.createDiagram(context, LA__CAPABILITIES__CAPABILITYREALIZATION_1__ES_SCENARIO_1);

    diagram.createFunctionalExchange(LA__ROOT_LF__FUNCTIONALEXCHANGE_2,
        LA__CAPABILITIES__CAPABILITYREALIZATION_1__ES_SCENARIO_1__LA_1,
        LA__CAPABILITIES__CAPABILITYREALIZATION_1__ES_SCENARIO_1__LA_2);
  }
  
  public static Test suite() {
    return new FunctionalExchangeWhenAllocationWithoutTarget();
  }
}
