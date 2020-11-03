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
package org.polarsys.capella.test.benchmarks.ju.createBigLFBD;

import org.eclipse.sirius.business.api.session.Session;
import org.polarsys.capella.test.benchmarks.ju.testcases.AbstractBenchmarkTestCase;
import org.polarsys.capella.test.diagram.common.ju.context.XBreakdownDiagram;
import org.polarsys.capella.test.framework.context.SessionContext;

public class CreateBigLFBDTestCase extends AbstractBenchmarkTestCase {

  public CreateBigLFBDTestCase() {
    // Do nothing
  }

  public String getRootLFId() {
    return "0f580227-c543-436e-979f-ee70432656e2";
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void test() {
    Session session = getSession(getRequiredTestModels().get(0));
    SessionContext context = new SessionContext(session);
    XBreakdownDiagram.createFBDiagram(context, getRootLFId());
  }
}
