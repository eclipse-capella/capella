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
 * Max Hierarchy level: 2 | Tested tools: Functional chain, Function, Exchange, Connect.
 * 
 */
public abstract class AbstractMaxHierarchyLevel2Test extends AbstractFunctionalChainToolTest {

  // to be defined by the child class
  protected String LEVEL3_CHAIN1_DIAG;
  protected String LEVEL1CHAIN1;

  protected String FUNCTION_2;
  protected String FUNCTION_3;

  protected String EXCHANGE_2;

  protected String FUNCTIONAL_CHAIN_REFERENCE_TO_LEVEL2CHAIN1;
  protected String FUNCTIONAL_CHAIN_REFERENCE_TO_LEVEL2CHAIN1_BIS;

  @Override
  public void test() throws Exception {
    
    Session session = getSession(getRequiredTestModel());
    SessionContext context = new SessionContext(session);

    XFCDDiagram xfcd = XFCDDiagram.getDiagram(context, LEVEL3_CHAIN1_DIAG);
    SessionContext sessionContext = xfcd.getSessionContext();

    // level 2 chains that will serve as containers
    FunctionalChainReference functionalChainRefLevel2 = sessionContext
        .getSemanticElement(FUNCTIONAL_CHAIN_REFERENCE_TO_LEVEL2CHAIN1);
    FunctionalChain functionalChainLevel2 = functionalChainRefLevel2.getReferencedFunctionalChain();

    FunctionalChainReference functionalChainRefLevel2Bis = sessionContext
        .getSemanticElement(FUNCTIONAL_CHAIN_REFERENCE_TO_LEVEL2CHAIN1_BIS);
    FunctionalChain functionalChainLevel2Bis = functionalChainRefLevel2Bis.getReferencedFunctionalChain();

    // involve functional chain in another functional chain (functionalChainLevel2)
    String functionalChainRefLevel1Id = xfcd.involveFunctionalChain(functionalChainLevel2.getId(), LEVEL1CHAIN1);
    FunctionalChainReference functionalChainRefLevel1 = sessionContext.getSemanticElement(functionalChainRefLevel1Id);
    FunctionalChain functionalChainLevel1 = functionalChainRefLevel1.getReferencedFunctionalChain();

    // involve functional chain in another functional chain (functionalChainLevel2Bis)
    String functionalChainRefLevel1IdBis = xfcd.involveFunctionalChain(functionalChainLevel2Bis.getId(), LEVEL1CHAIN1);
    FunctionalChainReference functionalChainRefLevel1Bis = sessionContext
        .getSemanticElement(functionalChainRefLevel1IdBis);
    FunctionalChain functionalChainLevel1Bis = functionalChainRefLevel1Bis.getReferencedFunctionalChain();

    // exchange between functions in different functional chains
    String involvementFunction2Level2Id = xfcd.involveFunction(functionalChainLevel2.getId(), FUNCTION_2);
    String involvementFunction3Level1Id = xfcd.involveFunction(functionalChainLevel1.getId(), FUNCTION_3);

    String exchange2Id = xfcd.involveExchange(involvementFunction2Level2Id, involvementFunction3Level1Id, EXCHANGE_2);
    FunctionalChainInvolvementLink exchangeLink2 = sessionContext.getSemanticElement(exchange2Id);

    // check the link hierarchy
    assertTrue(exchangeLink2.getSourceReferenceHierarchy().isEmpty());
    assertEquals(Arrays.asList(functionalChainRefLevel1), exchangeLink2.getTargetReferenceHierarchy());

    // exchange between functions in functional chain and function on diagram
    String involvementFunction3OnDiagram = xfcd.involveFunction(xfcd.getDiagramId(), FUNCTION_3);
    String link3Id = xfcd.connectFunctions(involvementFunction3Level1Id, involvementFunction3OnDiagram);
    FunctionalChainInvolvementLink link3 = sessionContext.getSemanticElement(link3Id);

    // check the link hierarchy
    assertEquals(Arrays.asList(functionalChainRefLevel1, functionalChainRefLevel2),
        link3.getSourceReferenceHierarchy());
    assertTrue(link3.getTargetReferenceHierarchy().isEmpty());

    // connect between functions in different functional chains
    String involvementFunction3Level2BisId = xfcd.involveFunction(functionalChainLevel2Bis.getId(), FUNCTION_3);
    String connectLink3Id = xfcd.connectFunctions(involvementFunction3Level1Id, involvementFunction3Level2BisId);
    FunctionalChainInvolvementLink connectLink3 = sessionContext.getSemanticElement(connectLink3Id);

    // check the link hierarchy
    assertEquals(Arrays.asList(functionalChainRefLevel1), connectLink3.getSourceReferenceHierarchy());
    assertTrue(connectLink3.getTargetReferenceHierarchy().isEmpty());
  }
}
