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
package org.polarsys.capella.test.benchmarks.ju.refreshBigPAB;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.test.benchmarks.ju.testcases.AbstractBenchmarkTestCase;
import org.polarsys.capella.test.diagram.common.ju.wrapper.utils.DiagramHelper;

public class RefreshBigPABTestCase extends AbstractBenchmarkTestCase {

  public RefreshBigPABTestCase() {
    // Do nothing
  }

  public String getBigPABName() {
    return "[PAB] Implementation and Behaviour Components";
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void test() {
    Session session = getSession(getRequiredTestModels().get(0));
    String bigPABName = getBigPABName();

    DRepresentation dRepresentation = DiagramHelper.getDRepresentation(session, bigPABName);
    TransactionHelper.getExecutionManager(session).execute(new AbstractReadWriteCommand() {
      @Override
      public void run() {
        DialectManager.INSTANCE.refresh(dRepresentation, new NullProgressMonitor());
      }
    });
  }

}
