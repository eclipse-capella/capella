/*******************************************************************************
 * Copyright (c) 2024 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.platform.ju.testcases;

import java.util.Collections;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.core.commands.preferences.preferences.CapellaDiagramPreferences;
import org.polarsys.capella.core.commands.preferences.ui.sirius.DoubleClickBehaviourUtil;
import org.polarsys.capella.core.libraries.model.CapellaModel;
import org.polarsys.capella.core.preferences.Activator;
import org.polarsys.capella.test.framework.api.BasicTestCase;
import org.polarsys.capella.test.framework.helpers.EObjectHelper;

public class CapellaNavigateOnDoubleClickTestCase extends BasicTestCase {
  private static final String OPREF = "25ad13f5-d7f2-4578-9357-c7575ea201c4";
  private static final String FCREF = "fa753755-2732-4c2f-8877-89152e3e34f7";
  private static final String PPREF = "c51fbc42-5749-4525-a63f-10ef7b21ba85";
  private static final String SCREF = "a54d2421-ac33-44b1-b4eb-171a3d0cf514";

  private static final String OPREF_NOTARGET = "3c9ac5d5-6e2b-4ab4-a2c2-c04d75c7d3ca";
  private static final String FCREF_NOTARGET = "86fb844b-0809-4ce1-9c0e-172f994b81ca";
  private static final String PPREF_NOTARGET = "b81dc886-4527-4423-8f9d-96955c4145b1";
  private static final String SCREF_NOTARGET = "829bfc17-79c4-4889-ad60-ead193522e12";

  @Override
  public void test() throws Exception {
    CapellaModel model = getTestModel(getRequiredTestModels().iterator().next());
    
    boolean initialPreferenceValue =  Activator.getDefault().getPreferenceStore()
            .getBoolean(CapellaDiagramPreferences.PREF_DISPLAY_NAVIGATE_ON_DOUBLE_CLICK);

    // With navigation preference set to TRUE
    Activator.getDefault().getPreferenceStore()
        .setValue(CapellaDiagramPreferences.PREF_DISPLAY_NAVIGATE_ON_DOUBLE_CLICK, true);
    testNavigationOnDoubleClick(model, OPREF, true);
    testNavigationOnDoubleClick(model, FCREF, true);
    testNavigationOnDoubleClick(model, PPREF, true);
    testNavigationOnDoubleClick(model, SCREF, true);

    // Navigation to reference with null target is always disabled (=false)
    testNavigationOnDoubleClick(model, OPREF_NOTARGET, false);
    testNavigationOnDoubleClick(model, FCREF_NOTARGET, false);
    testNavigationOnDoubleClick(model, PPREF_NOTARGET, false);
    testNavigationOnDoubleClick(model, SCREF_NOTARGET, false);

    // With navigation preference set to FALSE
    Activator.getDefault().getPreferenceStore()
        .setValue(CapellaDiagramPreferences.PREF_DISPLAY_NAVIGATE_ON_DOUBLE_CLICK, false);

    testNavigationOnDoubleClick(model, OPREF, false);
    testNavigationOnDoubleClick(model, FCREF, false);
    testNavigationOnDoubleClick(model, PPREF, false);
    testNavigationOnDoubleClick(model, SCREF, false);

    // Navigation to reference with null target is always disabled (=false)
    testNavigationOnDoubleClick(model, OPREF_NOTARGET, false);
    testNavigationOnDoubleClick(model, FCREF_NOTARGET, false);
    testNavigationOnDoubleClick(model, PPREF_NOTARGET, false);
    testNavigationOnDoubleClick(model, SCREF_NOTARGET, false);
    
    // Restore preference value
    Activator.getDefault().getPreferenceStore()
        .setValue(CapellaDiagramPreferences.PREF_DISPLAY_NAVIGATE_ON_DOUBLE_CLICK, initialPreferenceValue);
  }

  protected void testNavigationOnDoubleClick(CapellaModel model, String element, boolean expectedValue) {
    EObject eObject = EObjectHelper.getObject(model, element);
    boolean actualValue = DoubleClickBehaviourUtil.INSTANCE.shouldOpenRelatedDiagramsOnDoubleClick(eObject);
    assertEquals(expectedValue, actualValue);
  }

  @Override
  public List<String> getRequiredTestModels() {
    return Collections.singletonList("TestNavigationDoubleClick");
  }

}
