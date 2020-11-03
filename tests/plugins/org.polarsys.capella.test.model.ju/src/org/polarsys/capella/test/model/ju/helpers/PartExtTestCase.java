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
package org.polarsys.capella.test.model.ju.helpers;

import java.util.Arrays;
import java.util.List;

import org.polarsys.capella.core.commands.preferences.service.ScopedCapellaPreferencesStore;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.libraries.model.ICapellaModel;
import org.polarsys.capella.core.libraries.utils.ScopeModelWrapper;
import org.polarsys.capella.core.model.helpers.PartExt;
import org.polarsys.capella.core.model.preferences.IDeploymentPreferences;
import org.polarsys.capella.shared.id.handler.IScope;
import org.polarsys.capella.shared.id.handler.IdManager;
import org.polarsys.capella.test.framework.api.BasicTestCase;

public class PartExtTestCase extends BasicTestCase {
  public static final String ACTOR_BEHAVIOR_2 = "3eea84a2-f2a3-4efc-93f2-cf44dfc2020b"; //$NON-NLS-1$
  public static final String DEPLOYED_PC_NODE_1 = "579dcdaa-98a1-4c79-a2d9-64adc202319e"; //$NON-NLS-1$
  public static final String DEPLOYED_ACTOR_NODE_1 = "f0eb97d1-919c-453a-909c-f3db749bff04"; //$NON-NLS-1$

  public static String MODEL_NAME = "miscmodel"; //$NON-NLS-1$

  @Override
  public List<String> getRequiredTestModels() {
    return Arrays.asList(MODEL_NAME);
  }

  @Override
  public void test() throws Exception {
    ICapellaModel model = getTestModel(MODEL_NAME);
    IScope scope = new ScopeModelWrapper(model);
    
    assertFalse("In mono part mode, deployed part is not deployable", PartExt.isDeployable(((Component) IdManager.getInstance().getEObject(DEPLOYED_PC_NODE_1, scope)).getRepresentingParts().get(0)));
    assertFalse("In mono part mode, deployed part is not deployable", PartExt.isDeployable(((Component) IdManager.getInstance().getEObject(DEPLOYED_ACTOR_NODE_1, scope)).getRepresentingParts().get(0)));
    assertTrue("In mono part mode, not deployed part is deployable", PartExt.isDeployable(((Component) IdManager.getInstance().getEObject(ACTOR_BEHAVIOR_2, scope)).getRepresentingParts().get(0)));
    
    ScopedCapellaPreferencesStore.getInstance("").setValue(IDeploymentPreferences.PREFS_ALLOW_MULTIPLE_DEPLOYMENT, true);
    
    assertTrue("In multi part mode, deployed part is deployable", PartExt.isDeployable(((Component) IdManager.getInstance().getEObject(DEPLOYED_PC_NODE_1, scope)).getRepresentingParts().get(0)));
    assertTrue("In multi part mode, deployed part is deployable", PartExt.isDeployable(((Component) IdManager.getInstance().getEObject(DEPLOYED_ACTOR_NODE_1, scope)).getRepresentingParts().get(0)));
    assertTrue("In multi part mode, not deployed part is deployable", PartExt.isDeployable(((Component) IdManager.getInstance().getEObject(ACTOR_BEHAVIOR_2, scope)).getRepresentingParts().get(0)));
    
    ScopedCapellaPreferencesStore.getInstance("").setValue(IDeploymentPreferences.PREFS_ALLOW_MULTIPLE_DEPLOYMENT, false);

  }

}
