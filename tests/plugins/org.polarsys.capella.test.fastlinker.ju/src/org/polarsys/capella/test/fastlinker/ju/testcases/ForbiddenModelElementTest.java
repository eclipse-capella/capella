/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.fastlinker.ju.testcases;

import java.util.Collections;
import java.util.List;

import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;
import org.polarsys.capella.core.data.capellamodeller.Project;
import org.polarsys.capella.core.data.oa.OperationalAnalysis;
import org.polarsys.capella.core.model.helpers.ModelQueryHelper;
import org.polarsys.capella.core.ui.fastlinker.FastLinkerActivator;

/**
 * Check OperationalAnalysis is not accepted by the Fast Linker.
 */
public class ForbiddenModelElementTest extends AbstractFastLinkerTest {

  @Override
  public List<String> getRequiredTestModels() {
    return Collections.singletonList("TestsFastLinker");
  }

  @Override
  public void test() throws Exception {
    executeCommand(new AbstractReadWriteCommand() {
      @Override
      public void run() {
        // Tries and put a forbidden element in the Fast Linker.
        Project testProject = getTestProject();
        OperationalAnalysis operationalAnalysis = ModelQueryHelper.getOperationalAnalysis(testProject);
        //
        // Call FastLinker.
        //
        boolean acceptOperationalAnalysis = FastLinkerActivator.getDefault().getFastLinkerManager().acceptElementInFastLinker(Collections.singletonList(operationalAnalysis));
        assertFalse("Fast Linker should not accept OperationalAnalysis", acceptOperationalAnalysis);
      }
    });
  }
}
