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

/**
 * Verify that after project preference are properly manipulating the preferences
 */
public class SpecificSettings extends AbstractDiagramTestCase {

  @Override
  public String getRequiredTestModel() {
    return "DiagramAction";
  }

  @Override
  public void test() throws Exception {
    Session session = getSessionForTestModel(getRequiredTestModel());
    
    // Refresh on opening
    session.getSiriusPreferences().setRefreshOnRepresentationOpening(true);
    assertTrue(session.getSiriusPreferences().isRefreshOnRepresentationOpening());
    assertTrue(session.getSiriusPreferences().hasSpecificSettingRefreshOnRepresentationOpening());

    session.getSiriusPreferences().setRefreshOnRepresentationOpening(false);
    assertFalse(session.getSiriusPreferences().isRefreshOnRepresentationOpening());
    assertTrue(session.getSiriusPreferences().hasSpecificSettingRefreshOnRepresentationOpening());
    
    session.getSiriusPreferences().unsetRefreshOnRepresentationOpening();
    assertFalse(session.getSiriusPreferences().hasSpecificSettingRefreshOnRepresentationOpening());
    
    // Automatic refresh
    session.getSiriusPreferences().setAutoRefresh(true);
    assertTrue(session.getSiriusPreferences().isAutoRefresh());
    assertTrue(session.getSiriusPreferences().hasSpecificSettingAutoRefresh());

    session.getSiriusPreferences().setAutoRefresh(false);
    assertFalse(session.getSiriusPreferences().isAutoRefresh());
    assertTrue(session.getSiriusPreferences().hasSpecificSettingAutoRefresh());
    
    session.getSiriusPreferences().unsetAutoRefresh();
    assertFalse(session.getSiriusPreferences().hasSpecificSettingAutoRefresh());
    
  }
}
