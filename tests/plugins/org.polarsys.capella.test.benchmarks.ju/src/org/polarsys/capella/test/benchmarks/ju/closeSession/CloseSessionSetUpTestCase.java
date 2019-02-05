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
import org.polarsys.capella.test.benchmarks.ju.testcases.AbstractSetUpTestCase;
import org.polarsys.capella.test.framework.api.BasicTestArtefact;

public class CloseSessionSetUpTestCase extends AbstractSetUpTestCase {

  public CloseSessionSetUpTestCase(BasicTestArtefact benchmarkTestCase) {
    super(benchmarkTestCase);
  }

  @Override
  public void test() throws Exception {
    Session session = getSession(getRequiredTestModels().get(0));
    if (!session.isOpen()) {
      session.open(new NullProgressMonitor());
    }
  }
}
