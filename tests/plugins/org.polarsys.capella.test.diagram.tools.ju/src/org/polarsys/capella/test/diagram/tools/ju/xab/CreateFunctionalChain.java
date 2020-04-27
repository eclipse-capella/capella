/*******************************************************************************
 * Copyright (c) 2017, 2019 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.diagram.tools.ju.xab;

import org.eclipse.sirius.business.api.session.Session;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt.Type;
import org.polarsys.capella.test.diagram.common.ju.context.XABDiagram;
import org.polarsys.capella.test.diagram.tools.ju.model.EmptyProject;
import org.polarsys.capella.test.framework.context.SessionContext;
import org.polarsys.capella.test.framework.model.GenericModel;

public class CreateFunctionalChain extends EmptyProject {

  @Override
  public void test() throws Exception {
    Session session = getSession(getRequiredTestModel());
    SessionContext context = new SessionContext(session);

    testChain(context, SA__SYSTEM);
    testChain(context, LA__LOGICAL_SYSTEM);
    testChain(context, PA__PHYSICAL_SYSTEM);

  }

  protected void testChain(SessionContext context, String idSource) {
    XABDiagram diagram = XABDiagram.createDiagram(context, idSource);

    if (diagram.getDiagramType() == Type.LA)
      diagram.insertComponent(LA__LOGICAL_CONTEXT__PART_LOGICAL_SYSTEM__LOGICAL_SYSTEM);
    
    diagram.createActor(GenericModel.LA_1);
    diagram.createActor(GenericModel.LA_2);
    diagram.createActor(GenericModel.LA_3);
    diagram.createFunction(GenericModel.FUNCTION_1, GenericModel.LA_1);
    diagram.createFunction(GenericModel.FUNCTION_2, GenericModel.LA_2);
    diagram.createFunction(GenericModel.FUNCTION_3, GenericModel.LA_2);

    diagram.createFunctionalExchange(GenericModel.FUNCTION_1, GenericModel.FUNCTION_2,
        GenericModel.FUNCTIONAL_EXCHANGE_1);
    diagram.createFunctionalExchange(GenericModel.FUNCTION_2, GenericModel.FUNCTION_3,
        GenericModel.FUNCTIONAL_EXCHANGE_2);

    diagram.createFunctionalChain(GenericModel.PATH_1, GenericModel.FUNCTIONAL_EXCHANGE_1,
        GenericModel.FUNCTIONAL_EXCHANGE_2);
  }

}
