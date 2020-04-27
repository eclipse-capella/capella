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
package org.polarsys.capella.test.diagram.tools.ju.xfcd;

import java.util.Arrays;

import org.eclipse.sirius.business.api.session.Session;
import org.polarsys.capella.core.data.fa.FunctionalChain;
import org.polarsys.capella.core.data.fa.FunctionalChainInvolvementLink;
import org.polarsys.capella.core.data.fa.FunctionalChainReference;
import org.polarsys.capella.test.diagram.common.ju.context.XFCDDiagram;
import org.polarsys.capella.test.diagram.tools.ju.model.AbstractFunctionalChainToolTest;
import org.polarsys.capella.test.framework.context.SessionContext;

/**
 * Max Hierarchy level: 1 | Tested tools: Functional chain, Function, Exchange, Exchange and Function, Connect.
 * 
 */
public abstract class AbstractMaxHierarchyLevel1Test extends AbstractFunctionalChainToolTest {

  // to be defined by the child class
  protected String EMPTYCHAIN1_DIAG;

  protected String LEVEL1CHAIN1;
  protected String LEVEL1CHAIN2;

  protected String FUNCTION_1;
  protected String FUNCTION_2;
  protected String FUNCTION_3;

  protected String EXCHANGE_2;

  @Override
  public void test() throws Exception {

    Session session = getSession(getRequiredTestModel());
    SessionContext context = new SessionContext(session);

    XFCDDiagram xfcd = XFCDDiagram.getDiagram(context, EMPTYCHAIN1_DIAG);
    SessionContext sessionContext = xfcd.getSessionContext();

    // involve functional chains
    String functionalChainReference1Id = xfcd.involveFunctionalChain(xfcd.getDiagramId(), LEVEL1CHAIN1);
    FunctionalChainReference functionalChainReference1 = sessionContext.getSemanticElement(functionalChainReference1Id);
    FunctionalChain functionalChain1 = functionalChainReference1.getReferencedFunctionalChain();

    String functionalChainReference2Id = xfcd.involveFunctionalChain(xfcd.getDiagramId(), LEVEL1CHAIN2);
    FunctionalChainReference functionalChainReference2 = sessionContext.getSemanticElement(functionalChainReference2Id);
    FunctionalChain functionalChain2 = functionalChainReference2.getReferencedFunctionalChain();

    // involve 3 functions in the functional chains
    String involvementFunction1Id = xfcd.involveFunction(functionalChain1.getId(), FUNCTION_1);
    String involvementFunction3Id = xfcd.involveFunction(functionalChain1.getId(), FUNCTION_3);

    String involvementFunction3BisId = xfcd.involveFunction(functionalChain2.getId(), FUNCTION_3);
    String involvementFunction2Id = xfcd.involveFunction(functionalChain2.getId(), FUNCTION_2);

    // connect 2 functions from different functional chains
    String connectionLink1Id = xfcd.connectFunctions(involvementFunction3Id, involvementFunction3BisId);
    FunctionalChainInvolvementLink connectionLink1 = sessionContext.getSemanticElement(connectionLink1Id);

    // check the link hierarchy
    assertEquals(Arrays.asList(functionalChainReference1), connectionLink1.getSourceReferenceHierarchy());
    assertEquals(Arrays.asList(functionalChainReference2), connectionLink1.getTargetReferenceHierarchy());

    // create an exchange between 2 functions from different functional chains
    String exchangeLink2Id = xfcd.involveExchange(involvementFunction1Id, involvementFunction2Id, EXCHANGE_2);
    FunctionalChainInvolvementLink exchangeLink2 = sessionContext.getSemanticElement(exchangeLink2Id);

    // check the link hierarchy
    assertEquals(Arrays.asList(functionalChainReference1), exchangeLink2.getSourceReferenceHierarchy());
    assertEquals(Arrays.asList(functionalChainReference2), exchangeLink2.getTargetReferenceHierarchy());

    // involve function on diagram
    String involvementFunction2OnDiagramId = xfcd.involveFunction(xfcd.getDiagramId(), FUNCTION_2);

    // link between function on diagram and function in chain
    String exchangeLink3Id = xfcd.involveExchange(involvementFunction2OnDiagramId, involvementFunction3Id, EXCHANGE_2);
    FunctionalChainInvolvementLink exchangeLink3 = sessionContext.getSemanticElement(exchangeLink3Id);

    assertTrue(exchangeLink3.getSourceReferenceHierarchy().isEmpty());
    assertEquals(Arrays.asList(functionalChainReference1), exchangeLink3.getTargetReferenceHierarchy());

    // connect between function in chain and function on diagram
    String connectionLink3Id = xfcd.connectFunctions(involvementFunction2Id, involvementFunction2OnDiagramId);
    FunctionalChainInvolvementLink connectionLink3 = sessionContext.getSemanticElement(connectionLink3Id);

    assertEquals(Arrays.asList(functionalChainReference2), connectionLink3.getSourceReferenceHierarchy());
    assertTrue(connectionLink3.getTargetReferenceHierarchy().isEmpty());
  }
}
