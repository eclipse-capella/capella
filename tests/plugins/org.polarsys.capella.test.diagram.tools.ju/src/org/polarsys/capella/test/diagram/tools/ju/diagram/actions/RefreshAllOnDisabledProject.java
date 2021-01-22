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
import org.polarsys.capella.core.sirius.ui.helper.SessionHelper;
import org.polarsys.capella.test.diagram.common.ju.api.AbstractDiagramTestCase;
import org.polarsys.capella.test.framework.helpers.GuiActions;

/**
 * Verify that after the "Refresh All Sub Representations" action, the option "Refresh on opening" on project setting is
 * still disabled
 */
public class RefreshAllOnDisabledProject extends AbstractDiagramTestCase {

  @Override
  public String getRequiredTestModel() {
    return "DiagramAction";
  }

  protected boolean getValue() {
    return false;
  }
  
  @Override
  public void test() throws Exception {
    Session session = getSessionForTestModel(getRequiredTestModel());
    testOptions(session, getValue());
  }

  private void testOptions(Session session, boolean b) {
    session.getSiriusPreferences().setRefreshOnRepresentationOpening(b);
    assertTrue(SessionHelper.hasSpecificSettingRefreshOnRepresentationOpening(session));
    
    GuiActions.refreshAllSubRepresentations(getAirdFileForLoadedModel(getRequiredTestModel()), session, () -> {
      assertEquals(b, session.getSiriusPreferences().isRefreshOnRepresentationOpening());
      assertTrue(SessionHelper.hasSpecificSettingRefreshOnRepresentationOpening(session));
    });
  }
}
