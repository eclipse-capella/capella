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
package org.polarsys.capella.test.benchmarks.ju.saveSessionAfterModifications;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.sirius.business.api.session.Session;
import org.polarsys.capella.test.benchmarks.ju.testcases.AbstractBenchmarkTestCase;

/**
 * 
 * Save the session after modifications made in SaveSessionAfterModificationsSetUpTestCase
 */
public class SaveSessionAfterModificationsTestCase extends AbstractBenchmarkTestCase {

  public SaveSessionAfterModificationsTestCase() {
    // Do nothing
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void test() {
    Session session = getSession(getRequiredTestModels().get(0));
    session.save(new NullProgressMonitor());
  }
}
