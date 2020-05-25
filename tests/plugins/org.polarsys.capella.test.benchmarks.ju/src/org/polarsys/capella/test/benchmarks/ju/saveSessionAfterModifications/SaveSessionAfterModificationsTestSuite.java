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

import java.util.List;

import org.polarsys.capella.test.benchmarks.ju.suites.BenchmarkTestSuite;
import org.polarsys.capella.test.diagram.common.ju.context.DiagramContext;
import org.polarsys.capella.test.framework.api.BasicTestArtefact;

import junit.framework.Test;

public class SaveSessionAfterModificationsTestSuite extends BenchmarkTestSuite {
  /**
   * Returns the suite. This is required to unary launch this test.
   */
  public static Test suite() {
    return new SaveSessionAfterModificationsTestSuite();
  }

  @Override
  protected BasicTestArtefact initMainTestCase(List<DiagramContext> contexts) {
    return new SaveSessionAfterModificationsTestCase();
  }

  @Override
  protected BasicTestArtefact initSetUpTestCase(List<DiagramContext> contexts, BasicTestArtefact mainTestCase) {
    return new SaveSessionAfterModificationsSetUpTestCase(mainTestCase);
  }
}
