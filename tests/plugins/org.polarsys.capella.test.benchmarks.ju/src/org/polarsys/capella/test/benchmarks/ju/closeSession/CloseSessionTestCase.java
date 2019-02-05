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
package org.polarsys.capella.test.benchmarks.ju.closeSession;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.sirius.business.api.session.Session;
import org.polarsys.capella.common.lib.Memory;
import org.polarsys.capella.test.benchmarks.ju.testcases.AbstractBenchmarkTestCase;

public class CloseSessionTestCase extends AbstractBenchmarkTestCase {
  /**
   * {@inheritDoc}
   */
  @Override
  public void test() {
    long usedMemoryBefore = Memory.getUsedMemory();

    Session session = getSession(getRequiredTestModels().get(0));
    session.close(new NullProgressMonitor());

    long usedMemoryAfter = Memory.getUsedMemory();
    System.out.println(
        "Used memory: " + usedMemoryBefore / (1024 * 1024) + "MB / " + usedMemoryAfter / (1024 * 1024) + "MB.");
  }

}
