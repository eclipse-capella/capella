/*******************************************************************************
 * Copyright (c) 2018, 2020 THALES GLOBAL SERVICES.
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 * 
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 * Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.test.massactions.ju.model;

import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;
import org.polarsys.capella.common.helpers.TransactionHelper;

/**
 * Generic implementation of a read write Capella Mass Action test case. Wraps the test method into a read write
 * command.
 *
 * @author Sandu Postaru
 * 
 */
public abstract class AbstractCapellaMAReadWriteTestCase extends AbstractCapellaMATestCase {

  @Override
  public void test() throws Exception {
    final Exception[] exceptions = new Exception[] { null };

    TransactionHelper.getExecutionManager(getProject()).execute(new AbstractReadWriteCommand() {
      @Override
      public void run() {
        try {
          performTest();
        } catch (Exception e) {
          exceptions[0] = e;
        }
      }
    });

    if (exceptions[0] != null) {
      throw exceptions[0];
    }
  }
}