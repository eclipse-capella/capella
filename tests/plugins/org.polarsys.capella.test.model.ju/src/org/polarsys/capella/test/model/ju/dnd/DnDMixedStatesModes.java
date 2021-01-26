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
package org.polarsys.capella.test.model.ju.dnd;

import java.util.Arrays;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.business.api.session.Session;
import org.polarsys.capella.core.model.preferences.IModeAndStateManagementPreferences;
import org.polarsys.capella.test.framework.context.SessionContext;
import org.polarsys.capella.test.framework.helpers.GuiActions;
import org.polarsys.capella.test.model.ju.model.MiscModel;

public class DnDMixedStatesModes extends MiscModel {
  private final String REGION_ID = "540670b4-1cd2-4dad-9c25-39e8da85ab89";
  private final String STATE_ID = "6b01bdc1-096a-4cc2-9329-fe9b0ddd8bc5";
  private final String STATE_MACHINE_ID = "199c547f-bbc4-4777-95dd-d394e2dd514b";
  
  @Override
  public void test() throws Exception {
    Session session = getSessionForTestModel(getRequiredTestModels().get(0));
    SessionContext context = new SessionContext(session);
    EObject region = context.getSemanticElement(REGION_ID);
    EObject stateContainer = context.getSemanticElement(STATE_ID);
    EObject stateMachineContainer = context.getSemanticElement(STATE_MACHINE_ID);
    
    context.setPreference(IModeAndStateManagementPreferences.PREFS_MIXED_MODE_STATE_ALLOWED, false);
    assertFalse(GuiActions.canDnD(stateContainer, Arrays.asList(region)));
    assertFalse(GuiActions.canDnD(stateMachineContainer, Arrays.asList(region)));
  
    context.setPreference(IModeAndStateManagementPreferences.PREFS_MIXED_MODE_STATE_ALLOWED, true);
    assertTrue(GuiActions.canDnD(stateContainer, Arrays.asList(region)));
    assertTrue(GuiActions.canDnD(stateMachineContainer, Arrays.asList(region)));
  }

}
