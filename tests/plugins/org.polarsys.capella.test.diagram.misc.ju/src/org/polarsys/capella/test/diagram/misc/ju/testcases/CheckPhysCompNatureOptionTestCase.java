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
package org.polarsys.capella.test.diagram.misc.ju.testcases;

import java.util.Arrays;
import java.util.List;

import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.preferences.ScopedPreferenceStore;
import org.polarsys.capella.core.data.pa.PhysicalComponent;
import org.polarsys.capella.core.data.pa.properties.fields.PhysicalComponentNatureGroup;
import org.polarsys.capella.core.data.pa.properties.sections.PhysicalComponentSection;
import org.polarsys.capella.core.model.preferences.IDataPreferences;
import org.polarsys.capella.core.model.preferences.helpers.PreferencesHelper;
import org.polarsys.capella.core.preferences.Activator;
import org.polarsys.capella.core.ui.properties.fields.AbstractSemanticField;
import org.polarsys.capella.test.framework.api.BasicTestCase;
import org.polarsys.capella.test.framework.context.SessionContext;

public class CheckPhysCompNatureOptionTestCase extends BasicTestCase {

  private ScopedPreferenceStore preferenceStore;

  private static final String PROJECT_NAME = "component-breakdown";

  @Override
  public void test() {
    preferenceStore = (ScopedPreferenceStore) Activator.getDefault().getPreferenceStore();
    Session session = getSession(PROJECT_NAME);
    assertNotNull(session);

    updateDataPreferences("true");
    assertTrue(PreferencesHelper.isChangePhysicalComponentNatureAllowed());
    SessionContext context = new SessionContext(session);
    String idPhysComp_01 = "6a369845-b31c-4c9f-b586-3b659d245b88";
    PhysicalComponent PC_01 = (PhysicalComponent) context.getSemanticElement(idPhysComp_01);
    checkPhysicalComponentSection(PC_01, true);

    updateDataPreferences("false");
    assertFalse(PreferencesHelper.isChangePhysicalComponentNatureAllowed());
    checkPhysicalComponentSection(PC_01, false);
  }

  protected void checkPhysicalComponentSection(PhysicalComponent physicalComponent, boolean checkCriteria) {
    PhysicalComponentSection physicalComponentSection = new PhysicalComponentSection();
    assertTrue(physicalComponentSection.select(physicalComponent));
    Shell temporaryShell = new Shell();
    physicalComponentSection.createControls(temporaryShell, null);
    physicalComponentSection.loadData(physicalComponent);
    List<AbstractSemanticField> semanticFields = physicalComponentSection.getSemanticFields();
    for (AbstractSemanticField crtSemanticField : semanticFields) {
      if (crtSemanticField instanceof PhysicalComponentNatureGroup) {
        PhysicalComponentNatureGroup pcNatureGrp = (PhysicalComponentNatureGroup) crtSemanticField;
        List<Button> pcNatureButtons = pcNatureGrp.getSemanticFields();
        for (Button pcNatureBtn : pcNatureButtons) {
          assertEquals(pcNatureBtn.isEnabled(), checkCriteria);
        }
        break;
      }
    }

  }

  @Override
  public List<String> getRequiredTestModels() {
    return Arrays.asList(PROJECT_NAME);
  }

  private void updateDataPreferences(String newPref) {
    preferenceStore.putValue(IDataPreferences.PREFS_ALLOW_PHYSICAL_COMPONENT_NATURE_CHANGE, newPref);
    String tmpCheckPref = preferenceStore.getString(IDataPreferences.PREFS_ALLOW_PHYSICAL_COMPONENT_NATURE_CHANGE);
    assertTrue(tmpCheckPref.equals(newPref));
  }
}
