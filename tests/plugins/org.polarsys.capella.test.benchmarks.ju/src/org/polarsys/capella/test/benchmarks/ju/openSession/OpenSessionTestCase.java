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
package org.polarsys.capella.test.benchmarks.ju.openSession;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.sirius.business.api.session.Session;
import org.polarsys.capella.common.lib.Memory;
import org.polarsys.capella.test.benchmarks.ju.testcases.AbstractBenchmarkTestCase;
import org.polarsys.capella.test.benchmarks.ju.utils.MemoryLogger;

public class OpenSessionTestCase extends AbstractBenchmarkTestCase {

  /**
   * {@inheritDoc}
   */
  @Override
  public void test() {
    long usedMemoryBefore = Memory.getUsedMemory() / (1024 * 1024);

    Session session = getSession(getRequiredTestModels().get(0));
    if (!session.isOpen()) {
      session.open(new NullProgressMonitor());
    }

    long usedMemoryAfter = Memory.getUsedMemory() / (1024 * 1024);

    System.out.println("Used memory: " + usedMemoryBefore + "MB / " + usedMemoryAfter + "MB.");

    MemoryLogger.getInstance().log(this.getClass().getCanonicalName(), usedMemoryAfter - usedMemoryBefore);
  }

}
