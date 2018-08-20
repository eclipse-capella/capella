/*******************************************************************************
 * Copyright (c) 2018 THALES GLOBAL SERVICES.
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
import org.polarsys.capella.test.diagram.common.ju.api.AbstractDiagramTestCase;
import org.polarsys.capella.test.diagram.common.ju.context.XDFBDiagram;
import org.polarsys.capella.test.framework.context.SessionContext;

import junit.framework.Test;

public class OAIBShowHideOA extends AbstractDiagramTestCase {

  public static final String OA__ROOT_OA = "8c484279-1999-4880-a134-e00ba6466884";
  public static final String OA__OperationalActivityParent = "20bef62d-48cb-40e9-9bac-5d2d9f5ed7b9";
  public static final String OA__OperationalActivityChild = "12d8be60-1b72-45c6-b244-b17e1b9d1d09";

  @Override
  public void test() throws Exception {
    Session session = getSession(getRequiredTestModel());
    SessionContext context = new SessionContext(session);

    testOnOAIBDiagram(context, OA__ROOT_OA);
  }

  protected void testOnOAIBDiagram(SessionContext context, String idSource) {
    XDFBDiagram diagram = XDFBDiagram.createDiagram(context, idSource);

    diagram.insertFunction(diagram.getDiagramId(), OA__OperationalActivityParent);
    diagram.insertFunction(diagram.getDiagramId(), OA__OperationalActivityChild);
  }

  public static Test suite() {
    return new OAIBShowHideOA();
  }

  @Override
  protected String getRequiredTestModel() {
    return "test";
  }
}
