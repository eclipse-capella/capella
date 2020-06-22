/*******************************************************************************
 * Copyright (c) 2020, THALES GLOBAL SERVICES.
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
import org.polarsys.capella.test.diagram.common.ju.api.AbstractDiagramTestCase;
import org.polarsys.capella.test.diagram.common.ju.context.XFCDDiagram;
import org.polarsys.capella.test.framework.context.SessionContext;

/**
 * Previously the Involve Exchange tool would not allow cycles at a semantic level.
 * 
 * This constraint has since been relaxed. Cycles are now analyzed at a graphical level. This test ensures that newly
 * authorized cycle scenarios are possible. The existing tests ensure that the old scenarios are still valid.
 *
 */
public class InsertExchangeCycleDetection extends AbstractDiagramTestCase {

  private static final String MODEL_NAME = "opd-fcd-exchange-tool-cycle-model";

  public static final String OPD_2 = "_qGMWlbFpEeq-EJ4697_U6Q"; //$NON-NLS-1$

  public static final String OA2_UID = "_q7F-ULFpEeq-EJ4697_U6Q"; //$NON-NLS-1$
  public static final String OA1_UID = "_qqZ25bFpEeq-EJ4697_U6Q"; //$NON-NLS-1$
  public static final String I5_ID = "944e93fb-57bb-4053-bb3b-489b48f9e891"; //$NON-NLS-1$

  public static final String SFCD_B = "_xLsuk7FGEeqoD5VLzCQ3nw"; //$NON-NLS-1$

  public static final String SF2_UID = "_yRvJmLFGEeqoD5VLzCQ3nw"; //$NON-NLS-1$
  public static final String SF1_UID = "_yosRDbFGEeqoD5VLzCQ3nw"; //$NON-NLS-1$
  public static final String FE6_ID = "ac5aa36f-82d2-48ef-8f6f-2131d2eac8db"; //$NON-NLS-1$

  @Override
  protected String getRequiredTestModel() {
    return MODEL_NAME;
  }

  @Override
  public void test() throws Exception {
    Session session = getSession(getRequiredTestModel());
    SessionContext context = new SessionContext(session);

    XFCDDiagram diagram = XFCDDiagram.getDiagram(context, OPD_2);
    diagram.involveExchange(OA2_UID, OA1_UID, I5_ID);

    diagram = XFCDDiagram.getDiagram(context, SFCD_B);
    diagram.involveExchange(SF2_UID, SF1_UID, FE6_ID);
  }

}
