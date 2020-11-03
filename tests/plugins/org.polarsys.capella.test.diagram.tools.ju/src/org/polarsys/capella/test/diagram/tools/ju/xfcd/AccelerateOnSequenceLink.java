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
package org.polarsys.capella.test.diagram.tools.ju.xfcd;

import org.eclipse.sirius.business.api.session.Session;
import org.polarsys.capella.test.diagram.common.ju.context.XFCDDiagram;
import org.polarsys.capella.test.diagram.tools.ju.model.AbstractFunctionalChainToolTest;
import org.polarsys.capella.test.framework.context.SessionContext;

public class AccelerateOnSequenceLink extends AbstractFunctionalChainToolTest {

  @Override
  public void test() throws Exception {
    
    Session session = getSession(getRequiredTestModel());
    SessionContext context = new SessionContext(session);

    XFCDDiagram xfcd = XFCDDiagram.getDiagram(context, SFCD_LEVEL1_CHAIN1_SA_DIAG);
    
    // Functional Exchange is represented on the diagram case
    xfcd.accelerateOnSequenceLinkWithSelect(SEQUENCE_LINK_FUNCTION_4_FUNCTION_6_SA, FUNCTIONAL_EXCHANGE_6_SA);
    
    // Functional Exchange is not represented on the diagram case
    xfcd.accelerateOnSequenceLinkWithSelect(SEQUENCE_LINK_FUNCTION_6_FUNCTION_7_SA, FUNCTIONAL_EXCHANGE_7_SA);
    
    // A new Functional Exchange is created
    xfcd.accelerateOnSequenceLinkWithCreate(SEQUENCE_LINK_FUNCTION_6_FUNCTION_7_SA, SYSTEM_FUNCTION_6_SA, SYSTEM_FUNCTION_7_SA);
  }
}
