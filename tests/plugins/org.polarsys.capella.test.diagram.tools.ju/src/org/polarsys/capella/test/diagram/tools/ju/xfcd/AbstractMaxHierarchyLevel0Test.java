/*******************************************************************************
 * Copyright (c) 2019, 2020, THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.diagram.tools.ju.xfcd;

import org.eclipse.sirius.business.api.session.Session;
import org.polarsys.capella.core.data.fa.FunctionalChainInvolvementLink;
import org.polarsys.capella.test.diagram.common.ju.context.XFCDDiagram;
import org.polarsys.capella.test.diagram.tools.ju.model.AbstractFunctionalChainToolTest;
import org.polarsys.capella.test.framework.context.SessionContext;

/**
 * Max Hierarchy level: 0 | Tested tools: Function, Exchange, Exchange and Function, Connect.
 * 
 */
public abstract class AbstractMaxHierarchyLevel0Test extends AbstractFunctionalChainToolTest {

  // to be defined by the child class
  protected String EMPTYCHAIN1_DIAG;

  protected String FUNCTION_2;
  protected String FUNCTION_3;

  protected String EXCHANGE_1;
  protected String EXCHANGE_2;
  protected String EXCHANGE_3;
  protected String EXCHANGE_5;

  @Override
  public void test() throws Exception {

    Session session = getSession(getRequiredTestModel());
    SessionContext context = new SessionContext(session);

    XFCDDiagram xfcd = XFCDDiagram.getDiagram(context, EMPTYCHAIN1_DIAG);

    // involve two functions
    String involvementFunction2Id = xfcd.involveFunction(xfcd.getDiagramId(), FUNCTION_2);
    String involvementFunction3Id = xfcd.involveFunction(xfcd.getDiagramId(), FUNCTION_3);

    // involve an exchange between them
    String involvementLink2Id = xfcd.involveExchange(involvementFunction2Id, involvementFunction3Id, EXCHANGE_2);
    FunctionalChainInvolvementLink involvementLink2 = (FunctionalChainInvolvementLink) xfcd.getSessionContext()
        .getSemanticElement(involvementLink2Id);

    // check the reference hierarchy
    assertTrue(involvementLink2.getSourceReferenceHierarchy().isEmpty());
    assertTrue(involvementLink2.getTargetReferenceHierarchy().isEmpty());

    // involve exchange and function for target
    xfcd.involveExchangeAndFunction(involvementFunction2Id, EXCHANGE_1);

    // involve exchange and function for source
    xfcd.involveExchangeAndFunction(involvementFunction3Id, EXCHANGE_3);
    xfcd.involveExchangeAndFunction(involvementFunction3Id, EXCHANGE_5);
  }

}
