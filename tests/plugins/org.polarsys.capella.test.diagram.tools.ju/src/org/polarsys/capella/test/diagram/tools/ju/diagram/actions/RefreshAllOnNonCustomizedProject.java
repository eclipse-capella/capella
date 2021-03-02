/*******************************************************************************
 * Copyright (c) 2021 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.diagram.tools.ju.diagram.actions;

import org.eclipse.sirius.business.api.session.Session;
import org.polarsys.capella.test.diagram.common.ju.api.AbstractDiagramTestCase;
import org.polarsys.capella.test.framework.helpers.GuiActions;

/**
 * Verify that after the "Refresh All Sub Representations" action, the option "Refresh on opening" is still not
 * customized at project level
 */
public class RefreshAllOnNonCustomizedProject extends AbstractDiagramTestCase {

  public static String PC_2 = "0522138e-b42d-4392-bb8f-ec0afa751794";
  public static String PAB_DIAGRAM = "[PAB] Physical System";

  @Override
  public String getRequiredTestModel() {
    return "DiagramAction";
  }

  @Override
  public void test() throws Exception {
    Session session = getSessionForTestModel(getRequiredTestModel());
    testNoOptions(session);
  }

  private void testNoOptions(Session session) {
    session.getSiriusPreferences().unsetRefreshOnRepresentationOpening();
    assertFalse(session.getSiriusPreferences().hasSpecificSettingRefreshOnRepresentationOpening());

    GuiActions.refreshAllSubRepresentations(getAirdFileForLoadedModel(getRequiredTestModel()), session, () -> {
      assertFalse(session.getSiriusPreferences().hasSpecificSettingRefreshOnRepresentationOpening());
    });
  }

}
