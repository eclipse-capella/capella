/*******************************************************************************
 * Copyright (c) 2019 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.test.benchmarks.ju.refreshAllDiagrams;

import org.eclipse.sirius.ui.business.api.preferences.SiriusUIPreferencesKeys;
import org.eclipse.sirius.viewpoint.provider.SiriusEditPlugin;
import org.polarsys.capella.test.benchmarks.ju.testcases.AbstractSetUpTestCase;
import org.polarsys.capella.test.framework.api.BasicTestArtefact;

public class RefreshAllDiagramsSetUpTestCase extends AbstractSetUpTestCase {

  public RefreshAllDiagramsSetUpTestCase(BasicTestArtefact benchmarkTestCase) {
    super(benchmarkTestCase);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void test() {
    SiriusEditPlugin.getPlugin().getPreferenceStore()
        .setValue(SiriusUIPreferencesKeys.PREF_REFRESH_ON_REPRESENTATION_OPENING.name(), true);
  }

}
