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
package org.polarsys.capella.test.diagram.tools.ju.xdfb;

import org.eclipse.sirius.business.api.session.Session;
import org.polarsys.capella.test.diagram.common.ju.context.XDFBDiagram;
import org.polarsys.capella.test.diagram.tools.ju.model.EmptyProject;
import org.polarsys.capella.test.framework.context.SessionContext;
import org.polarsys.capella.test.framework.model.GenericModel;

public class CreateFunctionalChain extends EmptyProject {


  @Override
  public void test() throws Exception {
    Session session = getSession(getRequiredTestModel());
    SessionContext context = new SessionContext(session);

    testChain(context, SA__ROOT_SF);
    testChain(context, LA__ROOT_LF);
    testChain(context, PA__ROOT_PF);

  }
  
  protected void testChain(SessionContext context, String idSource) {
    XDFBDiagram diagram = XDFBDiagram.createDiagram(context, idSource);

    diagram.createFunction(GenericModel.FUNCTION_1);
    diagram.createFunction(GenericModel.FUNCTION_2);
    diagram.createActorFunction(GenericModel.FUNCTION_3);
    
    diagram.createFunctionalExchange(GenericModel.FUNCTIONAL_EXCHANGE_1, GenericModel.FUNCTION_1, GenericModel.FUNCTION_2);
    diagram.createFunctionalExchange(GenericModel.FUNCTIONAL_EXCHANGE_2, GenericModel.FUNCTION_2, GenericModel.FUNCTION_3);

    diagram.createFunctionalChain(GenericModel.PATH_1, GenericModel.FUNCTIONAL_EXCHANGE_1, GenericModel.FUNCTIONAL_EXCHANGE_2);
  }
  
  
}
