/*******************************************************************************
 * Copyright (c) 2019 THALES GLOBAL SERVICES.
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

package org.polarsys.capella.test.benchmarks.ju.refreshAllDiagrams;

import org.eclipse.sirius.ui.business.api.preferences.SiriusUIPreferencesKeys;
import org.eclipse.sirius.viewpoint.provider.SiriusEditPlugin;
import org.polarsys.capella.test.benchmarks.ju.testcases.TearDownTestCase;
import org.polarsys.capella.test.framework.api.BasicTestArtefact;

public class RefreshAllDiagramsTearDownTestCase extends TearDownTestCase {

  public RefreshAllDiagramsTearDownTestCase(BasicTestArtefact benchmarkTestCase) {
    super(benchmarkTestCase);
  }

  @Override
  public void tearDown() throws Exception {
    SiriusEditPlugin.getPlugin().getPreferenceStore()
        .setValue(SiriusUIPreferencesKeys.PREF_REFRESH_ON_REPRESENTATION_OPENING.name(), false);
    super.tearDown();
  }

}
